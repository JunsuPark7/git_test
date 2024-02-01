package hello.itemservice.depart.repository;
import hello.itemservice.depart.domain.Department;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Repository
@Transactional
public class DBDepartmentRepository implements DepartmentRepository{
    private EntityManager em;

    public DBDepartmentRepository(EntityManager em) {
        this.em = em;
    }
    @Override
    public Department save(Department department) {
        em.persist(department);
        return em.find(Department.class, department.getId());
    }
    @Override
    public Department findById(Long id) {
        return em.find(Department.class, id);
    }
    @Override
    public List<Department> findAll() {
        return em.createQuery("select d from Department d", Department.class)
                .getResultList();
    }
    @Override
    public void update(Long departmentId, Department updateParam) {
        Department findDepartment = em.find(Department.class, departmentId);
        findDepartment.setName(updateParam.getName());
    }
    @Override
    public void delete(Long departmentId) {
        Department findDepartment = em.find(Department.class, departmentId);
        em.remove(findDepartment);
    }
}
