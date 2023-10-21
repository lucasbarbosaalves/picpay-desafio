package br.com.lucasalves.picpaydesafio.repositories;

import br.com.lucasalves.picpaydesafio.domain.transaction.Transaction;
import br.com.lucasalves.picpaydesafio.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
