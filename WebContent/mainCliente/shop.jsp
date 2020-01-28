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
	//response.sendRedirect("login.jsp");//redirigimos en el cliente para evitar reenvio de formulario de login
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
<title>Hotel | Habitaciones y Servicios</title>
<!-- 
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/prettyPhoto.css" rel="stylesheet">
    <link href="css/price-range.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
	<link href="css/main.css" rel="stylesheet">
	<link href="css/responsive.css" rel="stylesheet">
	 -->

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
	<section id="advertisement">
		<div class="container"></div>
	</section>

	<section>
		<div class="container">
			<div class="row">
				<div class="col-sm-12">
					<div class="features_items">
						<!--features_items-->
						<h2 class="title text-center">Habitaciones Disponibles</h2>
						<!-- listamos habitaciones -->
						<c:forEach items="${requestScope.listaHabitacionesCarrito}"
							var="habitacion">
							<!-- recorremos la lista que nos envio el controlador -->
							<div class="col-sm-4">
								<div class="product-image-wrapper">
									<div class="single-products">
										<div class="productinfo text-center">
											<!--  <img src="images/shop/product12.jpg" alt="" />-->
											<img src="mainCliente/images/shop/${habitacion.imagen}"
												alt="" />
											<h2>$ ${habitacion.costoDiario}</h2>
											<p>${habitacion.tipo}</p>
											<a href="#" class="btn btn-default add-to-cart"><i
												class="fa fa-shopping-cart"></i>Ver Detalles</a>
										</div>
										<div class="product-overlay">
											<div class="overlay-content">
												<h2>$ ${habitacion.costoDiario}</h2>
												<p>${habitacion.tipo}</p>
												<!--  
												<a href="${pageContext.request.contextPath}/carrito.do?op=detalle&numeroHabitacion=${habitacion.numeroHabitacion}" class="btn btn-default add-to-cart"><i
													class="fa fa-shopping-cart"></i>Ver Detalles</a>-->

												<form action="${pageContext.request.contextPath}/carrito.do"
													method="post">
													<input type="hidden" name="op" value="detalle"> <input
														type="hidden" name="numeroHabitacion"
														value="${habitacion.numeroHabitacion}"> <input
														class="btn btn-default add-to-cart"
														class="fa fa-shopping-cart" type="submit"
														value="Ver Detalles">
												</form>
											</div>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				<!-- Servicios -->
				<div class="col-sm-9 padding-right">
					<div class="features_items">
						<!--features_items-->
						<h2 class="title text-center">Servicios Disponibles</h2>
						<!-- listamos servicios -->
						<c:forEach items="${requestScope.listaServiciosCarrito}"
							var="servicio">
							<!-- recorremos la lista que nos envio el controlador -->
							<div class="col-sm-4">
								<div class="product-image-wrapper">
									<div class="single-products">
										<div class="productinfo text-center">
											<!--  <img src="images/shop/product12.jpg" alt="" />-->
											<!-- <img src="mainCliente/images/shop/${habitacion.imagen}" alt="" /> -->
											<h2>$ ${servicio.precioDiario}</h2>
											<p>${servicio.descripcion}</p>
											<a href="#" class="btn btn-default add-to-cart"><i
												class="fa fa-shopping-cart"></i>Ver Detalles</a>
										</div>
										<div class="product-overlay">
											<div class="overlay-content">
												<h2>$ ${servicio.precioDiario}</h2>
												<p>${servicio.descripcion}</p>
												<a href="#" class="btn btn-default add-to-cart"><i
													class="fa fa-shopping-cart"></i>Ver Detalles</a>
											</div>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>


						<ul class="pagination">
							<li><a href="">&laquo;</a></li>
							<li class="active"><a href="">1</a></li>
							<li><a href="">2</a></li>
							<li><a href="">3</a></li>
							<li><a href="">&raquo;</a></li>
						</ul>
					</div>
					<!--features_items-->
				</div>
			</div>
		</div>
	</section>

	<jsp:include page="/mainCliente/piePaginaCarrito.jsp" />

	<script
		src="http://localhost:7683/SitioWebHotel/mainCliente/js/jquery.js"></script>
	<script
		src="http://localhost:7683/SitioWebHotel/mainCliente/js/price-range.js"></script>
	<script
		src="http://localhost:7683/SitioWebHotel/mainCliente/js/jquery.scrollUp.min.js"></script>
	<script
		src="http://localhost:7683/SitioWebHotel/mainCliente/js/bootstrap.min.js"></script>
	<script
		src="http://localhost:7683/SitioWebHotel/mainCliente/js/jquery.prettyPhoto.js"></script>
	<script
		src="http://localhost:7683/SitioWebHotel/mainCliente/js/main.js"></script>

</body>
</html>