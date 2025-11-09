package org.example.service;

import org.example.model.Employee;
import org.example.model.Position;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    private EmployeeService service;

    @BeforeEach
    void setUp() {
        service = new EmployeeService();
        service.addEmployee(new Employee("Jan Kowalski", "jan.k@techcorp.pl", "TechCorp", Position.MANAGER));
        service.addEmployee(new Employee("Anna Nowak", "anna.n@techcorp.pl", "TechCorp", Position.DEVELOPER));
        service.addEmployee(new Employee("Michał Wianiewski", "michal.w@devhouse.pl", "DevHouse", Position.INTERN));
        service.addEmployee(new Employee("Eryk Wielgat", "eryk.w@techcorp.pl", "TechCorp", Position.CEO));
        service.addEmployee(new Employee("Ewa Zielinska", "ewa.z@techcorp.pl", "TechCorp", Position.VICE_PRESIDENT));
    }

    @AfterEach
    void tearDown() {
        service = null;
    }

    @Test
    void addEmployeeShouldSucceedForUniqueEmail() {
        Employee e = new Employee("Piotr Kowalski", "piotr.k@techcorp.pl", "TechCorp", Position.MANAGER);
        assertTrue(service.addEmployee(e));
    }

    @Test
    void addEmployeeShouldFailForDuplicateEmail() {
        assertFalse(service.addEmployee(new Employee("Anna Nowak", "ewa.z@techcorp.pl", "TechCorp", Position.DEVELOPER)));
    }

    @Test
    void findEmployeesInCompanyShouldReturnCorrectEmployees() {
        List<Employee> result = service.findEmployeesInCompany("TechCorp");
        assertAll(
                () -> assertEquals(4, result.size()),
                () -> result.forEach(e -> assertEquals("TechCorp", e.getCompany()))
        );
    }

    @Test
    void sortByLastNameShouldReturnAlphabeticalOrder() {
        List<Employee> sorted = service.sortByLastName();
        assertAll(
                () -> assertEquals("Kowalski", sorted.get(0).getFullName().split(" ")[1]),
                () -> assertEquals("Nowak", sorted.get(1).getFullName().split(" ")[1]),
                () -> assertEquals("Wianiewski", sorted.get(2).getFullName().split(" ")[1]),
                () -> assertEquals("Wielgat", sorted.get(3).getFullName().split(" ")[1]),
                () -> assertEquals("Zielinska", sorted.get(4).getFullName().split(" ")[1])
        );
    }

    @Test
    void groupByPositionShouldGroupCorrectly() {
        Map<Position, List<Employee>> groups = service.groupByPosition();
        assertAll(
                () -> assertEquals(1, groups.get(Position.CEO).size()),
                () -> assertEquals(1, groups.get(Position.VICE_PRESIDENT).size()),
                () -> assertEquals(1, groups.get(Position.DEVELOPER).size()),
                () -> assertEquals(1, groups.get(Position.MANAGER).size()),
                () -> assertEquals(1, groups.get(Position.INTERN).size())
        );
    }

    @Test
    void countEmployeesByPositionShouldCountCorrectly() {
        Map<Position, Integer> counts = service.countEmployeesByPosition();
        assertAll(
                () -> assertEquals(1, counts.get(Position.CEO)),
                () -> assertEquals(1, counts.get(Position.VICE_PRESIDENT)),
                () -> assertEquals(1, counts.get(Position.DEVELOPER)),
                () -> assertEquals(1, counts.get(Position.MANAGER)),
                () -> assertEquals(1, counts.get(Position.INTERN))
        );
    }

    @Test
    void averageSalaryShouldReturnZeroForEmptyList() {
        EmployeeService service2 = new EmployeeService();
        assertEquals(0.0, service2.averageSalary());
    }

    @Test
    void averageSalaryShouldReturnCorrectValue() {
        assertEquals(13200.0, service.averageSalary());
    }

    @Test
    void highestPaidShouldReturnNullForEmptyList() {
        EmployeeService service2 = new EmployeeService();
        assertNull(service2.highestPaid());
    }

    @Test
    void highestPaidShouldReturnEmployeeWithMaxSalary() {
        Employee top = service.highestPaid();
        assertEquals(Position.CEO.getBaseSalary(), top.getPosition().getBaseSalary());
    }

    @Test
    void shouldNotAddEmployeeWithEmptyName() {
        Employee e = new Employee("", "test@firma.pl", "Firma", Position.DEVELOPER);
        assertFalse(service.addEmployee(e));
    }

    @Test
    void shouldNotAddEmployeeWithEmptyEmail() {
        Employee e = new Employee("Jan Nowak", "", "Firma", Position.DEVELOPER);
        assertFalse(service.addEmployee(e));
    }

    @Test
    void shouldNotAddEmployeeWithEmptyCompany() {
        Employee e = new Employee("Jan Nowak", "jan.nowak@firma.pl", "", Position.DEVELOPER);
        assertFalse(service.addEmployee(e));
    }

    @Test
    void shouldNotAddEmployeeWithNullPosition() {
        assertThrows(IllegalArgumentException.class, () ->
                new Employee("Jan Nowak", "jan.nowak@firma.pl", "Firma", null)
        );
    }


    @Test
    void shouldNotAddNullEmployee() {
        assertFalse(service.addEmployee(null));
    }
}
