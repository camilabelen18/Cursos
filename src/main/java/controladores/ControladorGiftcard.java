package controladores;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import modelo.*;
import servicios.*;

@Controller
public class ControladorGiftcard {
	
	private ServicioGiftcard servicioGiftcard;
	private ServicioUsuario servicioUsuario;
	private ServicioCurso servicioCurso;
	
	@Autowired
	public ControladorGiftcard(ServicioGiftcard servicioGiftcard, ServicioUsuario servicioUsuario, ServicioCurso servicioCurso) {
		this.servicioGiftcard = servicioGiftcard;
		this.servicioUsuario = servicioUsuario;
		this.servicioCurso = servicioCurso;
	}
	

	@RequestMapping(path ="/verGiftcard", method = RequestMethod.GET)
	public ModelAndView verGiftcard(HttpSession session) {
		
		ModelMap model = new ModelMap();
		int idUsuario = (int) session.getAttribute("idUsuario");
		
		Usuario usuario = servicioUsuario.buscarUsuarioPorID(idUsuario);
		
		Giftcard gift = usuario.getGiftcard();
		
		model.put("usuario", usuario);
		model.put("giftcard", gift);
		
		return new ModelAndView("miGiftcard", model);
	}
	
	
	@RequestMapping(path = "/verificarCompraConGiftcard", method = RequestMethod.GET)
	public ModelAndView verificarCompraConGiftcard(HttpSession session, @RequestParam("precioTotal") Double total, @RequestParam("idCurso") int idCurso) {
		
		ModelMap model = new ModelMap();
		Curso curso_obtenido = servicioCurso.buscarCursoPorId(idCurso);
		String viewName = "";
		
		// Si comprueba si el usuario tiene iniciada la sesión
		if (session.getAttribute("idUsuario") != null) {

			int id_user = (int) session.getAttribute("idUsuario");
			Usuario usuario = servicioUsuario.buscarUsuarioPorID(id_user);

			if (!servicioUsuario.existeCursoEnListaUsuario(idCurso, usuario) || curso_obtenido.getEstado() == Estado.CANCELADO) {
				model.put("idCurso", idCurso);
				model.put("precioTotal", total);
				model.put("curso", curso_obtenido);
				viewName = "verificacionGiftcard";
			}
			else {
				model.addAttribute("cursoYaComprado", "El curso ya fue comprado, compre otro curso.");
				viewName = "redirect:/verListaCursos";
			}
		}
		else {
			model.addAttribute("error_sesion", "Para comprar necesitas ingresar a tu cuenta.");
			viewName = "redirect:/verListaCursos";
		}
		
		return new ModelAndView(viewName, model);
	}
	
	
	@RequestMapping(path = "/comprarConGiftcard", method = RequestMethod.POST)
	public ModelAndView comprarConGiftcard(@RequestParam("nroTarjeta") Integer nroTarjeta, @RequestParam("curso_id") int idCurso, HttpSession session) {
		
		ModelMap model = new ModelMap();
		int id_user = Integer.parseInt(session.getAttribute("idUsuario").toString());
		Usuario usuario = servicioUsuario.buscarUsuarioPorID(id_user);
		Curso curso_obtenido = servicioCurso.buscarCursoPorId(idCurso);
		Giftcard giftcard = usuario.getGiftcard();
		String viewName = "";
		
		try {
			// Se verifica si el numero de tarjeta del usuario es igual al numero de tarjeta ingresado.
			// Si no son iguales, lanza una excepcion.
			servicioGiftcard.verificarTarjetaDeGiftcard(giftcard, nroTarjeta);
			
			// Se verifica que el saldo de la giftcard sea suficiente
			servicioGiftcard.verificarSaldoDeGiftcard(giftcard, curso_obtenido);
			
			if(curso_obtenido.getEstado() == Estado.CANCELADO) {
				
				servicioCurso.cambiarEstadoCurso(curso_obtenido, Estado.EN_CURSO);
			}
			else {
				servicioUsuario.guardarCursoEnListaUsuario(curso_obtenido, usuario);
			}
			viewName = "compraRealizada";
		}
		
		catch (TarjetaInvalidaException e) {
			model.put("tarjetaIncorrecta", "El número de tarjeta ingresado es incorrecto.");
			model.put("idCurso", curso_obtenido.getId());
			model.put("precioTotal", curso_obtenido.getPrecio());
			viewName = "verificacionGiftcard";
		}
		
		catch (SaldoInsuficienteException e) {
			model.put("saldoInsuficiente", "El saldo de la giftcard es insuficiente.");
			model.put("idCurso", curso_obtenido.getId());
			model.put("precioTotal", curso_obtenido.getPrecio());
			viewName = "verificacionGiftcard";
		}
		
		return new ModelAndView(viewName, model);
	}
	
	

}
