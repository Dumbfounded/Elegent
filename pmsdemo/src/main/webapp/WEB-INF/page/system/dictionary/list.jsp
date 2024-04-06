<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/inc/taglib.jsp"%>
<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath}/system/dictionary/list" method="post">
		<!-- 分页表单参数 -->
		<%@include file="/WEB-INF/page/inc/pageForm.jsp"%>
		<div class="searchBar">
			<table class="searchContent">
				<tr>
				
					<td></td>
					<%-- <td>姓名：<input type="text" name="name" value="${requestScope.dwzPageBean.countResultMap.name}" size="30" alt="姓名" />
					</td>
					<td>手机号码：<input type="text" name="phone" value="${requestScope.dwzPageBean.countResultMap.phone}" size="30" alt="手机号码" />
					</td> --%>
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
			<li><a class="add" href="${pageContext.request.contextPath}/system/dictionary/toInsert" rel="insert" target="dialog" mask="true"><span>添加</span></a></li>
			<%-- <li><a class="edit" href="${pageContext.request.contextPath}/system/dictionary/toUpdate?id={id}" rel="update" target="dialog" warn="请选择一条记录" mask="true"><span>修改</span></a></li>
			<li><a class="delete" href="${pageContext.request.contextPath}/system/dictionary/delete?id={id}" target="ajaxTodo" title="确定要删除吗？" warn="请选择一条记录" mask="true"><span>删除</span></a></li>
		 --%>	
		 <li><a class="delete" href="${pageContext.request.contextPath}/system/dictionary/delete" target="selectedTodo" rel="id" title="确定要删除吗？" warn="请最小选择一条记录" mask="true"><span>批量删除</span></a></li>

		</ul>
	</div>
	<table class="table" targetType="navTab" asc="asc" desc="desc" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="id" class="checkboxCtrl"></th>
				<th>key</th>
				<th>value</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${requestScope.dwzPageBean != null &&  requestScope.dwzPageBean.recordList != null }">
				<c:forEach var="record" items="${requestScope.dwzPageBean.recordList}" varStatus="status">
					<tr target="id" rel="${record.id}">
						<td><input name="id" value="${record.id}" type="checkbox"></td>
						<td>${record.dkey }</td>
						<td>${record.dvalue }</td>
						<td>
							<a title="编辑" target="dialog" mask="true" href="${pageContext.request.contextPath}/system/dictionary/toUpdate?id=${record.id}" class="btnEdit">编辑</a>
						 	<a title="确定要删除吗？" mask="true" target="ajaxTodo" href="${pageContext.request.contextPath}/system/dictionary/delete?id=${record.id}" class="btnDel">删除</a>
						 </td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<!-- 分页条 -->
	<%@include file="/WEB-INF/page/inc/pageBar.jsp"%>
</div>