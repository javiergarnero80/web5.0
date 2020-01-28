package com.hotel.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class EliminarCarritoController
 */
@WebServlet("/eliminarCarrito.do")
public class EliminarCarritoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarCarritoController() {
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
			HttpSession borrar_carrito = request.getSession();//creamos objeto tipo httpsession
			borrar_carrito.removeAttribute("carrito");//elimino atributo
			//borrar_carrito.invalidate();//invalido sesion
			//request.getRequestDispatcher("carrito.do?op=listarHabCarrito").forward(request, response);
			response.sendRedirect(request.getContextPath() + "/carrito.do?op=listarHabCarrito");
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
		try {
			HttpSession borrar_carrito = request.getSession();//creamos objeto tipo httpsession
			borrar_carrito.removeAttribute("carrito");//elimino atributo
			borrar_carrito.invalidate();//invalido sesion
			request.getRequestDispatcher("/carrito.do?op=listarHabCarrito").forward(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
	}

}
