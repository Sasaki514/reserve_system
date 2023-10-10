<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${hasError}">
            <div id="flush_error">会員IDかパスワードが間違っています。</div>
        </c:if>
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>ログイン</h2>
        <form method="POST" action="<c:url value='/login' />">
            <label for="member_id">会員ID</label><br /> <input type="text"
                name="member_id" value="${member_id}" /> <br />
            <br /> <label for="password">パスワード</label><br /> <input
                type="password" name="password" /> <br />
            <br /> <input type="hidden" name="_token" value="${_token}" />
            <button type="submit">ログイン</button>
        </form>
        <br>
        <p>
            <a href="<c:url value='/customer/new' />">会員の登録はこちら</a>
        </p>
    </c:param>
</c:import>