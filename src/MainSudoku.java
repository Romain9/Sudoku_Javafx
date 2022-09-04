import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;



public class MainSudoku extends Application
{

	public static void main(String[] args)
	{
		launch(args);
		
		//MakeSudoku sudokuGrid = new MakeSudoku();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		//FXMLLoader loader = new FXMLLoader(getClass().getResource("/Main.fxml"));
		Parent root = FXMLLoader.load(getClass().getResource("/Main.fxml"));
		//Parent root = loader.load();
		
		
		primaryStage.setTitle("Sudoku app");
		
		primaryStage.setResizable(false);
		
		Scene scene = new Scene(root);
		
		Image icon = new Image("sudokuAppLogo.png");
		
		
		primaryStage.getIcons().add(icon);
		
		
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
