package sample.view;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.view.product.CategoriesFrame;
import sample.view.product.ProductAddFrame;
import sample.view.product.ProductDeleteFrame;
import sample.view.product.ProductEditFrame;

import java.sql.SQLException;


public class MainFrame {
    private Stage stage;
    private Scene scene;
    private BorderPane root;
    private MenuBar menuBar;

    private VBox addFrameVBox;

    public MainFrame(Stage stage){
    this.stage = stage;
}

public void show(){
//TODO add в меню еще два просмотра
    menuBar = new MenuBar();
    menuBar.getMenus().addAll(createProductMenu(),
                              createDeliveryNoteMenu(),
                              createProductCategoryMenu());
    root = new BorderPane();
    root.setTop(menuBar);

    scene = new Scene(root, 500, 500);
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

            if (!root.getChildren().contains(addFrameVBox)) {
                ProductAddFrame productAddFrame = new ProductAddFrame();
                try {
                    addFrameVBox = productAddFrame.addProduct();
                    root.setCenter(addFrameVBox);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            //todo проверка на правильность введенных данных
            //todo вывести алерт "продукт добавлен"
            //todo обновить mainframe
        });


        editProd.setOnAction(actionEvent -> {

            ProductEditFrame productEditFrame = new ProductEditFrame();
            try {
                root.setCenter(productEditFrame.editProduct());
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        deleteProd.setOnAction(actionEvent -> {
            ProductDeleteFrame productDeleteFrame = new ProductDeleteFrame();
            try {
                root.setCenter(productDeleteFrame.deleteProduct());
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

        showCategories.setOnAction(actionEvent -> {

            CategoriesFrame categoriesFrame = new CategoriesFrame();
            try {
                root.setCenter(categoriesFrame.showCategories());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        productCategoryMenu.getItems().addAll(showCategories);
        return productCategoryMenu;
    }

}
