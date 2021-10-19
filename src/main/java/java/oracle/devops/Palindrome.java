package java.oracle.devops;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Palindrome
 */
@EnableAutoConfiguration
public class Palindrome {

    public String checkPalindrome(String input) {
        String result = "";
        String reversedString = "";
        if (input == null) {
            return "Error: Please enter valid number or string";
        } else {

            for (int i = input.length() - 1; i >= 0; i--) {
                reversedString = reversedString + input.charAt(i);
            }

            if (reversedString.equals(input)) {
                result = "Palindrome";
            } else {
                result = "Not Palindrome";
            }
        }
        return result;
    }
}
