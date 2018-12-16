public class Cart {
    /*Constructor*/
    public Cart() {
        cart = new LinkedList<String>();
        totalPrice = 0;
    }
    /*Members*/
    LinkedList<String> cart;
    int totalPrice;

    /*Get Methods*/
    public LinkedList<String> getCart() { return cart; } 
    public int getTotalPrice() { return totalPrice; }
    public int getQuantity() { return cart.size(); }

    /*Set Methods*/
    public void setPrice(int price) { totalPrice += price; }

    /*Other Methods*/
    public void addProduct(String id, Product.Type type, int quantity) {
        if (quantity == 0) { return; }

        int index = find(id, type);
        if (index != -1) {
            String[] str = cart.get(index).split(" ");
            str[2] = Integer.toString(Integer.parseInt(str[2]) + quantity);
            cart.remove(cart.get(index));
            cart.add(str[0] + " " + str[1] + " " + str[2]);
        }
        else {
            cart.add(id + " " + type + " " + quantity);
        }
    }

    public void removeProduct(String id, Product.Type type, int quantity) {
        if (quantity == 0) { return; }
        int index = find(id, type);
        if (index != -1) {
            String[] str = cart.get(index).split(" ");
            str[2] = Integer.toString(Integer.parseInt(str[2]) - quantity);

            if (Integer.parseInt(str[2]) <= 0) { 
                cart.remove(cart.get(index));
                return;
            }

            cart.remove(cart.get(index));
            cart.add(str[0] + " " + str[1] + " " + str[2]);
        }
        else {
            System.out.println("Product is not in the cart! Please re-check!");
        }
    } 

    public int find(String id, Product.Type type) {
        for (String str : cart) {
            String[] s = str.split(" ");
            if (s[0].equals(id) && s[1].equalsIgnoreCase(type.toString())) {
                return cart.indexOf(str);
            }
        }

        return -1;
    }

    public void clear() {
        cart.clear();
    }
}