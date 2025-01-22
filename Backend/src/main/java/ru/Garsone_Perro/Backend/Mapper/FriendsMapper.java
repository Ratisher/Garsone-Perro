
package ru.Garsone_Perro.Backend.Mapper;

import ru.Garsone_Perro.Backend.Dto.FriendsDto;
import ru.Garsone_Perro.Backend.Entities.Friends;

public class FriendsMapper {
    public static FriendsDto mapToFriendsDto(Friends friend) {
        return new FriendsDto(
            friend.getId(),
            friend.getFriendId(),
            friend.getUserId()
        );
    }
    
    public static Friends mapToFriends(FriendsDto friendDto) {
        return new Friends(
            friendDto.getId(),
            friendDto.getFriendId(),
            friendDto.getUserId()
        );
    }
}
