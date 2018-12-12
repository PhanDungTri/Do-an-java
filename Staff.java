public class Staff implements IPrintable,IWritable {
    /*Member*/
    private String id ;
    private String password;
    private boolean isAdmin;
    private String lastName ;
    private String firstName ;
    private String yearOfBirth ;
    private String address ;
    private String phoneNumber ;
    private String mail ;
    private int salary ;

    /*Constructor*/
    public Staff () {}
    public Staff (String id)
    {
        setID (id) ;
        createStaffInfo();
        isAdmin=false;
    }

    /*Set Methods*/
    public void     setID(String id) {this.id = id;}
    public void     setPassword(String password){this.password = password;}
    public void     setLastName(String lastName) {this.lastName = lastName;}
    public void     setFirstName(String firstName) {this.firstName = firstName;}
    public void     setYearOfBirth (String yearOfBirth) {this.yearOfBirth = yearOfBirth;}
    public void     setAddress (String address) {this.address = address;}
    public void     setAdmin(boolean isAdmin){this.isAdmin=isAdmin;}
    public void     setPhoneNumber (String phoneNumber) {this.phoneNumber = phoneNumber;}
    public void     setMail (String mail) {this.mail = mail;}
    public void     setSalary (int salary) {this.salary = salary;}

    /*Get Methods*/
    public String getID() {return id ;}
    public String getPassword() {return password;}
    public String getLastName() {return lastName;}
    public String getFistName() {return firstName;}
    public boolean getIsAdmin()  {return isAdmin;}
    public String getYearOfBirth() {return yearOfBirth;}
    public String getAddress() {return address;}
    public String getPhoneNumber() {return phoneNumber;}
    public String getMail() {return mail;}
    public int getSalary() {return salary;}

    public String toString ()
    {
        String str = "" ;
        str += String.format("|%-10s|%-20s|%-20s|%-15s|%-25s|%-15s|%-15s|%-15s|%n",getID(),getLastName(),getFistName(),getYearOfBirth(),getAddress(),getPhoneNumber(),getMail(),getSalary());
        return str; 
    }

    public void createStaffInfo()
    {
        System.out.print("Input Password: ");
        setPassword(Shop.scanner.nextLine());

        System.out.print("Input First Name: ");
        setFirstName(Shop.scanner.nextLine());

        System.out.print("Input Last Name: ");
        setLastName(Shop.scanner.nextLine());

        System.out.print("Input Year Of Birth: ");
        setYearOfBirth(Shop.scanner.nextLine());

        System.out.print("Input Address: ");
        setAddress(Shop.scanner.nextLine());

        System.out.print("Input Phone Number: ");
        setPhoneNumber(Shop.scanner.nextLine());

        System.out.print("Input Mail: ");
        setMail(Shop.scanner.nextLine());
        
        System.out.print("Set admin (true/false): ");
        setAdmin(Shop.scanner.nextBoolean());

        setSalary(1500000);
    }

    @Override 
    public String toData() {
        String str = getID() + " "
                   + getPassword() +" "
                   + getFistName().replace(" ", "_") + " "
                   + getLastName().replace(" ", "_") + " "
                   + getYearOfBirth() + " "
                   + getAddress().replace(" ", "_") + " "
                   + getPhoneNumber() + " "
                   + getMail() + " "
                   + getSalary() +" "
                   + getIsAdmin() + " "
                   + "xDATASEPARATEx";

        return str ;
    }

    @Override
    public void getData(String[] str){
        setID(str[0]);
        setPassword(str[1]);
        setFirstName(str[2].replace("_"," "));
        setLastName(str[3].replace("_", " "));
        setYearOfBirth(str[4]);
        setAddress(str[5].replace("_", " "));
        setPhoneNumber(str[6]);
        setMail(str[7]);
        setSalary(Integer.parseInt(str[8]));
        setAdmin(Boolean.parseBoolean(str[9]));
    }
    
}