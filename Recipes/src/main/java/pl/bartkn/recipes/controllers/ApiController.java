package pl.bartkn.recipes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.bartkn.recipes.db.RecipeService;
import pl.bartkn.recipes.db.UserService;
import pl.bartkn.recipes.exceptions.ParametersException;
import pl.bartkn.recipes.exceptions.ResourceNotFoundException;
import pl.bartkn.recipes.recipe.Recipe;
import pl.bartkn.recipes.user.User;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class ApiController {

    private final RecipeService recipeService;
    private final UserService userService;
    private final PasswordEncoder encoder;

    @Autowired
    public ApiController(RecipeService recipeService, UserService userService, PasswordEncoder encoder) {
        this.recipeService = recipeService;
        this.userService = userService;
        this.encoder = encoder;
    }

    @GetMapping(value = "/api/recipe/{id}")
    public Recipe getRecipeById(@PathVariable String id, HttpServletResponse response) {
        response.addHeader("Content-type", "application/json");

        try {
            Recipe recipeToReturn = recipeService.findRecipeById(Long.parseLong(id));

            if (recipeToReturn != null) {
                return recipeToReturn;
            } else {
                throw new ResourceNotFoundException();
            }
        } catch (IllegalArgumentException ex) {
            throw new ResourceNotFoundException();
        }
    }

    @PostMapping(value = "api/recipe/new")
    public Map<String, Long> postNewRecipe(@Valid @RequestBody Recipe newRecipe,
                                           @AuthenticationPrincipal UserDetails details) {
        User recipeOwner = userService.findUserByEmail(details.getUsername());

        newRecipe.setUser(recipeOwner);
        recipeOwner.addRecipe(newRecipe);

        recipeService.save(newRecipe);
        userService.saveUser(recipeOwner);
        return Collections.singletonMap("id", newRecipe.getId());
    }

    @DeleteMapping(value = "/api/recipe/{id}")
    public void deleteRecipe(@PathVariable String id, @AuthenticationPrincipal UserDetails details,
                             HttpServletResponse response) {
        try {
            recipeService.deleteById(Long.parseLong(id), details.getUsername());
            response.setStatus(204);
        } catch (IllegalArgumentException ex) {
            response.setStatus(404);
        } catch (AuthenticationException ex) {
            response.setStatus(403);
        }
    }

    @PutMapping(value = "/api/recipe/{id}")
    public void updateRecipe(@PathVariable String id, @Valid @RequestBody Recipe newRecipe,
                             @AuthenticationPrincipal UserDetails details,
                             HttpServletResponse response) {
        try {
            recipeService.updateById(Long.parseLong(id), newRecipe, details.getUsername());
            response.setStatus(204);
        } catch (IllegalArgumentException ex) {
            response.setStatus(404);
        } catch (AuthenticationException ex) {
            response.setStatus(403);
        }
    }

    @GetMapping(value = "/api/recipe/search")
    public List<Recipe> findRecipe(@RequestParam Map<String,String> allParams) {
        if (allParams.size() != 1) {
            throw new ParametersException();
        }

        String type = allParams
                .keySet()
                .stream()
                .findFirst()
                .get();
        String value = allParams.getOrDefault(type, "null");

        switch (type) {
            case "category":
                return recipeService.findRecipesByCategory(value);
            case "name":
                return recipeService.findRecipesByName(value);
            default:
                throw new ParametersException();
        }
    }

    @PostMapping(value = "/api/register")
    public void register(@Valid @RequestBody User user, HttpServletResponse response) {
        if (userService.isEmailTaken(user.getEmail())) {
            response.setStatus(400);
        } else {
            user.setPassword(encoder.encode(user.getPassword()));
            user.setRole("ROLE_USER");
            userService.saveUser(user);
        }
    }
}

