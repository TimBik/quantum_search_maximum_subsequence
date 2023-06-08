<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Поиск</title>
</head>
<body>
<div>
    <form method="post" action="/find-right-suitable-with-border-data">
        <div>
            <div>
                <h4>Поиск левой границы диапазона с правильными элементами, находящимися на расстоянии не более чем d от данного индекса</h4>
            </div>
            <div>
                <input name="data" type="text" placeholder="Введите значения через запятую" />
            </div>
            <div>
                <input name="index" type="text" placeholder="Введите индекс, от которого ищется правая граница диапазона"/>
            </div>
            <div>
                <input name="d" type="text" placeholder="Введите максимальное расстояние между индексом и правильным элементом"/>
            </div>
            <button type="submit">Go</button>
        </div>
    </form>
</div>
</body>
</html>
