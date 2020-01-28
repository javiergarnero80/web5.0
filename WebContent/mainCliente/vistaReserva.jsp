<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<%
//afregamos seguridad al uso de sesiones, evitando que al recargar o al hacer atras se mantengan las sesiones
response.setHeader("Pragma","no-cache");
response.addHeader("Cache-control","must-revalidate");
response.addHeader("Cache-control","no-cache");
response.addHeader("Cache-control","no-store");
response.setDateHeader("Expires", 0);

try{
if(session.getAttribute("email") ==null && session.getAttribute("nivel")==null && session.getAttribute("idCliente")==null){
	//request.getRequestDispatcher("login.jsp").forward(request, response);//despacho al login si no hay variables de sesion
	response.sendRedirect("login.jsp");//redirigimos en el cliente para evitar reenvio de formulario de login
}

}catch(Exception e){
	//request.getRequestDispatcher("login.jsp").forward(request, response);//si hay excepcion tambien redirijo al login
	response.sendRedirect("login.jsp");//redirigimos en el cliente para evitar reenvio de formulario de login
}
%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>Hotel | Reservas</title>

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
<body>
	<jsp:include page="/mainCliente/cabeceraCarrito.jsp" />
	<section id="advertisement">
		<div class="container"></div>
	</section>

	<section id="cart_items">
		<div class="container">
			<div class="breadcrumbs">
				<ol class="breadcrumb">
					<li><a href="#">Home</a></li>
					<li class="active">Reservas</li>
				</ol>
			</div>
			<div class="table-responsive cart_info" id="cart-container">

				<h2 class="title text-center">Reservas hechas</h2>

				<c:forEach items="${requestScope.listaReservas}" var="reserva">

					<table class="table table-condensed" id="shop-table">
						<thead>
							<tr class="cart_menu">
								<td class="image">Número de Reserva</td>
								<td class="description">Fecha/Hora de Reserva</td>
								<!--  <td class="price">Precio Diario</td>-->
								<!--  <td class="quantity">Días a Reservar</td>-->
								<td class="total">Costo Total de la Reserva</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td class="cart_product">
									<p>${reserva.idReserva}</p>
								</td>
								<td class="cart_description">
									<p>${reserva.fechaHoraReserva}</p>
								</td>
								<td class="cart_total">
									<p class="cart_total_price">$
										${reserva.totalGeneralReserva}</p>
								</td>
							</tr>
						</tbody>
					</table>
				</c:forEach>

				<br> <br>

				<h2 class="title text-center">Detalle de reservas hechas</h2>
				<c:forEach items="${requestScope.listaDetalleReservas}"
					var="detalle">
					<table class="table table-condensed" id="shop-table">
						<thead>
							<tr class="cart_menu">
								<td class="product">Número de Reserva</td>
								<td class="product">Número de Habitación</td>
								<td class="description">Fecha Ingreso</td>
								<td class="description">Fecha Salida</td>
								<td class="quantity">Días reservados</td>
								<td class="price">Costo parcial de la reserva</td>
							</tr>
						</thead>

						<c:forEach items="${detalle}" var="item">
							<tbody>
								<tr>
									<td class="cart_image">
										<p>${item.idReserva}</p>
									</td>
									<td class="cart_product">
										<p>${item.numeroHabitacion}</p>
									</td>
									<td class="cart_description">
										<p>${item.fechaIngreso}</p>
									</td>
									<td class="cart_description">
										<p>${item.fechaSalida}</p>
									</td>
									<td class="cart_quantity">
										<div class="cart_quantity_button">
											<p>${item.cantidadDiasReserva}</p>
										</div>
									</td>
									<td class="cart_total">
										<p class="cart_total_price">$
											${item.totalCostoReservaHabitacion}</p>
									</td>
								</tr>
							</tbody>
						</c:forEach>
					</table>
				</c:forEach>

			</div>
		</div>

	</section>
	<!--/#cart_items-->
	<br>
	<br>

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