package sample.view.product;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import sample.controller.ProductController;

import java.sql.SQLException;

import static java.lang.Integer.parseInt;

public class ProductAddFrame {
    private ProductController productController;
    private HBox form;
    private VBox allElements;
    private Label addingLabel;
    private TextField textFieldCodeP, textFieldNameP;
    private ComboBox<String> categoryComboBox;

    public ProductAddFrame() {
        productController = new ProductController();
    }

    public VBox addProduct() throws SQLException, ClassNotFoundException {

        createForm();

        Button addButton = new Button("ADD");
        addButton.setMinWidth(90);
        actions(addButton);

        allElements = new VBox();
        allElements.getChildren().addAll(addingLabel, form, addButton);
        allElements.setSpacing(10);
        allElements.setPadding(new Insets(10, 0, 0, 10));

        return allElements;
    }

    private void createForm() throws SQLException, ClassNotFoundException {
        addingLabel = new Label("Add new product");
        addingLabel.setFont(Font.font(20));
        addingLabel.setStyle("-fx-font-weight: bold");

        textFieldCodeP = new TextField();
        textFieldCodeP.setPromptText("Product code(e.g. 73891)");

        textFieldNameP = new TextField();
        textFieldNameP.setPromptText("Product name");

        ObservableList<String> categories = FXCollections.observableArrayList(productController.getCategoryList());
        categoryComboBox = new ComboBox<String>(categories);

        form = new HBox();
        form.getChildren().addAll(textFieldCodeP, textFieldNameP, categoryComboBox);
        form.setSpacing(10);
    }

    public void actions(Button addButton){
        addButton.setOnAction(actionEvent -> {
            if(!textFieldCodeP.getText().equals("") && !textFieldNameP.getText().equals("")){
                try {
                    productController.addProduct(parseInt(textFieldCodeP.getText()), textFieldNameP.getText(), categoryComboBox.getValue());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                alertSuccess();
            }
            else {
                if (textFieldCodeP.getText().equals("") || !textFieldNameP.getText().equals("")){
                    alertError();
                }
            }
        });
    }

    private void alertSuccess(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Product added successfully!");
        alert.showAndWait();
    }

    private void alertError(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Fill in the fields correctly!");
        alert.showAndWait();
    }
}
