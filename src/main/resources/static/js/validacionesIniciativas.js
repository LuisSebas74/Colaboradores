const formulario = document.getElementById("enviarDatos");

formulario.addEventListener("submit", function(evento) {
	
	const ItemsCatalogoSolicitantes = document.getElementById("ItemsCatalogoSolicitantes").value;
	const ItemsCatalogoTipo = document.getElementById("ItemsCatalogoTipo").value;
	const ItemsCatalogoMOperativo = document.getElementById("ItemsCatalogoMOperativo").value;
	const ItemsCatalogoDireccion = document.getElementById("ItemsCatalogoDireccion").value;
	const ItemsCatalogoEstatus = document.getElementById("ItemsCatalogoEstatus").value;
	const fechainicio = document.getElementById("fechainicio").value;
	const Festimada = document.getElementById("Festimada").value;
	const Ffin = document.getElementById("Ffin").value;
	const Porcentaje = document.getElementById("Porcentaje").value;
	
	
	if (ItemsCatalogoSolicitantes == "0") {
		alert("Por favor, seleccione una opción valida para SOLICITANTE");
		evento.preventDefault();
	}
	
	else if (ItemsCatalogoTipo == "0") {
		alert("Por favor, seleccione una opción valida para TIPO DE PROYECTO");
		evento.preventDefault();
	}
	
	else if (ItemsCatalogoMOperativo == "0") {
		alert("Por favor, seleccione una opción valida para MODELO OPERATIVO");
		evento.preventDefault();
	}
	
	else if (ItemsCatalogoDireccion == "0") {
		alert("Por favor, seleccione una opción valida para DIRECCION");
		evento.preventDefault();
	}
	
	else if (ItemsCatalogoEstatus == "0") {
		alert("Por favor, seleccione una opción valida para ESTATUS");
		evento.preventDefault();
	}
	
	else if(fechainicio > Festimada){
		alert("Fecha estimada no puede ser antes o igual a fecha de inicio");
		evento.preventDefault();
	}
	
	else if(fechainicio > Ffin) {
		alert("Fecha fin no puede ser antes o igual a fecha de inicio");
		evento.preventDefault();
	}
	
	else if(Ffin < Festimada){
		alert("Fecha estimada no puede ser antes o igual a fecha final");
		evento.preventDefault();
	}
	

	
});

function seleccionarFilaIniciativa(id) {
	window.location.href = '/iniciativas/vistas/consultar?folio=' + id;
}

function validadorIniciativa(id, tipoDato) {

	const inputData = document.getElementById(id);

	switch (tipoDato) {

		case 'letras':
			inputData.value = inputData.value.replace(/[^a-zA-Z ]/g, '');
			inputData.value = inputData.value.toUpperCase();
			break;

		case 'numeros':
			inputData.value = inputData.value.replace(/\D/g, '');
			break;

		case 'correos':
			inputData.value = inputData.value.replace(/[^\w@.-]/gi, '');
			break;

		case 'direccionIp':
			inputData.value = inputData.value.replace(/[^\d.]/g, '');
			break;
			
		case 'alfanum':
			inputData.value = inputData.value.replace(/[^a-zA-Z0-9\s-\/]/g, '');
			inputData.value = inputData.value.toUpperCase();
			break;
			
		case 'porcentaje':
			inputData.value = inputData.value.replace(/\D/g, '');
			var a = parseFloat(inputData.value);
			if (a>101){
				alert("El porcentaje no puede ser mayor a 100");
				inputData.value = '';
			}

			break;	
			
			
	}

}

function eliminaIniciativa() {
	const id = document.getElementById("Idiniciativa");
	window.location.href = '/iniciativas/vistas/eliminar/' + id.value;
}














