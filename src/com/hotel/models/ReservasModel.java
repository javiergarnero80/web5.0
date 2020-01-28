package com.hotel.models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hotel.beans.Cliente;
import com.hotel.beans.DetalleReservaHabitacion;
import com.hotel.beans.Habitacion;
import com.hotel.beans.Reserva;

public class ReservasModel extends Conexion {
	
	
	//metodo para insertar una nueva reserva (maestro/encabezado)
	public boolean nuevaReserva(Reserva reserva) throws SQLException {
		
		try {
			boolean bandera=false;
			String sql="call sp_nuevaReserva(null,?,?,?,?)";
			this.conectar();
			cs=conexion.prepareCall(sql);
			
			//setear parametros
			cs.setInt(1, reserva.getHotel().getIdHotel());
			cs.setInt(2,reserva.getIdCliente().getIdCliente());
			cs.setString(3, reserva.getFechaHoraReserva());
			cs.setDouble(4, reserva.getTotalGeneralReserva());
			if(cs.executeUpdate()>0) {
				bandera=true;//se realizo correctamente el INSERT
			}
			this.desconectar();
			return bandera;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.desconectar();
			return false;
		}
		
	}
	
	//metodo para insertar el detalle de la reserva (con cada una de las habitaciones reservadas en una misma reserva)
	public boolean nuevoDetalleReserva(DetalleReservaHabitacion detalleReserva) throws SQLException {
		try {
			boolean bandera=false;
			String sql="call sp_detalleReserva(null,?,?,?,?,?,?)";
			this.conectar();
			cs=conexion.prepareCall(sql);

			//setear parametros
			cs.setInt(1,detalleReserva.getIdReserva().getIdReserva());
			cs.setInt(2, detalleReserva.getNumeroHabitacion().getNumeroHabitacion());
			cs.setString(3, detalleReserva.getFechaIngreso());
			cs.setString(4, detalleReserva.getFechaSalida());
			cs.setInt(5, detalleReserva.getCantidadDiasReserva());
			cs.setDouble(6,detalleReserva.getTotalCostoReservaHabitacion());
			
			if(cs.executeUpdate()>0) {
				bandera=true;//se realizo correctamente el INSERT
			}
			this.desconectar();
			return bandera;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.desconectar();
			return false;
		}
		
	}

	//metodo que devuelve el idReserva de la ultima insercion (para el maestro detalle del INSERT)
	public int consultarIdReserva() throws SQLException {
		try {
			int idReserva=0;
			
			String sql="SELECT MAX(idReserva) idReserva FROM Reserva"; //obtengo el mayor idReserva que corresponde al ultimo insert
			this.conectar();
			st=conexion.prepareStatement(sql);
			rs=st.executeQuery();//ejecutamos consulta, y obtenemos el ResultSet de la misma
			
			if(rs.next()) {
				idReserva=rs.getInt("idReserva");
			}
			this.desconectar();
			return idReserva;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.desconectar();
			return 0;
		}
	}

	//metodo para consultar todos los encabezados (maestros) de reservas hechas por un cliente
	public List<Reserva> listarReservas(int idCliente) throws SQLException {
		// TODO Auto-generated method stub
		try {
		List<Reserva> lista=new ArrayList<>();//declaro una lista para almacenar objetos de tipo reserva
		
		//creamos un procedimiento almacenado que devuelva los encabezados de reserva de este cliente.
		String sql="CALL sp_reserva_encabezado(?)";
		this.conectar();
		cs=conexion.prepareCall(sql);
		
		//seteamos parametro
		cs.setInt(1,idCliente);//autenticamos con el email y el pass
		
		rs=cs.executeQuery();//ejecutamos consulta, y obtenemos el ResultSet de la misma
		
		while(rs.next())
		{
			//recorremos resulset, tomamos los datos devueltos, y creamos un objeto habitacion.
			Reserva reserva=new Reserva();//objeto tipo Reserva
			
			//tomo los datos del resultset y los almaceno en el objeto
			
			reserva.setIdReserva(rs.getInt("idReserva"));
			reserva.setFechaHoraReserva(rs.getString("fechaHoraReserva"));
			reserva.setTotalGeneralReserva(rs.getDouble("totalGeneralReserva"));
			
			//agrego cada objeto al arraylist
			lista.add(reserva);
		}
		this.desconectar();
		return lista;//retorno objeto reserva
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		this.desconectar();
		return null;
	}
	}
	
	//metodo para consultar cada detalle reserva para cada idReserva
	public List<List<DetalleReservaHabitacion>> listarDetalleReservas(List<Reserva> listaReservas) throws SQLException {
		
		//List<List<String>> ejemploLista = new ArrayList<List<String>>();
		List<List<DetalleReservaHabitacion>>lista = new ArrayList<List<DetalleReservaHabitacion>>();	
		
		/*
		for (Persona p:lista) {
	         
	        System.out.println(p.getNombre());
	        System.out.println(p.getApellidos());
	        System.out.println(p.getEdad());
	         
	    }*/
		int idReserva;
		
		//recorro la lista de Reservas con el encabezado
		for(Reserva res:listaReservas) {
			//obtengo primer idRserva del cliente en cuestion
			try {
				idReserva=res.getIdReserva();//obtengo idReserva
				String sql="CALL sp_reservaDetalle(?)";//para ese idReserva traigo todos los detalles
				this.conectar();
				cs=conexion.prepareCall(sql);
				
				//seteamos parametro
				cs.setInt(1,idReserva);//
				
				rs=cs.executeQuery();//ejecutamos consulta, y obtenemos el ResultSet de la misma
				
				List<DetalleReservaHabitacion> listaDetalleReserva=new ArrayList<>();// creo lista de tipo DetalleReservaHabitacion
				
				while(rs.next())
				{
					//recorremos resulset, tomamos los datos devueltos, y creamos un objeto 
					DetalleReservaHabitacion detalle=new DetalleReservaHabitacion();//objeto tipo Reserva
					
					
					
					
					//tomo los datos del resultset y los almaceno en el objeto
					/*
					reserva.setIdReserva(rs.getInt("idReserva"));
					reserva.setFechaHoraReserva(rs.getString("fechaHoraReserva"));
					reserva.setTotalGeneralReserva(rs.getDouble("totalGeneralReserva"));
					*/
					
					//detalle.setNumeroHabitacion((Habitacion) rs.getObject("numeroHabitacion"));
					detalle.setIdReserva(new Reserva(rs.getInt("idReserva")));
					detalle.setNumeroHabitacion(new Habitacion(rs.getInt("numeroHabitacion")));
					detalle.setFechaIngreso(rs.getString("fechaIngreso"));
					detalle.setFechaSalida(rs.getString("fechaSalida"));
					detalle.setCantidadDiasReserva(rs.getInt("cantidadDiasReserva"));
					detalle.setTotalCostoReservaHabitacion(rs.getDouble("totalCostoReservaHabitacion"));
					

					//agrego cada objeto al arraylist
					listaDetalleReserva.add(detalle);
				}
				//agrego la lista a la otra lista
				lista.add(listaDetalleReserva);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				this.desconectar();
				return null;
			}
			
		}
		this.desconectar();
		return lista;
	}

	//metodo que devuelve la lista de habitaciones almacenadas en la BD. Usamos la interfaz List 
		public List<Reserva> listarReservas () throws SQLException 
		{
			
			try {
				List<Reserva> lista=new ArrayList<>();//declaro una lista para almacenar objetos de tipo Reserva
				
				String sql="CALL sp_listarReservasAdmin";
				this.conectar();
				cs=conexion.prepareCall(sql);
				rs=cs.executeQuery();//ejecutamos consulta, y obtenemos el ResultSet de la misma
				
				while(rs.next())
				{
					//recorremos resulset, tomamos los datos devueltos, y creamos un objeto habitacion.
					Reserva reserva=new Reserva();//objeto tipo Habitacion
					
					//tomo los datos del resultset y los almaceno en el objeto
					/*
					habitacion.setNumeroHabitacion(rs.getInt("numeroHabitacion"));
					habitacion.setTipo(rs.getString("tipo"));
					habitacion.setDescripcion(rs.getString("descripcion"));
					habitacion.setCantidadPlazas(rs.getInt("cantidadPlazas"));
					habitacion.setCostoDiario(rs.getDouble("costoDiario"));*/
					
					reserva.setIdReserva(rs.getInt("idReserva"));
					reserva.setCliente(new Cliente(rs.getString("nombre")));
					//reserva.setCliente(new Cliente(rs.getString("email")));
					reserva.setFechaHoraReserva(rs.getString("fechaHoraReserva"));
					reserva.setTotalGeneralReserva(rs.getDouble("totalGeneralReserva"));
					reserva.setCliente(new Cliente(rs.getString("Cliente.nombre")));
					
					//agrego cada objeto al arraylist
					lista.add(reserva);
				}
				
				this.desconectar();
				return lista;
			} catch (Exception e) {
				e.printStackTrace();
				this.desconectar();
				return null;
			}
		}

		//metodo recibe id de reserva y la elimina de la base de datos
		public int eliminarReserva(String id) throws SQLException {
			try {
				
				//antes de eliminar deberia volver a habilitar las habitaciones incluidas en las reservas
				String sql="SELECT numeroHabitacion FROM Detalle_reserva_habitacion WHERE idReserva=?";
				this.conectar();
				st=conexion.prepareStatement(sql);
				st.setString(1,id);//parametro
				rs=st.executeQuery();//ejecutamos consulta, y obtenemos el ResultSet de la misma
				
				while(rs.next()) {
					//ppr cada numeroHabitacion actualizo el campo disponible
					String sql3="UPDATE Habitacion SET disponible=1 WHERE numeroHabitacion=?";//consulta para hacer update con parametros. Seteo disponible a cero (false)
					//this.conectar();
					st = conexion.prepareStatement(sql3);
					//asigo los parametros para consulta UPDATE
					int numero=rs.getInt("numeroHabitacion");
					st.setInt(1,numero);
					st.executeUpdate();
				}
				
				
				//ahora elimino la reserva
				/*------------------------------------------------------------------------------------*/
				
				int filasAfectadas=0;
				
				String sql2="DELETE FROM Reserva WHERE idReserva=?"; //consulta para borrar
				
				//this.conectar();
				st = conexion.prepareStatement(sql2);
				
				st.setString(1,id);//parametro
				
				filasAfectadas = st.executeUpdate();///ejecuto consulta
				
				this.desconectar();
				return filasAfectadas;
			} catch (SQLException e) {
				e.printStackTrace();
				this.desconectar();
				return 0;
			}
		}
}
		


