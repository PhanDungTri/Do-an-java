public class AddStaffState implements State<Shop>
{
    /*Constructor - Singleton*/
    private AddStaffState() {}

    /*Members*/
    private static AddStaffState instance;

    /*Get methods*/
    public static AddStaffState getInstance() {
        if (instance == null)
        {
            instance = new AddStaffState();
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
            owner.getStaffList().addStaff(input);
            Shop.scanner.nextLine();
            enter(owner);
        }
    }

    @Override
    public void exit(Shop owner) {}
}