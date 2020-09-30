package sample.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;


public class MainFrame {
    private Stage stage;
    private Scene scene;
    private VBox root;
    private MenuBar menuBar;

    public MainFrame(Stage stage){
    this.stage = stage;
}

public void show(){
//TODO add в меню еще два просмотра
    menuBar = new MenuBar();
    menuBar.getMenus().addAll(createProductMenu(),
                              createDeliveryNoteMenu(),
                              createProductCategoryMenu());

    root = new VBox();
    root.getChildren().addAll(menuBar);

    scene = new Scene(root, 600, 600);
    stage.setScene(scene);
    stage.setMinWidth(800);
    stage.setMinHeight(800);
    stage.setResizable(false);
    stage.setTitle("Marketing Department");
    stage.show();
};

    private Menu createProductMenu() {
        Menu productMenu = new Menu("Product");
        MenuItem addProd = new MenuItem("Add");
        MenuItem editProd = new MenuItem("Edit");
        MenuItem deleteProd = new MenuItem("Delete");

        addProd.setOnAction(actionEvent -> {
            //updateMainFrame();
            ProductAddFrame productAddFrame = new ProductAddFrame();
            try {
                root.getChildren().addAll(productAddFrame.addProduct());
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            //todo проверка на правильность введенных данных
            //todo вывести алерт "продукт добавлен"
            //todo обновить mainframe
        });


        editProd.setOnAction(actionEvent -> {
            //updateMainFrame();
            ProductEditFrame productEditFrame = new ProductEditFrame();
            try {
                root.getChildren().addAll(productEditFrame.editProduct());
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        productMenu.getItems().addAll(addProd, editProd, deleteProd);
        return productMenu;
    }

    private Menu createDeliveryNoteMenu(){
        Menu deliveryNoteMenu = new Menu("Delivery Note");
        MenuItem addDN = new MenuItem("Add");
        MenuItem editDN = new MenuItem("Edit");
        MenuItem deleteDN = new MenuItem("Delete");
        deliveryNoteMenu.getItems().addAll(addDN, editDN, deleteDN);
        return deliveryNoteMenu;
    }

    private Menu createProductCategoryMenu(){
        Menu productCategoryMenu = new Menu("Categories");
        MenuItem showCategories = new MenuItem("Show all");
        productCategoryMenu.getItems().addAll(showCategories);
        return productCategoryMenu;
    }

    public void updateMainFrame(){
        stage.close();
        show();
    }
}
