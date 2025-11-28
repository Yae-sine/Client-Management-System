package ma.casablanca.ensam.jeeproject.mapper;


import ma.casablanca.ensam.jeeproject.dao.entities.Employee;
import ma.casablanca.ensam.jeeproject.dto.EmployeeDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeMapper {
    @Autowired
    private ModelMapper mapper ;

    public EmployeeDto toDto(Employee employee){
        return mapper.map(employee , EmployeeDto.class);
    }

    public Employee toEntity(EmployeeDto employeeDto){
        return mapper.map(employeeDto , Employee.class);
    }
}
