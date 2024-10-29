package ru.Garsone_Perro.Backend.Services;

import java.util.List;
import ru.Garsone_Perro.Backend.Dto.UserDto;

public interface CRUDServices {
    UserDto createUser(UserDto userDto);
    UserDto getUserById(Long userId);
    List <UserDto> getAllUsers();
    List <UserDto> getAllFriends(Long userId);
    UserDto updateUserById(UserDto userDto, Long userId);
    void deleteUser(Long userId);
    boolean searchNickname(String nickname);
    boolean searchLogin(String login);
    boolean searchEmail(String email);
    UserDto getUser(String login, String password);
    UserDto updateNickname(Long userId, String nickname);
    UserDto updateAvatar(String avatar, Long userId);
    UserDto updateEmail(Long userId, String email);
    String getAvatar(Long userId);
}
