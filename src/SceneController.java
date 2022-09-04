import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;


public class SceneController implements Initializable
{

	private Stage stage;
	private Scene scene;
	private Parent root;
	
	MakeSudoku grillePleine;
	
	@FXML
	private Button facile;
	
	@FXML
	private Button boutonMoyen;
	
	@FXML
	private Button boutonDifficile;
	
	@FXML
	private AnchorPane menu;
	
	@FXML
	private AnchorPane playground;
	
	@FXML 
	private GridPane grid;
	
	@FXML
	private Label vies;
	
	@FXML
	private Label endMessage;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		
		
		String loc = location.toString();
		
		if(loc.contains("playgroundFacile.fxml"))
		{
			grillePleine = new MakeSudoku();
			vies.setText(Integer.toString(grillePleine.getLives()));
			int[][] grille = grillePleine.getGrid();
			Node[][] emptyGrid = makeListToMat(grid.getChildren());
			
			fillGrid(grille, emptyGrid, 0);
			
		}
		else if(loc.contains("playgroundMoyen.fxml"))
		{
			grillePleine = new MakeSudoku();
			vies.setText(Integer.toString(grillePleine.getLives()));
			int[][] grille = grillePleine.getGrid();
			Node[][] emptyGrid = makeListToMat(grid.getChildren());
			
			fillGrid(grille, emptyGrid, 1);
		}
		else if(loc.contains("playgroundDifficile.fxml"))
		{
			grillePleine = new MakeSudoku();
			vies.setText(Integer.toString(grillePleine.getLives()));
			int[][] grille = grillePleine.getGrid();
			Node[][] emptyGrid = makeListToMat(grid.getChildren());
			
			fillGrid(grille, emptyGrid, 2);
		}
		
		//System.out.print(grid.getChildren());
	}
	
	
	

	public void switchToGame(ActionEvent event) throws IOException
	{
		FXMLLoader loader = null;
		
		switch(((Node)event.getSource()).getId())
		{
			case "facile":
			{
				loader = new FXMLLoader(getClass().getResource("/playgroundFacile.fxml"));
				break;
			}
			case "moyen":
			{
				loader = new FXMLLoader(getClass().getResource("/playgroundMoyen.fxml"));
				break;
			}
			case "difficile":
			{
				loader = new FXMLLoader(getClass().getResource("/playgroundDifficile.fxml"));
				break;
			}
			default:
				break;
		}
		
		
		root = loader.load();//FXMLLoader.load(getClass().getResource("/playground.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		
		
		stage.setResizable(false);
		
		stage.setScene(scene);
		stage.show();
		
	}
	
	
	public Node[][] makeListToMat(ObservableList<Node> lst)
	{
		Node[][] sudokuGrid = new Node[9][9];
		int z = 0;
		for(int i = 0 ; i < 9 ; i++)
		{
			for(int l = 0 ; l < 9 ; l++)
			{
				sudokuGrid[i][l] = lst.get(z);
				z++;
			}
		}
		
		return sudokuGrid;
	}
	
	public void fillGrid(int[][] grille, Node[][] emptyGrid, int difficulty)
	{
		double limite;
		if(difficulty == 0)
		{
			limite = 0.49;
		}
		else if(difficulty == 1)
		{
			limite = 0.58;
		}
		else
		{
			limite = 0.64;
		}
		
		double indice = Math.random();
		int cpt = 0;
		for(int i = 0 ; i < 9 ; i++)
		{
			for(int l = 0 ; l < 9 ; l++)
			{
				if(indice >= limite)
				{
					String val = Integer.toString(grille[i][l]);
					TextField txt = (TextField)emptyGrid[i][l];
					txt.setText(val);
					//txt.setEditable(false);
					txt.setDisable(true);
					txt.setOpacity(1);
					txt.setStyle("-fx-text-fill: black");
					cpt++;
					
				}
				indice = Math.random();
			}
			
		}
		grillePleine.givenIndices = cpt;
	}
	
	
	
	public void checkGrid(KeyEvent event)
	{
		TextField input = (TextField)event.getSource();
		input.setEditable(true);
		
			
		if(!event.getText().matches("0") && event.getCode().getName().contains("Numpad") && grillePleine.getLives() != 0)
		{
			int[][] grille = grillePleine.getGrid();
			if(input.getLength() >= 1)
			{
				String newValue = input.getText().substring(1);
				input.clear();
				input.setText(newValue);
			}
			
			
			if(Integer.parseInt(event.getText()) == grille[(int) input.getProperties().get("gridpane-row")][(int) input.getProperties().get("gridpane-column")])
			{
				input.setStyle("-fx-text-fill: blue");
				
				grillePleine.givenIndices++;
				System.out.println(grillePleine.getIndices());
				input.setText(event.getText());
				input.setDisable(true);
				input.setOpacity(1);
				
			}
			else
			{
				input.setStyle("-fx-text-fill: red");
				grillePleine.lives--;
				vies.setText(Integer.toString(grillePleine.getLives()));
			}
			
			
		}
		else
		{
			input.setEditable(false);
			input.clear();
			input.setText("");
			
		}
		
		if(grillePleine.getLives() == 0)
		{
			endMessage.setText("Perdu !");
		}
		else if(grillePleine.getIndices() == 81)
		{
			endMessage.setText("Gagné !");
		}
	}
		
	
	
	

	
	public void switchToMenu(ActionEvent event) throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("/Main.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		
		stage.setResizable(false);
		
		stage.setScene(scene);
		stage.show();
	}


	
}
