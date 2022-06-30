package lesson3;

import java.util.HashMap;
import java.util.Map;

public class Task2 {
    public static void main(String[] args) {

        HashMap<String, String> phoneBook = new HashMap<String, String>();

        phoneBook.put("42384", "Ivanov");
        phoneBook.put("85814", "Trofimov");
        phoneBook.put("18488", "Baskov");

        try {
            addPerson(phoneBook, "84841", "Eminem");
        } catch (NumberAlreadyAddedException e ){
            System.out.println("Исключение: " + e);
        }

        getPhoneNumber(phoneBook, "Ivanov");
    }
    private static void addPerson(HashMap<String, String> phoneBook, String phoneNumber, String name) throws NumberAlreadyAddedException {
        for (Map.Entry entry : phoneBook.entrySet()) {
            if (entry.getKey() == phoneNumber) {
                System.out.println("Номер телефона: " + phoneNumber + " уже имеется в телефонной книге");
            }
        }
        phoneBook.put(phoneNumber, name);
        System.out.println(name + " (номер телефона: " + phoneNumber+ ")"+" добавлен в телефонную книгу");
    }
    private static void getPhoneNumber(HashMap<String, String> phoneBook, String name) {
        for (Map.Entry entry : phoneBook.entrySet()) {
            if (entry.getValue() == name) {
                System.out.println("Номер телефона " + name + ": " + entry.getKey());
            }
        }
    }
}
