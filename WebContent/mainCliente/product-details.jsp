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
if(session.getAttribute("email") ==null && session.getAttribute("nivel")==null){
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
<title>Detalles de Habitaciones y Servicios | Hotel</title>
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

<!-- librerias de datepickers y jquery ui calendarios -->

<link
	href="${pageContext.request.contextPath}/mainCliente/css/bootstrap-datepicker3.min.css"
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
<body>
	<jsp:include page="/mainCliente/cabeceraCarrito.jsp" />

	<section>
		<div class="container">
			<div class="row">
				<div class="col-sm-12 padding-center">
					<div class="product-details">
						<!--product-details-->
						<div class="col-sm-6">
							<div class="view-product">
								<img src="mainCliente/images/shop/${detalleHabitacion.imagen}"
									style="width: 390px; height: 300px;" alt="" />
							</div>
						</div>
						<div class="col-sm-6">
							<div class="product-information">
								<!--/product-information-->
								<h2>${detalleHabitacion.descripcion}</h2>
								<p>
									<b>Numero Habitacion:</b> ${detalleHabitacion.numeroHabitacion}
								</p>
								<p>
									<b>Disponible para Reserva:</b> Si
								</p>

								<form id="detalle"
									action="${pageContext.request.contextPath}/carrito.do?op=agregarCarrito"
									method="post">
									<input type="hidden"
										value="${detalleHabitacion.numeroHabitacion}"
										name="numeroHabitacion"> <input type="hidden"
										value="${detalleHabitacion.descripcion}" name="descripcion">
									<input type="hidden" value="${detalleHabitacion.tipo}"
										name="tipo"> <input type="hidden"
										value="${detalleHabitacion.costoDiario}" name="costoDiario">
									<input type="hidden" value="${detalleHabitacion.imagen}"
										name="imagen">
									<!-- calendario -->
									<div id="sandbox-container">
										<div class="input-daterange input-group" id="datepicker">
											<span class="input-group-addon"><b>Fecha de
													Ingreso</b></span> <input type="text" class="input-sm form-control"
												id="start" name="start" readonly /> <span
												class="input-group-addon"><b>Fecha de Salida</b></span> <input
												type="text" class="input-sm form-control" id="end"
												name="end" readonly />
										</div>
									</div>

									<span> <span>$ ${detalleHabitacion.costoDiario}</span> <!--<button type="submit" class="btn btn-fefault cart">
											<i class="fa fa-shopping-cart"></i> Reservar
										</button>-->
									</span>
								</form>
								<button onclick="javascript:sendForm();"
									class="btn btn-second cart" title="Continuar">Reservar</button>
							</div>
							<!--/product-information-->
						</div>
					</div>
					<!--/product-details-->
				</div>
			</div>
		</div>
	</section>


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

	<!-- librerias para datepickers -->
	<script
		src="${pageContext.request.contextPath}/mainCliente/js/bootstrap-datepicker.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/mainCliente/js/bootstrap-datepicker.es.js"
		charset="UTF-8"></script>

	<script type="text/javascript">
		//usamos jquery para identificar el elemento de la pagina y le aplicamos la liberia datepicker
		
		  
		  $(function() {
			    $( "#sandbox-container .input-daterange" ).datepicker(
			    {
			    	language : "es",
					orientation : "bottom left",
					startDate: "Today" , 
					autoclose : true
				
			    });
			   
			
			  });
		
		  //funcion para validar las fechas
		  function sendForm() {
			  var valido = false; 
			  var inicio=document.getElementById("start").value
			  var fin=document.getElementById("end").value
			  if(inicio!=fin){
				  valido=true;
			  }
			  if (valido) {
			    document.getElementById("detalle").submit();
			  } else {
			    alert("DEBES RESERVAR AL MENOS UN DIA");
			    return false;
			  }
			}
		
	</script>
</body>
</html>