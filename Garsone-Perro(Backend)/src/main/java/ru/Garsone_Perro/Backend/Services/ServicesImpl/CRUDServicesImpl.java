package ru.Garsone_Perro.Backend.Services.ServicesImpl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.Garsone_Perro.Backend.Dto.UserDto;
import ru.Garsone_Perro.Backend.Entities.User;
import ru.Garsone_Perro.Backend.Exception.ResurceNotFoundException;
import ru.Garsone_Perro.Backend.Mapper.UserMapper;
import ru.Garsone_Perro.Backend.Repositories.UserRepository;
import ru.Garsone_Perro.Backend.Services.CRUDServices;

@Service
@AllArgsConstructor
public class CRUDServicesImpl implements CRUDServices {
    
    private UserRepository userRepository;
    
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto);
        User savedUser = userRepository.save(user);
        
        return UserMapper.mapToUserDto(savedUser);
    }
    
    @Override
    public UserDto getUserById(Long userId) {       
        User user = userRepository.findById(userId)
                .orElseThrow(() -> 
                        new ResurceNotFoundException("User is not exists with given id :" + userId));
        
        return UserMapper.mapToUserDto(user);
    }
    
    @Override
    public List <UserDto> getAllUsers() {
        List <User> users = userRepository.findAll();
        
        return users.stream().map((user) -> UserMapper.mapToUserDto(user)).collect(Collectors.toList());
    }
    
    @Override
    public UserDto updateUserById(UserDto userDto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> 
                        new ResurceNotFoundException("User is not exists with given id :" + userId));
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        user.setNickname(userDto.getNickname());
        user.setEmail(userDto.getEmail());
        user.setLevel(userDto.getLevel());
        
        User updatedUser = userRepository.save(user);
        
        return UserMapper.mapToUserDto(updatedUser);
    }
    
    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> 
                        new ResurceNotFoundException("User is not exists with given id :" + userId));
        
        userRepository.delete(user);
    }
}
