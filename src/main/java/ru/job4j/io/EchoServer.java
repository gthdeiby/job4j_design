package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String start = in.readLine();
                    System.out.println(start);
                    String message = start.split(" ")[1].split("=")[1];
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    switch (message) {
                        case "Hello":
                            out.write("Hello!".getBytes());
                            break;
                        case "Exit":
                            out.write("Bye!".getBytes());
                            server.close();
                            break;
                        default:
                            out.write("What?".getBytes());
                    }
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                    }
                    out.flush();
                }
            }
        }
    }
}