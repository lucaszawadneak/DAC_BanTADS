package com.bantads.account.services;

import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bantads.account.model.Account;
import com.bantads.account.model.Transaction;
import com.bantads.account.model.TransactionDTO;
import com.bantads.account.repository.TransactionRepository;
import com.google.gson.Gson;

@Service
public class TransactionServices {
    @Autowired
    private TransactionRepository repo;

    public List<TransactionDTO> getAccountTransactions(Long accountId) {
        List<Transaction> transactions = repo.findByAccountId(accountId);

        List<TransactionDTO> dtos = transactions.stream()
                .map(e -> e.toDto())
                .collect(Collectors.toList());

        return dtos;
    }

    private Transaction createTransaction(Account account, String type, Double amount, Timestamp timestamp,
            String extra_data) {
        Transaction newTransaction = new Transaction();
        newTransaction.setAccount(account);
        // newTransaction.setAccountId(account.getId());
        newTransaction.setType(type);
        newTransaction.setAmount(amount);
        newTransaction.setTimestamp(timestamp);
        newTransaction.setExtraData(extra_data);
        return repo.save(newTransaction);
    }

    private Transaction createTransaction(Account account, String type, Double amount, Timestamp timestamp) {
        return createTransaction(account, type, amount, timestamp, "");
    }

    public Transaction deposit(Account account, Double amount) {
        return createTransaction(account, "deposit", amount, new Timestamp(System.currentTimeMillis()));
    }

    public Transaction withdraw(Account account, Double amount) {
        return createTransaction(account, "withdraw", -1 * amount, new Timestamp(System.currentTimeMillis()));
    }

    public Transaction transfer(Account from, Account to, Double amount) {
        // TODO: Pegar o nome da api de usuários
        LinkedHashMap<String, String> origin = new LinkedHashMap<String, String>();
        origin.put("origin", from.getUserId().toString());
        LinkedHashMap<String, String> destiny = new LinkedHashMap<String, String>();
        destiny.put("destiny", to.getUserId().toString());

        Gson gson = new Gson();

        createTransaction(
                to, "transfer", amount,
                new Timestamp(System.currentTimeMillis()), gson.toJson(origin));

        return createTransaction(
                from, "transfer", -1 * amount,
                new Timestamp(System.currentTimeMillis()), gson.toJson(destiny));
    }
}
