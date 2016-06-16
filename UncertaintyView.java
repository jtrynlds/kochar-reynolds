import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class UncertaintyView extends Application{
	
	private Map map;
	int width, height;
	private static int TILE_SIZE = 10;
	
	public UncertaintyView(Map m){
		map = m;
		width = map.getWidth();
		height = map.getHeight();
	}


	@Override
	public void start(Stage pStage) throws Exception {
		
		//Sets stage title
		pStage.setTitle("This is a game of Uncertainty!");
		
		//Creates canvas to draw shapes on
		Canvas canvas = new Canvas(TILE_SIZE*width, TILE_SIZE*height);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		//Draws the original territories
		for(int i=0; i<width; i++){
			for(int j=0; j<height; j++){
				gc.setFill(Color.web(map.getTerr(i,j).getColor()));
				gc.fillRect(TILE_SIZE*i, TILE_SIZE*j, TILE_SIZE, TILE_SIZE);
				if(map.getTerrType(width*i+j)==1){
					gc.setStroke(Color.BLUE);
					gc.setLineWidth(2);
					gc.strokeLine(TILE_SIZE*i, TILE_SIZE*j, TILE_SIZE*(i+1), TILE_SIZE*(j+1));
				}else if(map.getTerrType(width*i+j)==2){
					gc.setStroke(Color.YELLOW);
					gc.setLineWidth(2);
					gc.strokeLine(TILE_SIZE*i, TILE_SIZE*j, TILE_SIZE*(i+1), TILE_SIZE*(j+1));
				}
			}
		}
		
		
	}
}
