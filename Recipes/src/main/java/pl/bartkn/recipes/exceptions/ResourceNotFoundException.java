package pl.bartkn.recipes.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ResourceNotFoundException extends RuntimeException {

    private String error;
}
