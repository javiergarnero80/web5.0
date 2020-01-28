<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Registro</title>
<jsp:include page="/cabecera.jsp" />
<script
	src="${pageContext.request.contextPath}/assets/js/jquery.validate.min.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/assets/js/validacion.js"
	type="text/javascript"></script>
<link
	href="${pageContext.request.contextPath}/assets/css/estilosValidation.css"
	rel="stylesheet">
<style type="text/css">
body {
	/*background-image:url('../../imagenes/cielo.jpg');*/
	/*background-image:url('../imagenes/gravel.png');*/
	background-image:
		url("${pageContext.request.contextPath}/imagenes/dark_wood.png");
	color: white;
	/*background-color:#d3d3d3;*/
	/*background-color:#848484;*/
}

#texto {
	color: black;
}
</style>
</head>
<body class="bg-secondary">
	<!--   <jsp:include page="/menu.jsp"/> -->
	<div class="container">
		<form role="form"
			action="${pageContext.request.contextPath}/clientes.do" method="POST"
			id="frm-registro">
			<div class="row">
				<h3>Complete el fomulario para registrarse</h3>
				<div id="texto" class="well well-sm">
					<strong><span class="glyphicon glyphicon-asterisk"></span>Campos
						requeridos</strong>
				</div>
			</div>
			<div class="row">
				<div class=" col-md-6">
					<input type="hidden" name="op" value="registrar" /> <input
						type="hidden" name="idUsuario" value="2" />

					<div class="form-group">
						<label for="nombre">Nombre:</label>
						<div class="input-group">
							<input type="text" class="form-control" id="nombre" name="nombre"
								placeholder="Ingresa tu nombre y apellido"> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-asterisk"></span></span>
						</div>
					</div>
					<div class="form-group">
						<label for="email">Email:</label>
						<div class="input-group">
							<input type="email" class="form-control" id="email" name="email"
								placeholder="Ingresa tu dirección de correo"> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-asterisk"></span></span>
						</div>
					</div>
					<div class="form-group">
						<label for="password">Password:</label>
						<div class="input-group">
							<input type="password" class="form-control" id="password"
								name="password" placeholder="Ingresa tu password"> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-asterisk"></span></span>
						</div>
					</div>
					<div class="form-group">
						<label for="password2">Confirmar Password:</label>
						<div class="input-group">
							<input type="password" class="form-control" id="password2"
								name="password2" placeholder="Confirma tu password"> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-asterisk"></span></span>
						</div>
					</div>
				</div>
				<div class=" col-md-6">
					<div class="form-group">
						<label for="edad">Edad:</label>
						<div class="input-group">
							<input type="number" step="1" min="18" class="form-control"
								id="edad" name="edad" placeholder="Ingresa tu edad"> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-asterisk"></span></span>
						</div>
					</div>
					<div class="form-group">
						<label for="domicilioCalle">Domicilio-Calle:</label>
						<div class="input-group">
							<input type="text" class="form-control" id="domicilioCalle"
								name="domicilioCalle"
								placeholder="Ingresa la calle de tu Domicilio"> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-asterisk"></span></span>
						</div>
					</div>
					<div class="form-group">
						<label for="domicilioNumero">Domicilio-Número:</label>
						<div class="input-group">
							<input type="number" class="form-control" id="domicilioNumero"
								name="domicilioNumero"
								placeholder="Ingresa el número de dirección de tu Domicilio">
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-asterisk"></span></span>
						</div>
					</div>
					<div class="form-group">
						<label for="localidad">Localidad:</label>
						<div class="input-group">
							<input type="text" class="form-control" id="localidad"
								name="localidad"
								placeholder="Ingresa la localidad de tu Domicilio"> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-asterisk"></span></span>
						</div>
					</div>
				</div>
			</div>
			<input type="submit" class="btn btn-info" value="Registrarme"
				name="Registrarme" id="btn-registro"> <a
				class="btn btn-danger"
				href="${pageContext.request.contextPath}/inicioHotel.html">Volver
				a Inicio</a>
		</form>
	</div>

</body>
</html>