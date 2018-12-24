package test;

import java.util.*;

/**
 * Created by zizhengli on 9/18/18.
 */
public class SortingTest {

    static List<Integer> intersection(List<Integer> A, List<Integer> B) {
        if(A == null || A.size() == 0) {
            return B;
        } else if(B == null || B.size() == 0) {
            return A;
        }

        int idxA = 0;
        int idxB = 0;
        List<Integer> result = new ArrayList<>();

        while(idxA < A.size() && idxB < B.size()) {
            if(A.get(idxA) > B.get(idxB)) {
                idxB++;
            } else if(A.get(idxA) < B.get(idxB)) {
                idxA++;
            } else {
                result.add(A.get(idxA));
                idxA++;
                idxB++;
            }

            while(idxA > 0 && idxA < A.size() && A.get(idxA) == A.get(idxA - 1)) {
                idxA++;
            }
            while(idxB > 0 && idxB < B.size() && B.get(idxB) == B.get(idxB - 1)) {
                idxB++;
            }
        }
        return result;
    }

    private static class Person {
        int age;
        String name;

        Person(int age, String name) {
            this.age = age;
            this.name = name;
        }
    }

    static void groupByAge(List<Person> people) {
        Map<Integer, Integer> ageCount = new HashMap<>();
        for(Person p : people) {
            if(ageCount.containsKey(p.age)) {
                ageCount.put(p.age, ageCount.get(p.age) + 1);
            } else {
                ageCount.put(p.age, 1);
            }
        }

        Map<Integer, Integer> ageOffset = new HashMap<>();
        Integer value = 0;
        for(Integer a : ageCount.keySet()) {
            ageOffset.put(a, value);
            value += ageCount.get(a);
        }

        while(!ageOffset.isEmpty()) {
            Map.Entry<Integer, Integer> from = ageOffset.entrySet().iterator().next();
            Integer toAge = people.get(from.getValue()).age;
            Integer toValue = ageOffset.get(toAge);

            System.out.println(from.getValue() + " " + toValue);
            Collections.swap(people, from.getValue(), toValue);

            Integer count = ageCount.get(toAge) - 1;
            ageCount.put(toAge, count);

            if(count > 0) {
                ageOffset.put(toAge, toValue + 1);
            } else {
                ageOffset.remove(toAge);
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> A = new ArrayList<>(Arrays.asList(2, 3, 3, 5, 5, 6, 7, 7, 8, 12));
        List<Integer> B = new ArrayList<>(Arrays.asList(5, 5, 6, 8, 8, 9, 10, 10));
        List<Integer> result = intersection(A, B);

        for(Integer i : result) {
            System.out.print(i + " ");
        }

        List<Person> people = Arrays.asList(new Person(10, "Tim"), new Person(14, "Grep"),
                                            new Person(12, "John"),
                                            new Person(11, "Andy"),
                                            new Person(13, "Jim"),
                                            new Person(12, "Phil"),
                                            new Person(13, "Bob"),
                                            new Person(13, "Chip"),
                                            new Person(14, "Tim"),
                                            new Person(10, "Tim"));
        groupByAge(people);
        for(Person p : people) {
            System.out.print(p.age + " ");
        }
    }
}
