
package ru.Garsone_Perro.Backend.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FriendsDto {
    private Long id;
    private Long friendId;
    private Long userId;
}
