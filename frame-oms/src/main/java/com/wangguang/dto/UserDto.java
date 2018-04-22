package com.wangguang.dto;

public class UserDto {

    private String name;

    private int age;

    private FatherDto fatherDto;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public FatherDto getFatherDto() {
        return fatherDto;
    }

    public void setFatherDto(FatherDto fatherDto) {
        this.fatherDto = fatherDto;
    }
}
