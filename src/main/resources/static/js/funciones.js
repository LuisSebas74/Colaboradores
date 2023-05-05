function exito(id) {

	swal({
		title: "Este registro se ha guardado exitosamente",
		text: "",
		icon: "success",
		buttons: true,
		dangerMode: false,
	})

}


//var a = fgh.replace(/[^0-9.]/g, "");

var fgh
let a;
var valorSelect;
let alertMostrado = false;

function addInput() {
	var select = document.getElementById('colaborador');
	var selectValue = select.value;
	var selectText = select.options[select.selectedIndex].text;
	var form = document.getElementById('asignaciones');
	var input1 = document.createElement('input');
	var label1 = document.createElement('label');
	var input2 = document.createElement('input');
	var br = document.createElement('br');

	input1.type = 'text';
	input1.name = 'porcentajeAsignacion';
	input1.id = 'porcentajeAsignacion';
	input1.placeholder = 'Full-time';
	input1.pattern = "[1-9]{0,1}[0-9]+";
	input1.max = '100';


	label1.setAttribute('for', selectValue);
	let selectTex = label1.innerHTML = selectText;
	a = parseInt(selectTex.replace(/[^0-9.]/g, ""));
	//console.log(typeof a);
    //console.log(a);
	label1.style.display = 'inline-block'; // Agregamos esta línea para alinear el label a la izquierda
	label1.style.width = '40%'; // Personalizamos el ancho del label
	label1.style.margin = '10px';


	input2.type = 'hidden';
	input2.name = 'idColaborador';
	input2.id = 'idColaborador';
	input2.value = selectValue;
	input2.style.display = 'inline-block'; // Agregamos esta línea para alinear el input a la derecha

	form.appendChild(label1);
	form.appendChild(input1);
	form.appendChild(br.cloneNode());
	form.appendChild(input2);
	form.appendChild(input2);
}

function concatenarInputs() {
	// Obtener los inputs con el mismo name
	const inputsColaboradores = document.getElementsByName("idColaborador");
	const inputsporcentajeAsignacion = document.getElementsByName("porcentajeAsignacion");
	// Obtener los valores de los inputs y concatenarlos
	let concatenatedValue = "";
	let concatenatedValuePA = "";
	for (let i = 0; i < inputsColaboradores.length; i++) {
		concatenatedValue += inputsColaboradores[i].value;
		concatenatedValue += "-";
		concatenatedValue += inputsporcentajeAsignacion[i].value;
		if (i < inputsColaboradores.length - 1) {
			concatenatedValue += ",";
		}
	}

	// Asignar el valor concatenado al input deseado
	const concatenatedInput = document.getElementById("colaboradoresPorcentajes");
	concatenatedInput.value = concatenatedValue;
}

function enviarFormulario() {
	// Obtener el formulario por su ID
	const formulario = document.getElementById("formAsignacion");
	concatenarInputs();
	// Enviar el formulario
	formulario.submit();
	if(!alertMostrado){
	   alert("La informacion ha sido guardada exitosamente");
	   alertMostrado=true;
     }
}

function validarPorcentajes() {

	const inputsporcentajeAsignacion = document.getElementsByName("porcentajeAsignacion");

	for (let i = 0; i < inputsporcentajeAsignacion.length; i++) {
		if (inputsporcentajeAsignacion[i].value > 100 || inputsporcentajeAsignacion[i].value == null || inputsporcentajeAsignacion[i].value < 0 ||inputsporcentajeAsignacion[i].value == '' || inputsporcentajeAsignacion[i].value > a) {
			
			if(!alertMostrado){
				alert("Valor de porcentaje incorrecto");
				alertMostrado=true;
				window.location.replace("/asignaciones/vistas/registrar");
			}
		}

		else {
			  validarIniciativa();
		}
	}
}

const select = document.getElementById('iniciativa');

// Agrega un eventListener al elemento select
select.addEventListener('change', function() {
  // Obtén el valor seleccionado del elemento select
    const selectedValue = this.value;
    valorSelect=selectedValue ;
    console.log(selectedValue); // muestra el valor seleccionado en la consola

});
function validarIniciativa() {

      //console.log("valor actual"+ valorSelect);
	 if (valorSelect === '' ||valorSelect === null || valorSelect === 0 ||valorSelect === undefined) {
    // El campo está vacío
        if (!alertMostrado) {
            window.location.replace("/asignaciones/vistas/registrar");
            alert("Seleccione una iniciativa");
            alertMostrado = true; // actualiza la variable para indicar que el alert ya se mostró
        }
     } else {
    // El campo tiene un valor seleccionado
    enviarFormulario();
     }
}

 //window.location.replace("/asignaciones/vistas/registrar");

/*const select = document.getElementById('iniciativa');

// Agrega un eventListener al elemento select
select.addEventListener('change', function validarIniciativa() {
  // Obtén el valor seleccionado del elemento select
  const selectedValue = this.value;

  // Verifica si el valor seleccionado está vacío o es null
  if (selectedValue === '' || selectedValue === null) {
    // El campo está vacío
   alert("Seleccione una iniciativa");
  } else {
    // El campo tiene un valor seleccionado
    enviarFormulario();
  }
});
*/

function requestDeleteAsignacionesColaborador(idIniciativa,idColaborador)
{
	console.log("Eliminar",idIniciativa,idColaborador);
	console.log(" Location ", window.location.href)
	
	//debugger;
	var xhttp = new XMLHttpRequest();
	 xhttp.open("DELETE", "http://localhost:10001/asignaciones/vistas/eliminar?idIniciativa=" +idIniciativa+ "&idColaborador="+idColaborador, true);
	  
	 xhttp.send();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	      //document.getElementById("demo").innerHTML =
	      //this.responseText;
	      //window.location.replace("http://localhost:10001/asignaciones/vistas/listar");
		console.log(" delete OK ")
	// similar behavior as clicking on a link
	     //window.location.href = "http://localhost:10001/asignaciones/vistas/listar";
	
	    }
	  };
}

