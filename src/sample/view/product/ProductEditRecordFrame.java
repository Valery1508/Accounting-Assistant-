package sample.view.product;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.controller.ProductController;

import java.sql.SQLException;

public class ProductEditRecordFrame {

    private HBox form;
    private VBox group;
    private TextField code, name;
    private ComboBox categoryComboBox;
    private ProductController productController;
    private Scene scene;
    private Stage stage;
    private int oldCode;

    public ProductEditRecordFrame() {
        productController = new ProductController();
    }

    public void editRecord(int codeP, String nameP, String category) throws SQLException, ClassNotFoundException {

        oldCode = codeP;
        createForm(oldCode, nameP, category);

        Button makeChanges = new Button("Make changes");
        makeChanges.setMinWidth(90);
        action(makeChanges);

        group = new VBox();
        group.getChildren().addAll(form, makeChanges);
        group.setSpacing(10);
        group.setPadding(new Insets(10, 10, 0, 10));

        scene = new Scene(group, 500, 100);
        stage = new Stage();
        stage.setScene(scene);
        stage.setMinWidth(500);
        stage.setMinHeight(100);
        stage.setResizable(false);
        stage.setTitle("Editing");
        stage.show();
    }

    private void createForm(int codeP, String nameP, String category) throws SQLException, ClassNotFoundException {
        form = new HBox();

        code = new TextField(String.valueOf(codeP));
        name = new TextField(nameP);
        ObservableList<String> categories = FXCollections.observableArrayList(productController.getCategoryList());
        categoryComboBox = new ComboBox<String>(categories);
        categoryComboBox.setValue(category);

        form.getChildren().addAll(code, name, categoryComboBox);
        form.setSpacing(10);
    }

    public void action(Button makeChanges){
        makeChanges.setOnAction(actionEvent -> {
            try {
                if(Integer.parseInt(code.getText())!=0 && !name.getText().equals("")) {
                    productController.setEditedRecord(oldCode, Integer.parseInt(code.getText()), name.getText(), (String) categoryComboBox.getValue());
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
        alert.setContentText("Product edited successfully!");
        alert.showAndWait();
        stage.close();
    }

    private void alertError(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Fill in the fields correctly!");
        alert.showAndWait();
    }
}
