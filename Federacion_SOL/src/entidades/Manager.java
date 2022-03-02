package entidades;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Scanner;
import utils.Datos;
import validaciones.Validaciones;

public class Manager {
	private long id;
	private String telefono;
	private String direccion;
	
	Documentacion nifnie;

	private DatosPersona persona;
	
	public Manager() {
		
	}

	public Manager(long id, String telefono, String direccion) {
		super();
		this.id = id;
		this.telefono = telefono;
		this.direccion = direccion;
		this.persona = Datos.buscarPersonaPorId(id);
	}

	public Manager(long id, String telefono, String direccion, DatosPersona dp) {
		super();
		this.id = id;
		this.telefono = telefono;
		this.direccion = direccion;
		this.persona = dp;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public DatosPersona getPersona() {
		return this.persona;
	}

	// Examen 5 Ejercicio 4
	/***
	 * Función que pregunta al usuario por cada uno de los campos para un nuevo
	 * Manager, los valida y si son correctos devuelve un objeto Manager completo
	 * 
	 * @return un objeto Manager completo válido o null si hubo algún error
	 */
	public static Manager nuevoManager() {
		Manager ret = null;
		long id = -1;
		String telefono = "";
		String direccion = "";
		DatosPersona dp = null;
		Scanner in;
		boolean valido = false;
		do {
			System.out.println("Introduzca el id del nuevo mánager:");
			in = new Scanner(System.in);
			id = in.nextInt();
			if (id > 0)
				valido = true;
			else
				System.out.println("Valor incorrecto para el identificador.");
		} while (!valido);

		valido = false;
		do {
			in = new Scanner(System.in);
			System.out.println("Introduzca el telefono de empresa del nuevo mánager");
			telefono = in.nextLine();
			valido = Validaciones.validarTelefono(telefono);
			if (!valido)
				System.out.println("ERROR: El valor introducido para el teéfono no es válido.");
		} while (!valido);

		valido = false;
		do {
			in = new Scanner(System.in);
			System.out.println("Introduzca la dirección del nuevo mánager:");
			direccion = in.next();
			valido = Validaciones.validarDireccion(direccion);
			if (!valido)
				System.out.println("ERROR: El valor introducido para la dirección no es válido.");
		} while (!valido);

		System.out.println("Introduzca ahora los datos personales:");
		in = new Scanner(System.in);
		dp = DatosPersona.nuevaPersona();

		ret = new Manager(id, telefono, direccion, dp);
		return ret;
	}
	//metodo del ejecicio 3 del examen
		public  String data() {
			return " " +persona.getId()+"|"+persona.getNombre()+"|"+persona.getNifnie()+"|"+persona.getFechaNac()+"|"+persona.getTelefono()+"|"+getDireccion();
		}
	//siguiente parte del ejercicio he hecho un intento de importacion pero no se si lo que estoy almacenando en la variable es el 
	//to String de data
		/***
		 * Función para exportar los datos de cada uno de los mánagers de una colección
		 * que se le pasa como parámetro, a través del método data() anterior, separando
		 * la información de cada mánager en una línea distinta.
		 * 
		 * @param managers la coleccion de managers a exportar
		 */ 
		//comentario de luis lo de arriba!
		private static void exportar(Manager[] managers) {
			String path = "managers.txt";
			File fichero = new File(path);
			FileWriter escritor = null;
			PrintWriter buffer = null;
			try {
				try {
					escritor = new FileWriter(fichero, false);
					buffer = new PrintWriter(escritor);
					for (Manager m : managers) {
						buffer.println(m.data());
					}
				} finally {
					if (buffer != null) {
						buffer.close();
					}
					if (escritor != null) {
						escritor.close();
					}
				}
			} catch (FileNotFoundException e) {
				System.out.println("Se ha producido una FileNotFoundException" + e.getMessage());
			} catch (IOException e) {
				System.out.println("Se ha producido una IOException" + e.getMessage());
			} catch (Exception e) {
				System.out.println("Se ha producido una Exception" + e.getMessage());
			}
		}
		
		//implementar la funcion del ejercicio 3 del examen de 16feb
		
		public static void leerManagers() {
			File ficheroM=new File("managers.txt");
			FileReader lector = null;
			BufferedReader buffer = null;
			
			 try {
				lector=new FileReader(ficheroM);
				buffer = new BufferedReader(lector);
				//aqui una variable aux
				String aux;
				
				
				while((aux=buffer.readLine()) != null) {
					String[] campos = aux.split("\\|");
					int idpersona  = Integer.valueOf(campos[0]);
					String nombre = campos[1];
					String Documentos = campos[2];
					String fechanac = campos[3];
					String telefonoP = campos[4];
					int idManager = Integer.valueOf(campos[5]);
					String telefonoM = campos[6];
					String direccionM= campos[7];
					
					// nom  incluyo la condicion por que no me deja comparar los datos
				 for(Equipo e:Datos.EQUIPOS) {
					if(e.getManager().getId()==idManager) {
						String mensaje="D/Dña "+campos[1]+"con NIF/NIE"+Documentos+"nacido el "+
					e.getManager().persona.getFechaNac().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
							+	"representa al equipo "+e.nombre+"de id"+e.id+"durante el año "+e.getAnioinscripcion()
							+"conformado por los siguientes atletas"+e.getAtletas().toString();
					}	 
					 
					if(e.getManager().getId()!=idManager) {
						System.out.println("el manager"+e.getManager().persona.getNombre()+"de id"+e.getManager().id);
					}
						 
					 
				
				 }
				
				}
				
			} 
			 
			 
			 
			 catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			 catch (IOException e) {
					e.printStackTrace();
				}
			 //esto me faltaba agregarlo de lo anterior 
			 finally {
				 if(lector!=null) {
					 try {
						lector.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				 }
				 if(buffer!=null) {
					 try {
						buffer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				 }
				 
			 }
			 
			
			
		}
			
		

	/***
	 * Función que devuelve una cadena de caracteres con los datos del mánager en el
	 * siguiente formato: <idManager> <nombre> ” (” <documentacion> ”) del año ”
	 * <fechaNac.año> ” Tfno1: <Manager.telefono>” ,Tfno2: ” <DatosPersona.telefono>
	 */
	@Override
	public String toString() {
		return "" + id + ". " + persona.getNombre() + " (" + persona.getNifnie().mostrar() + ") del año "
				+ persona.getFechaNac().getYear() + " Tfno1: " + telefono + " , Tfno2:" + persona.getTelefono() + "]";
	}

}
