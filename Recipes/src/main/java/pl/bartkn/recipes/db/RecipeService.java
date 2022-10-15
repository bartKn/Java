package pl.bartkn.recipes.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bartkn.recipes.recipe.Recipe;

import javax.security.sasl.AuthenticationException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Recipe findRecipeById(Long id) {
        return recipeRepository.findRecipeById(id);
    }

    public Recipe save(Recipe recipeToSave) {
        return recipeRepository.save(recipeToSave);
    }

    public void deleteById(Long id, String email) throws AuthenticationException {
        if (!recipeRepository.existsById(id)) {
            throw new IllegalArgumentException();
        } else if (!recipeRepository.findRecipeById(id)
                .getUser()
                .getEmail().equals(email)) {
            throw new AuthenticationException();
        } else {
            recipeRepository.deleteById(id);
        }
    }

    public void updateById(Long id, Recipe recipeToSave, String email) throws AuthenticationException {
        if (!recipeRepository.existsById(id)) {
            throw new IllegalArgumentException();
        } else if (!recipeRepository.findRecipeById(id)
                .getUser()
                .getEmail().equals(email)) {
            throw new AuthenticationException();
        } else {
            Recipe recipe = findRecipeById(id);
            recipe.setName(recipeToSave.getName());
            recipe.setCategory(recipeToSave.getCategory());
            recipe.setDate(LocalDateTime.now());
            recipe.setDescription(recipeToSave.getDescription());
            recipe.setIngredients(recipeToSave.getIngredients());
            recipe.setDirections(recipeToSave.getDirections());
            save(recipe);
        }
    }

    public List<Recipe> findRecipesByCategory(String category) {
        return recipeRepository.findByCategoryIgnoreCaseOrderByDateDesc(category);
    }

    public List<Recipe> findRecipesByName(String name) {
        return recipeRepository.findByNameContainingIgnoreCaseOrderByDateDesc(name);
    }
}
