package controllers.reserve;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TopPageIndexServlet
 */
@WebServlet("/reserve/index")
public class ReserveIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReserveIndexServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //テーブルに表示する日付のカラム数
        int NumofDays = 10;
        List<String> yearList = new ArrayList<String>();
        List<String> yearListAll = new ArrayList<String>();
        List<String> monthList = new ArrayList<String>();
        List<String> monthListAll = new ArrayList<String>();
        List<String> dateList = new ArrayList<String>();
        List<String> DayOfWeekList = new ArrayList<String>();

        //日付の取得
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
        cal.set(Calendar.DAY_OF_MONTH, 25);

        //同月カウンタ
        int sameMonthCount = 0;
        String month_0;

        //日付の表示形式の設定
        SimpleDateFormat sdf_year = new SimpleDateFormat("Y");
        SimpleDateFormat sdf_month = new SimpleDateFormat("M");
        SimpleDateFormat sdf_day = new SimpleDateFormat("d");
        SimpleDateFormat sdf_DayOfWeek = new SimpleDateFormat("(E)");

        //本日から指定日数分の日付を取得
        //月を跨ぐ場合の処理含む
        for (int j = 0; j < NumofDays; j++) {
            //月のリストが空であれば月の値を挿入
            if (monthList == null || monthList.isEmpty()) {
                yearList.add(sdf_year.format(cal.getTime()));
                monthList.add(sdf_month.format(cal.getTime()));
            }

            //for文のループ2回目以降(月の重複挿入防止)かつ月リストの最大要素数未満であれば月を挿入
            if (j >= 1 && monthList.size() < 2) {
                month_0 = monthList.get(0);
                if (month_0.equals(sdf_month.format(cal.getTime()))) {
                    ++sameMonthCount;
                } else {
                    yearList.add(sdf_year.format(cal.getTime()));
                    monthList.add(sdf_month.format(cal.getTime()));
                }
            }

            yearListAll.add(sdf_year.format(cal.getTime()));
            monthListAll.add(sdf_month.format(cal.getTime()));
            dateList.add(sdf_day.format(cal.getTime()));
            DayOfWeekList.add(sdf_DayOfWeek.format(cal.getTime()));
            cal.add(Calendar.DATE, 1);
        }

        //リクエスト属性の設定
        request.setAttribute("yearList", yearList);
        request.setAttribute("yearListAll", yearListAll);
        request.setAttribute("monthList", monthList);
        request.setAttribute("monthListAll", monthListAll);
        request.setAttribute("dateList", dateList);
        request.setAttribute("dowList", DayOfWeekList);
        request.setAttribute("firstColspan", sameMonthCount + 1);
        request.setAttribute("secondColspan", NumofDays - (sameMonthCount + 1));
        request.setAttribute("firstPx", (sameMonthCount + 1) * 8);
        request.setAttribute("secondPx", (NumofDays - (sameMonthCount + 1)) * 8);

        //1時間刻みで時刻を取得
        //現状9～20時
        List<String> timeList = new ArrayList<String>();
        int beginTime = 9;
        int endTime = 20;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");

        for (int hour = beginTime; hour <= endTime; hour++) {
            LocalTime localTime = LocalTime.of(hour, 0);
            String formattedTime = localTime.format(formatter);
            timeList.add(formattedTime);
        }

        request.setAttribute("timeList", timeList);

        //画面遷移
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reserve/index.jsp");
        rd.forward(request, response);
    }

}