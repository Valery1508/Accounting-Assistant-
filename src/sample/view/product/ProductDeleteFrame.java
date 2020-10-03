package sample.view.product;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import sample.controller.ProductController;

import java.sql.SQLException;

public class ProductDeleteFrame {

    private ProductController productController;
    private VBox allElements;
    private Label deleteLabel;
    private ComboBox<Integer> codeComboBox;

    public ProductDeleteFrame() {
        productController = new ProductController();
    }

    public VBox deleteProduct() throws SQLException, ClassNotFoundException {

        createForm();

        Button deleteButton = new Button("DELETE");
        deleteButton.setMinWidth(90);
        actions(deleteButton);

        allElements = new VBox();
        allElements.getChildren().addAll(deleteLabel, codeComboBox, deleteButton);
        allElements.setSpacing(10);
        allElements.setPadding(new Insets(10, 0, 0, 10));

        return allElements;
    }

    private void createForm() throws SQLException, ClassNotFoundException {
        deleteLabel = new Label("Delete product by code");
        deleteLabel.setFont(Font.font(20));
        deleteLabel.setStyle("-fx-font-weight: bold");

        ObservableList<Integer> codes = FXCollections.observableArrayList(productController.getCodeList());
        codeComboBox = new ComboBox<Integer>(codes);
    }

    public void actions(Button deleteButton){
        deleteButton.setOnAction(actionEvent -> {
                try {
                    productController.deleteProduct(codeComboBox.getValue());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            alertSuccess();
        });
    }

    private void alertSuccess(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Product deleted successfully!");
        alert.showAndWait();
    }
}
