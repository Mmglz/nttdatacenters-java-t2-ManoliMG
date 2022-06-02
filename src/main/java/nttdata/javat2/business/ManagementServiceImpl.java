package nttdata.javat2.business;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase que implementa la interfaz ManagmentServiceI.
 * 
 * @author manoli
 *
 */
public class ManagementServiceImpl implements ManagementServiceI {

	/** LOGGER */
	private static final Logger LOG = LoggerFactory.getLogger(ManagementServiceImpl.class);

	/** Constantes String para Logger */
	private static final String LOG_START = "TRAZA DE INICIO";
	private static final String LOG_END = "TRAZA FIN\n";

	/** Mapa database de clave integer y de valor un objeto Empleado */	
	private Map<Integer, Employee> database = new HashMap<>(Employee.getMaximumEmployees());

	/**
	 * Método para dar de alta a empleados.
	 */
	@Override
	public void addNewEmployee(String name, String rank) {

		LOG.info(LOG_START);

		// Si la base de datos ha llegado a su máximo no deja dar de alta a otro empleado.
		if (database.size() == Employee.getMaximumEmployees()) {

			LOG.warn("No se pueden dar de alta más empleados porque la empresa ha llegado a su máximo.");

			// No deja dar de alta al empleado si su nombre o categoría no son correctos.
		} else if (name.isBlank() || rank.isBlank() || !onlyLetters(name)) {

			LOG.error("Nombre y/o categoria incorrectos.");

			// Da de alta a un empleado.
		} else {

			Employee e = new Employee();

			e.setName(name);

			e.setRank(rank);

			database.put(e.getId(), e);

			LOG.info("{} ha sido dado/a de alta como {}", e.getName(), e.getRank());
		}

		LOG.info(LOG_END);
	}

	/**
	 * Método para mostrar todos los empleados del sistema
	 */
	@Override
	public void printAllEmployees() {

		// Muestra si el mapa está vacío.
		if (database.isEmpty()) {

			System.out.println("\nActualmente no hay empleados dados de alta.\n");

			// Recorre el mapa y muestra a todos los empleados dados de alta.
		} else {

			Iterator<Integer> iter = database.keySet().iterator();

			while (iter.hasNext()) {

				Integer key = iter.next();

				System.out.println(database.get(key).toString());
			}
		}
	}

	/**
	 * Método para mostrar el número total de empleados.
	 */
	@Override
	public void printEmployeesTotalNum() {

		System.out.println("\nEn la empresa " + Employee.getCompanyName() + " hay en total " + database.size() + " empleados.\n");
	}

	/**
	 * Método para comprobar que una cadena sólo tiene letras.
	 * 
	 * @param text
	 * @return boolean
	 */
	public static boolean onlyLetters(String text) {

		// Recorre la palabra (text) letra a letra
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);

			// Comprueba que no está entre a y z, A y Z, ni es un espacio
			if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Método para dar de baja a empleados.
	 * 
	 * @param id
	 */
	public void deleteEmployee(int id) {

		LOG.info(LOG_START);

		// Comprueba si el id está en el mapa y borra al empleado.
		if (database.containsKey(id)) {

			database.remove(id);

			LOG.info("El empleado con id {} ha sido dado de baja.", id);

		} else {

			LOG.warn("No hay ningún empleado con id {}.", id);
		}

		LOG.info(LOG_END);
	}

	/**
	 * Método para buscar a un empleado por su id.
	 * 
	 * @param id
	 */
	public void searchEmployee(int id) {

		// Comprueba si el id está en el mapa e imprime la información del empleado.
		if (database.containsKey(id)) {

			System.out.println("El id " + id + " corresponde a " + database.get(id).getName() + ", con categoria " + database.get(id).getRank());

		} else {

			System.out.println("No hay ningún empleado con el id " + id);
		}
	}
}