package sample.view;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.view.ShowFrame.ShowTableOfChangedPrice;
import sample.view.ShowFrame.ShowTableOfCustomersMaxSumAndDate;
import sample.view.deliveryNote.AddDeliveryNoteAndCustomerFrame;
import sample.view.deliveryNote.DeleteDeliveryNoteFrame;
import sample.view.deliveryNote.EditDeliveryNoteFrame;
import sample.view.product.*;

import java.sql.SQLException;


public class MainFrame {
    private Stage stage;
    private Scene scene;
    private BorderPane root;
    private MenuBar menuBar;

    private VBox addFrameVBox, addPriceVBox, addDeliveryNoteVBox;

    public MainFrame(Stage stage){
    this.stage = stage;
}

public void show(){

    menuBar = new MenuBar();
    menuBar.getMenus().addAll(createProductMenu(),
                              createDeliveryNoteMenu(),
                              createShowMenu());
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
        MenuItem addPriceProd = new MenuItem("Add price");

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

        addPriceProd.setOnAction(actionEvent -> {
            AddPriceFrame addPriceFrame = new AddPriceFrame();
            try {
                addPriceVBox = addPriceFrame.createAddPriceFrame();
                root.setCenter(addPriceVBox);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        productMenu.getItems().addAll(addProd, editProd, deleteProd, addPriceProd);
        return productMenu;
    }

    private Menu createDeliveryNoteMenu(){
        Menu deliveryNoteMenu = new Menu("Delivery Note");
        MenuItem addDN = new MenuItem("Add");
        MenuItem editDN = new MenuItem("Edit");
        MenuItem deleteDN = new MenuItem("Delete");

        addDN.setOnAction(actionEvent -> {

            if (!root.getChildren().contains(addDeliveryNoteVBox)) {
                AddDeliveryNoteAndCustomerFrame addDeliveryNoteAndCustomerFrame = new AddDeliveryNoteAndCustomerFrame();
                try {
                    addDeliveryNoteVBox = addDeliveryNoteAndCustomerFrame.addDeliveryNoteAndCustomer();
                    root.setCenter(addDeliveryNoteVBox);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        editDN.setOnAction(actionEvent -> {

            EditDeliveryNoteFrame editDeliveryNoteFrame = new EditDeliveryNoteFrame();
            try {
                root.setCenter(editDeliveryNoteFrame.editDN());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        deleteDN.setOnAction(actionEvent -> {

            DeleteDeliveryNoteFrame deleteDeliveryNoteFrame = new DeleteDeliveryNoteFrame();
                try {
                    root.setCenter(deleteDeliveryNoteFrame.deleteDN());
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
        });


        deliveryNoteMenu.getItems().addAll(addDN, editDN, deleteDN);
        return deliveryNoteMenu;
    }

    private Menu createShowMenu(){
        Menu showMenu = new Menu("Show");
        MenuItem showCategories = new MenuItem("Categories");
        MenuItem showCustomersByMaxSum = new MenuItem("Customers");
        MenuItem showListOfChangedPrice = new MenuItem("Changed price");

        showCategories.setOnAction(actionEvent -> {
            CategoriesFrame categoriesFrame = new CategoriesFrame();
            try {
                root.setCenter(categoriesFrame.showCategories());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        showCustomersByMaxSum.setOnAction(actionEvent -> {
            ShowTableOfCustomersMaxSumAndDate showTableOfCustomersMaxSumAndDate = new ShowTableOfCustomersMaxSumAndDate();
            try {
                root.setCenter(showTableOfCustomersMaxSumAndDate.showTable());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        showListOfChangedPrice.setOnAction(actionEvent -> {
            ShowTableOfChangedPrice showTableOfChangedPrice = new ShowTableOfChangedPrice();
            try {
                root.setCenter(showTableOfChangedPrice.show());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        showMenu.getItems().addAll(showCategories, showCustomersByMaxSum, showListOfChangedPrice);
        return showMenu;
    }

}
