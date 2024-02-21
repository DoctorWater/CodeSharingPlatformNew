package ru.malkov.codesharingplatformnew.dtos;

public class ApiDTO {
    private final String id;

    public ApiDTO(Long id) {
        this.id = id.toString();
    }

    public String getid() {
        return id;
    }
}