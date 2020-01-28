package com.hotel.beans;

public class Usuario {
	private int idUsuario;
	private String usuario;
	private String pass;
	private int nivel;
	
	
	public Usuario() {
		
		this.idUsuario=0;
		this.usuario="";
		this.pass="";
		this.nivel=0;
	}


	public Usuario(int idUsuario, String usuario, String pass, int nivel) {
		super();
		this.idUsuario = idUsuario;
		this.usuario = usuario;
		this.pass = pass;
		this.nivel = nivel;
	}


	public int getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}


	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}


	public int getNivel() {
		return nivel;
	}


	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	
	

}
