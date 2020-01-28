package com.hotel.beans;
//bean para tabla Reserva
public class Reserva {
	//campo idReserva es autonumerico
	private int idReserva;
	private Hotel hotel;
	private Cliente idCliente;
	private String fechaHoraReserva;
	private double totalGeneralReserva;
	
	
	//constructor para el insert de detalle reserva
	public Reserva(int idReserva) {
		this.idReserva = idReserva;
	}
	
	
	//constructor sin parametros
	public Reserva() {
		this.idReserva = 0;
		this.hotel = null;//es un objeto lo igualo a null
		this.idCliente = null;
		this.fechaHoraReserva = "";
		this.totalGeneralReserva = 0;
	}
	
	public Reserva(int idReserva,Hotel hotel, Cliente idCliente, String fechaHoraReserva,double totalGeneralReserva) {
		super();
		this.idReserva = idReserva;
		this.hotel = hotel;
		this.idCliente = idCliente;
		this.fechaHoraReserva = fechaHoraReserva;
		this.totalGeneralReserva = totalGeneralReserva;
	}

	
	
	public int getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Cliente getIdCliente() {
		return idCliente;
	}

	public void setCliente(Cliente idCliente) {
		this.idCliente = idCliente;
	}

	public String getFechaHoraReserva() {
		return fechaHoraReserva;
	}

	public void setFechaHoraReserva(String fechaHoraReserva) {
		this.fechaHoraReserva = fechaHoraReserva;
	}

	public double getTotalGeneralReserva() {
		return totalGeneralReserva;
	}

	public void setTotalGeneralReserva(double totalGeneralReserva) {
		this.totalGeneralReserva = totalGeneralReserva;
	}


	@Override
	public String toString() {
		return "Reserva nro.:" + idReserva;
	}
	
	


}
