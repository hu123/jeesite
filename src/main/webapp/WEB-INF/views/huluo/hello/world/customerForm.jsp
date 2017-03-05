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
		function addRow(list, idx, tpl, row){
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});
		}
		function delRow(obj, prefix){
			var id = $(prefix+"_id");
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().removeClass("error");
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/hello/world/customer/">一对多增删改查列表</a></li>
		<li class="active"><a href="${ctx}/hello/world/customer/form?id=${customer.id}">一对多增删改查<shiro:hasPermission name="cms:article:view">${not empty customer.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="cms:article:view">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="customer" action="${ctx}/hello/world/customer/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">customer_name：</label>
			<div class="controls">
				<form:input path="customerName" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">order：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>order_name</th>
								<shiro:hasPermission name="cms:article:view"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="orderList">
						</tbody>
						<shiro:hasPermission name="cms:article:view"><tfoot>
							<tr><td colspan="3"><a href="javascript:" onclick="addRow('#orderList', orderRowIdx, orderTpl);orderRowIdx = orderRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="orderTpl">//<!--
						<tr id="orderList{{idx}}">
							<td class="hide">
								<input id="orderList{{idx}}_id" name="orderList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="orderList{{idx}}_delFlag" name="orderList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="orderList{{idx}}_orderName" name="orderList[{{idx}}].orderName" type="text" value="{{row.orderName}}" maxlength="10" class="input-small "/>
							</td>
							<shiro:hasPermission name="cms:article:view"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#orderList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var orderRowIdx = 0, orderTpl = $("#orderTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(customer.orderList)};
							for (var i=0; i<data.length; i++){
								addRow('#orderList', orderRowIdx, orderTpl, data[i]);
								orderRowIdx = orderRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="cms:article:view"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>