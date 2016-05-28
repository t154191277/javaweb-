function login(){
	var path = getRootPath();
	var str1 = path + "/servlet/LoginServlet";
	var name = "name=" + getName();
	var pwd = "pwd=" + getPwd();
	var url = str1 + "?" + name + "&" + pwd;
	window.location.href = url;
}

function getName(){
	return document.getElementById("name").value;
}

function getPwd(){
	return document.getElementById("pwd").value;
}

function getEmail(){
	return document.getElementById("email").value;
}

function getName1(){
	return document.getElementById("name1").value;
}

function getPwd1(){
	return document.getElementById("pwd1").value;
}

function mycart(){
	var path = getRootPath();
	var str = path + "/servlet/CartUIServlet";
	window.location.href = str;
}

function logout(){
	var path = getRootPath();
	var str = path + "/servlet/LogoutServlet";
	window.location.href = str;
}

function register(){
	var path = getRootPath();
	var str = path + "/servlet/RegisterServlet";
	var name = "name=" + getName1();
	var pwd = "pwd=" + getPwd1();
	var email = "email=" + getEmail();
	var url = str + "?" + name + "&" + pwd + "&" + email;
	window.location.href = url;
}

function des(num,id){
	path = getRootPath();
 	var str1 = path + "/servlet/CartUpdateServlet?shoeID=" + id;
 	if(num==1) return;
 	var num = num - 1; 
	var str2 = "num=" + num;
	var str = str1 + "&" + str2;
	window.location.href = str;
}

function ins(num,id){
	path = getRootPath();
	var str1 = path + "/servlet/CartUpdateServlet?shoeID=" + id;
 	var num = num + 1; 
	var str2 = "num=" + num;
	var str = str1 + "&" + str2;
	window.location.href = str;
}

function del(id){
	path = getRootPath();
	var str = path + "/servlet/CartDeleteServlet?shoeID=" + id;
	window.location.href = str;
}

function mycart(){
		path = getRootPath();
		str = path + "/servlet/CartUIServlet";
		window.location.href = str;
	}
function index(){
	path = getRootPath();
	str = path + "/servlet/ShoeUIServlet";
	window.location.href = str;
}
function getRootPath(){
	//获取当前网址，如： http://localhost:7070/mbserv/share/meun.jsp
	var curWwwPath=window.document.location.href;
	//获取主机地址之后的目录，如： mbserv/share/meun.jsp
	var pathName=window.document.location.pathname;
	var pos=curWwwPath.indexOf(pathName);
	//获取主机地址，如： http://localhost:7070
	var localhostPaht=curWwwPath.substring(0,pos);
	//获取带"/"的项目名，如：/uimcardprj
	var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
	return(localhostPaht+projectName);
	}