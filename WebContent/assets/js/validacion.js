$(function(){
	//apunto al formulario 
	$("#frm-registro").validate({
		/*defino estilos para los textos de validacion*/
		errorClass: "miClaseError",
	    validClass: "miClaseValidada",
		
		/*defino las reglas de validacion, usamos un objeto JSON*/
		rules:{
			
			nombre:{
				required:true
				
			},
			
			email:{
				required:true,
				email:true
				
			},
			password:{
				required:true,
				minlength:8,
				maxlength:15
				
			},
			password2:{
				required:true,
				equalTo:"#password"
				
			},
			edad:{
				required:true,
				min:18,
				max:105,
				number:true
			},
			domicilioCalle:{
				required:true
			},
			domicilioNumero:{
				required:true,
				number:true
			},
			localidad:{
				required:true
			}
			
		},
			messages: {
                nombre: {
                    required: "El nombre es un campo obligatorio"
                },
                password: {
                	required: "El password es un campo obligatorio",
                	minlength: "El password debe tener un mínimo de 8 caracteres",
                	maxlength: "El password debe tener un máximo de 15 caracteres"
                },
                password2: {
                	required: "Debe confirmar su password",
                	equalTo: "Las claves tienen que ser iguales",
                	minlength: "El password debe tener un mínimo de 8 caracteres",
                    maxlength: "El password debe tener un máximo de 15 caracteres"
                },
                email:{
    				required:"El email es un campo obligatorio",
    				email:"No es un email válido"
                },
                edad:{
    				required:"La edad es un campo obligatorio",
    				number:"Debe ingresar una edad válida",
    				min: "Debe ser mayor de 18 años para poder registrarse",
    				max:"De verdad tiene esa edad.....!!!"
    			},
    			domicilioCalle:{
    				required:"La calle del domicilio es un campo obligatorio"
    			},
    			domicilioNumero:{
    				required:"El nro. de domicilio es un campo obligatorio",
    				number:"Debe ingresar un número para la dirección"
    			},
    			localidad:{
    				required:"La localidad es un campo obligatorio"
    			}
                
			},
		
			submitHandler: function(form) {
				/*una vez validado envio el formulario* por ajax*/
				 //form.submit();
				var data = $("#frm-registro").serialize();//serializamos los datos enviados
				//console.log(data);
				$.post("clientes.do",data,function(resultado,estado,jqXHR){
					alert(resultado);//imprimo la respuesta en un alert
					
					//limpiamos los campos de texto
					$("#nombre").val("");
					$("#email").val("");
					$("#password").val("");
					$("#password2").val("");
					$("#edad").val("");
					$("#domicilioCalle").val("");
					$("#domicilioNumero").val("");
					$("#localidad").val("");
					
					//redirecciono al presiona aceptar
					var url = "login.jsp";
					$(location).attr('href',url);
					
				});
			}
	
	});
	
	
	
	//validacion del formulario de login
	$("#frm-login").validate({
		
		/*defino estilos para los textos de validacion*/
		errorClass: "miClaseError",
	    validClass: "miClaseValidada",
	    
	   
		
		/*defino las reglas de validacion, usamos un objeto JSON*/
		
		rules:{
			
			email:{
				required:true,
				email:true
				
			},
			contrasena:{
				required:true,
				
			}
		},
		messages: {
            nombre: {
                required: "El nombre es un campo obligatorio"
            },
            contrasena: {
            	required: "El password es un campo obligatorio"
            },
            email:{
				required:"El email es un campo obligatorio",
				email:"No es un email"
            }
		}
	
	});
	
});