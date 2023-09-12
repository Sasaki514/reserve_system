package controllers.reserve;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ReserveConfirmationServlet
 */
@WebServlet("/reserve/confirmation")
public class ReserveConfirmationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReserveConfirmationServlet() {
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
        String time = request.getParameter("time");

        //曜日の取得
        LocalDate ld = LocalDate.of(year - 1900, month, date);
        Date date_ld = java.sql.Date.valueOf(ld);
        SimpleDateFormat sdf_dow = new SimpleDateFormat("(E)");
        String dayOfWeek = sdf_dow.format(date_ld);

        //予約時刻の設定
        List<String> timeList = new ArrayList<String>();

        String extractedHour = "";
        String pattern = "^(\\d+):\\d+$"; // 正規表現パターン
        Pattern r = Pattern.compile(pattern); // 正規表現パターンをコンパイル
        Matcher m = r.matcher(time); // マッチングを実行
        if (m.find()) {
            extractedHour = m.group(1); // グループ1の部分（時間）を抽出
        }

        int timeRequired = 1; //所要時間
        int beginTime = Integer.parseInt(extractedHour); //開始時刻
        int endTime = beginTime + timeRequired; //終了時刻
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
        LocalTime localTime_b = LocalTime.of(beginTime, 0);
        String formattedTime_b = localTime_b.format(formatter);
        LocalTime localTime_e = LocalTime.of(endTime, 0);
        String formattedTime_e = localTime_e.format(formatter);
        timeList.add(formattedTime_b);
        timeList.add(formattedTime_e);

        //リクエスト属性に値を設定
        request.setAttribute("year", year);
        request.setAttribute("month", month);
        request.setAttribute("date", date);
        request.setAttribute("dow", dayOfWeek);
        request.setAttribute("time", timeList);
        request.setAttribute("timeRequired", timeRequired);

        //セッションに値を設定
        HttpSession session = request.getSession();

        session.setAttribute("year", year);
        session.setAttribute("month", month);
        session.setAttribute("date", date);
        session.setAttribute("dow", dayOfWeek);
        session.setAttribute("time", timeList);
        session.setAttribute("timeRequired", timeRequired);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reserve/confirmaiton.jsp");
        rd.forward(request, response);
    }

}
