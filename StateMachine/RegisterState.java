public class RegisterState implements State<Shop>
{
    /*Constructor - Singleton*/
    private RegisterState() {}

    /*Members*/
    private static RegisterState instance;

    /*Get methods*/
    public static RegisterState getInstance() {
        if (instance == null)
        {
            instance = new RegisterState();
        }
        
        return instance;
    }

    /*Other methods*/
    @Override
    public void enter(Shop owner) {
        System.out.print("\nInput ID ('cancel' to go back): ");
    }

    @Override
    public void execute(Shop owner) {
        String input = Shop.scanner.nextLine();
        if (input.equalsIgnoreCase("cancel"))
        {
            owner.getStateMachine().pop();
        }
        else
        {
            owner.getCustomerList().addCustomer(input);
            enter(owner);
        }
    }

    @Override
    public void exit(Shop owner) {}
}