package ru.Garsone_Perro.Backend.Repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.Garsone_Perro.Backend.Entities.Friends;

public interface FriendsReposiroty extends JpaRepository<Friends, Long> {
    @Query("SELECT u.id FROM User u, Friends f WHERE u.id = f.friendId AND f.userId = :userId")
    List<Long> findFriendIds(@Param("userId") Long userId);
    @Query("SELECT f FROM Friends f WHERE f.friendId = :friendId AND f.userId = :userId")
    Friends findFriend(@Param("friendId") Long friendId, @Param("userId") Long userId);
}
