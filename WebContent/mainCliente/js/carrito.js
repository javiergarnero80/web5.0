/*archivo js para funcionalidad en vista cart.jsp: eliminar item de carrito*/
$(function(){
	
	$("tr #borrarItem").click(function(e){
		e.preventDefault();//para evitar recargas
		var elemento = $(this);//para identificar cual boton se hizo click para borrar
		/*recuperamos numero Habitacion para poder enviarlo en peticion ajax*/
		var numero = elemento.parent().find("#numeroHabitacion").text(); /*apunto a elemento que se hizo click bajo un nivel con parent y busco id de etiqueta span */
		//ya tengo numero de habitacion a quitar de carro, hago peticion ajax
		$.ajax({
			url : "../carrito.do?op=borrarItem", /*peticion ajax al controlador*/
			type : "POST",
			data : {numeroHabitacion : numero},/*objeto json con la informacion (atributo,valor)*/
			success : function(resultado){
				//alert(resultado);
				elemento.parent().parent().remove();/*bajo dos niveles de etiqueta hasta apuntar a <tr> y elimino toda la fila*/
				//cart-container
				
				//mostramos aviso si no hay reservas hechas cuando la tabla esta vacia
				var elementosTabla = $("#shop-table tr");
				if(elementosTabla.length <=1){
					$("#cart-container").append("<h4>No hay Reservas hechas por usted</h4>");
				}
				//actualizamos el subtotal y el total cuando elminamos articulos con ajax
				$("#txt-subtotal").text(resultado);
				$("#txt-total").text(resultado);
			}
			
		});
		
	});

});