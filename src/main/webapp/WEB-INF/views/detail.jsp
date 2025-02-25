<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="include/header.jsp" %>

<table class="table-horizontal">

<%--    <c:if test="${empty board}">--%>
        <tr><th>ID</th><td> ${board.id} </td></tr>
        <tr><th>Title</th><td> ${board.title} </td></tr>
        <tr><th>Content</th><td> ${board.content} </td></tr>
        <tr><th>Writer</th><td> ${board.writer} </td></tr>
        <tr><th>Count</th><td> ${board.viewCnt} </td></tr>
        <tr><th>CreateDate</th><td> ${board.createdAt} </td></tr>
        <tr><th>UpdateDate</th><td> ${board.updatedAt} </td></tr>
<%--    </c:if>--%>

</table>

<div class="btns-foot">
    <div class="left"></div>
    <div class="right">
        <a href="${pageContext.request.contextPath}/list" class="btn btn-default primary">목록</a>
    </div>
</div>

<%@ include file="include/footer.jsp" %>
