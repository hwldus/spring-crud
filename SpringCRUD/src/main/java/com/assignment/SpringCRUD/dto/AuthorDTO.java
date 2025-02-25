package com.assignment.SpringCRUD.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthorDTO {
    private Long id;
    private String name;
    private String email;
    public AuthorDTO() {
    }
    public AuthorDTO(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    public AuthorDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
