package ru.Garsone_Perro.Backend.Services.ServicesImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.Garsone_Perro.Backend.Dto.UserDto;
import ru.Garsone_Perro.Backend.Entities.Friends;
import ru.Garsone_Perro.Backend.Entities.User;
import ru.Garsone_Perro.Backend.Exception.ResurceNotFoundException;
import ru.Garsone_Perro.Backend.Mapper.UserMapper;
import ru.Garsone_Perro.Backend.Repositories.FriendsReposiroty;
import ru.Garsone_Perro.Backend.Repositories.UserRepository;
import ru.Garsone_Perro.Backend.Services.CRUDServices;

@Service
@AllArgsConstructor
public class CRUDServicesImpl implements CRUDServices {

    private UserRepository userRepository;
    private FriendsReposiroty friendsRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto);
        User savedUser = userRepository.save(user);

        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()
                        -> new ResurceNotFoundException("User is not exists with given id :" + userId));

        return UserMapper.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream().map((user) -> UserMapper.mapToUserDto(user)).collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getAllFriends(Long userId) {
        List<Friends> friends = friendsRepository.findAll();
        List<Long> userIds = new ArrayList<>();
        int i = 0;
        if (friends != null) {
            while (i < friends.size()) {
                if (friends.get(i).getUserId().toString().equals(userId.toString())) {
                    userIds.add(friends.get(i).getFriendId());
                }
                i++;
            }
        }
        if (userIds != null) {
            List<User> users = userRepository.findAllById(userIds);
            return users.stream().map((user) -> UserMapper.mapToUserDto(user)).collect(Collectors.toList());
        } else {
            return null;
        }
    }

    @Override
    public String getAvatar(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()
                        -> new ResurceNotFoundException("User is not exists with given id :" + userId));

        return user.getAvatar();
    }

    @Override
    public boolean searchNickname(String nickname) {
        List<User> users = userRepository.findAll();
        boolean have = true;
        int i = 0;
        while (i < users.size()) {
            if (users.get(i).getNickname().equals(nickname)) {
                have = false;
                break;
            }
            i++;
        }
        if (have == true) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean searchLogin(String login) {
        List<User> users = userRepository.findAll();
        boolean have = true;
        int i = 0;
        while (i < users.size()) {
            if (users.get(i).getLogin().equals(login)) {
                have = false;
                break;
            }
            i++;
        }
        if (have == true) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean searchEmail(String email) {
        List<User> users = userRepository.findAll();
        boolean have = true;
        int i = 0;
        while (i < users.size()) {
            if (users.get(i).getEmail().equals(email)) {
                have = false;
                break;
            }
            i++;
        }
        if (have == true) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public UserDto getUser(String login, String password) {
        List<User> users = userRepository.findAll();
        int i = 0;
        while (i < users.size()) {
            if (users.get(i).getLogin().equals(login) && users.get(i).getPassword().equals(password)) {
                return UserMapper.mapToUserDto(users.get(i));
            }
            if (users.get(i).getLogin().equals(login) && users.get(i).getPassword().equals(password)) {
                break;
            }
            i++;
        }
        return null;
    }

    @Override
    public UserDto updateUserById(UserDto userDto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()
                        -> new ResurceNotFoundException("User is not exists with given id :" + userId));

        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        user.setNickname(userDto.getNickname());
        user.setEmail(userDto.getEmail());
        user.setExperience(userDto.getExperience());

        User updatedUser = userRepository.save(user);

        return UserMapper.mapToUserDto(updatedUser);
    }

    @Override
    public UserDto updateNickname(Long userId, String nickname) {
        User user = userRepository.findById(userId)
                .orElseThrow(()
                        -> new ResurceNotFoundException("User is not exists with given id :" + userId));
        user.setNickname(nickname);

        User updatedUser = userRepository.save(user);

        return UserMapper.mapToUserDto(updatedUser);
    }

    @Override
    public UserDto updateAvatar(String avatar, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()
                        -> new ResurceNotFoundException("User is not exists with given id :" + userId));

        user.setAvatar(avatar);

        User updatedUser = userRepository.save(user);

        return UserMapper.mapToUserDto(updatedUser);
    }

    @Override
    public UserDto updateEmail(Long userId, String email) {
        User user = userRepository.findById(userId)
                .orElseThrow(()
                        -> new ResurceNotFoundException("User is not exists with given id :" + userId));

        user.setEmail(email);

        User updatedUser = userRepository.save(user);

        return UserMapper.mapToUserDto(updatedUser);

    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()
                        -> new ResurceNotFoundException("User is not exists with given id :" + userId));

        userRepository.delete(user);
    }
}
