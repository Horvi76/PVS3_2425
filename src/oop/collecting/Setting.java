package oop.collecting;

import java.util.*;

public class Setting {

    public static void main(String[] args) {
        int[] array = new int[100];


        for (int i = 0; i < array.length; i++) {
            array[i] = (int)(Math.random()*121);
        }

        System.out.println(Arrays.toString(array));

        Set<Integer> uniqueNumbers = new HashSet<>();
        uniqueNumbers.add(5);
        uniqueNumbers.add(5);
        uniqueNumbers.add(5);
        uniqueNumbers.add(5);
        System.out.println(uniqueNumbers);

        //vygeneruj 100 nahodnych cisel uniq
        while (uniqueNumbers.size() < 100){
            uniqueNumbers.add((int)(Math.random()*121));
        }
        System.out.println(uniqueNumbers);

        //je neco v setu? a)
        System.out.println(uniqueNumbers.contains(6));

        //zkus pridat, nevyjde --> uz to tam je
        System.out.println(uniqueNumbers.add(6));

        ArrayList<Integer> trulyUniqueNumbers = new ArrayList<>(uniqueNumbers);
        System.out.println(trulyUniqueNumbers);


        //1 vytvorit arraylist, random cisla (0-199)
        //2 zjistit, kolik jich bylo unique
        ArrayList<Integer> subsetNumbers = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            subsetNumbers.add((int)(Math.random()*200));
        }
        System.out.println(subsetNumbers);

        HashSet<Integer> subSet = new HashSet<>(subsetNumbers);
        System.out.println(subSet);
        //kolik cisel je unikatnich:
        System.out.println(subSet.size());

        //kolikrat tam byly non-unikatni
        System.out.println(subsetNumbers.size() - subSet.size());

    }
}
