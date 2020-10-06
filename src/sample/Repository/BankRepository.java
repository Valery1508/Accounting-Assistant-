package sample.Repository;

import sample.model.Bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BankRepository extends ConnectionToDB {

    private Connection connection;

    public BankRepository() {
    }

    public void addBank(Bank bank) throws SQLException {

        try {
            connection = getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String SQL = "INSERT INTO BANK (bankN, bankName)\n" + "VALUES\n (?, ?);";
        PreparedStatement preparedStatement = getPreparedStatement(SQL);
        preparedStatement.setInt(1, bank.getBankN());
        preparedStatement.setString(2, bank.getBankName());

        preparedStatement.execute();
        closePrepareStatement(preparedStatement);
        connection.close();

    }

}
