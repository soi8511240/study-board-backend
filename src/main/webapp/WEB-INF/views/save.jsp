<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="include/header.jsp" %>
<form id="frm-insert" action="save" method="post" enctype="multipart/form-data">
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
            <td><input type="text" name="title" value="title1" required="required" /></td>
            <th><label class="ess">category</label></th>
            <td>
                <select class="select" name="categoryCode">
                    <c:forEach items="${response.category}" var="item">
                        <option value="${item.id}">${item.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <th><label class="ess">Writer</label></th>
            <td><input type="text" name="writer" value="writer1" required="required" /></td>
            <th><label class="ess">Password</label></th>
            <td><input type="text" name="password" value="" /></td>
        </tr>
        <tr>
            <th><label class="ess">Content</label></th>
            <td colspan="3"><textarea name="content" class="textarea" required="required" >content1</textarea></td>
        </tr>
        <tr>
            <th>File</th>
            <td colspan="3"><input type="file" class="file" name="attachFiles" multiple /></td>
        </tr>
        </tbody>
    </table>
    <div class="btns-foot">
        <div class="left"></div>
        <div class="center">
            <button type="submit" class="btn btn-default primary">등록</button>
            <button type="button" class="btn btn-default" id="btn-back">취소</button>
            <script type="text/javascript">
                document.getElementById("btn-back").onclick = function(e){
                    e.preventDefault();
                    history.back();
                }
            </script>
        </div>
        <div class="right"></div>
    </div>
</form>

<%@ include file="include/footer.jsp" %>

