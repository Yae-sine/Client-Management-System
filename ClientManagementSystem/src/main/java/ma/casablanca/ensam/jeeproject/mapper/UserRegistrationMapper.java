package ma.casablanca.ensam.jeeproject.mapper;

import ma.casablanca.ensam.jeeproject.dao.entities.User;
import ma.casablanca.ensam.jeeproject.dto.UserRegistrationDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationMapper {
    @Autowired
    private ModelMapper mapper;

    public UserRegistrationDto toDto(User user){
        return mapper.map(user , UserRegistrationDto.class);
    }

    public User toEntity(UserRegistrationDto userDto){
        return  mapper.map(userDto , User.class);
    }
}
