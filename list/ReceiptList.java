import java.util.LinkedList;


public class ReceiptList {
  
    /* Members */
    private LinkedList<Receipt> list;
    private CustomerList customerList;
    private StaffList staffList;
    public String path;

    /* Constructor */
    public ReceiptList(CustomerList customerList,StaffList staffList) {
        path = "./data/Receiptlist.bin";
        list = new LinkedList<Receipt>();
        this.customerList= customerList;
        this.staffList = staffList;
        FileIO.readFromFile(list ,path, Receipt.class);
    }
 
    /*Get Methods*/
    public Receipt getReceipt(String id)
    {
        int index = findReceipt(id);
        if (index == -1)
        {
            System.out.println("Cannot find ID!");
            return null;
        }
        else
        {
            return list.get(index);
        }
    }

    public int getTotalQuantity() {
        return list.size();
    }

    public int findReceipt(String id) {
        for (Receipt Receipt : list)
            if (Receipt.getID().equals(id))
                return list.indexOf(Receipt);
        return -1;
    }

    public void addReceipt(String id,String staffID,String customerID) {
        int index = findReceipt(id);
        if (index == -1)
        {
            if(customerList.findCustomer(customerID)==-1)
            {
                System.out.println("Not found Customer's ID. Please register before !");
                customerList.addCustomer(customerID);
            }
            System.out.println("This is new Receipt's ID!");
            Receipt Receipt= new Receipt(id,staffID,customerID,staffList,customerList);
            list.add(Receipt);
            FileIO.writeToFile(Receipt, path);
        }
        else
        {
           System.out.println("This Receipt was created");
        }
    }
    

    public String toString(){
        String str;
        str = "\n**Receipt List**\n--------------------\n";
        str += String.format("|%-15s|%-15s|%-15s|%-15s|%-15s|", 
                            "ID","Customer","Staff","Date","Sumary");
        str += "\n--------------------\n"; 
        for (Receipt Receipt : list)
        {
            str += Receipt.toString();
        }

        return str;
    }
}

