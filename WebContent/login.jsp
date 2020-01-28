<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%

response.setHeader("Pragma","no-cache");
response.addHeader("Cache-control","must-revalidate");
response.addHeader("Cache-control","no-cache");
response.addHeader("Cache-control","no-store");
response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link
	href="${pageContext.request.contextPath}/assets/css4/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}//assets/css4/bootstrap-responsive.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css4/estilos.css"
	rel="stylesheet" type="text/css">

<script
	src="${pageContext.request.contextPath}/assets/js/jquery-1.12.0.min.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/assets/js/jquery.validate.min.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/assets/js/validacion.js"
	type="text/javascript"></script>
<link
	href="${pageContext.request.contextPath}/assets/css/estilosValidation.css"
	rel="stylesheet">

<link
	href="${pageContext.request.contextPath}/assets/css/alertify.core.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/assets/css/alertify.default.css"
	rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/assets/js/alertify.js"
	type="text/javascript"></script>
	<script src="js/jquery-3.2.1.min.js" type="text/javascript"></script>

<title>Login de Hotel</title>
</head>
<body class="bg-secondary" id="cuerpoFondo">

	<form id="frm-login"
		action="${pageContext.request.contextPath}/login.do" method="post">

		<div class="container">
			<div class="row">
				<div class="col-md-3"></div>
				<div class="col-md-8 col-xs-12 col-sm-12 col-lg-6">
					<div class="jumbotron">
						<h1 class="text-center">Inicio de Sesión</h1>
						<br>

						<div class="form-group">
							<label class="control-lable" for="usuario">Email</label> <input
								type="email" name="email" class="form-control"
								placeholder="email">
						</div>

						<div class="form-group">
							<label class="control-lable" for="usuario">Contraseña</label> <input
						 type="password" name="contrasena" class="form-control" id="myInput" placeholder="contrasena"> <input type="checkbox" onclick="myFunction()">   Ver Password   
						</div>
    				                	                   
                    <script>
                    function myFunction() {
                        var x = document.getElementById("myInput");
                                if (x.type === "password") {
                                x.type = "text";
                                }
                                else 
                                {
                                x.type = "password";
                                 }
                                }
</script>
    
    						<div class="pull-right">
							<button type="submit" class="btn btn-outline-warning"
								value="Ingresar" name="btnEnviar">Ingresar</button>
							<!--  <button type="reset" class="btn btn-outline-dark">Borrar</button>-->
							<a class="btn btn-danger"
								href="${pageContext.request.contextPath}/inicioHotel.html">Cancelar	e ir a Inicio</a>
						</div>
						<br>
						<br>
						<p>
							Si no posee cuenta por favor <a href="clientes.do?op=nuevo">registrese</a>.
							Muchas gracias.
						</p>
					</div>

					<!--  <div class="col-md-3"></div>-->
				</div>
			</div>
		</div>
	</form>
	<script type="text/javascript">
	
		<c:if test="${not empty fracaso}">/*si el login fue incorrecto muestro ventana de ingreso incorrecto con libreria alertify*/
			alertify.error("${fracaso}");
			<c:set var="fracaso" value="" scope="session"/>/*una vez aparece mensaje al recargar no vuelva a aparecer*/
		</c:if>
	
	</script>

</body>
</html>
