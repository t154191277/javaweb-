<%@ page language="java" import="java.util.*,domain.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'cart.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/1.css">
	<script type="text/javascript" src="js/layer.js">
	</script>
  </head>
  
  <body>
  		<% 
    	  	if(session.isNew()){
  	  			request.getRequestDispatcher("index.jsp").forward(request, response);
  	  			return;
  	  		}
  	  		request.setCharacterEncoding("gbk");
  			List<Cart> list = (ArrayList)session.getAttribute("cart");
  			double total = (Double)session.getAttribute("total");
  		%>
  		 <div id="toCart"><button class="btn" onclick="index()">INDEX</button></div>
  		<div id="frame">
  		<%
  			for(Cart cart : list){
  		 %>
  		<ul class="layer">
  			<li class="item1">
  				<a href="${pageContext.request.contextPath}/servlet/ShoeDetailUIServlet?shoeID=<%=cart.getProductID()%>">
  				<img class="pic" src="<%=cart.getImg()%>">
  				</a>
  			</li>
  			<li class="item2">
  				<span><%=cart.getName()%></span><br><br>
  				<span><%=cart.getProductID() %></span>
  			</li>
  			<li class="item3">
  				<span><%=cart.getPrice() %></span>
  			</li>
  			 <li class="item4">
  			 	<button onclick="des(<%=cart.getNum() %>,'<%=cart.getProductID() %>')">-</button>
  				<span><%=cart.getNum() %></span>
  				<button onclick="ins(<%=cart.getNum() %>,'<%=cart.getProductID() %>')">+</button>
  			</li>
  			<li class="item5">
  				<span>$<%=cart.getTotal() %></span>
  			</li>
  			<li class="item6">
  				<button onclick="del('<%=cart.getProductID() %>')">del</button>
  			</li>
  		</ul>
  		<%
  			}
  		 %>
  		<div class="total">
  			<h4>总价： $<%=total %></h4>
  		</div>
  		<div class="submit">
  			<button class="btn">结算</button>
  		</div>
  		
  	</div>
  </body>
</html>
