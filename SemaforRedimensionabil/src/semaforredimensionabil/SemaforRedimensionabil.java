package semaforredimensionabil;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @sulitas
 */
public class SemaforRedimensionabil extends Application {

	class ResizableCanvas extends Canvas {
                         
		public ResizableCanvas() {
			// redeseneaza canvasul cand se modofica dimensiunea
			widthProperty().addListener(evt -> draw());
			heightProperty().addListener(evt -> draw());
		}

		private void draw() {
			double width = getWidth();
			double height = getHeight();
                        double x0=20, y0=20 , Pspatiu, Dbec, Lsemafor, Hsemafor;    
                        Color culoare[] ={Color.RED,Color.YELLOW,Color.LIMEGREEN};
                                         
                        Hsemafor=height-2*y0;
                        Pspatiu=Hsemafor/28;
                        Dbec=8*Pspatiu;
                        Lsemafor=10*Pspatiu; // in functie de inaltime semafor
                                            
                        GraphicsContext gc = getGraphicsContext2D();
                        gc.clearRect(0, 0, width, height);

                        //deseneaza cadrul exterior al semaforului
                        gc.setStroke(Color.BLACK);  
                        gc.setLineWidth(20);
                        gc.strokeRect(x0,y0,Lsemafor,Hsemafor);
                        gc.setFill(Color.GREY); 
                        gc.fillRect(x0,y0,Lsemafor,Hsemafor);
                         
                        //deseneaza becurile semaforului
                        for(int i=0;i<3;i++) {
                            gc.setStroke(Color.BLACK);
                            gc.setLineWidth(5);
                            gc.strokeOval(x0+Pspatiu,y0+Pspatiu+i*(Pspatiu+Dbec),Dbec,Dbec);
                            gc.setFill(culoare[i]);
                            gc.fillOval(x0+Pspatiu,y0+Pspatiu+i*(Pspatiu+Dbec),Dbec,Dbec);
                        }
                }

		@Override
		public boolean isResizable() {
			return true;
		}

		@Override
		public double prefWidth(double height) {
			return getWidth();
		}

		@Override
		public double prefHeight(double width) {
			return getHeight();
		}
	}

	@Override
	public void start(Stage stage) throws Exception {
		ResizableCanvas canvas = new ResizableCanvas();

		StackPane stackPane = new StackPane();
		stackPane.getChildren().add(canvas);
              //  root.getChildren().add(cerc);

		// Conectarea marimii canvasului cu marimea stackpane
		canvas.widthProperty().bind(stackPane.widthProperty());
		canvas.heightProperty().bind(stackPane.heightProperty());

		stage.setScene(new Scene(stackPane, 150, 250));
		stage.setTitle("Silviu Sulita - JavaFX - Semafor Redimensionabil");
		stage.show();
	}

        /**
        * @param args the command line arguments
        */
	public static void main(String[] args) {
		launch(args);
	}
}
