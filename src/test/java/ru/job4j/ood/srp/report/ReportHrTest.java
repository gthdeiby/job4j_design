package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.comparators.SalaryByDesc;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import javax.xml.bind.JAXBException;
import java.util.Calendar;
import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

class ReportHrTest {

    @Test
    public void whenOldGenerated() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Comparator<Employee> comparator = new SalaryByDesc();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Dmitry", now, now, 200);
        store.add(worker1);
        store.add(worker2);
        Report hr = new ReportHr(store, comparator);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(" ")
                .append(worker2.getSalary())
                .append(System.lineSeparator())
                .append(worker1.getName()).append(" ")
                .append(worker1.getSalary())
                .append(System.lineSeparator());
        assertThat(hr.generate(em -> true)).isEqualTo(expect.toString());
    }
}