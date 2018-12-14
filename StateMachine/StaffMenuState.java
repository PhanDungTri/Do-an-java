public class StaffMenuState implements State<Shop>
{
    /*Constructor - Singleton*/
    private StaffMenuState() {}

    /*Members*/
    private static StaffMenuState instance;

    /*Get methods*/
    public static StaffMenuState getInstance() {
        if (instance == null)
        {
            instance = new StaffMenuState();
        }
        
        return instance;
    }

    /*Other methods*/
    @Override
    public void enter(Shop owner) {
        System.out.print("\n=== Choose the option ===\n1. Add product\n2. Remove Product\n3. View list\n4. View Publisher" +
                         "\n5. Get Quantity\n6. Find Product\n7. Edit Customer\n8. Edit Game\n9. View Receipt\n");
        if(owner.getStaffList().getStaff(LoginState.staffID).getIsAdmin())
        {
            System.out.print("10. Edit Staff\n11. Add Staff\n12. Logout\nInput: ");
        }
        else {
            System.out.print("10. Logout\nInput: ");
        }
    }

    @Override
    public void execute(Shop owner) {
        
            switch(Shop.scanner.nextInt())
            {
                case 1:
                    owner.getStateMachine().push(AddProductState.getInstance());
                    break;
                case 2:
                    owner.getStateMachine().push(RemoveProductState.getInstance());
                    break;
                case 3:
                    owner.getStateMachine().push(ViewListState.getInstance());
                    break;
                case 4:
                    owner.getStateMachine().push(ViewPublisherState.getInstance());
                    break;
                case 5:
                    owner.getStateMachine().push(GetQuantityState.getInstance());
                    break;
                case 6:
                    owner.getStateMachine().push(FindProductState.getInstance());
                    break;
                case 7:
                    owner.getStateMachine().push(EditCustomerState.getInstance());
                    break;
                case 8:
                    owner.getStateMachine().push(EditGameState.getInstance());
                    break;
                case 9:
                    owner.getStateMachine().push(ViewReceiptState.getInstance());
                    break;
                case 10:
                {
                    if(owner.getStaffList().getStaff(LoginState.staffID).getIsAdmin())
                    {
                        owner.getStateMachine().push(EditStaffState.getInstance());
                        break;
                    }
                    else {
                        owner.getStateMachine().pop();
                        break;
                    }
                }
                case 11:
                {
                    if(owner.getStaffList().getStaff(LoginState.staffID).getIsAdmin())
                    {
                        owner.getStateMachine().push(AddStaffState.getInstance());
                        break;
                    }
                }
                case 12:
                {
                    if(owner.getStaffList().getStaff(LoginState.staffID).getIsAdmin())
                    {
                        owner.getStateMachine().pop();
                        break;
                    }
                }
                default:
                    System.out.print("Invalid option! Please input: ");
                    break;
            }
            Shop.scanner.nextLine();
    }

    @Override
    public void exit(Shop owner) {}
}