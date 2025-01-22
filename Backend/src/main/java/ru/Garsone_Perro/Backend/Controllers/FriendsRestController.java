package ru.Garsone_Perro.Backend.Controllers;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import ru.Garsone_Perro.Backend.Dto.UserDto;
import ru.Garsone_Perro.Backend.Services.CRUDServices;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/friends")
public class FriendsRestController {
    
    public CRUDServices crudServices;
    
    @DeleteMapping("{friendId}/{userId}")
    public ResponseEntity<String> deleteFriend(@PathVariable("friendId") Long friendId, @PathVariable("userId") Long userId) {
        crudServices.deleteFriend(friendId, userId);
        return ResponseEntity.ok("Friend was deleted!");
    }
    
    @GetMapping("{id}")
    public ResponseEntity<List<UserDto>> getAllFriends(@PathVariable("id") Long id) {
        List<UserDto> users = crudServices.getAllFriends(id);
        if (users != null) {
            return ResponseEntity.ok(users);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    
    @PostMapping("{friendId}/{userId}")
    public ResponseEntity<List<UserDto>> addFriend(@PathVariable("friendId") Long friendId, @PathVariable("userId") Long userId) {
        List<UserDto> users = crudServices.addFriend(friendId, userId);
        if (users == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return ResponseEntity.ok(users);
        }
    }
}
