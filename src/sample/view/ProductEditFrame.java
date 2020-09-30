package sample.view;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import sample.controller.ProductController;

import java.sql.SQLException;

//таблица всех товаров, можно изменять любое поле
public class ProductEditFrame {

    private ProductController productController;
    public VBox group;

    public ProductEditFrame() {
        productController = new ProductController();
    }

    protected VBox editProduct() throws SQLException, ClassNotFoundException {

        group = new VBox();
        Label editingLabel = new Label("Products editing");
        editingLabel.setFont(Font.font(20));
        System.out.println(productController.getProductList());
        group.getChildren().addAll(editingLabel);
        return group;
    }
}
