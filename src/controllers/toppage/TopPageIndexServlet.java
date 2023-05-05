package controllers.toppage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TopPageIndexServlet
 */
@WebServlet("/index.html")
public class TopPageIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TopPageIndexServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int i=5;
        String[] table_dates = new String[i];

        //日付の取得
        Calendar cal=Calendar.getInstance();

        //日付の表示形式の設定
        SimpleDateFormat sdf=new SimpleDateFormat("MM/dd (E)");

        //本日から5日分の日付を取得
        for(i=0;i<5;i++){
            table_dates[i]=sdf.format(cal.getTime());
            request.setAttribute("table_date"+i, table_dates[i]);
            cal.add(Calendar.DATE, 1);
        }



        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/topPage/index.jsp");
        rd.forward(request, response);
    }

}