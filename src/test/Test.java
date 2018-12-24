package test;

import javax.xml.stream.events.Characters;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Test {


    static String[] map = {"", "", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};

    static void getPhoneNumbers(String number, int index, char[] chars, List<String> result) {
        if(index == number.length()) {
            result.add(new String(chars));
            return;
        }

        for(int i = 0; i < map[number.charAt(index) - '0'].length(); i++) {
            char c = map[number.charAt(index) - '0'].charAt(i);
            chars[index] = c;
            getPhoneNumbers(number, index + 1, chars, result);
        }
    }

    static void getIPAddress(String ip, int index, int numRecur, String newAddr, List<String> addrs) {
        if(numRecur == 4) {
            String last = ip.substring(index, ip.length());
            if(isValid(last)) {
                addrs.add(newAddr + last);
            }
            return;
        }

        for(int i = 1; i <= 3; i++) {
            if(index + i > ip.length()) {
                continue;
            }
            String s = ip.substring(index, index + i);
            if(!isValid(s)) {
                continue;
            }
            getIPAddress(ip, index + i, numRecur + 1, newAddr + s + ".", addrs);
        }
    }

    static boolean isValid(String s) {
        if(s == null || s.length() == 0) {
            return false;
        }
        if(s.startsWith("0") && s.length() > 1) {
            return false;
        }
        if(s.length() > 3) {
            return false;
        }
        int num = Integer.parseInt(s);
        return num >= 0 && num <= 255;
    }

    static String encode(String input) {
        if(input == null || input.length() == 0) {
            return "";
        }
        int num = 1;
        StringBuilder result = new StringBuilder();
        for(int i = 1; i <= input.length(); i++) {
            if(i == input.length() || input.charAt(i - 1) != input.charAt(i)) {
                result.append(Integer.toString(num));
                result.append(input.charAt(i - 1));
                num = 1;
            } else {
                num++;
            }
        }
        return result.toString();
    }

    static String decode(String input) {
        if(input == null || input.length() == 0) {
            return "";
        }
        int num = 0;
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < input.length(); i++) {
            if(Character.isDigit(input.charAt(i))) {
                num = num * 10 + input.charAt(i) - '0';
            } else {
                result.append(getCode(num, input.charAt(i)));
                num = 0;
            }
        }
        return result.toString();
    }

    static String getCode(int num, char c) {
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < num; i++) {
            res.append(c);
        }
        return res.toString();
    }

    static int robinKarp(String t, String s) {
        if(t == null || s == null || t.length() == 0 || s.length() == 0 || s.length() > t.length()) {
            return -1;
        }
        int hashT = 0;
        int hashS = 0;
        int prime = 13;
        int base = 26;
        int powerS = 1;

        for(int i = 0; i < s.length(); i++) {
            powerS = i > 0 ? powerS * base : 1;
            hashS = (hashS * base + s.charAt(i));
            hashT = (hashT * base + t.charAt(i));
        }

        for(int i = s.length(); i < t.length(); i++) {
            if(hashS == hashT && t.substring(i - s.length(), i).equals(s)) {
                return i - s.length();
            }
            hashT = hashT - t.charAt(i - s.length()) * powerS;
            hashT = hashT * base + t.charAt(i);
        }

        if(hashS == hashT && t.substring(t.length() - s.length()).equals(s)) {
            return t.length() - s.length();
        }
        return -1;
    }

    public static void main(String[] args) {

        String s = "2276696";
        char[] chars = new char[s.length()];
        List<String> result = new ArrayList<>();

        getPhoneNumbers(s, 0, chars, result);

/*        for(String res : result) {
            System.out.println(res);
        }*/

        List<String> result1 = new ArrayList<>();
        String ip = "19216811";
        getIPAddress(ip, 0, 1, "", result1);

/*        for(String res : result1) {
            System.out.println(res);
        }*/
        System.out.println(decode(encode("aabbccab")));


        System.out.println(robinKarp("fdsfabc fdc efd", "abc"));
    }
}
