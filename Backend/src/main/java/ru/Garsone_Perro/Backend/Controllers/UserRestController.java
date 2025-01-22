package ru.Garsone_Perro.Backend.Controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.Garsone_Perro.Backend.Dto.UserDto;
import ru.Garsone_Perro.Backend.Services.CRUDServices;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserRestController {

    private CRUDServices crudServices;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto createdUser = crudServices.createUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId) {
        UserDto user = crudServices.getUserById(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/data")
    public ResponseEntity<List<UserDto>> getUsersByIds(@RequestBody List<Long> userIds) {
        List<UserDto> users = crudServices.getUsersByIds(userIds);
        if (!users.isEmpty()) {
            return ResponseEntity.ok(users);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = crudServices.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/getUserByLogin/{login}")
    public ResponseEntity<UserDto> getUserByLogin(@PathVariable("login") String login) {
        UserDto user = crudServices.getUserByLogin(login);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return ResponseEntity.ok(user);
        }
    }
     
    @GetMapping("/check-nickname/{nickname}")
    public boolean searchNickname(@PathVariable("nickname") String nickname) {
        boolean answer = crudServices.searchNickname(nickname);
        if (answer == true) {
            return true;
        } else {
            return false;
        }
    }

    @GetMapping("/check-login/{login}")
    public boolean searchLogin(@PathVariable("login") String login) {
        boolean answer = crudServices.searchLogin(login);
        if (answer == true) {
            return true;
        } else {
            return false;
        }
    }

    @GetMapping("/check-email/{email}")
    public boolean searchEmail(@PathVariable("email") String email) {
        boolean answer = crudServices.searchEmail(email);
        if (answer == true) {
            return true;
        } else {
            return false;
        }
    }

    @GetMapping("/user-authorization/{login}/{password}")
    public ResponseEntity<UserDto> getUser(@PathVariable("login") String login, @PathVariable("password") String password) {
        UserDto user = crudServices.getUser(login, password);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(user);
        }
    }

    @GetMapping("/user-avatar/{id}")
    public ResponseEntity<byte[]> getAvatar(@PathVariable("id") Long userId) throws IOException {

        String avatarPath = crudServices.getAvatar(userId);

        if (avatarPath == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            byte[] imageBytes = Files.readAllBytes(Paths.get(avatarPath));
            return ResponseEntity.ok()
                    .header("Content-Type", Files.probeContentType(Paths.get(avatarPath)))
                    .body(imageBytes);
        }

    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable("id") Long userId) {
        UserDto user = crudServices.updateUserById(userDto, userId);
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/update-nickname/{id}/{nickname}")
    public ResponseEntity<UserDto> updateNickname(@PathVariable("id") Long userId, @PathVariable("nickname") String nickname) {
        UserDto user = crudServices.updateNickname(userId, nickname);
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/update-avatar/{id}/avatar")
    public ResponseEntity<UserDto> updateAvatar(@RequestParam("avatar") MultipartFile avatar, @PathVariable("id") Long userId) throws IOException {
        if (avatar.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        String fileName = avatar.getOriginalFilename();
        Path filePath = Paths.get("D:/server/images", fileName);

        File file = new File("D:/server/images", fileName);
        
        if (!file.exists()) {
            Files.copy(avatar.getInputStream(), filePath);
        }

        UserDto user = crudServices.updateAvatar(filePath.toString(), userId);

        return ResponseEntity.ok(user);
    }
    
    @PatchMapping("/update-email/{id}/{email}")
    public ResponseEntity<UserDto> updateEmail(@PathVariable("id") Long userId, @PathVariable("email") String email) {
        UserDto user = crudServices.updateEmail(userId, email);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
        crudServices.deleteUser(userId);
        return ResponseEntity.ok("User deleted!");
    }
}
