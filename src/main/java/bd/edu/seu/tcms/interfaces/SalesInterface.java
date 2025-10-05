package bd.edu.seu.tcms.interfaces;

import bd.edu.seu.tcms.model.CartItem;
import javafx.collections.ObservableList;

public interface SalesInterface {
    boolean processSale(ObservableList<CartItem> cartItems, String userName, String paymentMethod, String couponCode);}
