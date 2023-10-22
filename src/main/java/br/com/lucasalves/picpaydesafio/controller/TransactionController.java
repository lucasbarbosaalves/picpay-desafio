package br.com.lucasalves.picpaydesafio.controller;

import br.com.lucasalves.picpaydesafio.domain.transaction.Transaction;
import br.com.lucasalves.picpaydesafio.domain.transaction.TransactionDTO;
import br.com.lucasalves.picpaydesafio.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private TransactionService service;

    public TransactionController() {
    }

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO dto) throws Exception {
        var newTransaction = this.service.createTransaction(dto);
        return new ResponseEntity<>(newTransaction, HttpStatus.CREATED);
    }
}
