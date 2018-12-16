public class CheckoutState implements State<Shop>
{
    /*Constructor - Singleton*/
    private CheckoutState() {}

    /*Members*/
    private static CheckoutState instance;
    private static Cart cart;

    /*Get methods*/
    public static CheckoutState getInstance() {
        if (instance == null)
        {
            instance = new CheckoutState();
            cart = new Cart();
        }
        
        return instance;
    }

    /*Other methods*/
    @Override
    public void enter(Shop owner) {
        System.out.print("\n=== Choose the option ===\n1. Add Product\n2. Remove Product\n3. Checkout\n4. Cancel\nInput: ");
    }

    @Override
    public void execute(Shop owner) {
        switch(Shop.scanner.nextInt())
        {
            case 1:
            {
                int check = 1;
                do {
                    check = 0;
                    System.out.print("\n--Which type of product do you want to add?\n1. Game\n2. Card\n3. Cancel\nInput: ");
                    int type = Shop.scanner.nextInt();
                    if ( type != 1 || type != 2) {
                        check = 1;
                        System.out.print("Invalid option! Please input: ");
                    }
                    else {
                        System.out.print("\nQuantity: ");
                        int quantity = Shop.scanner.nextInt();
                        Shop.scanner.nextLine();
                        System.out.print("\nInput ID: ");
                        String id = Shop.scanner.nextLine();

                        if (type == 1) {
                            if (owner.getGameList().findProductByID(id) != -1) {
                                if (owner.getGameList().getQuantity(id) < quantity) {
                                    System.out.println("Not enough products!");
                                }
                                else {
                                    cart.addProduct(id, Product.Type.Game, quantity);
                                }
                            }
                            else {
                                System.out.println("Cannot find ID!");
                            }
                        }
                        else {
                            if (owner.getCardList().findProductByID(id) != -1) {
                                if (owner.getCardList().getQuantity(id) < quantity) {
                                    System.out.println("Not enough products!");
                                }
                                else {
                                    cart.addProduct(id, Product.Type.Card, quantity);
                                }
                            }
                            else {
                                System.out.println("Cannot find ID!");
                            }
                        }
                    }

                } while (check == 1);
                enter(owner);
                break;
            }
            case 2:
            {
                int check = 1;
                do {
                    check = 0;
                    System.out.print("\n--Which type of product do you want to remove?\n1. Game\n2. Card\n3. Cancel\nInput: ");
                    int type = Shop.scanner.nextInt();
                    if ( type != 1 || type != 2) {
                        check = 1;
                        System.out.print("Invalid option! Please input: ");
                    }
                    else {
                        System.out.print("\nQuantity: ");
                        int quantity = Shop.scanner.nextInt();
                        Shop.scanner.nextLine();
                        System.out.print("\nInput ID: ");
                        String id = Shop.scanner.nextLine();

                        if (type == 1) {
                            cart.removeProduct(id, Product.Type.Game, quantity);
                        }
                        else {
                            cart.removeProduct(id, Product.Type.Card, quantity);
                        }
                    }

                } while (check == 1);
                enter(owner);
                break;
            }
            case 3:
            {
                int check = 1;
                String customerID = "";
                do {
                    check = 0;
                    System.out.print("Input customer ID ('000' for non-member customer): ");
                    customerID = Shop.scanner.nextLine();
                    if (owner.getCustomerList().findCustomer(customerID) == -1 && customerID.equals("000")) {
                        System.out.print("Cannot find ID! Please input: ");
                        check = 1;
                    }
                } while (check == 1);

                int id;
                if (owner.getReceiptList().getQuantity() == 0) {
                    id = 1;
                }
                else {
                    id = owner.getReceiptList().getQuantity() + 1;
                }

                Receipt receipt = new Receipt(Integer.toString(id), owner.getStaffList().getStaff(LoginState.staffID).getLastName() + owner.getStaffList().getStaff(LoginState.staffID).getFistName(), Receipt.ReceiptType.Export);
                receipt.setCustomerID(customerID);

                for (String str : cart.getCart()) {
                    String[] s = str.split(" ");
                    if (s[1].equals(Product.Type.Game.toString())){
                        GameList list = owner.getGameList();

                        for (int i = 0; i < Integer.parseInt(s[2]); ++i) {
                            receipt.toInfo(list.getProduct(s[0]));
                            list.removeProduct(s[0], 1);
                        }
                    }
                    else {
                        CardList list = owner.getCardList();

                        for (int i = 0; i < Integer.parseInt(s[2]); ++i) {
                            receipt.toInfo(list.getProduct(s[0]));
                            list.removeProduct(s[0], 1);
                        }
                    }
                }
                
                if (!customerID.equals("000")) {
                    Customer customer = owner.getCustomerList().getCustomer(customerID);
                    customer.setPoint(receipt.getCost() / 1000);

                    FileIO.rewriteFile(owner.getCustomerList().getList(), "./data/customerlist.bin");
                }

                FileIO.writeToFile(receipt, "./data/receiptlist.bin");

                System.out.println("====================");
                System.out.println(receipt.toString());
                System.out.println("====================");

                owner.getStateMachine().pop();
                break;
            }
            case 4:
                owner.getStateMachine().pop();
                break;
            default:
                System.out.print("Invalid option! Please input: ");
                break;
        }
        Shop.scanner.nextLine();
    }

    @Override
    public void exit(Shop owner) {
        cart.clear();
    }
}