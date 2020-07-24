package project3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.*;
import javafx.util.Duration;
import java.util.Random;
import javafx.scene.text.Text;
import javafx.scene.text.Font;



public class ColorTrap extends Application
{
    private Scene scene;
    private BorderPane borderPane;
    private Text txtCountDown;
    private Timeline timeline;
    
    private Text trapText = new Text("Trap Word");
    private int numScore = 0;
	private Text score = new Text("Score: " + numScore);
	private Text time = new Text("Time: ");
    private String trapColor;
    private ColorsEnum colors[] = ColorsEnum.values();
    private ArrayList<String> colorList = new ArrayList(Arrays.asList(colors));
    private ArrayList<String> colorFill = new ArrayList(Arrays.asList(colors));
	private ImageView imgVw = new ImageView();
    private Text c1 = new Text();
    private Text c2 = new Text();
    private Text c3 = new Text();
    private Text c4 = new Text();
    private Text c5 = new Text();
    private Text c6 = new Text();
    private Text c7 = new Text();
    private Text[] clicked= {c1,c2,c3,c4,c5,c6,c7};
    private Timeline timeline2;
    
    
    private final int TIMER = 15;
    private int count = 0;

    @Override
    public void start(Stage primaryStage)
    {
        borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: lightgrey");
        scene = new Scene(borderPane, 600, 300);
        primaryStage.setMinHeight(300);
        primaryStage.setMinWidth(600);
        initializeGame();
        startPlay();

        primaryStage.setTitle("Color Trap");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void startPlay()
    {
        chooseTrapWordAndColor();
        colorNameOptions();

        count = TIMER;
        txtCountDown.setText(TIMER + "");
        timeline = new Timeline(new KeyFrame(
                Duration.millis(1000), e -> {
                    

                    if(count >= 0)
                    {
                        txtCountDown.setText(count + "");
                        count--;
                    
                    }
                    else
                    {
                        endOfGame();
                    }
                }));
        timeline.setCycleCount(TIMER + 2);
        timeline.play();
        
        ArrayList<Object> background = new ArrayList<Object>();
        background.add("PINK");
    	background.add("BEIGE");
    	background.add("BURLYWOOD");
    	background.add("CYAN");
    	background.add("GOLD");
    	background.add("LAVENDER");
                   
        timeline2 = new Timeline(new KeyFrame(
                Duration.millis(250), e -> {
                Random random = new Random();
                int num = random.nextInt(background.size());
                String color2 = String.valueOf(background.get(num));
                    if(count >= 0)
                    {
                        borderPane.setStyle("-fx-background-color:" + color2 );  
                    }
                    else
                    {
                        Duration.millis(0);
                        timeline2.stop();
                    }
                }));
        timeline2.setCycleCount(Timeline.INDEFINITE);
        timeline2.play();

    }
    
    public void endOfGame()
    {
        //TODO complete this method as required.

        Text endScore = new Text("Your score: " + numScore);
        endScore.setFont(Font.font("Marker Felt", 40));
        Button playAgain = new Button("Play again");
        VBox v1 = new VBox();
        VBox v2 = new VBox();
        VBox v3 = new VBox();
        VBox v4 = new VBox();
        VBox v5 = new VBox();
        v1.setAlignment(Pos.CENTER);
    	v1.getChildren().addAll(endScore);
    	v2.setAlignment(Pos.BOTTOM_CENTER);
    	v2.getChildren().addAll(playAgain);
    	v5.setSpacing(25);
        v5.getChildren().addAll(v1,v2);
        
        FlowPane fPane2 = new FlowPane();
        fPane2.getChildren().addAll(v5);
        fPane2.setAlignment(Pos.CENTER);
    	borderPane.setCenter(fPane2);
    	borderPane.setBottom(v3);
    	borderPane.setTop(v4);
        BorderPane.setMargin(fPane2, new Insets(0,80,0,80));
        
        borderPane.setStyle("-fx-background-color: lightgrey");
        
        playAgain.setOnAction(e -> {
            numScore = 0;
            initializeGame();
            startPlay();
        });

    }


    public void checkChoice(Text choice)
    {
        //TODO complete this method as required.
    	
        Image correct = new Image("image/correct.png");
        Image wrong = new Image("image/wrong.png");
    	
    	if((choice.getText()).equals(trapColor))
    	{
            numScore++;
            score.setText("Score: " + numScore);
            imgVw.setImage(correct);
        }
        else{
            imgVw.setImage(wrong);
        }
    	
        //Do NOT add any code after this comment
        //Choose a new trap word and options list
        chooseTrapWordAndColor();
        colorNameOptions();
    }

    public void chooseTrapWordAndColor()
    {
        //TODO complete this method as required.
    	
    	Random random = new Random();
    	int num = random.nextInt(colors.length);
    	String color = String.valueOf(colorList.get(num));
    	trapText.setText(color);
    	num = random.nextInt(colors.length);
    	trapText.setFill(Color.valueOf(String.valueOf(colorList.get(num))));
    	trapColor = String.valueOf(colorList.get(num));
    }
    
    public void colorNameOptions()
    {
        //TODO complete this method as required.
    	
    	Collections.shuffle(colorList);
    	Collections.shuffle(colorFill);
    	for(int i = 0; i < colorList.size(); i++)
    	{
        	clicked[i].setText(String.valueOf(colorList.get(i)));
            clicked[i].setFill(Color.valueOf(String.valueOf(colorFill.get(i))));
        	clicked[i].setFont(Font.font("Marker Felt", 40));
    	}
    	
    	for (Text choose : clicked) {
            choose.setOnMouseClicked((MouseEvent e) -> {
                checkChoice(choose);
            });
        }  	

    }

    public void initializeGame()
    {
        //TODO complete this method as required.
    	
    	//Top
    	borderPane.setTop(trapText);
    	trapText.setFont(Font("MARKER FELT", 60));
    	BorderPane.setAlignment(trapText,Pos.CENTER);
        BorderPane.setMargin(trapText, new Insets(0,35,0,35));  	
        
    	//Center
    	FlowPane fPane = new FlowPane(Orientation.HORIZONTAL,35,0);
    	fPane.setAlignment(Pos.CENTER);
        fPane.prefWidthProperty().bind(scene.widthProperty().multiply(100));
        fPane.prefHeightProperty().bind(scene.heightProperty().multiply(2));
        borderPane.setCenter(fPane);
        BorderPane.setMargin(fPane, new Insets(0,55,0,55));
        
        for(int i = 0; i < colorList.size(); i++){
        	clicked[i].setFill(Color.valueOf(String.valueOf(colorFill.get(i))));
        	clicked[i].setFont(Font.font("Marker Felt", 40));
        	fPane.getChildren().add(clicked[i]);
    	}
        
    	//Bottom
        score = new Text("Score: " + numScore);
    	score.setFont(Font.font (20));
    	time = new Text("Time: ");
    	time.setFont(Font.font (20));
        txtCountDown = new Text();       
        txtCountDown.setFont(Font.font(20));
    	imgVw = new ImageView();
        imgVw.setFitWidth(20);
        imgVw.setFitHeight(20);
    	HBox hTime = new HBox();
    	HBox hScore = new HBox();
    	HBox hImage = new HBox();
    	hTime.setAlignment(Pos.BASELINE_RIGHT);
    	hScore.setAlignment(Pos.BASELINE_LEFT);
    	hImage.setAlignment(Pos.BASELINE_CENTER);
    	hTime.getChildren().addAll(time, txtCountDown);
    	hScore.getChildren().add(score);
    	hImage.getChildren().add(imgVw);
    	HBox bottom = new HBox();
    	borderPane.setBottom(bottom);
        bottom.setMaxWidth(600);
    	bottom.getChildren().addAll(hTime, hImage, hScore);
        HBox.setMargin(hTime, new Insets(0,10,0,10));
        HBox.setMargin(hImage, new Insets(0,200,0,200));
        HBox.setMargin(hScore, new Insets(0,10,0,10));
        bottom.prefWidthProperty().bind(scene.widthProperty().multiply(2));
        hScore.prefWidthProperty().bind(scene.widthProperty().multiply(2));
    	
    }
    public static void main(String[] args)
    {
        launch(args);
    }
}