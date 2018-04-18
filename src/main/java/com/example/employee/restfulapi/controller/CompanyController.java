package com.example.employee.restfulapi.controller;

import com.example.employee.restfulapi.entity.Company;
import com.example.employee.restfulapi.entity.Employee;
import com.example.employee.restfulapi.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("/companies")
public class CompanyController {
    //在此处完成Company API
    @Autowired
    CompanyRepository companyRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Company getCompanyById(@PathVariable Long id) {
        return companyRepository.getCompanyById(id);
    }

    @RequestMapping(value = "/{id}/employees", method = RequestMethod.GET)
    public List<Employee> getEmployeesByCompanyId(@PathVariable Long id) {
        return companyRepository.getEmployeesByCompanyId(id);
    }

    @RequestMapping(value = "/page/{page}/pageSize/{pageSize}", method = RequestMethod.GET)
    public Page<Company> getPagesCompanies(@PathVariable int page, @PathVariable int pageSize) {
        return companyRepository.findAll(new PageRequest(page, pageSize));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Company saveCompany(Company company) throws Exception {
        if (company == null) {
            throw new Exception("Employee can not be null!");
        }

        if (company.getCompanyName() == null || company.getEmployeesNumber() == null) {
            throw new Exception("Invalid Company!");
        }
        return companyRepository.save(company);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Company updateCompany(@PathVariable Long id, @ModelAttribute Company company) throws Exception {
        if (getCompanyById(id) == null) {
            throw new Exception("There is no such Company which id is" + id);
        }
        companyRepository.updateById(id, company.getCompanyName(), company.getEmployeesNumber());
        return companyRepository.getCompanyById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Company deleteCompany(@PathVariable Long id) throws Exception {
        if (getCompanyById(id) == null) {
            throw new Exception("There is no such Company which id is" + id);
        }
        companyRepository.deleteById(id);
        return null;
    }

}
