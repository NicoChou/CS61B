public class ArrayDeque<Item> implements DequeAPI<Item>{
    private Item[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private static int RFACTOR = 2;

    public ArrayDeque(){
        size = 0;
        items = (Item[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;

    }

    private void resize(int capacity){
        Item[] a = (Item[]) new Object[capacity];
        int index = calNextLastIndex(nextFirst);
        for (int i =0; i < size ; i++){
            System.arraycopy(items,index,a,index,1);
            index = calNextLastIndex(index);
        }
        items = a;
    }
    public void addFirst(Item x){
        if(size == items.length){
            resize(size * RFACTOR);
        }
        items[nextFirst] = x;
        nextFirst = calNextFirstIndex(nextFirst);
        size += 1;
    }

    public int calNextFirstIndex(int index){
        int indexToReturn = index;
        if(indexToReturn == 0)
            indexToReturn = items.length;
        else
            indexToReturn = indexToReturn - 1;
        return indexToReturn;
    }

    public int calNextLastIndex(int index){
        int indexToReturn = index;
        indexToReturn = (indexToReturn + 1) % items.length;
        return indexToReturn;
    }
    public void addLast(Item x){
        if(size == items.length){
            resize(size * RFACTOR );
        }
        items[nextLast] = x;
        nextLast = calNextLastIndex(nextLast);
        size += 1;
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
        for(int i = 0; i < size; i++){
            System.out.print(get(i) + " ");
        }
    }
    public Item removeFirst(){
        int index = calNextLastIndex(nextFirst);
        Item itemToReturn = items[index];
        items[index] = null;
        size -= 1;
        return  itemToReturn;
    }

    public Item removeLast(){
        int index = calNextFirstIndex(nextLast);
        Item itemToReturn = items[index];
        items[index] = null;
        size -= 1;
        return itemToReturn;
    }
    public Item get(int index){
        return items[(nextFirst + index) % items.length];
    }


}