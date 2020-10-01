package sample.view.table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import sample.model.Product;

import java.util.List;

public class ProductTable {

    private TableView<Product> table;
    private ObservableList<Product> addList;

    public ProductTable (){
        this.table = new TableView<>();
    }

    public TableView<Product> createTable(List<Product> list) {     // public TableView<Product> createTable(List<Product> list)

        table.setEditable(true);

        TableColumn<Product, Integer> codeCol = new TableColumn<>("Code");
        codeCol.setEditable(true);
        codeCol.setMinWidth(150);
        codeCol.setCellValueFactory(new PropertyValueFactory<>("codeP"));
        /*codeCol.setCellFactory(TextFieldTableCell.<Product>forTableColumn());
        codeCol.setOnEditCommit(
                (CellEditEvent<Product, String> t) -> {
                    ( t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setCodeP(Integer.parseInt(t.getNewValue()));
        });*/

        TableColumn<Product, String> nameCol = new TableColumn<>("Name");
        nameCol.setEditable(true);
        nameCol.setMinWidth(290);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("nameP"));


        TableColumn<Product, String> categoryCol = new TableColumn<>("Category");
        categoryCol.setEditable(true);
        categoryCol.setMinWidth(250);
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));


        table.getColumns().addAll(codeCol, nameCol, categoryCol);

        addList= FXCollections.observableArrayList(list);
        table.setItems(addList);
        return table;
    }

    public void addToObservableList(Product product){
        addList.add(product);
    }

}
