package com.hotel.beans;


public class Habitacion {
	
	//private int idHabitacion;
	private Hotel hotel; //objeto de tipo hotel (por lo de clave foranea)
	private int numeroHabitacion;
	private String tipo;
	private boolean disponible;
	private String descripcion;
	private int cantidadPlazas;
	private double costoDiario;
	private String imagen;
	
	//constructor por defecto
	public Habitacion(){
	//this.idHabitacion=0;
	this.hotel=null;//es un objeto lo igualo a null
	this.numeroHabitacion=0;
	this.tipo="";
	this.disponible=false;
	this.cantidadPlazas=0;
	this.costoDiario=0;
	this.imagen="";

	}
	
	//constructor para el insert de un nuevo detalle de reserva y para listar reservas
	public Habitacion(int numeroHabitacion) {
		this.numeroHabitacion = numeroHabitacion;
	}
	
	//HAY QUE HACER UN CONSTRUCTOR QUE NO TENGA EL CAMPO ID HABITACION YA QUE ES AUTONUMERICO
	
	//constructor con parametros
	public Habitacion(Hotel hotel, int numeroHabitacion, String tipo, boolean disponible,
			String descripcion, int cantidadPlazas, double costoDiario, String imagen) {
		super();
		//this.idHabitacion = idHabitacion;
		this.hotel = hotel;
		this.numeroHabitacion = numeroHabitacion;
		this.tipo = tipo;
		this.disponible = disponible;
		this.descripcion = descripcion;
		this.cantidadPlazas = cantidadPlazas;
		this.costoDiario = costoDiario;
		this.imagen = imagen;
	}
	
	public Habitacion(int numeroHabitacion, String tipo,String descripcion, int cantidadPlazas, double costoDiario, String imagen) {
		super();
		//this.idHabitacion = idHabitacion;
		//this.hotel = hotel;
		this.numeroHabitacion = numeroHabitacion;
		this.tipo = tipo;
		//this.disponible = disponible;
		this.descripcion = descripcion;
		this.cantidadPlazas = cantidadPlazas;
		this.costoDiario = costoDiario;
		this.imagen = imagen;
	}

	//getters y setters
	/*public int getIdHabitacion() {
		return idHabitacion;
	}*/

	/*public void setIdHabitacion(int idHabitacion) {
		this.idHabitacion = idHabitacion;
	}*/

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public int getNumeroHabitacion() {
		return numeroHabitacion;
	}

	public void setNumeroHabitacion(int numeroHabitacion) {
		this.numeroHabitacion = numeroHabitacion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public boolean getDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getCantidadPlazas() {
		return cantidadPlazas;
	}

	public void setCantidadPlazas(int cantidadPlazas) {
		this.cantidadPlazas = cantidadPlazas;
	}

	public double getCostoDiario() {
		return costoDiario;
	}

	public void setCostoDiario(double costoDiario) {
		this.costoDiario = costoDiario;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	@Override
	public String toString() {
		return "Habitacion " + numeroHabitacion;//para devolver el objeto numeroHabitacion y no la direccion de memoria
		
	}
	
	
	
}
