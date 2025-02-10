package com.rodiugurlu.cvcreator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoReference {
    private String fullName;
    private String position;
    private String company;
    private String phone;
}
