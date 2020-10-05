package sample.view.product;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import sample.controller.PriceController;
import sample.controller.ProductController;

import java.sql.SQLException;
import java.time.LocalDate;

import static java.lang.Integer.parseInt;

public class AddPriceFrame {
    private ProductController productController;
    private PriceController priceController;
    private VBox group;
    private HBox form;
    private Label priceLabel;
    private ComboBox codes;
    private DatePicker dateField;
    private TextField priceField;

    public AddPriceFrame() {
        productController = new ProductController();
        priceController = new PriceController();
    }

    public VBox createAddPriceFrame() throws SQLException {
        group = new VBox();

        priceLabel = new Label("Add price");
        priceLabel.setFont(Font.font(20));
        priceLabel.setStyle("-fx-font-weight: bold");

        createForm();

        Button addButton = new Button("ADD");
        addButton.setMinWidth(90);
        action(addButton);

        group.getChildren().addAll(priceLabel, form, addButton);
        group.setSpacing(10);
        group.setPadding(new Insets(10, 0, 0, 10));
        return group;
    }

    public void createForm() throws SQLException {
        form = new HBox();

        ObservableList<Integer> codeList = FXCollections.observableArrayList(productController.getCodeList());
        codes = new ComboBox<Integer>(codeList);

        dateField = new DatePicker();
        dateField.setValue(LocalDate.now());
        dateField.setShowWeekNumbers(false);

        priceField = new TextField();
        priceField.setPromptText("Price(e.g. 89.4)");

        form.getChildren().addAll(codes, dateField, priceField);
        form.setSpacing(10);
    }

    private void action(Button addButton){
        addButton.setOnAction(actionEvent -> {
            if (!priceField.getText().equals("")) {
                try {
                    priceController.addDateAndPrice((Integer) codes.getValue(), dateField.getValue().toString(), Double.parseDouble(priceField.getText()));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                alertSuccess();
            } else {
                alertError();
            }

        });
    }

    private void alertSuccess(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Price added successfully!");
        alert.showAndWait();
    }

    private void alertError(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Fill in the fields correctly!");
        alert.showAndWait();
    }

}
