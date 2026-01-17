package com.app.service;

import com.app.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO create(EmployeeDTO dto);
    EmployeeDTO getById(Long id);
    List<EmployeeDTO> getAll();
    void delete(Long id);
    EmployeeDTO update(Long id, EmployeeDTO dto);


}
