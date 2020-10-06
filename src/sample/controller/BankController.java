package sample.controller;

import sample.Repository.BankRepository;
import sample.model.Bank;

import java.sql.SQLException;

public class BankController {

    private Bank bank;

    public void addBankToBD(int number, String name) throws SQLException {
        bank = new Bank(number, name);
        BankRepository bankRepository = new BankRepository();
        bankRepository.addBank(bank);
    }
}
