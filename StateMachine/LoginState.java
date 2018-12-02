public class LoginState implements State<Shop>
{
    /*Constructor - Singleton*/
    private LoginState() {}

    /*Members*/
    private static LoginState instance;

    /*Get methods*/
    public static LoginState getInstance() {
        if (instance == null)
        {
            instance = new LoginState();
        }
        
        return instance;
    }

    /*Other methods*/
    @Override
    public void enter(Shop owner) {
        System.out.print("\nInput Staff's ID ('cancel' to go back): ");
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
            if(owner.getStaffList().findStaff(input)!=-1)
                {
                    System.out.print("\nStaff "+ owner.getStaffList().getStaff(input).getFistName() +" has logged in ! \n");
                    owner.getStateMachine().pop(false);
                    owner.getStateMachine().push(StaffMenuState.getInstance()); 
                }
               
            else
                {
                 System.out.print("\nWrong staff's ID !!\n");
                 enter(owner);
                }
        }
    }

    @Override
    public void exit(Shop owner) {}
}