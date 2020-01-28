package com.hotel.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotel.beans.Reserva;
import com.hotel.models.ReservasModel;

/**
 * Servlet implementation class ReservasController
 */
@WebServlet("/reservas.do")
public class ReservasController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservasController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    ReservasModel modelo = new ReservasModel();//creo una instancia del modelo
    

    /*----------------------------------------------Metodos doGet() y doPost()---------------------------------------------------*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String operacion = request.getParameter("op");//variable para analizar que tarea realizar: listar, actualizar, insertar, borrar
		

		switch(operacion) 
		{
		
			case "listarReservas":
				listar(request,response);
				break;
			case "listarReservasAdmin":
				listarAdmin(request, response);
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
		String operacion = request.getParameter("op");//variable para analizar que tarea realizar: listar, actualizar, insertar, borrar
		

		switch(operacion) 
		{
		
			case "listarReservas":
				listar(request,response);
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

	//metodo para listar las reservas de cada cliente
	private void listar(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		try {
			int idCliente=Integer.parseInt(request.getParameter("idCliente"));//leo id enviado desde url (vista shop.jsp)
		
			List<Reserva> listaReservas=new ArrayList<>();
			
			//guardo la lista que devuelve el metodo  listarReservas (todos los encabezados de reservas hechas por el cliente)
			listaReservas = modelo.listarReservas(idCliente);//tipo (atributo,valor)
			
			request.setAttribute("listaDetalleReservas", modelo.listarDetalleReservas(listaReservas));//tipo (atributo,valor)
			request.setAttribute("listaReservas", listaReservas);
		
			//response.getWriter().println(listaReservas.get(3));
			request.getRequestDispatcher("mainCliente/vistaReserva.jsp").forward(request, response);
			
			
			//pasamos un atributo en la redireccion con la lista que devuelve el metodo listarReservas() del modelo
			//deberiamos llamar nuevamente al modelo para consultar los detalles de reservas del cliente en cuestion
			//request.setAttribute("listaDetalleReservas", modelo.listarDeatalleReservas(idCliente));//tipo (atributo,valor)
			
			
			//hacemos la redireccion a la vista en el servidor, NO en el cliente. Para no perder la lista que se pasa como atributo
			//request.getRequestDispatcher("/habitaciones/listaHabitaciones.jsp").forward(request, response);
		} catch (NumberFormatException | SQLException | IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//metodo para listar reservas en la vista de Admin
	private void listarAdmin(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try 
		{
			//pasamos un atributo en la redireccion con la lista que devuelve el metodo listarReservas) del modelo
			request.setAttribute("listaReservas", modelo.listarReservas());//tipo (atributo,valor)
			
			//hacemos la redireccion a la vista en el servidor, NO en el cliente. Para no perder la lista que se pasa como atributo
			request.getRequestDispatcher("/reservas/listaReservas.jsp").forward(request, response);
			
		} catch (SQLException | ServletException | IOException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	//metodo que elimina una reserva desde el admin
	private void eliminar(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id =request.getParameter("idReserva");//leo parametro numeroHabitacion de la vista
			
			//llamo a un metodo del modelo para que elimine el editorial de la bd y consulto si se pudo eliminar
			if(modelo.eliminarReserva(id) > 0) {
				
				//paso un atributo de exito si se pudo eliminar
				request.setAttribute("exito", "Reserva eliminada exitosamente");
				
			}
			else {
				//paso un atributo de fracaso. 
				request.setAttribute("fracaso", "No se puede eliminar esta Reserva");
				
			}
			
			
			//redirecciono sin importar si se pudo o no eliminar
			request.getRequestDispatcher("/reservas.do?op=listarReservasAdmin").forward(request, response);
		} catch (SQLException | ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
}
