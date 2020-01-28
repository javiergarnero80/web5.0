package com.hotel.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hotel.beans.Cliente;
import com.hotel.beans.DetalleReservaHabitacion;
import com.hotel.beans.Habitacion;
import com.hotel.beans.HabitacionesCarrito;
import com.hotel.beans.Hotel;
import com.hotel.beans.Reserva;
import com.hotel.models.ClientesModel;
import com.hotel.models.HabitacionesModel;
import com.hotel.models.ReservasModel;
import com.hotel.models.ServiciosModel;

/**
 * Servlet implementation class CarritoController
 */
@WebServlet(name = "carrito.do", urlPatterns = { "/carrito.do" })
public class CarritoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarritoController() {
        super();
        // TODO Auto-generated constructor stub
    }

    HabitacionesModel modelo = new HabitacionesModel();//creo una instancia del modelo
    ServiciosModel modeloServicio = new ServiciosModel();//creo una instancia del modelo
    ClientesModel modeloCliente = new ClientesModel();//creo una instancia del modelo
    ReservasModel modeloReserva = new ReservasModel();//creo una instancia del modelo
    
    /*----------------------------------------------Metodos doGet() y doPost()---------------------------------------------------*/
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String operacion = request.getParameter("op");//variable para analizar que tarea realizar: listar, actualizar, insertar, borrar
		

		switch(operacion) 
		{
		
			case "listarHabCarrito":
				listarHabCarrito(request,response);
				break;
			case "detalle":
				detalleHabitacion(request,response);
				break;
			case "checkout":
				checkout(request,response);
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
		
			case "listarHabCarrito":
				listarHabCarrito(request,response);
				break;
			case "detalle":
				detalleHabitacion(request,response);
				break;
			case "checkout":
				checkout(request,response);
				break;
			case "agregarCarrito":
				agregarCarrito(request,response);
				break;
			case "borrarItem":
				borrarItem(request,response);
				break;
			case "registrarReserva":
				registrarReserva(request,response);
				break;
			default: //por si op tiene un valor distinto a los que maneja la app
				request.getRequestDispatcher("/error404.jsp").forward(request, response);
				break;	
		}
		
	}
	
	

	/*--------------------------------Metodos llamados por doGet() o doPost() segun corresponda----------------------------------*/
	
	//metodo para listar las habitaciones en vista de Cliente (carrito)
		private void listarHabCarrito(HttpServletRequest request, HttpServletResponse response) {
			
			try 
			{
				//pasamos un atributo en la redireccion con la lista que devuelve el metodo listarHabitaciones() del modelo
				request.setAttribute("listaHabitacionesCarrito", modelo.listarHabCarrito());//tipo (atributo,valor)
				request.setAttribute("listaServiciosCarrito", modeloServicio.listarServCarrito());//tipo (atributo,valor)
				
				//hacemos la redireccion a la vista en el servidor, NO en el cliente. Para no perder la lista que se pasa como atributo
				request.getRequestDispatcher("/mainCliente/shop.jsp").forward(request, response);
				
				//response.sendRedirect(request.getContextPath() + "/mainCliente/shop.jsp");
				
			} catch (SQLException |ServletException| IOException e) {
				
				e.printStackTrace();
				
			}

		}
		
	// metodo para mostrar detalle de una habitacion seleccionada (hacia el product-details)
	private void detalleHabitacion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			int numero = Integer.parseInt(request.getParameter("numeroHabitacion"));// leo parametro enviado en la url
			
			//response.getWriter().print(numero);
			
			request.setAttribute("detalleHabitacion", modelo.detalleHabCarrito(numero));
			//response.getWriter().print(modelo.detalleHabCarrito(numero).getDescripcion());
			request.getRequestDispatcher("/mainCliente/product-details.jsp").forward(request, response);
			

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//metodo para agregar Habitacion a carrito
	@SuppressWarnings("unchecked")
	private void agregarCarrito(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			//capturo atributos
			int numero = Integer.parseInt(request.getParameter("numeroHabitacion"));
			String descripcion=request.getParameter("descripcion");
			String tipo=request.getParameter("tipo");
			double costoDiario=Double.parseDouble(request.getParameter("costoDiario"));
			String imagen=request.getParameter("imagen");
			
			String ingreso=request.getParameter("start");//fecha ingreso
			String egreso=request.getParameter("end");//fecha
			
			//cantidad de dias entre fechas
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
			Date fechaIngreso=formatoFecha.parse(ingreso);
			Date fechaEgreso=formatoFecha.parse(egreso);
			
			long startTime = fechaIngreso.getTime();
		    long endTime = fechaEgreso.getTime();
		    long diffTime = endTime - startTime;
		    int cantidad=(int)TimeUnit.DAYS.convert(diffTime, TimeUnit.MILLISECONDS);//cantidad de dias reserva
			
			//response.getWriter().print(numero+" "+cantidad);
			
			//trabajamos con variables de sesion para el carrito: creo una lista. 
		    //si no hay sesion "carrito" creo la lista, si hay sesion la recupero (recupero la variable de sesion)"
		    HttpSession sesion=request.getSession(true);
		    List<HabitacionesCarrito> listaHabitaciones=new ArrayList<>();//lista que guarda los atributos asociados a la variable de sesion
		    //listaHabitaciones=sesion.getAttribute("carrito") == null ? new ArrayList<>() : (ArrayList<HabitacionesCarrito>) sesion.getAttribute("carrito");
		    
		    if(sesion.getAttribute("carrito") == null){
				listaHabitaciones=new ArrayList<>();
			}
			else{
				listaHabitaciones=(ArrayList<HabitacionesCarrito>) sesion.getAttribute("carrito");
			}
		    
		    
		    listaHabitaciones.add(new HabitacionesCarrito(numero,ingreso,egreso,cantidad,descripcion,tipo,costoDiario,imagen));//agrego a la lista los atributos de la reserva de habitacion
		    
		    sesion.setAttribute("carrito", listaHabitaciones);//le asigno a la variable de sesion los datos de la lista
		    
			//request.getRequestDispatcher("/mainCliente/cart.jsp").forward(request, response);
			//hago redireccion en cliente
			response.sendRedirect(request.getContextPath() + "/mainCliente/cart.jsp");
			
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	//metodo para borrar un item del carrito con ajax
	private void borrarItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//response.getWriter().print("Ok ajax");
		int numeroHabitacion = Integer.parseInt(request.getParameter("numeroHabitacion"));//tomo parametro enviado por peticion ajax
		
		//recuperamos el carrito (variable de sesion)
		HttpSession sesion=request.getSession(true);
		List<HabitacionesCarrito> listaHabitaciones=new ArrayList<>();
		/*si no existe la lista dejamos null, pero si existe la recupero*/
		//listaHabitaciones=sesion.getAttribute("carrito") == null ? null : (ArrayList<HabitacionesCarrito>) sesion.getAttribute("carrito");
		
		if(sesion.getAttribute("carrito") == null){
			listaHabitaciones=null;
		}
		else{
			listaHabitaciones=(ArrayList<HabitacionesCarrito>) sesion.getAttribute("carrito");
		}
		
		
		
		//recorro la lista asociada a la sesion y por cada objeto habitacion pregunto si es igual al parametro de la peticion
		if (listaHabitaciones != null) //elimino si la lista no esta vacia
		{
			for (HabitacionesCarrito hc : listaHabitaciones) {
				if (hc.getNumeroHabitacion() == numeroHabitacion) {
					listaHabitaciones.remove(hc);// si coinciden elimino el objeto
					break;
				}
			}
		}
		
		//recalculamos precio cuando eliminamos una reserva del carrito con ajax
		double total=0;
		for (HabitacionesCarrito hc : listaHabitaciones) {
			total+=hc.getCantidadDias()*hc.getCostoDiario();
		}
		response.getWriter().print(Math.round(total * 100.)/100.0);
	}
	
	//metodo para proceso de checkout (luego de pulsar boton checkout en vista cart.jsp)
	private void checkout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	try {
		//response.getWriter().print("Recibido Ok");
		
		/*al no tener aun sesiones de usuario: probar pasando parametro de un cliente desde el enlace o un parametro hidden: 
		carrito.do?op=checkout&idCliente=2 o cliente=3*/
		
		int idCliente=Integer.parseInt(request.getParameter("idCliente"));	
		
		//tomar datos de cliente para colocarlos en campos de texto. Llamar a ClientesModel.java
		//asociar datos de cliente en la redireccion (por el momento luego se usara la sesion)
		request.setAttribute("listaDatosCliente", modeloCliente.datosCliente(idCliente));//tipo (atributo,valor)
		
		//redireccion en cliente a checkout.jsp
		
		//al redirigir: los datos del carrito los paso paso en la sesion y los del cliente los tomo del modeloCliente
		//response.sendRedirect(request.getContextPath() + "/mainCliente/checkout.jsp");
		request.getRequestDispatcher("mainCliente/checkout.jsp").forward(request, response);
	} catch (NumberFormatException | SQLException e) {
		e.printStackTrace();
	}

	}

	
	//metodo que recibe datos del checkout y registrar la reserva

	@SuppressWarnings("unchecked")
	private void registrarReserva(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		try {
			Reserva reserva = new Reserva(); //instancia clase Reserva
			DetalleReservaHabitacion detalleReserva = new DetalleReservaHabitacion();//instancia clase DetalleReservaHabitacion
			
			//recuperamos datos de Cliente pasados por formulario
			int idCliente=Integer.parseInt(request.getParameter("idCliente"));//se pasa como hidden
			String email=request.getParameter("email");
			//recuperamos el total de la reserva que se pasa como hidden
			double totalGeneralReserva = Double.parseDouble(request.getParameter("totalGeneralReserva"));
			//recuperamos la listaHabitaciones de la sesion con el carrito 
			HttpSession sesion=request.getSession(true);
			List<HabitacionesCarrito> listaHabitaciones=new ArrayList<>();		
			if(sesion.getAttribute("carrito") == null){
				listaHabitaciones=null;
			}
			else{
				listaHabitaciones=(ArrayList<HabitacionesCarrito>) sesion.getAttribute("carrito");
			}
			
			//miHabitacion.setHotel(new Hotel(Integer.parseInt(request.getParameter("idHotel"))));
			
			//listaHabitaciones.add(new HabitacionesCarrito(numero,ingreso,egreso,cantidad,descripcion,tipo,costoDiario,imagen))
			
			//DetalleReservaHabitacion(Reserva idReserva, Habitacion numeroHabitacion, String fechaIngreso,String fechaSalida, int cantidadDiasReserva, double totalCostoReservaHabitacion)
			

			//cargamos objeto reserva: idReserva es autonumerico, hotel, clliente, fechaHoraReserva, totalGeneralReserva
			reserva.setHotel(new Hotel(1));//constructor de bean hotel por tema de FK
			reserva.setCliente(new Cliente(idCliente));//constructor de bean Cliente por tema de FK
			
			//seteamos la fecha/hora de sesion
			Date d=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yy-MM-dd HH:mm:ss");//objeto tipo formato de fecha. Le pasamos el formato de la fecha
			sdf.setTimeZone(TimeZone.getTimeZone("America/Argentina/Buenos_Aires"));//coloco hora de Argentina(por el hosting que es de Brasil);
			String ultimaSesion=sdf.format(d);
			reserva.setFechaHoraReserva(ultimaSesion);
			
			reserva.setTotalGeneralReserva(totalGeneralReserva);
			
			if(modeloReserva.nuevaReserva(reserva)) { //paso el objeto al modelo para insertarlo en la BD
				response.getWriter().print("insert en tabla reserva correcto");
			}
			else {
				response.getWriter().print("insert en tabla Reserva incorrecto");
			}
			
			int idReserva=modeloReserva.consultarIdReserva();//traigo el idReserva de la ultima insersion
			
			//cargamos objetos detalleReserva (el detalle) y lo inserto en la BD: cada item de la lista es un objeto reserva vinculado a una sola reserva
			for(HabitacionesCarrito hc: listaHabitaciones) {

				detalleReserva.setIdReserva(new Reserva(idReserva));//asi asocio cada habitacion reservada con la misma reserva
				detalleReserva.setNumeroHabitacion(new Habitacion(hc.getNumeroHabitacion()));
				detalleReserva.setFechaIngreso(hc.getFechaIngreso());
				detalleReserva.setFechaSalida(hc.getFechaSalida());
				detalleReserva.setCantidadDiasReserva(hc.getCantidadDias());
				detalleReserva.setTotalCostoReservaHabitacion(hc.getCostoDiario()*hc.getCantidadDias());//costo diario por cantidad dias
				
				if(modeloReserva.nuevoDetalleReserva(detalleReserva)) {
					response.getWriter().print("insert en tabla Detalle_reserva_Habitacion correcto");
				}
				else {
					response.getWriter().print("insert en tabla Detalle_reserva_Habitacionincorrecto");
				}
				
				//aqui deberia por cada habitacion cargar un false en atributo de la habitacion para que posteriormente ya no aparezca en lista 
				modelo.deshabilitarHabitacion(hc.getNumeroHabitacion());
			}
			
			request.getRequestDispatcher("mainCliente/vistaReporteFinal.jsp").forward(request, response);
			//genero PDF redirijo a otro controlador
			//request.getRequestDispatcher(path)
			
			//2-redirijo a carrito.do?op=listarHabCarrito y deberia matar la variables de sesion carrito
			
			//response.sendRedirect(request.getContextPath() + "/carrito.do?op=listarHabCarrito");
			
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	
}
