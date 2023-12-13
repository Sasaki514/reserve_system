package controllers.reserve;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Customer;
import models.Reserve;
import utils.DBUtil;

/**
 * Servlet implementation class ReserveCancelCompletionServlet
 */
@WebServlet("/reserve/cancelCompletion")
public class ReserveCancelCompletionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReserveCancelCompletionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //セッションの取得
        HttpSession session = request.getSession();

        //クエリパラメータの取得
        int year = (int) session.getAttribute("year");
        int month = (int) session.getAttribute("month");
        int date = (int) session.getAttribute("date");
        int time = (int) session.getAttribute("time");
        int timeRequired = (int) session.getAttribute("timeRequired");
        int time_end = time + timeRequired;

        /*//時刻の◯時の部分を抽出
        Pattern pattern = Pattern.compile("(\\d+):(\\d+)");
        Matcher matcher = pattern.matcher(time);
        int hour = 0;

        if (matcher.find()) {
            String hourPart = matcher.group(1);
            hour = Integer.parseInt(hourPart);
        }*/

        LocalDateTime localDateTime = LocalDateTime.of(year, month, date, time, 0);

        Timestamp reserved_at = Timestamp.valueOf(localDateTime);

        //リクエスト属性に値を設定
        request.setAttribute("year", request.getAttribute("year"));
        request.setAttribute("month", request.getAttribute("month"));
        request.setAttribute("date", request.getAttribute("date"));
        request.setAttribute("dow", request.getAttribute("dow"));
        request.setAttribute("time", request.getAttribute("time"));
        request.setAttribute("time_end", time_end);
        request.setAttribute("timeRequired", request.getAttribute("timeRequired"));
        request.setAttribute("_token", request.getSession().getId());

        //予約情報を削除
        String _token = (String) request.getParameter("_token");
        if (_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            HttpSession session_reserve = ((HttpServletRequest) request).getSession();
            Customer login_customer = (Customer) session_reserve.getAttribute("login_customer");

            List<Reserve> r = em.createNamedQuery("getSingleReservation", Reserve.class)
                    .setParameter("member_id", login_customer.getMember_id())
                    .setParameter("reserved_at", reserved_at).getResultList();

            em.getTransaction().begin();
            em.remove(r.get(0));
            em.getTransaction().commit();

            request.setAttribute("flush", "予約の取り消しが完了しました。");
            em.close();

            //画面遷移
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reserve/cancelCompletion.jsp");
            rd.forward(request, response);

        }
    }

}
