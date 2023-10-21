package br.com.lucasalves.picpaydesafio.repositories;

import br.com.lucasalves.picpaydesafio.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
