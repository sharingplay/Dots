package figuras;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import figuras.Figuras;
import listas.listaSimple;
 /**
  * Clase dibujar, dibuja en la interfaz gráfica
  * @author wajib
  *
  */
public class Dibujar extends JPanel {
 
    listaSimple nodo = new listaSimple();
    listaSimple figuras = new listaSimple();
   
    public final int player1 = 1;
    public final int player2 = 2;
   
    public Dibujar(listaSimple nodo, listaSimple figuras) {
        this.nodo = nodo;
        this.figuras = figuras;
    }
   
    @Override
    /**
     * Dibujar figuras, líneas, etc, en la interfaz
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
       //Dibujar las líneas
        Graphics2D g2 = (Graphics2D) g;
        
        //Dibujar los polígonos y asignar color dependiendo del jugador
        for (int i=0; i<figuras.getSize(); i++) {
            Figuras temporal = (Figuras)figuras.getValor(i);
            if (temporal.getPlayer() == player1){
                g2.setColor(Color.blue);
                g2.fillPolygon(temporal.getPolygon().xpoints, temporal.getPolygon().ypoints, temporal.getPolygon().npoints);
            }
            else {
                g2.setColor(Color.red);
                g2.fillPolygon(temporal.getPolygon().xpoints, temporal.getPolygon().ypoints, temporal.getPolygon().npoints);
            }
        }
        //Color de la línea
        g2.setColor(Color.black);
        g2.setStroke(new BasicStroke(5));
       /*
        * Obtiene los valores de cada punto y sus vecinos y dibuja una linea entre ellos. 
        */
        for (int i=0;i<nodo.getSize();i++) {
            Punto puntoTemporal = (Punto) nodo.getValor(i);
            for (int j=0;j<puntoTemporal.getVecinos().getSize();j++) {
                Punto vecino = (Punto) puntoTemporal.getVecinos().getValor(j);
               
                int ERROR = 8;
               
                g2.drawLine(puntoTemporal.getX()+ERROR, puntoTemporal.getY()+ERROR, vecino.getX()+ERROR, vecino.getY()+ERROR);
 
               
            }
        }
 
        paintComponents(g);        
       
    }
   
}