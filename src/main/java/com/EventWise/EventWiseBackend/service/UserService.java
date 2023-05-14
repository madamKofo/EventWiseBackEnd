package com.EventWise.EventWiseBackend.service;

import com.EventWise.EventWiseBackend.entities.*;
import com.EventWise.EventWiseBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public List<User> getUsersByEvent(Event event) {
        List<User> users = new ArrayList<>();
        List<Participation> participations = event.getParticipations();
        for (Participation participation : participations) {
            users.add(participation.getUser());
        }
        return users;
    }

    public void addUserComment(User user, Comment comment) {
        UserComment userComment = new UserComment();
        userComment.getUsers().add(user);
        userComment.getComment().add(comment);
        user.getUserComments().add(userComment);
        comment.getUserComments().add(userComment);
    }

    public void removeUserComment(User user, Comment comment) {
        List<UserComment> userComments = user.getUserComments();
        for (UserComment userComment : userComments) {
            if (userComment.getComment().contains(comment)) {
                userComment.getComment().remove(comment);
                comment.getUserComments().remove(userComment);
            }
        }
    }

}

