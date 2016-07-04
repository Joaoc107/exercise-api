package data;


import com.google.common.base.Predicate;
import sbSim.PurchasesDatabaseSelectImpl;
import model.Purchase;
import model.Details;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

import static com.google.common.collect.FluentIterable.from;


public class OpsImpl implements Ops {

    private final ReentrantLock lock = new ReentrantLock();

    private static final Predicate<Purchase> datePredicate = new Predicate<Purchase>() {
        public boolean apply(Purchase input) {
            return input.getExpires().compareTo(new Date())>0;
        }
    };


    @Override
    public List<Purchase> fetchPurchase() {
        PurchasesDatabaseSelectImpl pds = new PurchasesDatabaseSelectImpl();

        //TODO  - timeout on database access

        //Get dummy purchases to test
        //List<Purchase> lp = from(getPurchaseExamle).filter(datePredicate).toList();

        List<Purchase> lp = from(pds.fetchPurchase()).filter(datePredicate).toList();


        List<Purchase> fetchedResults  = lp;

        return fetchedResults;
    }

    @Override
    public List<Purchase> filterPurchase(Date from, Date to) {


        return null;
    }

    @Override
    public void handlePurchase(List<Purchase> pl) {
        PurchasesDatabaseSelectImpl pds = new PurchasesDatabaseSelectImpl();
        lock.lock();
        try {

            ExecutorService executor = Executors.newSingleThreadExecutor();
            Future<String> future = executor.submit(new TaskStore(pl));

                //Timeout 2 seconds
                System.out.println("Started..");
                System.out.println(future.get(2, TimeUnit.SECONDS));
            System.out.println("Finished..");

            future.cancel(true);

            pds.storePurchase(pl);
        } catch (TimeoutException e) {
            System.out.println("TimeOut..");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static final List<Purchase> getPurchaseExamle(){
        List<Purchase> list = new ArrayList<>(20);

        Details d = new Details(new Long(1),"Rice",Integer.valueOf(2),Double.valueOf(6));

        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");


        list.add(new Purchase (new Long(1), "food", new Date(), d));
        try {
            list.add(new Purchase (new Long(2), "food", ft.parse("2017-01-13"), d));
            list.add(new Purchase (new Long(3), "food", ft.parse("2016-01-13"), d));
            list.add(new Purchase (new Long(5), "food", ft.parse("2017-02-18"), d));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }


    class TaskStore implements Callable<String> {

        private List<Purchase> list;

        public TaskStore(List<Purchase> list) {
            this.list = list;
        }

        @Override
        public String call() throws Exception {
            PurchasesDatabaseSelectImpl pds = new PurchasesDatabaseSelectImpl();
            pds.storePurchase(list);

            //Thread.sleep(4000);
            return "Ok";
        }
    }
}
