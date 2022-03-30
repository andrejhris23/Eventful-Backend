package app.eventful.service.impl;

import app.eventful.model.User;
import app.eventful.model.exceptions.InvalidUserIdException;
import app.eventful.repository.CommentRepo;
import app.eventful.repository.EventRepo;
import app.eventful.repository.PostRepo;
import app.eventful.repository.UserRepo;
import app.eventful.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public User createUser(User newUser) {
        // * TODO: proveri dali postoi userot spored email vo baza, pa potoa kreariraj go

        return userRepo.save(newUser);
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
