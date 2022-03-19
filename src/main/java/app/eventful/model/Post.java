package app.eventful.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="posts")

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "post title is required")
    @Column(name="title")
    private String title;

    @NotNull(message = "post content is required")
    @Column(name="content")
    private String content;

    @Column(name="date")
    private LocalDate date;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> likesFromUsers;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private User postAuthor;

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
    private List<Comment> comments;

    public Post(String title, String content, User postAuthor) {
        this.title = title;
        this.content = content;
        this.postAuthor = postAuthor;
        this.date = LocalDate.now();
        this.likesFromUsers = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", likesFromUsers=" + likesFromUsers +
                ", postAuthor=" + postAuthor +
                ", comments=" + comments +
                '}';
    }
}
