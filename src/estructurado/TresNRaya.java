package estructurado;

import modelo.Coordenada;
import modelo.Tablero;

public class TresNRaya {
	// El tablero tiene los valores iniciales necesarios par comenzar el juego
	// Equivale al metodo iniciar juego
	private Tablero tablero=new Tablero();
	public int numerojugada = 2;
	public int origenx, origeny;

	// botonx botony almacena las coordenadas de la casilla destino
	public int destinox, destinoy;
	// Cuando mover est� a true significa que la pieza seleccionada es para mover
	// Si est� a false la casilla seleccionada no est
	public boolean mover = true;

	/**
	 * Comprueba si la casilla destino es contigua a la casilla origen
	 * 
	 * @return True si es contigua false en caso contrario
	 */
	private boolean casillaContigua() {
		int x = destinox - origenx, y = destinoy - origeny;
		if (x > -2 && x < 2 && y > -2 && y < 2)
			return true;
		return false;

	}

	/**
	 * Informa de quien es el turno actual
	 * 
	 * @return 1 o 0 dependiendo de quien sea el propietario del turno
	 */
	public int verTurno() {
		if (numerojugada % 2 == 0)
			return 2;
		return 1;

	}

	


	

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
				if (casillaContigua() && tablero.mirarCasillaLibre(new Coordenada(destinox, destinoy))) {
					tablero.setValorPosicion(new Coordenada(origenx, origeny),0);
					tablero.setValorPosicion(new Coordenada(destinox, destinoy),verTurno());
					mover = true;
					numerojugada++;
					return true;
				} // if(casillaContigua())
				else
					return false;
			} // if(mover)
			else {
				if (tablero.comprobarPropiedad(new Coordenada(destinox, destinoy),verTurno()) && tablero.comprobarBloqueada(new Coordenada(destinox, destinoy))) {
					origenx = destinox;
					origeny = destinoy;
					mover = false;
					return true;
				} // if(comprobarPropiedad()&&comprobarBloqueada()
				else
					return false;
			} // if(!mover&&!colocar){
		} // if (numerojugada>6){
		else if (tablero.mirarCasillaLibre(new Coordenada(destinox, destinoy))) {
			tablero.setValorPosicion(new Coordenada(destinox, destinoy),verTurno());
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
			if (!tablero.mirarCasillaLibre(new Coordenada(destinox, destinoy)))
				return "La casilla no est� libre";
		} else {
			if (mover) {
				if (!tablero.comprobarPropiedad(new Coordenada(destinox, destinoy),verTurno()) )
					return "La pieza elegida no es correcta";
				if (!tablero.comprobarBloqueada(new Coordenada(destinox, destinoy)))
					return "La pieza seleccionada esta bloqueada";
			} // if
			else {
				if (!tablero.mirarCasillaLibre(new Coordenada(destinox, destinoy)))
					return "La casilla no est� libre";
				if (!casillaContigua())
					return "Casilla no contigua";

			} // else
		} // else
		return "";
	}
}
