package company.data;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import company.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    private SessionFactory sessionFactory;

    @Autowired
    public EmployeeDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    public List<Employee> findAll() {
        return (List<Employee>) currentSession().createCriteria(Employee.class).list();
    }

    public Employee findById(int id) {
        return (Employee) currentSession().get(Employee.class, id);
    }

    public Employee save(Employee employee) {
        Serializable id = currentSession().save(employee);
        return new Employee((Integer) id, 
                    employee.getName(), 
                    employee.getEmail());
    }
    
    public Employee update(Employee employee) {
        currentSession().update(employee);
        return employee;
    }

    public void deleteById(int id) {
        currentSession().delete(findById(id));
    }
}
