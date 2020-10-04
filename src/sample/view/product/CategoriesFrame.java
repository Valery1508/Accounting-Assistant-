package sample.view.product;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import sample.controller.ProductController;

import java.sql.SQLException;

public class CategoriesFrame {

    private ProductController productController;
    private VBox group;

    public CategoriesFrame(){productController = new ProductController();}

    public VBox showCategories() throws SQLException {
        group = new VBox();

        Label categoriesLabel = new Label("All categories: ");
        categoriesLabel.setFont(Font.font(20));
        categoriesLabel.setStyle("-fx-font-weight: bold");

        group.getChildren().addAll(categoriesLabel, productController.createTableOfCategories());
        group.setSpacing(10);
        group.setPadding(new Insets(10, 10, 0, 10));
        return group;
    }
}
