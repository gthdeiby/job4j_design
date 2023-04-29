package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import javax.xml.bind.*;
import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.function.Predicate;

public class ReportXml implements Report {

    private final Store store;
    private final Marshaller marshaller;

    public ReportXml(Store store) throws JAXBException {
        this.store = store;
        JAXBContext context = JAXBContext.newInstance(Employees.class);
        this.marshaller = context.createMarshaller();
    }

    @Override
    public String generate(Predicate<Employee> filter) throws JAXBException {
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(new Employees(store.findBy(filter)), writer);
            xml = writer.getBuffer().toString();
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return xml;
    }

    @XmlRootElement(name = "employees")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Employees {

        @XmlElement(name = "employee")
        private List<Employee> employees;

        public Employees() {
        }

        public Employees(List<Employee> employees) {
            this.employees = employees;
        }

        public List<Employee> getEmployees() {
            return employees;
        }

        public void setEmployee(List<Employee> employees) {
            this.employees = employees;
        }
    }
}
