package sbSim;

import model.Purchase;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class PurchasesDatabaseSelectImpl implements PurchasesDatabaseSelect{




    @Override
    public List<Purchase> fetchPurchase() {
        throw new NotImplementedException();
    }

    @Override
    public List<Purchase> fetchPurchaseWithDetails(List<Long> ids) {
        throw new NotImplementedException();
    }

    public void storePurchase(List<Purchase> pl){throw new NotImplementedException();}
}
