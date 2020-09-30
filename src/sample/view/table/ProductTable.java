package sample.view.table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.model.Product;

import java.util.List;

public class ProductTable {

    private TableView<Product> table = new TableView();

    public ProductTable (){
        this.table = new TableView<Product>();
    }

    public TableView createTable(List<Product> list) {
        TableColumn<Product, Integer> codeCol = new TableColumn<>("Code");
        codeCol.setCellValueFactory(new PropertyValueFactory<>("codeP"));
        TableColumn<Product, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("nameP"));
        TableColumn<Product, String> categoryCol = new TableColumn<>("Category");
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        table.getColumns().addAll(codeCol, nameCol, categoryCol);

        ObservableList<Product> addList= FXCollections.observableArrayList(list);
        table.setItems(addList);
        return table;
    }

}
