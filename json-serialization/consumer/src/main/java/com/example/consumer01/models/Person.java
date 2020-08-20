package com.example.consumer01.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Person {
    private String name;
    private Integer age;

    @Override
    public String toString() {
        return name + " " + age.toString();
    }
}
