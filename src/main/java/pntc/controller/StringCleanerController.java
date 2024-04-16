package pntc.controller;

import pntc.model.BaseStringPairsListObject;
import pntc.model.StringItem;
import pntc.service.StringsPrinter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public abstract class StringCleanerController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("stringsList", new BaseStringPairsListObject(new ArrayList<>()));
        req.getRequestDispatcher(getJspPageName()).forward(req, resp);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cleanParameter = request.getParameter("clean");
        String downloadParameter = request.getParameter("download");
        List<String> originalStrings =request.getParameterMap().get("stringsList")!=null?
                Arrays.stream(request.getParameterMap().get("stringsList")).toList(): new ArrayList<>();
        if (cleanParameter != null) {
            List<StringItem> stringItems = originalStrings.stream().map(inputString -> new StringItem(inputString, getCleanFunction().apply(inputString) )).toList();
            request.setAttribute("stringsList", new BaseStringPairsListObject(stringItems));
            request.getRequestDispatcher(getJspPageName()).forward(request, response);
        } else if (downloadParameter != null) {
            List<String> cleanStrings = originalStrings.stream().map(getCleanFunction()).toList();
            response.setContentType("text/plain");
            response.setHeader("Content-disposition", "attachment; filename=" + "strings-spiral.pdf");
            OutputStream responseOutputStream = response.getOutputStream();
            StringsPrinter.createPdf(responseOutputStream, cleanStrings);
            responseOutputStream.flush();
        }
    }

    protected abstract  String  getJspPageName();

    protected abstract Function<String,String> getCleanFunction();
}
