package oop.generics;

import java.util.ArrayList;

public class Basics {

    static <E> void  printArray(E[] array){
        System.out.println();
        for(E type : array){
            System.out.print(type + " ");
        }
        System.out.println();
    }

    public static <T extends Comparable<T> > void sort(T[] data) {
        int n = data.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
//                if (data[j] > data[j + 1]) {
                if (data[j].compareTo(data[j + 1]) > 0) {
                    T temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        //built-in
//        ArrayList<String> strings;
//        ArrayList<Integer> numbers;
//        ArrayList<Double> realNumbers;
        Integer[] numbers = {1, 6, 98, 53, 0, 12};
        String[] words = {"delta", "alpha", "beta", "gamma"};
        Double[] realNumbers = {Math.PI, -Math.sqrt(17),.5, 4.0};

        printArray(words);
        printArray(numbers);
        printArray(realNumbers);

        sort(numbers);
        sort(realNumbers);
        sort(words);

        printArray(words);
        printArray(numbers);
        printArray(realNumbers);
    }
}