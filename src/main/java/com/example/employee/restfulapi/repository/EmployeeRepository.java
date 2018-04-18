package com.example.employee.restfulapi.repository;

import com.example.employee.restfulapi.entity.Employee;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAll();

    Employee getEmployeeById(Long id);

    Page<Employee> findAll(Pageable pageable);

    List<Employee> getEmployeesByGender(String gender);

    Employee save(Employee employee);

    @Modifying
    @Transactional
    @Query("update Employee e set e.name = ?2, e.age = ?3, e.gender = ?4, e.salary = ?5, e.companyId = ?6 where e.id = ?1")
    int updateById(Long id, String name, Integer age, String gender, Integer salary, Long companyId);

    @Transactional
    void deleteById(Long id);
}
