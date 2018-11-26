import java.nio.file.Paths;
import java.util.Scanner;

public class Shop
{
    /*Constructor*/
    public Shop()
    {
        publisherList = new PublisherList();
        gameList = new GameList(publisherList);
        cardList = new CardList(publisherList);
        stateMachine = new StateMachine<Shop>(this);
        stateMachine.push(MainMenuState.getInstance());
    }
    
    /*Members*/
    PublisherList publisherList;
    GameList gameList;
    CardList cardList;
    StateMachine<Shop> stateMachine;

    public static Scanner scanner = new Scanner(System.in);

    /*Get methods*/
    public GameList getGameList() { return gameList; }
    public CardList getCardList() { return cardList; }
    public PublisherList getPublisherList() { return publisherList; }
    public StateMachine<Shop> getStateMachine() { return stateMachine; }

    /*Set methods*/
        
    /*Other methods*/
    public void update()
    {
        stateMachine.update();
    }

    /*Main method*/
    public static void main(String[] args)
    {
        Shop shop = new Shop();

        while(!shop.getStateMachine().isEmpty())
        {
            shop.update();
        }

        scanner.close();
    }
}