package employee_management.mapper;

import employee_management.dto.EmployeeDto;
import employee_management.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDto toDto(Employee employee);
    Employee toEntity(EmployeeDto employeeDto);

    void updateEntity(EmployeeDto dto, @MappingTarget Employee employee);

}
