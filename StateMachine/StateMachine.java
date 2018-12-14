import java.util.Stack;

public class StateMachine<T>
{
    /*Constructor*/
    public StateMachine(T owner) {
        this.owner = owner;
        stateStack = new LinkedList<State<T>>();
    }

    /*Members*/
    private T owner;
    private LinkedList<State<T>> stateStack;

    /*Get methods*/

    /*Set methods*/

    /*Other methods*/
    public void push(State<T> state) {
        if (state != null)
        {
            if (!stateStack.isEmpty())
                stateStack.getLast().exit(owner);
            stateStack.add(state);
            stateStack.getLast().enter(owner);
        }
        else
            System.out.println("Failed! Trying to change to a null state!");
    }

    public void pop(boolean skipEnter) {
        stateStack.pop().exit(owner);

        if (!stateStack.isEmpty() && skipEnter)
        stateStack.getLast().enter(owner);
    }

    public void pop() {
        pop(true);
    }

    public void update()
    {
        if (!stateStack.isEmpty())
            stateStack.getLast().execute(owner);;
    }

    public boolean isEmpty() {
        return stateStack.isEmpty();
    }
}