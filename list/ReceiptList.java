//import java.util.LinkedList;

public class ReceiptList {
    /*Constructor*/
    public ReceiptList() {
        list = new LinkedList<Receipt>();
        path = "./data/receiptlist.bin";

        FileIO.readFromFile(list, path, Receipt.class);
    }

    /*Members*/
    LinkedList<Receipt> list;
    String path;

    /*Get methods*/
    public int getQuantity() { return list.size(); }

    public Receipt getReceipt(String id) {
        int index = Integer.parseInt(id);
        if (index > list.size() || index < 1) {
            System.out.println("Cannot find id!");
            return null;
        }

        return list.get(index - 1);
    }

    public int findID(String id) {
        for (Receipt re : list) {
            if (re.getID().equals(id)) {
                return list.indexOf(re);
            }
        }

        return -1;
    }

    public Receipt getLast() {
        return list.getLast();
    }

    /*Set methods*/

    /*Other methods*/
    public void addReceipt(Receipt receipt) {
        list.add(receipt);
    }

    public void writeReceipt(Product product) {
        list.getLast().toInfo(product);
    }

    public String toString(String id) {
        return getReceipt(id).toString();
    }
}