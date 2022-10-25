package controladores;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import modelo.Curso;
import modelo.DatosLogin;
import modelo.DatosRegistro;
import modelo.Usuario;
import servicios.ServicioCurso;
import servicios.ServicioUsuario;

@Controller
public class ControladorUsuarios {

	@Autowired
	private ServicioUsuario servicioUsuario;

	@RequestMapping("/registro")
	public ModelAndView irARegistro() {

		ModelMap modelo = new ModelMap();

		DatosRegistro datosRegistro = new DatosRegistro();

		modelo.put("datosRegistro", datosRegistro);

		return new ModelAndView("registroUsuario", modelo);
	}

	@RequestMapping(path = "/validar-registro", method = RequestMethod.POST)
	public ModelAndView registrarNuevoUsuario(@ModelAttribute("datosRegistro") DatosRegistro datosRegistro) {

		ModelMap model = new ModelMap();
		String viewName = "";

		// Se comprueba si ya existe un usuario registrado mediante el email
		if (servicioUsuario.buscarUsuarioPorEmail(datosRegistro.getEmail()) == null) {

			// Se comprueba si las contraseñas ingresadas son iguales
			if (datosRegistro.getContrasenia().equals(datosRegistro.getRepetirContrasenia())) {

				servicioUsuario.registrar(datosRegistro.getNombre(), datosRegistro.getEmail(),
						datosRegistro.getContrasenia());

				model.put("datosLogin", new DatosLogin());
				viewName = "login";
			} else {
				model.put("error", "Las contraseñas no son iguales");
				model.put("datosRegistro", datosRegistro);
				viewName = "registroUsuario";
			}

		} else {
			model.put("error", "Ya existe un usuario registrado con el email ingresado");
			model.put("datosRegistro", datosRegistro);
			viewName = "registroUsuario";
		}
		return new ModelAndView(viewName, model);
	}

	@RequestMapping("/login")
	public ModelAndView irALogin() {

		ModelMap modelo = new ModelMap();

		DatosLogin datosLogin = new DatosLogin();

		modelo.put("datosLogin", datosLogin);

		return new ModelAndView("login", modelo);
	}

	@RequestMapping(path = "/validar-login", method = RequestMethod.POST)
	public ModelAndView validarLogin(@ModelAttribute("datosLogin") DatosLogin datosLogin, HttpSession session) {

		ModelMap model = new ModelMap();

		Usuario usuarioBuscado = servicioUsuario.consultarUsuario(datosLogin.getEmail(), datosLogin.getPassword());

		// Se verifica si se obtuvo un usuario en la bd con el email y contraseña
		// enviados
		if (usuarioBuscado != null) {

			// Si crea la sesion, se guardan los datos del usuario en la sesion y se
			// redirije al index.jsp

			session.setAttribute("idUsuario", usuarioBuscado.getId());
			session.setAttribute("nombreUsuario", usuarioBuscado.getNombre());
			session.setAttribute("ROL", usuarioBuscado.getRol());

			return new ModelAndView("redirect:/");
		} else {
			// si el usuario no existe agrega un mensaje de error en el modelo
			model.put("error", "Usuario o clave incorrectos");
		}

		return new ModelAndView("login", model);
	}

	@RequestMapping(path = "/cerrarSesion")
	public ModelAndView cerrarSesion(HttpSession session) {

		// Se eliminan los datos de la sesion iniciado del usuario y se lo redirije al
		// home
		session.removeAttribute("idUsuario");
		session.removeAttribute("nombreUsuario");
		session.removeAttribute("ROL");

		return new ModelAndView("redirect:/");
	}

	@RequestMapping(path = "/verPerfil", method = RequestMethod.GET)
	public ModelAndView verPerfil(HttpSession session) {

		ModelMap model = new ModelMap();
		String view = "";
		
		try {
			int id_user = Integer.parseInt(session.getAttribute("idUsuario").toString());
			Usuario usuario = servicioUsuario.buscarUsuarioPorID(id_user);

			model.put("usuario", usuario);
			view = "vistaPerfil";
		}
		catch(Exception e) {
			view = "errorVisualizacionPerfil";
		}
		
		return new ModelAndView(view, model);

	}

	@RequestMapping(path = "/editarPerfil", method = RequestMethod.GET)
	public ModelAndView editarPerfil(HttpSession session) {
		ModelMap model = new ModelMap();
		String view = "";
		
		try {
			int id_user = Integer.parseInt(session.getAttribute("idUsuario").toString());
			Usuario usuario = servicioUsuario.buscarUsuarioPorID(id_user);

			model.put("usuario", usuario);
			view = "editarPerfil";
		}
		catch(Exception e) {
			view = "errorVisualizacionPerfil";
		}
		
		return new ModelAndView(view, model);

	}

	@RequestMapping(path = "actualizarCambiosPerfil", method = RequestMethod.POST)
	public ModelAndView actualizarCambiosPerfil(HttpSession session) {
		ModelMap model = new ModelMap();

		int id_user = Integer.parseInt(session.getAttribute("idUsuario").toString());
		Usuario usuario = servicioUsuario.buscarUsuarioPorID(id_user);

		servicioUsuario.modificarUsuario(usuario);
		
		/*session.setAttribute("nombreUsuario", usuario.getNombre());
		session.setAttribute("nombreEmail", usuario.getEmail());
		session.setAttribute("nombrePassword", usuario.getPassword());*/

		model.put("usuario", usuario);

		return new ModelAndView("redirect:/verPerfil", model);

	}

}
