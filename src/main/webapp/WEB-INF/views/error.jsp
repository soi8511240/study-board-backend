<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="include/header.jsp" %>

<h1>ERROR</h1>
<p style="padding-top: 20px;padding-bottom: 80px;">무언가 잘못됫습니다.</p>

<div class="btns-foot">
    <div class="center">
        <button class="btn btn-primary" onclick="window.location='/board/list';">홈</button>
        <button class="btn btn-primary" onclick="history.back();">돌아가기</button>
    </div>
</div>

<%@ include file="include/footer.jsp" %>

