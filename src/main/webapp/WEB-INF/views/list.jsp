<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="include/header.jsp" %>

<form action="/board/list" method="get" id="searchForm">
    <div class="searchbar">
        <span>등록일</span>
        <div class="area-datepicker">
            <input type="text" class="input-datepicker" name="fromDt" id="fromDt" />
            <input type="text" class="input-datepicker" name="toDt" id="toDt" />
        </div>
        <select class="select" name="categoryId" id="categorySelect">
            <option value="">전체</option>
            <c:forEach items="${response.category}" var="item">
                <option value="${item.id}">${item.name}</option>
            </c:forEach>
        </select>
        <input type="text" id="keyword" name="keyword" placeholder="검색어를 입력해 주세요. (제목 + 작성자 + 내용)" value="" />
        <button id="sr-button" class="btn-sr" type="button">검색</button>
        <input type="hidden" id="currentPage" name="currentPage" value="1" />
    </div>
</form>

<script type="text/javascript">

const DATE_FORMAT = "yy-mm-dd";
const dateFormatter = (date)=>{
    return date.toISOString().split('T')[0];
}

// DATE 오늘, 1년전 셋팅
const now = new Date();
const oneYearAgo = new Date();
oneYearAgo.setFullYear(oneYearAgo.getFullYear() - 1);

/**
 * session 함수
 */
const session = window.sessionStorage;
const FILTERS = 'filters';
const saveSession = (key, value)=>{
    session.setItem(key, value);
}
const getSession = (key)=>{
    return session.getItem(key) || null;
}
const removeSession = (key)=>{
    session.removeItem(key);
}

const setCurrentPage = (page)=>{
    document.querySelector('#currentPage').value = page;
}
const initCurrentPage = ()=>{
    setCurrentPage(1);
}

const goSearch = ()=>{
    let filters = {
        fromDt: document.querySelector('#fromDt').value,
        toDt: document.querySelector('#toDt').value,
        categoryId: document.querySelector('#categorySelect').value,
        keyword: document.querySelector('#keyword').value,
    }
    saveSession(FILTERS, JSON.stringify(filters));

    $('#searchForm').submit();
}

const onload = ()=>{
    const filters = getSession(FILTERS);
    // removeSession(FILTERS);
    if(filters !== null){
        const {fromDt, toDt, categoryId, keyword} = JSON.parse(filters);
        document.querySelector('#fromDt').value = fromDt;
        document.querySelector('#toDt').value = toDt;
        document.querySelector('#categorySelect').value = categoryId;
        // $("#categorySelect").val(categoryId).prop("selected", true);
        document.querySelector('#keyword').value = keyword;
    }
}

const initSearchBar = async ()=> {

    /* date Input */
    $('#fromDt, #toDt')
        .datepicker({dateFormat: DATE_FORMAT});
    $('#fromDt')
        .datepicker('setDate', dateFormatter(oneYearAgo));
    $('#toDt')
        .datepicker('setDate', dateFormatter(now));

    /* category Select */
    // const $select = document.querySelector('#categorySelect');
    // const categories = await getCodes('category');
    // categories.forEach(item => {
    //     $select.append(new Option(item.name, item.id, false, false));
    // })

    /* button */
    document.querySelector('#sr-button').addEventListener('click', e=>{
        initCurrentPage(1);
        goSearch();
    });

    document.querySelector('#keyword').focus();
    document.querySelector('#keyword').addEventListener('keyup', function (e) {
        e.preventDefault();

        if (e.keyCode === 13) {
            initCurrentPage(1);
            goSearch();
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
    <c:if test="${response.totalCnt != null}">
    총  ${response.totalCnt}건
    </c:if>
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
    <c:if test="${response.totalCnt != null}">
    <div class="paging-area">
        <button class="first">&lt;&lt;</button>
        <button class="prev">&lt;</button>
        <ul class="paging">
            <c:set var="pageCnt" value="${(response.totalCnt + response.search.fetchCnt - 1) / response.search.fetchCnt}" />
            <c:forEach begin="1" end="${pageCnt}" var="i">
                <c:if test="${i == response.search.currentPage}">
                    <li><button class='active'>${i}</button></li>
                </c:if>
                <c:if test="${i != response.search.currentPage}">
                    <li><button>${i}</button></li>
                </c:if>
            </c:forEach>
        </ul>
        <button class="next">&gt;</button>
        <button class="last">&gt;&gt;</button>
    </div>
    </c:if>
    <div class="right">
        <a href="${pageContext.request.contextPath}/board/save" class="btn btn-default">등록</a>
    </div>
</div>
<script type="text/javascript">
const btns = document.querySelectorAll('.paging-area button');
const currentPage = ${response.search.currentPage};
const totalListCnt = ${pageCnt};
console.log('currentPage',currentPage);
console.log('totalListCnt',totalListCnt);
btns.forEach(btn => {
    btn.addEventListener('click', (e) => {
        switch (e.target.attributes.class?.value) {
            case 'first':
                if ( currentPage === 1 ) {
                    return;
                }

                initCurrentPage();

                break;
            case 'prev':
                if ( currentPage === 1 ) {
                    return;
                }
                setCurrentPage(currentPage-1);

                break;
            case 'next':
                if ( currentPage+1 > totalListCnt ) {
                    return;
                }
                setCurrentPage(currentPage+1);

                break;
            case 'last':
                if ( currentPage === totalListCnt ) {
                    return;
                }
                setCurrentPage(totalListCnt);
                break;

            case 'active':
                break;

            default:
                setCurrentPage(e.target.innerText);

                break;
        }

        goSearch();
    })
})

window.onload = onload;
</script>
<%@ include file="include/footer.jsp" %>

