<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
<html>
<head>
<title>Inicio</title>
<jsp:include page="/cabecera.jsp" />
</head>
<body>
	<jsp:include page="/menu.jsp" />
	<div align="right">
		Bienvenido Sr:
		<%=session.getAttribute("email") %>
		<a href="${pageContext.request.contextPath}/cerrarSesion.do">Cerrar
			Sesion</a>
	</div>
	<div class="container">
		<div class="row">
			<h2>Administración de hotel</h2>
		</div>

		<div class="row">
			<div class="col-lg-3 col-md-6">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-3">
								<i class="glyphicon glyphicon-bed huge"></i>
							</div>
							<div class="col-xs-9 text-right">
								<div>
									<h4>Habitaciones</h4>
								</div>
							</div>
						</div>
					</div>
					<a
						href="${pageContext.request.contextPath}/habitaciones.do?op=listar">
						<div class="panel-footer">
							<span class="pull-left">Ver habitaciones</span> <span
								class="pull-right"><i
								class="glyphicon glyphicon-arrow-right"></i></span>
							<div class="clearfix"></div>
						</div>
					</a>
				</div>
			</div>
			<div class="col-lg-3 col-md-6">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-3">
								<i class="glyphicon glyphicon-sunglasses huge"></i>
							</div>
							<div class="col-xs-9 text-right">
								<!--<div class="huge">10</div>-->
								<div>
									<h4>Servicios</h4>
								</div>
							</div>
						</div>
					</div>
					<a href="${pageContext.request.contextPath}/servicios.do?op=listar">
						<div class="panel-footer">
							<span class="pull-left">Ver servicios</span> <span
								class="pull-right"><i
								class="glyphicon glyphicon-arrow-right"></i></span>
							<div class="clearfix"></div>
						</div>
					</a>
				</div>
			</div>
			<div class="col-lg-3 col-md-6">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-3">
								<i class="glyphicon glyphicon glyphicon-home huge"></i>
							</div>
							<div class="col-xs-9 text-right">
								<!-- <div class="huge">10</div>-->
								<div>
									<h4>Reservas</h4>
								</div>
							</div>
						</div>
					</div>
					<a
						href="${pageContext.request.contextPath}/reservas.do?op=listarReservasAdmin">
						<div class="panel-footer">
							<span class="pull-left">Ver reservas</span> <span
								class="pull-right"><i
								class="glyphicon glyphicon-arrow-right"></i></span>
							<div class="clearfix"></div>
						</div>
					</a>
				</div>
			</div>
			<div class="col-lg-3 col-md-6">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-3">
								<i class="glyphicon glyphicon-user huge"></i>
							</div>
							<div class="col-xs-9 text-right">
								<!-- <div class="huge">10</div> -->
								<div>
									<h4>Clientes</h4>
								</div>
							</div>
						</div>
					</div>
					<a href="${pageContext.request.contextPath}/clientes.do?op=listar">
						<div class="panel-footer">
							<span class="pull-left">Ver clientes</span> <span
								class="pull-right"><i
								class="glyphicon glyphicon-arrow-right"></i></span>
							<div class="clearfix"></div>
						</div>
					</a>
				</div>
			</div>
			<div class="col-lg-3 col-md-6">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-3">
								<i class="glyphicon glyphicon glyphicon-comment huge"></i>
							</div>
							<div class="col-xs-9 text-right">
								<!-- <div class="huge">10</div> -->
								<div>
									<h4>Sugerencias</h4>
								</div>
							</div>
						</div>
					</div>
					<a href="${pageContext.request.contextPath}/contactos.do?op=listar">
						<div class="panel-footer">
							<span class="pull-left">Ver sugerencias recibidas</span> <span
								class="pull-right"><i
								class="glyphicon glyphicon-arrow-right"></i></span>
							<div class="clearfix"></div>
						</div>
					</a>
				</div>
			</div>

		</div>
	</div>

</body>
</html>
