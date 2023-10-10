<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>会員一覧</h2>
        <table id="customer_list">
            <tbody>
                <tr>
                    <th>ID</th>
                    <th>氏名</th>
                    <th>操作</th>
                    <th>会員ステータス</th>
                </tr>
                <c:forEach var="customer" items="${customers}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td><c:out value="${customer.member_id}" /></td>
                        <td><c:out value="${customer.name}" /></td>
                        <td>
                            <c:choose>
                                <c:when test="${customer.delete_flag == 1}">
                                    （削除済み）
                                </c:when>
                                <c:otherwise>
                                    <a href="<c:url value='/customer/show?id=${customer.member_id}' />">詳細を表示</a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:if test="${customer.admin_flag == 0}">
                                一般会員
                            </c:if>
                            <c:if test="${customer.admin_flag == 1}">
                                管理者
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${customers_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((customers_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/customer/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>


    </c:param>
</c:import>