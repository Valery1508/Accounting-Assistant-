package sample.Repository;

import sample.model.Customer;
import sample.model.DeliveryNote;
import sample.model.Price;
import sample.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeliveryNoteRepository extends ConnectionToDB {

    private Connection connection;

    public DeliveryNoteRepository() {
    }

    public void addDN(DeliveryNote deliveryNote) throws SQLException {
        try {
            connection = getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String SQL = "INSERT INTO DELIVERYNOTE (id, idCustomer, codeP, date, price, quantityP)\n" + "VALUES\n (?, ?, ?, ?, ?, ?);";
        PreparedStatement preparedStatement = getPreparedStatement(SQL);
        preparedStatement.setInt(1, deliveryNote.getId());
        preparedStatement.setInt(2, deliveryNote.getIdCustomer());
        preparedStatement.setInt(3, deliveryNote.getCodeP());
        preparedStatement.setString(4, deliveryNote.getDate());
        preparedStatement.setDouble(5, deliveryNote.getPrice());
        preparedStatement.setInt(6, deliveryNote.getQuantity());

        preparedStatement.execute();
        closePrepareStatement(preparedStatement);
        connection.close();

    }

    public List<Integer> getIds() throws SQLException {
        try {
            connection = getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select distinct id from deliverynote;");
        List<Integer> idList = new ArrayList<>();
        while (resultSet.next()) {
            idList.add(resultSet.getInt(1));
        }
        statement.close();
        connection.close();
        return idList;
    }

    public void deleteDN(int id) throws SQLException {
        try {
            connection = getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String SQL = "DELETE FROM deliverynote WHERE id = ?;";
        PreparedStatement preparedStatement = getPreparedStatement(SQL);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        closePrepareStatement(preparedStatement);
        connection.close();
    }

    public List<DeliveryNote> getAllDN() throws SQLException {
        try {
            connection = getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from deliverynote;");

        List<DeliveryNote> products = new ArrayList<>();
        while (resultSet.next()) {
            DeliveryNote deliveryNote = new DeliveryNote();
            deliveryNote.setId(resultSet.getInt(1));
            deliveryNote.setIdCustomer(resultSet.getInt(2));
            deliveryNote.setCodeP(resultSet.getInt(3));
            deliveryNote.setDate(resultSet.getString(4));
            deliveryNote.setPrice(resultSet.getDouble(5));
            deliveryNote.setQuantity(resultSet.getInt(6));
            deliveryNote.setTotalPrice(resultSet.getDouble(5)*resultSet.getInt(6));
            products.add(deliveryNote);
        }

        statement.close();
        connection.close();
        return products;
    };
//todo
    public List<Customer> getCustomersByMaxSumOnCertainDate(String date, double totalPrice) throws SQLException {
        try {
            connection = getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //String SQL = "select firstName, secondName, region, street, house from customer where idCustomer = (select idCustomer from deliverynote where price*quantityP = ?) AND date = ?;";
        String SQL = "select customer.firstName, customer.secondName, customer.region, customer.street, customer.house from customer JOIN deliverynote ON deliverynote.idCustomer = customer.idCustomer WHERE deliverynote.date = ? AND deliverynote.price*deliverynote.quantityP = ?" ;
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, date);
        preparedStatement.setDouble(2, totalPrice);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Customer> customers = new ArrayList<>();
        while (resultSet.next()) {
            Customer customer = new Customer();
            customer.setFirstName(resultSet.getString(1));
            customer.setSecondName(resultSet.getString(2));
            customer.setRegion(resultSet.getString(3));
            customer.setStreet(resultSet.getString(4));
            customer.setHouse(resultSet.getInt(5));
            customers.add(customer);
        }
        preparedStatement.close();
        connection.close();
        return customers;
    }

    public List<String> getDates() throws SQLException {
        try {
            connection = getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select distinct date from deliverynote;");

        List<String> dateList = new ArrayList<>();
        while (resultSet.next()) {
            dateList.add(resultSet.getString(1));
        }
        statement.close();
        connection.close();
        return dateList;
    }

    public List<Double> getTotalPriceList(String date) throws SQLException {
        try {
            connection = getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String SQL = "select price, quantityP from deliverynote where date = ?" ;
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, date);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Double> totalPriceList = new ArrayList<>();
        while (resultSet.next()) {
            totalPriceList.add(resultSet.getDouble(1)*resultSet.getInt(2));
        }
        preparedStatement.close();
        connection.close();
        return totalPriceList;
    }
}
