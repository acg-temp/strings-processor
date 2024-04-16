package pntc.service;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import lombok.experimental.UtilityClass;

import java.io.OutputStream;
import java.util.*;

@UtilityClass
public class StringsPrinter {

    private char DEFAULT_CHAR = '_';


    private final int FONT_SIZE = 15;

    public void createPdf(OutputStream outputStream, List<String> inputStrings) {
        List<String> stringsToArrange=toMonotoneSequence(inputStrings);
        try {
            PdfWriter pdfWriter = new PdfWriter(outputStream);
            //  CompletableFuture.supplyAsync(() -> {
            int maxStringSize = stringsToArrange.isEmpty() ? 0 : stringsToArrange.get(stringsToArrange.size() - 1).length();
            Document document = null;
            try {
                PdfDocument pdfDocument = new PdfDocument(pdfWriter);
                document = new Document(pdfDocument);
                if(!stringsToArrange.isEmpty()) {
                    PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
                    document.setFont(font);
                    document.setFontSize(FONT_SIZE);
                    int unitLength = (int) font.getWidth("##", FONT_SIZE);
                    int currentX = maxStringSize * unitLength*2;
                    int currentY = maxStringSize * unitLength*2;
                    pdfDocument.setDefaultPageSize(new PageSize(maxStringSize * unitLength * 4, maxStringSize * unitLength * 4));
                    for (int i = 0; i < stringsToArrange.size(); i++) {
                        String string = stringsToArrange.get(i);
                        Paragraph paragraph = new Paragraph(string);
                        int width = unitLength * string.length();
                        paragraph.setFixedPosition(currentX, currentY, width);
                        paragraph.setTextAlignment(TextAlignment.JUSTIFIED_ALL);
                        currentX = getNextX(i, currentX, width);
                        currentY = getNextY(i, currentY, width);
                        document.add(paragraph.setRotationAngle(-Math.PI / 2 * (i % 4)));
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("Error while generating strings spiral pdf ");
            } finally {
                if (document != null) {
                    document.close();
                }
            }
            //   });
        } catch (Exception e) {
            throw new RuntimeException("Error while generating strings spiral pdf ");
        }
    }


    private Integer getNextX(int iterationIndex, int currentX, int currentWidth) {
        switch (iterationIndex % 4) {
            case 0:
                return currentX + currentWidth;
            case 1:
                return currentX;
            case 2:
                return currentX - currentWidth;
            case 3:
                return currentX;
            default:
                return 0;
        }
    }

    private Integer getNextY(int iterationIndex, int currentY, int currentWidth) {
        switch (iterationIndex % 4) {
            case 0:
                return currentY;
            case 1:
                return currentY - currentWidth;
            case 2:
                return currentY;
            case 3:
                return currentY + currentWidth;
            default:
                return 0;
        }
    }


    public List<String> toMonotoneSequence(List<String> originalStrings) {
        List<String> monotoneStringsSequence = new ArrayList<>();
        if (originalStrings.isEmpty()) {
            return monotoneStringsSequence;
        }
        NavigableMap<Integer, LinkedList<String>> stringsByLengthMap = new TreeMap<>();
        for (String originalString : originalStrings) {
            String trimmedString = originalString != null ? originalString.trim() : "";
            stringsByLengthMap.computeIfAbsent(trimmedString.length(), key -> new LinkedList<>()).push(trimmedString);
        }
        Set<Integer> lengthsToRemove = new HashSet<>();
        for (Integer length : stringsByLengthMap.keySet()) {
            LinkedList<String> stringsOfThisLength = stringsByLengthMap.get(length);
            String firstStringOfThisLength = stringsOfThisLength.pollFirst();
            monotoneStringsSequence.add(firstStringOfThisLength);
            if (stringsOfThisLength.isEmpty()) {
                lengthsToRemove.add(length);
            }
        }
        lengthsToRemove.forEach(stringsByLengthMap::remove);
        int wrappedStringLength = monotoneStringsSequence.get(monotoneStringsSequence.size() - 1).length() + 1;
        for (String remainingString : stringsByLengthMap.values().stream().flatMap(Collection::stream).toList()) {
            String wrappedString = wrapString(remainingString, wrappedStringLength);
            wrappedStringLength = wrappedString.length() + 1;
            monotoneStringsSequence.add(wrappedString);
        }
        return monotoneStringsSequence;
    }


    private String wrapString(String originalString, int wrappedStringLength) {
        int defaultCharsNumber = wrappedStringLength - originalString.length();
        if (defaultCharsNumber % 2 == 1) {
            defaultCharsNumber++;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.valueOf(DEFAULT_CHAR).repeat(Math.max(0, defaultCharsNumber / 2)));
        stringBuilder.append(originalString);
        stringBuilder.append(String.valueOf(DEFAULT_CHAR).repeat(Math.max(0, defaultCharsNumber / 2)));
        return stringBuilder.toString();
    }


}
