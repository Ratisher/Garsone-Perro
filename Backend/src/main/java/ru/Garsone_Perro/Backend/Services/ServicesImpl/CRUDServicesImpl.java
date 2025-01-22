package ru.Garsone_Perro.Backend.Services.ServicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.Garsone_Perro.Backend.Dto.ConversationsDto;
import ru.Garsone_Perro.Backend.Dto.MessagesDto;
import ru.Garsone_Perro.Backend.Dto.UserDto;
import ru.Garsone_Perro.Backend.Entities.Conversations;
import ru.Garsone_Perro.Backend.Entities.Friends;
import ru.Garsone_Perro.Backend.Entities.Messages;
import ru.Garsone_Perro.Backend.Entities.User;
import ru.Garsone_Perro.Backend.Exception.ResurceNotFoundException;
import ru.Garsone_Perro.Backend.Mapper.ConversationsMapper;
import ru.Garsone_Perro.Backend.Mapper.MessagesMapper;
import ru.Garsone_Perro.Backend.Mapper.UserMapper;
import ru.Garsone_Perro.Backend.Repositories.FriendsReposiroty;
import ru.Garsone_Perro.Backend.Repositories.MessagesRepository;
import ru.Garsone_Perro.Backend.Repositories.UserRepository;
import ru.Garsone_Perro.Backend.Services.CRUDServices;
import ru.Garsone_Perro.Backend.Repositories.ConversationsRepository;

@Service
@AllArgsConstructor
public class CRUDServicesImpl implements CRUDServices {

    private UserRepository userRepository;
    private FriendsReposiroty friendsRepository;
    private ConversationsRepository conversationsRepository;
    private MessagesRepository messagesRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto);
        User savedUser = userRepository.save(user);

        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public List<UserDto> addFriend(Long friendId, Long userId) {
        Friends NewFriend = new Friends();

        NewFriend.setFriendId(friendId);

        NewFriend.setUserId(userId);

        friendsRepository.save(NewFriend);

        List<Long> friendIds = friendsRepository.findFriendIds(userId);

        if (!friendIds.isEmpty()) {
            List<User> users = userRepository.findAllById(friendIds);
            return users.stream().map((user) -> UserMapper.mapToUserDto(user)).collect(Collectors.toList());
        } else {
            return null;
        }

    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()
                        -> new ResurceNotFoundException("User is not exists with given id :" + userId));

        return UserMapper.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getUsersByIds(List userIds) {
        List<User> users = userRepository.findAllById(userIds);
        return users.stream().map((user) -> UserMapper.mapToUserDto(user)).collect(Collectors.toList());
    }

    @Override
    public MessagesDto addMessage(String content, Long senderId, Long conversationId) {
        Messages message = new Messages();
        message.setContent(content);
        message.setSenderId(senderId);
        Conversations conversation = new Conversations();
        conversation = conversationsRepository.findById(conversationId)
                .orElseThrow(()
                        -> new ResurceNotFoundException("User is not exists with given id :" + conversationId));
        message.setConverstionId(conversation);
        Messages AddedMessage = messagesRepository.save(message);
        return MessagesMapper.mapToMessagesDto(AddedMessage);
    }

    @Override
    public List<ConversationsDto> getConversationsById(Long userId) {
        List<Conversations> conversationsList = conversationsRepository.findConversationsById(userId);
        if (!conversationsList.isEmpty()) {
            return conversationsList.stream().map((converstion) -> ConversationsMapper.mapToConverstionsDto(converstion)).collect(Collectors.toList());
        } else {
            return null;
        }
    }

    @Override
    public ConversationsDto addConversation(Long userId, Long userTwoId) {
        Conversations newConversation = new Conversations();
        newConversation.setSender_one(userId);
        newConversation.setSender_two(userTwoId);
        Conversations addedConversation = conversationsRepository.save(newConversation);
        if (addedConversation == null) {
            return null;
        }
        else {
            return ConversationsMapper.mapToConverstionsDto(addedConversation);
        }
    }
    
    @Override
    public List<MessagesDto> getMessages(Long converstionId) {
        Conversations converstion = conversationsRepository.findById(converstionId)
                .orElseThrow(()
                        -> new ResurceNotFoundException("Converstion is not exists with given id :" + converstionId));
        List<Messages> messages = messagesRepository.findByConverstionId(converstion);
        if (!messages.isEmpty()) {
            return messages.stream().map((message) -> MessagesMapper.mapToMessagesDto(message)).collect(Collectors.toList());
        } else {
            return null;
        }
    }

    @Override
    public UserDto getUserByLogin(String login) {
        User user = userRepository.findUserByLogin(login);

        if (user == null) {
            return null;
        } else {
            return UserMapper.mapToUserDto(user);
        }
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream().map((user) -> UserMapper.mapToUserDto(user)).collect(Collectors.toList());
    }

    @Override
    public ConversationsDto getConversationByIds(Long userId, Long userTwoId) {
        Conversations conversation = conversationsRepository.findConversationByIds(userId, userTwoId);
        if (conversation != null) {
            return ConversationsMapper.mapToConverstionsDto(conversation);
        } else {
            return null;
        }
    }

    @Override
    public List<UserDto> getAllFriends(Long userId) {
        List<Long> friendIds = friendsRepository.findFriendIds(userId);

        if (!friendIds.isEmpty()) {
            List<User> users = userRepository.findAllById(friendIds);
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
        User user = userRepository.findUserByNickname(nickname);

        if (user == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean searchLogin(String login) {
        User user = userRepository.findUserByLogin(login);

        if (user == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean searchEmail(String email) {
        User user = userRepository.findUserByEmail(email);

        if (user == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public UserDto getUser(String login, String password) {
        User user = userRepository.findUser(login, password);

        if (user == null) {
            return null;
        } else {
            return UserMapper.mapToUserDto(user);
        }
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

    @Override
    public void deleteFriend(Long friendId, Long userId) {
        Friends findedFriend = friendsRepository.findFriend(friendId, userId);
        friendsRepository.delete(findedFriend);
    }
}
