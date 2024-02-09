/**
 * SegregateEvenOddNodesLL
 */
public class SegregateEvenOddNodesLL {

    public class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            next = null;
        }
    }

    public class LinkedList {
        Node head;

        public void append(int data) {
            Node newNode = new Node(data);
            if (head == null) {
                head = newNode;
                return;
            }
            Node last = head;
            while (last.next != null) {
                last = last.next;
            }
            last.next = newNode;
        }

        public void segregateEvenOdd() {
            Node evenStart = null;
            Node evenEnd = null;
            Node oddStart = null;
            Node oddEnd = null;
            Node currentNode = head;
            while (currentNode != null) {
                int element = currentNode.data;
                if (element % 2 == 0) {
                    if (evenStart == null) {
                        evenStart = currentNode;
                        evenEnd = evenStart;
                    } else {
                        evenEnd.next = currentNode;
                        evenEnd = evenEnd.next;
                    }
                } else {
                    if (oddStart == null) {
                        oddStart = currentNode;
                        oddEnd = oddStart;
                    } else {
                        oddEnd.next = currentNode;
                        oddEnd = oddEnd.next;
                    }
                }
                currentNode = currentNode.next;
            }
            if (oddStart == null || evenStart == null) {
                return;
            }
            evenEnd.next = oddStart;
            oddEnd.next = null;
            head = evenStart;
        }
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.append(17);
        list.append(15);
        list.append(8);
        list.append(12);
        list.append(10);
        list.append(5);
        list.append(4);
        list.append(1);
        list.segregateEvenOdd();

    }

}