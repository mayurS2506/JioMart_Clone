package DAO;

import DTO.ProductsDto;

public interface ProductDAO extends Operation {
    public  int addProduct(ProductsDto p);
    public  int updateProduct(ProductsDto p);
    public  int deleteProduct(ProductsDto p);
}
