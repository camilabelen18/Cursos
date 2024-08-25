package controladores;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import modelo.Estado;
import modelo.Tarjeta;
import modelo.Usuario;
import modelo.Usuario_Curso;
import servicios.PuntosInsuficientesException;
import servicios.ServicioCurso;
import servicios.ServicioTarjeta;
import servicios.ServicioUsuario;
import servicios.UsuarioInexistenteException;

@Controller
public class ControladorPrincipal {
	
	private ServicioUsuario servicioUsuario;
	private ServicioCurso servicioCurso;
	private ServicioTarjeta servicioTarjeta;
	
	@Autowired
	public ControladorPrincipal(ServicioUsuario servicioUsuario, ServicioCurso servicioCurso, ServicioTarjeta servicioTarjeta) {
		this.servicioUsuario = servicioUsuario;
		this.servicioCurso = servicioCurso;
		this.servicioTarjeta = servicioTarjeta;
	}

	@RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView inicio() {		
        return new ModelAndView("redirect:/home");
    }

	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public ModelAndView irAInicio(HttpSession session) {
		ModelMap model = new ModelMap();
		if (session.getAttribute("idUsuario") != null) {
			int id = (int) session.getAttribute("idUsuario");
			Usuario usuario = servicioUsuario.buscarUsuarioPorID(id);
			List<Usuario_Curso> cursos = servicioCurso.getCursosPorEstado(Estado.EN_CURSO, usuario);
			model.put("lista_cursos", cursos);
		}
		return new ModelAndView("index", model);
	}
	
	@RequestMapping(path = "/contacto", method = RequestMethod.GET)
	public ModelAndView irAContacto() {
		return new ModelAndView("contacto");
	}
	
	@RequestMapping(path = "/verMiTarjeta", method = RequestMethod.GET)
	public ModelAndView verMiTarjeta(HttpSession session) {
		ModelMap model = new ModelMap();
		int idUsuario = (int) session.getAttribute("idUsuario");
		Usuario usuario = servicioUsuario.buscarUsuarioPorID(idUsuario);
		Tarjeta tarj = usuario.getTarjeta();
		model.put("usuario", usuario);
		model.put("tarjeta", tarj);
		return new ModelAndView("miTarjeta", model);
	}
	
	@RequestMapping(path = "/enviarPuntos", method = RequestMethod.POST)
	public ModelAndView enviarPuntos(@RequestParam("email") String email, @RequestParam("puntos") Integer puntos, HttpSession session) {
		ModelMap model = new ModelMap();
		int id_user = Integer.parseInt(session.getAttribute("idUsuario").toString());
		Usuario usuario1 = servicioUsuario.buscarUsuarioPorID(id_user);
		Usuario usuario2 = servicioUsuario.buscarUsuarioPorEmail(email);
		Tarjeta tarj1 = usuario1.getTarjeta();
		String viewName = "miTarjeta";
		try {
			servicioUsuario.verificarUsuario(usuario2);
			servicioTarjeta.verificarPuntosDeTarjeta(tarj1, puntos);
			Tarjeta tarj2 = usuario2.getTarjeta();
			servicioTarjeta.enviarPuntos(tarj1, tarj2, puntos);
			servicioUsuario.enviarNotificacion(usuario1, "Se enviaron " + puntos + " puntos a " + usuario2.getNombre(), session);
			servicioUsuario.enviarNotificacion(usuario2, usuario1.getNombre() + " te envio " + puntos + " puntos");
			model.put("puntosEnviados", "Los puntos se enviaron con exito!");
			model.put("usuario", usuario1);
			model.put("tarjeta", tarj1);
		}
		catch (UsuarioInexistenteException e) {
			model.put("usuarioInexistente", "No existe un usuario con ese correo");
			model.put("usuario", usuario1);
			model.put("tarjeta", tarj1);
		}
		catch (PuntosInsuficientesException e) {
			model.put("puntosInsuficientes", "No posee esa cantidad de puntos para enviar");
			model.put("usuario", usuario1);
			model.put("tarjeta", tarj1);
		}
		session.setAttribute("user", servicioUsuario.buscarUsuarioPorID(id_user));
		return new ModelAndView(viewName, model);
	}

}
