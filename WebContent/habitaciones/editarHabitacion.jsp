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
	request.getRequestDispatcher("login.jsp").forward(request, response);//si hay excepcion tambien redirijo al login
}
%>
<!DOCTYPE html>
<html>
<head>
<title>Editar Habitación</title>
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
			<h3>Editar Habitación</h3>
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
					action="${pageContext.request.contextPath}/habitaciones.do"
					method="POST">
					<input type="hidden" name="op" value="modificar" /> <input
						type="hidden" name="idHotel" id="idHotel" value="1" /> <input
						type="hidden" name="disponible" id="disponible" value="true" />
					<div class="well well-sm">
						<strong><span class="glyphicon glyphicon-asterisk"></span>Campos
							requeridos</strong>
					</div>
					<div class="form-group">
						<label for="numeroHabitacion">Número de habitación:</label>
						<div class="input-group">
							<input type="number" step="1" min="100" max="999"
								class="form-control" name="numeroHabitacion"
								id="numeroHabitacion" value="${habitacion.numeroHabitacion}"
								readonly placeholder="Ingresa el número de habitacuón">
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-asterisk"></span></span>
						</div>
					</div>
					<div class="form-group">
						<label for="tipo">Tipo de habitación:</label>
						<!-- debería haber una lista de selección con las opciones -->
						<div class="input-group">
							<!--   <input type="text" class="form-control" name="tipo" id="tipo" value="${habitacion.tipo}" placeholder="Ingresa el tipo de habitación" >
                                -->
							<select name="tipo" id="tipo" class="form-control">
								<c:forEach items="${requestScope.listaTiposHabitacion}"
									var="habitacionTipo">
									<c:if test="${habitacionTipo.tipo eq habitacion.tipo}">
										<option selected value="${habitacionTipo.tipo}">${habitacionTipo.tipo}</option>
									</c:if>
									<c:if test="${habitacionTipo.tipo ne habitacion.tipo}">
										<option value="${habitacionTipo.tipo}">${habitacionTipo.tipo}</option>
									</c:if>
								</c:forEach>

							</select> <span class="input-group-addon"><span
								class="glyphicon glyphicon-asterisk"></span></span>
						</div>
					</div>
					<div class="form-group">
						<label for="descripcion">Descripción de habitación:</label>
						<div class="input-group">
							<!--<input type="text" class="form-control" id="descripcion" name="descripcion" value="${habitacion.descripcion}" placeholder="Ingresa una descripción de la habitación">-->
							<select name="descripcion" id="descripcion" class="form-control">
								<c:forEach items="${requestScope.listaDescripcionHabitacion}"
									var="habitacionDesc">
									<c:if
										test="${habitacionDesc.descripcion eq habitacion.descripcion}">
										<option selected value="${habitacionDesc.descripcion}">${habitacionDesc.descripcion}</option>
									</c:if>
									<c:if
										test="${habitacionDesc.descripcion ne habitacion.descripcion}">
										<option value="${habitacionDesc.descripcion}">${habitacionDesc.descripcion}</option>
									</c:if>
								</c:forEach>

							</select> <span class="input-group-addon"><span
								class="glyphicon glyphicon-asterisk"></span></span>
						</div>
					</div>
					<div class="form-group">
						<label for="cantidadPlazas">Cantidad de Plazas de la
							habitación:</label>
						<div class="input-group">
							<input type="number" step="1" min="1" max="5"
								class="form-control" id="cantidadPlazas" name="cantidadPlazas"
								value="${habitacion.cantidadPlazas}"
								placeholder="Ingresa cantidad de plazas de la habitación ">
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-asterisk"></span></span>
						</div>
					</div>
					<div class="form-group">
						<label for="costoDiario">Precio por día:</label>
						<div class="input-group">
							<input type="number" step="0.1" min="1" class="form-control"
								id="costoDiario" name="costoDiario"
								value="${habitacion.costoDiario}"
								placeholder="Ingresa el precio diario de la habitación ">
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-asterisk"></span></span>
						</div>
					</div>
					<input type="submit" class="btn btn-info" value="Guardar"
						name="Guardar"> <a class="btn btn-danger"
						href="${pageContext.request.contextPath}/habitaciones.do?op=listar">Cancelar</a>
				</form>
			</div>
		</div>
	</div>
</body>
</html>



