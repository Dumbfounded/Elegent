<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/inc/taglib.jsp"%>
<div class="pageContent">
	<form method="post" action="${requestScope.record ==null ?'system/permission/insert' :'system/permission/update' }" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<!-- 隐藏表单 -->

		<c:if test="${requestScope.record ==null}">
			<!-- 添加的逻辑 -->
			<c:if test="${requestScope.pid  !=null}">
				<!-- 添加子节点 -->
				<input type="hidden" name="pid" value="${requestScope.pid}">
				<input type="hidden" name="level" value="${requestScope.level+1}">
			</c:if>
			<c:if test="${requestScope.pid  ==null}">
				<!-- 添加父节点 -->
				<input type="hidden" name="level" value="0">
			</c:if>
		</c:if>

		<c:if test="${requestScope.record !=null}">
			<!-- 编辑逻辑 -->
			<input type="hidden" name="id" value="${requestScope.record.id}">
			<input type="hidden" name="pid" value="${requestScope.record.pid}">
			<input type="hidden" name="level" value="${requestScope.record.level}">
		</c:if>


		<div class="pageFormContent" layoutH="57">
			<dl class="nowrap">
				<dt>菜单名字：</dt>
				<dd>
					<input name="name" class="required" value="${requestScope.record.name}">
				</dd>
			</dl>
			<c:if test="${requestScope.record ==null}">
				<!-- 添加的逻辑 -->
				<c:if test="${requestScope.pid  !=null}">
					<!-- 添加子节点 -->
					<dl class="nowrap">
						<dt>url：</dt>
						<dd>
							<input name="url" class="required">
						</dd>
					</dl>
				</c:if>
			</c:if>
			<c:if test="${requestScope.record !=null}">
				<!-- 编辑逻辑 -->
				<c:if test="${requestScope.record.level  != 0}">
					<!-- 添加子节点 -->
					<dl class="nowrap">
						<dt>url：</dt>
						<dd>
							<input name="url" class="required" value="${requestScope.record.url}">
						</dd>
					</dl>
				</c:if>
			</c:if>
			<dl class="nowrap">
				<dt>排序：</dt>
				<dd>
					<input name="sort" class="required digits" value="${requestScope.record.sort}">
				</dd>
			</dl>

			<dl class="nowrap">
				<dt>备注：</dt>
				<dd>
					<textarea cols="45" rows="5" name="remarks">${requestScope.record.remarks }</textarea>
				</dd>
			</dl>
			<div class="divider"></div>
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">保存</button>
						</div>
					</div>
				</li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" id="closeBtn" class="close">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>
<script type="text/javascript">
	$("#closeBtn").click(function(){
		$("#permissionBox").empty();
	})
</script>
