package DTO;

public class OrderProductDto {
    private int   orderProductId ;
    private  int  orderCustomerId;

    public int getOrderProductId() {
        return orderProductId;
    }

    public void setOrderProductId(int orderProductId) {
        this.orderProductId = orderProductId;
    }

    public int getOrderCustomerId() {
        return orderCustomerId;
    }

    public void setOrderCustomerId(int orderCustomerId) {
        this.orderCustomerId = orderCustomerId;
    }

    public int getOrderProductQty() {
        return orderProductQty;
    }

    public void setOrderProductQty(int orderProductQty) {
        this.orderProductQty = orderProductQty;
    }

    public int getOrderProductid() {
        return orderProductid;
    }

    public void setOrderProductid(int orderProductid) {
        this.orderProductid = orderProductid;
    }

    private  int orderProductQty;
    private  int orderProductid;
}
