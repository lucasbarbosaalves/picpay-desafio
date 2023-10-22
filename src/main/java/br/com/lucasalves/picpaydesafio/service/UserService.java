package br.com.lucasalves.picpaydesafio.service;

import br.com.lucasalves.picpaydesafio.domain.user.User;
import br.com.lucasalves.picpaydesafio.domain.user.UserType;
import br.com.lucasalves.picpaydesafio.domain.user.UserDTO;
import br.com.lucasalves.picpaydesafio.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    private UserRepository repository;

    public UserService() {
    }

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void saveUser(User user) {
        this.repository.save(user);
    }

    public User createUser(UserDTO user) {
        User newUser = new User(user);
        this.saveUser(newUser);
        return newUser;
    }

    public List<User> getAllUsers() {
        return this.repository.findAll();
    }

    public User findUserById(Long id) throws Exception {
       return this.repository.findById(id).orElseThrow(() -> new Exception("User not found"));
    }

    public boolean validateUser(User payer, BigDecimal amount) {
        if (payer.getUserType() == UserType.MERCHANT) {
            throw new RuntimeException("Merchant can't make transactions");
        }

        if (payer.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds");
        }

        return true;
    }
}
