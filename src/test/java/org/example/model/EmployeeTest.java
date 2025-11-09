package org.example.model;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    private Employee employee1;
    private Employee employee2;

    @BeforeEach
    void before(){
        employee1 = new Employee("Piotr Nowak","pnowak@techcorp.pl","techcorp", Position.DEVELOPER);
        employee2 = new Employee("Maciej Kicki","mkicki@techcorp.pl","techcorp",Position.DEVELOPER);
    }

    @AfterEach
    void after(){
        employee1=null;
        employee2=null;

    }

    @Test
    void equalsShouldReturnTrueForSameEmail(){
        Employee e = new Employee("Piotr Nowakowski","pnowak@techcorp.pl","PolyBrick",Position.INTERN);
        assertEquals(e,employee1);
    }
    @Test
    void equalsShouldReturnFalseForDiffrentEmail(){
        assertNotEquals(employee1,employee2);
    }
    @Test
    void hashCodeShouldBeSameForSameEmail(){
        Employee e = new Employee("Piotr Nowakowski","pnowak@techcorp.pl","PolyBrick",Position.INTERN);
        assertEquals(e.hashCode(),employee1.hashCode());
    }
    @Test
    void salaryShouldBeFromPosition(){
        assertEquals(Position.DEVELOPER.getBaseSalary(),employee2.getSalary());
    }
    @Test
    void settingNegativeSalaryShouldThrowException() {
        Employee e = new Employee("Jan Kowalski", "jan.k@firma.pl", "Firma", Position.MANAGER);

        assertThrows(IllegalArgumentException.class, () -> e.setSalary(-1000));
    }


}
