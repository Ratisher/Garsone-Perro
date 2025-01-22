package ru.Garsone_Perro.Backend.Mapper;

import ru.Garsone_Perro.Backend.Dto.ConversationsDto;
import ru.Garsone_Perro.Backend.Entities.Conversations;

public class ConversationsMapper {
    public static ConversationsDto mapToConverstionsDto(Conversations converstion) {
        return new ConversationsDto(
            converstion.getId(),
            converstion.getSender_one(),
            converstion.getSender_two()
        );
    }
    
    public static Conversations mapToConverstions(ConversationsDto converstionDto) {
        return new Conversations(
            converstionDto.getId(),
            converstionDto.getSender_one(),
            converstionDto.getSender_two()
        );
    }
}
