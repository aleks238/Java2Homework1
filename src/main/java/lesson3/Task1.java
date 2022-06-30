package lesson3;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Task1 {
    public static void main(String[] args) {

        String[] array = {"A", "A", "B", "C", "D", "E", "E","A", "W1", "G", "H1","A", "B","A"};

        Set<String> set = new HashSet<String>();
        
        removeDuplicate(array,set);

        countDuplicate (array);
    }
    private static void removeDuplicate(String[] array,Set<String> set) {
        for (String i : array) {
            set.add(i);
        }
        System.out.println("Уникальные элементы: "+set);
    }
    private static void countDuplicate(String[] array) {

        boolean visited[] = new boolean[array.length];
        Arrays.fill(visited,false);

        for (int i = 0; i < array.length; i++) {
            if (visited[i] == true){
                continue;
            }
            int count = 0;
            for (int j = i+1; j < array.length; j++) {
                if (array[i] == array[j]){
                    visited[j] =true;
                    count++;
                }
            }
            System.out.println(array[i] +"Повторяющиеся элементы: "+ count);
        }
    }
}

