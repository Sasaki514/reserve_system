package controllers.reserve;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ReserveCancelServlet
 */
@WebServlet("/reserve/cancel")
public class ReserveCancelServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReserveCancelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        //クエリパラメータの取得
        int year = Integer.parseInt(request.getParameter("year"));
        int month = Integer.parseInt(request.getParameter("month"));
        int date = Integer.parseInt(request.getParameter("date"));
        int time_begin = Integer.parseInt(request.getParameter("time"));

        //曜日の取得
        LocalDate ld = LocalDate.of(year - 1900, month, date);
        Date date_ld = java.sql.Date.valueOf(ld);
        SimpleDateFormat sdf_dow = new SimpleDateFormat("(E)");
        String dayOfWeek = sdf_dow.format(date_ld);

        int timeRequired = 1; //所要時間
        int time_end = time_begin + 1;

        //リクエスト属性に値を設定
        request.setAttribute("year", year);
        request.setAttribute("month", month);
        request.setAttribute("date", date);
        request.setAttribute("dow", dayOfWeek);
        request.setAttribute("time", time_begin);
        request.setAttribute("time_end", time_end);
        request.setAttribute("timeRequired", timeRequired);
        request.setAttribute("_token", request.getSession().getId());

        //セッションに値を設定
        HttpSession session = request.getSession();

        session.setAttribute("year", year);
        session.setAttribute("month", month);
        session.setAttribute("date", date);
        session.setAttribute("dow", dayOfWeek);
        session.setAttribute("time", time_begin);
        session.setAttribute("timeRequired", timeRequired);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reserve/cancel.jsp");
        rd.forward(request, response);
    }

}
