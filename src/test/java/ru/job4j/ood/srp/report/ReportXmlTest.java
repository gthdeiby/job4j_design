package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import javax.xml.bind.JAXBException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class ReportXmlTest {

    @Test
    public void whenXmlGenerated() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Employee worker1 = new Employee("Diman", now, now, 200);
        Employee worker2 = new Employee("Ivan", now, now, 100);
        store.add(worker1);
        store.add(worker2);
        Report xml = new ReportXml(store);
        StringBuilder expect = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n")
                .append("<employees>\n")
                .append("    <employee>\n")
                .append(String.format("        <fired>%s</fired>\n", dateFormat.format(worker1.getFired().getTime())))
                .append(String.format("        <hired>%s</hired>\n", dateFormat.format(worker1.getHired().getTime())))
                .append(String.format("        <name>%s</name>\n", worker1.getName()))
                .append(String.format("        <salary>%s</salary>\n", worker1.getSalary()))
                .append("    </employee>\n")
                .append("    <employee>\n")
                .append(String.format("        <fired>%s</fired>\n", dateFormat.format(worker2.getFired().getTime())))
                .append(String.format("        <hired>%s</hired>\n", dateFormat.format(worker2.getHired().getTime())))
                .append(String.format("        <name>%s</name>\n", worker2.getName()))
                .append(String.format("        <salary>%s</salary>\n", worker2.getSalary()))
                .append("    </employee>\n")
                .append("</employees>\n");
        assertThat(xml.generate(em -> true)).isEqualTo(expect.toString());
    }
}