package bd.edu.seu.tcms.interfaces;

import bd.edu.seu.tcms.model.Chocolate;

import java.util.List;

public interface ChocolateInterface {
    void insertChocolateDetails(Chocolate chocolate);
    void updateChocolateDetails(Chocolate oldChocolate,Chocolate updateChocolate);
    void deleteChocolateDetails(Chocolate chocolate);

    List<Chocolate> getChocolatesObservableList();

}
