package com.hotel.beans;

public class Servicio {
	private int idServicio;
	private Hotel hotel; //objeto de tipo hotel (por lo de clave foranea)
	private String descripcion;
	private double precioDiario;
	private boolean estado;
	
	//constructor por defecto
	public Servicio(){
	this.idServicio=0;
	this.hotel=null;//es un objeto lo igualo a null
	this.descripcion="";
	this.precioDiario=0;
	this.estado=false;

	}

	public Servicio(int idServicio, Hotel hotel, String descripcion, double precioDiario, boolean estado) {
		super();
		this.idServicio=idServicio;
		this.hotel = hotel;
		this.descripcion = descripcion;
		this.precioDiario = precioDiario;
		this.estado = estado;
	}
	
	

	public void setIdServicio(int idServicio) {
		this.idServicio = idServicio;
	}

	public int getIdServicio() {
		return idServicio;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecioDiario() {
		return precioDiario;
	}

	public void setPrecioDiario(double precioDiario) {
		this.precioDiario = precioDiario;
	}

	public boolean getEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	
	
	

}
