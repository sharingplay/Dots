package Logica;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;



import Estados.Estado;
import Estados.Estado_Juego;
import Estados.Estado_Menu;
import graficos.Assets;
import graficos.Display;
import graficos.Fondo;



//implementa Runnable para usar hilos
public class Game implements Runnable {
	private Display display;
	
	public String titulo;
	private int ancho;
	private int alto;
	
	private boolean ejecutando = false;
	private Thread hilo;
	
	private BufferStrategy bs;//sirve para evitar el parpadeo de las imagenes
	private Graphics g;

	
	//Estados del juego
	private Estado estadoJuego;
	private Estado estadoMenu;

	
	//Input
	private Controles manejoControles;
	
	public Game(String titulo,int ancho,int alto) {
		this.ancho = ancho;
		this.alto = alto;
		this.titulo = titulo;
		
		manejoControles = new Controles();
		
		
	}
	private void init() {//inicializa
		display = new Display(titulo,ancho,alto);
		display.getFrame().addKeyListener(manejoControles);//
		Assets.init();//inicializa todas las imagenes 1 vez
		
		estadoJuego = new Estado_Juego(this);
		estadoMenu = new Estado_Menu(this);
		Estado.setEstado(estadoJuego);//se establece el estado que se quiere

	}
	
	
	private void update() {//actualiza en el estado en el que se encuentre
		manejoControles.update();//actualiza al jugador de acuerdo a sus controles
		if (Estado.getEstado()!=null) {
			
			Estado.getEstado().update();
			
			
		}
	}
	private void render() {//dibuja 
		
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		
		//limpia todo lo que hay en pantalla
		g.clearRect(0, 0, ancho, alto);
		//Se agrega lo que se quiere dibujar en pantalla
		if (Estado.getEstado()!=null) {
			Estado.getEstado().render(g);
			
			
		}
		bs.show();
		g.dispose();
	}
	public void run() { //
		init();
		
		int fps = 60;
		double timePerTick = 1000000000/fps;//cuantas veces se quiere que se actualice en 1 segundo
		double delta = 0;//tiempo que queda antes de actualizar nuevamente
		long now;//tiempo actual de la computadora en el que se llama
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(ejecutando) {
			
			now = System.nanoTime();//tiempo actual
			
			/*tiempo que paso desde la ultima vez que se asigno un valor a now
			sirve para determinar cada cuanto actualizar la pantalla*/
			delta += (now - lastTime)/timePerTick;
			timer += now - lastTime; 
			lastTime = now;
			
			if(delta >=1 ) {
			update();
			render();
			ticks++;
			delta--;
			}
			if (timer >= 1000000000) {
				System.out.println(ticks);
				ticks = 0;
				timer = 0;
			}
		}
		parar();
	}
	
	public Controles getControles() {
		return manejoControles;
	}
	
	public synchronized void iniciar() {
		if (ejecutando){
			return;
		}
		else {
		ejecutando = true;
		hilo = new Thread(this);//se inicializa esta clase con el hilo
		hilo.start();//inicio del hilo
		}
	}
	public synchronized void parar() {
		if (!ejecutando) {
			return;
		}
		else {
		ejecutando = false;
		try {
			hilo.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
	}
	
}
