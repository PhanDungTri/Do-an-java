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
        switch(Shop.scanner.nextInt())
        {
            case 1:
                list = owner.getGameList();
                break;
            case 2:
                list = owner.getCardList();
                break;
            case 3:
            {
                owner.getStateMachine().pop();
                Shop.scanner.nextLine();
                return;
            }
            default:
                System.out.print("Invalid option! Please input: ");
                break;
        }
        Shop.scanner.nextLine();

        System.out.print("Input id: ");
        String input = Shop.scanner.nextLine();
        if (list.findProduct(input) == -1)
        {
            System.out.println("Product not found!");
        }
        else
        {
            System.out.println(list.getProduct(input).toString());
        }
        enter(owner);
    }

    @Override
    public void exit(Shop owner) {}
}