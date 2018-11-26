import java.util.LinkedList;

public class Publisher implements IPrintable, IWritable
{
    /*Constructor*/
    public Publisher() {
        this.name = new String("");
        titleList = new LinkedList<String>();
    }
    
    public Publisher(String name) {
        this.name = new String("");
        this.name = name;
        titleList = new LinkedList<String>();
    }

    /*Members*/
    private String name;
    private LinkedList<String> titleList;

    /*Get methods*/
    public String getName() {
        return name;
    }

    public int getQuantity() {
        return titleList.size();
    }

    public int getTitleList() {
        return titleList;
    }

    /*Set methods*/
    public void setName(String name) {
        this.name = name;
    }

    /*Other methods*/
    public void addTitle(String name) {
        if (findTitle(name) != -1)
        {
            System.out.println("Title is already exist!");
            return;
        }

        titleList.add(name);
    }

    public void removeTitle(String name) {
        int index = findTitle(name);
        if (index == -1)
        {
            System.out.println("Cannot find title!");
            return;
        }

        titleList.remove(index);
    }

    public int findTitle(String name) {
        for (String str : titleList)
        {
            if (str.equals(name))
                return titleList.indexOf(str);
        }

        return -1;
    }

    public String toString() {
        String str;
        str = "\n----------\n" + 
               "Name: " + getName() + "\n" +
               "Title: ";
        
        for (String title : titleList)
        {
            str += title + "\t";
        }

        str += "\n----------\n";

        return str;
    }

    @Override
    public String toData() {
        String str = getName() + " ";
        for (String s : titleList)
        {
            str += s + " ";
        }
        str += "xDATASEPARATEx";

        return str;
    }

    @Override
    public void getData(String[] str) {
        setName(str[0]);
        for (int i = 1; i < str.length; ++i)
        {
            titleList.add(str[i]);
        }
    }
}