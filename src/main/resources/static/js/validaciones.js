const formulario = document.getElementById("envioDatos");

formulario.addEventListener("submit", function(evento) {


	const itemCatalogoTipoColaborador = document.getElementById("itemCatalogoTipoColaborador").value;
	const itemCatalogoEspecialidad = document.getElementById("itemCatalogoEspecialidad").value;
	const itemCatalogoTipoEmpresa = document.getElementById("itemCatalogoTipoEmpresa").value;
	const itemCatalogoCentro = document.getElementById("itemCatalogoCentro").value;
	const itemCatalogoGerenciaSR = document.getElementById("itemCatalogoGerenciaSR").value;
	const itemCatalogoLider = document.getElementById("itemCatalogoLider").value;
	const itemCatalogoUbicacion = document.getElementById("itemCatalogoUbicacion").value;
	const itemCatalogoEsquema = document.getElementById("itemCatalogoEsquema").value;
	const itemCatalogoPuesto = document.getElementById("itemCatalogoPuesto").value;
	const itemCatalogoEquipo = document.getElementById("itemCatalogoEquipo").value;

	if ("0" === itemCatalogoTipoColaborador) {
		alert("Por favor, seleccione una opción valida para TIPO COLABORADOR");
		evento.preventDefault();
	}

	else if ("0" === itemCatalogoEspecialidad) {
		alert("Por favor, seleccione una opción valida para TIPO ESPECIALIDAD");
		evento.preventDefault();
	}

	else if ("0" === itemCatalogoTipoEmpresa) {
		alert("Por favor, seleccione una opción valida para TIPO EMPRESA");
		evento.preventDefault();
	}

	else if ("0" === itemCatalogoCentro) {
		alert("Por favor, seleccione una opción valida para TIPO CENTRO");
		evento.preventDefault();
	}

	else if ("0" === itemCatalogoGerenciaSR) {
		alert("Por favor, seleccione una opción valida para TIPO GERENCIA SR");
		evento.preventDefault();
	}

	else if ("0" === itemCatalogoLider) {
		alert("Por favor, seleccione una opción valida para TIPO LIDER ASIGNADO");
		evento.preventDefault();
	}

	else if ("0" === itemCatalogoUbicacion) {
		alert("Por favor, seleccione una opción valida para TIPO UBIACION PARA EL COLABORADOR");
		evento.preventDefault();
	}

	else if ("0" === itemCatalogoEsquema) {
		alert("Por favor, seleccione una opción valida para TIPO DE ESQUEMA");
		evento.preventDefault();
	}

	else if ("0" === itemCatalogoFullTime) {
		alert("Por favor, seleccione una opción valida para TIPO FULLTIME %");
		evento.preventDefault();
	}

	else if ("0" === itemCatalogoPuesto) {
		alert("Por favor, seleccione una opción valida para TIPO PUESTO");
		evento.preventDefault();
	}


	else if ("0" === itemCatalogoEquipo) {
		alert("Por favor, seleccione una opción valida para TIPO EQUIPO");
		evento.preventDefault();
	}

});

function seleccionarFila(id) {
	window.location.href = '/colaboradores/vistas/consultar?idColaborador=' + id;
}



function eliminaColaborador(id) {
	window.location.href = '/colaboradores/vistas/eliminar/' + id;
}

function activarInputs() {
    var itemNombre=document.getElementById("nombreColaborador");
    var itemNempleado= document.getElementById("numeroColaborador");
	var itemComentario = document.getElementById("comentario");
	var itemCorreoColab = document.getElementById("correoColaborador");
	var itemipF5 = document.getElementById("ipF5");
	var itemipBanco = document.getElementById("ipBanco");

	var itemCatalogoTipoColaborador = document.getElementById("itemCatalogoTipoColaborador");
	var itemCatalogoEspecialidad = document.getElementById("itemCatalogoEspecialidad");
	var itemCatalogoTipoEmpresa = document.getElementById("itemCatalogoTipoEmpresa");
	var itemCatalogoCentro = document.getElementById("itemCatalogoCentro");
	var itemCatalogoGerenciaSR = document.getElementById("itemCatalogoGerenciaSR");
	var itemCatalogoLider = document.getElementById("itemCatalogoLider");
	var itemCatalogoUbicacion = document.getElementById("itemCatalogoUbicacion");
	var itemCatalogoEsquema = document.getElementById("itemCatalogoEsquema");
	var itemCatalogoPuesto = document.getElementById("itemCatalogoPuesto");
	var itemCatalogoEquipo = document.getElementById("itemCatalogoEquipo");

	itemCatalogoTipoColaborador.disabled = false;
	itemCatalogoEspecialidad.disabled = false;
	itemCatalogoTipoEmpresa.disabled = false;
	itemCatalogoCentro.disabled = false;
	itemCatalogoGerenciaSR.disabled = false;
	itemCatalogoLider.disabled = false;
	itemCatalogoUbicacion.disabled = false;
	itemCatalogoEsquema.disabled = false;
	itemCatalogoPuesto.disabled = false;
	itemCatalogoEquipo.disabled = false;
	itemComentario.disabled = false;
	itemCorreoColab.disabled = false;
	itemipF5.disabled = false;
	itemipBanco.disabled = false;
	itemNombre.disabled=false;
	itemNempleado.disabled=false;
	

	

}


function validador(id, tipoDato) {

	const inputData = document.getElementById(id);

	switch (tipoDato) {

	/*	case 'letras':
			inputData.value = inputData.value.replace(/[^a-zA-Z ]/g, '');
			inputData.value = inputData.value.toUpperCase();
			break;
*/
        case 'letras':
			inputData.value = inputData.value.replace(/[^a-zA-ZÀ-ÿ ]/g, '');
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

	}

}



