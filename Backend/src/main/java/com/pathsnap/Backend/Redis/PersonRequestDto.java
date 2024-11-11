package com.pathsnap.Backend.Redis;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PersonRequestDto {
    private String name;
    private Integer age;
}
