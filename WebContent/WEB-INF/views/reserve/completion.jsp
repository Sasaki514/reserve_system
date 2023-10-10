<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <head>
<meta charset="UTF-8">
<title>予約完了</title>
        </head>
        <body>
            <c:if test="${flush != null}">
                <div id="flush_success">
                    <c:out value="${flush}"></c:out>
                </div>
            </c:if>
            <h3>【予約内容】</h3>
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
        </body>
    </c:param>
</c:import>
