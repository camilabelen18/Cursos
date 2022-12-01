package controladores;

import java.util.List;

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


import modelo.*;
import servicios.*;


@Controller
public class ControladorCarrito {

	private ServicioCurso servicioCurso;
	private ServicioCarrito servicioCarrito;
	private ServicioUsuario servicioUsuario;

	@Autowired
	public ControladorCarrito(ServicioCurso servicioCurso, ServicioCarrito servicioCarrito, ServicioUsuario servicioUsuario) {
		this.servicioCurso = servicioCurso;
		this.servicioCarrito = servicioCarrito;
		this.servicioUsuario = servicioUsuario;
	}


	@RequestMapping(path ="/vistaCarrito", method = RequestMethod.GET)
	public ModelAndView vistaCarrito(HttpSession sesion) {

		ModelMap model = new ModelMap();
		String view = "";

		// Si comprueba si el usuario tiene iniciada la sesión
		if (sesion.getAttribute("idUsuario") != null) {

			try {
				int id_user = (int) sesion.getAttribute("idUsuario");
				Carrito carrito = servicioCarrito.obtenerCarritoPorIdUsuario(id_user);

				// Si no se obtiene ningun curso del carrito entonces se lanza una excepcion
				List<Curso> cursos = servicioCarrito.obtenerCursosDelCarrito(carrito);

				double preciosTotal = servicioCarrito.getTotalDePrecios(cursos);

				model.put("lista_cursos_carrito", cursos);
				model.put("precio_total", preciosTotal);
				view = "carrito";
			}
			catch (Exception e) {
				// Si el carrito se encuentra vacio entonces se guarda un mensaje iformativo
				model.put("msj", "El carrito se encuentra vacio.");
				view = "carrito";
			}
		}
		else {
			view = "redirect:/";
		}

		return new ModelAndView(view, model);
	}


	//Cambiar a post si es necesario 
	@RequestMapping(path ="/agregarCursoAlCarrito", method = RequestMethod.GET)
	public ModelAndView agregarCursoAlCarrito(@RequestParam("id_curso") int idCurso, HttpSession sesion) {

		ModelMap model = new ModelMap();


		if(sesion.getAttribute("idUsuario")!=null) 
		{
			int id_user = (int) sesion.getAttribute("idUsuario");
			Usuario usuario = servicioUsuario.buscarUsuarioPorID(id_user);
			Curso curso_obtenido = servicioCurso.buscarCursoPorId(idCurso);
			Carrito carrito = servicioCarrito.obtenerCarritoPorIdUsuario(id_user);

			if (!servicioCarrito.existeCursoEnListaCarrito(curso_obtenido, carrito)) {

				if (!servicioUsuario.existeCursoEnListaUsuario(idCurso, usuario)) {

					servicioCarrito.agregarCursoAlCarrito(curso_obtenido, carrito);
					model.put("msj_exito", "Se agrego el curso al carrito con exito!");
				}
				else {
					model.put("msj_error", "El curso " + curso_obtenido.getNombre() + " ya fue comprado.");
				}
			}
			else {
				model.put("msj_error", "El curso " + curso_obtenido.getNombre() + " ya se encuentra en el carrito.");
			}
		}else {
			model.addAttribute("msj_error", "Para comprar necesitas ingresar a tu cuenta.");
		}

		return new ModelAndView("redirect:/verListaCursos", model);
	}


	@RequestMapping(path ="/eliminarCursoDeListaCarrito", method = RequestMethod.GET)
	public ModelAndView eliminarCursoDeListaCarrito(@RequestParam("curso_id") int curso_id, HttpSession sesion) {

		int id_user = (int) sesion.getAttribute("idUsuario");
		Curso curso = servicioCurso.buscarCursoPorId(curso_id);
		Carrito carrito = servicioCarrito.obtenerCarritoPorIdUsuario(id_user);
		Carrito_Curso carritoCurso = servicioCarrito.obtenerCarritoCurso(carrito, curso);

		servicioCarrito.eliminarCursoDelCarrito(carritoCurso);

		return new ModelAndView("redirect:/vistaCarrito");
	}

}
