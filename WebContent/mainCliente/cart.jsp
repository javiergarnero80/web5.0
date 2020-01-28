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
	//recuperamos la variable de sesion para el carrito
	HttpSession sesion=request.getSession(true);
	List<HabitacionesCarrito> listaHabitaciones=new ArrayList<>();
	/*si no existe la lista dejamos null, pero si existe la recupero*/
	//listaHabitaciones=sesion.getAttribute("carrito") == null ? null : (ArrayList<HabitacionesCarrito>) sesion.getAttribute("carrito");
	
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
<title>Hotel | Lista de Reservas</title>

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
					<li class="active">Carrito de reservas</li>
				</ol>
			</div>
			<div class="table-responsive cart_info" id="cart-container">
				<table class="table table-condensed" id="shop-table">
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
							<td class="cart_delete"><span id="numeroHabitacion"
								style="display: none;"><%=hc.getNumeroHabitacion()%></span> <a
								class="cart_quantity_delete" href="" id="borrarItem"><i
									class="fa fa-times"></i></a></td>
						</tr>
						<%}}%>
					</tbody>
				</table>
				<% if(listaHabitaciones == null || session.getAttribute("carrito") == null){%>
				<h4>Su carro de reservas está vacío</h4>
				<%}%>
			</div>
			<!-- enlace a dos paginas hacia atras para seguir reservando -->
			<p>
				<i><a href="../carrito.do?op=listarHabCarrito">Volver a
						lista de habitaciones y servicios</a></i>
			</p>
		</div>

	</section>
	<!--/#cart_items-->
	<br>
	<br>
	<input type="hidden" id="permiso"
		value="<%=Math.round(total*100.0)/100.0 %>">
	<% if(session.getAttribute("carrito") != null && listaHabitaciones != null){%>
	<section id="do_action">
		<div class="container">
			<div class="heading">
				<h3>Si lo desea puede finalizar su Reserva y pasar a Check Out</h3>
				<p>A continuación se detalla el subtotal y total de la Reserva</p>
			</div>
			<div class="row">
				<div class="col-sm-12">
					<div class="total_area">
						<ul>
							<li>Sub Total de la Reserva <span id="txt-subtotal">$<%=Math.round(total*100.0)/100.0 %></span></li>
							<li>Impuestos <span>$0.0</span></li>
							<li>Total de la Reserva<span id="txt-total">$<%=Math.round(total*100.0)/100.0 %></span></li>
						</ul>
						<form id="checkout"
							action="${pageContext.request.contextPath}/carrito.do"
							method="post">
							<input type="hidden" name="op" value="checkout"> <input
								type="hidden" name="idCliente"
								value="<%=session.getAttribute("idCliente")%>">
							<!--  <input class="btn btn-default check_out" type="submit" value="Ir a Check Out">-->
						</form>
						<button onclick="javascript:sendForm();"
							class="btn btn-default check_out" title="Continuar">Ir a
							Check Out</button>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!--/#do_action-->
	<%}%>

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
	<script
		src="${pageContext.request.contextPath}/mainCliente/js/carrito.js"></script>

	<script type="text/javascript">
 		/*funcion para validar que cuando se borren item del carro con ajax no se pueda enviar form*/
	  function sendForm() {
		  var valido = false; 
		  var valor = $('#permiso').val();
		  if(valor != 0.0){
			  valido=true;
		  }
		  if (valido) {
			  var opcion = confirm("Desea confirmar y pasar al checkout");
			    if (opcion == true) {
			    	document.getElementById("checkout").submit();
				} else {
				    //mensaje = "Has clickado Cancelar";
				}
		    
		  } else {
		    alert("DEBES TENER AL MENOS UNA RESERVA HECHA");
		    return false;
		  }
 		}
   </script>


</body>
</html>