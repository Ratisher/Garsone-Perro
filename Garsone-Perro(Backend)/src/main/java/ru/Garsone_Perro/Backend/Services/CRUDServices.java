package ru.Garsone_Perro.Backend.Services;

import java.util.List;
import ru.Garsone_Perro.Backend.Dto.UserDto;

public interface CRUDServices {
    UserDto createUser(UserDto userDto);
    UserDto getUserById(Long userId);
    List <UserDto> getAllUsers();
    UserDto updateUserById(UserDto userDto, Long userId);
    void deleteUser(Long userId);
}
