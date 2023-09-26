<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>予約管理システム</title>
<link rel="stylesheet" href="<c:url value='/css/reset.css' />">
<link rel="stylesheet" href="<c:url value='/css/style.css' />">
</head>
<body>
    <div id="wrapper">
        <div id="header">
            <div id="header_menu">
                <h1>
                    <a href="<c:url value='/' />">予約管理システム</a>
                </h1>
                &nbsp;&nbsp;&nbsp;
                <c:if test="${sessionScope.login_customer != null}">
                    <c:if test="${sessionScope.login_customer.admin_flag == 1}">
                        <a href="<c:url value='/customer/index' />">会員管理</a>&nbsp;
                        </c:if>
                </c:if>
                <c:if test="${sessionScope.login_customer != null}">
                    <div id="customer_name">
                        <c:out value="${sessionScope.login_customer.name}" />
                        &nbsp;さん&nbsp;&nbsp;&nbsp; <a href="<c:url value='/logout' />">ログアウト</a>
                    </div>
                </c:if>
            </div>
        </div>
        <div id="content">${param.content}</div>
        <div id="footer">
            <br> <br> <a href="<c:url value='/' />">トップページ</a> <br>
            <br> by Tatsuya Sasaki.

        </div>
    </div>
</body>
</html>