package ru.Garsone_Perro.Backend.Repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.Garsone_Perro.Backend.Entities.Conversations;

public interface ConversationsRepository extends JpaRepository<Conversations, Long>{
    @Query("SELECT c FROM Conversations c WHERE c.sender_one = :userId AND c.sender_two = :userTwoId")
    Conversations findConversationByIds(@Param("userId") Long userId, @Param("userTwoId") Long userTwoId);
    @Query("SELECT c FROM Conversations c WHERE c.sender_one = :userId OR c.sender_two = :userId")
    List <Conversations> findConversationsById(@Param("userId") Long userId);
}
