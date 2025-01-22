package ru.Garsone_Perro.Backend.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.Garsone_Perro.Backend.Entities.Conversations;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessagesDto {
    private Long id;
    private Long senderId;
    private String content;
    private Conversations converstionId;
}
