package FigureToWord;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.geometry.*;

/*
 * GUI For Numbers
 */
public class NumbersToWordGUI extends Application {
	private TextArea outputArea = new TextArea();				// Instance field for output
	private TextField amountBox = new TextField();				// Instance field for input
	
	public static void main(String[] args) {
		Application.launch();
	}

	// Override the start method in Application 
	public void start(Stage primaryStage) {
		VBox bodyPane = new VBox(5);							// The body of the program with the spacing
		HBox topPane = new HBox(15);							// Container with the spacing for label and textfield for number.

		Label amount = new Label("Enter the number ");
		amount.setFont(Font.font("SansSerif", FontWeight.BOLD, FontPosture.REGULAR, 16));
		
		amountBox.setPrefColumnCount(20);							// Set the pref columns for the input textbox

		topPane.setPadding(new Insets(15, 20, 15, 0));
		topPane.getChildren().addAll(amount, amountBox);

		// The label for the text area
		HBox labelHbox = new HBox();
		Label txtAreaLbl = new Label("The figure in word is");
		txtAreaLbl.setFont(Font.font("SansSerif", FontWeight.BOLD, FontPosture.ITALIC, 15)); 
		
		labelHbox.getChildren().add(txtAreaLbl);

		HBox buttomPane = new HBox();
		
		// Other properties for the output text area.
		outputArea.setFont(new Font("SansSerif", 14));
		outputArea.setPrefColumnCount(30);
		outputArea.setPrefRowCount(5);
		outputArea.setWrapText(true);
		outputArea.setEditable(false);
		buttomPane.getChildren().add(outputArea);

		HBox buttonPane = new HBox(10);
		// The Convert button that drives the conversion event.
		Button convertBt = new Button("Convert");
		convertBt.setOnAction(e -> conversion());

		// The Clear button that drives the clear event.
		Button clearBt = new Button("Clear");
		clearBt.setOnAction((e) -> {amountBox.setText("");
								outputArea.setText("");
							});

		// The exit button
		Button exitBt = new Button("Exit");
		exitBt.setOnAction(e -> {System.exit(1);});
		buttonPane.getChildren().addAll(convertBt, clearBt, exitBt);
		buttonPane.setAlignment(Pos.CENTER);
		buttonPane.setPadding(new Insets(10, 0, 5, 0));

		// Adding all the child panes to the body pane
		bodyPane.getChildren().addAll(topPane,labelHbox, buttomPane, buttonPane);
		bodyPane.setAlignment(Pos.TOP_CENTER);
		bodyPane.setPadding(new Insets(10, 10, 10, 40));
		//bodyPane.spacing(10);

		Scene scene = new Scene(bodyPane, 520, 240);
		primaryStage.setTitle("Number to Word");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void conversion(){
		try {
			String enteredFigure = amountBox.getText();
			long figure = Long.parseLong(enteredFigure);
			String result = String.valueOf(NumbersToWord.convertToWord(figure)); 
			outputArea.setText(result);
		}catch (Exception e) {
			outputArea.setText("Check the figure and try again!");
		}
	}
} 