<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="include/header.jsp" %>

<form action="/board/list" method="get">
    <div class="searchbar">
        <span>등록일</span>
        <div class="area-datepicker">
            <input type="text" class="input-datepicker" name="startDt" id="startDt" value="" />
            <input type="text" class="input-datepicker" name="endDt" id="endDt" value="" />
        </div>
        <select class="select" name="categorySelect">
            <option value="">전체</option>
            <c:forEach items="${response.category}" var="item">
                <option value="${item.id}">${item.name}</option>
            </c:forEach>
        </select>
        <input type="text" id="keyword" placeholder="검색어를 입력해 주세요. (제목 + 작성자 + 내용)" value="" />
        <button id="sr-button" class="btn-sr" type="button">검색</button>
    </div>
</form>

<script type="text/javascript">

const DATE_FORMAT = "yy-mm-dd";

const initSearchBar = async ()=> {

    /* date Input */
    $('#startDt, #endDt')
        .datepicker({dateFormat: DATE_FORMAT});

    /* category Select */
    // const $select = document.querySelector('#categorySelect');
    // const categories = await getCodes('category');
    // categories.forEach(item => {
    //     $select.append(new Option(item.name, item.id, false, false));
    // })

    /* button */
    document.querySelector('#sr-button').addEventListener('click', goSearch);

    keywordInput.focus();
    keywordInput.addEventListener('keyup', function (e) {
        e.preventDefault();

        if (e.keyCode === 13) {
            goSearch(defaultSearchFilter);
        }
    })
}
initSearchBar();
// categorySelect.addEventListener('change', (e)=>{
//     const category = e.target.value;
//     console.log(category)
// });
</script>

<div class="table-top">
    총 건
</div>

<table class="table-list">
    <colgroup>
        <col style="width: 15%;" />
        <col style="" />
        <col style="width: 10%" />
        <col style="width: 10%" />
        <col style="width: 16%" />
        <col style="width: 16%" />
    </colgroup>
    <thead>
    <tr>
        <th>카테고리</th>
        <th>제목</th>
        <th>작성자</th>
        <th>조회수</th>
        <th>등록 일시</th>
        <th>수정 일시</th>
    </tr>
    </thead>
    <tbody>
    <c:if test="${empty response.board}">
        <tr>
            <td colspan="6" style="height:200px">데이터가 없습니다.</td>
        </tr>
    </c:if>
    <c:forEach items="${response.board}" var="item">
        <tr>
            <td>${item.categoryName}</td>
            <td>
                <a href="${item.id}">${item.title}</a>
<%--                    ${item.attachYn}--%>
                <c:if test="${item.attachYn == 'Y'}">
                    <i class="ico attach"></i>
                </c:if>
            </td>
            <td>${item.writer}</td>
            <td>${item.viewCnt}</td>
            <td>${item.createdAt}</td>
            <td>${item.updatedAt}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div class="btns-foot">
    <div class="left"></div>
    <div class="paging-area">
        <button class="first">&lt;&lt;</button>
        <button class="prev">&lt;</button>
        <ul class="paging">

        </ul>
        <button class="next">&gt;</button>
        <button class="last">&gt;&gt;</button>
    </div>
    <div class="right">
        <a href="${pageContext.request.contextPath}/board/save" class="btn btn-default">등록</a>
    </div>
</div>
<%@ include file="include/footer.jsp" %>

