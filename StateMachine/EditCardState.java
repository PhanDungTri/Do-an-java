public class EditCardState implements State<Shop>
{
    /*Constructor - Singleton*/
    private EditCardState() {}

    /*Members*/
    private static EditCardState instance;

    /*Get methods*/
    public static EditCardState getInstance() {
        if (instance == null)
        {
            instance = new EditCardState();
        }
        
        return instance;
    }

    /*Other methods*/
    @Override
    public void enter(Shop owner) {
        System.out.print("\nInput Card's ID ('cancel' to go back): ");
    }

    @Override
    public void execute(Shop owner) {
        String input = Shop.scanner.nextLine();
        if (input.equalsIgnoreCase("cancel"))
        {
            owner.getStateMachine().pop();
        }
        else if(owner.getCardList().findProductByID(input)!=-1)
        {
            System.out.print("\n=== Card's infomation ===\n");
            System.out.print( owner.getCardList().getProduct(input).toString());
            int isDone = 1;
            System.out.print("\n=== Choose the option ===\n1. Name\n2. Publisher \n3. Price\n4. Value\nInput: ");
            
            do {
                isDone = 0;
                switch(Shop.scanner.nextInt())
                {
                    case 1:
                    {
                        Card card = owner.getCardList().getProduct(input);
                        Shop.scanner.nextLine();
                        String oldName = card.getName();
                        System.out.print("\nInput Name: ");
                        String name = Shop.scanner.nextLine();
                        for (Card product : owner.getCardList().list)
                        {
                            if(product.getID().equals(input))
                                    product.setName(name);
                        }

                        Publisher publisher = owner.getPublisherList().getPublisher(card.getPublisher());
                        publisher.removeTitle(oldName);
                        publisher.addTitle(name, input);

                        FileIO.rewriteFile(owner.getPublisherList().getList(), "./data/publisherlist.bin");
                        FileIO.rewriteFile(owner.getCardList().getList(),"./data/cardlist.bin");
                        enter(owner);
                        break;
                    }
                    case 2:
                    {
                        Shop.scanner.nextLine();
                        Card card = owner.getCardList().getProduct(input);
                        System.out.print("\nInput Pushlisher: ");
                        String pushlisher = Shop.scanner.nextLine();
                        String oldPublisher = card.getPublisher();

                        for (Card product : owner.getCardList().getList())
                        {
                            if(product.getID().equals(input))
                                product.setPublisher(pushlisher);
                        } 

                        PublisherList list = owner.getPublisherList();
                        
                        list.getPublisher(oldPublisher).removeTitle(card.getName());

                        if (list.findPublisher(pushlisher) != -1) {
                            list.getPublisher(pushlisher).addTitle(card.getName(), card.getID());
                        }
                        else {
                            list.addPublisher(pushlisher);
                            list.getPublisher(pushlisher).addTitle(card.getName(), card.getID());
                        }

                        FileIO.rewriteFile(owner.getPublisherList().getList(), "./data/publisherlist.bin");
                        FileIO.rewriteFile(owner.getCardList().getList(),"./data/cardlist.bin");
                        enter(owner);
                        break;
                    }
                    case 3:
                    {
                        Shop.scanner.nextLine();
                        System.out.print("\nInput Price: ");
                        int price = Shop.scanner.nextInt();
                        for (Card product : owner.getCardList().getList())
                        {
                            if(product.getID().equals(input))
                                    product.setPrice(price);
                        }          
                        FileIO.rewriteFile(owner.getCardList().getList(),"./data/cardlist.bin");
                        Shop.scanner.nextLine();
                        enter(owner);
                        break;
                    }
                    case 4:
                    {
                        Shop.scanner.nextLine();
                        System.out.print("\nInput value: ");
                        int value = Shop.scanner.nextInt();
                        for (Card product : owner.getCardList().getList())
                        {
                            if(product.getID().equals(input))
                                product.setValue(value);;
                        }          
                        FileIO.rewriteFile(owner.getCardList().getList(),"./data/cardlist.bin");
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
            System.out.print("\nThis came has not created ! Please input again: ");
        }
    }

    @Override
    public void exit(Shop owner) {}
}