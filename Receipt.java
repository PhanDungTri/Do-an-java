import java.util.Scanner;
import java.util.Date;
import java.lang.Double;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
public class Receipt implements IPrintable, IWritable {
    /*Members*/
    private String id;
    private String customerID;
    private String staffID;
    private String date;
    private Double sumary;
    private StaffList staffList;
    private CustomerList customerList;
    
    /*Constructor*/
    public Receipt(){}
    public Receipt(String id, String staffID, String customerID,StaffList staffList,CustomerList customerList)
    {   
        setID(id);
        setCustomerID(customerList.getCustomer(customerID).getFirstName());
        setStaffID(staffList.getStaff(staffID).getFirstName());
        createReceiptInfo();
        
    }

    
    /*Set Methods*/
    public void    setID(String id) { this.id = id; }
    public void    setCustomerID(String customerID) {this.customerID=customerID; }
    public void    setStaffID(String staffID) {this.staffID=staffID; }
    public void    setDate(String date) {this.date = date; }
    public void    setSumary(Double sumary) { this.sumary=sumary; }

    
    /*Get Methods*/
    public String getID() { return id; }
    public String getCustomerID() { return customerID; }
    public String getStaffID() { return staffID; }
    public String getDate() { return date; }
    public Double getSumary() { return sumary; }
    
    public String toString()
    {
        String str = "";
        str += String.format("|%-15s|%-15s|%-15s|%-15s|%-15s|%n", getID(),getCustomerID(),getStaffID(),getDate(),getSumary());
        return str;
    }

    public void createReceiptInfo()
    {
        //System.out.print("Input Date (DD/MM/YYYY) : ");
        String date = new SimpleDateFormat("dd-MM-yy HH:mm:ss").format(new Date());
        setDate(date);
        
        setSumary(0.0);
    }

    @Override
    public String toData() {
        String str = getID() + " "
                   + getStaffID().replace(" ", "_") + " "
                   + getCustomerID().replace(" ", "_") + " "
                   + getDate().replace(" ", "_") + " "
                   + getSumary()
                   + "xDATASEPARATEx";
        return str;
    }
    @Override
    public void getData(String[] str) {
        setID(str[0]);
        setStaffID(str[1].replace("_", " "));
        setCustomerID(str[2].replace("_", " "));
        setDate(str[3].replace("_", " "));
        setSumary(Double.parseDouble(str[4]));
    }
    
}
