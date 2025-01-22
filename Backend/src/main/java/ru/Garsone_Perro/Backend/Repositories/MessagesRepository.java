package ru.Garsone_Perro.Backend.Repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.Garsone_Perro.Backend.Entities.Conversations;
import ru.Garsone_Perro.Backend.Entities.Messages;

public interface MessagesRepository extends JpaRepository<Messages, Long>{
    @Query("SELECT m FROM Messages m WHERE m.converstionId = :converstionId")
    List<Messages> findByConverstionId(@Param("converstionId") Conversations converstionId);
}
