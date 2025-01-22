package ru.Garsone_Perro.Backend.Services;

import java.util.List;
import ru.Garsone_Perro.Backend.Dto.ConversationsDto;
import ru.Garsone_Perro.Backend.Dto.MessagesDto;
import ru.Garsone_Perro.Backend.Dto.UserDto;

public interface CRUDServices {
    UserDto createUser(UserDto userDto);
    UserDto getUserById(Long userId);
    UserDto getUserByLogin(String login);
    List <UserDto> getAllUsers();
    List <UserDto> getUsersByIds(List userIds);
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
    void deleteFriend(Long friendId, Long userId);
    List<UserDto> addFriend(Long friendId, Long userId);
    List<ConversationsDto> getConversationsById(Long userId);
    ConversationsDto getConversationByIds(Long userId, Long userTwoId);
    ConversationsDto addConversation(Long userId, Long userTwoId);
    List<MessagesDto> getMessages(Long converstionId);
    MessagesDto addMessage(String content, Long senderId, Long conversationId);
}
