package model;

import java.util.Date;

public class Purchase {

    private Long id;
    private String productType;
    private Date expires;
    private Details purchaseDetails;

    public Purchase(Long id,
                    String productType,
                    Date expires,
                    Details purchaseDetails){
        this.id = id;
        this.productType = productType;
        this.expires = expires;
        this.purchaseDetails = purchaseDetails;
    }

    public Purchase(Long id,
                    String productType,
                    Date expires,
                    Details purchaseDetails,Long detailsId,
                    String description,
                    Integer quantity,
                    Double value){
        this.id = id;
        this.productType = productType;
        this.expires = expires;
        this.purchaseDetails = new Details(detailsId,description,quantity,value);
    }

    public Purchase(){
        this.id = null;
        this.productType = null;
        this.expires = null;
        this.purchaseDetails = null;
    }


    public Long getId(){
        return id;
    }
    public String getProductType(){
        return productType;
    }
    public Date getExpires(){
        return expires;
    }
    public Details getPurchaseDetails(){
        return purchaseDetails;
    }


    public void setId(Long id){
        this.id = id;
    }
    public void setProductType(String productType){
        this.productType = productType;
    }
    public void setExpires(Date expires){
        this.expires = expires;
    }
    public void setPurchaseDetails(Details purchaseDetails){
        this.purchaseDetails = purchaseDetails;
    }


    }
