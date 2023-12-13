<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <title>予約確認</title>
        <h3>【予約確認】</h3>
        <table>
            <thead>
                <tr>
                    <td class="monthCell"></td>
                    <th class="dateCellNonborder" colspan="${firstColspan}"
                        style="width:${firstPx}px;">${yearList[0]}年${monthList[0]}月</th>
                    <c:if test="${secondColspan > 0}">
                        <th class="dateCellNonborder" colspan="${secondColspan}"
                            style="width:${secondPx}px;">${yearList[1]}年${monthList[1]}月</th>
                    </c:if>
                </tr>
                <tr>
                    <td class="timeCellNonborder"></td>
                    <c:forEach var="date" varStatus="d" items="${dateList}">
                        <th class="dateCell">${date}<br> ${dowList[d.index]}
                        </th>
                    </c:forEach>
                </tr>

            </thead>
            <tbody>
                <tr>
                    <th>
                        <table class="body">
                            <tbody>
                                <c:forEach var="time" items="${timeList}">
                                    <tr>
                                        <th class="timeCell">${time}:00</th>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </th>
                    <c:forEach var="date" varStatus="d" items="${dateList}">
                        <th>
                            <table class="body">
                                <tbody>
                                    <c:forEach var="time" varStatus="t" items="${timeList}">
                                        <tr>
                                            <td class="centered">
                                            <c:choose>
                                                    <c:when test="${Mark[widthOfTime * d.index + t.index] == 0}">
                                                        -
                                                    </c:when>
                                                    <c:otherwise>
                                                    <a href="<c:url value="/reserve/cancel?year=${yearListAll[d.index]}&month=${monthListAll[d.index]}&date=${date}&time=${time}" />">◯</a>
                                                    </c:otherwise>
                                            </c:choose>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </th>
                    </c:forEach>
                </tr>
            </tbody>
        </table>

    </c:param>

</c:import>