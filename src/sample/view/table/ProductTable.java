package sample.view.table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.model.Product;

import java.util.List;

public class ProductTable {

    private TableView<Product> table;
    private TableColumn<Product, Integer> codeCol;
    private TableColumn<Product, String> nameCol, categoryCol;
    private ObservableList<Product> addList;

    public ProductTable (){
        this.table = new TableView<>();
    }

    public TableView<Product> createTable(List<Product> list) {

        table.setEditable(true);

        codeCol = new TableColumn<>("Code");
        codeCol.setEditable(true);
        codeCol.setMinWidth(150);
        codeCol.setCellValueFactory(new PropertyValueFactory<>("codeP"));

        nameCol = new TableColumn<>("Name");
        nameCol.setEditable(true);
        nameCol.setMinWidth(290);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("nameP"));


        categoryCol = new TableColumn<>("Category");
        categoryCol.setEditable(true);
        categoryCol.setMinWidth(250);
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));


        table.getColumns().addAll(codeCol, nameCol, categoryCol);

        addList= FXCollections.observableArrayList(list);
        table.setItems(addList);
        return table;
    }

    public Product getSelectedProduct(){
        TableView.TableViewSelectionModel<Product> selectionModel = table.getSelectionModel();
        return selectionModel.getSelectedItem();
    }

    public int getSelectedCode(){
        TableView.TableViewSelectionModel<Product> selectionModel = table.getSelectionModel();
        return selectionModel.getSelectedItem().getCodeP();
    }

    public String getSelectedName(){
        TableView.TableViewSelectionModel<Product> selectionModel = table.getSelectionModel();
        return selectionModel.getSelectedItem().getNameP();
    }

    public String getSelectedCategory(){
        TableView.TableViewSelectionModel<Product> selectionModel = table.getSelectionModel();
        return selectionModel.getSelectedItem().getCategory();
    }

    public void addToObservableList(Product product){
        addList.add(product);
    }

    public void removeFromObservableList(Product product){
        addList.remove(product);
    }
}
