<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
    </head>
    <body>

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
</html>