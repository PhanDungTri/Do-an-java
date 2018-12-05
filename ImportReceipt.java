import java.util.LinkedList;

public class ImportReceipt extends Receipt
{
    /*Constructor*/
    ImportReceipt(String id, String staffID) {
        super(id, staffID);
        productInfo = "";
    }

    /*Members*/
    private String productInfo;

    /*Get methods*/
    public String getInfo() { return productInfo; }

    /*Set methods*/
    public void setInfo(String info) {
        productInfo = info;
    }

    /*Other methods*/
    @Override
    public void writeReceipt(Product product) {
        productInfo += productInfo.toString() + "\n";
    }

    @Override
    public String toString() {
        String str = super.toString();
        str += "=====Product Infomation=====\n";
        str += productInfo;
        return str;
    }

    @Override
    public String toData() {
        String str = super.toData();
        str += productInfo.replace(" ", "_") + "xDATASEPARATEx";

        return str;
    }

    public void getData(String[] str) {
        super.getData(str);
        setInfo(str[4].replace("_", " "));
    }
}