package sample.view.ShowFrame;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import sample.controller.DeliveryNoteController;

import java.sql.SQLException;
import java.time.LocalDate;

public class ShowTableOfCustomersMaxSumAndDate {

    private DeliveryNoteController deliveryNoteController;
    private VBox group;
    private HBox form;
    private Label customerByDateAndMAxSum;
    private ComboBox dates;

    public ShowTableOfCustomersMaxSumAndDate() {
        deliveryNoteController = new DeliveryNoteController();
    }

    public VBox showTable() throws SQLException {
        group = new VBox();
        customerByDateAndMAxSum = new Label("Customer who made a delivery note on certain date on max sum:");
        customerByDateAndMAxSum.setFont(Font.font(20));
        customerByDateAndMAxSum.setStyle("-fx-font-weight: bold");

        createForm();

        Button showButton = new Button("SHOW");
        showButton.setMinWidth(90);

        action(showButton);

        group.getChildren().addAll(customerByDateAndMAxSum, form, showButton);
        group.setSpacing(10);
        group.setPadding(new Insets(10, 10, 0, 10));
        return group;
    }

    private void createForm() throws SQLException {
        form = new HBox();

        ObservableList<String> dateList = FXCollections.observableArrayList(deliveryNoteController.getDateList());
        dates = new ComboBox<String>(dateList);

        form.getChildren().addAll(dates);
        form.setSpacing(10);
    }

    private void action(Button show){
        show.setOnAction(actionEvent -> {
            try {
                group.getChildren().addAll(deliveryNoteController.showCustomerDateMaxSum(dates.getValue().toString()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
