package com.gamenism;

import com.gamenism.dao.EmployeeDao;
import com.gamenism.model.Employee;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * User: halil
 * Date: 9/27/13
 * Time: 11:18 PM
 */
public class EmployeeTest extends AbstractTest {
    @Autowired
    EmployeeDao employeeDao;

    @Test
    public void testCreate() {
        persist();
    }

    @Test
    public void testSelect() {
        Employee persist = persist();
        Long id = persist.getId();
        System.out.println("id = " + id);
        employeeDao.clear();

        Employee employee = employeeDao.find(id);
        System.out.println("employee = " + employee.getId());
        assertEquals(id, employee.getId());
    }

    private Employee persist() {
        Employee employee = new Employee();
        employee.setTitle("Mr.");
        employee.setHiddenValue("Bey");
        employee.setName("Halil");

        Employee employee1 = employeeDao.create(employee);
        return employee1;
    }
}
