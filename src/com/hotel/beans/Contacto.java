package com.hotel.beans;

public class Contacto {
	private int idContacto;
	private String nombre;
	private String email;
	private String consulta;
	private Cliente idCliente;
	
	public Contacto() {
		this.idContacto=0;
		this.nombre="";
		this.email="";
		this.consulta="";
		this.idCliente=null;
	}

	public Contacto(int idContacto, String nombre, String email, String consulta, Cliente idCliente) {
		super();
		this.idContacto = idContacto;
		this.nombre = nombre;
		this.email = email;
		this.consulta = consulta;
		this.idCliente = idCliente;
	}

	public int getIdContacto() {
		return idContacto;
	}

	public void setIdContacto(int idContacto) {
		this.idContacto = idContacto;
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

	public String getConsulta() {
		return consulta;
	}

	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}

	public Cliente getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Cliente idCliente) {
		this.idCliente = idCliente;
	}
	
	

}
