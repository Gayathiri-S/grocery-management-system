import java.util.*;

class Member {
    String name, address, mail, password;
    int age, id;
    float amt;
    char role;
    ArrayList<CartProduct> cartlist;
    Scanner sc = new Scanner(System.in);

    Member(String name, int age, String address, int id, float amt, char role, String mail, String password,
            ArrayList<CartProduct> cartlist) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.id = id;
        this.amt = amt;
        this.role = role;
        this.mail = mail;
        this.password = password;
        this.cartlist = cartlist;
    }

    Member(String name, int age, String address, int id, char role, String mail, String password) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.id = id;
        this.role = role;
        this.mail = mail;
        this.password = password;
    }

    String getMail() {
        return this.mail;
    }

    int getId() {
        return this.id;
    }

    String getPassword() {
        return this.password;
    }

    void AddProduct(Map<Integer, Product> available_products) {
        // Add new products to the shop
        int add_products = 'y';
        Scanner sc = new Scanner(System.in);
        while (add_products != 'n') {
            System.out.println("Enter the product Details");
            // Newly added products details
            int pid, pprice, pcount;
            String pname;
            Map.Entry<Integer, Product> m1 = null;
            System.out.println("Enter the product name:");
            pname = sc.nextLine();
            int r = 0;
            Set<Map.Entry<Integer, Product>> entries2 = available_products.entrySet();
            for (Map.Entry<Integer, Product> prod12 : entries2) {
                if (prod12.getValue().product_name.equals(pname)) {
                    r = 1;
                    m1 = prod12;
                }
            }
            if (r == 1) {
                System.out.println("This product is already available");
                System.out.println("How many " + pname + " do you want to add?");
                pcount = sc.nextInt();
                if (pcount < 0) {
                    System.out.println("Not a valid count");
                    System.out.println("Enter a vaild number:(only positive number)");
                    pcount = sc.nextInt();
                }
                Product p5 = m1.getValue();
                p5.count += pcount;// Add the quantity of the product
                available_products.put(p5.getid(), p5);
            } else {
                pid = available_products.size() + 1;
                System.out.println("Enter the price:");
                pprice = sc.nextInt();
                if (pprice < 0) {
                    System.out.println("Enter valid price");
                    System.out.println("Enter the correct amount");
                    pprice = sc.nextInt();
                }
                System.out.println("How many " + pname + " do you want to add?");
                pcount = sc.nextInt();
                if (pcount < 0) {
                    System.out.println("Not a valid count");
                    System.out.println("Enter a vaild number:(only positive number)");
                    pcount = sc.nextInt();
                }
                Product p = new Product(pid, pname, pprice, pcount);
                available_products.put(p.getid(), p);
            }
            System.out.println("Do you want to add more products(y/n)?");
            add_products = sc.next().charAt(0);
        }

    }

    void ViewHistory(ArrayList<BoughtProduct> boughtProducts) {
        // To show the transactions happened in the shop
        int total3 = 0;
        if (boughtProducts.isEmpty()) {
            // To check whether the transactions are happened yet or not
            System.out.println("No transactions yet!!");
        } else {
            for (BoughtProduct p5 : boughtProducts) {
                // Print the products bought and the user who bought it
                total3 += (p5.price * p5.count);
                System.out.println("Product id:" + p5.id + "\tProduct name:" + p5.product_name + "\tPrice:" + p5.price
                        + "\tCount" + p5.count + "\tUser name:" + p5.user4.name + "\tUser id:" + p5.user4.id
                        + "\tUser place:" + p5.user4.address);
            }
        }
        System.out.println("\nTotal amount received:" + total3);
    }

    void ViewProducts(Map<Integer, Product> available_products) {
        // To show the products to the user which are available in the shop
        boolean to_check_product = false;
        Set<Map.Entry<Integer, Product>> entries1 = available_products.entrySet();
        for (Map.Entry<Integer, Product> prod1 : entries1) {
            Product product2 = prod1.getValue();
            if (product2.count > 0) {
                to_check_product = true;
                System.out.println("Product id:" + prod1.getKey() + "\tProduct name:" + product2.product_name
                        + "\tPrice:" + product2.price + "\tAvailable :" + product2.count);
            }
        }
        if (!to_check_product) {
            System.out.println("No product is available");
        }
    }

    void ViewHistory(Member u, ArrayList<BoughtProduct> boughtProducts) {
        // To view the transaction history of particular user
        int to_check = 0, total1 = 0;
        System.out.println("Transactions:");
        for (BoughtProduct p4 : boughtProducts) {
            if (p4.user4.id == u.id) {
                to_check = 1;
                total1 += (p4.price * p4.count);
                System.out.println("\nProduct id:" + p4.id + "\tProduct name:" + p4.product_name + "\tPrice:" + p4.price
                        + "\tCount:" + p4.count);
                // To show the balance amount to the user in the account
            }
        }
        System.out.println("\nTotal amount paid: " + total1);
        System.out.println("Remaining balance in your account: " + (u.amt));
        if (to_check == 0) {
            System.out.println("No transactions yet!!");
        }
    }

    void addToCart(Member u6, Map<Integer, Product> available_products, ArrayList<BoughtProduct> boughtProducts) {
        // To add the items to the cart
        int cart_product_id;
        char cart_choice = 'y';
        int product_count2;
        int k = 0;
        while (cart_choice != 'n') {
            System.out.println("Select the product id which you want to add to the cart: ");
            cart_product_id = sc.nextInt();
            if (available_products.containsKey(cart_product_id)) {
                Boolean set_condition1 = true;
                System.out.println("How many do you want to add to the cart?");
                product_count2 = sc.nextInt();
                if (product_count2 < 0) {
                    System.out.println("Not a valid count");
                    System.out.println("Enter a valid number:(only positive number)");
                    product_count2 = sc.nextInt();
                }
                Set<Map.Entry<Integer, Product>> entries4 = available_products.entrySet();
                loop5: for (Map.Entry<Integer, Product> prod4 : entries4) {
                    if (prod4.getKey() == cart_product_id) {
                        Product product4 = prod4.getValue();
                        for (CartProduct c10 : u6.cartlist) {
                            if (c10.id == cart_product_id) {
                                if (c10.count <= product4.count) {
                                    c10.count += product_count2;
                                    int j = u6.cartlist.indexOf(c10);
                                    u6.cartlist.set(j, c10);
                                    break loop5;
                                } else {
                                    System.out.println("Only " + product4.count + " " + product4.product_name
                                            + " available.\nEnter the valid count.");
                                    product_count2 = sc.nextInt();
                                    c10.count += product_count2;
                                    int j = u6.cartlist.indexOf(c10);
                                    u6.cartlist.set(j, c10);
                                    break loop5;
                                }
                            }
                        }
                        if (product_count2 > product4.count) {
                            System.out.println("Only " + product4.count + " " + product4.product_name
                                    + " available.\nEnter the valid count.");
                            product_count2 = sc.nextInt();
                        }
                        CartProduct c1 = new CartProduct(product4.id, product4.product_name, product4.price,
                                product_count2);
                        u6.cartlist.add(c1);
                    }
                }
            } else {
                System.out.println("Product is unavailable");
            }
            showCart(u6);
            System.out.println("\nDo you want to add some more products to the cart?(y/n)");
            cart_choice = sc.next().charAt(0);
        }
    }

    boolean showCart(Member u9) {
        // To show the items in the cart
        float total = 0;
        boolean to_check_cart = true;// To check whether the cart of the particular user is empty or not
        System.out.println("\nItems in the cart");
        for (CartProduct c2 : u9.cartlist) {
            if (c2.count != 0) {
                to_check_cart = false;
                System.out.println("\nProduct id:" + c2.id + "\tProduct name:" + c2.product_name + "\tPrice:" + c2.price
                        + "\tQuantity:" + c2.count);
                total += c2.price * c2.count;
            }
        }
        if (to_check_cart) {
            System.out.println("\nCart is empty!!");
            return false;
        } else {
            System.out.println("\nTotal price:");
            System.out.println(total);
            return true;
        }
    }

    void removeFromCart(Member u9) {
        char remove_item;
        System.out.println("Do you want to remove any items from the cart?(y/n)");
        remove_item = sc.next().charAt(0);
        while (remove_item == 'y') {
            int remove_id;
            System.out.println("Select the product id which you want to remove?");
            remove_id = sc.nextInt();
            loop1: for (CartProduct c2 : u9.cartlist) {
                if (c2.id == remove_id) {
                    System.out.println("How many " + c2.product_name + " do you want to remove?");
                    int no_remove_products;
                    no_remove_products = sc.nextInt();
                    if (no_remove_products == c2.count) {
                        u9.cartlist.remove(c2);
                        break loop1;
                    } else {
                        int i;
                        i = u9.cartlist.indexOf(c2);
                        c2.count -= no_remove_products;
                        u9.cartlist.set(i, c2);
                        break loop1;
                    }
                }
            }
            System.out.println("Do you want to remove further?(y/n)");
            remove_item = sc.next().charAt(0);
        }
    }

    int BuyCartProduct(ArrayList<BoughtProduct> boughtProducts, Map<Integer, Product> available_products, Member u5) {
        // To buy the products from the shopping cart
        showCart(u5);
        removeFromCart(u5);
        int sum = 0;
        for (CartProduct c8 : u5.cartlist) {
            if (available_products.containsKey(c8.id)) {
                if (c8.count > available_products.get(c8.id).count) {
                    if (available_products.get(c8.id).count == 0) {
                        System.out.println("Product " + c8.product_name + " is unavailable");
                        System.out.println("Remove it from cart");
                        u5.cartlist.remove(c8);
                        continue;
                    } else {
                        System.out.println("Count exceeded for the product " + c8.product_name + ".Only "
                                + available_products.get(c8.id).count + " will available");
                        System.out.println("Enter how many products do you want?");
                        c8.count = sc.nextInt();
                        int i = u5.cartlist.indexOf(c8);
                        u5.cartlist.set(i, c8);
                        continue;
                    }
                } else {
                    continue;
                }
            } else {
                System.out.println("Product is unavailable");
                System.out.println("Remove it from cart");
                u5.cartlist.remove(c8);
                continue;
            }
        }
        for (CartProduct c : u5.cartlist) {
            sum += c.price * c.count;
        }
        if (sum > u5.amt) {
            System.out.println("You dont't have sufficient balance");
            System.out.println("Remove some items from the cart");
            removeFromCart(u5);
            BuyCartProduct(boughtProducts, available_products, u5);
            return 0;
        }
        float total = 0;
        for (CartProduct c9 : u5.cartlist) {
            BoughtProduct p6 = new BoughtProduct(c9.id, c9.product_name, c9.price, u5, c9.count);
            boughtProducts.add(p6);
            total = total + c9.price * c9.count;
        }
        u5.amt = u5.amt - total;
        System.out.println("Bill: " + total);
        System.out.println("Remaining balance :" + (u5.amt));
        Set<Map.Entry<Integer, Product>> entries5 = available_products.entrySet();
        for (Map.Entry<Integer, Product> prod5 : entries5) {
            for (CartProduct c8 : u5.cartlist) {
                if (prod5.getKey() == c8.id) {
                    Product product6 = prod5.getValue();
                    product6.count -= c8.count;
                }
            }
        }
        u5.cartlist.removeAll(u5.cartlist);
        return 0;
    }

    void func(Member u, Map<Integer, Product> available_products, ArrayList<BoughtProduct> boughtProducts,
            ArrayList<CartProduct> cartlist) {
        // Functions of the user
        int uchoice = 'y';
        Scanner sc = new Scanner(System.in);
        while (uchoice != 'n') {
            System.out.println("-------------Welcome to the ABC store--------------\n\n");
            System.out.println("What do you want?\n");
            System.out.println("");
            System.out.println(
                    "1.View the products\n2.Add items to the cart\n3.Show the items in the cart\n4.Buy product\n5.View trasaction History");
            int user_choice = sc.nextInt();
            switch (user_choice) {
                case 1:
                    u.ViewProducts(available_products);
                    break;
                case 2:
                    u.ViewProducts(available_products);
                    u.addToCart(u, available_products, boughtProducts);
                    break;
                case 3:
                    u.showCart(u);
                    break;
                case 4:
                    u.BuyCartProduct(boughtProducts, available_products, u);
                    break;
                case 5:
                    u.ViewHistory(u, boughtProducts);
                    break;
                default:
                    System.out.println("No option available");
                    break;
            }
            System.out.println("\nDo you want to continue(y/n)(for user)?");
            uchoice = sc.next().charAt(0);
        }
    }

    void func(Member a, Map<Integer, Product> available_products, ArrayList<BoughtProduct> boughtProducts) {
        char achoice;
        // Admin functions
        achoice = 'y';
        while (achoice != 'n') {
            int admin_choice;
            System.out.println("What do you want to do?");
            System.out.println("1.View the Products\n2.Add the Products\n3.View the transacation history");
            admin_choice = sc.nextInt();
            switch (admin_choice) {
                case 1:
                    a.ViewProducts(available_products);
                    break;
                case 2:
                    a.AddProduct(available_products);
                    break;
                case 3:
                    a.ViewHistory(boughtProducts);
                    break;
                default:
                    System.out.println("No option available");
                    break;
            }
            System.out.println("\nDo you want to continue(y/n)(for admin)?");
            achoice = sc.next().charAt(0);
        }
    }

}
