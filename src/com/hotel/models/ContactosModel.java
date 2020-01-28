package com.hotel.models;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import com.hotel.beans.Contacto;

public class ContactosModel extends Conexion {
	
public List<Contacto> listarContactos() throws SQLException {
		

		try {
			List<Contacto> lista=new ArrayList<>();//declaro una lista para almacenar objetos de tipo Cliente
			
			String sql="SELECT idContacto,nombre,email,consulta FROM Contacto";
			this.conectar();
			st=conexion.prepareStatement(sql);
			rs=st.executeQuery();//ejecutamos consulta, y obtenemos el ResultSet de la misma
			
			while(rs.next())
			{
				//recorremos resulset, tomamos los datos devueltos, y creamos un objeto servicio.
				Contacto contacto=new Contacto();//objeto tipo Contacto
				
				//tomo los datos del resultset y los almaceno en el objeto
				contacto.setIdContacto(rs.getInt("idContacto"));
				contacto.setNombre(rs.getString("nombre"));
				contacto.setEmail(rs.getString("email"));
				contacto.setConsulta(rs.getString("consulta"));
				
				//agrego cada objeto al arraylist
				lista.add(contacto);
			}
			
			this.desconectar();
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			this.desconectar();
			return null;
		}
		
		
	}

//metodo que retorna un objeto al controlador buscando pasar la cadena con la consulta
public Contacto detalleContacto(int id) throws SQLException {
	

	try {
		
		
		String sql="call sp_detalleConsulta(?)"; //llamo al procedimiento almacenado
		this.conectar();
		cs=conexion.prepareCall(sql);
		cs.setInt(1,id);//seteo parametro para el sp
		rs=cs.executeQuery();//ejecutamos consulta
		
		if(rs.next()) //recorremos con if porque la consulta a lo sumo devuelve una sola fila
		{
			//recorremos resulset, tomamos los datos devueltos, y creamos un objeto Contacto.
			Contacto contacto=new Contacto();//objeto tipo Contacto
			
			//tomo los datos del resultset y los almaceno en el objeto
			contacto.setIdContacto(rs.getInt("idContacto"));
			contacto.setNombre(rs.getString("nombre"));
			contacto.setEmail(rs.getString("email"));
			contacto.setConsulta(rs.getString("consulta"));
			
			this.desconectar();
			return contacto;//retorno objeto libro
		
		}
		this.desconectar();
		return null;//devuelvo null si no entra al if
	} catch (Exception e) {
		e.printStackTrace();
		this.desconectar();
		return null;
	}
	
	
}

	public int insertarContacto(Contacto miContacto) throws SQLException, ServletException, IOException {

		/*
		 * metodo devuelve un int=0 si no pudo insertar o int=1* si se pudo insertar: y
		 * recibe un objeto tipo habitacion
		 */
		try {
			int filasAfectadas = 0;// cantidad de filas afectadas por la consulta insert

			String sql = "INSERT INTO Contacto VALUES(null,?,?,?,?)";// consulta de insersion con parametros
			this.conectar();
			st = conexion.prepareStatement(sql);

			//asigo los parametros para consulta INSERT
			
			st.setString(1, miContacto.getNombre());
			st.setString(2, miContacto.getEmail());
			st.setString(3, miContacto.getConsulta());
			st.setInt(4,1);//pondo un idCliente cero para indicar que no hace falta estar registrado para comunicarse
		
			filasAfectadas = st.executeUpdate(); // ejecuto consulta de insert, el metodo devuelve un int la cantidad de
													// filas afectas por la consulta de insersion

			this.desconectar();
			return filasAfectadas;// aqui devuelvo la cantidad de filas afectadas

		} catch (SQLException e) {

			e.printStackTrace();// si se produce excepcion devuelvo 0
			this.desconectar();
			return 0;
		}

	}

	//metodo para eliminar contacto. Recibe como parametro el id
	public int eliminarContacto(String numero) throws SQLException {

		// seguimos el mismo proceso usado en INSERT y UPDATE

		try {
			int filasAfectadas = 0;

			String sql = "DELETE FROM Contacto WHERE idContacto=?"; // consulta para borrar

			this.conectar();
			st = conexion.prepareStatement(sql);

			st.setString(1, numero);// parametro

			filasAfectadas = st.executeUpdate();/// ejecuto consulta

			this.desconectar();
			return filasAfectadas;
		} catch (SQLException e) {
			e.printStackTrace();
			this.desconectar();
			return 0;
		}

	}
	
}


