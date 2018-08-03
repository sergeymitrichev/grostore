package ru.ftob.grostore.ucoz.util;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.util.StringUtils;

public class ApiJsonValidator {
    public static boolean checkDateIsCorrect(JsonNode date) {
        return !date.toString().equals("0000-00-00") && !date.toString().isEmpty();
    }
}
