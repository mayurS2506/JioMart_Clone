package DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductsDto    {
    private int pid;
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getpBrand() {
        return pbrand;
    }

    public void setpBrand(String brand) {
        this.pbrand = brand;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }


    public double getpPrice() {
        return pPrice;
    }

    public void setpPrice(double pPrice) {
        this.pPrice = pPrice;
    }

    public double getpDiscountPrice() {
        return pDiscountPrice;
    }

    public void setpDiscountPrice(double pDiscountPrice) {
        this.pDiscountPrice = pDiscountPrice;
    }

    public String getpCategory() {
        return pCategory;
    }

    public void setpCategory(String pCategory) {
        this.pCategory = pCategory;
    }

    public String getpSubCategories() {
        return pSubCategories;
    }

    public void setpSubCategories(String pSubCategories) {
        this.pSubCategories = pSubCategories;
    }

    public String getPqty() {
        return pqty;
    }

    public void setPqty(String pqty) {
        this.pqty = pqty;
    }

    private  String pbrand;
    private  String pName;
    private  double pPrice;
    private  double pDiscountPrice;
    private  String pCategory;
    private  String pSubCategories;
    private  String pqty;

}
