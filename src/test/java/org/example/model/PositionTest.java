package org.example.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PositionTest {
    @Test
    void salaryShouldMatchValues() {
        assertAll("Salary checks",
                () -> assertEquals(25000, Position.CEO.getBaseSalary()),
                () -> assertEquals(18000, Position.VICE_PRESIDENT.getBaseSalary()),
                () -> assertEquals(12000, Position.MANAGER.getBaseSalary()),
                () -> assertEquals(8000, Position.DEVELOPER.getBaseSalary()),
                () -> assertEquals(3000, Position.INTERN.getBaseSalary())
        );
    }

    @Test
    void hierarchyLevelShouldMatchValue() {
        assertAll("Hierarchy levels",
                () -> assertEquals(1, Position.CEO.getHierarchyLevel()),
                () -> assertEquals(2, Position.VICE_PRESIDENT.getHierarchyLevel()),
                () -> assertEquals(3, Position.MANAGER.getHierarchyLevel()),
                () -> assertEquals(4, Position.DEVELOPER.getHierarchyLevel()),
                () -> assertEquals(5, Position.INTERN.getHierarchyLevel())
        );
    }

}
