package sample.view.table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.model.Category;
import sample.model.Product;

import java.util.Collection;
import java.util.List;

public class CategoryTable {

    private TableView<Category> table;
    private TableColumn<Category, String> categoryColumn;
    private ObservableList<Category> addList;

    public CategoryTable (){
        this.table = new TableView<>();
    }

    public TableView<Category> createTable(List<Category> list) {

        table.setEditable(true);

        categoryColumn = new TableColumn<>("Category");
        categoryColumn.setEditable(true);
        categoryColumn.setMinWidth(500);
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));


        table.getColumns().addAll(categoryColumn);

        addList= FXCollections.observableArrayList(list);
        table.setItems(addList);
        return table;
    }

}
