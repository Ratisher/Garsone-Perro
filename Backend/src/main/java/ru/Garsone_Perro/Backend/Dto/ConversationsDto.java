package ru.Garsone_Perro.Backend.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConversationsDto {
    private Long id;
    private Long sender_one;
    private Long sender_two;
}
