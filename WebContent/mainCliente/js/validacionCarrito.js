function comprobar()
	{
	   var ingreso = document.form-detalle.start.value;
	   var salida = document.form-detalle.end.value;

	   if (ingreso===salida)
	   {
	      alert("La fecha de salida debe ser mayor a la de ingreso al hotel. Al menos un dia.");
	      return false;
	   }
	   
	   if (ingreso==null & egreso==null)
	   {
	      alert("Complete las fechas de ingreso y egreso.");
	      return false;
	   }
	   
	   return true;
	}
	