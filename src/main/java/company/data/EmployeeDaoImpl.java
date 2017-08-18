package company.data;

import java.io.Serializable;
import java.util.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import company.Employee;
import company.StatisticResult;

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
        return new Employee((Integer) id, employee.getName(), employee.getEmail());
    }

    public Employee update(Employee employee) {
        currentSession().update(employee);
        return employee;
    }

    public void deleteById(int id) {
        currentSession().delete(findById(id));
    }

    public List<StatisticResult> getStatisticResult() {
        List<StatisticResult> statisticResult = new ArrayList<StatisticResult>();
        String hql = "select e.age, count(e) from Employee e group by e.age order by e.age desc";
        @SuppressWarnings("unchecked")
        List<Object[]> list = currentSession().createQuery(hql).list();
        for (Object[] object : list) {      //没什么好的办法，调试的时候查看具体的数据类型，用时强转
            int age = (Integer) object[0];  // 数据库int(11)，object[0]本身也是个object，所以先转Integer再赋给int
            long count = (Long) object[1];  // 可能mysql的count(*)结果类型就是long定死的，下面为了配合它就在StatisticResult里也写long
            StatisticResult element = new StatisticResult(age, count);
            statisticResult.add(element);
        }
        return statisticResult;
    }

}
