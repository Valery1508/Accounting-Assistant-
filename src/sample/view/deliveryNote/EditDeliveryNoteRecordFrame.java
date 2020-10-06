package sample.view.deliveryNote;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.controller.DeliveryNoteController;

import java.sql.SQLException;
import java.time.LocalDate;

public class EditDeliveryNoteRecordFrame {

    private HBox form;
    private VBox group;
    private TextField idF, idCustF, codeF, priceF, quantityF;
    private DatePicker datePicker;
    private DeliveryNoteController deliveryNoteController;
    private Scene scene;
    private Stage stage;
    private int oldId;

    public EditDeliveryNoteRecordFrame() {
        deliveryNoteController = new DeliveryNoteController();
    }

    public void editDNRecord(int id, int idCustomer, int codeP, String data, double price, int quantity) throws SQLException, ClassNotFoundException {
        oldId = id;
        createForm(id, idCustomer, codeP, data, price, quantity);

        Button makeChange = new Button("Make changes");
        makeChange.setMinWidth(90);
        action(makeChange);

        group = new VBox();
        group.getChildren().addAll(form, makeChange);
        group.setSpacing(10);
        group.setPadding(new Insets(10, 10, 0, 10));

        scene = new Scene(group, 700, 100);
        stage = new Stage();
        stage.setScene(scene);
        stage.setMinWidth(700);
        stage.setMinHeight(100);
        stage.setResizable(false);
        stage.setTitle("Editing");
        stage.show();
    }

    private void createForm(int id, int idCustomer, int code, String data, double price, int quantity) throws SQLException, ClassNotFoundException {
        form = new HBox();

        idF = new TextField(String.valueOf(id));
        idCustF = new TextField(String.valueOf(idCustomer));
        codeF = new TextField(String.valueOf(code));
        datePicker = new DatePicker();
        datePicker.setValue(LocalDate.parse(data));
        datePicker.setShowWeekNumbers(false);
        priceF = new TextField(String.valueOf(price));
        quantityF = new TextField(String.valueOf(quantity));


        form.getChildren().addAll(idF, idCustF, codeF, datePicker, priceF, quantityF);
        form.setSpacing(10);
    }

    public void action(Button makeChanges){
        makeChanges.setOnAction(actionEvent -> {
            try {
                if(Integer.parseInt(idF.getText())!=0 || Integer.parseInt(idCustF.getText())!=0 || Integer.parseInt(codeF.getText())!=0
                        || Double.parseDouble(priceF.getText())!=0 || Integer.parseInt(quantityF.getText())!=0) {
                    deliveryNoteController.setEditedRecord(oldId, Integer.parseInt(idF.getText()), Integer.parseInt(idCustF.getText()),
                            Integer.parseInt(codeF.getText()), datePicker.getValue().toString(), Double.parseDouble(priceF.getText()), Integer.parseInt(quantityF.getText()));
                    alertSuccess();
                } else {
                    alertError();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private void alertSuccess(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Delivery note edited successfully!");
        alert.showAndWait();
        stage.close();
    }

    private void alertError(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Fill in the fields correctly!");
        alert.showAndWait();
    }

}
