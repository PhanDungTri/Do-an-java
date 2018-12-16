//import java.util.LinkedList;

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

    public LinkedList<String> getTitleList() {
        return titleList;
    }

    /*Set methods*/
    public void setName(String name) {
        this.name = name;
    }

    /*Other methods*/
    public void addTitle(String name, String id) {
        if (find(name, id) != -1)
        {
            System.out.println("Title or ID is already exist!");
            return;
        }
        String info = id + "::" + name;
        titleList.add(info);
    }

    public void removeTitle(String name) {
        int index = findName(name);
        if (index == -1)
        {
            System.out.println("Cannot find title!");
            return;
        }

        titleList.remove(index);
    }

    public int findName(String name) {
        for (String str : titleList)
        {
            String[] s = str.split("::");
            if (s[1].equals(name))
                return titleList.indexOf(str);
        }

        return -1;
    }

    public int findID(String id) {
        for (String str : titleList)
        {
            String[] s = str.split("::");
            if (s[0].equals(id))
                return titleList.indexOf(str);
        }

        return -1;
    }

    public int find(String name, String id) {
        for (String str : titleList) {
            String[] s = str.split("::");
            if (s[1].equals(name) && s[0].equals(id)) {
                return titleList.indexOf(str);
            }
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
        String str = getName().replace(" ", "_") + " ";
        for (String s : titleList)
        {
            str += s.replace(" ", "_") + " ";
        }
        str += "xDATASEPARATEx";

        return str;
    }

    @Override
    public void getData(String[] str) {
        setName(str[0].replace("_", " "));
        for (int i = 1; i < str.length; ++i)
        {
            titleList.add(str[i].replace("_", " "));
        }
    }
}