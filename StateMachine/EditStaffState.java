public class EditStaffState implements State<Shop>
{
    //Contructor
    private EditStaffState() {}

    //Members
    private static EditStaffState instance;

    //Get Methods
    public static EditStaffState getInstance()
    {
        if(instance == null)
        {
            instance = new EditStaffState();
        }

        return instance;
    }

    //Other methods
    @Override
    public void enter(Shop owner)
    {
        System.out.print("\nInput Staff's ID ('cancel' to go back): ");
    }

    @Override
    public void execute(Shop owner)
    {
        String input = Shop.scanner.nextLine();
        if (input.equalsIgnoreCase("cancel"))
        {
            owner.getStateMachine().pop();
        }
        else if (owner.getStaffList().findStaff(input)==-1)
        {
            System.out.print("\nThis staff has not created ! Please input again: ");

        }
        else 
        {
            System.out.print("\n=== Staff's information ===\n");
            System.out.print( owner.getStaffList().getStaff(input).toString());
            System.out.print("\n=== Choose the option ===\n1. Fist Name\n2. Last Name\n3. Year Of Birth\n4. Address\n5. Phone Number\n6. Mail\n7. Salary\nInput: ");
            int isDone=1;
            do  {
                isDone =0;
                switch( Shop.scanner.nextInt())
                {
                    case 1:
                    Shop.scanner.nextLine();
                    System.out.print("\nInput First Name: ");
                    String firstname = Shop.scanner.nextLine();
                    owner.getStaffList().getStaff(input).setFirstName(firstname);
                    FileIO.rewriteFile(owner.getStaffList().list,"./data/stafflist.bin");
                    enter(owner);
                    break;
    
                    case 2:
                    Shop.scanner.nextLine();
                    System.out.print("\nInput Last Name: ");
                    String lastname = Shop.scanner.nextLine();
                    owner.getStaffList().getStaff(input).setLastName(lastname);
                    FileIO.rewriteFile(owner.getStaffList().list,"./data/stafflist.bin");
                    enter(owner);
                    break;
    
                    case 3:
                    Shop.scanner.nextLine();
                    System.out.print("\nInput Year Of Birth: ");
                    String yearofbirth = Shop.scanner.nextLine();
                    owner.getStaffList().getStaff(input).setYearOfBirth(yearofbirth);
                    FileIO.rewriteFile(owner.getStaffList().list,"./data/stafflist.bin");
                    enter(owner);
                    break;
    
                    case 4:
                    Shop.scanner.nextLine();
                    System.out.print("\nInput Address: ");
                    String add = Shop.scanner.nextLine();
                    owner.getStaffList().getStaff(input).setAddress(add);
                    FileIO.rewriteFile(owner.getStaffList().list,"./data/stafflist.bin");
                    enter(owner);
                    break;
    
                    case 5:
                    Shop.scanner.nextLine();
                    System.out.print("\nInput Phone Number: ");
                    String phonenum = Shop.scanner.nextLine();
                    owner.getStaffList().getStaff(input).setPhoneNumber(phonenum);
                    FileIO.rewriteFile(owner.getStaffList().list,"./data/stafflist.bin");
                    enter(owner);
                    break;
    
                    case 6:
                    Shop.scanner.nextLine();
                    System.out.print("\nInput Mail: ");
                    String mail = Shop.scanner.nextLine();
                    owner.getStaffList().getStaff(input).setMail(mail);
                    FileIO.rewriteFile(owner.getStaffList().list,"./data/stafflist.bin");
                    enter(owner);
                    break;
    
                    case 7:
                    Shop.scanner.nextLine();
                    System.out.print("\nInput First Name: ");
                    int salary = Shop.scanner.nextInt();
                    owner.getStaffList().getStaff(input).setSalary(salary);
                    FileIO.rewriteFile(owner.getStaffList().list,"./data/stafflist.bin");
                    Shop.scanner.nextLine();
                    enter(owner);
                    break;
                    default:
                    isDone=1;
                    System.out.print("Invalid option! Please input: ");

                }
            } while(isDone==1);
            
        }
    }

    @Override 
    public void exit(Shop owner) {}
}