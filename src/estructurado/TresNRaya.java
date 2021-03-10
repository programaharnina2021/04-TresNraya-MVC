package estructurado;


public class TresNRaya {
	//El tablero tiene los valores iniciales necesarios par comenzar el juego
	//Equivale al metodo iniciar juego
	public int tablero[][]={{0,0,0},{0,1,0},{0,0,0}};
	public int numerojugada=2;
	public int origenx,origeny;
	
	
	//botonx botony almacena las coordenadas de la casilla destino
	public int destinox,destinoy;
	//Cuando mover est� a true significa que la pieza seleccionada es para mover
	//Si est� a false la casilla seleccionada no est
	public boolean mover=true;	
	/**
	 * Comprueba si la casilla destino es contigua a la casilla origen
	 * @return True si es contigua false en caso contrario
	 */
	private boolean casillaContigua(){
		int x=destinox-origenx,y=destinoy-origeny;
		if(x>-2&&x<2&&y>-2&&y<2)
			return true;			
		return false;
			
	}
	/**
	 * Informa de quien es el turno actual
	 * @return 1 o 0 dependiendo de quien sea el propietario del turno
	 */
	public int verTurno(){
		if (numerojugada%2==0) return 2;
		return 1;
		
	}
	/**
	 * Comprueba si la casilla origen esta ocupada por una ficha de la 
	 * propiedad del turno actual
	 * @return true si casilla pertenece al turno actual o false en caso 
	 * contrario
	 */
	private boolean comprobarPropiedad(){
		if (tablero[destinox][destinoy]==verTurno())
			return true;
		
		return false;
	}
	
	/** casilla libre adyacente libre
	 * @return true si encuentra al menos una casilla libre contigua
	 * false si la pieza est� bloqueada
	 */
	private boolean comprobarBloqueada(){
		for (int x=destinox-1;x<=destinox+1;x++)
			for (int y=destinoy-1;y<=destinoy+1;y++)
				if (x>-1&&x<3&&y>-1&&y<3)
					if (tablero[x][y]==0)
						return true;
		return false;
	}
	
	/**Si en la casilla determinada por las coordenadas x e y se encuentra con un cero
	 * seignifica que la casilla est� libre
	 * @param coordenada de fila
	 * @param coordenada de columna
	 * @return verdadero si la casilla esta libre y falso en caso contrario
	 */
	private boolean mirarCasillaLibre(){
		if (tablero[destinox][destinoy]==0){
			return true;
		}
		
		return false;
	}
	/**Va a pedir posiciones de la ficha que queremos colocar hasta que hayamos elegido una 
	 * posicion libre
	 */

	public boolean realizarJugada(){
		//Turno sirve para conocer a quien le correponde el turno de jugada y as� colocar 
		//la ficha correcta
		//Si la jugada es la sexta debemos mover. la variable mover controla que se haya metido la casilla origen
		if (numerojugada>6){
			if(!mover){
				if(casillaContigua()&& mirarCasillaLibre()){
					tablero[origenx][origeny]=0;
					tablero[destinox][destinoy]=verTurno();
					mover=true;
					numerojugada++;
					return true;
				}//if(casillaContigua())	
				else return false;
			}//if(mover)
			else {
				if(comprobarPropiedad()&&comprobarBloqueada()){
					origenx=destinox;
					origeny=destinoy;
					mover=false;
					return true;
				}//if(comprobarPropiedad()&&comprobarBloqueada()
				else return false;
			}//if(!mover&&!colocar){
		}//if (numerojugada>6){
		else if (mirarCasillaLibre()) {
			tablero[destinox][destinoy]=verTurno();
			incrementaJugada();
			//Si ha modificado el tablero
			return true;
			}
			//si no ha realizado ningun cambio
			else return false;
			
	}//public void realizarJugada(){
			
		//Si el numero de jugada es par el turno es del jugador 2 y si no del jugador 1

	/**Su cometido es controlar el numero de jugada que llevamos
	 * 
	 */
	private void incrementaJugada(){
		numerojugada++;
	}
	/**
	 * Mirar las filas del tablero
	 * @return true si hay tres fichas de la misma clase en una fila
	 */
	private boolean compruebaFila(){
		for(int x=0;x<3;x++)
			if (tablero[x][0]==tablero[x][1]&&tablero[x][1]==tablero[x][2]&&tablero[x][1]!=0)
				return true;
		return false;
	}
	/**
	 * Mira las columnas del tablero
	 * @return true si tres fichas de la misma clase en una columna
	 */
	private boolean compruebaColumna(){
		for(int y=0;y<3;y++)
			if (tablero[0][y]==tablero[1][y]&&tablero[1][y]==tablero[2][y]&&tablero[1][y]!=0)
				return true;
		return false;
	}
	/**
	 * Comprueba si alguna diagonal tiene tres en raya
	 * No hace falta comparar si est�n vacias, no puede ocurrir
	 * @return  true si se da la circunstancia
	 */
	private boolean compruebaDiagonal(){
		if (tablero[0][0]==tablero[1][1]&&tablero[1][1]==tablero[2][2])
			return true;
		if (tablero[0][2]==tablero[1][1]&&tablero[1][1]==tablero[2][0])
			return true;
		return false;
	}
	/**Se encarga de llamar a los metodos que comprueban las tres en raya
	 * 
	 * @return true si se han conseguida tres en raya
	 */
	public boolean comprobarTresEnRaya(){
		if(compruebaFila()||compruebaColumna()||compruebaDiagonal())
			return true;
		return false;
	}

/**
 * Esta funcion retorna una "X" cuando se le pasa un 2 por parametro, una "O"
 * cuando se le pasa un 1 y un espacio en blanco cuando se le pasa un cero
 */
	public char retornaSimbolo(int numero){
		switch(numero){
		case 0:return ' ';
		case 1:return 'O';
		case 2:return 'X';
		}
		return ' ';
	}
	/**
	 * Recoje el valor actual del estado de la jugada para poder dar un mensaje correcto
	 * @param numero
	 */
	public String muestraLetrero(){
		
		if (numerojugada<=6) return "Seleccione casilla libre";
			else{
				if (mover)return "Seleccione casilla a mover";
				else return "Seleccione casilla a colocar";
			}//else	
			
	}
	public String indicarAnomalia(){
		if (numerojugada<=6){		
			if (!mirarCasillaLibre())return "La casilla no est� libre";
		}
		else {
			if (mover){				
				if (!comprobarPropiedad())return "La pieza elegida no es correcta";
				if (!comprobarBloqueada())return "La pieza seleccionada esta bloqueada";
			}//if
			else {
				if (!mirarCasillaLibre())return "La casilla no est� libre";
				if (!casillaContigua()) return "Casilla no contigua";
				
			}//else
		}//else	
	return "";		
	}
}

