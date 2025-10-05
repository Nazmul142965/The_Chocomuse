package bd.edu.seu.tcms.interfaces;

import bd.edu.seu.tcms.model.Sale;
import bd.edu.seu.tcms.model.SaleItem;
import javafx.collections.ObservableList;

public interface UserInventoryInterface {

    ObservableList<Sale> getSalesForUser(String userName);

    ObservableList<SaleItem> getSaleItemsForSale(int saleId);

}