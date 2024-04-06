<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/inc/taglib.jsp"%>
<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath}/system/role/list" method="post">
		<!-- 分页表单参数 -->
		<%@include file="/WEB-INF/page/inc/pageForm.jsp"%>
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td></td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">查询</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${pageContext.request.contextPath}/system/role/toInsert" rel="insert" target="dialog" mask="true"><span>添加</span></a></li>
			<li><a class="delete" href="${pageContext.request.contextPath}/system/role/delete" rel="id" target="selectedTodo" title="确定要删除吗？" warn="请最小选择一条记录" mask="true"><span>批量删除</span></a></li>
		</ul>
	</div>
	<table class="table" targetType="navTab" asc="asc" desc="desc" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="id" class="checkboxCtrl"></th>
				<th>名字</th>
				<th>备注</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>

			<c:if test="${requestScope.dwzPageBean != null &&  requestScope.dwzPageBean.recordList != null }">
				<c:forEach var="record" items="${requestScope.dwzPageBean.recordList}" varStatus="status">
					<tr>

						<td><input name="id" value="${record.id}" type="checkbox"></td>
						<td>${record.name}</td>
						<td>${record.remarks}</td>

						<td>
						<a title="分配权限" target="dialog" mask="true" href="${pageContext.request.contextPath}/system/role/toUpdatePermission?id=${record.id}" class="editBtn"><span>分配权限</span></a> 
						   <a title="确定要删除吗？" mask="true" target="ajaxTodo" href="${pageContext.request.contextPath}/system/role/delete?id=${record.id}" class="btnDel" mask="true">删除</a> 
						   <a title="编辑" target="dialog" href="${pageContext.request.contextPath}/system/role/toUpdate?id=${record.id}" class="btnEdit" mask="true">编辑</a>
						</td>




					</tr>
				</c:forEach>
			</c:if>

		</tbody>
	</table>
	<!-- 分页条 -->
	<%@include file="/WEB-INF/page/inc/pageBar.jsp"%>
</div>