import java.util.*;

public class LinkedList<AnyType> implements Iterable<AnyType>
{
   private Node<AnyType> head;
   private int size;

   public LinkedList()
   {
      head = null;
      size = 0;
   }

   public int size() {
      return size;
   }

   public boolean isEmpty()
   {
      return head == null;
   }

   public void addFirst(AnyType item)
   {
      head = new Node<AnyType>(item, head);
      ++size;
   }

   public void add(AnyType item)
   {
      if( head == null)
         addFirst(item);
      else
      {
         Node<AnyType> tmp = head;
         while(tmp.next != null) tmp = tmp.next;

         tmp.next = new Node<AnyType>(item, null);
         ++size;
      }
   }

   public void clear()
   {
      head = null;
      size = 0;
   }

   public boolean contains(AnyType x)
   {
      for(AnyType tmp : this)
         if(tmp.equals(x)) return true;

      return false;
   }

   public AnyType get(int pos)
   {
      if (head == null) throw new IndexOutOfBoundsException();

      Node<AnyType> tmp = head;
      for (int k = 0; k < pos; k++) tmp = tmp.next;

      if (tmp == null) throw new IndexOutOfBoundsException();

      return tmp.data;
   }

   public AnyType getLast()
   {
      if(head == null) throw new NoSuchElementException();

      Node<AnyType> tmp = head;
      while(tmp.next != null) tmp = tmp.next;

      return tmp.data;
   }

   public int indexOf(AnyType key) {
      if( head.data.equals(key) )
      {
         return 0;
      }

      Node<AnyType> cur  = head;
      int i = 0;

      while(cur != null && !cur.data.equals(key) )
      {
         cur = cur.next;
         ++i;
      }

      if(cur == null)
        return -1;

      return i;
   }

   public void remove(AnyType key)
   {
      if(head == null)
         throw new RuntimeException("cannot delete");

      if( head.data.equals(key) )
      {
         head = head.next;
         --size;
         return;
      }

      Node<AnyType> cur  = head;
      Node<AnyType> prev = null;

      while(cur != null && !cur.data.equals(key) )
      {
         prev = cur;
         cur = cur.next;
      }

      if(cur == null)
         throw new RuntimeException("cannot delete");

      //delete cur node
      prev.next = cur.next;
      --size;
   }

   public void remove(int index) {
      remove(get(index));
   }

   private static class Node<AnyType>
   {
      private AnyType data;
      private Node<AnyType> next;

      public Node(AnyType data, Node<AnyType> next)
      {
         this.data = data;
         this.next = next;
      }
   }

   public Iterator<AnyType> iterator()
   {
      return new LinkedListIterator();
   }

   private class LinkedListIterator  implements Iterator<AnyType>
   {
      private Node<AnyType> nextNode;

      public LinkedListIterator()
      {
         nextNode = head;
      }

      public boolean hasNext()
      {
         return nextNode != null;
      }

      public AnyType next()
      {
         if (!hasNext()) throw new NoSuchElementException();
         AnyType res = nextNode.data;
         nextNode = nextNode.next;
         return res;
      }

      public void remove() { throw new UnsupportedOperationException(); }
   }
}