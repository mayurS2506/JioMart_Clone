package DTO;

public class OrderDto {
    private  int order_id;
    private int  customer_id;
    private  double amount ;
    public int getOrder_id() {
        return order_id;
    }
    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public String getPayment_mode() {
        return payment_mode;
    }
    public void setPayment_mode(String payment_mode) {
        this.payment_mode = payment_mode;
    }
    private  String payment_mode ;

}
