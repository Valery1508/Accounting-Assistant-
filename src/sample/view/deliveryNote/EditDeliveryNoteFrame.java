package sample.view.deliveryNote;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import sample.controller.*;

import java.sql.SQLException;

public class EditDeliveryNoteFrame {

    private DeliveryNoteController deliveryNoteController;
    private ProductController productController;
    private CustomerController customerController;
    private PriceController priceController;
    private BankController bankController;
    private VBox editFrame;

    public EditDeliveryNoteFrame() {
        deliveryNoteController = new DeliveryNoteController();
        productController = new ProductController();
        customerController = new CustomerController();
        priceController = new PriceController();
        bankController = new BankController();
    }

    public VBox editDN() throws SQLException {
        editFrame = new VBox();
        Label editingLabel = new Label("Edit delivery notes info");
        editingLabel.setFont(Font.font(20));
        editingLabel.setStyle("-fx-font-weight: bold");

        Button editButton = new Button("EDIT");
        editButton.setMinWidth(90);

        actions(editButton);

        editFrame.getChildren().addAll(editingLabel, deliveryNoteController.createTableOfDN(), editButton);
        editFrame.setPadding(new Insets(10, 10, 0, 10));
        editFrame.setSpacing(10);

        return editFrame;
    }

    private void actions(Button editButton){
        editButton.setOnAction(actionEvent -> {
            try {
                deliveryNoteController.takeRecord();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
}
