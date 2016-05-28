<%@ page language="java" import="java.util.*,domain.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'main.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
   <link rel="stylesheet" type="text/css" href="css/1.css" media="all"/>
   
   <script src="js/layer.js" type="text/javascript" ></script>

  </head>
  
  <body>
  	<%
  		request.setCharacterEncoding("gbk");
  	 %>
  	 	<ul id="logout">
  	 	<li class="div1"><button class="btn" onclick="logout()">注销</button></li>
  	 	<li class="div2"><button class="btn" onclick="mycart()">购物车</button></li>
  	 	</ul>

  		
    <%
    	List<Shoe> shoeList = (ArrayList<Shoe>)session.getAttribute("shoeList");
    	for(int i=0;i<shoeList.size();i++){
			Shoe shoe = shoeList.get(i);
    	 	if(i%3==0){
    	  %>
    		<ul class="products-grid">
    		<%
    			}
    		 %>
    			<li class="item">
    			<div class="div1">
    			<div><a href="${pageContext.request.contextPath}/servlet/ShoeDetailUIServlet?shoeID=<%=shoe.getShoeID()%>"><img src="<%=shoe.getImgPath()%>"></a></div>
    			<div class="div2">
    			<a href="${pageContext.request.contextPath}/servlet/ShoeDetailUIServlet?shoeID=<%=shoe.getShoeID()%>"></a>
    			<div>
    			<h2><a href="${pageContext.request.contextPath}/servlet/ShoeDetailUIServlet?shoeID=<%=shoe.getShoeID()%>" title="xx">
    			<span><%=shoe.getBrand() %></span><br>
    			<span><%=shoe.getName() %></span></a></h2>
    			<a href="${pageContext.request.contextPath}/servlet/ShoeDetailUIServlet?shoeID=<%=shoe.getShoeID()%>">
    			<div>
    			<span>$<%=shoe.getPrice() %></span>
    			</div>
    			</a>
    			</div>
    			</div>
    			</div>
    			</li>
    		<%
    			if((i+1)%3==0){
    		 %>
    		</ul>
    		 <%
    			}
    		 %>
    		<%
    			}
    		 %>
  </body>
</html>
