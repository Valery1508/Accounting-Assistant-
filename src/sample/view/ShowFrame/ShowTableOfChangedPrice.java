package sample.view.ShowFrame;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import sample.controller.PriceController;
import sample.controller.ProductController;

import java.sql.SQLException;
import java.time.LocalDate;

public class ShowTableOfChangedPrice {

    private VBox group;
    private HBox form;
    private BorderPane all;
    private Label changedPriceLabel;
    private ComboBox codes;
    private DatePicker dateFrom, dateTo;
    private ProductController productController;
    private PriceController priceController;
    //todo сделай для всей формы и таблицы BorderPane (чтобы можно было посмотреть изменения для одного товара и для другого, нажав на кнопку меняется таблица)

    public ShowTableOfChangedPrice(){
        productController = new ProductController();
        priceController = new PriceController();
    }

    public VBox show() throws SQLException {
        group = new VBox();

        changedPriceLabel = new Label("List of changes of product price for the specified period of time:");
        changedPriceLabel.setFont(Font.font(20));
        changedPriceLabel.setStyle("-fx-font-weight: bold");

        createForm();

        Button showButton = new Button("SHOW");
        showButton.setMinWidth(90);

        action(showButton);

        group.getChildren().addAll(changedPriceLabel, form, showButton);
        group.setSpacing(10);
        group.setPadding(new Insets(10, 10, 0, 10));
        return group;
    }

    private void createForm() throws SQLException {
        form = new HBox();

        ObservableList<Integer> codeList = FXCollections.observableArrayList(productController.getCodeList());
        codes = new ComboBox<Integer>(codeList);

        dateFrom = new DatePicker();
        dateFrom.setValue(LocalDate.now());
        dateFrom.setShowWeekNumbers(false);

        dateTo = new DatePicker();
        dateTo.setValue(LocalDate.now());
        dateTo.setShowWeekNumbers(false);

        form.getChildren().addAll(codes, dateFrom, dateTo);
        form.setSpacing(10);
    }

    private void action(Button show){
        show.setOnAction(actionEvent -> {
            try {
                group.getChildren().addAll(priceController.showChangedPriceForProductByProductCodeAndByDate((Integer) codes.getValue(), dateFrom.getValue().toString(), dateTo.getValue().toString()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
