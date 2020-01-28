package com.hotel.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.hotel.beans.Contacto;
import com.hotel.models.ContactosModel;

/**
 * Servlet implementation class ContactosController
 */
@WebServlet("/contactos.do")
public class ContactosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactosController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    ContactosModel modelo = new ContactosModel();//creo una instancia del modelo
    
    ArrayList<String> listaErrores = new ArrayList<>();//declaro un arraylist para la validacion y alamacenar errores

    /*----------------------------------------------Metodos doGet() y doPost()---------------------------------------------------*/
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		if(request.getParameter("op")==null) { //verifico por si no se coloca parametro op en url: leo si hay parametro
			try {
				listar(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;//sacarme del metodo doGet si no hay parametro op
		}
		
		String operacion = request.getParameter("op");//variable para analizar que tarea realizar: listar, actualizar, insertar, borrar
		

		switch(operacion) 
		{
		
			case "listar":
			try {
				listar(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				break;
			case "detalles":
				detalles(request,response);
				break;
			case "nuevo":
				// redireccionamos hacia la vista nuevoServicio.jsp
				//request.getRequestDispatcher("/servicios/nuevoServicio.jsp").forward(request, response);
				break;
			case "obtener":
				//obtener(request, response);
				break;
			case "modificar": 
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
			try {
				listar(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;// sacarme del metodo doPost
		}

		String operacion = request.getParameter("op");// variable para analizar que tarea realizar: listar,
														// actualizar, agregar, borrar

		switch (operacion) {

		case "insertar":
			break;
		case "listar":
			try {
				listar(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "detalles":
			detalles(request,response);
			break;
		case "nuevo":
			nuevaSugerencia(request,response);
			break;
		case "obtener":
			//obtener(request, response);
			break;
		case "modificar": // llamada a metodo modificar que se encargara de tomar los datos del formulario
							// de editarEditorial
			//modificar(request, response);
			break;
		case "eliminar":
			//eliminar(request, response);
			break;
		default: //por si op tiene un valor distinto a los que maneja la app
			request.getRequestDispatcher("/error404.jsp").forward(request, response);
			break;
		}
	}

	
	/*--------------------------------Metodos llamados por doGet() o doPost() segun corresponda----------------------------------*/
	
	//metodo para listar las consultas/sugerencias
		private void listar(HttpServletRequest request, HttpServletResponse response) throws SQLException {
			// TODO Auto-generated method stub
			try {
				//pasamos un atributo en la redireccion con la lista que devuelve el metodo listarHabitaciones() del modelo
				request.setAttribute("listaContactos", modelo.listarContactos());//tipo (atributo,valor)
					
				//hacemos la redireccion a la vista en el servidor, NO en el cliente. Para no perder la lista que se pasa como atributo
				request.getRequestDispatcher("/contactos/listaContactos.jsp").forward(request, response);
					
			} catch (ServletException | IOException e) {
				System.out.println("error");
				e.printStackTrace();
					
			}
		}
		
		//metodo para el detalle de la consulta
		private void detalles(HttpServletRequest request, HttpServletResponse response) {
			
			try {
				PrintWriter out=response.getWriter();//out sirve para imprimir desde el servlet. Busco impirmir el json que voy a retornar
				
				int id=Integer.parseInt(request.getParameter("id"));//leo el idContacto proveniente de la vista listaContactos.jsp
				
				Contacto contacto=modelo.detalleContacto(id);//traemos el objeto que nos devuelve la consulta del modeloContacto
				
				//creamos un objeto de tipo json y los cargamos con los datos del objeto: {atributo1:valor1,atributo2:valor2,......}
				JSONObject json=new JSONObject();
				json.put("id", contacto.getIdContacto());//put es un metodo con dos argumentos: (atributo del json, valor de ese atributo del json)
				json.put("nombre", contacto.getNombre());
				json.put("email", contacto.getEmail());
				json.put("consulta", contacto.getConsulta());
				
				out.print(json);//imprimo el json 
				
			} catch (NumberFormatException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		//metodo que inserta un nuevo contacto o sugerencia
		private void nuevaSugerencia(HttpServletRequest request, HttpServletResponse response) {
			//leo lo que nos envia el formulario de la vista formularioSugerencia.jsp y los almaceno en un objeto tipo Contacto
			
			try {
				Contacto miContacto=new Contacto();
				
				//agregamos al objeto 
				miContacto.setNombre(request.getParameter("nombre"));
				miContacto.setEmail(request.getParameter("correo"));
				miContacto.setConsulta(request.getParameter("consulta"));
				
				if(modelo.insertarContacto(miContacto)>0) {
					
					//redirecciono
					request.getRequestDispatcher("/resultadoSugerencia.jsp").forward(request, response);
					
				}
				else {
					response.getWriter().print("No se pudo insertar");
				}
			} catch (SQLException | ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		//metodo para eliminar una consulta/contacto
		private void eliminar(HttpServletRequest request, HttpServletResponse response) {
			
			try {
				String numero =request.getParameter("idContacto");//leo parametro de la vista
				
				//llamo a un metodo del modelo para que elimine el editorial de la bd y consulto si se pudo eliminar
				if(modelo.eliminarContacto(numero) > 0) {
					
					//paso un atributo de exito si se pudo eliminar
					request.setAttribute("exito", "Sugerencia eliminada exitosamente");
					
				}
				else {
					//paso un atributo de fracaso. 
					request.setAttribute("fracaso", "No se puede eliminar esta sugerencia");
					
				}
				
				//redirecciono sin importar si se pudo o no eliminar
				request.getRequestDispatcher("/contactos.do?op=listar").forward(request, response);
			} catch (SQLException | ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	
}
