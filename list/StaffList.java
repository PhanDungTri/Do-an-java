import java.awt.List;
//import java.util.LinkedList;

public class StaffList {

    /* Members */
    protected LinkedList<Staff> list ;
    public String path ;

    /* Constructor */
    public StaffList() {
        path = "./data/stafflist.bin";
        list = new LinkedList<Staff>();
        FileIO.readFromFile(list, path, Staff.class);
    }

    /* Get Methods */
    public Staff getStaff (String id) 
    {
        int index = findStaff(id);
        if (index == -1)
        {
            System.out.println("Cannot find ID!");
            return null ;
        }
        else 
        {
            return list.get(index);
        }
    }

    public int getTotalQuantify()
    {
        return list.size();
    }

    public int findStaff(String id)
    {
        for (Staff staff : list)
            if(staff.getID().equals(id))
                return list.indexOf(staff);
        return -1;
    }

    public void addStaff(String id)
    {
        int index = findStaff(id);
        if(index == -1)
        {
            System.out.println("This is new Staff's ID");
            Staff staff = new Staff(id);
            list.add(staff);
            FileIO.writeToFile(staff, path);
        }
        else
        {
            System.out.println("This Staff was created");
        }
    }

    public String toString() {
        String str;
        str = "\n**Staff List**\n--------------------\n";
        str += String.format("|%-10s|%-20s|%-20s|%-15s|%-25s|%-15s|%-15s|%-15s|%n",
                                "ID","Last Name","Fist Name"," Year Of Birth" ,"Address","Phone Number","Mail","Salary");
        str += "\n--------------------\n";
        for (Staff staff : list)
        {
            str += staff.toString();
        } 

        return str;
    }
}