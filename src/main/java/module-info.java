module bd.edu.seu.tcms {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens bd.edu.seu.tcms to javafx.fxml;
    exports bd.edu.seu.tcms;
    exports bd.edu.seu.tcms.controller;
    opens bd.edu.seu.tcms.controller to javafx.fxml;
    exports bd.edu.seu.tcms.utility;
    opens bd.edu.seu.tcms.utility to javafx.fxml;
    exports bd.edu.seu.tcms.admin;
    opens bd.edu.seu.tcms.admin to javafx.fxml;
    exports bd.edu.seu.tcms.user;
    opens bd.edu.seu.tcms.user to javafx.fxml;

    opens bd.edu.seu.tcms.model to javafx.base;
}