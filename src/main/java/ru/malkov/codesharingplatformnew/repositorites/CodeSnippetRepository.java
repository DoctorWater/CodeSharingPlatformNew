package ru.malkov.codesharingplatformnew.repositorites;


import org.springframework.data.repository.CrudRepository;
import ru.malkov.codesharingplatformnew.entities.CodeSnippet;

import java.util.List;

public interface CodeSnippetRepository extends CrudRepository<CodeSnippet, Long> {
    List<CodeSnippet> getCodeSnippetsByIdGreaterThanOrderByIdDesc(Long id);
}