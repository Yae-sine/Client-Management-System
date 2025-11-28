package ma.casablanca.ensam.jeeproject.mapper;

import ma.casablanca.ensam.jeeproject.dao.entities.User;
import ma.casablanca.ensam.jeeproject.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


@Service
public class UserMapper {
    @Autowired
    private ModelMapper mapper;

    public UserDto toDto(User user){
        return mapper.map(user , UserDto.class);
    }

    public User toEntity(UserDto userDto){
        return  mapper.map(userDto , User.class);
    }
}
