<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>一对多增删改查管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/hello/world/order/">一对多增删改查列表</a></li>
		<li class="active"><a href="${ctx}/hello/world/order/form?id=${order.id}">一对多增删改查<shiro:hasPermission name="cms:article:view">${not empty order.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="hello:world:order:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="order" action="${ctx}/hello/world/order/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">order_name：</label>
			<div class="controls">
				<form:input path="orderName" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">customer_id：</label>
			<div class="controls">
				<form:input path="customerId" htmlEscape="false" maxlength="4" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="cms:article:view"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>