package oop.generics;

public class Recap {

    static void doSomething(int a){
        System.out.println(a);
        a += 5;
        System.out.println(a);
    }

    static void doSomethingElse(int[] arr){
        System.out.println(arr[0]);
        arr[0]++;
        System.out.println(arr[0]);
    }
    public static void main(String[] args) {
        int[] arr = {5, 6};
        System.out.println(arr[0]);
        doSomethingElse(arr);
        System.out.println(arr[0]);
    }
}
