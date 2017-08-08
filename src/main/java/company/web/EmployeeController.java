package company.web;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import company.Employee;
import company.data.EmployeeDao;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeDao employeeDao;

    @Autowired
    public EmployeeController(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String All(Model model) {
        List<Employee> employeeList = employeeDao.findAll();
        model.addAttribute(employeeList);
        return "AllEmployeeList";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String findById(@PathVariable int id, Model model) {
        Employee employee = employeeDao.findById(id);
        model.addAttribute(employee);
        return "SpeificEmployeeInfo";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("FormFunc", "新增雇员");
        model.addAttribute(new Employee());
        return "EmployeeForm";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addExec(Employee employee) {
        employeeDao.save(employee);
        return "redirect:" + employee.getId();
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable int id, Model model) {
        Employee employee = employeeDao.findById(id);
        model.addAttribute("FormFunc", "修改详情(id="+id+")");
        model.addAttribute(employee);
        return "EmployeeForm";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    public String editExec(@PathVariable int id, Employee employee) {
        employee.setId(id);
        employeeDao.update(employee);
        return "redirect:../" + employee.getId();
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String deleteExec(@PathVariable int id) {
        employeeDao.deleteById(id);
        return "redirect:../";
    }
}
