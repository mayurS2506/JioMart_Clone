package MainApp;

import DAO.CartDAOImplementation;
import DAO.LoginDAOImplementation;
import DAO.Operation;
import DAO.OrderImplementation;
import DTO.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserMainApp {
    static Scanner sc= new Scanner(System.in);
    private  static  final Operation basicOperation = new CartDAOImplementation();
    private  static  final  CartDAOImplementation cartimplemntation = (CartDAOImplementation) basicOperation;
    private  static  final LoginDAOImplementation logDao = new LoginDAOImplementation();
    private  static  final OrderImplementation orderDao = new OrderImplementation();
    void operation(){
        boolean status = true;
        while (status) {
            System.out.println();
            System.out.println("-----------------------Welcome------------------------");
            System.out.println("1 : Add product to the Cart ");
            System.out.println("2 : Remove product from Cart ");
            System.out.println("3 : Display product from Cart ");
            System.out.println("4 : Display All product ");
            System.out.println("5 : Place order ");
            System.out.println("6 : Check order ");
            System.out.println("7 : Exit ");
            System.out.print("Enter your choice : ");
            int choice = sc.nextInt();
            switch (choice){
                case  1 :
                    insertCart();
                    break;
                case  2 :
                    removeCart();
                    break;
                case  3 :
                    displayCart();
                    break;
                case  4 :
                    displayProduct();
                    break;
                case  5 :
                    checkLogin();
                    break;
                case  6 :
                    displayOrder();
                    break;
                case 7 :
                    status=false;
                    break;
            }
        }
    }

    private void displayOrder() {
        CustomerDto cust_dto = loginMethod();
        List<OrderPayload> orderInfo ;
        orderInfo  = orderDao.displayOrder(cust_dto);
        if (orderInfo!=null){
            System.out.println("Order_ID \tName\t\tAddress\t\tAmount\t\tPayment Mode ");
        for (OrderPayload  order: orderInfo) {
            System.out.println("\t"+order.getOrder_id()+"\t\t"+order.getCust_name() +"\t\t"+order.getCust_address()+"\t\t"+order.getAmount()+"\t\t\t"+order.getPayment_mode());
        }}else {
            System.out.println("Something went Wrong ");
        }

    }

    private void checkLogin() {
                System.out.println("------------------- Place your order here -------------------- ");
                System.out.println("1: Login Your Account ");
                System.out.println("2: Create Account  ");
                System.out.print("Enter your choice : ");
                int choice = sc.nextInt();
              boolean orderStatus = false;
                if(choice==1){
                    CustomerDto cust_dto = loginMethod();
                    if(cust_dto !=null){
                        OrderDto order1=orderDao.addProductToOrderProduct(cust_dto);
                        orderStatus =   placeOrder(cust_dto);
                        if (orderStatus){
                            System.out.println("Order Place sucessfully ");
                        }else{
                            System.out.println("Something went wrong");
                        }

                    }else{
                        System.out.println("Not valid  user");
                    }
                } else if (choice==2) {
                  boolean account =  createAccount();
                if (account){
                    System.out.println("Account Created Sucessfully ");
                    CustomerDto c2 =  loginMethod();
                    if(c2 !=null){
                     orderStatus = placeOrder(c2);
                    if (orderStatus){
                        System.out.println("Order Place sucessfully ");
                    }else{
                        System.out.println("Something went wrong");
                    }

                    }else{
                        System.out.println("Not valid  user");
                    }
                }else{
                    System.out.println("Invalid input");
                }
            }else{
                System.out.println("Invalid input");
            }


    }

    private boolean createAccount() {
        try {
            System.out.println("------------------Create Account-----------------------");
        System.out.print("Enter your name : ");
            sc.nextLine();
        String name = sc.nextLine();
        System.out.print("Enter contact number : ");
        String contactNo = sc.next();
            sc.nextLine();
        System.out.print("Enter your address : ");
        String address = sc.nextLine();
        System.out.print("Enter email : ");
        String email = sc.next();
        System.out.print("Enter password : ");
        String password = sc.next();
         boolean emailCheck=  checkEmailAndMobileNo(contactNo,email ,password);
            if (!emailCheck){
                return  false;
            }
        CustomerDto c1 =new CustomerDto();
        c1.setCust_name(name);
        c1.setCust_contact_no(contactNo);
        Long.parseLong(c1.getCust_contact_no());

        c1.setCust_address(address);
        c1.setCust_email(email);
        c1.setCust_pass(password);
        return    logDao.createAccount(c1);
        }catch (NumberFormatException e){
            System.out.println("Invalid Input ");
        }
        return false;
    }

    private boolean checkEmailAndMobileNo(String contactNo, String email ,String password) {
        email.contains("@");
        boolean b = contactNo.length() == 10;
        boolean isLowerCase = true;
        for (int i = 0; i < email.length(); i++) {
            char ch = email.charAt(i);
            if (!Character.isLowerCase(ch) & !(ch=='@' || ch=='.') ) {
                isLowerCase = false;
                break;
            }
        }
        if ((b && isLowerCase) && (password.length()>=8 && password.length()<=12)){

            return true;
        }else {
            return  false;
        }
    }


    private boolean placeOrder(CustomerDto c) {
        OrderDto order1=orderDao.addProductToOrderProduct(c);
        order1 =  orderDao.getTotal(order1);
        System.out.println("Your order Amount : "+Math.round(order1.getAmount()));
        System.out.println("Enter Order Amount ");
        double total = sc.nextDouble();
        if (total==Math.round(order1.getAmount())){
            System.out.println("Chose payment mode \n1 : UPI \n2 : C.O.D.");
            int paymentMode = sc.nextInt();
            if (paymentMode==1){
                order1.setPayment_mode("UPI");
            }else if (paymentMode==2){
                order1.setPayment_mode("C.O.D.");
            }else{
                System.out.println("Invalid Input");
                return  false;
            }
           int check =   orderDao.PlaceOrder(order1);
            if (check>0){
                return true;
            }else {
                return  false ;
            }
        }else {
            System.out.println(" Invalid Amount ");
            return false;
        }
    }

    private CustomerDto loginMethod() {
        System.out.println("------------------------Login------------------------");
        System.out.print("Enter mobile/email : ");
        String username = sc.next();
        System.out.print("Enter Password : ");
        String password = sc.next();
        LoginDto dto = new LoginDto();
        dto.setPassword(password);
        dto.setUsername(username);

       return logDao.login(dto);
    }


    private static void displayProduct() {
        List<ProductsDto> productList = cartimplemntation.displayProduct();
        System.out.println("ID\tProduct Name\t\t\t Brand\t\t\t Price\t\t\t Discounted Price\t\tProduct Quantity");
        System.out.println();
        for (ProductsDto p: productList ) {
            System.out.println(p.getPid()+"\t"+p.getpName()+"\t\t\t"+p.getpBrand()+"\t\t\t"+p.getpPrice()+"\t\t\t"+p.getpDiscountPrice()+"\t\t\t\t"+p.getPqty());
        }
    }

    private static void displayCart() {
     List<PayloadClass> cartData =  cartimplemntation.displayCartProduct();
     System.out.println("Cart ID\tProduct ID  \tProduct Name\t\t Brand\t\t\t Price\t\t\t Discounted Price\t\tProduct Quantity \t\t Cart Quantity \t\t Total Price  ");
        for (PayloadClass data : cartData) {
            System.out.println(data.getCartId()+"\t\t\t\t"+data.getProductId()+"\t"+data.getProductName()
            +"\t\t"+data.getBrand()+"\t\t\t"+data.getPrice()+"\t\t\t\t"+data.getDiscountedPrice()+"\t\t\t\t"+data.getProductQty()
            +"\t\t\t\t\t"+data.getCartQty()+"\t\t\t\t"+(data.getCartQty()*data.getDiscountedPrice()));
        }
    }

    private static void removeCart() {
        try {
            CartDto c = new CartDto();
            System.out.print("Enter Product ID : ");
            int pId = sc.nextInt();
            c.setProduct_id(pId);
            int count = cartimplemntation.removeProductFromCart(c);
            if (count > 0) {
                System.out.println("Product Remove Successfully");
            } else {
                System.out.println("Something Went Wrong");
            }
        }catch (InputMismatchException e){
            sc.nextLine();
            System.out.println("-------  Invalid Input ! ---------- ");
        }
    }

    private static void insertCart() {
        try{
        CartDto c = new CartDto();
        System.out.print("Enter Product ID : ");
        int pId = sc.nextInt();
        System.out.print("Enter Product Quantity : ");
        int pqty = sc.nextInt();
        c.setProduct_id(pId);
        c.setProduct_qty(pqty);
       int count=  cartimplemntation.addProductToCart(c);
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
}
