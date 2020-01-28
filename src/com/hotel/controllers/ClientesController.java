package com.hotel.controllers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotel.beans.Cliente;
import com.hotel.models.ClientesModel;

/**
 * Servlet implementation class ClientesController
 */
@WebServlet("/clientes.do")
public class ClientesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public ClientesController() {
        super();
        // TODO Auto-generated constructor stub
    }

    ClientesModel modelo = new ClientesModel();//creo una instancia del modelo
    
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
			case "nuevo":
				request.getRequestDispatcher("/clientes/nuevoCliente.jsp").forward(request, response);
				//nuevo(request,response);
				break;
			case "registrar":
				registrar(request,response);
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
			/*try {
				insertar(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			break;
		case "listar":
			try {
				listar(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "nuevo":
			//deberia regdirigir a la vista del formulario nuevoCliente.jsp
			//nuevo(request,response);
			break;
		case "registrar":
			registrar(request,response);
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
	
	//metodo para listar clientes
	private void listar(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		try {
			//pasamos un atributo en la redireccion con la lista que devuelve el metodo listarHabitaciones() del modelo
			request.setAttribute("listaClientes", modelo.listarClientes());//tipo (atributo,valor)
				
			//hacemos la redireccion a la vista en el servidor, NO en el cliente. Para no perder la lista que se pasa como atributo
			request.getRequestDispatcher("/clientes/listaClientes.jsp").forward(request, response);
				
		} catch (ServletException | IOException e) {
				
			e.printStackTrace();
				
		}
	}
	
	//metodo para registrar un nuevo cliente
	private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {
			//leo lo que nos envia el formulario de la vista nuevoCliente.jsp y los almaceno en un objeto tipo Cliente
			Cliente miCliente = new Cliente();
				
			//agregamos al objeto lo que proviene del formulario usando el constructor de clase Cliente
			miCliente.setNombre(request.getParameter("nombre"));
			miCliente.setEmail(request.getParameter("email"));
			miCliente.setPass(request.getParameter("password"));
			miCliente.setEdad(Integer.parseInt(request.getParameter("edad")));
			miCliente.setDomicilioCalle(request.getParameter("domicilioCalle"));
			miCliente.setDomicilioNumero(Integer.parseInt(request.getParameter("domicilioNumero")));
			miCliente.setLocalidad(request.getParameter("localidad"));
			
			//seteamos la fecha/hora de sesion
			Date d=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yy-MM-dd hh:mm:ss");//objeto tipo formato de fecha. Le pasamos el formato de la fecha
			String ultimaSesion=sdf.format(d);
			miCliente.setUltimaSesion(ultimaSesion);
			
			miCliente.setIdUsuario(Integer.parseInt(request.getParameter("idUsuario")));//seteo el valor 2 que viene oculto desde el form
			
			//llamo al modelo para hacer el INSERT verfico si pudo insertar
			if(modelo.crearCliente(miCliente)) {
				response.getWriter().println("Usted se ha registrado Correctamente");
				
			}
			else {
					response.getWriter().println("No se pudo efectuar registrar");
				}
		} catch (NumberFormatException | NoSuchAlgorithmException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//metodo que permite eliminar un cliente vista admin
	private void eliminar(HttpServletRequest request, HttpServletResponse response) {
	
		try {
			String numero =request.getParameter("idCliente");//leo parametro numeroHabitacion de la vista
			
			//llamo a un metodo del modelo para que elimine el editorial de la bd y consulto si se pudo eliminar
			if(modelo.eliminarCliente(numero) > 0) {
				
				//paso un atributo de exito si se pudo eliminar
				request.setAttribute("exito", "Cliente eliminado exitosamente");
				
			}
			else {
				//paso un atributo de fracaso. 
				request.setAttribute("fracaso", "No se puede eliminar Cliente");
				
			}
			
			//redirecciono sin importar si se pudo o no eliminar
			request.getRequestDispatcher("/clientes.do?op=listar").forward(request, response);
		} catch (SQLException | ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	
}
