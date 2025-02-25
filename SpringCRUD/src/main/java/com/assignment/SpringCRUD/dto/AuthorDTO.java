package com.assignment.SpringCRUD.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorDTO {
    private Long id;
    private String name;
    private String email;
    public AuthorDTO(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
