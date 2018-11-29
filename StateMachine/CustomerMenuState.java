public class CustomerMenuState implements State<Shop>
{
    /*Constructor - Singleton*/
    private CustomerMenuState() {}

    /*Members*/
    private static CustomerMenuState instance;

    /*Get methods*/
    public static CustomerMenuState getInstance() {
        if (instance == null)
        {
            instance = new CustomerMenuState();
        }
        
        return instance;
    }

    /*Other methods*/
    @Override
    public void enter(Shop owner) {
        System.out.print("\n=== Choose the option ===\n1. Register\n2. Cancel\nInput: ");
    }

    @Override
    public void execute(Shop owner) {
        switch(Shop.scanner.nextInt())
        {
            case 1:
                owner.getStateMachine().push(RegisterState.getInstance());
                break;
            case 2:
                owner.getStateMachine().pop();
                break;
            case 3:
                owner.getStateMachine().push(ViewListState.getInstance());
            default:
                System.out.print("Invalid option! Please input: ");
                break;
        }
        Shop.scanner.nextLine();
    }

    @Override
    public void exit(Shop owner) {}
}