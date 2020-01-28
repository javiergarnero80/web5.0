package com.hotel.models;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hotel.beans.Cliente;
import com.hotel.utils.Encriptar;

public class ClientesModel extends Conexion {

	public List<Cliente> listarClientes() throws SQLException {
		

		try {
			List<Cliente> lista=new ArrayList<>();//declaro una lista para almacenar objetos de tipo Cliente
			
			String sql="SELECT idCliente,nombre,email FROM Cliente";
			this.conectar();
			st=conexion.prepareStatement(sql);
			rs=st.executeQuery();//ejecutamos consulta, y obtenemos el ResultSet de la misma
			
			while(rs.next())
			{
				//recorremos resulset, tomamos los datos devueltos, y creamos un objeto servicio.
				Cliente cliente=new Cliente();//objeto tipo Cliente
				
				//tomo los datos del resultset y los almaceno en el objeto
				cliente.setIdCliente(rs.getInt("idCliente"));
				cliente.setNombre(rs.getString("nombre"));
				cliente.setEmail(rs.getString("email"));
				//cliente.setUsuario_idUsuario(rs.get);
				
				//agrego cada objeto al arraylist
				lista.add(cliente);
			}
			
			this.desconectar();
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			this.desconectar();
			return null;
		}
	
	}
	
	//metodo validar
    public int validar(String email, String contrasena) throws SQLException {//retornará Usuario_idUsuario que corresponde al nivel del mismo
    	int nivel=0;
    	try {
    		String sql="SELECT Usuario_idUsuario FROM Cliente WHERE email='"+email+"' AND pass='"+contrasena+"'";
    		this.conectar();
    		
    		st=conexion.prepareStatement(sql);
			rs=st.executeQuery();//ejecutamos consulta, y obtenemos el ResultSet de la misma
    	     
    	     while(rs.next()) {
    	    	 nivel=rs.getInt(1);
    	     }
    	    this.desconectar();
    	     
    	    return nivel;
    	}catch(Exception e){
    		e.printStackTrace();
			this.desconectar();
    		return nivel;
    	}
    }
	//metodo para obtener idCliente para el carrito asociado a un cliente
    public int obtenerIdCliente(String email, String contrasena) throws SQLException {//retornará Usuario_idUsuario que corresponde al nivel del mismo
    	int idCliente=0;
    	try {
    		String sql="SELECT idCliente FROM Cliente WHERE email='"+email+"' AND pass='"+contrasena+"'";
    		this.conectar();
    		
    		st=conexion.prepareStatement(sql);
			rs=st.executeQuery();//ejecutamos consulta, y obtenemos el ResultSet de la misma
    	     
    	     while(rs.next()) {
    	    	 idCliente=rs.getInt(1);
    	     }
    	    this.desconectar();
    	     
    	     return idCliente;
    	}catch(Exception e){
    		e.printStackTrace();
			this.desconectar();
    		return idCliente;
    	}
    }
	
	
	//metodo para insertar un nuevo cliente: sera usuario nivel 2 por defecto
		public boolean crearCliente(Cliente cl) throws SQLException, NoSuchAlgorithmException {
			
			try {
				boolean bandera=false;

				String sql="call sp_nuevoCliente(null,?,?,?,?,?,?,?,?,?)";//9 campos + 1 null porque el id es autonumerico
				/*String sql="INSERT INTO Cliente (idCliente,nombre,email,pass,edad,domicilioCalle,domicilioNumero,localidad,ultimaSesion,Usuario_idUsuario) "
						+ "VALUES(null,?,?,?,?,?,?,?,?,?)";*/
				this.conectar();
				cs=conexion.prepareCall(sql);
			
				//seteamos los parametros
				cs.setString(1,cl.getNombre());
				cs.setString(2,cl.getEmail());
				//cs.setString(3,Encriptar.sha256(cl.getPass()));//seteamos el pasword en la BD encriptado
				cs.setString(3,cl.getPass());
				cs.setInt(4,cl.getEdad());
				cs.setString(5,cl.getDomicilioCalle());
				cs.setInt(6,cl.getDomicilioNumero());
				cs.setString(7,cl.getLocalidad());
				cs.setString(8,cl.getUltimaSesion());
				cs.setInt(9,cl.getIdUsuario());//se le setea un 2 por ser nivel de usuario registrado*/
				
				if(cs.executeUpdate()>0) {
					bandera=true;//se realizo correctamente el INSERT
				}
				this.desconectar();
				return bandera;
			} catch (SQLException e) {
				e.printStackTrace();
				this.desconectar();
				return false;
			}
		}
		
		//Metodo para validar/autenticar que un usuario que inicia sesion exista en la BD
		
		public boolean autenticarCliente(Cliente cl) throws SQLException {
			try {
				boolean bandera=false;
				
				String sql="CALL sp_autenticarCliente(?,?)";//el sp recibe 2 parametros usuarios y password
				this.conectar();
				cs=conexion.prepareCall(sql);
				
				//seteamos parametros
				cs.setString(1,cl.getEmail());//autenticamos con el email y el pass
				cs.setString(2,Encriptar.sha256(cl.getPass()));
				
				rs=cs.executeQuery();//ejecutamos consulta, y obtenemos el ResultSet de la misma
				
				if(rs.next())
				{
					bandera=true;//si hay resultado es porque existe el cliente en la BD
					
				}
				this.desconectar();
				return bandera;
			} catch (NoSuchAlgorithmException | SQLException e) {
				e.printStackTrace();
				this.desconectar();
				return false;
			}
			
		}
	
		//método para traer informacion de un cliente para checkout
		public List<Cliente> datosCliente(int idCliente) throws SQLException {
			
			try {
				List<Cliente> lista=new ArrayList<>();//declaro una lista para almacenar objetos (será uno solo) de tipo Cliente
				
				Cliente cl =new Cliente();
				
				String sql="CALL sp_ObtenerCliente(?)";//el sp recibe 1 parametro idCliente
				this.conectar();
				cs=conexion.prepareCall(sql);
				
				//seteamos parametros
				cs.setInt(1,idCliente);//
				
				rs=cs.executeQuery();//ejecutamos consulta, y obtenemos el ResultSet de la misma
				
				if(rs.next()) {
					
					//tomo los datos del resultset y los almaceno en el objeto
					cl.setIdCliente(rs.getInt("idCliente"));
					cl.setNombre(rs.getString("nombre"));
					cl.setEmail(rs.getString("email"));
					cl.setDomicilioCalle(rs.getString("domicilioCalle"));
					cl.setDomicilioNumero(rs.getInt("domicilioNumero"));
				
					//cliente.setUsuario_idUsuario(rs.get);
					
					//agrego cada objeto al arraylist
					lista.add(cl);
					
				}
				this.desconectar();
				return lista;
			} catch (SQLException e) {
				e.printStackTrace();
				this.desconectar();
				return null;
			}
			
		}

		public int eliminarCliente(String numero) throws SQLException {
			//seguimos el mismo proceso usado en INSERT y UPDATE
			
			try {
				int filasAfectadas=0;
				
				String sql="DELETE FROM Cliente WHERE idCliente=?"; //consulta para borrar
				
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
	
}


