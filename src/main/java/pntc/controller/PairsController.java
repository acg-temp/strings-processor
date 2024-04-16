package pntc.controller;

import pntc.service.StringsCleaner;

import javax.servlet.annotation.WebServlet;
import java.util.function.Function;

@WebServlet("/pairs-en")
public class PairsController extends StringCleanerController {
    @Override
    protected String getJspPageName() {
        return "jsp/pairs.jsp";
    }

    @Override
    protected Function<String, String> getCleanFunction() {
        return StringsCleaner::removeOuterAlphabetPairs;
    }
}
