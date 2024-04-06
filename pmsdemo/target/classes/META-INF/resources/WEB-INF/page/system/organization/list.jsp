<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/inc/taglib.jsp"%>
<!-- 引用ztree 的css -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/statics/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath}/system/organization/list" method="post">
		<!-- 分页表单参数 -->
		<%@include file="/WEB-INF/page/inc/pageForm.jsp"%>
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td></td>
				</tr>
			</table>
			<div class="subBar">
				<!-- <ul>
                        <li><div class="buttonActive">
                                        <div class="buttonContent">
                                                <button type="submit">查询</button>
                                        </div>
                                </div></li>
                </ul> -->
			</div>
		</div>
	</form>
</div>
<div class="pageContent j-resizeGrid">
	<!-- 按钮 -->
	<div class="panelBar">
		<ul class="toolBar">
			<!-- 局部刷新 在a标签添加属性  target="ajax" 和添加 rel="所要刷新的位置"-->
			<li><a class="add" id="addBth" mask="true" href="${pageContext.request.contextPath}/system/organization/toInsert"><span>添加</span></a></li>
			<li><a class="edit" id="editBth" mask="true" href="${pageContext.request.contextPath}/system/organization/toUpdate?id=" rel="update"><span>修改</span></a></li>
			<li><a class="delete" id="deleteBth" mask="true" href="${pageContext.request.contextPath}/system/organization/delete?id="><span>删除</span></a></li>
		</ul>
	</div>
	<!-- 左布局 -->
	<div style="float: left; display: block; overflow: auto; width: 240px; border: solid 1px #CCC; background: #fff">
		<!-- <ul class="tree treeFolder"> -->
		<ul layoutH="100" id="organizationTree" class="ztree"></ul>
	</div>
	<!-- 右布局 -->
	<div id="organizationBox" class="unitBox" style="margin-left: 246px;"></div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/ztree/js/jquery.ztree.core.js"></script>
<SCRIPT type="text/javascript">
	//声明属性setting
	var setting = {
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			onClick : clickTree
		}
	};

	var id = null;
	var level = null;

	//点击树的时候的回调
	function clickTree(event, treeId, treeNode, clickFlag) {
		//记录点击节点level
		//debugger 前端断点
		level = treeNode.level;
		id = treeNode.id;
	}

	//树的数据源
	var zNodes = ${requestScope.ztreeBeans};

	$(document, navTab.getCurrentPanel()).ready(function() {
		//ztree初始化  ztree 设置参数
		$.fn.zTree.init($("#organizationTree", navTab.getCurrentPanel()), setting, zNodes);
	});

	$("#addBth", navTab.getCurrentPanel()).click(function() {

		if (level == 1) {
			alertMsg.error("等级不能超过2级");
			return false;
		}
		//局部刷新的方案
		if (id) {
			//假设选中父亲id，那把父亲id带到后台
			$("#organizationBox", navTab.getCurrentPanel()).loadUrl($(this, navTab.getCurrentPanel()).attr("href") + "?pid=" + id + "&level=" + level, null, null);
		} else {
			$("#organizationBox", navTab.getCurrentPanel()).loadUrl($(this, navTab.getCurrentPanel()).attr("href"), null, null);
		}
		//把a标签原有的跳转逻辑禁用
		return false;
	})

	$("#editBth", navTab.getCurrentPanel()).click(function() {
		if (id) {
			$("#organizationBox", navTab.getCurrentPanel()).loadUrl($(this, navTab.getCurrentPanel()).attr("href") + id, null, null);
		} else {
			alertMsg.error("请选择要编辑的记录");
		}
		//把a标签原有的跳转逻辑禁用
		return false;
	});

	$("#deleteBth", navTab.getCurrentPanel()).click(function() {
		if (id) {
			//为了防止this局部的问题，可以先赋值到全局的funtion里面
			 var url = $(this, navTab.getCurrentPanel()).attr("href") + id ;
			alertMsg.confirm("是否要删除？", {
				okCall : function() {
					//是jui提供的方法
					ajaxTodo(url);
				}
			});

		} else {
			alertMsg.error("请选择要删除的记录");
		}
		//把a标签原有的跳转逻辑禁用
		return false;
	})
</SCRIPT>







