package sample.Repository;

import sample.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository extends ConnectionToDB {

    private Connection connection;

    public CustomerRepository() {
    }

    public List<String> getTypes() throws SQLException {
        try {
            connection = getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select distinct type from customer;");
        List<String> types = new ArrayList<>();
        while (resultSet.next()) {
            types.add(resultSet.getString(1));
        }
        statement.close();
        connection.close();
        return types;
    }

    public void addCustomer(Customer customer) throws SQLException {

        try {
            connection = getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String SQL = "INSERT INTO CUSTOMER (idCustomer, type, firstName, secondName, region, street, house, bankN)\n" + "VALUES\n (?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement preparedStatement = getPreparedStatement(SQL);
        preparedStatement.setInt(1, customer.getIdCustomer());
        preparedStatement.setString(2, customer.getType());
        preparedStatement.setString(3, customer.getFirstName());
        preparedStatement.setString(4, customer.getSecondName());
        preparedStatement.setString(5, customer.getRegion());
        preparedStatement.setString(6, customer.getStreet());
        preparedStatement.setInt(7, customer.getHouse());
        preparedStatement.setInt(8, customer.getBankN());

        preparedStatement.execute();
        closePrepareStatement(preparedStatement);
        connection.close();

    }

}
