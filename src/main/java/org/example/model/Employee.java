package org.example.model;
import java.util.Objects;

public class Employee {
    private final String fullName;
    private final String email;
    private final String company;
    private final Position position;
    private double salary;

    public Employee(String fullName, String email, String company, Position position) {
        if (position == null) {
            throw new IllegalArgumentException("pozycja nie moze być null");
        }
        this.fullName = fullName;
        this.email = email;
        this.company = company;
        this.position = position;
        this.salary = position.getBaseSalary();
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getCompany() {
        return company;
    }

    public Position getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary <= 0) {
            throw new IllegalArgumentException("Wynagrodzenie musi być większe od zera");
        }
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee that = (Employee) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - %s, %.2f zł, %s",
                fullName, email, position, salary, company);
    }
}
