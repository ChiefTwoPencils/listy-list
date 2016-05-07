package me.twopencils.list;

import java.util.stream.IntStream;

/**
 * Created by Robert Wilk
 * on 5/6/2016.
 */
public class List<T extends Comparable<T>> {

    ListNode head;

    private class ListNode {
        ListNode previous, next;
        T data;

        ListNode(ListNode previous, ListNode next, T data) {
            this.previous = previous;
            this.next = next;
            this.data = data;
        }
    }

    public void insert(T data) {
        if (data == null)
            throw new IllegalArgumentException();
        ListNode node = new ListNode(null, null, data);
        if (head != null)
            head.previous = node;
        node.next = head;
        head = node;
    }

    public void remove(T data) {
        // There's no point in searching for a null.
        if (data != null)
            // Remove the data if possible; rebuild the list.
            // Attach the rebuilt list to the head.
            head = remove(head, data);
    }

    private ListNode remove(ListNode current, T data) {
        if (current == null)
            // We're at the end of the list and it wasn't found.
            // Rebuild the list as it was.
            return null;
        if (current.data.compareTo(data) == 0)
            // We have our match; delete it
            return current.next;
        // No match was found; try with the next node in the list.
        // Attach the newly formed list to current.
        current.next = remove(current.next, data);
        // Insert current back into the list.
        return current;
    }

    public static void main(String[] args) {
        final int MANY = 10000;
        List<Integer> list = new List<>();
        IntStream.range(0, MANY).forEach(list::insert);
        System.out.println("Inserted " + MANY + " integers into the list.");
        list.remove(0);
        System.out.println("Done removing the deepest node (a.k.a. 0).");
    }
}
