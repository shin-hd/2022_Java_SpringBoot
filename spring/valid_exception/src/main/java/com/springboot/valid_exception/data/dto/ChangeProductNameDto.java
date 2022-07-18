package com.springboot.valid_exception.data.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChangeProductNameDto {

    private Long number;
    private String name;

}
