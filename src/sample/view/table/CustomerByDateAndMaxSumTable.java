package sample.view.table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.model.Category;
import sample.model.Customer;

import java.util.List;


public class CustomerByDateAndMaxSumTable {

    private TableView<Customer> table;
    private TableColumn<Customer, String> fNameCol, sNameCol, regionCol, streetCol;
    private TableColumn<Customer, Integer> houseCol;
    private ObservableList<Customer> addList;

    public CustomerByDateAndMaxSumTable (){
        this.table = new TableView<>();
    }

    public TableView<Customer> createTable(List<Customer> list) {

        table.setEditable(true);

        fNameCol = new TableColumn<>("FirstName");
        fNameCol.setEditable(true);
        fNameCol.setMinWidth(70);
        fNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        sNameCol = new TableColumn<>("SecondName");
        sNameCol.setEditable(true);
        sNameCol.setMinWidth(70);
        sNameCol.setCellValueFactory(new PropertyValueFactory<>("secondName"));

        regionCol = new TableColumn<>("Region");
        regionCol.setEditable(true);
        regionCol.setMinWidth(50);
        regionCol.setCellValueFactory(new PropertyValueFactory<>("region"));

        streetCol = new TableColumn<>("Street");
        streetCol.setEditable(true);
        streetCol.setMinWidth(50);
        streetCol.setCellValueFactory(new PropertyValueFactory<>("street"));

        houseCol = new TableColumn<>("House");
        houseCol.setEditable(true);
        houseCol.setMinWidth(40);
        houseCol.setCellValueFactory(new PropertyValueFactory<>("house"));


        table.getColumns().addAll(fNameCol, sNameCol, regionCol, streetCol, houseCol);

        addList= FXCollections.observableArrayList(list);
        table.setItems(addList);
        return table;
    }

}
