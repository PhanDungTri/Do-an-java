import java.util.LinkedList;


public class CustomerList {
  
    /* Members */
    protected LinkedList<Customer> list;
    public String path;

    /* Constructor */
    public CustomerList() {
        path = ".\\data\\customerlist.bin";
        list = new LinkedList<Customer>();
        FileIO.readFromFile(list ,path, Customer.class);
    }
 
    /*Get Methods*/
    public Customer getCustomer(String id)
    {
        int index = findCustomer(id);
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

    public int findCustomer(String id) {
        for (Customer customer : list)
            if (customer.getID().equals(id))
                return list.indexOf(customer);
        return -1;
    }

    public void addCustomer(String id) {
        int index = findCustomer(id);
        if (index == -1)
        {
            System.out.println("This is new customer's ID!");
            Customer customer= new Customer(id);
            list.add(customer);
            FileIO.writeToFile(customer, path);
        }
        else
        {
           System.out.println("This customer was created");
        }
    }
    

    public String toString(){
        String str;
        str = "\n**Customer List**\n--------------------\n";
        str += String.format("|%-10s|%-15s|%-20s|%-5s|%-25s|%-15s|%-10s|", 
                             "ID", "First Name", "Last Name", "Year", "Address", "Phone number", "Point");
        str += "\n--------------------\n"; 
        for (Customer customer : list)
        {
            str += customer.toString();
        }

        return str;
    }
}

