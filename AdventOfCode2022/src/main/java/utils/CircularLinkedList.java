package utils;

import java.util.HashMap;

public class CircularLinkedList {
    public LinkedListItem head;
    public LinkedListItem tail;

    public HashMap<Integer, LinkedListItem> lookupTable;

    public CircularLinkedList() {
        this.head = null;
        this.tail = null;
        this.lookupTable = new HashMap<>();
    }

    public void addItem(Long value, Integer id) {
        LinkedListItem newItem = new LinkedListItem(value, id);
        if (head == null) {
            head = newItem;
            tail = head;
            head.setNext(head);
        } else {
            newItem.setPrevious(tail);
            tail.setNext(newItem);
            tail = newItem;
            tail.setNext(head);
            head.setPrevious(tail);
        }
        lookupTable.put(id, newItem);
    }

    public static class LinkedListItem {
        private LinkedListItem previous;
        private LinkedListItem next;
        private Long value;
        private final Integer id;

        public LinkedListItem(Long value, Integer id) {
            this.previous = null;
            this.next = null;
            this.value = value;
            this.id = id;
        }

        public LinkedListItem getPrevious() {
            return previous;
        }

        public void setPrevious(LinkedListItem previous) {
            this.previous = previous;
        }

        public LinkedListItem getNext() {
            return next;
        }

        public void setNext(LinkedListItem next) {
            this.next = next;
        }

        public Long getValue() {
            return value;
        }

        public void setValue(Long value) {
            this.value = value;
        }

        public Integer getId() {
            return id;
        }

        public void removeSelfFromList() {
            this.getPrevious().setNext(this.getNext());
            this.getNext().setPrevious(this.getPrevious());
        }

        public void insertSelfAfterItem(LinkedListItem item) {
            this.setNext(item.getNext());
            item.getNext().setPrevious(this);
            item.setNext(this);
            this.setPrevious(item);
        }

        public void move(Long value, Integer listSize) {
            long moves = Math.floorMod(value, listSize - 1);
            removeSelfFromList();
            LinkedListItem cur = this.getPrevious();
            for (int i = 0; i < moves; i++) {
                cur = cur.getNext();
            }
            insertSelfAfterItem(cur);
        }
    }
}
