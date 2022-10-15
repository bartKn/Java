package pl.bartkn.recipes.recipe;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import pl.bartkn.recipes.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Recipe {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "email")
    @JsonIgnore
    private User user;

    @Column
    @NotBlank
    private String name;

    @Column
    @NotBlank
    private String category;

    @Column
    @CreationTimestamp
    private LocalDateTime date;

    @Column
    @NotBlank
    private String description;

    @Column
    @ElementCollection
    @Size(min = 1)
    private List<String> ingredients = new ArrayList<>();

    @Column
    @ElementCollection
    @Size(min = 1)
    private List<String> directions = new ArrayList<>();
}
