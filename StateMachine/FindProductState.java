import java.util.LinkedList;

public class FindProductState implements State<Shop>
{
    /*Constructor - Singleton*/
    private FindProductState() {}

    /*Members*/
    private static FindProductState instance;

    /*Get methods*/
    public static FindProductState getInstance() {
        if (instance == null)
        {
            instance = new FindProductState();
        }
        
        return instance;
    }

    /*Other methods*/
    @Override
    public void enter(Shop owner) {
        System.out.print("\n=== Choose the option ===\n1. Game\n2. Card\n3. Cancel\nInput: ");
    }

    @Override
    public void execute(Shop owner) {
        ProductList list = null;
        int type = Shop.scanner.nextInt();
        switch(type)
        {
            case 1:
            {
                list = owner.getGameList();
                break;
            } 
            case 2:
            {
                list = owner.getCardList();
                break;
            }
            case 3:
            {
                owner.getStateMachine().pop();
                Shop.scanner.nextLine();
                return;
            }
            default:
            {
                System.out.print("Invalid option! Please input: ");
                execute(owner);
                return;
            }            
        } 

        Shop.scanner.nextLine();
        findProduct(owner, list, type);
        enter(owner);
    }

    private void findProduct(Shop owner, ProductList list, int type) {
        System.out.print("Input information: ");
        String input = Shop.scanner.nextLine();
        System.out.print("\n-- Find by --\n1. ID\n2. Name\n3. Publisher\n4. Price\n5. More\nInput: ");
        LinkedList<Product> found = null;
        int check;
        
        do {
            check = Shop.scanner.nextInt();
            switch (check)
            {
                case 1:
                    found = list.findAllProductsByID(input);
                    break;
                case 2:
                    found = list.findAllProductsByName(input);
                    break;
                case 3:
                    found = list.findAllProductsByPublisher(input);
                    break;
                case 4:
                    found = list.findAllProductsByPrice(Integer.parseInt(input));
                    break;
                case 5:
                    advancedFindProduct(owner, type, input);
                    return;
                default: 
                {
                    System.out.print("Invalid option! Please input: ");
                    break;
                }
            }
        } while (check < 1 || check > 5);

        showResult(found);
    }

    private void advancedFindProduct(Shop owner, int type, String inputStr) {
        switch (type) {
            case 1:
            {
                System.out.print("\n-- Find by (Advanced) --\n1. Genre\n2. Platform\n3. Released Year\nInput: ");
                LinkedList<Game> found = null;
                int input;
                do {
                    input = Shop.scanner.nextInt();
                    switch (input) {
                        case 1:
                            found = owner.getGameList().findAllProductsByGenre(inputStr);
                            break;
                        case 2:
                            found = owner.getGameList().findAllProductsByPlatform(inputStr);
                            break;
                        case 3:
                            found = owner.getGameList().findAllProductsByYear(Integer.parseInt(inputStr));
                            break;
                        default:
                            System.out.print("Invalid option! Please input: ");
                            break;
                    }
                } while (input < 1 || input > 3);

                showResult(found);
                break;
            }

            case 2: {
                System.out.print("\n-- Find by (Advanced)--\n1. Value\nInput: ");
                LinkedList<Card> found = null;
                int input;
                do {
                    input = Shop.scanner.nextInt();
                    switch (input) {
                        case 1:
                            found = owner.getCardList().findAllProductsByValue(Integer.parseInt(inputStr));
                            break;
                        default:
                            System.out.print("Invalid option! Please input: ");
                            break;
                    }
                } while (input < 1 || input > 3);

                showResult(found);
                break;
            }
        }
    }

    private <T extends Product> void showResult(LinkedList<T> found) {
        if (found.size() == 0)
        {
            System.out.println("Product not found!");
        }
        else
        {
            for (T p : found)
                System.out.print(p.toString());
        }
    }

    @Override
    public void exit(Shop owner) {}
}