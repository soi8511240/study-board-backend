<%--
  Created by IntelliJ IDEA.
  User: soi85
  Date: 2025-02-24
  Time: 오후 3:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="//code.jquery.com/ui/1.14.1/themes/base/jquery-ui.css" />
    <link rel="stylesheet" href="/css/style.css" />
    <script src="//code.jquery.com/jquery-3.7.1.js"></script>
    <script src="//code.jquery.com/ui/1.14.1/jquery-ui.js"></script>
</head>
<body>
<script type="text/javascript">
    const LocalStorage = window.localStorage;
    const CODES_STORAGE_NAME = 'codes';
    /**
     * common code LocalStorage에 저장
     * getCodes( String key ) 로 사용
     * Obejct 반환
     *
     * @param codes
     */
    const __setCodesToStorage = (codes)=>{
        if (LocalStorage.getItem(CODES_STORAGE_NAME) === null){
            LocalStorage.setItem(CODES_STORAGE_NAME, JSON.stringify(codes));
        }
    }
    const __getCodesFromStorage = async ()=>{
        const codes = LocalStorage.getItem(CODES_STORAGE_NAME);
        if (codes === null){
            await initCodes();
        }
        return JSON.parse(LocalStorage.getItem(CODES_STORAGE_NAME));
    }

    const getCodes = async (key)=>{
        const codes = await __getCodesFromStorage();
        return codes[key];
    }

    const initCodes = ()=>{
        $.ajax({
            url: "/api/commonCodes",
            type: "get",
            success: __setCodesToStorage,
        })
    }

    $(async function(){
        await initCodes();
    })
</script>

<div class="wrap">