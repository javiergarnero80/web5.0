package com.hotel.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotel.beans.Hotel;
import com.hotel.beans.Servicio;
import com.hotel.models.ServiciosModel;
import com.hotel.utils.Validaciones;

/**
 * Servlet implementation class ServiciosController
 */
@WebServlet("/servicios.do")
public class ServiciosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServiciosController() {
        super();
        // TODO Auto-generated constructor stub
    }

    ServiciosModel modelo = new ServiciosModel();//creo una instancia del modelo
    
    ArrayList<String> listaErrores = new ArrayList<>();//declaro un arraylist para la validacion y alamacenar errores


    /*----------------------------------------------Metodos doGet() y doPost()---------------------------------------------------*/
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
				// redireccionamos hacia la vista nuevoServicio.jsp
				request.getRequestDispatcher("/servicios/nuevoServicio.jsp").forward(request, response);
				break;
			case "obtener":
				//obtener(request, response);
				break;
			case "modificar": // llamada a metodo modificar que se encargara de tomar los datos del formulario
				// de editarEditorial
				//modificar(request, response);
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
		//doGet(request, response);
		
		if (request.getParameter("op") == null) { // verifico por si no se coloca parametro op en url
			//listar(request, response);
			return;// sacarme del metodo doPost
		}

		String operacion = request.getParameter("op");// variable para analizar que tarea realizar: listar,
														// actualizar, agregar, borrar

		switch (operacion) {

		case "insertar":
			//el campo op=insertar viene oculto desde el formulario de nuevoServicio.jsp
			try {
				insertar(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "listar":
			listar(request, response);
			break;
		case "nuevo":
			/*try {
				//nuevo(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			break;
		case "obtener":
			//obtener(request, response);
			break;
		case "modificar": // llamada a metodo modificar que se encargara de tomar los datos del formulario
							// de editarEditorial
			//modificar(request, response);
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

	
	//metodo para listar servicio, reenvio los datos a la vista listaServicios.jsp
	private void listar(HttpServletRequest request, HttpServletResponse response) {
		try {
			//pasamos un atributo en la redireccion con la lista que devuelve el metodo listarHabitaciones() del modelo
			request.setAttribute("listaServicios", modelo.listarServicios());//tipo (atributo,valor)
				
			//hacemos la redireccion a la vista en el servidor, NO en el cliente. Para no perder la lista que se pasa como atributo
			request.getRequestDispatcher("/servicios/listaServicios.jsp").forward(request, response);
				
		} catch (SQLException | ServletException | IOException e) {
				
			e.printStackTrace();
				
		}
			
	}
	
	//metodo para insertar un nuevo servicio
		private void insertar(HttpServletRequest request, HttpServletResponse response) throws SQLException {
			
			try {
				listaErrores.clear();//limpiamos la lista de errores antes que nada
				
				
				//leo lo que nos envia el formulario de la vista nuevoServicio.jsp y los almaceno en un objeto tipo servicio
				Servicio miServicio = new Servicio();
				
				
				//agregamos al objeto solo el objeto y booleano y los string
				miServicio.setHotel(new Hotel(Integer.parseInt(request.getParameter("idHotel"))));//paso un objeto tipo hotel con el dato idHotel=1 desde el formulario
				miServicio.setDescripcion(request.getParameter("descripcion"));
				miServicio.setEstado(Boolean.parseBoolean(request.getParameter("estado")));
				
				
			
				//validamos lo enviado desde el fomulario: que no vengan campos vacios,y que tengan los formatos correctos
				
				if(Validaciones.isEmpty(miServicio.getDescripcion())) {
					
					listaErrores.add("Una descripción del servicio es obligatoria");
				
				}
				
				if (Validaciones.esDecimalPositivo(request.getParameter("precioDiario"))) {
					
					miServicio.setPrecioDiario(Double.parseDouble(request.getParameter("precioDiario")));
					
				}
				else {
					
					listaErrores.add("El costo diario por el servicio debe ser un decimal positivo");
					
				}
				//consulto si hubo algun error de validacion: si la lista no esta vacia debo informar los errores
				if(listaErrores.size()>0) {
					
					//conservo campos con datos correctos cargados. Va a ir el objeto como parametro
					request.setAttribute("servicio", miServicio);
					
					//envio como parametro la lista de errores
					request.setAttribute("listaErrores", listaErrores);
				
					//redirijo de nuevo al controlador y a su vez a la vista nuevoServicio.jsp para que rellene nuevamente los campos con error
					request.getRequestDispatcher("/servicios.do?op=nuevo").forward(request, response);
				}
				else { //si no hay errores de validacion
					
					//llamo al modelo para hacer el insert en la BD
					if(modelo.insertarServicio(miServicio) > 0) {
						
						//envio un mensaje como parametro para indicar que la la insersion fue correcta
						request.getSession().setAttribute("exito","Servicio registrado exitosamente");
						
						//si es mayor a cero se inserto correctamente debo enviarme a la vista de listaHabitaciones.jsp
						
						//redireccion en el cliente
						response.sendRedirect(request.getContextPath() + "/servicios.do?op=listar");
						
						//redireccion en el servidor 
						//request.getRequestDispatcher("/editoriales.do?op=listar").forward(request, response);
					}
					else {
						//si no se inserto correctamente redirijo a la vista listaServicios.jsp pero con mensaje de error
						//envio un mensaje como parametro para indicar que la la insersion fue incorrecta
						
						request.getSession().setAttribute("fracaso","No se pudo registrar este Servicio");
						
						//redireccion en el cliente
						response.sendRedirect(request.getContextPath() + "/servicios.do?op=listar");
						
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
			}
		
			
		}
	
	
	
		
	//metodo para eliminar una habitacion
	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		try {
			String id =request.getParameter("idServicio");//leo parametro numeroHabitacion de la vista
			
			//llamo a un metodo del modelo para que elimine el editorial de la bd y consulto si se pudo eliminar
			if(modelo.eliminarServicio(id) > 0) {
				
				//paso un atributo de exito si se pudo eliminar: De este modo al recargar aparece mensaje alertify de nuevo
				//request.setAttribute("exito", "Servicio eliminado exitosamente");
				request.getSession().setAttribute("exito", "Servicio eliminado exitosamente");
				
			}
			else {
				//paso un atributo de fracaso. De este modo al recargar aparece mensaje alertify de nuevo. Ver hacer redireccion en el cliente
				//request.setAttribute("exito", "Servicio eliminado exitosamente");
				request.getSession().setAttribute("fracaso", "No se puede eliminar este Servicio");
				
			}
			
			//redirecciono sin importar si se pudo o no eliminar. 
			//request.getRequestDispatcher("/servicios.do?op=listar").forward(request, response);
			
			//Redireccion en el cliente
			response.sendRedirect(request.getContextPath() +"/servicios.do?op=listar");
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
	}

}
