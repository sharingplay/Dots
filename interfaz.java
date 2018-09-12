package interfaz;
/**
 * importacion de librerias
 */
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.Scene;
 
/**
 * @author Mario
 *interfaz del juego
 */
public class interfaz extends Application {
	public static void main(String[] args){
		//launch() llama start() de JavaFX application
		launch(args);
	}
	 
	@Override
	public void start(Stage primaryStage) throws Exception {
 /**
  * creacion btn1
  */
		Button btn1 = new Button("1");
		btn1.setOnAction(new EventHandler<ActionEvent>(){ //para asignarle una accion al btn1
			@Override
			public void handle(ActionEvent event){//accion que hace el btn1
				System.out.println("boton 1");
			}});
		btn1.setTranslateX(-605);//sets de la posicion btn1
		btn1.setTranslateY(-215);
	/**
	 * creacion btn2
	 */
		Button btn2 = new Button("2");
		btn2.setOnAction(new EventHandler<ActionEvent>(){ //para asignarle una accion al btn2
			@Override
			public void handle(ActionEvent event){//accion que hace el btn2
				System.out.println("boton 2");
			}});
		btn2.setTranslateX(-150);//posicion btn2
		btn2.setTranslateY(240);
		/**
		 * carga imagen de fondo
		 */
		ImageView imagenFondo = new ImageView();//
		Image imagen = new Image("file:DibujoMatriz.png");
		imagenFondo.setImage(imagen);
		StackPane layout = new StackPane();
		layout.getChildren().add(imagenFondo);
		

		
		//Agregar multiples botones
		layout.getChildren().addAll(btn1, btn2);
		
		Scene scene1 = new Scene(layout, 1700, 900); //500 is width, 200 is height of window
		primaryStage.setTitle("Dots");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene1);
		primaryStage.show();
	}
	
}