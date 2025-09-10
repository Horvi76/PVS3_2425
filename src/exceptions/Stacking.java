package exceptions;

import java.util.EmptyStackException;

public class Stacking {
    public static void main(String[] args) {

    }

}
class ArrayStack{
    private int[] data;
    private int freeIndex;

    void expandArray(){
        int[] newOne = new int[data.length * 2];
//        int[] newOne = new int[data.length + 1];
        System.arraycopy(data, 0, newOne, 0, data.length);
        data = newOne;
    }

    ArrayStack(int capacity){
        data = new int[capacity];
        freeIndex = 0;
    }

    void push(int toAdd){
        if (freeIndex < data.length){
            data[freeIndex] = toAdd;
            freeIndex++;
        } else {
            System.out.println("Out of capacity :(");
        }
    }

    int pop(){
        if (freeIndex > 0){
            int toReturn = data[freeIndex-1];
            freeIndex--;
            return toReturn;
//            return data[freeIndex--];
        }
       throw new EmptyStackException();
    }

    int peek() {
        if (freeIndex > 0)
            return data[freeIndex-1];
        throw new EmptyStackException();
    }
}
