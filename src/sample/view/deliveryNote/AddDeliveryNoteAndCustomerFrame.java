package sample.view.deliveryNote;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import sample.controller.*;

import java.sql.SQLException;
import java.time.LocalDate;

public class AddDeliveryNoteAndCustomerFrame {

    private DeliveryNoteController deliveryNoteController;
    private ProductController productController;
    private CustomerController customerController;
    private PriceController priceController;
    private BankController bankController;
    private VBox group;
    private VBox form;
    private HBox fLine, prodLine, sLine, addressLine, bankLine;
    private Label dnLabel, customerLabel;
    private TextField idCustomerF, priceF, quantityField, fNameField, sNameField, regionField, streetField, houseField, bankNField;
    private ComboBox codes, types, bankNames;
    private DatePicker dateField;

    public AddDeliveryNoteAndCustomerFrame() {
        deliveryNoteController = new DeliveryNoteController();
        productController = new ProductController();
        customerController = new CustomerController();
        priceController = new PriceController();
        bankController = new BankController();
    }

    public VBox addDeliveryNoteAndCustomer() throws SQLException {
        group = new VBox();

        dnLabel = new Label("Add delivery note");
        dnLabel.setFont(Font.font(20));
        dnLabel.setStyle("-fx-font-weight: bold");

        createForm();

        Button addButton = new Button("ADD");
        addButton.setMinWidth(90);
        action(addButton);

        group.getChildren().addAll(dnLabel, form, addButton);
        group.setSpacing(10);
        group.setPadding(new Insets(10, 0, 0, 10));

        return group;
    }

    public void createForm() throws SQLException {
        form = new VBox();

        fLine = new HBox();
        idCustomerF = new TextField();
        idCustomerF.setPromptText("Id Customer(e.g. 89)");

        ObservableList<Integer> codeList = FXCollections.observableArrayList(productController.getCodeList());
        codes = new ComboBox<Integer>(codeList);

        dateField = new DatePicker();
        dateField.setValue(LocalDate.now());
        dateField.setShowWeekNumbers(false);

        fLine.getChildren().addAll(idCustomerF, codes, dateField);
        fLine.setSpacing(10);

        prodLine = new HBox();
        priceF = new TextField();
        priceF.setPromptText("Price(e.g. 87.4)");

        quantityField = new TextField();
        quantityField.setPromptText("Quantity");

        prodLine.getChildren().addAll(priceF, quantityField);
        prodLine.setSpacing(10);

        customerLabel = new Label("Add customer info");
        customerLabel.setFont(Font.font(20));
        customerLabel.setStyle("-fx-font-weight: bold");

        sLine = new HBox();
        ObservableList<String> typeList = FXCollections.observableArrayList(customerController.getTypeList());
        types = new ComboBox<String>(typeList);

        fNameField = new TextField();
        fNameField.setPromptText("First name");

        sNameField = new TextField();
        sNameField.setPromptText("Second name");

        sLine.getChildren().addAll(types, fNameField, sNameField);
        sLine.setSpacing(10);

        addressLine = new HBox();
        regionField = new TextField();
        regionField.setPromptText("Region");

        streetField = new TextField();
        streetField.setPromptText("Street");

        houseField = new TextField();
        houseField.setPromptText("House");

        addressLine.getChildren().addAll(regionField, streetField, houseField);
        addressLine.setSpacing(10);

        bankLine = new HBox();
        bankNField = new TextField();
        bankNField.setPromptText("Bank number");

        ObservableList<String> bankNamesList = FXCollections.observableArrayList("BelarusBank", "Alfa-Bank",
                                                                                        "BelinvestBank", "BPS-Sberbank",
                                                                                        "IdeaBank", "BelagropromBank");
        bankNames = new ComboBox<String>(bankNamesList);

        bankLine.getChildren().addAll(bankNField, bankNames);
        bankLine.setSpacing(10);

        form.getChildren().addAll(fLine, prodLine, customerLabel, sLine, addressLine, bankLine);
        form.setSpacing(10);
    }

    private void action(Button addButton){
        addButton.setOnAction(actionEvent -> {
            try {
                priceController.addDateAndPrice((Integer) codes.getValue(), dateField.getValue().toString(), Double.parseDouble(priceF.getText()));
                customerController.addCustomerToBD(Integer.parseInt(idCustomerF.getText()), types.getValue().toString(), fNameField.getText(), sNameField.getText(), regionField.getText(), streetField.getText(), Integer.parseInt(houseField.getText()), Integer.parseInt(bankNField.getText()));
                bankController.addBankToBD(Integer.parseInt(bankNField.getText()), bankNames.getValue().toString());
                deliveryNoteController.addDeliveryNoteToBD(Integer.parseInt(idCustomerF.getText()), (Integer) codes.getValue(), dateField.getValue().toString(), Double.parseDouble(priceF.getText()), Integer.parseInt(quantityField.getText()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            alertSuccess();
        });
    }

    private void alertSuccess(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Delivery note and customer added successfully!");
        alert.showAndWait();
    }

    private void alertError(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Fill in the fields correctly!");
        alert.showAndWait();
    }

}
