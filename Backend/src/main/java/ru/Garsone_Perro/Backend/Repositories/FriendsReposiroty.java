package ru.Garsone_Perro.Backend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.Garsone_Perro.Backend.Entities.Friends;

public interface FriendsReposiroty extends JpaRepository<Friends, Long> {
    
}
