//Ejecutar función en el evento click
document.getElementById("btn_open").addEventListener("click", open_close_menu);

//Declaramos variables
var side_menu = document.getElementById("menu_side");
var btn_open = document.getElementById("btn_open");
var body = document.getElementById("body");
var logo = document.getElementById("logo");
var image=document.getElementById("imagelogo");

//Evento para mostrar y ocultar menú
function open_close_menu() {
	body.classList.toggle("body_move");
	side_menu.classList.toggle("menu__side_move");

	if (logo.style.display === "none") 
	  {
		    logo.style.transition = "opacity 0.5s ease-in-out"; // Agregamos una transición de opacidad de 0.5 segundos con una aceleración suave
            logo.style.opacity = "1"; // Hacemos que el elemento sea visible
            logo.style.display = "block";
		logo.style.display = "block";
		image.style.display="none";
	} else {
		logo.style.display = "none";
		image.style.display="block";
	}
	

}

//Si el ancho de la página es menor a 760px, ocultará el menú al recargar la página

if (window.innerWidth < 760) {

	body.classList.add("body_move");
	side_menu.classList.add("menu__side_move");

}

//Haciendo el menú responsive(adaptable)

window.addEventListener("resize", function() {

	if (window.innerWidth > 760) {

		body.classList.remove("body_move");
		side_menu.classList.remove("menu__side_move");
	

	}

	if (window.innerWidth < 760) {

		body.classList.add("body_move");
		side_menu.classList.add("menu__side_move");
			

	}

});