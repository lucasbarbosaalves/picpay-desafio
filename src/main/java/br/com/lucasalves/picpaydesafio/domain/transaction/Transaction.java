package br.com.lucasalves.picpaydesafio.domain.transaction;

import br.com.lucasalves.picpaydesafio.domain.user.User;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "transactions")
@Table(name = "transactions")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Transaction {

    public Transaction() {
    }

    public Transaction(Long id, BigDecimal amount, User payer, User payee, LocalDateTime transactionTime) {
        this.id = id;
        this.amount = amount;
        this.payer = payer;
        this.payee = payee;
        this.transactionTime = transactionTime;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "payer_id")
    private User payer;

    @ManyToOne
    @JoinColumn(name = "payee_id")
    private User payee;

    private LocalDateTime transactionTime;

}
