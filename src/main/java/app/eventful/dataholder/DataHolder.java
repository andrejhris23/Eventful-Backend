package app.eventful.dataholder;


import app.eventful.model.Comment;
import app.eventful.model.Event;
import app.eventful.model.Post;
import app.eventful.model.User;
import app.eventful.repository.CommentRepo;
import app.eventful.repository.EventRepo;
import app.eventful.repository.PostRepo;
import app.eventful.repository.UserRepo;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Component
public class DataHolder {

    private final CommentRepo commentRepo;
    private final EventRepo eventRepo;
    private final UserRepo userRepo;
    private final PostRepo postRepo;

    List<User> initialUsers;
    List<Event> initialEvents;
    List<Post> initialPosts;
    List<Comment> initialComments;

    public DataHolder(CommentRepo commentRepo, EventRepo eventRepo, UserRepo userRepo, PostRepo postRepo) {
        this.commentRepo = commentRepo;
        this.eventRepo = eventRepo;
        this.userRepo = userRepo;
        this.postRepo = postRepo;

        initialUsers = new ArrayList<>();
        initialEvents = new ArrayList<>();
        initialPosts = new ArrayList<>();
        initialComments = new ArrayList<>();

        generateData();
        fillDBAtStart();
    }

    public void generateData() {
        User testuser1 = new User( "testuser@gmail.com", "Test User", "TestUserName", "TestUserovichLastName", "https://gravatar.com/avatar/1f82b0492a0a938288c2d5b70534a1fb?s=400&d=robohash&r=x" );
        User testuser2= new User("ivansada@gmail.com", "Ivan Sada", "Ivan", "Sada", "https://nretnil.com/avatar/LawrenceEzekielAmos.png" );

        initialUsers.add(testuser1);
        initialUsers.add(testuser2);
    }

    public void fillDBAtStart() {
        if(userRepo.count() == 0) {
            userRepo.saveAll(initialUsers);
        } else {
            System.out.println("Bazata e vekje puna");
        }
    }
}
