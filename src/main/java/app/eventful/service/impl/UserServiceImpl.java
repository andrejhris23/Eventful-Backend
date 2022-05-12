package app.eventful.service.impl;

import app.eventful.model.User;
import app.eventful.model.exceptions.InvalidUserIdException;
import app.eventful.repository.UserRepo;
import app.eventful.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepo.findById(id).orElseThrow(()-> new InvalidUserIdException(id));
    }

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public User createUser(User newUser) {
        User oldUser = findByEmail(newUser.getEmail());
        return Objects.requireNonNullElseGet(oldUser, () -> userRepo.save(newUser));
    }

    @Override
    public User editUser(User editedUser){
        User oldUser = userRepo.findById(editedUser.getId())
                .orElseThrow(()-> new InvalidUserIdException(editedUser.getId()));

        // not sure if everything should be here
        oldUser.setDisplayName(editedUser.getDisplayName());
        oldUser.setFirstName(editedUser.getFirstName());
        oldUser.setLastName(editedUser.getLastName());
        oldUser.setImage(editedUser.getImage());
        oldUser.setEarnings(editedUser.getEarnings());

        return userRepo.save(oldUser);
    }

    @Override
    public void deleteById(Long id) {
        userRepo.deleteById(id);
    }
}
