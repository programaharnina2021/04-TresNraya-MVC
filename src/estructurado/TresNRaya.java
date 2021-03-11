package estructurado;

import modelo.Coordenada;
import modelo.Tablero;

public class TresNRaya {
	// El tablero tiene los valores iniciales necesarios par comenzar el juego
	// Equivale al metodo iniciar juego
	
	public Coordenada origen=new Coordenada(0, 0),destino=new Coordenada(0, 0);
	// botonx botony almacena las coordenadas de la casilla destino
	// Cuando mover est� a true significa que la pieza seleccionada es para mover
	// Si est� a false la casilla seleccionada no est
	public boolean mover = true;





	


	

	/**
	 * Va a pedir posiciones de la ficha que queremos colocar hasta que hayamos
	 * elegido una posicion libre
	 */

	public boolean realizarJugada() {
		// Turno sirve para conocer a quien le correponde el turno de jugada y as�
		// colocar
		// la ficha correcta
		// Si la jugada es la sexta debemos mover. la variable mover controla que se
		// haya metido la casilla origen
		if (numerojugada > 6) {
			if (!mover) {
				if (origen.casillaContigua(destino) && tablero.mirarCasillaLibre(destino)) {
					tablero.setValorPosicion(origen,0);
					tablero.setValorPosicion(destino,verTurno());
					mover = true;
					numerojugada++;
					return true;
				} // if(casillaContigua())
				else
					return false;
			} // if(mover)
			else {
				if (tablero.comprobarPropiedad(destino,verTurno()) && tablero.comprobarBloqueada(destino)) {
					origen.asignarCoordenada(destino);
					mover = false;
					return true;
				} // if(comprobarPropiedad()&&comprobarBloqueada()
				else
					return false;
			} // if(!mover&&!colocar){
		} // if (numerojugada>6){
		else if (tablero.mirarCasillaLibre(destino)) {
			tablero.setValorPosicion(destino,verTurno());
			incrementaJugada();
			// Si ha modificado el tablero
			return true;
		}
		// si no ha realizado ningun cambio
		else
			return false;

	}// public void realizarJugada(){

	/**
	 * Su cometido es controlar el numero de jugada que llevamos
	 * 
	 */
	private void incrementaJugada() {
		numerojugada++;
	}

	

	

	/**
	 * Esta funcion retorna una "X" cuando se le pasa un 2 por parametro, una "O"
	 * cuando se le pasa un 1 y un espacio en blanco cuando se le pasa un cero
	 */
	public char retornaSimbolo(int numero) {
		switch (numero) {
		case 0:
			return ' ';
		case 1:
			return 'O';
		case 2:
			return 'X';
		}
		return ' ';
	}

	/**
	 * Recoje el valor actual del estado de la jugada para poder dar un mensaje
	 * correcto
	 * 
	 * @param numero
	 */
	public String muestraLetrero() {

		if (numerojugada <= 6)
			return "Seleccione casilla libre";
		else {
			if (mover)
				return "Seleccione casilla a mover";
			else
				return "Seleccione casilla a colocar";
		} // else

	}

	public String indicarAnomalia() {
		if (numerojugada <= 6) {
			if (!tablero.mirarCasillaLibre(destino))
				return "La casilla no est� libre";
		} else {
			if (mover) {
				if (!tablero.comprobarPropiedad(destino,verTurno()) )
					return "La pieza elegida no es correcta";
				if (!tablero.comprobarBloqueada(destino))
					return "La pieza seleccionada esta bloqueada";
			} // if
			else {
				if (!tablero.mirarCasillaLibre(destino))
					return "La casilla no est� libre";
				if (!origen.casillaContigua(destino))
					return "Casilla no contigua";

			} // else
		} // else
		return "";
	}
}
