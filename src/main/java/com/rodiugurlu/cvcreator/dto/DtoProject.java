package com.rodiugurlu.cvcreator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoProject {
    private String name;
    private String description;
    private String link;
}
