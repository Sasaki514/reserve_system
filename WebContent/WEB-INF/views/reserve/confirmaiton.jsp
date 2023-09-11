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
            <table class="border reservation">
                <tr>
                    <td class="reservationHeader">予約時刻</td>
                    <td class="border reservationCell">${year}年${month}月${date}日${dow}　${time[0]}～${time[1]}</td>
                </tr>
            </table>
    </c:param>

</c:import>