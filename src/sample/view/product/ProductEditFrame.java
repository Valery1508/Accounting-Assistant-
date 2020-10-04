package sample.view.product;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import sample.controller.ProductController;

import java.sql.SQLException;

public class ProductEditFrame {

    private ProductController productController;
    private VBox group;

    public ProductEditFrame() {
        productController = new ProductController();
    }

    public VBox editProduct() throws SQLException, ClassNotFoundException {

        group = new VBox();
        Label editingLabel = new Label("Edit products info");
        editingLabel.setFont(Font.font(20));
        editingLabel.setStyle("-fx-font-weight: bold");

        Button editButton = new Button("EDIT");
        editButton.setMinWidth(90);

        actions(editButton);

        group.getChildren().addAll(editingLabel, productController.createTableOfProducts(), editButton);
        group.setPadding(new Insets(10, 10, 0, 10));
        group.setSpacing(10);
        return group;
    }

    private void actions(Button editButton){
        editButton.setOnAction(actionEvent -> {
            try {
                productController.takeRecord();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
}
