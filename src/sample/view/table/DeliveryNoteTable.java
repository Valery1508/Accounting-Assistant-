package sample.view.table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.model.DeliveryNote;
import sample.model.Product;


import java.util.List;

public class DeliveryNoteTable {

    private TableView<DeliveryNote> table;
    private TableColumn<DeliveryNote, Integer> idCol, idCustomerCol, codePCol, quantityCol;
    private TableColumn<DeliveryNote, String> dateCol;
    private TableColumn<DeliveryNote, Double> priceCol, totalPriceCol;
    private ObservableList<DeliveryNote> addList;

    public DeliveryNoteTable (){
        this.table = new TableView<>();
    }

    public TableView<DeliveryNote> createTable(List<DeliveryNote> list) {

        table.setEditable(true);

        idCol = new TableColumn<>("Id");
        idCol.setEditable(true);
        idCol.setMinWidth(40);
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        idCustomerCol = new TableColumn<>("Customer Id");
        idCustomerCol.setEditable(true);
        idCustomerCol.setMinWidth(50);
        idCustomerCol.setCellValueFactory(new PropertyValueFactory<>("idCustomer"));


        codePCol = new TableColumn<>("Product code");
        codePCol.setEditable(true);
        codePCol.setMinWidth(70);
        codePCol.setCellValueFactory(new PropertyValueFactory<>("codeP"));

        dateCol = new TableColumn<>("Date");
        dateCol.setEditable(true);
        dateCol.setMinWidth(60);
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

        priceCol = new TableColumn<>("Price");
        priceCol.setEditable(true);
        priceCol.setMinWidth(90);
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        quantityCol = new TableColumn<>("Quantity");
        quantityCol.setEditable(true);
        quantityCol.setMinWidth(80);
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        totalPriceCol = new TableColumn<>("Total price");
        totalPriceCol.setEditable(true);
        totalPriceCol.setMinWidth(90);
        totalPriceCol.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));


        table.getColumns().addAll(idCol, idCustomerCol, codePCol, dateCol, priceCol, quantityCol, totalPriceCol);

        addList= FXCollections.observableArrayList(list);
        table.setItems(addList);
        return table;
    }

    public int getSelectedId(){
        TableView.TableViewSelectionModel<DeliveryNote> selectionModel = table.getSelectionModel();
        return selectionModel.getSelectedItem().getId();
    }

    public int getSelectedIdCustomer(){
        TableView.TableViewSelectionModel<DeliveryNote> selectionModel = table.getSelectionModel();
        return selectionModel.getSelectedItem().getIdCustomer();
    }

    public int getSelectedCodeP(){
        TableView.TableViewSelectionModel<DeliveryNote> selectionModel = table.getSelectionModel();
        return selectionModel.getSelectedItem().getCodeP();
    }

    public String getSelectedDate(){
        TableView.TableViewSelectionModel<DeliveryNote> selectionModel = table.getSelectionModel();
        return selectionModel.getSelectedItem().getDate();
    }

    public double getSelectedPrice(){
        TableView.TableViewSelectionModel<DeliveryNote> selectionModel = table.getSelectionModel();
        return selectionModel.getSelectedItem().getPrice();
    }

    public int getSelectedQuantity(){
        TableView.TableViewSelectionModel<DeliveryNote> selectionModel = table.getSelectionModel();
        return selectionModel.getSelectedItem().getQuantity();
    }
}
