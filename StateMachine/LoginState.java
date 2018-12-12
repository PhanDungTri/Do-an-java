import javax.lang.model.util.ElementScanner6;

public class LoginState implements State<Shop>
{
    /*Constructor - Singleton*/
    private LoginState() {}

    /*Members*/
    private static LoginState instance;
    public static String staffID;

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
            if(owner.getStaffList().findStaff(input) != -1)
            {
                System.out.print("Password: ");
                int flag=0;
                String password = Shop.scanner.nextLine();
                while (flag==0)
                {
                    if(owner.getStaffList().getStaff(input).getPassword().equals(password))
                    {
                        System.out.print("\nStaff "+ owner.getStaffList().getStaff(input).getFistName() +" has logged in ! \n");
                        staffID = input;
                        owner.getStateMachine().pop(false);
                        owner.getStateMachine().push(StaffMenuState.getInstance()); 
                        flag=1;
                    }
                    else
                    {
                        System.out.print("Invalid input ! Password: ");
                        password = Shop.scanner.nextLine();
                    }
                }                 
               
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