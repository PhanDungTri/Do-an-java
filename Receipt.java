import java.text.SimpleDateFormat;
import java.util.Date;

public class Receipt implements IPrintable, IWritable {
    public enum ReceiptType {
        Import,
        Export,
    }

    /*Constructor*/
    public Receipt() {
        id = new String("");
        staff = new String("");
        date = new String("");
        info = new String("");
        customerID = new String("");
    }

    public Receipt(String id, String staff, ReceiptType type) {
        this.id = new String("");
        this.staff = new String("");
        customerID = new String("");
        date = new String("");
        setID(id);
        setDate(new SimpleDateFormat("yy-MM-dd HH:mm").format(new Date()));
        setStaff(staff);
        setType(type);
        info = new String("");
        cost = 0;
        quantity = 0;
    }
    /*Members*/
    ReceiptType type;
    int quantity;
    String date;
    String info;
    String staff;
    String id;
    String customerID;
    int cost;

    /*Get methods*/
    public int getQuantity() { return quantity; }
    public String getDate() { return date; }
    public String getInfo() { return info; }
    public String getStaff() { return staff; }
    public String getID() { return id; }
    public String getCustomerID() { return customerID; }
    public int getCost() { return cost; }
    public ReceiptType getType() { return type; }

    /*Set methods*/
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setDate(String date) { this.date = date; }
    public void setInfo(String info) { this.info = info; }
    public void setStaff(String staff) { this.staff = staff; }
    public void setID(String id) { this.id = id; }
    public void setCustomerID(String id) { customerID = id; }
    public void setCost(int cost) { this.cost = cost; }
    public void setType(ReceiptType type) { this.type = type; }
    
    /*Other methods*/
    public void toInfo(Product product) {
        info += product.toString();
        info += "Activation Code: " + product.getCode() + "\n";
        cost += product.getPrice();
        ++quantity;
    }
    @Override
    public String toString() {
        String str = "";
        if (getType() == ReceiptType.Import) {
            str = String.format("|%-8s|%-10s|%-20s|%-20s|%-5s|%-10s|\n", "Action", "ID", "Staff", "Date", "Quan.", "Price");
            str += String.format("|%-8s|%-10s|%-20s|%-20s|%-5s|%-10s|\n\n", getType().toString(), getID(), getStaff(), getDate(), getQuantity(), getCost());
        }
        else {
            str = String.format("|%-8s|%-10s|%-20s|%-20s|%-5s|%-10s|%-10s|\n", "Action", "ID", "Staff", "Date", "Quan.", "Price", "Customer");
            str += String.format("|%-8s|%-10s|%-20s|%-20s|%-5s|%-10s|\n\n", getType().toString(), getID(), getStaff(), getDate(), getQuantity(), getCost(), getCustomerID());
        }

        str += getInfo();
        return str;
    }

    @Override
    public String toData() {
        String str = getID() + " " +
                     getStaff().replace(" ", "_") + " " +
                     getDate().replace(" ", "_") + " " +
                     getQuantity() + " " +
                     getCost() + " " +
                     getInfo().replace(" ", "_") + " " +
                     getType().toString() + " " +
                     getCustomerID() +
                     "xDATASEPARATEx";
        
        return str;
    }

    @Override
    public void getData(String[] str) {
        setID(str[0]);
        setStaff(str[1].replace("_", " "));
        setDate(str[2].replace("_", " "));
        setQuantity(Integer.parseInt(str[3]));
        setCost(Integer.parseInt(str[4]));
        setInfo(str[5].replace("_", " "));
        setType(ReceiptType.valueOf(str[6]));

        if (getType() == ReceiptType.Export) {
            setCustomerID(str[7]);
        }
    }
}