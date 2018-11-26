public class ViewPublisherState implements State<Shop>
{
    /*Constructor*/
    private ViewPublisherState() {}

    /*Member*/
    private static ViewPublisherState instance;

    /*Get methods*/
    public static ViewPublisherState getInstance() {
        if (instance == null)
        {
            instance = new ViewPublisherState();
        }

        return instance;
    }

    /*Other methods*/
    @Override
    public void enter(Shop owner) {
        System.out.print("Input publisher name ('cancel' to go back): ");
    }

    @Override
    public void execute(Shop owner) {
        String str = Shop.scanner.nextLine();
        if (str.equalsIgnoreCase("cancel"))
        {
            owner.getStateMachine().pop();
        }
        else
        {
            System.out.println(owner.getPublisherList().getPublisher(str));
            enter(owner);
        }
    }

    @Override
    public void exit(Shop owner) {}
}