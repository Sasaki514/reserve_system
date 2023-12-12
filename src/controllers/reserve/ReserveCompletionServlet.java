package controllers.reserve;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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

        //予約情報を登録
        String _token = (String) request.getParameter("_token");
        if (_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Reserve r = new Reserve();
            HttpSession session_reserve = ((HttpServletRequest) request).getSession();
            Customer login_customer = (Customer) session_reserve.getAttribute("login_customer");

            r.setMember_id((String) login_customer.getMember_id());
            r.setReserved_at(reserved_at);
            r.setRequired_time(timeRequired);

            //現在時刻を取得
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            r.setApplied_at(currentTime);

            em.getTransaction().begin();
            em.persist(r);
            em.getTransaction().commit();
            request.setAttribute("flush", "予約が完了しました。");
            em.close();

            //画面遷移
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reserve/completion.jsp");
            rd.forward(request, response);

        }

    }

}
