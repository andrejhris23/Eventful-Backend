package app.eventful.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")

@Data
@NoArgsConstructor

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "email is required")
    @Email(message = "must a valid email format")
    @Column(name="email", unique = true)
    private String email;

    @NotNull(message = "full name is required")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "full name must be a string")
    @Column(name="displayName")
    private String displayName;

    @NotNull(message = "first name is required")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "first name must be a string")
    @Column(name="firstName")
    private String firstName;

    @NotNull(message = "last name is required")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "last name must be a string")
    @Column(name="lastName")
    private String lastName;

    @NotNull(message = "image is required")
    @URL(message = "image must be an URL")
    @Column(name="image")
    private String image;

    @Column(name="earnings")
    private float earnings;

    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "host", fetch = FetchType.EAGER)
    private List<Event> createdEvents;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Event> enrolledEvents;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Comment> comments;

    @JsonIgnore
    @OneToMany(mappedBy = "postAuthor", fetch = FetchType.EAGER)
    private List<Post> createdPosts;

    @JsonIgnore
    @ManyToMany(mappedBy = "likesFromUsers" , fetch = FetchType.EAGER)
    private List<Post> likedPosts;



    public User(Long id, String email, String displayName, String firstName, String lastName, String image) {
        this.id = id;
        this.email = email;
        this.displayName = displayName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = image;
        this.createdEvents = new ArrayList<>();
        this.enrolledEvents = new ArrayList<>();
        this.createdPosts = new ArrayList<>();
        this.likedPosts = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }
}

