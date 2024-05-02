package org.example.services;

import org.example.entities.Orders;
import org.example.entities.Users;
import org.example.repositories.OrderRepository;
import org.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final OrderRepository orderRepository;

    @Autowired
    public UserService(UserRepository userRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    public Users getById(UUID id) {
        Optional<Users> optionalUser = userRepository.findById(id);
        return optionalUser.orElseGet(Users::new);
    }

    public List<Users> getAll() {
        return (List<Users>) userRepository.findAll();
    }

    @Transactional
    public Users save(Users users) {
        if(isValidEmail(users.getEmail())) {
            return userRepository.save(users);
        } else {
            return new Users();
        }

    }

    @Transactional
    public Users delete(UUID id) {
        Users users = getById(id);
        if(users.getName() != null)
            userRepository.deleteById(id);
        return users;
    }

    private boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-z-AZ]{2,7}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
    ///////////////////////////////////////////////////////////////////////////////////////////
    public List<Orders> getOrds(UUID userId) {
        return orderRepository.getByUserId(userId);

    }

}
