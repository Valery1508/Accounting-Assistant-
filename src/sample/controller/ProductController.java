package sample.controller;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import sample.Repository.ProductRepository;
import sample.model.Product;
import sample.view.table.ProductTable;

import java.sql.SQLException;
import java.util.List;

public class ProductController {
//сделать private
    public List<String> categories;
    public List<Integer> codes;
    private List<Product> productList;
    private ObservableList<Product> data;
     //todo один контроллер принимает имя команды -> команда вызывает сервис(их может быть много) в котором все вычисления
    //todo -> сервис вызывает нужный репозиторий (их много) -> репозиторий вызывает нужную db -> db


    public List<String> getCategoryList() throws SQLException, ClassNotFoundException {
        ProductRepository productRepository = new ProductRepository();  //todo сделать ее локальной переменной(private)
        //System.out.println(productRepository.getCategories());
        categories = productRepository.getCategories();
        return categories;
    }

    public List<Integer> getCodeList() throws SQLException, ClassNotFoundException {
        ProductRepository productRepository = new ProductRepository();
        //System.out.println(productRepository.getCategories());
        codes = productRepository.getCodes();
        return codes;
    }

    public List<Product> getProductList() throws SQLException, ClassNotFoundException {
        ProductRepository productRepository = new ProductRepository();
        productList = productRepository.getProducts();
            //convertResultSetIntoProduct(productRepository.getProducts(), productList);
        return productList;
    }

    public TableView<Product> createTableOfProducts() throws SQLException, ClassNotFoundException {
        ProductTable productTable = new ProductTable();
        return productTable.createTable(getProductList());
    }


    /*private void convertResultSetIntoProduct(ResultSet resultSet, List<Product> list) throws SQLException {
        while (resultSet.next()) {
            Product product = new Product();
            product.setCodeP(resultSet.getInt(1));
            product.setNameP(resultSet.getString(2));
            product.setCategory(resultSet.getString(3));
            list.add(product);
        }
        resultSet.close();
    }*/

    public void addProduct(int codeP, String nameP, String category) throws SQLException {
        Product product = new Product(codeP, nameP, category);
        ProductRepository productRepository = new ProductRepository();
        productRepository.addProduct(product);
    }

    public void deleteProduct(int codeP) throws SQLException {
        ProductRepository productRepository = new ProductRepository();
        productRepository.deleteProduct(codeP);
    }



}
