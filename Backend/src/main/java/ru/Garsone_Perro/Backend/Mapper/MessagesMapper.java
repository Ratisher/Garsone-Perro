package ru.Garsone_Perro.Backend.Mapper;

import ru.Garsone_Perro.Backend.Dto.MessagesDto;
import ru.Garsone_Perro.Backend.Entities.Messages;

public class MessagesMapper {
    public static MessagesDto mapToMessagesDto(Messages message) {
        return new MessagesDto(
            message.getId(),
            message.getSenderId(),
            message.getContent(),
            message.getConverstionId()
        );
    }
    
    public static Messages mapToMessages(MessagesDto messageDto) {
        return new Messages(
            messageDto.getId(),
            messageDto.getSenderId(),
            messageDto.getContent(),
            messageDto.getConverstionId()
        );
    }
}
