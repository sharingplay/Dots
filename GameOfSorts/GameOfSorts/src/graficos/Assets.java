package graficos;

import java.awt.image.BufferedImage;

public class Assets {
	//dimensiones de cada sprite del jugador
	private static final int ancho = 96;
	private static final int alto = 96;
	//dimensiones dragon 1
	private static final int anchoD1 = 183;
	private static final int altoD1 = 141;
	//dimensiones dragon 2
	private static final int anchoD2 = 175;
	private static final int altoD2 = 106;
	//dimensiones dragon 3
	private static final int anchoD3 = 130;
	private static final int altoD3 = 105;
	
	public static BufferedImage heart,hud,fireball,celeste,cielo,Hud;
	public static BufferedImage[] jugadorVolando;
	public static BufferedImage[] dragon1;
	public static BufferedImage[] dragon2;
	public static BufferedImage[] dragon3;
	
	public static void init() {//sirve para cargar todos los efectos 1 sola vez
		jugadorVolando = new BufferedImage[4];
		dragon1 = new BufferedImage[5];
		dragon2 = new BufferedImage[5];
		dragon3 = new BufferedImage[4];
		
		SpriteSheet hoja = new SpriteSheet(ImageLoader.cargarImagen("/texturas/spritesDragon.png"));//sprites del jugador volando
		SpriteSheet hoja2 = new SpriteSheet(ImageLoader.cargarImagen("/texturas/dragon1.png"));//sprites del dragon 1
		SpriteSheet hoja3 = new SpriteSheet(ImageLoader.cargarImagen("/texturas/dragon2.png"));//sprites del dragon 2
		SpriteSheet hoja4 = new SpriteSheet(ImageLoader.cargarImagen("/texturas/dragon3.png"));//sprites del dragon 3
		
		
		 cielo = ImageLoader.cargarImagen("/texturas/cielo.png");
		 Hud = ImageLoader.cargarImagen("/texturas/Hud.png");
		 heart = ImageLoader.cargarImagen("/texturas/heart.png");
		 hud = ImageLoader.cargarImagen("/texturas/Hud.png");
		 fireball = ImageLoader.cargarImagen("/texturas/Fireball.png");
		
		/*
		 * asignacion imagenes jugador
		 */
		jugadorVolando[0] = hoja.cortarImagen(0, 192, ancho, alto);
		jugadorVolando[1] = hoja.cortarImagen(ancho, 192, ancho, alto);
		jugadorVolando[2] = hoja.cortarImagen(ancho*2, 192, ancho, alto);
		jugadorVolando[3] = hoja.cortarImagen(ancho*3, 192, ancho, alto);
		/*
		 * asignacion imagenes de dragones
		 */
		dragon1[0] = hoja2.cortarImagen(0, 0, anchoD1, altoD1);
		dragon1[1] = hoja2.cortarImagen(anchoD1,0, anchoD1, altoD1);
		dragon1[2] = hoja2.cortarImagen(anchoD1*2,0, anchoD1, altoD1);
		dragon1[3] = hoja2.cortarImagen(anchoD1*3,0, anchoD1, altoD1);
		dragon1[4] = hoja2.cortarImagen(anchoD1*4,0, anchoD1, altoD1);
		
		dragon2[0] = hoja2.cortarImagen(0, 0, anchoD2, altoD2);
		dragon2[1] = hoja2.cortarImagen(anchoD2,0, anchoD2, altoD2);
		dragon2[2] = hoja2.cortarImagen(anchoD2*2,0, anchoD2, altoD2);
		dragon2[3] = hoja2.cortarImagen(anchoD2*3,0, anchoD2, altoD2);
		dragon2[4] = hoja2.cortarImagen(anchoD2*4,0, anchoD2, altoD2);
				
		dragon3[0] = hoja2.cortarImagen(0, 0, anchoD3, altoD3);
		dragon3[1] = hoja2.cortarImagen(anchoD3,0, anchoD3, altoD3);
		dragon3[2] = hoja2.cortarImagen(anchoD3*2,0, anchoD3, altoD3);
		dragon3[3] = hoja2.cortarImagen(anchoD3*3,0, anchoD3, altoD3);
		
	}
}