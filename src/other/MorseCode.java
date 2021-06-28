package other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class MorseCode {

    /*
    *   'A': '.-', 'B': '-...', 'C': '-.-.', 'D': '-..', 'E': '.', 'F': '..-.', 'G': '--.', 'H': '....',
        'I': '..', 'J': '.---', 'K': '-.-', 'L': '.-..', 'M': '--', 'N': '-.', 'O': '---', 'P': '.--.',
        'Q': '--.-', 'R': '.-.', 'S': '...', 'T': '-', 'U': '..-', 'V': '...-', 'W': '.--', 'X': '-..-',
        'Y': '-.--', 'Z': '--..',
    *
    * */
    private static Map<String, Character> morseCodeMap = new HashMap<>();
    static {
        morseCodeMap.put(".-", 'A');
        morseCodeMap.put("...", 'B');
        morseCodeMap.put("-.-.", 'C');
        morseCodeMap.put("-..", 'D');
        morseCodeMap.put(".", 'E');
        morseCodeMap.put("..-.", 'F');
        morseCodeMap.put("--.", 'G');
        morseCodeMap.put("....", 'H');
        morseCodeMap.put("..", 'I');
        morseCodeMap.put(".---", 'J');
        morseCodeMap.put("-.-", 'K');
        morseCodeMap.put(".-..", 'L');
        morseCodeMap.put("--", 'M');
        morseCodeMap.put("-.", 'N');
        morseCodeMap.put("---", 'O');
        morseCodeMap.put(".--.", 'P');
        morseCodeMap.put("--.-", 'Q');
        morseCodeMap.put(".-.", 'R');
        morseCodeMap.put("...", 'S');
        morseCodeMap.put("-", 'T');
        morseCodeMap.put("..-", 'U');
        morseCodeMap.put("...-", 'V');
        morseCodeMap.put(".--", 'W');
        morseCodeMap.put("-..-", 'X');
        morseCodeMap.put("-.--", 'Y');
        morseCodeMap.put("--..", 'Z');
    }

    public List<String> getAllCombination(String morseCode) {
        if(morseCode == null || morseCode.length() == 0) {
            return new ArrayList<>();
        }





        return null;
    }

    private void getAllCombination(String morseCode, int index, List<Character> word, List<String> codes) {
        if(index > morseCode.length()) {
            return;
        }


        for(int offset = 1; index + offset <= morseCode.length(); offset++) {
            if(!morseCodeMap.containsKey(morseCode.substring(index, index + offset))) {
                continue;
            }
            word.add(morseCodeMap.get(morseCode.substring(index, index + offset)));
            getAllCombination(morseCode, index + offset + 1, word, codes);
        }
    }
}
