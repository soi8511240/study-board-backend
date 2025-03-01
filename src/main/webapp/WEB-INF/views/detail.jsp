<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="include/header.jsp" %>

<c:forEach items="${response.board}" var="item">
<table class="table-horizontal">
    <tr><th>ID</th><td> ${item.id} </td></tr>
    <tr><th>Category</th><td> ${item.categoryName} </td></tr>
    <tr><th>Title</th><td> ${item.title} </td></tr>
    <tr><th>Content</th><td> ${item.content} </td></tr>
    <tr><th>Writer</th><td> ${item.writer} </td></tr>
    <tr><th>Count</th><td> ${item.viewCnt} </td></tr>
    <c:if test="${not empty response.attach}">
    <tr>
        <th>AttachFiles</th>
        <td>
        <c:forEach items="${response.attach}" var="attachItem">
             <div>${attachItem.storedFileName}</div>
        </c:forEach>
        </td>
    </tr>
    </c:if>
    <tr><th>CreateDate</th><td> ${item.createdAt} </td></tr>
    <tr><th>UpdateDate</th><td> ${item.updatedAt} </td></tr>
</table>
</c:forEach>

<div class="btns-foot">
    <div class="left">
        <a href="${pageContext.request.contextPath}/board/delete/${response.board[0].id}" class="btn btn-default negative">삭제</a>
    </div>
    <div class="right">
        <a href="${pageContext.request.contextPath}/board/update/${response.board[0].id}" class="btn btn-default">수정</a>
        <a href="${pageContext.request.contextPath}/board/list" class="btn btn-default primary">목록</a>
    </div>
</div>

<%@ include file="include/footer.jsp" %>
