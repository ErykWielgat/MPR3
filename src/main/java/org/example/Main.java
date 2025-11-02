package org.example;

import org.example.model.Employee;
import org.example.model.Position;
import org.example.service.EmployeeService;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        EmployeeService system = new EmployeeService();

        system.addEmployee(new Employee("Jan Kowalski", "jan.k@techcorp.pl", "TechCorp", Position.MANAGER));
        system.addEmployee(new Employee("Anna Nowak", "anna.n@techcorp.pl", "TechCorp", Position.DEVELOPER));
        system.addEmployee(new Employee("Michal Wisniewski", "michal.w@devhouse.pl", "DevHouse", Position.INTERN));
        system.addEmployee(new Employee("Eryk Wielgat", "eryk.w@techcorp.pl", "TechCorp", Position.CEO));
        system.addEmployee(new Employee("Ewa Zielinska", "ewa.z@techcorp.pl", "TechCorp", Position.VICE_PRESIDENT));

        System.out.println("\n********* Wszyscy pracownicy *********");
        system.showAll();

        System.out.println("\n********* Pracownicy firmy TechCorp *********");
        List<Employee> techcorp = system.findEmployeesInCompany("TechCorp");
        for (Employee e : techcorp) {
            System.out.println(e);
        }

        System.out.println("\n********* Posortowani po nazwisku *********");
        List<Employee> sorted = system.sortByLastName();
        for (Employee e : sorted) {
            System.out.println(e);
        }

        System.out.println("\n********* Grupowanie po stanowisku *********");
        Map<Position, List<Employee>> groups = system.groupByPosition();
        for (Position p : groups.keySet()) {
            System.out.println(p + ": " + groups.get(p).size() + " pracownikow");
        }

        System.out.println("\n********* Zliczanie pracownikow *********");
        Map<Position, Integer> counts = system.countEmployeesByPosition();
        for (Position p : counts.keySet()) {
            System.out.println(p + ": " + counts.get(p));
        }

        System.out.println("\n********* Srednie wynagrodzenie *********");
        System.out.printf("%.2f zl%n", system.averageSalary());

        System.out.println("\n********* Najlepiej zarabiający *********");
        Employee top = system.highestPaid();
        if (top != null) {
            System.out.println(top);
        }
    }
}
