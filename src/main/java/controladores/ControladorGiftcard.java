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
	public ControladorGiftcard(ServicioGiftcard servicioGiftcard, ServicioUsuario servicioUsuario,
			ServicioCurso servicioCurso) {
		this.servicioGiftcard = servicioGiftcard;
		this.servicioUsuario = servicioUsuario;
		this.servicioCurso = servicioCurso;
	}

	@RequestMapping(path = "/verGiftcard", method = RequestMethod.GET)
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
	public ModelAndView verificarCompraConGiftcard(HttpSession session, @RequestParam("precioTotal") Double total,
			@RequestParam("idCurso") int idCurso) {

		ModelMap model = new ModelMap();
		String viewName = "";

		// Si comprueba si el usuario tiene iniciada la sesión
		if (session.getAttribute("idUsuario") != null) {

			int id_user = (int) session.getAttribute("idUsuario");
			Usuario usuario = servicioUsuario.buscarUsuarioPorID(id_user);
			Curso curso_obtenido = servicioCurso.buscarCursoPorId(idCurso);
			Usuario_Curso usuarioCurso = servicioUsuario.obtenerUsuarioCurso(curso_obtenido, usuario);

			if (!servicioUsuario.existeCursoEnListaUsuario(idCurso, usuario)
					|| usuarioCurso.getEstado() == Estado.CANCELADO) {
				model.put("idCurso", idCurso);
				model.put("precioTotal", total);
				model.put("curso", curso_obtenido);
				viewName = "verificacionGiftcard";
			} else {
				model.addAttribute("cursoYaComprado", "El curso ya fue comprado, compre otro curso.");
				viewName = "redirect:/verListaCursos";
			}
		} else {
			model.addAttribute("error_sesion", "Para comprar necesitas ingresar a tu cuenta.");
			viewName = "redirect:/verListaCursos";
		}

		return new ModelAndView(viewName, model);
	}

	@RequestMapping(path = "/comprarConGiftcard", method = RequestMethod.POST)
	public ModelAndView comprarConGiftcard(@RequestParam("nroTarjeta") Integer nroTarjeta,
			@RequestParam("curso_id") int idCurso, HttpSession session) {

		ModelMap model = new ModelMap();
		int id_user = Integer.parseInt(session.getAttribute("idUsuario").toString());
		Usuario usuario = servicioUsuario.buscarUsuarioPorID(id_user);
		Curso curso_obtenido = servicioCurso.buscarCursoPorId(idCurso);
		Usuario_Curso usuarioCurso = servicioUsuario.obtenerUsuarioCurso(curso_obtenido, usuario);
		Giftcard giftcard = usuario.getGiftcard();
		String viewName = "";

		try {
			// Se verifica si el numero de tarjeta del usuario es igual al numero de tarjeta
			// ingresado.
			// Si no son iguales, lanza una excepcion.
			servicioGiftcard.verificarTarjetaDeGiftcard(giftcard, nroTarjeta);

			// Se verifica que el saldo de la giftcard sea suficiente
			servicioGiftcard.verificarSaldoDeGiftcard(giftcard, curso_obtenido);

			if (servicioUsuario.existeCursoEnListaUsuario(idCurso, usuario)
					&& usuarioCurso.getEstado() == Estado.CANCELADO) {

				servicioCurso.cambiarEstadoCurso(usuarioCurso, Estado.EN_CURSO);
			} else {
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

	@RequestMapping(path = "/puntosEnviados", method = RequestMethod.POST)
	public ModelAndView puntosEnviados(@RequestParam("email") String email, @RequestParam("puntos") Integer puntos,
			HttpSession session) {

		ModelMap model = new ModelMap();
		int id_user = Integer.parseInt(session.getAttribute("idUsuario").toString());
		Usuario usuario1 = servicioUsuario.buscarUsuarioPorID(id_user);
		Usuario usuario2 = servicioUsuario.buscarUsuarioPorEmail(email);
		Giftcard gc1 = usuario1.getGiftcard();
		String viewName = "";
		try {
			servicioUsuario.verificarUsuario(usuario2);
			servicioGiftcard.verificarSaldoDeGiftcard(gc1, puntos);
		
			Giftcard gc2 = usuario2.getGiftcard();
			servicioGiftcard.enviarPuntos(gc1, gc2, puntos);
			servicioUsuario.enviarNotificacion(usuario1, "Se enviaron " + puntos + " puntos a " + usuario2.getNombre(), session);
			servicioUsuario.enviarNotificacion(usuario2, usuario1.getNombre() + " te envi� " + puntos + " puntos");
			viewName = "puntosEnviados";

		} catch (UsuarioInexistenteException e) {
			model.put("usuarioInexistente", "No existe un usuario con ese correo");
			model.put("usuario", usuario1);
			model.put("giftcard", gc1);
			viewName = "miGiftcard";
		} catch (PuntosInsuficientesException e) {
			model.put("puntosInsuficientes", "No posee esa cantidad de puntos para enviar");
			model.put("usuario", usuario1);
			model.put("giftcard", gc1);
			viewName = "miGiftcard";
		}

		return new ModelAndView(viewName, model);
	}
}
