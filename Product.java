import java.util.*;
class Product{
    int id;//Product ID
    String product_name;//Product name
    float price;//Cost price of the product
    int count;//Quantity
    Scanner sc=new Scanner(System.in);
    Product(int id,String product_name,float price,int count){
        this.id=id;
        this.product_name=product_name;
        this.price=price;
        this.count=count;
    }
    int getid(){
        //Get the id for the product
        return this.id;
    }
}
class BoughtProduct extends Product{
    Member user4;
    // Calling the Constructor of Parent(Product) class for Initialization
    BoughtProduct(int id,String product_name,float price,Member user4,int count){
        super(id, product_name,price,count);
        this.user4=user4;
    }
}
class CartProduct extends Product{
    // Calling the Constructor of Parent(Product) class for Initialization
    CartProduct(int id,String product_name,float price,int count){
        super(id, product_name, price, count);
    }
}
