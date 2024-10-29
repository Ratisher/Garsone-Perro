package ru.Garsone_Perro.Backend.Mapper;

import ru.Garsone_Perro.Backend.Dto.UserDto;
import ru.Garsone_Perro.Backend.Entities.User;

public class UserMapper {
    public static UserDto mapToUserDto(User user) {
        return new UserDto(
        user.getId(),
        user.getLogin(),
        user.getPassword(),
        user.getNickname(),
        user.getEmail(),
        user.getExperience(),
        user.getAvatar()
        );
    }
    
    public static User mapToUser(UserDto userDto) {
        return new User(
        userDto.getId(),
        userDto.getLogin(),
        userDto.getPassword(),
        userDto.getNickname(),
        userDto.getEmail(),
        userDto.getExperience(),
        userDto.getAvatar()
        );
    }
}
