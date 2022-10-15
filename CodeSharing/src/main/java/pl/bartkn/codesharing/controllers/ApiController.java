package pl.bartkn.codesharing.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.bartkn.codesharing.code.Code;
import pl.bartkn.codesharing.db.CodeService;
import pl.bartkn.codesharing.exceptions.NotFoundException;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class ApiController {

    private final CodeService codeService;

    @Autowired
    public ApiController(CodeService codeService) {
        this.codeService = codeService;
    }

    @GetMapping(value = "/api/code/{id}")
    public Code getJson(@PathVariable String id, HttpServletResponse response) {
        response.addHeader("Content-type", "application/json");
        try {
            Code codeToReturn = codeService.findCodeById(UUID.fromString(id));
            if (codeToReturn == null) {
                throw new NotFoundException("Code not found!");
            } else {
                return codeToReturn;
            }
        } catch (IllegalArgumentException ex) {
            throw new NotFoundException("Code not found!");
        }
    }

    @PostMapping(value = "/api/code/new")
    public Map<String, String> postNewCode(@RequestBody Code postedCode, HttpServletResponse response) {
        response.addHeader("Content-type", "application/json");
        System.out.println("Saving: " + postedCode);
        codeService.save(postedCode);
        return Collections.singletonMap("id", postedCode.getId().toString());
    }

    @GetMapping(value = "/api/code/latest")
    public List<Code> getLatestSnippetsList(HttpServletResponse response) {
        response.addHeader("Content-type", "application/json");
        return codeService.getCodesWithoutRestrictions();
    }

    @GetMapping(value = "/count")
    public Map<String, String> getCount(HttpServletResponse response) {
        response.addHeader("Content-type", "application/json");
        return Collections.singletonMap("id", codeService.count().toString());
    }
}
