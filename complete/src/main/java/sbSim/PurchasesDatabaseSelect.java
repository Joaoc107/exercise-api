package sbSim;

import model.Purchase;

import java.util.List;


public interface PurchasesDatabaseSelect {


    public List<Purchase> fetchPurchase();
    public List<Purchase> fetchPurchaseWithDetails(List<Long> ids);
    public void storePurchase(List<Purchase> pl);

}
