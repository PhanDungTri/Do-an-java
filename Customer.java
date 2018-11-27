
public class Customer implements IPrintable, IWritable {
    /*Members*/
    private String id;
    private String lastName;
    private String firstName;
    private int    yearOfBirth;
    private String phoneNumber;
    private String address;
    private int    point;
    
    /*Constructor*/
    public Customer(String id)
    {
        setID(id);
        createCustomerInfo();
    }

    
    /*Set Methods*/
    public void    setID(String id) { this.id = id; }
    public void    setLastName(String lastName) { this.lastName=lastName; }
    public void    setFirstName(String firstName) { this.firstName=firstName; }
    public void    setYearOfBirth(int yearOfBirth) { this.yearOfBirth=yearOfBirth; }
    public void    setAddress(String address) { this.address=address; }
    public void    setPhoneNumber(String phoneNumber) { this.phoneNumber=phoneNumber; }
    public void    setPoint(int point) { this.point=point; }
    
    /*Get Methods*/
    public String getID() { return id; }
    public String getLastName() { return lastName; }
    public String getFirstName() { return firstName; }
    public int    getYearOfBirth() { return yearOfBirth; }
    public String getAddress()  { return address; }
    public String getPhoneNumber() { return phoneNumber; }
    public int    getPoint() { return point; }
    
    public String toString()
    {
        String str = "";
        str += String.format("|%-10s|%-20s|%-20s|%-15s|%-25s|%-15s|%-15s|%n", getID(), getFirstName(), getLastName(), getYearOfBirth(), getAddress(),getPhoneNumber(),getPoint());
        return str;
    }

    public void createCustomerInfo()
    {
        System.out.print("Input First Name: ");
        setFirstName(Shop.scanner.nextLine());

        System.out.print("Input Last Name: ");
        setLastName(Shop.scanner.nextLine());

        System.out.print("Input Year Of Birth: ");
        setYearOfBirth(Shop.scanner.nextInt());
        Shop.scanner.nextLine();
        
        System.out.print("Input Address: ");
        setAddress(Shop.scanner.nextLine());
        
        System.out.print("Input Phone Number: ");
        setPhoneNumber(Shop.scanner.nextLine());
        
        setPoint(0);
    }

    @Override
    public String toData() {
        String str = getID() + " "
                   + getFirstName().replace(" ", "_") + " "
                   + getLastName().replace(" ", "_") + " "
                   + getPhoneNumber() + " "
                   + getYearOfBirth() + " " 
                   + getAddress().replace(" ", "_") + " "
                   + getPoint()
                   + "xDATASEPARATEx";
        
        return str;
    }

    @Override
    public void getData(String[] str) {
        setID(str[0]);
        setFirstName(str[1].replace("_", " "));
        setLastName(str[2].replace("_", " "));
        setPhoneNumber(str[3]);
        setYearOfBirth(Integer.parseInt(str[4]));
        setAddress(str[5].replace("_", " "));
        setPoint(Integer.parseInt(str[6]));
    }
}
