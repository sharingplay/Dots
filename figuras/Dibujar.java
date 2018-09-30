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
 
    listaSimple puntos = new listaSimple();
    listaSimple figuras = new listaSimple();
   
    public Dibujar(listaSimple puntos, listaSimple figuras) {
        this.puntos = puntos;
        this.figuras = figuras;
    }
   
    @Override
    /**
     * Dibujar figuras, líneas, etc, en la interfaz
     */
    public void paintComponent(Graphics dibujo) {
        super.paintComponent(dibujo);
       //Renderizar figuras
        Graphics2D dibujo2D = (Graphics2D) dibujo;
        
        //Dibujar los polígonos y asignar color dependiendo del jugador
        for (int i=0; i<figuras.getSize(); i++) {
            Figuras temporal = (Figuras)figuras.getValor(i);
            if (temporal.getPlayer() == 1){//pinta el area de la figura de azul si es jugador 1
                dibujo2D.setColor(Color.blue);
                dibujo2D.fillPolygon(temporal.getPolygon().xpoints, temporal.getPolygon().ypoints, temporal.getPolygon().npoints);
            }
            else {//pinta el area de la figura de rojo si es jugador 2
                dibujo2D.setColor(Color.red);
                dibujo2D.fillPolygon(temporal.getPolygon().xpoints, temporal.getPolygon().ypoints, temporal.getPolygon().npoints);

            }
        }
        dibujo2D.setColor(Color.black);//Color de la línea
        dibujo2D.setStroke(new BasicStroke(6));//grosor de la linea
       /**
        * Obtiene los valores de cada punto y sus vecinos y dibuja una linea entre ellos. 
        */
        for (int i=0;i<puntos.getSize();i++) {
            Punto puntoTemporal = (Punto) puntos.getValor(i);
            for (int j=0;j<puntoTemporal.getVecinos().getSize();j++) {
                Punto vecino = (Punto) puntoTemporal.getVecinos().getValor(j);//saca cada vecino
                int AJUSTE = 8;//ajuste de las lineas para que coincidan con los puntos
               
                dibujo2D.drawLine(puntoTemporal.getX()+AJUSTE, puntoTemporal.getY()+AJUSTE, vecino.getX()+AJUSTE, vecino.getY()+AJUSTE);

            }
        }
        paintComponents(dibujo2D);        
    }
}