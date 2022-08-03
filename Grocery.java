import java.io.Console;
import java.util.*;
public class Grocery{
    public static void main(String[] args){
        char choice='n';//To open or close the shop
        String mailid;//Mail ID for login
        Console cnsl= System.console();
        Scanner sc=new Scanner(System.in);
        //Available products 
        Map<Integer,Product> available_products=new HashMap<>();
        //Products in the cart
        ArrayList<CartProduct> cartlist1=new ArrayList<>();
        ArrayList<CartProduct> cartlist2=new ArrayList<>();
        ArrayList<CartProduct> cartlist3=new ArrayList<>();
        //User bought products
        ArrayList<BoughtProduct> boughtProducts=new ArrayList<>();
        //User
        ArrayList<Member> userlist=new ArrayList<>();
        //Admin
        ArrayList<Member> adminlist=new ArrayList<>();
        //User details
        Member u1=new Member("Vijay",20,"Salem",100,1000,'U',"vijay100@gmail.com","vijay@100",cartlist1);
        Member u2=new Member("Ajith",28,"Bangalore",101,100000,'U',"ajith101@gmail.com","ajith@101",cartlist2);
        Member u3=new Member("Gandhi",40,"Dharmapuri",102,30000,'U',"gandhi102@gmail.com","gandhi@102",cartlist3);
        //Admin details
        Member a1=new Member("Gayathiri",15,"Coimbatore",201,'A',"gayathiri201@gmail.com","gayathiri@201");
        Member a2=new Member("Prathish",30,"Chennai",202,'A',"prathish202@gmail.com","prathish@202");
        //Product details
        Product p1=new Product(1,"Pencil", 10,5);
        Product p2=new Product(2,"Shampoo",20,3);
        userlist.add(u1);
        userlist.add(u2);
        userlist.add(u3);
        adminlist.add(a1);
        adminlist.add(a2);
        available_products.put(p1.getid(),p1);
        available_products.put(p2.getid(),p2);
        while(choice!='y'){
            //LOGIN page for our shop
        System.out.println("-----------------------LOGIN PAGE----------------");
        System.out.println("Who are you?\n1.Admin\n2.User");
        int admin_user,not_user=0,not_admin=0;
        admin_user=sc.nextInt();
        
        //Check whether he is admin or user
        if(admin_user!=1 && admin_user!=2){
            System.out.println("No option available");
        }
        if(admin_user==2){
            mailid=sc.nextLine();
            while(mailid==""){
                System.out.println("Enter your mailid:");
                mailid=sc.nextLine();
            }
            for(Member user: userlist){
                if(user.getMail().equals(mailid)){
                    not_user=1;
                    int count_1=2;
                    while(count_1!=0){
                        char[] ch1 = cnsl.readPassword("Enter password : ");
                        String ch=new String(ch1);
                        //Checks for the correct password
                        if(user.getPassword().equals(ch)){
                            switch(user.id){
                                case 100:
                                user.func(user,available_products,boughtProducts,cartlist1);
                                break;
                                case 101:
                                user.func(user,available_products,boughtProducts,cartlist2);
                                break;
                                case 102:
                                user.func(user,available_products,boughtProducts,cartlist3);
                                break;
                                default:
                                break;
                            }
                            break;
                        }
                        else{
                            count_1-=1;
                            System.out.println("Incorrect password.Retry!!\n");   
                        }  
                    }
                    if(count_1==0){
                        System.out.println("Incorrect Password");
                    } 
                }    
            }
            if(not_user==0){
                //Mail id is not registered 
                System.out.println("Your email id is not registered");
            }
        }
        else if(admin_user==1){
            //System.out.println("Enter your mailid:");
            mailid=sc.nextLine();
            while(mailid==""){
                System.out.println("Enter your mailid:");
                mailid=sc.nextLine();
            }
            for(Member admin: adminlist){
                if(admin.getMail().equals(mailid)){
                    not_admin=1;
                    int count_2=2;
                    while(count_2!=0){
                    char[] ch1 = cnsl.readPassword("Enter password : ");
                    String ch=new String(ch1);
                    if(admin.getPassword().equals(ch)){
                        admin.func(admin,available_products,boughtProducts);
                        break;
                    }
                    else{
                        count_2-=1;
                        System.out.println("Incorrect password.Retry!!\n");
                    }
                    }
                    if(count_2==0){
                        System.out.println("Incorrect Password");
                    } 
                }      
             }
             if(not_admin==0){
                System.out.println("Your email id is not registered");
            }
        }    
        System.out.println("\nDo you want to close the shop (y/n)?");
        choice=sc.next().charAt(0);    
    }
    }
}