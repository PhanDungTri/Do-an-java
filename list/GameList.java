//import java.util.LinkedList;

public class GameList extends ProductList<Game>
{
    /*Constructor*/
    public GameList(PublisherList publisherList) {
        path = "./data/gamelist.bin";

        list = new LinkedList<Game>();
        this.publisherList = publisherList;

        FileIO.readFromFile(list, path, Game.class);
    }

    /*Other methods*/
    @Override
    public String toString() {
        String str;
        str = "\n**Game List**\n--------------------\n";
        str += String.format("|%-10s|%-20s|%-20s|%-5s|%-10s|%-5s|%-20s|%-10s|%-8s|",  
                             "ID", "Name", "Publisher", "Type", "Price", "Year", "Genre", "Platform", "Version");
        str += "\n--------------------\n";
        for (Game product : list)
        {
            str += product.toString();
        }

        return str;
    }

    public LinkedList<Game> findAllProductsByGenre(String name) {
        LinkedList<Game> foundList = new LinkedList<Game>();
        name = name.trim();
        name = name.replace(" ", "_");

        for (Game product : list)
            if (product.getGenre().toString().equalsIgnoreCase(name))
                foundList.add(product);

        return foundList;
    }

    public LinkedList<Game> findAllProductsByYear(int year) {
        LinkedList<Game> foundList = new LinkedList<Game>();

        for (Game product : list)
            if (product.getReleasedYear() == year)
                foundList.add(product);

        return foundList;
    }

    public LinkedList<Game> findAllProductsByPlatform(String name) {
        LinkedList<Game> foundList = new LinkedList<Game>();
        name = name.trim();
        name = name.replace(".", "");

        for (Game product : list)
            if (product.getPlatform().toString().equalsIgnoreCase(name))
                foundList.add(product);

        return foundList;
    }
}