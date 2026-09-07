package employee_management;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import employee_management.dto.EmployeeDto;
import employee_management.entity.Employee;
import employee_management.exception.ResourceNotFoundException;
import employee_management.mapper.EmployeeMapper;
import employee_management.repository.EmployeeRepository;
import employee_management.service.EmployeeServiceImpl;

@ExtendWith(MockitoExtension.class)
@DisplayName("EmployeeServiceImpl Unit Tests")
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeeServiceImpl employeeServiceImpl;
    
    @DisplayName("create employee tests")
    @Nested
    class createEmployeeTests{

        @Test
        void shouldCreateEmployee(){

            EmployeeDto inputDto = new EmployeeDto();
            Employee newEmployee = new Employee();
            Employee savedEmployee = new Employee();
            EmployeeDto expectedEmployee = new EmployeeDto();

            when(employeeMapper.toEntity(inputDto))
            .thenReturn(newEmployee);

            when(employeeRepository.save(newEmployee))
            .thenReturn(savedEmployee);

            when(employeeMapper.toDto(savedEmployee))
            .thenReturn(expectedEmployee);

            // ACT

            EmployeeDto result = employeeServiceImpl.createEmployee(inputDto);

            // Assert

            assertEquals(expectedEmployee, result);

            // Verify
            
            verify(employeeMapper).toEntity(inputDto);
            verify(employeeRepository).save(newEmployee);
            verify(employeeMapper).toDto(savedEmployee);
        }
    }

    @DisplayName("get employee test case")
    @Nested
    class getEmployeeTest{

        @Test
        void shouldReturnEmployeeById(){

            Employee DBemployee = new Employee();
            EmployeeDto expectedEmployee = new EmployeeDto();
            Long employeeId = 1L;
            
            when(employeeRepository.findById(employeeId))
            .thenReturn(Optional.of(DBemployee));

            when(employeeMapper.toDto(DBemployee))
            .thenReturn(expectedEmployee);

            // ACT

            EmployeeDto result = employeeServiceImpl.getEmployeeById(employeeId);
            
            // Assert

            assertEquals(expectedEmployee, result);

            // Verify

            verify(employeeRepository).findById(employeeId);
            verify(employeeMapper).toDto(DBemployee);
        }

        @Test
        void shouldThrowExceptionWhenEmployeeNotFound() {
            
            Long employeeId = 1L;

            when(employeeRepository.findById(employeeId))
            .thenReturn(Optional.empty());

            assertThrows(ResourceNotFoundException.class, () -> {
                employeeServiceImpl.getEmployeeById(employeeId);
            });

            verify(employeeRepository).findById(employeeId);
        }

        @Test
        void shoudlReturnAllEmployee(){

            List<Employee> employees = new ArrayList<>();
            Employee employee = new Employee();
            EmployeeDto employeeDto = new EmployeeDto();

            employees.add(employee);

            when(employeeRepository.findAll())
            .thenReturn(employees);

            when(employeeMapper.toDto(employee))
            .thenReturn(employeeDto);

            // Act
            List<EmployeeDto> result = employeeServiceImpl.getAllEmployee();

            // Assert
            assertEquals(List.of(employeeDto), result);

            // verify
            verify(employeeRepository).findAll();
            verify(employeeMapper).toDto(employee);

        }

    }

}
