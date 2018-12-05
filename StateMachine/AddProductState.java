import java.util.LinkedList;

public class AddProductState implements State<Shop>
{
    /*Constructor - Singleton pattern*/
    private AddProductState() { count = 0; }
    /*Members*/
    private static AddProductState instance;
    private LinkedList<Integer> count;

    /*Get methods*/
    public static AddProductState getInstance() {
        if (instance == null)
        {
            instance = new AddProductState();
        }

        return instance;
    }

    /*Other methods*/
    @Override
    public void enter(Shop owner) {
        System.out.print("\n--Which type of product do you want to add?\n1. Game\n2. Card\n3. Cancel\nInput: ");
    }

    @Override
    public void execute(Shop owner) {
        switch (Shop.scanner.nextInt())
        {
            case 1:
            {
                Shop.scanner.nextLine();
                System.out.print("\nGame chosen! Input ID: ");
                String id = Shop.scanner.nextLine();
                System.out.print("How many products do you want to add? ");
                int quantity = Shop.scanner.nextInt();
                Shop.scanner.nextLine();
                owner.getGameList().addProduct(id, quantity, Game.class);
                for (int i = 0; i < quantity; ++i)
                {
                    count.add(1);
                }
                enter(owner);
                break;
            }
            case 2:
            {
                Shop.scanner.nextLine();
                System.out.print("\nCard chosen! Input ID: ");
                String id = Shop.scanner.nextLine();
                System.out.print("How many products do you want to add? ");
                int quantity = Shop.scanner.nextInt();
                Shop.scanner.nextLine();
                owner.getCardList().addProduct(id, quantity, Card.class);
                for (int i = 0; i < quantity; ++i)
                {
                    count.add(2);
                }
                enter(owner);
                break;
            }
            case 3:
                owner.getStateMachine().pop();
                break;
            default: 
                System.out.print("Invalid option! Please input: ");
                break;
        }
    }
    
    @Override
    public void exit(Shop owner) {
        GameList gameList = owner.getGameList();
        CardList cardList = owner.getCardList();

        int i = gameList.getTotalQuantity() - 1;
        int j = cardList.getTotalQuantity() - 1;

        for (int n : count) {
            if (n == 1) {
                
            }
        }
    }
}