package employee_management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import employee_management.dto.EmployeeDto;
import employee_management.entity.Employee;
import employee_management.exception.ResourceNotFoundException;
import employee_management.mapper.EmployeeMapper;
import employee_management.repository.EmployeeRepository;
 

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper){
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

        @Override
        public EmployeeDto createEmployee(EmployeeDto employeeDto){
            Employee employee = employeeMapper.toEntity(employeeDto);
            Employee savedEmployee = employeeRepository.save(employee);

            return employeeMapper.toDto(savedEmployee);
        }

        @Override
        public EmployeeDto getEmployeeById(Long employeeId){
           Employee employee =  employeeRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("employee does not exist with this given id" + employeeId));
            
            return employeeMapper.toDto(employee);
        }
        
        @Override
        public List<EmployeeDto> getAllEmployee(){
            List<Employee> employees = employeeRepository.findAll();

            return employees.stream()
                            .map((emp) -> employeeMapper.toDto(emp))
                            .toList();
        }

        @Override
        public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee){
            Employee emp = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("employee does not exist with this given id" + employeeId));

            employeeMapper.updateEntity(updatedEmployee, emp);
            employeeRepository.save(emp);

            return employeeMapper.toDto(emp);
        }

        @Override
        public void deleteEmployee(Long employeeId){
            Employee emp = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("employee does not exist with this given id" + employeeId));

            employeeRepository.delete(emp);

            
        }

}
