package com.hotel.beans;

public class Cliente {
	
	private int idCliente;
	private String nombre;
	private String email;
	private String pass;
	private int edad;
	private String domicilioCalle;
	private int domicilioNumero;
	private String localidad;
	private String ultimaSesion;
	private int idUsuario;//para la operacion de INSERT
	private Usuario Usuario_idUsuario; //objeto de tipo Usuario por lo de la fk. Sirve para consultar
	
	//constructor que permite pasar desde formulario insertarHabitacion un idHotel=1 y para la reserva
	
	public Cliente(){
		this.idCliente=0;
		this.nombre="";
		this.email="";
		this.pass="";
		this.edad=0;
		this.domicilioCalle="";
		this.domicilioNumero=0;
		this.localidad="";
		this.ultimaSesion="";
		this.idUsuario=0;
		this.Usuario_idUsuario=null;
	
	}

	//reutilizo este constructor para cargar idCliente en tabla Reserva. CUIDADO antes los tenia por relacion usuario y cliente
	public Cliente(int idCliente) {
		super();
		//this.idUsuario = idUsuario;
		this.idCliente = idCliente;
	}
	
	//para select de vista de reservas Admin
	
	public Cliente(String nombre) { 
		super();
		//this.idUsuario = idUsuario;
		this.nombre = nombre;
	}
	
	
	//constructor para el insert
	public Cliente(int idCliente, String nombre, String email, String pass, int edad, String domicilioCalle,
			int domicilioNumero,String localidad,String ultimaSesion,int idUsuario) {
		super();
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.email = email;
		this.pass = pass;
		this.edad = edad;
		this.domicilioCalle = domicilioCalle;
		this.domicilioNumero = domicilioNumero;
		this.localidad = localidad;
		this.ultimaSesion=ultimaSesion;
		this.idUsuario=idUsuario;
		
	}
	
	
	public int getIdCliente() {
		return idCliente;
	}


	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}


	public int getEdad() {
		return edad;
	}


	public void setEdad(int edad) {
		this.edad = edad;
	}


	public String getDomicilioCalle() {
		return domicilioCalle;
	}


	public void setDomicilioCalle(String domicilioCalle) {
		this.domicilioCalle = domicilioCalle;
	}


	public int getDomicilioNumero() {
		return domicilioNumero;
	}


	public void setDomicilioNumero(int domicilioNumero) {
		this.domicilioNumero = domicilioNumero;
	}


	public String getLocalidad() {
		return localidad;
	}


	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}


	public String getUltimaSesion() {
		return ultimaSesion;
	}


	public void setUltimaSesion(String ultimaSesion) {
		this.ultimaSesion = ultimaSesion;
	}


	public int getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}


	public Usuario getUsuario_idUsuario() {
		return Usuario_idUsuario;
	}


	public void setUsuario_idUsuario(Usuario usuario_idUsuario) {
		Usuario_idUsuario = usuario_idUsuario;
	}
	

}
