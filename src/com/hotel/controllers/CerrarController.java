package com.hotel.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CerrarController
 */
@WebServlet(name = "cerrarSesion.do", urlPatterns = { "/cerrarSesion.do" })
public class CerrarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CerrarController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//proceso para cerrar sesion
		try {
		HttpSession cerrar_sesion = request.getSession();//creamos objeto tipo httpsession
		cerrar_sesion.removeAttribute("email");//elimino atributo
		cerrar_sesion.removeAttribute("nivel");//elimino atributo
		cerrar_sesion.removeAttribute("idCliente");//elimino atributo
		cerrar_sesion.invalidate();//invalido sesion
		request.getRequestDispatcher("login.jsp").forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		HttpSession cerrar_sesion = request.getSession();//creamos objeto tipo httpsession
		cerrar_sesion.removeAttribute("email");//elimino atributo
		cerrar_sesion.removeAttribute("nivel");//elimino atributo
		cerrar_sesion.removeAttribute("idCliente");//elimino atributo
		cerrar_sesion.invalidate();//invalido sesion
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

}
