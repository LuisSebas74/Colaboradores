package com.bancoppel.creditocobranza.gestorcolaboradores.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bancoppel.creditocobranza.gestorcolaboradores.servicios.CatalogoSrv;
import com.bancoppel.creditocobranza.gestorcolaboradores.solicitudrespuesta.RequestDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.solicitudrespuesta.ResponseDTO;

@RestController
@RequestMapping(value = "/catalogos")
public class CatalogoCnt {
	
	@Autowired
	private CatalogoSrv catalogosSrv;
	
	@GetMapping(value = "/{nombreCatalogo}", produces = "appication/json")
    public ResponseEntity<ResponseDTO> consultar(
    		@PathVariable(name = "nombreCatalogo", required = true) String nombreCatalogo){
    	ResponseDTO response = new ResponseDTO();
    	RequestDTO request = new RequestDTO();
    
    	request.setParametrosPath(nombreCatalogo);
  
    	response = catalogosSrv.listar(request);
    	System.out.println(response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
 

}
