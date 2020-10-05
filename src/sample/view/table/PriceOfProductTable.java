package sample.view.table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.model.Price;

import java.util.List;

public class PriceOfProductTable {

    private TableView<Price> priceTable;
    private TableColumn<Price, Integer> codeCol;
    private TableColumn<Price, String> dateCol;
    private TableColumn<Price, Double> priceCol;
    private ObservableList<Price> addList;

    public PriceOfProductTable (){
        this.priceTable = new TableView<>();
    }

    public TableView<Price> createTable(List<Price> list) {

        priceTable.setEditable(true);

        codeCol = new TableColumn<>("Code");
        codeCol.setEditable(true);
        codeCol.setMinWidth(150);
        codeCol.setCellValueFactory(new PropertyValueFactory<>("codeP"));

        dateCol = new TableColumn<>("Date");
        dateCol.setEditable(true);
        dateCol.setMinWidth(150);
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

        priceCol = new TableColumn<>("Price");
        priceCol.setEditable(true);
        priceCol.setMinWidth(150);
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));


        priceTable.getColumns().addAll(codeCol, dateCol, priceCol);

        addList= FXCollections.observableArrayList(list);
        priceTable.setItems(addList);
        return priceTable;
    }

}
