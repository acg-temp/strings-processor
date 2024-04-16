package pntc.controller;

import pntc.service.StringsCleaner;

import javax.servlet.annotation.WebServlet;
import java.util.function.Function;

@WebServlet("/brackets")
public class BracketsController extends StringCleanerController {

    @Override
    protected String getJspPageName() {
        return "jsp/brackets.jsp";
    }

    @Override
    protected Function<String, String> getCleanFunction() {
        return StringsCleaner::cleanStringBrackets;
    }


}
