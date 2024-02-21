package ru.malkov.codesharingplatformnew.dtos;

public class CodeResponseDTO {
    private final String code;
    private final String date;

    public CodeResponseDTO(String code, String dateAndTime) {
        this.code = code;
        this.date = dateAndTime;
    }

    public String getCode() {
        return code;
    }

    public String getDate() {
        return date;
    }
}