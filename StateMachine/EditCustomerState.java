public class EditCustomerState implements State<Shop>
{
    /*Constructor - Singleton*/
    private EditCustomerState() {}

    /*Members*/
    private static EditCustomerState instance;

    /*Get methods*/
    public static EditCustomerState getInstance() {
        if (instance == null)
        {
            instance = new EditCustomerState();
        }
        
        return instance;
    }

    /*Other methods*/
    @Override
    public void enter(Shop owner) {
        System.out.print("\nInput Customer's ID ('cancel' to go back): ");
    }

    @Override
    public void execute(Shop owner) {
        String input = Shop.scanner.nextLine();
        if (input.equalsIgnoreCase("cancel"))
        {
            owner.getStateMachine().pop();
        }
        else if(owner.getCustomerList().findCustomer(input)!=-1)
        {
            System.out.print("\n=== Customer's infomation ===\n");
            System.out.print( owner.getCustomerList().getCustomer(input).toString());
            System.out.print("\n=== Choose the option ===\n1. First Name\n2. Last Name\n3. Year Of Birth\n4 .Address\n5. Phone Number\n6. Point\nInput: ");
            switch( Shop.scanner.nextInt())
            {
                case 1:
                Shop.scanner.nextLine();
                System.out.print("\nInput First Name: ");
                String firstname = Shop.scanner.nextLine();
                owner.getCustomerList().getCustomer(input).setFirstName(firstname);
                FileIO.rewriteFile(owner.getCustomerList().list,"./data/customerlist.bin");
                enter(owner);
                break;

                case 2:
                Shop.scanner.nextLine();
                System.out.print("\nInput Last Name: ");
                String lastname = Shop.scanner.nextLine();
                owner.getCustomerList().getCustomer(input).setLastName(lastname);
                FileIO.rewriteFile(owner.getCustomerList().list,"./data/customerlist.bin");
                enter(owner);
                break;

                case 3:
                Shop.scanner.nextLine();
                System.out.print("\nInput Year Of Birth: ");
                int yearofbirth = Shop.scanner.nextInt();
                owner.getCustomerList().getCustomer(input).setYearOfBirth(yearofbirth);
                FileIO.rewriteFile(owner.getCustomerList().list,"./data/customerlist.bin");
                Shop.scanner.nextLine();
                enter(owner);
                break;

                case 4:
                Shop.scanner.nextLine();
                System.out.print("\nInput Address: ");
                String in = Shop.scanner.nextLine();
                owner.getCustomerList().getCustomer(input).setAddress(in);
                FileIO.rewriteFile(owner.getCustomerList().list,"./data/customerlist.bin");
                enter(owner);
                break;

                case 5:
                Shop.scanner.nextLine();
                System.out.print("\nInput Phone Number: ");
                String phonenumber = Shop.scanner.nextLine();
                owner.getCustomerList().getCustomer(input).setPhoneNumber(phonenumber);
                FileIO.rewriteFile(owner.getCustomerList().list,"./data/customerlist.bin");
                enter(owner);
                break;

                case 6:
                Shop.scanner.nextLine();
                System.out.print("\nInput Point: ");
                int point = Shop.scanner.nextInt();
                owner.getCustomerList().getCustomer(input).setPoint(point);
                FileIO.rewriteFile(owner.getCustomerList().list,"./data/customerlist.bin");
                Shop.scanner.nextLine();
                enter(owner);
                break;
                
               
            }
        
            
           
        }
        else
        {
            System.out.print("\nThis customer has not created ! Please input again: ");

        }
    }

    @Override
    public void exit(Shop owner) {}
}