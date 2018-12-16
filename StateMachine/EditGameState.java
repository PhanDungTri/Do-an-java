public class EditGameState implements State<Shop>
{
    /*Constructor - Singleton*/
    private EditGameState() {}

    /*Members*/
    private static EditGameState instance;

    /*Get methods*/
    public static EditGameState getInstance() {
        if (instance == null)
        {
            instance = new EditGameState();
        }
        
        return instance;
    }

    /*Other methods*/
    @Override
    public void enter(Shop owner) {
        System.out.print("\nInput Game's ID ('cancel' to go back): ");
    }

    @Override
    public void execute(Shop owner) {
        String input = Shop.scanner.nextLine();
        if (input.equalsIgnoreCase("cancel"))
        {
            owner.getStateMachine().pop();
        }
        else if(owner.getGameList().findProductByID(input)!=-1)
        {
            System.out.print("\n=== Game's infomation ===\n");
            System.out.print( owner.getGameList().getProduct(input).toString());
            int isDone = 1;
            System.out.print("\n=== Choose the option ===\n1. Name\n2. Publisher \n3. Price\n4. Released Year\n5. Genre\n6. Version \n7. Platform\nInput: ");
            
            do {
                isDone = 0;
                switch(Shop.scanner.nextInt())
                {
                    case 1:
                    {
                        Game game = owner.getGameList().getProduct(input);
                        Shop.scanner.nextLine();
                        String oldName = game.getName();
                        System.out.print("\nInput Name: ");
                        String name = Shop.scanner.nextLine();
                        for (Game product : owner.getGameList().list)
                        {
                            if(product.getID().equals(input))
                                    product.setName(name);
                        }

                        Publisher publisher = owner.getPublisherList().getPublisher(game.getPublisher());
                        publisher.removeTitle(oldName);
                        publisher.addTitle(name, input);

                        FileIO.rewriteFile(owner.getPublisherList().getList(), "./data/publisherlist.bin");
                        FileIO.rewriteFile(owner.getGameList().getList(),"./data/gamelist.bin");
                        enter(owner);
                        break;
                    }
                    case 2:
                    {
                        Shop.scanner.nextLine();
                        Game game = owner.getGameList().getProduct(input);
                        System.out.print("\nInput Pushlisher: ");
                        String pushlisher = Shop.scanner.nextLine();
                        String oldPublisher = game.getPublisher();

                        for (Game product : owner.getGameList().getList())
                        {
                            if(product.getID().equals(input))
                                product.setPublisher(pushlisher);
                        } 

                        PublisherList list = owner.getPublisherList();
                        
                        list.getPublisher(oldPublisher).removeTitle(game.getName());

                        if (list.findPublisher(pushlisher) != -1) {
                            list.getPublisher(pushlisher).addTitle(game.getName(), game.getID());
                        }
                        else {
                            list.addPublisher(pushlisher);
                            list.getPublisher(pushlisher).addTitle(game.getName(), game.getID());
                        }

                        FileIO.rewriteFile(owner.getPublisherList().getList(), "./data/publisherlist.bin");
                        FileIO.rewriteFile(owner.getGameList().getList(),"./data/gamelist.bin");
                        enter(owner);
                        break;
                    }
                    case 3:
                    {
                        Shop.scanner.nextLine();
                        System.out.print("\nInput Price: ");
                        int price = Shop.scanner.nextInt();
                        for (Game product : owner.getGameList().getList())
                        {
                            if(product.getID().equals(input))
                                    product.setPrice(price);
                        }          
                        FileIO.rewriteFile(owner.getGameList().getList(),"./data/gamelist.bin");
                        Shop.scanner.nextLine();
                        enter(owner);
                        break;
                    }
                    case 4:
                    {
                        Shop.scanner.nextLine();
                        System.out.print("\nInput Release Year: ");
                        int releaseyear = Shop.scanner.nextInt();
                        for (Game product : owner.getGameList().getList())
                        {
                            if(product.getID().equals(input))
                                product.setReleasedYear(releaseyear);
                        }          
                        FileIO.rewriteFile(owner.getGameList().getList(),"./data/gamelist.bin");
                        Shop.scanner.nextLine();
                        enter(owner);
                        break;
                    }
                    case 5:
                    {
                        Shop.scanner.nextLine();
                        int check=0;
                        while (check == 0)
                        {
                            check = 1;
                            System.out.print("1. Action\n2. Action-adventure\n3. Adventure\n4. Simulation\n5. Strategy\n6. Sports\n7. Role-playing\n");
                            System.out.print("Choose game genre: ");
                            switch (Shop.scanner.nextInt())
                            {
                                case 1:
                                    for (Game product : owner.getGameList().getList())
                                    {
                                        if(product.getID().equals(input))
                                        product.setGenre(Game.Genre.Action);
                                    }         
                                    break;
                                case 2:
                                    for (Game product : owner.getGameList().getList())
                                    {
                                        if(product.getID().equals(input))
                                        product.setGenre(Game.Genre.Action_adventure);
                                    }  
                                    break; 
                                case 3:
                                for (Game product : owner.getGameList().getList())
                                    {
                                        if(product.getID().equals(input))
                                        product.setGenre(Game.Genre.Adventure);
                                    }   
                                    break;
                                case 4:
                                    for (Game product : owner.getGameList().getList())
                                    {
                                        if(product.getID().equals(input))
                                        product.setGenre(Game.Genre.Simulation);
                                    }   
                                    break;
                                case 5:
                                    for (Game product : owner.getGameList().getList())
                                    {
                                        if(product.getID().equals(input))
                                        product.setGenre(Game.Genre.Strategy);
                                    } 
                                    break;  
                                case 6:
                                    for (Game product : owner.getGameList().getList())
                                    {
                                    if(product.getID().equals(input))
                                    product.setGenre(Game.Genre.Sports);
                                    }   
                                    break;
                                case 7:
                                    for (Game product : owner.getGameList().getList())
                                    {
                                    if(product.getID().equals(input))
                                    product.setGenre(Game.Genre.Role_playing);
                                    }   
                                    break;
                                default: 
                                    check = 0;
                                    break;
                            }
                        }
                        FileIO.rewriteFile(owner.getGameList().getList(),"./data/gamelist.bin");
                        Shop.scanner.nextLine();
                        enter(owner);
                        break;
                    }
                    case 6:
                    {
                        Shop.scanner.nextLine();
                        System.out.print("\nInput version: ");
                        String version = Shop.scanner.nextLine();
                        for (Game product : owner.getGameList().getList())
                        {
                            if(product.getID().equals(input))
                                product.setVersion(version);
                        }    

                        FileIO.rewriteFile(owner.getGameList().getList(),"./data/gamelist.bin");
                        enter(owner);
                        break;
                    }
                    case 7:
                    {
                        Shop.scanner.nextLine();
                        int check=0;
                        while (check == 0)
                        {
                            check = 1;
                            System.out.print("1. Steam\n2. Origin\n3. BattleNet\n");
                            System.out.print("Choose platform: ");
                            switch (Shop.scanner.nextInt())
                            {
                                case 1:
                                    for (Game product : owner.getGameList().getList())
                                    {
                                        if(product.getID().equals(input))
                                        product.setPlatform(Game.Platform.Steam);
                                    }         
                                    break;
                                case 2:
                                    for (Game product : owner.getGameList().getList())
                                    {
                                        if(product.getID().equals(input))
                                        product.setPlatform(Game.Platform.Origin);
                                    }  
                                    break; 
                                case 3:
                                    for (Game product : owner.getGameList().getList())
                                    {
                                        if(product.getID().equals(input))
                                        product.setPlatform(Game.Platform.BattleNet);
                                    }   
                                    break;
                                default: 
                                    check = 0;
                                    break;
                            }
                        }
                        FileIO.rewriteFile(owner.getGameList().getList(),"./data/gamelist.bin");
                        Shop.scanner.nextLine();
                        enter(owner);
                        break;
                    }
                    default: 
                    {
                        System.out.print("Invalid option! Please input: ");
                        isDone = 1;
                        break;
                    }
                }
            } while (isDone == 1);
        }
        else
        {
            System.out.print("\nThis game has not created ! Please input again: ");
        }
    }

    @Override
    public void exit(Shop owner) {}
}