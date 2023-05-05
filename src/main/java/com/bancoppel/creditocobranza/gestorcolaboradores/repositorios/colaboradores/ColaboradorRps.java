package com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.colaboradores;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.iniciativas.IniciativaDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.solicitudrespuesta.RequestDTO;

@Repository
public class ColaboradorRps {

	@PersistenceContext
	EntityManager entityManager;

	public List<ColaboradorDTO> consultar(RequestDTO request)
	{
		List<Object[]> listaColaboradoresObjects = new ArrayList<>();
		List<ColaboradorDTO> listaColaboradorDto = new ArrayList<>();
		Query nativeQuery = null;
		ColaboradorDTO colaboradorDto = new ColaboradorDTO();
		StringBuilder sql = new StringBuilder();
		StringBuilder sql1 = new StringBuilder();
		StringBuilder fields = new StringBuilder();
		StringBuilder where = new StringBuilder();
		
		colaboradorDto = (ColaboradorDTO) request.getParametrosQuery();
		
		//Se contruye la proyección del select
		sql.append("SELECT ");
		sql.append("CLB.idcolaborador, ");
		sql.append("CLB.idequipo, ");
		sql.append("EQP.tituloequipo, ");
		sql.append("CLB.idespecialidad, ");
		sql.append("ESP.tituloespecialidad, ");
		sql.append("CLB.nombre, ");
		sql.append("CLB.idgerenciasr, ");
		sql.append("GER.nombressr, ");
		sql.append("CLB.comentarionegocio, ");
		sql.append("CLB.idpuesto, ");
		sql.append("PST.titulopuesto, ");
		sql.append("CLB.fulltime, ");
		sql.append("CLB.tipocolaborador, ");
		sql.append("CLB.idempresa, ");
		sql.append("EMP.tituloempresa, ");
		sql.append("CLB.numcolaborador, ");
		sql.append("CLB.idcentro, ");
		sql.append("CEN.titulocentro, ");
		sql.append("CLB.correo, ");
		sql.append("CLB.idlider, ");
		sql.append("GRS.nombresgerentesresponsables, ");
		sql.append("CLB.idubicacion, ");
		sql.append("UBC.tituloubicacion, ");
		sql.append("CLB.idesquema, ");
		sql.append("ESQ.tituloesquema, ");
		sql.append("CLB.ipbanco, ");
		sql.append("CLB.ipvpn, ");
		sql.append("CLB.solicitante ");
		sql.append("FROM public.tcolaboradores CLB ");
		sql.append("inner join public.cequipos EQP on (EQP.idequipo=CLB.idequipo) ");
		sql.append("inner join public.cespecialidades ESP on (ESP.idespecialidad=CLB.idespecialidad) ");
		sql.append("inner join public.cgerenciassr GER on (GER.idgerenciasr=CLB.idgerenciasr) ");
		sql.append("inner join public.cpuestos PST on (PST.idpuesto=CLB.idpuesto) ");
		sql.append("inner join public.cempresas EMP on (EMP.idempresa=CLB.idempresa) ");
		sql.append("inner join public.ccentros CEN on (CEN.idcentro=CLB.idcentro) ");
		sql.append("inner join public.cgerentesresponsables GRS on (GRS.idgerentesresponsables=CLB.idlider) ");
		sql.append("inner join public.cubicaciones UBC on (UBC.idubicacion=CLB.idubicacion) ");
		sql.append("inner join public.cesquemas ESQ on (ESQ.idesquema=CLB.idesquema) ");
		
		if(colaboradorDto.getNombre() !=null && !colaboradorDto.getNombre().equals(""))
		{
			sql.append("where CLB.nombre like('%").append(colaboradorDto.getNombre()).append("%')");
		}

		nativeQuery = entityManager.createNativeQuery(sql.toString());
		listaColaboradoresObjects = nativeQuery.getResultList();
		
		listaColaboradorDto = transformar(listaColaboradoresObjects);
		return listaColaboradorDto;
	}
	
	public String consultarSkills(Integer idColaborador)
	{
		List<Object[]> listaSkillsObjects = new ArrayList<>();
		String listaSkills = "";
		Query nativeQuery = null;

		StringBuilder sql = new StringBuilder();
		StringBuilder fields = new StringBuilder();
		StringBuilder where = new StringBuilder();
		
		//Se contruye la proyección del select
		sql.append("SELECT ");
		//sql.append("CSK.idcolaborador, ");
		//sql.append("CSK.idskill, ");
		sql.append("SKL.tituloSkill ");
		sql.append("FROM public.tcolaboradorskills CSK ");
		sql.append("INNER JOIN public.cskills SKL ON (SKL.idskill=CSK.idskill) ");
		sql.append("WHERE CSK.idcolaborador =").append(idColaborador);


		nativeQuery = entityManager.createNativeQuery(sql.toString());
		listaSkillsObjects = nativeQuery.getResultList();
		
		listaSkills = listaSkillsObjects.toString(); //transformarString(listaSkillsObjects);
		return listaSkills;
	}
	
	public void actualizarPorcentaje(Integer idColaborador, Double porcentajeAsinacion)
	{
		EntityTransaction tx = entityManager.getTransaction(); // obtener la transacción
		tx.begin(); // iniciar la transacción
		
		Query nativeQuery = null;
		StringBuilder sql = new StringBuilder();
		StringBuilder fields = new StringBuilder();
		StringBuilder where = new StringBuilder();
				
		//Se contruye la proyección del select
		sql.append("UPDATE ");
		sql.append("tcolaboradores ");
		sql.append("set ");
		sql.append("fulltime= fulltime-").append(porcentajeAsinacion).append(" ");
		sql.append("where idColaborador=").append(idColaborador);
		

		nativeQuery = entityManager.createNativeQuery(sql.toString());
		nativeQuery.executeUpdate();
		tx.commit();
	}
	
	private List<ColaboradorDTO> transformar(List<Object[]> lista)
	{
		List<ColaboradorDTO> listaColaboradores = new ArrayList<>();
		ColaboradorDTO dto;
		Integer contador=0;
		
		for(Object[] itemLista: lista)
		{
			dto  = new ColaboradorDTO();
			dto.setIdColaborador(Integer.parseInt(itemLista[0].toString()));
			dto.setIdEquipo(Integer.parseInt(itemLista[1].toString()));
			dto.setTituloEquipo(itemLista[2].toString());
			dto.setIdEspecialidad(Integer.parseInt(itemLista[3].toString()));
			dto.setTituloEspecialidad(itemLista[4].toString());
			dto.setNombre(itemLista[5].toString());
			dto.setIdGerenciaSR(Integer.parseInt(itemLista[6].toString()));
			dto.setTituloGenrenciaSR(itemLista[7].toString());
			dto.setComentarioNegocio(itemLista[8].toString());
			dto.setIdPuesto(Integer.parseInt(itemLista[9].toString()));
			dto.setTituloPuesto(itemLista[10].toString());
			dto.setFulltime(Double.valueOf(itemLista[11].toString()));
			dto.setTipoColaborador(itemLista[12].toString());
			dto.setIdEmpresa(Integer.parseInt(itemLista[13].toString()));
			dto.setTituloEmpresa(itemLista[14].toString());
			dto.setNumColaborador(Integer.parseInt(itemLista[15].toString()));
			dto.setIdCentro(Integer.parseInt(itemLista[16].toString()));
			dto.setTituloCentro(itemLista[17].toString());
			dto.setCorreo(itemLista[18].toString());
			dto.setIdLider(Integer.parseInt(itemLista[19].toString()));
			dto.setLider(itemLista[20].toString());
			dto.setIdUbicacion(Integer.parseInt(itemLista[21].toString()));
			dto.setTituloUbicacion(itemLista[22].toString());
			dto.setIdEsquema(Integer.parseInt(itemLista[23].toString()));
			dto.setTituloEsquema(itemLista[24].toString());
			dto.setIpBanco(itemLista[25].toString());
			dto.setIpVPN(itemLista[26].toString());
			dto.setSolicitante(itemLista[27].toString());
			dto.setContador(contador);
			listaColaboradores.add(dto);
		}
		
		return listaColaboradores;
	}
}