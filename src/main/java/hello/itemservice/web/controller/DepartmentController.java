package hello.itemservice.web.controller;

import hello.itemservice.depart.domain.Department;
import hello.itemservice.depart.repository.DepartmentRepository;
import hello.itemservice.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentRepository departmentRepository;

    @GetMapping
    public String departments(Model model) {
        List<Department> departments = departmentRepository.findAll();
        model.addAttribute("departments", departments);
        return "department/departments";
    }

//    @GetMapping
//    public String department(@PathVariable long departmentId){
//        Department department = departmentRepository.findById(departmentId);
//
//
//    }


    @GetMapping("/add")
    public String addForm(Model model){
        model.addAttribute("department", new Department());
        return "department/addForm";
    }






}
