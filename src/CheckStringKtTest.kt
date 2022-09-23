import junit.framework.TestCase.*
import org.junit.Test

internal class checkStringTest {

    @Test
    fun test() {
        assertTrue(check("file.txt", "0000"))
        assertTrue(check("file.txt", "00111111100"))
        assertTrue(check("file.txt", "0001010100"))
        assertFalse(check("file.txt", "0000110"))
        assertFalse(check("file.txt", "00111111"))
    }
}