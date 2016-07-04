package data;


import model.Purchase;

import java.util.Date;
import java.util.List;

public interface Ops{

    public List<Purchase> fetchPurchase();

    List<Purchase> filterPurchase(Date from, Date to);

    public void handlePurchase(List<Purchase> pl);


}