package company.data;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import company.Employee;

@Transactional
public interface EmployeeDao {
    List<Employee> findAll();
    Employee findById(int id);
    Employee save(Employee employee);
    Employee update(Employee employee);
    void deleteById(int id);
}
