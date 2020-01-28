package com.hotel.beans;


public class HabitacionesCarrito {
	
	private int numeroHabitacion;
	private String fechaIngreso;
	private String fechaSalida;
	private int cantidadDias;
	private String descripcion;
	private String tipo;
	private double costoDiario;
	private String imagen;
	public HabitacionesCarrito(int numeroHabitacion,String fechaIngreso,String fechaSalida, int cantidadDias, String descripcion, String tipo, double costoDiario,
			String imagen) {
		super();
		this.numeroHabitacion = numeroHabitacion;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.cantidadDias = cantidadDias;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.costoDiario = costoDiario;
		this.imagen = imagen;
	}
	public int getNumeroHabitacion() {
		return numeroHabitacion;
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
	public void setNumeroHabitacion(int numeroHabitacion) {
		this.numeroHabitacion = numeroHabitacion;
	}
	public int getCantidadDias() {
		return cantidadDias;
	}
	public void setCantidadDias(int cantidadDias) {
		this.cantidadDias = cantidadDias;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
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
	
	
	
	
}
