package ru.malkov.codesharingplatformnew.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class CodeSnippet {
    @Column
    private String code;
    @Column
    private String date;
    @Id
    private Long id;

    public CodeSnippet(Long id, String code) {
        this.id = id;
        this.code = code;
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.date = dateTime.format(myFormatObj);
    }

    public CodeSnippet() {
    }

    public String getCode() {
        return code;
    }

    public String getDate() {
        return date;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CodeSnippet)) return false;

        CodeSnippet snippet = (CodeSnippet) o;

        if (getCode() != null ? !getCode().equals(snippet.getCode()) : snippet.getCode() != null) return false;
        if (getDate() != null ? !getDate().equals(snippet.getDate()) : snippet.getDate() != null) return false;
        return getId() != null ? getId().equals(snippet.getId()) : snippet.getId() == null;
    }

    @Override
    public int hashCode() {
        int result = getCode() != null ? getCode().hashCode() : 0;
        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
        result = 31 * result + (getId() != null ? getId().hashCode() : 0);
        return result;
    }
}