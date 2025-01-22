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
import ru.Garsone_Perro.Backend.Dto.MessagesDto;
import ru.Garsone_Perro.Backend.Services.CRUDServices;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/messages")
public class MessagesRestController {
    
    public CRUDServices crudServices;
    
    @GetMapping("{converstionId}")
    public ResponseEntity<List<MessagesDto>> getMessages(@PathVariable("converstionId") Long converstionId) {
        List<MessagesDto> messages = crudServices.getMessages(converstionId);
        if (messages == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return ResponseEntity.ok(messages);
        }
    }
    
    @PostMapping("/addMessage/{content}/{senderId}/{conversationId}")
    public ResponseEntity<MessagesDto> addMessage(@PathVariable("content") String content, @PathVariable("senderId") Long senderId, @PathVariable("conversationId") Long conversationId) {
        MessagesDto AddedMessage = crudServices.addMessage(content, senderId, conversationId);
        if (AddedMessage == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return ResponseEntity.ok(AddedMessage);
        }
    }
}
