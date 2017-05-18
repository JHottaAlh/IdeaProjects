package InputSample;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.SevletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by smi_kun on 2017/05/18.
 */
@webServlet("/input")
public class InputServlet extends javax.servlet.http.HttpServlet {
    private static final long serialVersionUID = 1L;

    @override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {

        response.setContentType("text/html; charset = UTF-8");

        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<Entry<String, String[]>> parameters = parameteMap.entrySet();
        for (Entry<String, String[]> parameter : parameters){
            String key = parameter.getKey();
            String[] values = parameter.getValue();

            System.out.println(key + ":");
            for(String value : values){
                System.out.print(value);
                System.out.print(",");
            }
            System.out.println();
        }

        InputValues inputsResult = createInputValues(request);

        request.setAttribute("inputsResult", inputsResult);

        RequestDispatcher dispatcher = request.getRequestDispatcher("input.jsp");
        dispatcher.forward(request, response);
    }

    private InputValues createInputValues(HttpServletRequest request){
        InputValues inputsResult = new InputValues();
        inputsResult.setName(request.getParameter("name"));
        inputsResult.setPassword(request.getParameter("password"));
        inputsResult.setHidden(request.getParameter("hidden"));
        inputsResult.setSex(request.getParameter("sex"));
        inputsResult.setHobby(request.getParameterValues("hobby"));
        inputsResult.setNationality(request.getParameter("nationality"));
        inputsResult.setLanguage(request.getParameterValues("language"));
        inputsResult.setMemo(request.getParameter("memo"));

        inputsResult.setSubmit1(request.getParameter("submit1"));
        inputsResult.setSubmit2(request.getParameter("submit2"));
        inputsResult.setImage_x(request.getParameter("image.x"));
        inputsResult.setImage_y(request.getParameter("image.y"));
        return inputsResult;
    }
}
