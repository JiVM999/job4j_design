package ru.job4j.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class UserStore {
    public static void main(String[] args) {
        Calendar birthday = Calendar.getInstance();
        User userLeha = new User("Леха", 0, birthday);
        User userVova = new User("Леха", 0, birthday);
        Map<User, Object> map = new HashMap<>();

        int hashCode1 = userLeha.hashCode();
        int hash1 = hashCode1 ^ (hashCode1 >>> 16);
        int bucket1 = hash1 & 15;

        int hashCode2 = userVova.hashCode();
        int hash2 = hashCode2 ^ (hashCode2 >>> 16);
        int bucket2 = hash2 & 15;

        map.put(userLeha, new Object());
        map.put(userVova, new Object());

        System.out.println("хешкод: " + hashCode1 + " хеш: " + hash1 + " бакет: " + bucket1);
        System.out.println("хешкод: " + hashCode2 + " хеш: " + hash2 + " бакет: " + bucket2);

        for (Map.Entry<User, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
