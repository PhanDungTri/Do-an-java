public class ViewReceiptState implements State<Shop>
{
    /*Constructor - Singleton*/
    private ViewReceiptState() {}
    
    /*Members*/
    private static ViewReceiptState instance;

    /*Get methods*/
    public static ViewReceiptState getInstance() {
        if (instance == null)
        {
            instance = new ViewReceiptState();
        }

        return instance;
    }

    /*Other methods*/
    @Override
    public void enter(Shop owner) {
        System.out.print("\nInput receipt id ('cancel' to go back): ");
    }

    @Override
    public void execute(Shop owner) {
        String input = Shop.scanner.nextLine();
        if (input.equalsIgnoreCase("cancel")) {
            owner.getStateMachine().pop();
        }
        else {
            ReceiptList list = owner.getReceiptList();
            if (list.findID(input) != -1) {
                System.out.println(list.getReceipt(input).toString());
            }
            else {
                System.out.println("Cannot find id!");
            }

            enter(owner);
        }
    }

    @Override
    public void exit(Shop owner) {}
}