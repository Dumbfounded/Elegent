<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/taglib.jsp"%>
<!-- 引用ztree 的css -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/statics/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">

<div class="pageContent">
	<form id="form" method="post" action="${pageContext.request.contextPath}/system/role/updatePermission" class="pageForm required-validate" >
		<!-- 关闭窗口 -->
		<input type="hidden" name="callbackType" value="closeCurrent" />
		<!-- 角色id -->
		<input type="hidden" name="id" value="${requestScope.id }">
		<div class="pageFormContent" layoutH="56">
			 <ul id="treeDemo" class="ztree"></ul>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/ztree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/ztree/js/jquery.ztree.excheck.js"></script>
<SCRIPT type="text/javascript">
		var setting = {
			check: {
				enable: true,
				chkboxType: { "Y": "ps", "N": "s" }
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};

		var zNodes =${requestScope.ztreeBeans};
		 
		
		$(document, $.pdialog.getCurrent()).ready(function(){
			$.fn.zTree.init($("#treeDemo", $.pdialog.getCurrent()), setting, zNodes);
		});
		//onsubmit=""
		$("#form", $.pdialog.getCurrent()).submit(function(){
			
			//1.获取所有选中的节点
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
			var nodes = treeObj.getCheckedNodes(true);
			//2.如果让表单识别，获取的内容并且帮我们提交？
			if(nodes){
				for(var i = 0 ;i<nodes.length;i++){
					//;
					$(this).append("<input type='hidden' name='pid' value='"+nodes[i].id+"'>");
				}
			}
			 
			return validateCallback(this, dialogAjaxDone);
		})
	</SCRIPT>
