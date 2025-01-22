package ru.Garsone_Perro.Backend.Controllers;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.Garsone_Perro.Backend.Dto.ConversationsDto;
import ru.Garsone_Perro.Backend.Services.CRUDServices;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/conversations")
public class ConversationsRestController {
    
    public CRUDServices crudServices;
    
    @PostMapping("{userId}/{userTwoId}")
    public ResponseEntity<ConversationsDto> addConversation(@PathVariable("userId") Long userId, @PathVariable("userTwoId") Long userTwoId) {
         ConversationsDto addedConversation = crudServices.addConversation(userId, userTwoId);
         if (addedConversation == null) {
             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
         }
         else {
             return ResponseEntity.ok(addedConversation);
         }
    }
    
    @GetMapping("{userId}")
    public ResponseEntity<List<ConversationsDto>> getConversationsById(@PathVariable("userId") Long userId) {
        List<ConversationsDto> conversations = crudServices.getConversationsById(userId);
        if (conversations == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return ResponseEntity.ok(conversations);
        }
    } 
    
    @GetMapping("/get-conversation/{userId}/{userTwoId}")
    public ResponseEntity<ConversationsDto> getConversationByIds(@PathVariable("userId") Long userId, @PathVariable("userTwoId") Long userTwoId) {
        ConversationsDto conversation = crudServices.getConversationByIds(userId, userTwoId);
        if (conversation == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return ResponseEntity.ok(conversation);
        }
    }
}
