public class GetQuantityState implements State<Shop>
{
    /*Constructor - Singleton*/
    private GetQuantityState() {}

    /*Members*/
    private static GetQuantityState instance;

    /*Get methods*/
    public static GetQuantityState getInstance() {
        if (instance == null)
        {
            instance = new GetQuantityState();
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
        int choose = 0;
        while( (choose!=1) && (choose!=2) &&  (choose!=3) )
        {
            choose = Shop.scanner.nextInt();
            switch(choose)
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
                    break; 
                }            
            }
        }
        Shop.scanner.nextLine();
        System.out.print("Input id ('total' to get total quantity): ");
        String input = Shop.scanner.nextLine();
        if (input.equalsIgnoreCase("total"))
        {
            System.out.println("Quantity: " + list.getTotalQuantity());
        }
        else
        {
            System.out.println("Quantity: " + list.getQuantity(input));
        }
        enter(owner);
    }

    @Override
    public void exit(Shop owner) {}
}