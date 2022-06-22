package com.example.coffeeshopexam.service.Impl;

import com.example.coffeeshopexam.model.entity.User;
import com.example.coffeeshopexam.model.service.UserServiceModel;
import com.example.coffeeshopexam.model.view.UserViewModel;
import com.example.coffeeshopexam.repository.UserRepository;
import com.example.coffeeshopexam.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final HttpSession httpSession;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, HttpSession httpSession) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.httpSession = httpSession;
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        User user = modelMapper
                .map(userServiceModel, User.class);

        user.setPassword(passwordEncoder
                .encode(userServiceModel.getPassword()));

        userRepository.save(user);
    }

    @Override
    public boolean loginUser(UserServiceModel userServiceModel) {
        Optional<User> optionalUser = userRepository
                .findByUsername(userServiceModel.getUsername());

        if (optionalUser.isEmpty()) {
            logout();
            return false;
        }

        boolean success = passwordEncoder
                .matches(userServiceModel.getPassword(), optionalUser.get().getPassword());

        if (success) {
            User userLogged = optionalUser.get();

        }

        return success;
    }

    @Override
    public void logout() {
        httpSession.invalidate();
    }

    @Override
    public UserServiceModel findByUsername(String username) {
        return modelMapper
                .map(userRepository.findByUsername(username)
                        .orElse(null), UserServiceModel.class);
    }

    @Override
    public List<UserViewModel> findAllUserAndCountOfOrders() {
        return userRepository.findAllByOrdersCountDesc()
                .stream()
                .map(user -> {
                    UserViewModel userViewModel = new UserViewModel();
                    userViewModel.setUsername(user.getUsername());
                    userViewModel.setCountOfOrders(user.getOrders().size());
                    return userViewModel;
                })
                .collect(Collectors.toList());
    }
}
