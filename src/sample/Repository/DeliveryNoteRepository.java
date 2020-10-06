package sample.Repository;

import sample.model.DeliveryNote;

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

}
