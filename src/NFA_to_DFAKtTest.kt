import junit.framework.TestCase.*
import org.junit.Test
import java.io.File
import java.nio.file.Files
import java.nio.file.StandardCopyOption

internal class NFA_to_DFAKtTest {

    fun checkTest(s : String) {
        Files.copy(File("file.txt").toPath(), File("file2.txt").toPath(),  StandardCopyOption.REPLACE_EXISTING)
        NFAtoDFA("file2.txt")
        assertEquals(check("file.txt", s), check("file2.txt", s))
    }

    @Test
    fun test() {
        checkTest("0000")
        checkTest("00111111100")
        checkTest("0001010100")
        checkTest("0000110")
        checkTest("00111111")
    }
}