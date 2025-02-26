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
}
