<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.hotel.beans.HabitacionesCarrito"%>
<%@page import="com.hotel.controllers.HabitacionesController"%>
<%@ page session="true"%>
<%
//afregamos seguridad al uso de sesiones, evitando que al recargar o al hacer atras se mantengan las sesiones
response.setHeader("Pragma","no-cache");
response.addHeader("Cache-control","must-revalidate");
response.addHeader("Cache-control","no-cache");
response.addHeader("Cache-control","no-store");
response.setDateHeader("Expires", 0);

try{
if(session.getAttribute("email") ==null && session.getAttribute("nivel")==null){
	//request.getRequestDispatcher("login.jsp").forward(request, response);//despacho al login si no hay variables de sesion
	response.sendRedirect("login.jsp");//redirigimos en el cliente para evitar reenvio de formulario de login
}

}catch(Exception e){
	//request.getRequestDispatcher("login.jsp").forward(request, response);//si hay excepcion tambien redirijo al login
	response.sendRedirect("login.jsp");//redirigimos en el cliente para evitar reenvio de formulario de login
}


%>


<%
	//recuperamos la variable de sesion
	HttpSession sesion=request.getSession(true);
	List<HabitacionesCarrito> listaHabitaciones=new ArrayList<>();
	/*si no existe la lista dejamos null, pero si existe la recupero*/
	if(sesion.getAttribute("carrito") == null){
		listaHabitaciones=null;
	}
	else{
		listaHabitaciones=(ArrayList<HabitacionesCarrito>) sesion.getAttribute("carrito");
	}
	

%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>Hotel | Checkout</title>
<link
	href="${pageContext.request.contextPath}/mainCliente/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/mainCliente/css/font-awesome.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/mainCliente/css/prettyPhoto.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/mainCliente/css/price-range.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/mainCliente/css/animate.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/mainCliente/css/main.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/mainCliente/css/responsive.css"
	rel="stylesheet">
<!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
<link rel="shortcut icon" href="images/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="images/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="images/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="images/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="images/ico/apple-touch-icon-57-precomposed.png">
</head>
<!--/head-->

<body>

	<jsp:include page="/mainCliente/cabeceraCarrito.jsp" />
	<section id="cart_items">
		<div class="container">
			<div class="breadcrumbs">
				<ol class="breadcrumb">
					<li><a href="#">Home</a></li>
					<li class="active">Check out</li>
				</ol>
			</div>
			<!--/breadcrums-->

			<div class="review-payment">
				<h2>
					<b>Detalle de su reserva</b>
				</h2>
			</div>

			<div class="table-responsive cart_info">
				<table class="table table-condensed">
					<thead>
						<tr class="cart_menu">
							<td class="image">Foto de Habitación</td>
							<td class="description">Tipo de Habitación</td>
							<td class="price">Precio Diario</td>
							<td class="quantity">Días a Reservar</td>
							<td class="total">Total</td>
							<td></td>
						</tr>
					</thead>
					<tbody>
						<%
						double total = 0;//variable que acumula el total del carrito
						if(listaHabitaciones != null){
							for(HabitacionesCarrito hc: listaHabitaciones)
							{
								total+=hc.getCantidadDias()*hc.getCostoDiario();
						%>
						<tr>
							<td class="cart_product"><a href=""><img
									src="${pageContext.request.contextPath}/mainCliente/images/shop/<%=hc.getImagen()%>"
									height="80" width="110" alt=""></a></td>
							<td class="cart_description">
								<h4>
									<a href=""><%=hc.getTipo() %></a>
								</h4>
								<p>
									ID:<%=hc.getNumeroHabitacion()%></p>
							</td>
							<td class="cart_price">
								<p>
									$<%=Math.round(hc.getCostoDiario())*100.00/100.00%></p>
							</td>
							<td class="cart_quantity">
								<div class="cart_quantity_button">
									<p><%=hc.getCantidadDias() %></p>
								</div>
							</td>
							<td class="cart_total">
								<p class="cart_total_price">
									$<%=Math.round(hc.getCostoDiario()*hc.getCantidadDias()*100)/100.0 %></p>
							</td>
						</tr>
						<%}}%>
						<tr>
							<td colspan="4">&nbsp;</td>
							<td colspan="2">
								<table class="table table-condensed total-result">
									<tr>
										<td>SubTotal de la Reserva</td>
										<td>$<%=Math.round(total*100.0)/100.0 %></td>
									</tr>
									<tr>
										<td>Impuestos</td>
										<td>$0.0</td>
									</tr>
									<tr>
										<td>Total de la Reserva</td>
										<td><span>$<%=Math.round(total*100.0)/100.0 %></span></td>
									</tr>
								</table>
							</td>
						</tr>
					</tbody>
				</table>
			</div>

			<c:forEach items="${requestScope.listaDatosCliente}" var="cliente">
				<div class="shopper-informations">
					<div class="row">
						<div class="col-md-8 col-md-offset-2">
							<div class="shopper-info">
								<p>
									<b>Información Registrada</b>
								</p>
								<p>Al confirmar su reserva prodrá ver y descargar el voucher
									en PDF.</p>
								<form action="carrito.do?op=registrarReserva" method="post">
									<label>Nombre</label><input type="text" name="nombre"
										value="${cliente.nombre}" placeholder="Display Name" readonly>
									<label>Email para envío del voucher</label><input type="text"
										name="email" value="${cliente.email}" readonly> <input
										type="hidden" name="idCliente" value="${cliente.idCliente}">
									<input type="hidden" name="totalGeneralReserva"
										value="<%=Math.round(total*100.0)/100.0 %>"> <input
										class="btn btn-primary" type="submit" value="Confirmar">
								</form>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>




			<br>
			<br>

		</div>
	</section>
	<!--/#cart_items-->

	<jsp:include page="/mainCliente/piePaginaCarrito.jsp" />

	<script
		src="${pageContext.request.contextPath}/mainCliente/js/jquery.js"></script>
	<script
		src="${pageContext.request.contextPath}/mainCliente/js/price-range.js"></script>
	<script
		src="${pageContext.request.contextPath}/mainCliente/js/jquery.scrollUp.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/mainCliente/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/mainCliente/js/jquery.prettyPhoto.js"></script>
	<script src="${pageContext.request.contextPath}/mainCliente/js/main.js"></script>
</body>
</html>