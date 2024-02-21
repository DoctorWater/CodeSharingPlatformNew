package ru.malkov.codesharingplatformnew.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.malkov.codesharingplatformnew.dtos.ApiDTO;
import ru.malkov.codesharingplatformnew.dtos.ApiLatestDTO;
import ru.malkov.codesharingplatformnew.dtos.CodeResponseDTO;
import ru.malkov.codesharingplatformnew.dtos.RequestCodeDTO;
import ru.malkov.codesharingplatformnew.entities.CodeSnippet;
import ru.malkov.codesharingplatformnew.exceptions.CodeSnippetNotFoundException;
import ru.malkov.codesharingplatformnew.services.CodeSnippetService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class RESTController {
    final CodeSnippetService service;
    Long nextSnippetId;

    public RESTController(CodeSnippetService service, Long nextSnippetId) {
        this.service = service;
        this.nextSnippetId = nextSnippetId;
    }

    @GetMapping(value = "/code/{N}")
    public String getCodeSnippet(ModelMap model, @PathVariable Long N) throws CodeSnippetNotFoundException {
        ArrayList<CodeSnippet> list = new ArrayList<>();
        list.add(service.getCodeSnippetById(N));
        if (model.containsAttribute("codeSnippets")) {
            model.replace("codeSnippets", list);
        } else {
            model.addAttribute("codeSnippets", list);
        }
        return "CodeSnippet";
    }

    @GetMapping(value = "/api/code/{N}")
    public @ResponseBody CodeResponseDTO getCodeSnippetAPI(@PathVariable Long N) throws CodeSnippetNotFoundException {
        CodeSnippet snippet = service.getCodeSnippetById(N);
        return new CodeResponseDTO(snippet.getCode(), snippet.getDate());
    }

    @PostMapping(value = "/api/code/new")
    @ResponseBody
    public ApiDTO addNewCode(@RequestBody RequestCodeDTO code) {
        CodeSnippet codeSnippet = new CodeSnippet(nextSnippetId++, code.getCode());
        synchronized (this) {
            service.saveCodeSnippet(codeSnippet);
        }
        return new ApiDTO(nextSnippetId-1);
    }

    @GetMapping(value = "/code/new")
    public String returnNewCode() {
        return "CodeRequest";
    }

    @GetMapping(value = "/api/code/latest")
    @ResponseBody
    public List<CodeResponseDTO> getLatestSnippetsApi() {
        return new ApiLatestDTO(service.getLatest()).getSnippets();
    }

    @GetMapping(value = "/code/latest")
    public String getLatestSnippets(ModelMap model) {
        List<CodeSnippet> list = service.getLatest();
        if (model.containsAttribute("codeSnippets")) {
            model.replace("codeSnippets", list);
        } else {
            model.addAttribute("codeSnippets", list);
        }
        return "CodeLatest";
    }

    @GetMapping(value = "/error")
    @ResponseBody
    public String returnNothing() {
        return "";
    }
}