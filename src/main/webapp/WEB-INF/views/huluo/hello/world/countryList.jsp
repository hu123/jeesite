<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>cityCountry一对多增删改查管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {

        });
        function page(n, s) {
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/hello/world/country/">cityCountry一对多增删改查列表</a></li>
    <shiro:hasPermission name="cms:article:view">
        <li><a href="${ctx}/hello/world/country/form">cityCountry一对多增删改查添加</a></li>
    </shiro:hasPermission>
</ul>
<form:form id="searchForm" modelAttribute="country" action="${ctx}/hello/world/country/" method="post"
           class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">

        <li><label>name：</label>
            <form:input path="countryName" htmlEscape="false" maxlength="10" class="input-medium"/>
        </li>
        <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
        <li class="clearfix"></li>
    </ul>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <shiro:hasPermission name="cms:article:view">
            <th>操作</th>
        </shiro:hasPermission>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="country">

        <tr>
            <shiro:hasPermission name="cms:article:view">
                <td><a href="${ctx}/hello/world/country/form?id=${country.id}">
                        ${country.countryName}
                </a></td>
                <td>
                    <a href="${ctx}/hello/world/country/form?id=${country.id}">修改</a>
                    <a href="${ctx}/hello/world/country/delete?id=${country.id}"
                       onclick="return confirmx('确认要删除该cityCountry一对多增删改查吗？', this.href)">删除</a>
                </td>
            </shiro:hasPermission>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>