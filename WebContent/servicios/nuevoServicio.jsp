<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<%

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
<title>Nuevo Servicio</title>
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
			<h3>Nuevo Servicio</h3>
		</div>
		<div class="row">
			<div class=" col-md-7">
				<!-- mostramos la lista de errores de validacion en la parte superior del formulario (si los hay)-->
				<c:if test="${not empty requestScope.listaErrores}">
					<div class="alert alert-danger">
						<ul>
							<c:forEach items="${requestScope.listaErrores}" var="error">
								<li>${error}</li>
							</c:forEach>
						</ul>
					</div>
				</c:if>
				<form role="form"
					action="${pageContext.request.contextPath}/servicios.do"
					method="POST">
					<input type="hidden" name="op" value="insertar" /> <input
						type="hidden" name="idHotel" id="idHotel" value="1" /> <input
						type="hidden" name="estado" id="estado" value="true" />
					<div class="well well-sm">
						<strong><span class="glyphicon glyphicon-asterisk"></span>Campos
							requeridos</strong>
					</div>
					<div class="form-group">
						<label for="descripcion">Descripción del servicio:</label>
						<div class="input-group">
							<input type="text" class="form-control" id="descripcion"
								name="descripcion" value="${servicio.descripcion}"
								placeholder="Ingresa una descripción del servicio"> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-asterisk"></span></span>
						</div>
					</div>
					<div class="form-group">
						<label for="costoDiario">Precio por día:</label>
						<div class="input-group">
							<input type="number" step="0.1" min="1" class="form-control"
								id="precioDiario" name="precioDiario"
								value="${servicio.precioDiario}"
								placeholder="Ingresa el precio diario por el servicio ">
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-asterisk"></span></span>
						</div>
					</div>
					<input type="submit" class="btn btn-info" value="Guardar"
						name="Guardar"> <a class="btn btn-danger"
						href="${pageContext.request.contextPath}/servicios.do?op=listar">Cancelar</a>
				</form>
			</div>
		</div>
	</div>
</body>
</html>



