package sample.controller;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import sample.Repository.ProductRepository;
import sample.model.Category;
import sample.model.Price;
import sample.model.Product;
import sample.view.product.ProductEditFrame;
import sample.view.product.ProductEditRecordFrame;
import sample.view.table.CategoryTable;
import sample.view.table.ProductTable;

import java.sql.SQLException;
import java.util.List;

public class ProductController {

    private List<String> categories;
    private List<Integer> codes;
    private List<Product> productList;
    private List<Category> listOfCategories;
    private ProductTable productTable;
    private CategoryTable categoryTable;
    private Product product;


    public List<String> getCategoryList() throws SQLException {
        ProductRepository productRepository = new ProductRepository();  //todo сделать ее локальной переменной(private)
        categories = productRepository.getCategories();
        return categories;
    }

    public List<Integer> getCodeList() throws SQLException {
        ProductRepository productRepository = new ProductRepository();
        codes = productRepository.getCodes();
        return codes;
    }

    public List<Product> getProductList() throws SQLException {
        ProductRepository productRepository = new ProductRepository();
        productList = productRepository.getProducts();
        return productList;
    }

    public TableView<Product> createTableOfProducts() throws SQLException {
        productTable = new ProductTable();
        return productTable.createTable(getProductList());
    }

    public void addProduct(int codeP, String nameP, String category) throws SQLException {
        product = new Product(codeP, nameP, category);
        ProductRepository productRepository = new ProductRepository();
        productRepository.addProduct(product);
    }

    public void deleteProduct(int codeP) throws SQLException {
        ProductRepository productRepository = new ProductRepository();
        productRepository.deleteProduct(codeP);
    }

    public void takeRecord() throws SQLException, ClassNotFoundException {
        int selectedCode = productTable.getSelectedCode();
        String selectedName = productTable.getSelectedName();
        String selectedCategory = productTable.getSelectedCategory();

        ProductEditRecordFrame productEditRecordFrame = new ProductEditRecordFrame();
        productEditRecordFrame.editRecord(selectedCode, selectedName, selectedCategory);
    }

    public void setEditedRecord(int oldCode, int code, String name, String category) throws SQLException {
        deleteProduct(oldCode);
        addProduct(code, name, category);
        //productTable.addToObservableList(product);
    }

    public List<Category> getListOfCategories() throws SQLException {
        ProductRepository productRepository = new ProductRepository();
        listOfCategories = productRepository.getListOfCategories();
        return listOfCategories;
    }
    public TableView<Category> createTableOfCategories() throws SQLException {
        categoryTable = new CategoryTable();
        return categoryTable.createTable(getListOfCategories());
    }

}
