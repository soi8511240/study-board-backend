<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="include/header.jsp" %>

<form id="frm-insert" action="/board/update/${response.board[0].id}" method="post">
    <table class="table-horizontal">
        <colgroup>
            <col style="width: 15%;" />
            <col style="width: 35%;" />
            <col style="width: 15%;" />
            <col style="width: 35%;" />
        </colgroup>
        <tbody>
        <tr>
            <th><label class="ess">Title</label></th>
            <td><input type="text" name="title" required="required" value="${response.board[0].title}" /></td>
            <th><label class="ess">category</label></th>
            <td>
                <select class="select" name="categoryCode">
                    <c:forEach items="${response.category}" var="item">
                        <c:if test="${response.board[0].categoryCode eq item.id}">
                            <option value="${item.id}" selected>${item.name}</option>
                        </c:if>
                        <option value="${item.id}">${item.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <th><label class="ess">Writer</label></th>
            <td><input type="text" name="writer" required="required" value="${response.board[0].writer}" /></td>
            <th><label class="ess">Password</label></th>
            <td><input type="text" name="password" value="${response.board[0].password}" /></td>
        </tr>
        <tr>
            <th><label class="ess">Content</label></th>
            <td colspan="3"><textarea name="content" class="textarea" required="required" >${response.board[0].content}</textarea></td>
        </tr>
        <tr>
            <th>File</th>
            <td colspan="3"><input type="file" class="file" name="attachFile" /></td>
        </tr>
        </tbody>
    </table>
    <div class="btns-foot">
        <div class="left"></div>
        <div class="center">
            <button type="submit" class="btn btn-default primary">수정</button>
            <button class="btn btn-default">취소</button>
        </div>
        <div class="right"></div>
    </div>
</form>

<%@ include file="include/footer.jsp" %>

