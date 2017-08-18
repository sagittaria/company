package company.data;

import java.util.*;

import org.springframework.transaction.annotation.Transactional;

import company.Employee;
import company.StatisticResult;

@Transactional
public interface EmployeeDao {
    List<Employee> findAll();
    Employee findById(int id);
    Employee save(Employee employee);
    Employee update(Employee employee);
    void deleteById(int id);
    List<StatisticResult> getStatisticResult();
}
