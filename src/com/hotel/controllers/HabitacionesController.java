package com.hotel.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotel.beans.Habitacion;
import com.hotel.beans.Hotel;
import com.hotel.models.HabitacionesModel;
import com.hotel.utils.Validaciones;


/**
 * Servlet implementation class HabitacionesController
 */
@WebServlet("/habitaciones.do") //indica la forma en que se accede al servlet desde el navegador o desde formularios(el urlpatterns)
public class HabitacionesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HabitacionesController() {
        super();
    }
    
    HabitacionesModel modelo = new HabitacionesModel();//creo una instancia del modelo
    
    ArrayList<String> listaErrores = new ArrayList<>();//declaro un arraylist para la validacion y alamacenar errores


    /*----------------------------------------------Metodos doGet() y doPost()---------------------------------------------------*/
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		if(request.getParameter("op")==null) { //verifico por si no se coloca parametro op en url: leo si hay parametro
			listar(request,response);
			return;//sacarme del metodo doGet si no hay parametro op
		}
		
		String operacion = request.getParameter("op");//variable para analizar que tarea realizar: listar, actualizar, insertar, borrar
		

		switch(operacion) 
		{
		
			case "listar":
				listar(request,response);
				break;
			case "nuevo":
			try {
				nuevo(request,response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
				break;
			case "obtener":
				obtener(request, response);
				break;
			case "modificar": // llamada a metodo modificar que se encargara de tomar los datos del formulario editar
				modificar(request, response);
				break;
			case "eliminar":
				eliminar(request, response);
				break;
			default: //por si op tiene un valor distinto a los que maneja la app
				request.getRequestDispatcher("/error404.jsp").forward(request, response);
				break;
		}
		
	}
	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		if (request.getParameter("op") == null) { // verifico por si no se coloca parametro op en url
			//listar(request, response);
			return;// sacarme del metodo doPost
		}

		String operacion = request.getParameter("op");// variable para analizar que tarea realizar: listar,
														// actualizar, agregar, borrar

		switch (operacion) {

		case "insertar":
			// el campo op=insertar viene oculto desde el formulario de nuevaHabitacion.js
			insertar(request, response);
			break;
		case "listar":
			listar(request, response);
			break;
		case "nuevo":
			try {
				nuevo(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "obtener":
			obtener(request, response);
			break;
		case "modificar": // llamada a metodo modificar que se encargara de tomar los datos del formulario editar
			modificar(request, response);
			break;
		case "eliminar":
			eliminar(request, response);
			break;
		default: //por si op tiene un valor distinto a los que maneja la app
			request.getRequestDispatcher("/error404.jsp").forward(request, response);
			break;
		}
		
		
		
	}
	
	
	/*--------------------------------Metodos llamados por doGet() o doPost() segun corresponda----------------------------------*/


	//metodo para listar habitaciones, reenvio los datos a la vista listaHabitaciones.jsp
	private void listar(HttpServletRequest request, HttpServletResponse response) {
		try 
		{
			//pasamos un atributo en la redireccion con la lista que devuelve el metodo listarHabitaciones() del modelo
			request.setAttribute("listaHabitaciones", modelo.listarHabitaciones());//tipo (atributo,valor)
			
			//hacemos la redireccion a la vista en el servidor, NO en el cliente. Para no perder la lista que se pasa como atributo
			request.getRequestDispatcher("/habitaciones/listaHabitaciones.jsp").forward(request, response);
			
		} catch (SQLException | ServletException | IOException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	//metodo para insertar una nueva habitacion
	private void insertar(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			listaErrores.clear();//limpiamos la lista de errores antes que nada
			
			
			//leo lo que nos envia el formulario de la vista nuevaHabitacion.jsp y los almaceno en un objeto tipo habitacion
			Habitacion miHabitacion = new Habitacion();
			
			
			//agregamos al objeto solo el objeto y booleano y los string
			miHabitacion.setHotel(new Hotel(Integer.parseInt(request.getParameter("idHotel"))));//paso un objeto tipo hotel con el dato idHotel=1 desde el formulario
			miHabitacion.setTipo(request.getParameter("tipo"));
			miHabitacion.setDisponible(Boolean.parseBoolean(request.getParameter("disponible")));
			miHabitacion.setDescripcion(request.getParameter("descripcion"));
			
			
		
			//validamos lo enviado desde el fomulario: que no vengan campos vacios,y que tengan los formatos correctos
			/*if(Validaciones.isEmpty(miHabitacion.getTipo())) {
				
				listaErrores.add("El tipo del habitación es obligatorio");
			
			}*/

			/*if(Validaciones.isEmpty(miHabitacion.getDescripcion())) {
			
				listaErrores.add("Una descripción de la habitación es obligatoria");
			
			}*/
			if(Validaciones.esEnteroPositivo(request.getParameter("numeroHabitacion"))) {
				//agrego al objeto el numero de habitacion
				miHabitacion.setNumeroHabitacion(Integer.parseInt(request.getParameter("numeroHabitacion")));
				
				//agrego la concatenacion para el nombre de la imagen
				miHabitacion.setImagen("hab"+Integer.parseInt(request.getParameter("numeroHabitacion"))+".jpg");//habXXX.jpg
				
			}
			else {
				listaErrores.add("El número de la habitación debe ser entero positivo entre 100 y 999 ");
			}

			
			if(Validaciones.esEnteroPositivo(request.getParameter("cantidadPlazas"))) {
				
				miHabitacion.setCantidadPlazas(Integer.parseInt(request.getParameter("cantidadPlazas")));
				
			}
			else {
				
				listaErrores.add("La cantidad de plazas de la habitación debe ser entero entre 1 y 5");
				
			}
			
			if (Validaciones.esDecimalPositivo(request.getParameter("costoDiario"))) {
				
				miHabitacion.setCostoDiario(Double.parseDouble(request.getParameter("costoDiario")));
				
			}
			else {
				
				listaErrores.add("El costo diario de la habitación debe ser un decimal positivo");
				
			}
			//consulto si hubo algun error de validacion: si la lista no esta vacia debo informar los errores
			if(listaErrores.size()>0) {
				
				//conservo campos con datos correctos cargados. Va a ir el objeto como parametro
				request.setAttribute("habitacion", miHabitacion);
				
				//envio como parametro la lista de errores
				request.setAttribute("listaErrores", listaErrores);
			
				//redirijo de nuevo al controlador y a su vez a la vista nuevaHabitacion.jsp para que rellene nuevamente los campos con error
				request.getRequestDispatcher("/habitaciones.do?op=nuevo").forward(request, response);
			}
			else { //si no hay errores de validacion
				
				//llamo al modelo para hacer el insert en la BD
				if(modelo.insertarHabitacion(miHabitacion) > 0) {
					
					//envio un mensaje como parametro para indicar que la la insersion fue correcta
					request.getSession().setAttribute("exito","Habitacion registrada exitosamente");
					
					//si es mayor a cero se inserto correctamente debo enviarme a la vista de listaHabitaciones.jsp
					
					//redireccion en el cliente
					response.sendRedirect(request.getContextPath() + "/habitaciones.do?op=listar");
					
					//redireccion en el servidor 
					//request.getRequestDispatcher("/editoriales.do?op=listar").forward(request, response);
				}
				else {
					//si no se inserto correctamente redirijo a la vista listaHabitaciones.jsp pero con mensaje de error
					//envio un mensaje como parametro para indicar que la la insersion fue incorrecta
					
					request.getSession().setAttribute("fracaso","No se pudo registrar esta Habitacion ya exsite otra habitación con ese número");
					
					//redireccion en el cliente
					response.sendRedirect(request.getContextPath() + "/habitaciones.do?op=listar");
					
					//redirijo a la vista de listaHabitaciones. Redireccion en el servidor
					//request.getRequestDispatcher("/editoriales.do?op=listar").forward(request, response);
				}
				
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("not a number"); 
		}catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("no se pudo insertar"); 
		}
	
		
	}
	
	//metodo cuando se pulsa "nueva habitacion" en vista listarHabitaciones.jsp
	private void nuevo(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		
		try {
			//pasamos como atributos dos listas: cargamos listas de seleccion de tipos de habitacion y descripcion
			request.setAttribute("listaTiposHabitacion", modelo.listarTipos());
			request.setAttribute("listaDescripcionHabitacion", modelo.listarDescripciones());
			
			//ahora redireccionamos a la vista
			request.getRequestDispatcher("habitaciones/nuevaHabitacion.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//metodo para eliminar una habitacion
	private void eliminar(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			String numero =request.getParameter("numeroHabitacion");//leo parametro numeroHabitacion de la vista
			
			//llamo a un metodo del modelo para que elimine el editorial de la bd y consulto si se pudo eliminar
			if(modelo.eliminarHabitacion(numero) > 0) {
				
				//paso un atributo de exito si se pudo eliminar
				request.setAttribute("exito", "Habitación eliminada exitosamente");
				
			}
			else {
				//paso un atributo de fracaso. 
				request.setAttribute("fracaso", "No se puede eliminar esta Habitacion");
				
			}
			
			//redirecciono sin importar si se pudo o no eliminar
			request.getRequestDispatcher("/habitaciones.do?op=listar").forward(request, response);
		} catch (SQLException | ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//metodo que recibe nroHabitacion y llama al modelo que le devuelve un objeto con los datos de la habitacion que corresponda
	private void obtener(HttpServletRequest request, HttpServletResponse response) {
		try {
			String numero=request.getParameter("numeroHabitacion");
			Habitacion miHabitacion=modelo.obtenerHabitacion(numero);
			
			//verificamos que no se modifique url al querer actualizar por un numeroHabitacion inexistente
			if(miHabitacion!=null) { //si no es nulo el objeto muestro la vista editar
				request.setAttribute("listaTiposHabitacion", modelo.listarTipos());
				request.setAttribute("listaDescripcionHabitacion", modelo.listarDescripciones());
				//paso como aributo a la vista el objeto de la habitacion 
				request.setAttribute("habitacion", miHabitacion);
				//renderizamos hacia la vista
				request.getRequestDispatcher("/habitaciones/editarHabitacion.jsp").forward(request, response);
			}
			else {
				response.sendRedirect(request.getContextPath()+"/error404.jsp");//redirijo a pagina de error
			}
		} catch (SQLException | ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	
	//metodo que recibe datos del formulario editar llama al modelo para hacer el update de la habitacion
	private void modificar(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			listaErrores.clear();//limpiamos la lista de errores antes que nada
			
			
			//leo lo que nos envia el formulario de la vista editarHabitacion.jsp y los almaceno en un objeto tipo habitacion
			Habitacion miHabitacion = new Habitacion();
			
			
			//agregamos al objeto solo el objeto y booleano y los string
			miHabitacion.setHotel(new Hotel(Integer.parseInt(request.getParameter("idHotel"))));//paso un objeto tipo hotel con el dato idHotel=1 desde el formulario
			miHabitacion.setTipo(request.getParameter("tipo"));
			miHabitacion.setDisponible(Boolean.parseBoolean(request.getParameter("disponible")));
			miHabitacion.setDescripcion(request.getParameter("descripcion"));
			
			
		
			//validamos lo enviado desde el fomulario: que no vengan campos vacios,y que tengan los formatos correctos
			/*if(Validaciones.isEmpty(miHabitacion.getTipo())) {
				
				listaErrores.add("El tipo del habitación es obligatorio");
			
			}*/

			/*if(Validaciones.isEmpty(miHabitacion.getDescripcion())) {
			
				listaErrores.add("Una descripción de la habitación es obligatoria");
			
			}*/
			if(Validaciones.esEnteroPositivo(request.getParameter("numeroHabitacion"))) {
				//agrego al objeto el numero de habitacion
				miHabitacion.setNumeroHabitacion(Integer.parseInt(request.getParameter("numeroHabitacion")));
				
				//agrego la concatenacion para el nombre de la imagen
				miHabitacion.setImagen("hab"+Integer.parseInt(request.getParameter("numeroHabitacion"))+".jpg");//habXXX.jpg
				
			}
			else {
				listaErrores.add("El número de la habitación debe ser entero positivo entre 100 y 999 ");
			}

			
			if(Validaciones.esEnteroPositivo(request.getParameter("cantidadPlazas"))) {
				
				miHabitacion.setCantidadPlazas(Integer.parseInt(request.getParameter("cantidadPlazas")));
				
			}
			else {
				
				listaErrores.add("La cantidad de plazas de la habitación debe ser entero entre 1 y 5");
				
			}
			
			if (Validaciones.esDecimalPositivo(request.getParameter("costoDiario"))) {
				
				miHabitacion.setCostoDiario(Double.parseDouble(request.getParameter("costoDiario")));
				
			}
			else {
				
				listaErrores.add("El costo diario de la habitación debe ser un decimal positivo");
				
			}
			//consulto si hubo algun error de validacion: si la lista no esta vacia debo informar los errores
			if(listaErrores.size()>0) {
				
				//conservo campos con datos correctos cargados. Va a ir el objeto como parametro
				request.setAttribute("habitacion", miHabitacion);
				
				//envio como parametro la lista de errores
				request.setAttribute("listaErrores", listaErrores);
			
				//redirijo a la vista editarHabitacion para que rellene nuevamente los campos con error
				request.getRequestDispatcher("/habitaciones/editarHabitacion.jsp").forward(request, response);
			}
			else { //si no hay errores de validacion
				
				//llamo al modelo para hacer el update en la BD
				if(modelo.modificarHabitacion(miHabitacion) > 0) {
					
					//envio un mensaje como parametro para indicar que el update fue correcta
					request.getSession().setAttribute("exito","Habitacion modificada exitosamente");
					
					//si es mayor a cero se actualizo correctamente debo enviarme a la vista de listaHabitaciones.jsp
					
					//redireccion en el cliente
					response.sendRedirect(request.getContextPath() + "/habitaciones.do?op=listar");
					
					//redireccion en el servidor 
					//request.getRequestDispatcher("/editoriales.do?op=listar").forward(request, response);
				}
				else {
					//si no se inserto correctamente redirijo a la vista listaHabitaciones.jsp pero con mensaje de error
					//envio un mensaje como parametro para indicar que la la insersion fue incorrecta
					
					request.getSession().setAttribute("fracaso","No se pudo actualizar los datos de esta Habitacion");
					
					//redireccion en el cliente
					response.sendRedirect(request.getContextPath() + "/habitaciones.do?op=listar");
					
					//redirijo a la vista de listaHabitaciones. Redireccion en el servidor
					//request.getRequestDispatcher("/editoriales.do?op=listar").forward(request, response);
				}
				
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("not a number"); 
		}catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("no se pudo insertar"); 
		}
	}
	


}
