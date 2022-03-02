package entidades;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import utils.Utilidades;

public class Equipo extends Participante {
	
	private long idEquipo;
	private int anioinscripcion;
	private Manager manager;
	private Atleta[] atletas;
	
	String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Equipo() {
		
	}

	public Equipo(long id, int anioinscripcion, Manager manager, Atleta[] atletas) {
		super();
		this.idEquipo = id;
		this.anioinscripcion = anioinscripcion;
		this.manager = manager;
		this.atletas = atletas;
	}

	public Equipo(long idParticipante, Equipo e, int dorsal, char calle) {
		super(idParticipante, dorsal, calle);
		this.idEquipo = e.idEquipo;
		this.anioinscripcion = e.anioinscripcion;
		this.manager = e.manager;
		this.atletas = e.atletas;
	}

	@Override
	public long getId() {
		return idEquipo;
	}
	@Override
	public void setId(long id) {
		this.idEquipo = id;
	}
	public int getAnioinscripcion() {
		return anioinscripcion;
	}
	public void setAnioinscripcion(int anioinscripcion) {
		this.anioinscripcion = anioinscripcion;
	}
	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	public Atleta[] getAtletas() {
		return atletas;
	}
	public void setAtletas(Atleta[] atletas) {
		this.atletas = atletas;
	}
	
	
	//metodo del examen del dia 2/03 del ejercicio 1
public static Equipo nuevoEquipo() {
		Equipo ret=null;
		Scanner scan=new Scanner(System.in);
		long id=-1;
		int anioinscripcion=-1;
		Manager manager=null;
		//puse 5 por poner un numero concreto de tamaño 
		Atleta[]atletas=new Atleta[5];
		boolean val=false;
		boolean valmax=false;
				do {
		do {
			System.out.println("introduce el id del equipo");
			id=scan.nextLong();
			if(id<0 || id>1000000) {
				System.out.println("valor no valido, introduce un id valido");
			val=false;	
			}
			else {
				val=true;
			}	
		}while(!val);
		val=false;
		do {
			System.out.println("introduce el año de inscripcion del equipo");
			anioinscripcion=scan.nextInt();
			if(anioinscripcion<0|| anioinscripcion>2022) {
				System.out.println("error valor no valido");
				val=false;
			}
			else {
				val=true;
			}	
		}while(!val);
		val=false;
		do {
			System.out.println("introduce un Manager del equipo");
			manager=Manager.nuevoManager();
			val=true;
		}while(!val);
		ArrayList<Atleta> aux= new ArrayList<Atleta>();
		do {
			System.out.println("introduce los atletas del equipo ");
			  for(int i=0;i<5;i++) { 
			aux.add(Atleta.nuevoAtleta());	
			  }
		}while(!val);
		System.out.println("son correctos los datos?");
		boolean decision=Utilidades.leerBoolean();
		if(decision) {
			valmax=true;
		}
		else {
			valmax=false;
		}
				}while(!valmax);
		return ret=new Equipo(id,anioinscripcion,manager,atletas);
	}

    //
  
	//Examen 1 Ejercicio 3
	@Override
	public String toString() {
		String ret = "";
		ret+= "EQ"+idEquipo + ". de " + manager.getPersona().getNombre() + " (" + manager.getDireccion()+") " + atletas.length + " componentes en el equipo:\n";
		for(Atleta a: atletas) {
			ret += a.getId()+". " + a.getPersona().getNombre() + "(" + a.getPersona().getFechaNac().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+ ") "
					+ " Datos físicos:\t"+ a.getPeso()+ "Kgs.\t" + a.getAltura()+"m.\n";
		}
		ret += "Valido durante el " + anioinscripcion;
		return ret;
	}
	
	


}
