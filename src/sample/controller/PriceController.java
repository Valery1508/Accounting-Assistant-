package sample.controller;

import javafx.scene.control.TableView;
import sample.Repository.ProductRepository;
import sample.model.Price;
import sample.model.Product;
import sample.view.table.PriceOfProductTable;

import java.sql.SQLException;
import java.util.List;

public class PriceController {

    private Price price;
    private PriceOfProductTable priceOfProductTable;
    private List<Price> priceList;

    public void addDateAndPrice(Integer code, String date, Double priceP) throws SQLException {
        price = new Price(code, date, priceP);
        ProductRepository productRepository = new ProductRepository();
        productRepository.addDatePrice(price);
    }

    public TableView<Price> showChangedPriceForProductByProductCodeAndByDate(int codeP, String dateFrom, String dateTo) throws SQLException {
        priceOfProductTable = new PriceOfProductTable();
        return priceOfProductTable.createTable(getPriceAndDateListByCode(codeP, dateFrom, dateTo));
    }

    public List<Price> getPriceAndDateListByCode(int codeP, String dateF, String dateT) throws SQLException {
        ProductRepository productRepository = new ProductRepository();
        priceList = productRepository.getPriceAndDateByCode(codeP, dateF, dateT);
        return priceList;
    }

}
