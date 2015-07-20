var m_index_user;
var m_changePwd_dlg;
var m_index_orgId;
var m_index_permission;
var m_index_iconStyles = {};

$(function() {

	var obj = getCurrentUser();
	$("#labOrgName").text(obj.orgName);
	$("#userName").text(obj.name);

	m_index_orgId = obj.orgId;
	if (obj.isAllDataPermission) {
		m_index_permission = 1;
	} else {
		m_index_permission = 0;
	}
	var args = getUrlArgs();
	if (args.optType == 1 || args.optType == "1") {
		$.messager.confirm("系统提示", "页面过期，请重新登录！", function(r) {
			if (r) {
				top.location.href = "login.jsp";
			}
		});
	} else if (args.optType == 2 || args.optType == "2") {
		$.messager.alert("操作提示", "初始密码错误！", "error");
		onChangePwd();
	} else if (args.optType == 3 || args.optType == "3") {
		$.messager.alert("操作提示", "两次密码不一致，请检查！", "error");
		onChangePwd();
	} else if (args.optType == 4 || args.optType == "4") {
		$.messager.confirm("系统提示", "密码修改成功！是否重新登录？", function(r) {
			if (r) {
				top.location.href = "login.jsp";
			}
		});
	} else if (args.optType == 5 || args.optType == "5") {
		$.messager.alert("操作提示", "初始密码不能为空！", "error");
		onChangePwd();
	} else if (args.optType == 6 || args.optType == "6") {
		$.messager.alert("操作提示", "请输入新密码！", "error");
		onChangePwd();
	} else if (args.optType == 7 || args.optType == "7") {
		$.messager.alert("操作提示", "请确认密码！", "error");
		onChangePwd();
	}
	// isSignIn(gSetCurrentUserCallback);
	args.optType = 0;
	$('#treeMenu').tree({
		checkbox : false,
		cascadeCheck : true,
		onClick : onTreeMenuDblClick
	});

	loadMenu();
});

function loadMenu() {
	$.ajax({
		url : "index/getList.do",
		type : "POST",
		dataType : "json",
		async : false,
		success : function(req) {
			if (req.isSuccess) {

				// top.location.href="login.jsp";
				var nodes = buildTreeMenu(req.rows);
				$('#treeMenu').tree("loadData", nodes);
			} else {
				$.messager.alert('提示ʾ', req.msg, "warning");
			}
		}
	});
}
/**
 * 退出主页面，返回登录页面
 */
function onExit() {
	$.messager.confirm("系统提示", "是否退出主页，返回登录页面？", function(r) {
		if (r) {
			location = "index/onExit.do";
		}
	});
	// location = "index/onExit.do"
}
/**
 * 修改密码
 */
function onChangePwd() {
	m_changePwd_dlg = art.dialog({
		id : 'dlgchangePwd',
		title : '修改密码',
		content : document.getElementById("div_changePwd"),
		// content:"123",
		lock : true,
		initFn : function() {
		}
	});
}
/**
 * 
 */
function onTreeMenuDblClick(row) {
	var src = null;

	var user = getCurrentUser();

	switch (row.funcKey) {
	case "cust_data":
		src = "view/base/custuserList.jsp?viewType=1";
		break;
	case "cust_recharge":
		src = "view/sale/chargeList.jsp?optType=0&orgId="+m_index_orgId;
		break;
	case "cust_birthday":
		src = "view/base/birthdayInfoList.jsp";
		break;
	case "shop_stockin":
		src = "view/sale/stockList.jsp?billType=10";
		break;
	case "shop_stockout":
		src = "view/sale/stockList.jsp?billType=11";
		break;
	case "shop_inventory":
		src = "view/sale/inventory.jsp?orgId=" + m_index_orgId;
		break;
	case "shop_transfer":
		src = "view/sale/stockList.jsp?billType=12";
		break;
	case "sys_user":
		src = "view/base/user.jsp?optType=0";
		break;
	case "sys_shop":
		src = "view/base/organization.jsp";
		break;
	case "sys_setting":
		src = "view/base/sysparameter.jsp";
		break;

	case "biz_recharge":
		src = "view/sale/chargeList.jsp?optType=1&orgId="+m_index_orgId;
		break;
	case "biz_report":
		src = "view/sale/chargeList.jsp?optType=2&orgId="+m_index_orgId;
		break;
	case "sys_authorize":
		src = "view/base/user.jsp?optType=1";
		break;
	case "shop_sale":
		src = "view/sale/saleList.jsp?orgId=" + m_index_orgId+"&permission="+m_index_permission;
		break;
	case "shop_cash_sale":
		src = "view/sale/cashsaleList.jsp?orgId=" + m_index_orgId+"&permission="+m_index_permission;
		break;

	}
	if (user.isAllDataPermission == false) {
		if (checkAuthorize(row.funcKey, user.id)) {
			$("#ifrContent").attr("src", src);
		} else {
			$.messager.alert('提示ʾ', "用户:" + user.name + "没有对应的权限!", "warning");
		}
	} else {
		$("#ifrContent").attr("src", src);
	}

}

/**
 * 建立主菜单
 * 
 * @param items
 * @returns {Array}
 */
function buildTreeMenu(items) {
	var ss = [];
	var cache = {};

	if (items == null || items.length == 0) {
		return ss;
	}

	var count = items.length;

	for ( var i = 0; i < count; i++) {
		var node = items[i];
		node.text = node.name;
		cache[node.id] = node;
		createIconStyle(node);
		if (node.nodeLevel == 1) {
			ss.push(node);
		} else {
			var node2 = cache[node.parentId];
			if (node2.children == undefined) {
				node2.children = [];
			}
			node2.children.push(node);
		}
	}
	return ss;
}

function createIconStyle(node) {
	if (node.iconCls == undefined || node.iconCls == null) {
		if (node.icon != null && node.icon.length > 0) {
			var classId = "icon_main_menu_" + node.id;
			var tmpId = m_index_iconStyles[classId];
			if (tmpId == undefined || tmpId == null) {
				var style = "."
						+ classId
						+ "{	background:url('"
						+ location.href
						+ node.icon
						+ "');background-size:contain; width:16px; height:16px}";
				createStyle(style);
				m_index_iconStyles[classId] = classId;
			}
			node.iconCls = classId;
		}
	}
}

function createStyle(css) {
	try { // IE兼容
		var style = document.createStyleSheet();
		style.cssText = css;
	} catch (e) { // Firefox,Opera,Safari,Chrome兼容
		var style = document.createElement("style");
		style.type = "text/css";
		style.textContent = css;
		document.getElementsByTagName("HEAD").item(0).appendChild(style);
	}
}

function iframeSize() {
	var ifm = document.getElementById("ifrContent");
	var subWeb = document.frames ? document.frames["ifrContent"].document
			: ifm.contentDocument;
	if (ifm != null && subWeb != null) {
		ifm.height = subWeb.body.scrollHeight;
		ifm.width = subWeb.body.scrollWidth;
	}
}
