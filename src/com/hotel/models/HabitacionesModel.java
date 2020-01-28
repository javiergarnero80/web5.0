package com.hotel.models;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import com.hotel.beans.Habitacion;

public class HabitacionesModel extends Conexion  {
	
/*clase tipo modelo para acceder a la BD. Extiende de clase Conexion (herencia)*/
	
	//metodo que devuelve la lista de habitaciones almacenadas en la BD. Usamos la interfaz List 
	public List<Habitacion> listarHabitaciones () throws SQLException 
	{
		
		try {
			List<Habitacion> lista=new ArrayList<>();//declaro una lista para almacenar objetos de tipo habitacion
			
			String sql="SELECT numeroHabitacion,tipo,descripcion,cantidadPlazas,costoDiario FROM Habitacion WHERE disponible=1";
			this.conectar();
			st=conexion.prepareStatement(sql);
			rs=st.executeQuery();//ejecutamos consulta, y obtenemos el ResultSet de la misma
			
			while(rs.next())
			{
				//recorremos resulset, tomamos los datos devueltos, y creamos un objeto habitacion.
				Habitacion habitacion=new Habitacion();//objeto tipo Habitacion
				
				//tomo los datos del resultset y los almaceno en el objeto
				habitacion.setNumeroHabitacion(rs.getInt("numeroHabitacion"));
				habitacion.setTipo(rs.getString("tipo"));
				habitacion.setDescripcion(rs.getString("descripcion"));
				habitacion.setCantidadPlazas(rs.getInt("cantidadPlazas"));
				habitacion.setCostoDiario(rs.getDouble("costoDiario"));
				
				//agrego cada objeto al arraylist
				lista.add(habitacion);
			}
			
			this.desconectar();
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			this.desconectar();
			return null;
		}
	}
	
	
	//metodo que devuelve lista de tipos de habitacion para cargar el campo de vista nuevaHabitacion.jsp
	public List<Habitacion> listarTipos () throws SQLException {
		
		try {
			List<Habitacion> listaTipos=new ArrayList<>();//declaro una lista para almacenar objetos de tipo habitacion
			
			String sql="SELECT DISTINCT tipo FROM Habitacion ORDER BY tipo";
			this.conectar();
			st=conexion.prepareStatement(sql);
			rs=st.executeQuery();//ejecutamos consulta, y obtenemos el ResultSet de la misma
			
			while(rs.next())
			{
				//recorremos resulset, tomamos los datos devueltos, y creamos un objeto habitacion.
				Habitacion habitacionTipo=new Habitacion();//objeto tipo Habitacion
				
				//tomo los datos del resultset y los almaceno en el objeto
				habitacionTipo.setTipo(rs.getString("tipo"));
				
				//agrego cada objeto al arraylist
				listaTipos.add(habitacionTipo);
			}
			this.desconectar();
			return listaTipos;
		} catch (SQLException e) {
			
			e.printStackTrace();
			this.desconectar();
			return null;
		}
		
		
	}
	
	//metodo que devuelve lista de descripciones de habitacion para cargar el campo de vista nuevaHabitacion.jsp
		public List<Habitacion> listarDescripciones () throws SQLException {
			
			try {
				List<Habitacion> listaDescripciones=new ArrayList<>();//declaro una lista para almacenar objetos de tipo habitacion
				
				String sql="SELECT DISTINCT descripcion FROM Habitacion ORDER BY descripcion";
				this.conectar();
				st=conexion.prepareStatement(sql);
				rs=st.executeQuery();//ejecutamos consulta, y obtenemos el ResultSet de la misma
				
				while(rs.next())
				{
					//recorremos resulset, tomamos los datos devueltos, y creamos un objeto habitacion.
					Habitacion habitacionDesc=new Habitacion();//objeto tipo Habitacion
					
					//tomo los datos del resultset y los almaceno en el objeto
					habitacionDesc.setDescripcion(rs.getString("descripcion"));
					
					//agrego cada objeto al arraylist
					listaDescripciones.add(habitacionDesc);
				}
				this.desconectar();
				return listaDescripciones;
			} catch (SQLException e) {
				
				e.printStackTrace();
				this.desconectar();
				return null;
			}
			
			
		}
	
	
	
	//metodo que inserta una nueva habitacion en la BD
		public int insertarHabitacion(Habitacion habitacion) throws SQLException , ServletException, IOException 
																			  /*metodo devuelve un int=0 si no pudo insertar o  int=1* si se pudo insertar: y recibe un objeto tipo habitacion*/
		{
			try
			{
				int filasAfectadas = 0;//cantidad de filas afectadas por la consulta insert
		
				String sql="INSERT INTO Habitacion VALUES(null,?,?,?,?,?,?,?,?)";//consulta de insersion con parametros
				this.conectar();
				st = conexion.prepareStatement(sql);
				
				//asigo los parametros para consulta INSERT
				st.setInt(1, habitacion.getHotel().getIdHotel());//st.setInt(1,1);
				st.setInt(2, habitacion.getNumeroHabitacion());
				st.setString(3, habitacion.getTipo());
				st.setBoolean(4, habitacion.getDisponible()); //st.setBoolean(true);
				st.setString(5, habitacion.getDescripcion());
				st.setInt(6, habitacion.getCantidadPlazas());
				st.setDouble(7, habitacion.getCostoDiario());
				st.setString(8, habitacion.getImagen());
			
				filasAfectadas = st.executeUpdate(); //ejecuto consulta de insert, el metodo devuelve un int la cantidad de filas afectas por la consulta de  insersion
			
				this.desconectar();
				return filasAfectadas;//aqui devuelvo la cantidad de filas afectadas
			
				}catch (SQLException e) {
				
					e.printStackTrace();//si se produce excepcion devuelvo 0
					System.out.println("no se pudo insertar "+habitacion.getHotel()); 
					this.desconectar();
					return 0;
			}
			
		}
		
		//metodo para eliminar una habitación. Recibe como parametro el codigo
		public int eliminarHabitacion(String numero) throws SQLException {
			
			//seguimos el mismo proceso usado en INSERT y UPDATE
			
			try {
				int filasAfectadas=0;
				
				String sql="DELETE FROM Habitacion WHERE numeroHabitacion=?"; //consulta para borrar
				
				this.conectar();
				st = conexion.prepareStatement(sql);
				
				st.setString(1,numero);//parametro
				
				filasAfectadas = st.executeUpdate();///ejecuto consulta
				
				this.desconectar();
				return filasAfectadas;
			} catch (SQLException e) {
				e.printStackTrace();
				this.desconectar();
				return 0;
			}
			
		}

		//metodo para listar habitaciones en la vista shop.jsp (para carrito)
		public List<Habitacion> listarHabCarrito() throws SQLException {
			
			try {
				List<Habitacion> lista=new ArrayList<>();//declaro una lista para almacenar objetos de tipo habitacion
				
				String sql="SELECT * FROM Habitacion WHERE disponible='1'";
				this.conectar();
				st=conexion.prepareStatement(sql);
				rs=st.executeQuery();//ejecutamos consulta, y obtenemos el ResultSet de la misma
				
				while(rs.next())
				{
					//recorremos resulset, tomamos los datos devueltos, y creamos un objeto habitacion.
					Habitacion habitacion=new Habitacion();//objeto tipo Habitacion
					
					//tomo los datos del resultset y los almaceno en el objeto
					habitacion.setNumeroHabitacion(rs.getInt("numeroHabitacion"));
					habitacion.setTipo(rs.getString("tipo"));
					habitacion.setDescripcion(rs.getString("descripcion"));
					habitacion.setCantidadPlazas(rs.getInt("cantidadPlazas"));
					habitacion.setCostoDiario(rs.getDouble("costoDiario"));
					habitacion.setImagen(rs.getString("imagen"));
					
					//agrego cada objeto al arraylist
					lista.add(habitacion);
				}
				
				this.desconectar();
				return lista;
			} catch (Exception e) {
				e.printStackTrace();
				this.desconectar();
				return null;
			}
			
		}

		//devuelvo un objeto tipo habitacion segun el numero de Habitacion recibido como parametro (carrito)
		public Habitacion detalleHabCarrito(int numero) throws SQLException {
			
			try {
				Habitacion habitacion=new Habitacion();
				String sql="CALL sp_detalleHabCarrito(?)";
				this.conectar();
				cs=conexion.prepareCall(sql);
				
				//seteamos parametro
				cs.setInt(1,numero);//autenticamos con el email y el pass
				
				rs=cs.executeQuery();//ejecutamos consulta, y obtenemos el ResultSet de la misma
				
				if(rs.next()) {
					habitacion.setNumeroHabitacion(rs.getInt("numeroHabitacion"));
					habitacion.setTipo(rs.getString("tipo"));
					habitacion.setDescripcion(rs.getString("descripcion"));
					habitacion.setCantidadPlazas(rs.getInt("cantidadPlazas"));
					habitacion.setCostoDiario(rs.getDouble("costoDiario"));
					habitacion.setImagen(rs.getString("imagen"));
					
				}
				this.desconectar();
				return habitacion;//retorno objeto habitacion
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				this.desconectar();
				return null;
			}
		}
		
		//metodo que devuelve los datos de una habitacion, sirve para el proceso de editar
		public Habitacion obtenerHabitacion(String numero) throws SQLException {
			
			try {
				String sql="SELECT * FROM Habitacion WHERE numeroHabitacion=?";
				this.conectar();
				st=conexion.prepareStatement(sql);
				st.setString(1, numero);
				rs=st.executeQuery();
				
				//recorro el resulset, que sera una sola o ninguna fila
				if(rs.next()) {
					//creo un objeto de tipo Habitacion
					Habitacion habitacion = new Habitacion();
					//tomo los datos del resultset y los almaceno en el objeto
					habitacion.setNumeroHabitacion(rs.getInt("numeroHabitacion"));
					habitacion.setTipo(rs.getString("tipo"));
					habitacion.setDescripcion(rs.getString("descripcion"));
					habitacion.setCantidadPlazas(rs.getInt("cantidadPlazas"));
					habitacion.setCostoDiario(rs.getDouble("costoDiario"));
					habitacion.setImagen(rs.getString("imagen"));
					
					this.desconectar();
					return habitacion;
				}
				this.desconectar();
				return null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				this.desconectar();
				return null;
			}
			
		}
		
		
		//metodo para hacer UPDATE en la base de datos de una habitacion
		public int modificarHabitacion(Habitacion habitacion) throws SQLException {
			
			try
			{
				int filasAfectadas = 0;//cantidad de filas afectadas por la consulta insert
		
				String sql="UPDATE Habitacion SET idHotel=?,tipo=?,disponible=?,"
						+ "descripcion=?,cantidadPlazas=?,costoDiario=?,imagen=?"
						+ "WHERE numeroHabitacion=?";//consulta para hacer update con parametros
				this.conectar();
				st = conexion.prepareStatement(sql);
				
				//asigo los parametros para consulta INSERT
				st.setInt(1, habitacion.getHotel().getIdHotel());//st.setInt(1,1);
				st.setString(2, habitacion.getTipo());
				st.setBoolean(3, habitacion.getDisponible()); //st.setBoolean(true);
				st.setString(4, habitacion.getDescripcion());
				st.setInt(5, habitacion.getCantidadPlazas());
				st.setDouble(6, habitacion.getCostoDiario());
				st.setString(7, habitacion.getImagen());
				st.setInt(8, habitacion.getNumeroHabitacion());
			
				filasAfectadas = st.executeUpdate(); //ejecuto consulta de insert, el metodo devuelve un int la cantidad de filas afectas por la consulta de  insersion
			
				this.desconectar();
				return filasAfectadas;//aqui devuelvo la cantidad de filas afectadas
			
				}catch (SQLException e) {
				
					e.printStackTrace();//si se produce excepcion devuelvo 0
					System.out.println("no se pudo insertar "); 
					this.desconectar();
					return 0;
			}
			
		}

		//al reservar una habitacion la misma ya no esta disponible para otra reserva
		public void deshabilitarHabitacion(int numeroHabitacion) throws SQLException {
			
			try
			{
				String sql="UPDATE Habitacion SET disponible=0 WHERE numeroHabitacion=?";//consulta para hacer update con parametros. Seteo disponible a cero (false)
				this.conectar();
				st = conexion.prepareStatement(sql);
				
				//asigo los parametros para consulta UPDATE
			
				st.setInt(1,numeroHabitacion);
			
				st.executeUpdate(); //ejecuto consulta de insert, el metodo devuelve un int la cantidad de filas afectas por la consulta de  insersion
			
				this.desconectar();
				}catch (SQLException e) {
				
					e.printStackTrace();//si se produce excepcion devuelvo 0
					System.out.println("no se pudo insertar "); 
					this.desconectar();
			}
			
		}

}
