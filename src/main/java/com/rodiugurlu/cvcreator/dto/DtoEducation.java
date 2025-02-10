package com.rodiugurlu.cvcreator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoEducation {
    private String university;
    private String department;
    private String startDate;
    private String endDate;
}
