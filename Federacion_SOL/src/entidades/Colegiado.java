package entidades;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import utils.Datos;
import validaciones.Validaciones;

public class Colegiado {
	private long id;
	private Categoria categoria; // Examen 3 Ejercicio 3
	private DatosPersona persona;

	public Colegiado(long id, Categoria categoria) {
		super();
		this.id = id;
		this.categoria = categoria;
		this.persona = Datos.buscarPersonaPorId(id);
	}

	public Colegiado(long id, Categoria categoria, DatosPersona dp) {
		super();
		this.id = id;
		this.categoria = categoria;
		this.persona = dp;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getPersona() {
		return this.persona.toString();
	}

	// Examen 3 Ejercicio 3
	public static Colegiado nuevoColegiado() {
		Colegiado ret = null;
		long id = -1;
		Categoria cat;
		int elecc = -1;
		DatosPersona dp = null;
		Scanner in;
		boolean valido = false;
		do {
			System.out.println("Introduzca el id del nuevo colegiado:");
			in = new Scanner(System.in);
			id = in.nextInt();
			valido = Validaciones.validarId(id);
			if (!valido)
				System.out.println("ERROR: Valor incorrecto para el identificador.");
			else
				valido = true;
		} while (!valido);

		valido = false;
		do {
			System.out.println("Introduzca la categoria del nuevo colegiado:");
			System.out.println("Seleccione el id de entre las siguientes categorias:");
			Categoria.mostrarTodos();
			in = new Scanner(System.in);
			elecc = in.nextInt();
			if (elecc >= 1 && elecc <= Categoria.values().length)
				valido = true;
		} while (!valido);
		cat = Categoria.values()[elecc - 1];

		System.out.println("Introduzca ahora los datos personales:");
		in = new Scanner(System.in);
		dp = DatosPersona.nuevaPersona();

		ret = new Colegiado(id, cat, dp);
		return ret;
	}
	
	//ejercicio 2 del examen del 16feb
	
	// he implementado el metodo del ejercicio dos aqui creo que de forma correcta
	//abriendo un file output stream  por cada uno de los ficheros que pide implementar
	
	public static void exportarColegeados() {
		String path ="colegeadosjunior.dat";
		String path2 ="colegeadossenior.dat";
		String path3 ="colegeadosespecial.dat";
		
		try {
			FileOutputStream fichero = new FileOutputStream(path,false);
			FileOutputStream fichero2 = new FileOutputStream(path2,false);
			FileOutputStream fichero3 = new FileOutputStream(path3,false);
			
			ObjectOutputStream escritor = new ObjectOutputStream(fichero);
			ObjectOutputStream escritor2 = new ObjectOutputStream(fichero2);
			ObjectOutputStream escritor3 = new ObjectOutputStream(fichero3);
			
			for(Colegiado a:Datos.COLEGIADOS) {
				if(a.categoria==Categoria.JUNIOR ) {
					escritor.writeObject((Colegiado) a);
					escritor.flush();
				}
				if(a.categoria==Categoria.SENIOR ) {
					escritor2.writeObject((Colegiado) a);
					escritor2.flush();
				}
				if(a.categoria==Categoria.ESPECIAL ) {
					escritor3.writeObject((Colegiado) a);
					escritor3.flush();
				}
			}
			escritor.close();
			escritor2.close();
			escritor3.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	//Examen 5 Ejercicio 2
	/**
	 * Función que devuelve una cadena de caracteres con el formato siguiente:
	 * <idColegiado>”. ”<nombre>” (”<documentacion>”) nacido el <fechaNac.año>
	 * “,tfno: “<telefono>” CAT= ”<categoria>
	 */
	@Override
	public String toString() {
		return "" + id + ". " + persona.getNombre() + " ("+ persona.getNifnie().mostrar() +") nacido el "+ persona.getFechaNac().getYear() +", tfno: "+persona.getTelefono()+" CAT="+categoria.getNombre();
	}

}
