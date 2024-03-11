package MainApp;
import DAO.GenralProperty;
import DAO.ProductDAOImplementation;
import DTO.ProductsDto;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
public class MainAppAdmin {
  private final static Scanner sc = new Scanner(System.in);
    private  static  final GenralProperty  temp= new ProductDAOImplementation();
    private  static  final  ProductDAOImplementation productDao= (ProductDAOImplementation) temp;
    public   void productOperation(){
        boolean status = true;
        while (status){
            System.out.println("-------------------------- ADMIN OPERATION ---------------------------");
            System.out.println("1 : Add Product ");
            System.out.println("2 : Upadate Product ");
            System.out.println("3 : Delete Product ");
            System.out.println("4 : Display Product ");
            System.out.println("5 : Exit");
            System.out.print("Enter Your Choice : ");
            int choice = sc.nextInt();
            switch (choice){
                case  1 :
                    insertProduct();
                    break;
                case  2 :
                    updateProduct();
                    break;
                case  3 :
                    deleteProduct();
                    break;
                case  4 :
                    displayProduct();
                    break;
                case 5 :
                    status=false;
                    break;
            }

        }
    }

    private void insertProduct() { try {
        System.out.print("Enter Product Name : ");
        sc.next();
       String name = sc.nextLine();
        System.out.print("Enter Product Brand Name : ");
        String brand = sc.next();
        System.out.print("Enter Product Price : ");

            double price = sc.nextDouble();
            System.out.print("Enter Product Discounted Price : ");
            double disPrice = sc.nextDouble();

        System.out.print("Enter Product Category Name : ");
        String category = sc.next();
        System.out.print("Enter Product sub Category Name : ");
        sc.next();
        String subCat = sc.nextLine();
        System.out.print("Enter Product Quantity : ");
        sc.next();
        String qty = sc.nextLine();
        ProductsDto p = new ProductsDto();
        p.setpName(name);
        p.setpBrand(brand);
        p.setpPrice(price);
        p.setpDiscountPrice(disPrice);
        p.setpCategory(category);
        p.setpSubCategories(subCat);
        p.setPqty(qty);
      int count =  productDao.addProduct(p);
        if (count>0){
            System.out.println("Product Added Successfully");
        }else {
            System.out.println("Something Went Wrong");
        }
    }catch (InputMismatchException e){
        sc.nextLine();
        System.out.println("-------  Invalid Input ! ---------- ");
    }
    }

    private void updateProduct() {
        try {
            System.out.print("Enter Product ID : ");
            int id = sc.nextInt();
            System.out.print("Enter Product New Discounted Price : ");
            double disPrice = sc.nextDouble();
            ProductsDto p = new ProductsDto();
            p.setPid(id);
            p.setpDiscountPrice(disPrice);
            int count = productDao.updateProduct(p);
            if (count > 0) {
                System.out.println("Product Updated Successfully");
            } else {
                System.out.println("Something Went Wrong");
            }
        }catch (InputMismatchException e){
            sc.nextLine();
            System.out.println("-------  Invalid Input ! ---------- ");
        }
    }

    private  static  void displayProduct(){
        List<ProductsDto> productList = productDao.displayProduct();
        System.out.println("ID\tProduct Name\t\t\t Brand\t\t\t Price\t\t\t Discounted Price\t\t Product Category \t\tProduct SubCategory \t\t\t Product Quantity ");
        System.out.println();
        for (ProductsDto p: productList ) {
            System.out.println(p.getPid()+"\t"+p.getpName()+"\t\t\t"+p.getpBrand()+"\t\t\t"+p.getpPrice()+"\t\t\t"+p.getpDiscountPrice()+"\t\t\t\t\t"+p.getpCategory()+"\t\t\t"+p.getpSubCategories()+"\t\t\t"+p.getPqty());
        }
    }
    private  static  void deleteProduct(){
        try{
        System.out.print("Enter Product ID : ");
        int id = sc.nextInt();
        ProductsDto p = new ProductsDto();
        p.setPid(id);
       int count =  productDao.deleteProduct(p);
       if (count>0){
           System.out.println("Product Deleted Successfully");
       }else {
           System.out.println("Something Went Wrong");
       }
        }catch (InputMismatchException e){
                sc.nextLine();
                System.out.println("-------  Invalid Input ! ---------- ");
        }
    }
}
