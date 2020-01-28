package com.hotel.models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hotel.beans.Servicio;

public class ServiciosModel extends Conexion {
	
	//metodo que devuelve la lista de servicios almacenadas en la BD. Usamos la interfaz List 
	public List<Servicio> listarServicios() throws SQLException {
		
		try {
			List<Servicio> lista=new ArrayList<>();//declaro una lista para almacenar objetos de tipo servicio
			
			String sql="SELECT idServicio,descripcion,precioDiario FROM Servicio";
			this.conectar();
			st=conexion.prepareStatement(sql);
			rs=st.executeQuery();//ejecutamos consulta, y obtenemos el ResultSet de la misma
			
			while(rs.next())
			{
				//recorremos resulset, tomamos los datos devueltos, y creamos un objeto servicio.
				Servicio servicio=new Servicio();//objeto tipo Servicio
				
				//tomo los datos del resultset y los almaceno en el objeto
				servicio.setIdServicio(rs.getInt("idServicio"));
				servicio.setDescripcion(rs.getString("descripcion"));
				servicio.setPrecioDiario(rs.getDouble("precioDiario"));
				
				//agrego cada objeto al arraylist
				lista.add(servicio);
			}
			
			this.desconectar();
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			this.desconectar();
			return null;
		}
		
	}

	public int insertarServicio(Servicio miServicio) throws SQLException {
		
		/*metodo devuelve un int=0 si no pudo insertar o  int=1* si se pudo insertar: y recibe un objeto tipo servicio*/
			try
			{
				int filasAfectadas = 0;//cantidad de filas afectadas por la consulta insert
		
				String sql="INSERT INTO Servicio VALUES(null,?,?,?,?)";//consulta de insersion con parametros
				this.conectar();
				st = conexion.prepareStatement(sql);
				
				//asigno los parametros para consulta INSERT
				st.setInt(1, miServicio.getHotel().getIdHotel());//st.setInt(1,1);
				st.setString(2, miServicio.getDescripcion());
				st.setDouble(3,miServicio.getPrecioDiario());
				st.setBoolean(4, miServicio.getEstado());
			
				filasAfectadas = st.executeUpdate(); //ejecuto consulta de insert, el metodo devuelve un int la cantidad de filas afectas por la consulta de  insersion
			
				this.desconectar();
				return filasAfectadas;//aqui devuelvo la cantidad de filas afectadas
			
				}catch (SQLException e) {
				
					e.printStackTrace();//si se produce excepcion devuelvo 0
					this.desconectar();
					return 0;
			}
	}
	
	//metodo para eliminar una habitación. Recibe como parametro el codigo
			public int eliminarServicio(String id) throws SQLException {
				
				//seguimos el mismo proceso usado en INSERT y UPDATE
				
				try {
					int filasAfectadas=0;
					
					String sql="DELETE FROM Servicio WHERE idServicio=?"; //consulta para borrar
					
					this.conectar();
					st = conexion.prepareStatement(sql);
					
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
			//metodo para listar habitaciones en la vista shop.jsp (para carrito)
			public List<Servicio> listarServCarrito() throws SQLException {
				
				try {
					List<Servicio> lista=new ArrayList<>();//declaro una lista para almacenar objetos de tipo habitacion
					
					String sql="SELECT descripcion,precioDiario,estado FROM Servicio WHERE estado='1'";
					this.conectar();
					st=conexion.prepareStatement(sql);
					rs=st.executeQuery();//ejecutamos consulta, y obtenemos el ResultSet de la misma
					
					while(rs.next())
					{
						//recorremos resulset, tomamos los datos devueltos, y creamos un objeto habitacion.
						Servicio servicio=new Servicio();//objeto tipo Servicio
						//tomo los datos del resultset y los almaceno en el objeto
						servicio.setDescripcion(rs.getString("descripcion"));
						servicio.setPrecioDiario(rs.getDouble("precioDiario"));
						servicio.setEstado(rs.getBoolean("estado"));
						
						//agrego cada objeto al arraylist
						lista.add(servicio);
					}
					
					this.desconectar();
					return lista;
				} catch (Exception e) {
					e.printStackTrace();
					this.desconectar();
					return null;
				}
				
			}

}
