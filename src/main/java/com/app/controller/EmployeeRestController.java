package com.app.controller;

import com.app.dto.EmployeeDTO;
import com.app.model.Employee;
import com.app.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeRestController {

    private EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/create")
    public EmployeeDTO saveEmp(@RequestBody EmployeeDTO employeeDTO)
        {
            return employeeService.create(employeeDTO);
        }

    @GetMapping("/{id}")
    public EmployeeDTO getEmployeeBYId(@PathVariable Long id)
        {
            return employeeService.getById(id);
        }

    @GetMapping("/allemp")
    public List<EmployeeDTO> getAllEmployee()
        {
            return employeeService.getAll();
        }

    @PutMapping("/update/{id}")
    public EmployeeDTO updateEmp(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO )
        {
            return employeeService.update(id,employeeDTO);
        }
    @DeleteMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id)
        {
            employeeService.delete(id);

            return "Employee deleted successfully";
        }
}
