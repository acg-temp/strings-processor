import org.junit.Assert;
import org.junit.Test;
import pntc.service.StringsPrinter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class StringsPrinterTest {

    @Test
    public void testStringsMonotoneSequence() {
        List<String> inputList = List.of(
                "aaa",
                "aaa",
                "asdjkfdalk",
                ""
        );
        List<String> monotoneList = StringsPrinter.toMonotoneSequence(inputList);
        Assert.assertEquals(4, monotoneList.size());
        for (int i = 0; i < 3; i++) {
            Assert.assertTrue(monotoneList.get(i).length() < monotoneList.get(i + 1).length());
        }
    }

    @Test
    public void testFileGenerator() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        List<String> inputList = List.of(
                "aaa",
                "aaa",
                "asdjkfdalk",
                "",
                "bbb",
                "KKKKKKKKKKKKKKKKK",
                "sdhuisfiu",
                "(___*é*KJHF78wthidz098",
                "854jhdzjkaklJSDOISFGDFNFGD",
                "66SFDJ",
                "666"
        );
        StringsPrinter.createPdf(byteArrayOutputStream, inputList);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        Assert.assertTrue(byteArray.length > 0);
        File file = new File("C:/Users/annac/Documents/guitar/test1.pdf");
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(byteArray);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
