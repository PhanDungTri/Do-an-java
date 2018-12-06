import java.util.LinkedList;

public class AddProductState implements State<Shop>
{
    /*Constructor - Singleton pattern*/
    private AddProductState() {}

    /*Members*/
    private static AddProductState instance;
    private static LinkedList<Integer> count;

    /*Get methods*/
    public static AddProductState getInstance() {
        if (instance == null)
        {
            instance = new AddProductState();
            count = new LinkedList<Integer>();
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
                for (int i = 0; i < quantity; ++i) {
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
                for (int i = 0; i < quantity; ++i) {
                    count.add(2);
                }
                enter(owner);;
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
        if (!count.isEmpty())
        {
            int id;
            if (owner.getReceiptList().getQuantity() == 0) {
                id = 1;
            }
            else {
                id = owner.getReceiptList().getQuantity() + 1;
            }

            Receipt receipt = new Receipt(Integer.toString(id), owner.getStaffList().getStaff(LoginState.staffID).getLastName() + owner.getStaffList().getStaff(LoginState.staffID).getFistName(), Receipt.ReceiptType.Import);
            GameList gameList = owner.getGameList();
            CardList cardList = owner.getCardList();
            ReceiptList receiptList = owner.getReceiptList();
            receiptList.addReceipt(receipt);

            int i = gameList.getTotalQuantity() - 1;
            int j = cardList.getTotalQuantity() - 1;

            for (int n : count) {
                if (n == 1) {
                    receiptList.writeReceipt(gameList.getProduct(i));
                    --i;
                }
                else {
                    receiptList.writeReceipt(cardList.getProduct(j));
                    --j;
                }
            }
            FileIO.writeToFile(receipt, receiptList.path);
        }

        count.clear();
    }
}