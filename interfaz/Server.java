package interfaz;

import java.awt.*;
import javax.swing.*;

import figuras.Figuras;
import figuras.Punto;
import listas.listaSimple;
import figuras.Dibujar;
import calculos.Calculos;
 
public class Server extends javax.swing.JFrame {
	/**
	 * Clase servidor que contiene la logica del juego
	 */
    Punto desde;//punto de partida
    Punto hacia;//punto de llegada
    listaSimple nodos = new listaSimple();//lista de nodos que recorre
    listaSimple componentes = new listaSimple();//lista de componentes para dibujar
    listaSimple figuras = new listaSimple();//lista de figuras que existen en ese momento
    
    public final int player1 = 1;//constantes para indicar el jugador
    public final int player2 = 2;
    int jugadorActual = 1;
    Calculos operaciones = new Calculos();
   
    public final int ESPACIO = 50;//distancia en pixeles entre puntos
    public final int ALTO = 450;//alto de la ventana en pixeles
    public final int ANCHO = 600;//ancho de la ventana en pixeles
   
    /**
     * Constructor del servidor
     */
    public Server() {
        initComponents();//inicializa los componentes
        for (int i=220; i<ANCHO; i = i + ESPACIO ) {//creacion de las filas
            for (int j=50; j<ALTO; j = j + ESPACIO ) {//creacion de las columnas
                Punto puntoTemporal = new Punto(i, j);
                nodos.agregar(puntoTemporal);
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
     * 
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
    private void initComponents() {//Medidas del label
 
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);//cerrar en x
        setMaximumSize(new java.awt.Dimension(800, 600));//set de dimensiones para que no se puedan modificar
        setMinimumSize(new java.awt.Dimension(800, 600));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 600));
        pack();//ajusta la ventana a las dimensiones
    }                       
 
    public void updateCanvas () {
    	int puntosJ1 = 0;
        int puntosJ2 = 0;
        for (int i=0;i<this.componentes.getSize();i++) {//borra todo lo que esta en pantalla
            remove((Component) componentes.getValor(i));
        }
        //validar y dibujar los elementos nuevos
        validate();
        repaint();
       
        //Modificar puntaje de los jugadores
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
        
        for (int i=0;i<nodos.getSize();i++) {
        	//dibujado de los puntos
            Punto puntoTemporal = (Punto) nodos.getValor(i);
            JButton punto = new JButton(new ImageIcon("images/circulo.png"));
            punto.setBounds(puntoTemporal.getX()-1, puntoTemporal.getY()+1, 15, 15);
            add(punto);
           //detectar click del usuario
            punto.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    click(puntoTemporal);
                }
            });
            componentes.agregar(punto);
        }
       
        if (desde != null ) {
        	//dibujado de las sombras del punto
            JLabel puntoShadeLabel = new JLabel(new ImageIcon("images/sombraCirculo.png"));
            puntoShadeLabel.setBounds(desde.getX()-4, desde.getY()-4, 24, 24); 
            add(puntoShadeLabel);
            componentes.agregar(puntoShadeLabel);
        }
        //
        Dibujar panel = new Dibujar(this.nodos, this.figuras);
        panel.setBounds(0, 0, 600, 800);
        add(panel);
        componentes.agregar(panel);
        revalidate();
        repaint();
       
    }
   
    /**
     * Encontrar los caminos entre los puntos
     * @param desde = coordenada inicial
     * @param hacia = coordenada final
     */
    private void detectarFiguras (Punto desde, Punto hacia) {
        listaSimple resultado = new listaSimple();
        buscarFigura(resultado,desde,hacia);
    } 
    /**
     * Busca el camino entre puntos para ver ya existe una figura con ese mismo camino
     * si no existe la figura la dibuja y la agrega a la lista de las que ya existen
     * @param camino
     * @param actual
     * @param destino
     */
    private void buscarFigura (listaSimple camino, Punto actual, Punto destino) {
       
        if (actual.equals(destino)) {
           
            Punto aux = new Punto (destino.getX(), destino.getY());
            camino.agregar(aux);
            Figuras figura = new Figuras(camino, nodos, jugadorActual);
         
            // revisa si la figura colisiona con otra
            boolean collide = false;
            for (int i=0; i<this.figuras.getSize(); i++) {
                Figuras temporal = (Figuras)this.figuras.getValor(i);
                if (figura.collide(temporal) ){//si interseca se sale
                    collide = true;
                    break;
                }
            }
 
            if (!collide) {//si no interseca la agrega a la lista
                this.figuras.agregar(figura);
            }
            camino.borrar(aux);
        }
         /**
          * revisa si 2 puntos son iguales entre los vecinos
          */
        for (int i = 0; i < actual.getVecinos().getSize(); i++) {
            Punto siguiente = (Punto) actual.getVecinos().getValor(i);//revisa todos los vecinos del punto
            if (camino.getValor(siguiente) == null) {//si los puntos no son iguales los agrega al recorrido de la figura
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
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
 
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Server().setVisible(true);
               
            }
        });
    }           
}