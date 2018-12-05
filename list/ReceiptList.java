import java.util.LinkedList;


public class ReceiptList {
  
    /* Members */
    private LinkedList<Receipt> list;
    private CustomerList customerList;
    private StaffList staffList;
    public String path;

    /* Constructor */
    public ReceiptList(CustomerList customerList,StaffList staffList) {
        path = "./data/receiptlist.bin";
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

    public void addReceipt(String staffID/*,String customerID*/) {
        /*if(customerList.findCustomer(customerID)==-1)
        {
            System.out.println("Not found Customer's ID. Please register before !");
            customerList.addCustomer(customerID);
        }*/
        int id;
        if (list.size() == 0)
        {
            id = 1;
        }
        else
        {
            id = Integer.parseInt(list.getLast().getID()) + 1;
        }
        Receipt receipt= new Receipt(Integer.toString(id),staffID/*,customerID,staffList,customerList*/);
        receipt.setStaffInfo(staffList.getStaff(staffID).getID() + " " 
                             + staffList.getStaff(staffID).getFirstName() + " "
                             + staffList.getStaff(staffID).getLastName());
        list.add(receipt);
        FileIO.writeToFile(receipt, path);
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

