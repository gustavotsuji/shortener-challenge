package com.neueda.challenge.shortenerUrl.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Base62Converter {

    private Map<Character, Long> characterMap = new HashMap<>();

    public Base62Converter() {
        for(int i = 0; i < 62; i++){
            characterMap.put(mapNumberToCharacter(i), Long.valueOf(i));
        }
    }

    /**
     * Make the id conversion into slug
     * @param id a long to be converted
     * @return a String which corresponds the id converted
     */
    public String encode(long id) {
        long result = id / 62;
        char encoded = mapNumberToCharacter(id % 62);

        if(result == 0) return Character.toString(encoded);
        else return encode(id / 62) + encoded;
    }

    /**
     * Convert a letter into a char (a-Z, A-Z or 0-9). We are using the ascii mapping,
     * 48 is a shift to get the number, 55 uppercase letters and 61 the lowercase letters
     * @param number a number to be converted
     * @return a letter converted from a number
     */
    private char mapNumberToCharacter(long number){
        if(number >= 0 && number <= 9) return (char)(number + 48);
        else if(number > 9 && number <= 35) return (char)(number + 55);
        else if(number > 35 && number <= 61) return (char)(number + 61);
        else throw new RuntimeException("Number out of limits");
    }

    /**
     * Convert a slug into a number (base 10)
     * @param slug
     * @return a converted number from slug
     */
    public long decode(String slug) {
        if(slug == null || slug.length() == 0) throw new RuntimeException("Invalid input");
        return decode(slug, 0);
    }

    /**
     * Recursive decode method
     * @param str the slug (or part of that) to be converted
     * @param level an extra parameter which aids on calculation
     * @return the id converted from slug
     */
    private long decode(String str, int level) {
        long mapping = characterMap.get(str.charAt(str.length() - 1));
        long result = mapping * (long)Math.pow(62, level);
        if (str.length() == 1) return result;
        else return decode(str.substring(0, str.length() - 1), level + 1) + result;
    }
}
