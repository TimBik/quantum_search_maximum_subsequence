<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Алгортм Гловера</title>
</head>
<body>
<div>
    <form method="post" action="/glover-algorithm">
        <div>
            <div>
                <h4>Алгортм Гловера</h4>
            </div>
            <div>
                <input name="data" type="text" placeholder="Введите значения через запятую" />
            </div>
            <div>
                <input name="indexStart" type="text" placeholder="Введите стартовый индекс"/>
            </div>
            <div>
                <input name="indexEnd" type="text" placeholder="Введите конечный индекс"/>
            </div>
            <button type="submit">Go</button>
        </div>
    </form>
</div>
</body>
</html>
