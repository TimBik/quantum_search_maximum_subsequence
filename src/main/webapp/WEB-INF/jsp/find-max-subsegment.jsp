<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Нахождение максимального подотрезка</title>
</head>
<body>
<div>
    <form method="post" action="/find-max-subsegment">
        <div>
            <div>
                <h4>Нахождение максимального подотрезка</h4>
            </div>
            <div>
                <input name="data" type="text" placeholder="Введите значения через запятую" />
            </div>
            <button type="submit">Go</button>
        </div>
    </form>
</div>
</body>
</html>
