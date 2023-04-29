package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import javax.xml.bind.JAXBException;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class ReportJsonTest {
    @Test
    public void whenJsonGenerated() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Diman", now, now, 200);
        Employee worker2 = new Employee("Ivan", now, now, 100);
        store.add(worker1);
        store.add(worker2);
        Report json = new ReportJson(store);
        StringBuilder expect = new StringBuilder()
                .append(String.format("[{\"name\":\"%s\",", worker1.getName()))
                .append(String.format("\"hired\":{\"year\":%s,\"month\":%s,\"dayOfMonth\":%s,\"hourOfDay\":%s,\"minute\":%s,\"second\":%s},",
                        worker1.getHired().get(Calendar.YEAR), worker1.getHired().get(Calendar.MONTH), worker1.getHired().get(Calendar.DAY_OF_MONTH),
                        worker1.getHired().get(Calendar.HOUR_OF_DAY), worker1.getHired().get(Calendar.MINUTE), worker1.getHired().get(Calendar.SECOND)))
                .append(String.format("\"fired\":{\"year\":%s,\"month\":%s,\"dayOfMonth\":%s,\"hourOfDay\":%s,\"minute\":%s,\"second\":%s},",
                        worker1.getFired().get(Calendar.YEAR), worker1.getFired().get(Calendar.MONTH), worker1.getFired().get(Calendar.DAY_OF_MONTH),
                        worker1.getFired().get(Calendar.HOUR_OF_DAY), worker1.getFired().get(Calendar.MINUTE), worker1.getFired().get(Calendar.SECOND)))
                .append(String.format("\"salary\":%s},", worker1.getSalary()))
                .append(String.format("{\"name\":\"%s\",", worker2.getName()))
                .append(String.format("\"hired\":{\"year\":%s,\"month\":%s,\"dayOfMonth\":%s,\"hourOfDay\":%s,\"minute\":%s,\"second\":%s},",
                        worker2.getHired().get(Calendar.YEAR), worker2.getHired().get(Calendar.MONTH), worker2.getHired().get(Calendar.DAY_OF_MONTH),
                        worker2.getHired().get(Calendar.HOUR_OF_DAY), worker2.getHired().get(Calendar.MINUTE), worker2.getHired().get(Calendar.SECOND)))
                .append(String.format("\"fired\":{\"year\":%s,\"month\":%s,\"dayOfMonth\":%s,\"hourOfDay\":%s,\"minute\":%s,\"second\":%s},",
                        worker2.getFired().get(Calendar.YEAR), worker2.getFired().get(Calendar.MONTH), worker2.getFired().get(Calendar.DAY_OF_MONTH),
                        worker2.getFired().get(Calendar.HOUR_OF_DAY), worker2.getFired().get(Calendar.MINUTE), worker2.getFired().get(Calendar.SECOND)))
                .append(String.format("\"salary\":%s}]", worker2.getSalary()));
        assertThat(json.generate(em -> true)).isEqualTo(expect.toString());
    }
}