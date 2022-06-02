package nttdata.javat2.business;

/**
 * Interfaz implementada por la clase ManagementServiceImpl.
 * 
 * @author manoli
 *
 */
public interface ManagementServiceI {

	/**
	 * Método para dar de alta a empleados.
	 * 
	 * @param name
	 * @param rank
	 */
	public void addNewEmployee(String name, String rank);

	
	/**
	 * Método para mostrar todos los empleados del sistema.
	 */
	public void printAllEmployees();

	
	/**
	 * Método para mostrar el número total de empleados.
	 */
	public void printEmployeesTotalNum();
}