<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <meta charset="UTF-8">
        <title>トップページ</title>
        </head>
        <body>
            <c:if test="${flush != null}">
                <div id="flush_success">
                    <c:out value="${flush}"></c:out>
                </div>
            </c:if>
            ・
            <a href="<c:url value="/reserve/index" />">予約する</a>
            <br>
            ・
            <a href="<c:url value="/reserve/myConfirmation" />">予約確認</a>

        </body>
    </c:param>
</c:import>