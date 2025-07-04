package com.example.demo.emp.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.emp.dto.EmployeeDto;
import com.example.demo.emp.exception.ResourceNotFoundException;
import com.example.demo.emp.mapper.EmployeeMapper;
import com.example.demo.emp.model.Employee;
import com.example.demo.emp.repository.EmployeeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

	private EmployeeRepository empRepository;
	
	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
	    Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
	    Employee savedEmployee = empRepository.save(employee);
		return EmployeeMapper.mapToEmployeeDto(savedEmployee);
	}

	@Override
	public EmployeeDto getEmployeeById(Long employeeId) {
		Employee employee = empRepository.findById(employeeId).orElseThrow(()-> new ResourceNotFoundException("Employee is not exists with given id :" +employeeId));
		return EmployeeMapper.mapToEmployeeDto(employee);
	}

	@Override
	public List<EmployeeDto> getAllEmployees() {
		List<Employee> employees = empRepository.findAll();
		return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee)).collect(Collectors.toList());
	}

	@Override
	public EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeDto) {
		Employee employee = empRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee is not exists with given id :" + employeeId));
		employee.setFirstName(employeeDto.getFirstName());
		employee.setLastName(employeeDto.getLastName());
		employee.setEmail(employeeDto.getEmail());
		Employee emp = empRepository.save(employee);
		return EmployeeMapper.mapToEmployeeDto(emp);
	}

	@Override
	public void deleteEmployee(Long employeeId) {
		Employee employee = empRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee is not exists with given id :" + employeeId));
		empRepository.deleteById(employeeId);
		
	}

}
