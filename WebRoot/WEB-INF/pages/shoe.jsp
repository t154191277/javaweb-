<%@ page language="java" import="java.util.*,domain.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'shoe.jsp' starting page</title>
    <link href="css.css" rel="stylesheet" type="text/css" />
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/lrtk.js"></script>



  </head>
  
  <body>
  	<%
  		List<String> imgList= (ArrayList)session.getAttribute("imgList");
  		Shoe shoe = (Shoe)session.getAttribute("shoe");
  	 %>
  	<div id="playBox">
    <div class="pre"></div>
    <div class="next"></div>
    <div class="smalltitle">
      <ul>
          <%
    	for(int i = 0;i < imgList.size();i++){
    		String imgPath = imgList.get(i);
     	%>
        <li class="thistitle"></li>
	<%
	}
	 %>
      </ul>
    </div>
    <ul class="oUlplay">
    <%
    	for(int i = 0;i < imgList.size();i++){
    		String imgPath = imgList.get(i);
     %>
       <li><img src="<%=imgPath%>"></a></li>
      <%
      	}
       %>
    </ul>
  </div>
 	<div id="info">
 	 	<h1><%=shoe.getBrand() %></h1>
 	 	<h4><%=shoe.getName() %></h4>
 	 	<h4><%=shoe.getShoeID() %></h4>
 	 	<h4><%=shoe.getStatus() %></h4>
 	 	<h3>$<%=shoe.getPrice() %></h3>
 	 	<input type="text" class="input" maxlength="1" align="center" id="num"></input>
 	 	<div class="info1">
 	 	<script type="text/javascript" charset="gbk">
 	 		function add2cart(){
 	 			var str1 = "${pageContext.request.contextPath}/servlet/ShoeDetailServlet?productID=<%=shoe.getShoeID()%>&num=";
 	 			var str2 = document.getElementById("num").value;
 	 			var reg1 = /\d/;
 	 			if (!reg1.test(str2)) {
 	 				alert("请输入正确的数量！");
 	 				return;
 	 				}
 	 			window.location.href = str1 + str2;
 	 		}
 	 	</script>	
 		<input type="button" class="btn" onclick="add2cart()" value="ADD TO CART">
 		</div>
  </div>
  </body>
</html>
