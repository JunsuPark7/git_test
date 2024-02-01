package hello.itemservice.web.basic;

import hello.itemservice.depart.domain.Department;
import hello.itemservice.depart.repository.DepartmentRepository;
import hello.itemservice.member.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/basic/departments")
public class DepartmentController {

    private final DepartmentRepository departmentRepository;

    public DepartmentController(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @GetMapping
    public String departments(Model model) {
        List<Department> departments = departmentRepository.findAll();
        model.addAttribute("Departments", departments);
        return "department/departments";
    }



}
