package org.example.service;
import org.example.model.Employee;
import org.example.model.Position;
import java.util.*;

public class EmployeeService {
    private final List<Employee> employees = new ArrayList<>();

    public boolean addEmployee(Employee employee) {
        if (employee == null
                || employee.getFullName() == null || employee.getFullName().isBlank()
                || employee.getEmail() == null || employee.getEmail().isBlank()
                || employee.getCompany() == null || employee.getCompany().isBlank()
                || employee.getPosition() == null) {
            System.out.println("Nie można dodać pracownika z pustymi danymi");
            return false;
        }
        for (Employee e : employees) {
            if (e.getEmail().equalsIgnoreCase(employee.getEmail())) {
                System.out.println("Pracownik o tym emailu już istnieje: " + employee.getEmail());
                return false;
            }

        }
        employees.add(employee);
        return true;
    }

    public void showAll() {
        for (Employee e : employees) {
            System.out.println(e);
        }
    }

    public List<Employee> findEmployeesInCompany(String companyName) {
        List<Employee> result = new ArrayList<>();
        for (Employee e : employees) {
            if (e.getCompany().equalsIgnoreCase(companyName)) {
                result.add(e);
            }
        }
        return result;
    }

    public List<Employee> sortByLastName() {
        List<Employee> copy = new ArrayList<>(employees);
        Collections.sort(copy, new Comparator<Employee>() {
            @Override
            public int compare(Employee e1, Employee e2) {
                String lastName1 = e1.getFullName().split(" ")[1];
                String lastName2 = e2.getFullName().split(" ")[1];
                return lastName1.compareToIgnoreCase(lastName2);
            }
        });
        return copy;
    }

    public Map<Position, List<Employee>> groupByPosition() {
        Map<Position, List<Employee>> map = new HashMap<>();
        for (Employee e : employees) {
            Position p = e.getPosition();
            if (!map.containsKey(p)) {
                map.put(p, new ArrayList<>());
            }
            map.get(p).add(e);
        }
        return map;
    }

    public Map<Position, Integer> countEmployeesByPosition() {
        Map<Position, Integer> map = new HashMap<>();
        for (Employee e : employees) {
            Position p = e.getPosition();
            map.put(p, map.getOrDefault(p, 0) + 1);
        }
        return map;
    }

    public double averageSalary() {
        if (employees.isEmpty()) return 0;
        double sum = 0;
        for (Employee e : employees) {
            sum += e.getSalary();
        }
        return sum / employees.size();
    }

    public Employee highestPaid() {
        if (employees.isEmpty()) return null;
        Employee best = employees.get(0);
        for (Employee e : employees) {
            if (e.getSalary() > best.getSalary()) {
                best = e;
            }
        }
        return best;
    }
}
