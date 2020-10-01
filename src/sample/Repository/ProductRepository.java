package sample.Repository;

import javafx.scene.control.TableView;
import sample.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository extends ConnectionToDB {
   //todo в каждом репозитории такое
    //add: в view если add -> создаем объект product -> идем в контроллер и потом ->repository
    private Connection connection;
    //private List<Product> products;

    public ProductRepository() {
    }

    public void addProduct(Product product) throws SQLException {
        try {
            connection = getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String SQL = "INSERT INTO PRODUCT (codeP, nameP, category)\n" + "VALUES\n (?, ?, ?);";
        //PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        PreparedStatement preparedStatement = getPreparedStatement(SQL);
        preparedStatement.setInt(1, product.getCodeP());
        preparedStatement.setString(2, product.getNameP());
        preparedStatement.setString(3, product.getCategory());

        preparedStatement.execute();
        //preparedStatement.close();
        closePrepareStatement(preparedStatement);
        connection.close();
    }

    public List<String> getCategories() throws SQLException {
        try {
            connection = getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Statement statement = connection.createStatement();
        String SQL = "select distinct category from product;";
        ResultSet resultSet = statement.executeQuery(SQL);
        List<String> categories = new ArrayList<>();
        while (resultSet.next()) {
            categories.add(resultSet.getString(1));
        }
        statement.close();
        connection.close();
        return categories;
    }

    public List<Integer> getCodes() throws SQLException {
        try {
            connection = getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select codeP from product;");
        List<Integer> codes = new ArrayList<>();
        while (resultSet.next()) {
            codes.add(resultSet.getInt(1));
        }
        statement.close();
        connection.close();
        return codes;
    }

    public List<Product> getProducts() throws SQLException, ClassNotFoundException {
        try {
            connection = getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from product;");
        //
        List<Product> products = new ArrayList<>();
        while (resultSet.next()) {
            Product product = new Product();
            product.setCodeP(resultSet.getInt(1));
            product.setNameP(resultSet.getString(2));
            product.setCategory(resultSet.getString(3));
            products.add(product);
        }
        //System.out.println(products);
        //
        statement.close();
        connection.close();
        return products;
    }

    public void deleteProduct(int codeP) throws SQLException {
        try {
            connection = getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String SQL = "DELETE FROM product WHERE codeP = ?;";
        PreparedStatement preparedStatement = getPreparedStatement(SQL);
        preparedStatement.setInt(1, codeP);
        preparedStatement.execute();
        closePrepareStatement(preparedStatement);
        connection.close();
    }


}
