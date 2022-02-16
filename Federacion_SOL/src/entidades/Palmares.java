package entidades;

public class  Palmares<T, S> {
	
	private long id;
	private  T medalla  ;
	private S participante;
	private Prueba prueba;
	private String observaciones ;
	
	//constructor por defecto
	public Palmares() {	
	}
	
	public Palmares(long id,T medalla,S particioante,Prueba prueba,String observaciones) {
		this.id=id;
		this.medalla=medalla;
		this.participante=participante;
		this.prueba=prueba;
		this.observaciones=observaciones;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public T getMedalla() {
		return medalla;
	}

	public void setMedalla(T medalla) {
		this.medalla = medalla;
	}

	public S getParticipante() {
		return participante;
	}

	public void setParticipante(S participante) {
		this.participante = participante;
	}

	public Prueba getPrueba() {
		return prueba;
	}

	public void setPrueba(Prueba prueba) {
		this.prueba = prueba;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	//falta un metodo que muestre aqui en el orden deseado que implementare luego
	//pero podria hacerlo con un to string o un metodo propio de la clase llamado mostrarloquesea()

}
