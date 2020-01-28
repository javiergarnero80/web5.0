package com.hotel.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hotel.models.ClientesModel;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			//doGet(request, response);
			String email;
			String contrasena;
			int idCliente;
			int nivel=0;
			//RequestDispatcher rd=null;//despachador de solicitudes
			
			//creo objeto de clase clienteModel
			ClientesModel u=new ClientesModel();
			if(request.getParameter("btnEnviar")!=null) {//si presiono boton enviar, recupero los valores ingresados en las variables email y contrasena
				email=request.getParameter("email");
				contrasena=request.getParameter("contrasena");
				//llamo a metodo validar de clase ClientesModel y le paso parametros recogidos del formulario
				nivel=u.validar(email, contrasena);//0-noexiste,1-admin,2userRegistrado,3-userAnonimo
				idCliente=u.obtenerIdCliente(email, contrasena);//obtengo idCliente para carrito
				//manejo de sesiones
				HttpSession sesion= request.getSession();
					if(nivel==1){
						//creo sesion
						sesion.setAttribute("email",email);
						sesion.setAttribute("nivel",1);
						//envio a pagina de administrador
						request.getRequestDispatcher("index.jsp").forward(request, response);;
					}
					if(nivel==2){
						//creo sesion
								sesion.setAttribute("email",email);
								sesion.setAttribute("nivel",2);
								sesion.setAttribute("idCliente",idCliente);//para el carrito
								//envio a pagina de usuario Registrado
								//response.sendRedirect("carrito.do?op=listarHabCarrito");//cambiar jsp
								request.getRequestDispatcher("carrito.do?op=listarHabCarrito").forward(request, response);
					}
					if(nivel==0){
								//Cliente no existe en la base de datos, me mantengo en la pagina de login
								response.sendRedirect("login.jsp");//cambiar jsp
					}
				}
				
					
				
				//request.setAttribute("nivel",nivel);//se llama nivel mi atributo, y es el que se recupero(el idUsuario)
				//request.setAttribute("nombre",email);//se llama nombre mi atributo
				//rd=request.getRequestDispatcher("login.jsp");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		try {
			//doGet(request, response);
			String email;
			String contrasena;
			int idCliente;
			int nivel=0;
			//RequestDispatcher rd=null;//despachador de solicitudes
			
			//creo objeto de clase clienteModel
			ClientesModel u=new ClientesModel();
			if(request.getParameter("btnEnviar")!=null) {//si presiono boton enviar, recupero los valores ingresados en las variables email y contrasena
				email=request.getParameter("email");
				contrasena=request.getParameter("contrasena");
				//llamo a metodo validar de clase ClientesModel y le paso parametros recogidos del formulario
				nivel=u.validar(email, contrasena);//0-noexiste,1-admin,2userRegistrado,3-userAnonimo
				idCliente=u.obtenerIdCliente(email, contrasena);//obtengo idCliente para carrito
				//manjo de sesiones
				HttpSession sesion= request.getSession();
					if(nivel==1){
						//creo sesion
						sesion.setAttribute("email",email);
						sesion.setAttribute("nivel",1);
						//envio a pagina de administrador
						request.getRequestDispatcher("index.jsp").forward(request, response);;
					}
					if(nivel==2){
						//creo sesion
								sesion.setAttribute("email",email);
								sesion.setAttribute("nivel",2);
								sesion.setAttribute("idCliente",idCliente);//para el carrito
								//envio a pagina de usuario Registrado
								//response.sendRedirect("carrito.do?op=listarHabCarrito");//cambiar jsp
								request.getRequestDispatcher("carrito.do?op=listarHabCarrito").forward(request, response);
					}
					if(nivel==0){
								//Cliente no existe en la base de datos, me mantengo en la pagina de login
								request.setAttribute("fracaso", "Datos de login incorrectos. Intente nuevamente");
								//response.sendRedirect("login.jsp");//cambiar jsp
								request.getRequestDispatcher("/login.jsp").forward(request, response);
					}
				}
				
					
				
				//request.setAttribute("nivel",nivel);//se llama nivel mi atributo, y es el que se recupero(el idUsuario)
				//request.setAttribute("nombre",email);//se llama nombre mi atributo
				//rd=request.getRequestDispatcher("login.jsp");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
