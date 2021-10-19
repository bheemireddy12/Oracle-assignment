package java.oracle.devops;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import static org.junit.Assert.assertEquals;

import java.oracle.devops.Palindrome;

public class PalindromeTest {

    // Creates object before running Test cases
    Palindrome palindrome;

    @Before
    public void setUp() {
        this.palindrome = new Palindrome();
    }

    // Destroys object created after running test cases
    @After
    public void tearDown() {
        this.palindrome = null;
    }

    @Test
    public void givenPalindromeNumberShouldReturnItsPalindrome() {
        String actualResult = palindrome.checkPalindrome("3456543");
        assertEquals("Palindrome", actualResult);
    }

    @Test
    public void givenNonPalindromeNumberShouldReturnNotPalindrome() {
        String actualResult = palindrome.checkPalindrome("6342554");
        assertEquals("Not Palindrome", actualResult);
    }

    @Test
    public void givenPalindromeStringShouldReturnItsPalindrome() {
        String actualResult = palindrome.checkPalindrome("wertytrew");
        assertEquals("Palindrome", actualResult);
    }

    @Test
    public void givenNonPalindromeStringShouldReturnNotPalindrome() {
        String actualResult = palindrome.checkPalindrome("djwdwyb");
        assertEquals("Not Palindrome", actualResult);
    }


    @Test
    public void givenNullShouldReturnErrorMessage() {
        String actualResult = palindrome.checkPalindrome(null);
        assertEquals("Error: Please enter valid number or string", actualResult);
    }
}
