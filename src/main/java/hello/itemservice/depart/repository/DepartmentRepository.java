package hello.itemservice.depart.repository;

import hello.itemservice.depart.domain.Department;
import hello.itemservice.member.domain.Member;

import java.util.List;

public interface DepartmentRepository {


    public Department save(Department department);
    public Department findById(Long id);

    public List<Department> findAll();

    public void update(Long departmentId, Department updateParam);

    public void delete(Long departmentId);
}
