package sample.controller;

import sample.Repository.ProductRepository;
import sample.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductController {
//сделать private
    public List<String> categories;
    private List<Product> productList;
     //todo один контроллер принимает имя команды -> команда вызывает сервис(их может быть много) в котором все вычисления
    //todo -> сервис вызывает нужный репозиторий (их много) -> репозиторий вызывает нужную db -> db


    public List<String> getCategoryList() throws SQLException, ClassNotFoundException {
        ProductRepository productRepository = new ProductRepository();  //todo сделать ее локальной переменной(private)
        //System.out.println(productRepository.getCategories());
        categories = productRepository.getCategories();
        return categories;
    }

    public List<Product> getProductList() throws SQLException, ClassNotFoundException {
        ProductRepository productRepository = new ProductRepository();
        //productList = new ArrayList<>();
        productList = productRepository.getProducts();
        //convertResultSetIntoProduct(productRepository.getProducts(), productList);
        return productList;
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


}
