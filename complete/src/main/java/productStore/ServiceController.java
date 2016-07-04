package productStore;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.OpsImpl;
import model.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ServiceController {

    public OpsImpl ops = new OpsImpl();

    private final CounterService counterServicePurchase,counterServiceFetch;


    @Autowired
    public ServiceController(CounterService csp,CounterService csf){
        this.counterServicePurchase = csp;
        this.counterServiceFetch = csf;

    }


    @RequestMapping(value = "/purchase", method = RequestMethod.POST, produces="application/json")
    public  @ResponseBody List<Purchase> purchase(@RequestBody List<Purchase> purchases ) {

        ops.handlePurchase(purchases);
        counterServicePurchase.increment("counter.calls.purchase");
        return purchases;
    }

    @RequestMapping(value="fetch", method = RequestMethod.GET)
    public @ResponseBody
    List<Purchase> fetch() {

        List<Purchase> purchase = ops.fetchPurchase();

        //List<Purchase> purchase = OpsImpl.getPurchaseExamle();
        counterServiceFetch.increment("counter.calls.fetch");


        return purchase;
    }


}
