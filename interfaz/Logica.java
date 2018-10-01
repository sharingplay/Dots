package interfaz;

import java.awt.*;
import javax.swing.*;

import figuras.Figuras;
import figuras.Punto;
import listas.listaSimple;
import figuras.Dibujar;
import calculos.Calculos;

public class Logica extends javax.swing.JFrame {
	/**
	 * Clase servidor que contiene la logica del juego
	 */
    Punto desde;//punto de partida
    Punto hacia;//punto de llegada
    listaSimple puntos = new listaSimple();//lista de puntos de la malla
    listaSimple componentes = new listaSimple();//lista de componentes para dibujar
    listaSimple figuras = new listaSimple();//lista de figuras que existen en ese momento
    
    public final int player1 = 1;//constantes para indicar el jugador
    public final int player2 = 2;
    int jugadorActual = 0;
    Calculos operaciones = new Calculos();
    //constantes
    public final int ESPACIO = 50;//distancia en pixeles entre puntos
    public final int ALTO = 450;//alto de la ventana en pixeles
    public final int ANCHO = 600;//ancho de la ventana en pixeles
   
    /**
     * Constructor del servidor
     */
    public Logica() {
        inicializacionComponentes();//inicializa las medidas
        for (int i=220; i<ANCHO; i += ESPACIO ) {//creacion de las filas
            for (int j=50; j<ALTO; j += ESPACIO ) {//creacion de las columnas
                Punto puntoTemporal = new Punto(i, j);
                puntos.agregar(puntoTemporal);
            }
        }
        /**
         * colocacion de la imagen del fondo
         */
        JLabel playersLabel = new JLabel(new ImageIcon("images/players.png"));
        playersLabel.setBounds(0,0, 800, 600);
        add(playersLabel);
        updateCanvas();
    }
 
    /**
     * Flags para validar el click
     * @param punto que se selecciona primero
     */
    private void click (Punto punto) {
        if (desde == null) {//primer click
            desde = punto;
        }
        else if (desde != null && desde.equals(punto)) {//se hace click en el mismo punto
            desde = null;
        }
        else {
        	//click en un punto distinto
        	//verifica que la distancia entre los puntos no se exceda de la maxima permitida
        	int distancia = operaciones.distancia(desde.getX(), desde.getY(), punto.getX(), punto.getY());
        	int distanciaMax = operaciones.pitagoras(ESPACIO);
            if ( distancia <= distanciaMax ) {//movimiento valido
            	//establece los puntos a recorrer 
                hacia = punto;
                detectarFiguras(desde, hacia);//asigna los vecinos a los puntos del recorrido
                desde.nuevoVecino(hacia);
                hacia.nuevoVecino(desde);
                desde = null;
                hacia = null;
                cambioJugador(this.jugadorActual);//cambia entre jugadores     
            }
        }
        updateCanvas();
    }
    /** 
     * @param jugadorActual se le ingresa el jugador actual
     * @return jugadorActual se lo asigna al otro jugador para que sea su turno
     */
    private int cambioJugador (int jugadorActual){
    	if (jugadorActual == player1) {
    		this.jugadorActual = player2;
    	}
    	else {
    		this.jugadorActual = player1;
    	}
    	return this.jugadorActual;
    }
    /**
     * Definicion de la ventana
     */
    private void inicializacionComponentes() {
 
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);//cerrar en x
        setMaximumSize(new java.awt.Dimension(800, 600));//set de dimensiones para que no se puedan modificar
        setMinimumSize(new java.awt.Dimension(800, 600));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 600));
        pack();//ajusta la ventana a las dimensiones
    }                       
    /**
     * actualizacion de los elementos en el label
     */
    public void updateCanvas () {
    	int puntosJ1 = 0;
        int puntosJ2 = 0;
        //borra todo lo que esta en pantalla
        for (int i=0;i<this.componentes.getSize();i++) {
            remove((Component) componentes.getValor(i));
        }
        validate();
        repaint();
       
        //Modificar puntaje de los jugadores de acuerdo a la cantidad de figuras completadas
        for (int i=0; i<figuras.getSize(); i++) {
            Figuras temporal = (Figuras)figuras.getValor(i);
            if (temporal.getPlayer() == player1){
                puntosJ1 += temporal.getSegmentos()*2;
            }
            else {
            	puntosJ2 += temporal.getSegmentos()*2;
            }
        }
        /**
         * datos del jugador 1
         */
        JLabel datosJ1 = new JLabel((String)Integer.toString(puntosJ1));
        datosJ1.setFont(new Font("Arial",Font.BOLD,50));
        datosJ1.setForeground(Color.blue);
        datosJ1.setBounds(350, 430, 100, 100);
        add(datosJ1);
        componentes.agregar(datosJ1);
        /**
         * datos del jugador 2
         */
        JLabel datosJ2 = new JLabel((String)Integer.toString(puntosJ2));
        datosJ2.setFont(new Font("Arial",Font.BOLD,50));
        datosJ2.setForeground(Color.red);
        datosJ2.setBounds(350, 490, 100, 100);
        add(datosJ2);
        componentes.agregar(datosJ2);
        
        for (int i=0;i<puntos.getSize();i++) {
        	//dibujado de los puntos
            Punto puntoTemporal = (Punto) puntos.getValor(i);
            JLabel punto = new JLabel(new ImageIcon("images/circulo.png"));
            punto.setBounds(puntoTemporal.getX(), puntoTemporal.getY(), 15, 15);
            
           //detectar click del usuario
            punto.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mousePressed(java.awt.event.MouseEvent evento) {
                    click(puntoTemporal);
                }
            });
            add(punto);
            componentes.agregar(punto);
        }
       
        if (desde != null ) {
        	//dibujado de la sombra del punto seleccionado
            JLabel sombraPunto = new JLabel(new ImageIcon("images/sombraCirculo.png"));
            sombraPunto.setBounds(desde.getX()-4, desde.getY()-5, 25, 25); 
            add(sombraPunto);
            componentes.agregar(sombraPunto);
        }
        //
        Dibujar ventana = new Dibujar(this.puntos, this.figuras);
        ventana.setBounds(0, 0, 600, 800);
        add(ventana);
        componentes.agregar(ventana);
        validate();
        repaint();
       
    }
   
    /**
     * Encontrar los caminos entre los puntos
     * @param desde = coordenada inicial
     * @param hacia = coordenada final
     */
    private void detectarFiguras (Punto desde, Punto hacia) {
        listaSimple camino = new listaSimple();
        buscarFigura(camino,desde,hacia);
    } 
    /**
     * Busca el camino entre puntos para ver ya existe una figura con ese mismo camino
     * si no existe la figura la dibuja y la agrega a la lista de las que ya existen
     * @param camino
     * @param actual
     * @param destino
     */
    private void buscarFigura (listaSimple camino, Punto actual, Punto destino) {
        if (actual.equals(destino)) {//si se completa la figura
           
            Punto aux = new Punto (destino.getX(), destino.getY());
            camino.agregar(aux);
            Figuras figura = new Figuras(camino,jugadorActual);
            // revisa si la figura colisiona con la figura grande
            boolean colision = false;
            for (int i=0; i<this.figuras.getSize(); i++) {
                Figuras temporal = (Figuras)this.figuras.getValor(i);
                if (figura.colisiona(temporal)){//si interseca se sale
                    colision = true;
                    break;
                }
            }
 
            if (colision == false) {//si no interseca la agrega a la lista
                this.figuras.agregar(figura);
            }
            camino.borrar(aux);
        }
         /**
          * revisa si 2 puntos son iguales entre los vecinos
          */
        for (int i = 0; i < actual.getVecinos().getSize(); i++) {
            Punto siguiente = (Punto) actual.getVecinos().getValor(i);//revisa todos los vecinos del punto inicial
            if (camino.getValor(siguiente) == null) {//verifica si el camino de la figura no
                Punto aux = new Punto(actual.getX(), actual.getY());
                camino.agregar(aux);
                buscarFigura(camino, siguiente, destino);
                camino.borrar(aux);
            }
        }        
    }
   
   
    //Metodo Main()
    public static void main(String args[]) {
 
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Logica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Logica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Logica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Logica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Logica().setVisible(true);
               
            }
        });
    }           
}