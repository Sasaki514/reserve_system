package controllers.reserve;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ReserveCompletionServlet
 */
@WebServlet("/reserve/completion")
public class ReserveCompletionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReserveCompletionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub

        //セッションの取得
        HttpSession session = request.getSession();

        //リクエスト属性に値を設定
        request.setAttribute("year", session.getAttribute("year"));
        request.setAttribute("month", session.getAttribute("month"));
        request.setAttribute("date", session.getAttribute("date"));
        request.setAttribute("dow", session.getAttribute("dow"));
        request.setAttribute("time", session.getAttribute("time"));
        request.setAttribute("timeRequired", session.getAttribute("timeRequired"));

        //セッション情報の削除
        session.removeAttribute("year");
        session.removeAttribute("month");
        session.removeAttribute("date");
        session.removeAttribute("dow");
        session.removeAttribute("time");
        session.removeAttribute("timeRequired");

        //画面遷移
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reserve/completion.jsp");
        rd.forward(request, response);
    }

}
