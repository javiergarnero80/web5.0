package com.hotel.beans;

public class Hotel {
	
	//mapeamos los campos de tabla Hotel de Base de Datos
	private int idHotel;
	private String nombreHotel; 
	private String direccionCalle;
	private int direccionNumero;
	private String localidad;
	private String provincia;
	private String pais;
	private String telefono;
	private String email;
	
	//constructor por defecto
	public Hotel() {
		this.idHotel=0;
		this.nombreHotel=""; 
		this.direccionCalle="";
		this.direccionNumero=0;
		this.localidad="";
		this.provincia="";
		this.pais="";
		this.telefono="";
		this.email="";
		
	}
	
	//constructor que permite pasar desde formulario insertarHabitacion un idHotel=1 y para la reserva
	public Hotel(int idHotel) {
		this.idHotel=idHotel;
	}
	
	//constructor con parametros
	public Hotel(int idHotel, String nombreHotel, String direccionCalle, int direccionNumero, String localidad,
			String provincia, String pais, String telefono, String email) {
		super();
		this.idHotel = idHotel;
		this.nombreHotel = nombreHotel;
		this.direccionCalle = direccionCalle;
		this.direccionNumero = direccionNumero;
		this.localidad = localidad;
		this.provincia = provincia;
		this.pais = pais;
		this.telefono = telefono;
		this.email = email;
	}
	
	
	//getters y setters
	public int getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(int idHotel) {
		this.idHotel = idHotel;
	}

	public String getNombreHotel() {
		return nombreHotel;
	}

	public void setNombreHotel(String nombreHotel) {
		this.nombreHotel = nombreHotel;
	}

	public String getDireccionCalle() {
		return direccionCalle;
	}

	public void setDireccionCalle(String direccionCalle) {
		this.direccionCalle = direccionCalle;
	}

	public int getDireccionNumero() {
		return direccionNumero;
	}

	public void setDireccionNumero(int direccionNumero) {
		this.direccionNumero = direccionNumero;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
