<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <head>
<meta charset="UTF-8">
<title>予約確認</title>
        </head>
        <body>
            <h3>【予約確認】</h3>
            <form action="completion" method="post">
                <table class="border reservation">
                    <tr>
                        <td class="reservationHeader">予約時刻</td>
                        <td class="border reservationCell">${year}年${month}月${date}日${dow}
                            ${time[0]}～${time[1]}</td>
                    </tr>
                    <tr>
                        <td class="reservationHeader">所要時間</td>
                        <td class="border reservationCell">${timeRequired}時間</td>
                    </tr>
                </table>
                <br>
                <div class="centerButton">
                    <button type="submit" name="confirm" value="予約を確定する"
                        class="pointerCursor buttonSize">予約を確定する</button>
                </div>
            </form>
    </c:param>

</c:import>