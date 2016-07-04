package model;

public class Details {

    private Long id;
    private String description;
    private Integer quantity;
    private Double value;



    public Details(Long id,
                   String description,
                   Integer quantity,
                   Double value){

        this.id =id;
        this.description =description;
        this.quantity =quantity;
        this.value =value;
    }

    public Details(){

        this.id =null;
        this.description =null;
        this.quantity =null;
        this.value =null;
    }


    public Long getId(){
        return id;
    }
    public String getDescription(){
        return description;
    }
    public Integer getQuantity(){
        return quantity;
    }
    public Double getValue(){
        return value;
    }




    public void setId(Long id){
        this.id = id;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setQuantity(Integer quantity){
        this.quantity = quantity;
    }
    public void setValue(Double value){
        this.value = value;
    }

}
