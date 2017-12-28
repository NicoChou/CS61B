public class LinkedListDeque<Blorp> implements DequeAPI<Blorp> {
    public class Node {
        public Blorp item;
        public Node prev;
        public Node next;

        public Node(Blorp i, Node one, Node two) {
            item = i;
            prev = one;
            next = two;
        }
    }
        private Node sentinel;
        private int size;

        public LinkedListDeque(){
            size = 0;
            sentinel = new Node(null,null,null);
            sentinel.prev = sentinel;
            sentinel.next = sentinel;

        }

        public void addFirst(Blorp x){
            Node oldFrontNode = sentinel.next;
            Node newNode = new Node(x,sentinel,oldFrontNode);
            sentinel.next = newNode;
            newNode.next.prev = newNode;
            size += 1;
        }

        public void addLast(Blorp x){
            Node oldLastNode = sentinel.prev;
            Node newNode = new Node(x, oldLastNode, sentinel);
            sentinel.prev = newNode;
            newNode.prev.next = newNode;
            size += 1;
        }

        public Blorp removeFirst(){
            if(size == 0)
                return null;
            Node first = sentinel.next;
            sentinel.next = first.next;
            first.next.prev = sentinel;
            size -= 1;
            return first.item;
        }

        public Blorp removeLast(){
            if(size == 0)
                return null;
            Node last = sentinel.prev;
            sentinel.prev = last.prev;
            last.prev.next = sentinel;
            size -= 1;
            return last.item;
        }

        public Blorp get(int index){
            if (index >= size)
                return null;
            int i = 0;
            Blorp ret = sentinel.next.item;
            for (Node p = sentinel.next; p != sentinel; p = p.next, i++){
                if (i == index) {
                    ret = p.item;
                    break;
                }
            }
            if(i == index)
                return ret;
            else
                return null;
        }

        public boolean isEmpty(){
            if(size == 0)
                return true;
            else
                return false;
        }

        public int size(){
            return size;
        }

        public void printDeque(){
            for (Node p = sentinel.next; p!= sentinel; p = p.next){
                System.out.print(p.item + " ");
            }
        }

        public Blorp getRecursive(int index){
            if (index >= size)
                return null;
            if (index == 0)
                return sentinel.next.item;
            else{
                index = index - 1;
                LinkedListDeque ld = LinkedListDeque.this;
                ld.removeFirst();
                return (Blorp) ld.getRecursive(index);
            }
        }

//        public static void main(String args[]){
//        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();
//        lld1.addFirst("test");
//        lld1.printDeque();
//    }

}