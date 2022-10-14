package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private final Properties properties;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("driver_class"));
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        connection = DriverManager.getConnection(url, login, password);
    }

    public void createTable(String tableName) throws Exception {
        initStatement(String.format(
                    "create table if not exists %s();", tableName));
        System.out.println(getTableScheme(connection, tableName));
    }

    public void dropTable(String tableName) throws Exception {
        initStatement(String.format(
                "drop table if exists %s;", tableName));
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        initStatement(String.format(
                "alter table %s add column %s %s", tableName, columnName, type));
        System.out.println(getTableScheme(connection, tableName));
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        initStatement(String.format(
                "alter table %s drop column %s;", tableName, columnName));
        System.out.println(getTableScheme(connection, tableName));
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        initStatement(String.format(
                "alter table %s rename column %s to %s", tableName, columnName, newColumnName));
        System.out.println(getTableScheme(connection, tableName));
    }


    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    private void initStatement(String sql) throws Exception {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("table.properties")) {
            config.load(in);
            TableEditor tableEditor = new TableEditor(config);
            tableEditor.createTable("cars");
            tableEditor.addColumn("cars", "model", "text");
            tableEditor.addColumn("cars", "year", "int");
            tableEditor.renameColumn("cars", "year", "production_year");
            tableEditor.dropColumn("cars", "production_year");
            tableEditor.dropTable("cars");
        }
    }
}