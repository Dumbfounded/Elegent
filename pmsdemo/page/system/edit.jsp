<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/inc/taglib.jsp"%>
<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath}${requestScope.record ==null ?'/system/permission/insert' :'/system/permission/update' }" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<!-- 关闭窗口 -->
			<input type="hidden" name="callbackType" value="closeCurrent" />
			<!-- 隐藏表单 -->
			
			<c:if test="${requestScope.record !=null}">
																	
					<input type="hidden" name="id" value="${requestScope.record.id}" />
																																																																																							
			</c:if>
			
																						<p>
					<label>父级id</label> <input name="pid" class="required" maxlength="32" type="text" value="${requestScope.record.pid}"  />
				</p>
								<p>
					<label>名字</label> <input name="name" class="required" maxlength="50" type="text" value="${requestScope.record.name}"  />
				</p>
								<p>
					<label>url</label> <input name="url" class="required" maxlength="500" type="text" value="${requestScope.record.url}"  />
				</p>
								<p>
					<label>序号</label> <input name="sort" class="required" maxlength="8,0" type="text" value="${requestScope.record.sort}"  />
				</p>
								<p>
					<label>备注</label> <input name="remarks" class="required" maxlength="200" type="text" value="${requestScope.record.remarks}"  />
				</p>
								<p>
					<label>等级</label> <input name="level" class="required" maxlength="8,0" type="text" value="${requestScope.record.level}"  />
				</p>
								<p>
					<label>图标</label> <input name="icon" class="required" maxlength="100" type="text" value="${requestScope.record.icon}"  />
				</p>
								<p>
					<label>类型 1-显示菜单、2-功能菜单、3-功能按钮</label> <input name="type" class="required" maxlength="8,0" type="text" value="${requestScope.record.type}"  />
				</p>
						
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">保存</button>
						</div>
					</div></li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>
