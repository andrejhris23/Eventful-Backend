package app.eventful.repository;

import app.eventful.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository<User, Long> {

    @Query("SELECT DISTINCT u from User u where u.email=?1")
    User findByEmail(String email);
}
