package ru.ftob.grostore.model.util;

import java.util.stream.IntStream;

public class TextUtils {

    private static final char[] ABC_CYR = 
            {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 
                    'ж', 'з', 'и', 'й', 'к', 'л', 'м',
                    'н', 'о', 'п', 'р', 'с', 'т', 'у', 
                    'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 
                    'ы', 'ь', 'э', 'ю', 'я', 'А', 'Б', 
                    'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 
                    'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 
                    'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 
                    'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 
                    'Э', 'Ю', 'Я'};
    private static final String[] ABC_LAT = {"a", "b", "v", "g", "d", "e", "e", "zh", "z", "i", "j", "k", "l", "m", "n", "o", "p", "r", "s", "t", "u", "f", "h", "ts", "ch", "sh", "sch", "", "y", "", "e", "ju", "ja", "A", "B", "V", "G", "D", "E", "E", "Zh", "Z", "I", "Y", "K", "L", "M", "N", "O", "P", "R", "S", "T", "U", "F", "H", "Ts", "Ch", "Sh", "Sch", "", "I", "", "E", "Ju", "Ja"};
    private static final char[] REPLACED_BY_DASH = {' ', '%', ',', '`', '&', '?', '*', '/', '\\', '@', '#', '$', '^', '(', ')', '_', '+', '='};
    private static final char DASH = '-';
    private static final char[] IGNORED = {'!'};
    private static final char[] ALLOWED = {'-', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static String transliterateForUrl(String message) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            final char c = message.charAt(i);
            if(IntStream.range(0, IGNORED.length).anyMatch(x -> IGNORED[x] == c)) {
                continue;
            }

            if(IntStream.range(0, ALLOWED.length).anyMatch(x -> ALLOWED[x] == c)) {
                builder.append(c);
                continue;
            }
            if(IntStream.range(0, REPLACED_BY_DASH.length).anyMatch(x -> REPLACED_BY_DASH[x] == c)) {
                builder.append(DASH);
                continue;
            }

            IntStream.range(0, ABC_CYR.length).filter(x -> ABC_CYR[x] == c).forEach((x) -> {
                builder.append(ABC_LAT[x]);
            });
        }

        return builder.toString().toLowerCase();
    }
}
