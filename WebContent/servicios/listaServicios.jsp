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
<title>Lista de Servicios</title>
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
			<h3>Lista de Servicios</h3>
		</div>
		<div class="row">
			<div class="col-md-10">
				<a type="button" class="btn btn-primary btn-md"
					href="${pageContext.request.contextPath}/servicios.do?op=nuevo">
					Nuevo Servicio</a> <br>
				<br>
				<table class="table table-striped table-bordered table-hover"
					id=tabla>
					<thead>
						<tr>
							<th>idServicio</th>
							<th>Descripción</th>
							<th>Precio/día</th>
							<th>Operaciones</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach items="${requestScope.listaServicios}" var="servicio">
							<!-- recorremos la lista que nos envio el controlador -->
							<tr>
								<td>${servicio.idServicio}</td>
								<td>${servicio.descripcion}</td>
								<td>$ ${servicio.precioDiario}</td>
								<td><a class="btn btn-primary" href="#"><span
										class="glyphicon glyphicon-edit"></span></a> <a
									class="btn btn-danger"
									href="javascript:eliminar('${servicio.idServicio}')"><span
										class="glyphicon glyphicon-trash"></span></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

		</div>
	</div>

	<script type="text/javascript">
        /*convertimos la tabla en un data table*/
    	$(document).ready(function() {
				$('#tabla').DataTable({
					  "language": {
				  "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json"
					}
				
				});
		});
        	
        	<c:if test="${not empty exito}">/*si el insert del servicio fue correcto muestro ventana de ingreso correcto con libreria alertify*/
    			alertify.success("${exito}");
    			<c:set var="exito" value="" scope="session"/>/*una vez aparece mensaje al recargar no vuelva a aparecer*/
    	
    		</c:if>
    		
    		<c:if test="${not empty fracaso}">/*si el insert del servicio fue incorrecto muestro ventana de ingreso incorrecto con libreria alertify*/
				alertify.error("${fracaso}");
				<c:set var="fracaso" value="" scope="session"/>/*una vez aparece mensaje al recargar no vuelva a aparecer*/
	
			</c:if>
        
			//funcion javascript que elimina un editorial: se llama desde href de boton eliminar
	    	function eliminar(id){
	    			
	    		alertify.confirm('Realmente desea eliminar este servicio?', function(e){
	    				
	    			if(e){
	    				
	    				//si se produjo el evento redireccionamos hacia el controlador para eliminar
	    				location.href="servicios.do?op=eliminar&idServicio="+id;
	    					
	    			}
	    				
	    		});
	    			
	    	}
		
        </script>


</body>
</html>


