
package ru.Garsone_Perro.Backend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.Garsone_Perro.Backend.Entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.login LIKE :login")
    User findUserByLogin(@Param("login") String login);
    @Query("SELECT u FROM User u WHERE u.nickname LIKE :nickname")
    User findUserByNickname(@Param("nickname") String nickname);
    @Query("SELECT u FROM User u WHERE u.email LIKE :email")
    User findUserByEmail(@Param("email") String email);
    @Query("SELECT u FROM User u WHERE u.login LIKE :login AND u.password LIKE :password")
    User findUser(@Param("login") String login, @Param("password") String password);
}
