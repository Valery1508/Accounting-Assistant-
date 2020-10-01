package sample.view.product;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import sample.controller.ProductController;
import sample.view.table.ProductTable;

import java.sql.SQLException;

//таблица всех товаров, можно изменять любое поле
public class ProductEditFrame {

    private ProductController productController;
    public VBox group;

    public ProductEditFrame() {
        productController = new ProductController();
    }

    public VBox editProduct() throws SQLException, ClassNotFoundException {

        group = new VBox();
        Label editingLabel = new Label("Edit products info");
        editingLabel.setFont(Font.font(20));
        editingLabel.setStyle("-fx-font-weight: bold");

        group.getChildren().addAll(editingLabel, productController.createTableOfProducts());
        /*ProductTable productTable = new ProductTable();
        group.getChildren().addAll(editingLabel, productTable.createTable(productController.getProductList()));*/
        group.setPadding(new Insets(10, 10, 0, 10));
        return group;
    }
}
