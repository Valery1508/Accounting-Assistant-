package sample.view.deliveryNote;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import sample.controller.DeliveryNoteController;

import java.sql.SQLException;

public class DeleteDeliveryNoteFrame {

    private DeliveryNoteController deliveryNoteController;
    private VBox group;
    private Label deleteLabel;
    private ComboBox<Integer> idBox;

    public DeleteDeliveryNoteFrame() {
        deliveryNoteController = new DeliveryNoteController();
    }

    public VBox deleteDN() throws SQLException, ClassNotFoundException {
        group = new VBox();

        createForm();

        Button deleteButton = new Button("DELETE");
        deleteButton.setMinWidth(90);
        actions(deleteButton);

        group.getChildren().addAll(deleteLabel, idBox, deleteButton);
        group.setSpacing(10);
        group.setPadding(new Insets(10, 0, 0, 10));

        return group;
    }

    private void createForm() throws SQLException, ClassNotFoundException {
        deleteLabel = new Label("Delete delivery note by id");
        deleteLabel.setFont(Font.font(20));
        deleteLabel.setStyle("-fx-font-weight: bold");

        ObservableList<Integer> ids = FXCollections.observableArrayList(deliveryNoteController.getIdList());
        idBox = new ComboBox<Integer>(ids);
    }

    private void actions(Button deleteButton){
        deleteButton.setOnAction(actionEvent -> {
            try {
                deliveryNoteController.deleteDNFromDB(idBox.getValue());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            alertSuccess();
        });
    }

    private void alertSuccess(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Delivery note deleted successfully!");
        alert.showAndWait();
    }

}
