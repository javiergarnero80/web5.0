<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="assets/css4/bootstrap.css">
<!-- estilos personalizados CSS -->
<link rel="stylesheet" href="assets/css4/estilosFormulario.css">


<title>Formulario de contacto</title>
</head>
<body>
	<form action="https://formspree.io/hotelmyj@gmail.com" method="POST"
		id="contact-form">
		<h2>Contacto</h2>
		<!-- <input type="text" name="para" placeholder="para..." required> -->
		<!-- <input type="hidden" name="para" value="hotelaydaw2@gmail.com">-->

		<input type="hidden" name="_next"
			value="${pageContext.request.contextPath}/resultado.jsp" /> <input
			type="hidden" name="_subject" value="Nuevo Correo de contacto!" /> <input
			type="text" name="_gotcha" style="display: none" /> <input
			type="hidden" name="_language" value="es" /> <input type="text"
			id="inputName" name="nombre" placeholder="Ingrese su nombre" required>
		<input type="email" name="_replyto" placeholder="Ingrese su correo" />
		<textarea name="mensaje" id="inputMessage"
			placeholder="Escriba su mensaje" required></textarea>

		<input type="submit" value="enviar correo electrónico" id="boton">
	</form>
	<div align="center">
		<h5>
			<a style="color: white;" href="inicioHotel.html">Volver a Inicio</a>
		</h5>
	</div>


</body>
</html>