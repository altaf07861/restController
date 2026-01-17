package com.app.service;

import com.app.dto.EmployeeDTO;
import com.app.model.Employee;
import com.app.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImp implements EmployeeService{

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImp(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    private EmployeeDTO mapToDTO(Employee employee)
        {
            return new EmployeeDTO(employee.getName(),employee.getEmail(),employee.getSalary());
        }

    @Override
    public EmployeeDTO create(EmployeeDTO dto) {
        Employee employee=new Employee(dto.name(),dto.email(),dto.salary());
        Employee saveEmp=employeeRepository.save(employee);
        return mapToDTO(saveEmp);
    }

    @Override
    public EmployeeDTO getById(Long id) {
        Employee employee=employeeRepository.findById(id).
                orElseThrow(()->new RuntimeException("User not found with this id : "+id));
        return mapToDTO(employee);
    }

    @Override
    public List<EmployeeDTO> getAll() {
        return employeeRepository.findAll().stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public void delete(Long id) {
        if(!employeeRepository.existsById(id))
            {
                throw new RuntimeException("User not found with this id : "+id);
            }
        employeeRepository.deleteById(id);
    }

    @Override
    public EmployeeDTO update(Long id, EmployeeDTO dto) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found with this id : " + id)
                );

        if (dto.name() != null) {
            employee.setName(dto.name());
        }

        if (dto.email() != null) {
            employee.setEmail(dto.email());
        }

        if (dto.salary() != null) {
            employee.setSalary(dto.salary());
        }

        return mapToDTO(employeeRepository.save(employee));
    }

}
