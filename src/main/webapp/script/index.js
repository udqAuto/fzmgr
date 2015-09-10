var m_index_user;
var m_changePwd_dlg;
var m_index_orgId;
var m_index_user={};
var m_index_permission;
var m_index_iconStyles = {};

$(function() {  
	m_index_user = getCurrentUser();
	m_index_orgId = m_index_user.orgId;
	$('#treeMenu').tree({
		checkbox : false, 
		onClick : onTreeMenuDblClick
	}); 
});
 
/**
 * 退出主页面，返回登录页面
 */
function onExit() {
	$.messager.confirm("系统提示", "是否退出主页，返回登录页面？", function(r) {
		if (r) {
			location = "login/onExit.do";
		}
	}); 
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
	switch (row.text) {
	case "车主管理":
		src = "view/user/userListInfo.jsp?userType=8";
		break;
	case "用户管理":
		src = "view/user/userListInfo.jsp?userType=1,2,4";
		break;
	case "未完成订单":
		src = "view/order/orderListInfo.jsp?orderState=1,2,3&orgId="+m_index_orgId;
		break;
	case "已完成订单":
		src = "view/order/orderListInfo.jsp?orderState=4,5&orgId="+m_index_orgId;
		break;
	case "已取消订单":
		src = "view/order/orderListInfo.jsp?orderState=10,11&orgId="+m_index_orgId;
		break;
	case "车辆管理":
		src = "view/auto/autoListInfo.jsp";
		break;
	case "组织机构管理":
		src = "view/organ/organListInfo.jsp";
		break;
	case "机构区域管理":
		src = "view/region/regionListInfo.jsp";
		break; 
	case "洗车类型管理":
		src = "view/washType/washTypeListInfo.jsp";
		break; 
	case "用户":
		src = "view/test.jsp";
		break;
	}
	$("#ifrContent").attr("src", src); 
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
