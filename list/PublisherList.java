//import java.util.LinkedList;

public class PublisherList
{
    /*Constructor*/
    PublisherList() {
        path = "./data/publisherlist.bin";
        list = new LinkedList<Publisher>();

        FileIO.readFromFile(list, path, Publisher.class);
    }
    
    /*Members*/
    private LinkedList<Publisher> list;
    public String path;

    /*Get methods*/
    public Publisher getPublisher(String name) {
        int index = findPublisher(name);
        if (index == -1)
        {
            System.out.println("Cannot find publisher!");
            return null;
        }

        return list.get(index);
    }

    public int getQuantity() {
        return list.size();
    }

    public int getQuantity(String name) {
        return getPublisher(name).getQuantity();
    }
    
    /*Other methods*/
    public void addPublisher(String name) {
        int index = findPublisher(name);
        if (index != -1)
        {
            System.out.println("Publisher has already existed!");
            return;
        }
        
        Publisher publisher = new Publisher(name);
        list.add(publisher);
    }

    public void removePublisher(String name) {
        int index = findPublisher(name);
        if (index != -1)
        {
            System.out.println("Publisher has already existed!");
            return;
        }

        list.remove(index);
        
        FileIO.rewriteFile(list, path);
    }

    public int findPublisher(String name) { 
        for (Publisher publisher : list) 
            if (publisher.getName().equalsIgnoreCase(name))
                return list.indexOf(publisher);
        
        return -1;
    }

    public boolean checkPublisherList(Product product) {
        String publisher = product.getPublisher();
        String name = product.getName();
        String id = product.getID();
        if (findPublisher(publisher) == -1) 
        {
            System.out.println("This is new publisher! Adding to the list!");
            addPublisher(publisher);
            getPublisher(publisher).addTitle(name, id);
            FileIO.rewriteFile(list, path);
            return true;
        }
        else
        {
            Publisher existPublisher = getPublisher(publisher);

            if (existPublisher.find(name, id) != -1)
            {
                return true;
            }
            else if (existPublisher.findID(id) == -1 && existPublisher.findName(name) == -1)
            {
                existPublisher.addTitle(product.getName(), product.getID());
                FileIO.rewriteFile(list, path);
                return true;
            }
            else {
                System.out.println("Invalid ID or product name - The ID or Name has existed and it has another Name or ID");
                return false;
            }
        }
    }
}