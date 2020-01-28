package com.hotel.beans;

public class DetalleReservaHabitacion {
	
	private Reserva idReserva; //por FK
	private Habitacion numeroHabitacion; //por fk
	private String fechaIngreso;
	private String fechaSalida;
	private int cantidadDiasReserva;
	private double totalCostoReservaHabitacion;//precioHabitacion * cantidadDias
	
	//constructor sin parametros
	
	public DetalleReservaHabitacion() {
		this.idReserva = null;
		this.numeroHabitacion = null;
		this.fechaIngreso = "";
		this.fechaSalida = "";
		this.cantidadDiasReserva = 0;
		this.totalCostoReservaHabitacion = 0;
	}
	
	public DetalleReservaHabitacion(Reserva idReserva, Habitacion numeroHabitacion, String fechaIngreso,
			String fechaSalida, int cantidadDiasReserva, double totalCostoReservaHabitacion) {
		super();
		this.idReserva = idReserva;
		this.numeroHabitacion = numeroHabitacion;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.cantidadDiasReserva = cantidadDiasReserva;
		this.totalCostoReservaHabitacion = totalCostoReservaHabitacion;
	}

	public Reserva getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(Reserva idReserva) {
		this.idReserva = idReserva;
	}

	public Habitacion getNumeroHabitacion() {
		return numeroHabitacion;
	}

	public void setNumeroHabitacion(Habitacion numeroHabitacion) {
		this.numeroHabitacion = numeroHabitacion;
	}

	public String getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(String fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public int getCantidadDiasReserva() {
		return cantidadDiasReserva;
	}

	public void setCantidadDiasReserva(int cantidadDiasReserva) {
		this.cantidadDiasReserva = cantidadDiasReserva;
	}

	public double getTotalCostoReservaHabitacion() {
		return totalCostoReservaHabitacion;
	}

	public void setTotalCostoReservaHabitacion(double totalCostoReservaHabitacion) {
		this.totalCostoReservaHabitacion = totalCostoReservaHabitacion;
	}
	
	
	

}
