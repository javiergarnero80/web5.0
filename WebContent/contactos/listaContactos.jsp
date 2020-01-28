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
<title>Listado de Sugerencias</title>
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
			<h3>Listado de Sugerencias</h3>
		</div>
		<div class="row">
			<div class="col-md-10">
				<!--  <a type="button" class="btn btn-primary btn-md" href="${pageContext.request.contextPath}/clientes.do?op=nuevo"> Nuevo Cliente</a>-->
				<br>
				<table class="table table-striped table-bordered table-hover"
					id=tabla>
					<thead>
						<tr>
							<th>IdContacto</th>
							<th>Nombre</th>
							<th>Email</th>
							<th>Operaciones</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach items="${requestScope.listaContactos}" var="contacto">
							<!-- recorremos la lista que nos envio el controlador -->
							<tr>
								<td>${contacto.idContacto}</td>
								<td>${contacto.nombre}</td>
								<td>${contacto.email}</td>
								<td><a class="btn btn-default"
									href="javascript:detalles('${contacto.idContacto}')"><span
										class="glyphicon glyphicon-search"></span></a> <!--<a class="btn btn-primary" href="#"><span class="glyphicon glyphicon-edit"></span></a>-->
									<a class="btn btn-danger"
									href="javascript:eliminar('${contacto.idContacto}')"><span
										class="glyphicon glyphicon-trash"></span></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

		</div>
	</div>

	<!-- Ventana modal -->

	<div class="modal fade" id="miModal" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body">
					<ul class="list-group">
						<li class="list-group-item"><b>Email del contacto: </b>
						<spam id="email" /></li>
						<li class="list-group-item"><b>Sugerencia: </b>
						<spam id="consulta" /></li>

					</ul>


				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<script type="text/javascript">
	 /*convertimos la tabla en un data table*/
		$(document).ready(function() {
				$('#tabla').DataTable({
					  "language": {
					  "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json"
					}
			
				});
		});
        	
        	
		<c:if test="${not empty exito}">/*si el insert del contactoi fue correcto muestro ventana de ingreso correcto con libreria alertify*/
		alertify.success("${exito}");
		<c:set var="exito" value="" scope="session"/>/*una vez aparece mensaje al recargar no vuelva a aparecer*/

		</c:if>

		<c:if test="${not empty fracaso}">/*si el insert de la habitacion fue incorrecto muestro ventana de ingreso incorrecto con libreria alertify*/
		alertify.error("${fracaso}");
		<c:set var="fracaso" value="" scope="session"/>/*una vez aparece mensaje al recargar no vuelva a aparecer*/

		</c:if>

		//funcion javascript que elimina una editorial: se llama desde href de boton eliminar
		function eliminar(id) {

			alertify
					.confirm(
							'Realmente desea eliminar esta sugerencia hecha por un cliente?',
							function(e) {

								if (e) {

									//si se produjo el evento redireccionamos hacia el controlador para eliminar
									location.href = "contactos.do?op=eliminar&idContacto="
											+ id;

								}

							});

		}

		//funcion js para ver ventana modal con detalle de la consulta hecho con peticion AJAX
		function detalles(id) {

			//hacemos peticion ajax con JQuery para manipular el json. En data se guarda el JSON
			$
					.ajax({
						url : "${pageContext.request.contextPath}/contactos.do?op=detalles&id="
								+ id, //url en el servidor que trata la peticion
						type : "GET", //modo de envio en este caso GET
						dataType : "JSON",//respuesta del servidor en este caso un JSON
						success : function(data) { //defino lo que vamos a hacer con los datos si fue exitosa la respuesta del servidor

							//mostramos los datos del json con una ventana modal
							$(".modal-title")
									.text("Sugerencia enviada por " + data.nombre);//titulo de ventana modal
							$("#email").text(data.email);//email en el cuerpo del modal
							$("#consulta").text(data.consulta);//consulta en el cuerpo del modal
							$("#miModal").modal("show");//muestra la ventana.modal

						}

					}

					);

		}
	</script>


</body>
</html>


