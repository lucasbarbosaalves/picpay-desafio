package br.com.lucasalves.picpaydesafio.service;


import br.com.lucasalves.picpaydesafio.domain.transaction.Transaction;
import br.com.lucasalves.picpaydesafio.domain.transaction.TransactionDTO;
import br.com.lucasalves.picpaydesafio.repositories.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {

    private TransactionRepository repository;

    private UserService userService;

    private RestTemplate restTemplate;

    private NotificationService notificationService;

    public TransactionService() {
    }

    public TransactionService(TransactionRepository repository, UserService userService, RestTemplate restTemplate, NotificationService notificationService) {
        this.repository = repository;
        this.userService = userService;
        this.restTemplate = restTemplate;
        this.notificationService = notificationService;
    }


    public Transaction createTransaction(TransactionDTO dto) throws Exception {

        var payer = this.userService.findUserById(dto.payerId());
        var payee = this.userService.findUserById(dto.payeeId());

        userService.validateUser(payer, dto.amount());

        boolean isAuthorize = this.authorizeTransaction();

        if (!isAuthorize) {
            throw new RuntimeException("Transaction not authorized");
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(dto.amount());
        newTransaction.setPayer(payer);
        newTransaction.setPayee(payee);
        newTransaction.setTransactionTime(LocalDateTime.now());

        payer.setBalance(payer.getBalance().subtract(dto.amount()));
        payee.setBalance(payee.getBalance().add(dto.amount()));

        this.repository.save(newTransaction);
        this.userService.saveUser(payer);
        this.userService.saveUser(payee);

        notificationService.sendNotification(payer, "Transaction made successfully");
        notificationService.sendNotification(payee, "You received a transaction");

        return newTransaction;
    }

    private boolean authorizeTransaction() {
        var response = restTemplate.getForEntity("https://run.mocky.io/v3/9b89b419-a2f7-4885-aa86-5ddcea24d520", Map.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            String message = response.getBody().get("message").toString();
            return message.equalsIgnoreCase("Autorizado");
        } else return false;
    }
}
