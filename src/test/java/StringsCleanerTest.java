import org.junit.Assert;
import org.junit.Test;
import pntc.service.StringsCleaner;

public class StringsCleanerTest {


    @Test
    public void testCleanStringsBrackets() {
        Assert.assertEquals("", StringsCleaner.cleanStringBrackets(""));
        Assert.assertEquals("(", StringsCleaner.cleanStringBrackets("("));
        Assert.assertEquals("((", StringsCleaner.cleanStringBrackets("(("));
        Assert.assertEquals("(()", StringsCleaner.cleanStringBrackets("(()"));
        Assert.assertEquals("", StringsCleaner.cleanStringBrackets("()"));
        Assert.assertEquals("((fkjfdkl()j(aaab))", StringsCleaner.cleanStringBrackets("((fkjfdkl()j(aaab))"));
        Assert.assertEquals("fkjfdkl()j(aaab)", StringsCleaner.cleanStringBrackets("((fkjfdkl()j(aaab)))"));
        Assert.assertEquals("a", StringsCleaner.cleanStringBrackets("((a))"));
        Assert.assertEquals("ab", StringsCleaner.cleanStringBrackets("((ab))"));
        Assert.assertEquals("(ab)a", StringsCleaner.cleanStringBrackets("((ab)a)"));
        Assert.assertEquals("(ab)(c)", StringsCleaner.cleanStringBrackets("((ab)(c))"));
        Assert.assertEquals("abc  ", StringsCleaner.cleanStringBrackets("(abc  )"));
        Assert.assertEquals("abc", StringsCleaner.cleanStringBrackets("((abc))"));
        Assert.assertEquals("abc", StringsCleaner.cleanStringBrackets("abc"));
        Assert.assertEquals("ab(cd)", StringsCleaner.cleanStringBrackets("ab(cd)"));
    }


    @Test
    public void testCleanStringsAlphabetPairs() {
        Assert.assertEquals("", StringsCleaner.removeOuterAlphabetPairs(""));
        Assert.assertEquals("a", StringsCleaner.removeOuterAlphabetPairs("a"));
        Assert.assertEquals("", StringsCleaner.removeOuterAlphabetPairs("az"));
        Assert.assertEquals("za", StringsCleaner.removeOuterAlphabetPairs("za"));
        Assert.assertEquals("zbya", StringsCleaner.removeOuterAlphabetPairs("zbya"));
        Assert.assertEquals("", StringsCleaner.removeOuterAlphabetPairs("abyz"));
        Assert.assertEquals("k", StringsCleaner.removeOuterAlphabetPairs("ackxz"));
        Assert.assertEquals("qwertyuioplkjhgfdsazxcvbnm:?", StringsCleaner.removeOuterAlphabetPairs("gqwertyuioplkjhgfdsazxcvbnm:?t"));
        Assert.assertEquals("ee", StringsCleaner.removeOuterAlphabetPairs("keep"));
        Assert.assertEquals("", StringsCleaner.removeOuterAlphabetPairs("Abcdefghijklmnopqrstuvwxyz"));
        Assert.assertEquals("1)bky", StringsCleaner.removeOuterAlphabetPairs("g1)bkyt"));
    }


}
