/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lighting.program;

import java.awt.Color;
import java.io.File;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ahmed Mohamed
 */
public class LightingProgram extends Application {
    float a, b, h, luxValue, DF=0.8f, wattValue, EofLamp, roomIndex, UF;
    int NOFLamp, NoOfUnitsValue;
    
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        Label lengthLabel = new Label("length");
        lengthLabel.setStyle("-fx-font-size: 20 px; -fx-font-weight: bold; margin: 10px");
        TextField lengthInput = new TextField();
        lengthInput.setStyle("-fx-font-size: 20 px; -fx-font-weight: bold; margin: 10px");
        VBox length = new VBox();
        length.getChildren().addAll(lengthLabel, lengthInput);
        
        Label widthLabel = new Label("Width");
        widthLabel.setStyle("-fx-font-size: 20 px; -fx-font-weight: bold; margin: 10px");
        TextField widthInput = new TextField();
        widthInput.setStyle("-fx-font-size: 20 px; -fx-font-weight: bold; margin: 10px");
        VBox width = new VBox(); 
        width.getChildren().addAll(widthLabel, widthInput);
        
        Label heightLabel = new Label("Height");
        heightLabel.setStyle("-fx-font-size: 20 px; -fx-font-weight: bold; margin: 10px");
        TextField heightInput = new TextField();
        heightInput.setStyle("-fx-font-size: 20 px; -fx-font-weight: bold; margin: 10px");
        VBox height = new VBox(); 
        height.getChildren().addAll(heightLabel, heightInput);
        
      
        HBox dimensions = new HBox();
        dimensions.setSpacing(10);
        dimensions.setStyle("-fx-border-style: solid; -fx-border-width: 5px; -fx-padding: 10px");
        dimensions.getChildren().addAll(length, width, height);
        
        Label watt = new Label("Watage of Lamp");
        watt.setStyle("-fx-font-size: 20 px; -fx-font-weight: bold; margin: 10px");
        VBox wattContainer = new VBox();
        Label wattLabel = new Label("Wattage of lamp");
        wattLabel.setStyle("-fx-font-size: 20 px; -fx-font-weight: bold; margin: 10px");
        TextField wattInput = new TextField();
        wattInput.setStyle("-fx-font-size: 20 px; -fx-font-weight: bold; margin: 10px");
        wattContainer.getChildren().addAll(wattLabel, wattInput);
        
        VBox effiecincyContainer = new VBox();
        Label effiecincyLabel = new Label("Lamp effiecincy");
        effiecincyLabel.setStyle("-fx-font-size: 20 px; -fx-font-weight: bold; margin: 10px");
        TextField effiecincyInput = new TextField();
        effiecincyInput.setStyle("-fx-font-size: 20 px; -fx-font-weight: bold; margin: 10px");
        effiecincyContainer.getChildren().addAll(effiecincyLabel, effiecincyInput);
        HBox watBox = new HBox();
        watBox.getChildren().addAll( wattContainer, effiecincyContainer);
        watBox.setSpacing(10);
        Label roomIndexLabel = new Label("Room Index");
        roomIndexLabel.setStyle("-fx-font-size: 20 px; -fx-font-weight: bold; margin: 10px");
        Label roomIndexOutput = new Label("Not calculated");
        roomIndexOutput.setStyle("-fx-font-size: 20 px; -fx-font-weight: bold; margin: 10px");

        Label UFLabel = new Label("U.F");
        UFLabel.setStyle("-fx-font-size: 20 px; -fx-font-weight: bold; margin: 10px");

        Label UFOutout = new Label("Not calculated");
        UFOutout.setStyle("-fx-font-size: 20 px; -fx-font-weight: bold; margin: 10px");

        Label NOFLampLabel = new Label("No. of Lamps");
        NOFLampLabel .setStyle("-fx-font-size: 20 px; -fx-font-weight: bold; margin: 10px");

        Label NOFLampOutput = new Label("Not calculated");
        NOFLampOutput.setStyle("-fx-font-size: 20 px; -fx-font-weight: bold; margin: 10px");
        
        VBox roomIndexContainer = new VBox();
        roomIndexContainer.getChildren().addAll(roomIndexLabel, roomIndexOutput);
        
        VBox UFContainer = new VBox();
        UFContainer.getChildren().addAll(roomIndexLabel, roomIndexOutput);
        
        VBox roomIndexContainer_ = new VBox();
        roomIndexContainer_.getChildren().addAll(UFLabel, UFOutout);
         
        VBox NOFLampContainer = new VBox();

        NOFLampContainer.getChildren().addAll(NOFLampLabel, NOFLampOutput);
        HBox calculatedValues1 = new HBox();
        calculatedValues1.setSpacing(10);
        calculatedValues1.getChildren().addAll(UFContainer, roomIndexContainer_, NOFLampContainer);
       
        Label lux = new Label("Lux  ");
        lux.setStyle("-fx-font-size: 20 px; -fx-font-weight: bold; margin: 10px");
        TextField luxInput = new TextField();
        luxInput.setStyle("-fx-font-size: 20 px; -fx-font-weight: bold; margin: 10px");
        HBox luxBox = new HBox();
        luxBox.getChildren().addAll(lux, luxInput);
        Button calculate = new Button("Calculate");
        calculate.setStyle("-fx-font-size: 20 px; -fx-font-weight: bold; margin: 10px");
        calculate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                a = Float.parseFloat(lengthInput.getText());
                System.out.println("A : " + a);
                b = Float.parseFloat(widthInput.getText());
                System.out.println("B : " + b);
                h = Float.parseFloat(heightInput.getText());
                System.out.println("H : " + h);
                luxValue = Float.parseFloat(luxInput.getText());
                wattValue = Float.parseFloat(wattInput.getText());
                EofLamp = Float.parseFloat(effiecincyInput.getText());
                roomIndex = (a*b)/(h*(a+b));
                UF = (0.44f*roomIndex)/1.6f;
                NOFLamp = (int) Math.ceil((luxValue*a*b)/(EofLamp*UF*DF*wattValue));
                roomIndexOutput.setText(String.valueOf(roomIndex));
                UFOutout.setText(String.valueOf(UF));
                NOFLampOutput.setText(String.valueOf(NOFLamp));
            }
        });
        
        VBox lampBox = new VBox();
        lampBox.setSpacing(20);
        lampBox.setStyle("-fx-border-style: solid; -fx-border-width: 5px; -fx-padding: 10px");
        lampBox.getChildren().addAll(watBox, luxBox, calculate);
        
       Label numberOfLambsLabel = new Label("Enter No. of Lamps            ");
       numberOfLambsLabel.setStyle("-fx-font-size: 20 px; -fx-font-weight: bold; margin: 10px");
       TextField numberOfLambsInput = new TextField();
       numberOfLambsInput.setStyle("-fx-font-size: 20 px; -fx-font-weight: bold; margin: 10px");
       HBox numberOfLambsContainer = new HBox(numberOfLambsLabel, numberOfLambsInput);
       Label lamsPerUnitLabel = new Label("Enter No. of Lamps in unit ");
       lamsPerUnitLabel.setStyle("-fx-font-size: 20 px; -fx-font-weight: bold; margin: 10px");
       TextField NoLampsinUnit = new TextField();
       NoLampsinUnit.setStyle("-fx-font-size: 20 px; -fx-font-weight: bold; margin: 10px");
       Label NoOfUnits = new Label("No. of unit   ");
       NoOfUnits.setStyle("-fx-font-size: 20 px; -fx-font-weight: bold; margin: 10px");
       Label NoOfUnitsOutput = new Label("Not calculated");
       NoOfUnitsOutput.setStyle("-fx-font-size: 20 px; -fx-font-weight: bold; margin: 10px");
       Label NoOfOutLamps = new Label("No. of lamps out   ");
       NoOfOutLamps.setStyle("-fx-font-size: 20 px; -fx-font-weight: bold; margin: 10px");
       Label NoOfOutLampsOutput = new Label("Not calculated");
       NoOfOutLampsOutput.setStyle("-fx-font-size: 20 px; -fx-font-weight: bold; margin: 10px");
       Button calculateNoOfUnits = new Button("Calculate");
       calculateNoOfUnits.setStyle("-fx-font-size: 20 px; -fx-font-weight: bold; margin: 10px");
       calculateNoOfUnits.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                NoOfUnitsValue = (int) Math.floor(Float.parseFloat(numberOfLambsInput.getText()) / Float.parseFloat(NoLampsinUnit.getText()));
                NoOfUnitsOutput.setText(String.valueOf(NoOfUnitsValue));
                NoOfOutLampsOutput.setText(String.valueOf(Integer.parseInt(numberOfLambsInput.getText()) % Integer.parseInt(NoLampsinUnit.getText())));
            }
       });
        HBox NoOfUnitsContainer = new HBox(lamsPerUnitLabel, NoLampsinUnit);
        
        NoOfUnitsContainer.getChildren().addAll();
        HBox NoOfUnitsOutputContainer = new HBox(NoOfUnits, NoOfUnitsOutput);
        HBox NoOfOutLampsContainer = new HBox(NoOfOutLamps, NoOfOutLampsOutput);
        NoOfUnitsOutputContainer.getChildren().addAll();
        VBox unitLampsContainer = new VBox();
        unitLampsContainer.setSpacing(20);
        unitLampsContainer.setStyle("-fx-border-style: solid; -fx-border-width: 5px; -fx-padding: 10px");
        unitLampsContainer.getChildren().addAll(numberOfLambsContainer, NoOfUnitsContainer, NoOfUnitsOutputContainer, NoOfOutLampsContainer, calculateNoOfUnits);

       
        Label noOfEmergencyUnitLabel = new Label("No. of unit emergerncy");
        noOfEmergencyUnitLabel.setStyle("-fx-font-size: 20 px; -fx-font-weight: bold; margin: 10px");
        Label noOfEmergencyUnitOutput = new Label("Not calculated");
        noOfEmergencyUnitOutput.setStyle("-fx-font-size: 20 px; -fx-font-weight: bold; margin: 10px");
        Button noOfEmergencyUnitButton = new Button("Calculate");
        noOfEmergencyUnitButton.setStyle("-fx-font-size: 20 px; -fx-font-weight: bold; margin: 10px");
        noOfEmergencyUnitButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                noOfEmergencyUnitOutput.setText(String.valueOf((int) Math.ceil((NoOfUnitsValue * 0.2))));
            }
        });
        HBox emergencyContainer = new HBox();
        emergencyContainer.setSpacing(20);
        emergencyContainer.getChildren().addAll(noOfEmergencyUnitLabel, noOfEmergencyUnitOutput, noOfEmergencyUnitButton);
        emergencyContainer.setStyle("-fx-border-style: solid; -fx-border-width: 5px; -fx-padding: 10px");
        
        
        Label noOfUnitinLength = new Label("no. of unit in length  ");
        noOfUnitinLength.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; margin: 10px");
        TextField noOfUnitinLengthInput = new TextField();
        noOfUnitinLengthInput.setStyle("-fx-font-size: 20 px; -fx-font-weight: bold; margin: 10px");
        HBox noOfUnitinLengthContainer = new HBox(noOfUnitinLength, noOfUnitinLengthInput);
        Label noOfUnitinWidth = new Label("no. of unit in width   ");
        noOfUnitinWidth.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; margin: 10px");
        TextField noOfUnitinWidthInput = new TextField();
        noOfUnitinWidthInput.setStyle("-fx-font-size: 20 px; -fx-font-weight: bold; margin: 10px");
        HBox noOfUnitinWidthContainer = new HBox(noOfUnitinWidth, noOfUnitinWidthInput);
        Label spactToHeightWidth = new Label("Space to height ratio Width  ");
        spactToHeightWidth.setStyle("-fx-font-size: 20 px; -fx-font-weight: bold; margin: 10px");
        Label checkWidth = new Label();
        checkWidth.setVisible(false);
        spactToHeightWidth.setStyle("-fx-font-size: 20 px; -fx-font-weight: bold; margin: 10px");
        Label spactToHeightWidthOutput = new Label("Not calculated");
        spactToHeightWidthOutput.setStyle("-fx-font-size: 20 px; -fx-font-weight: bold; margin: 10px");

        HBox spactToHeightWidthContainer = new HBox(spactToHeightWidth, spactToHeightWidthOutput, checkWidth);
        Label spactToHeightlength= new Label("Space to height ratio Length  ");
        Label checkLength = new Label();
        checkLength.setVisible(false);
        spactToHeightlength.setStyle("-fx-font-size: 20 px; -fx-font-weight: bold; margin: 10px");
        Label spactToHeightlengthOutput = new Label("Not calculated");
        spactToHeightlengthOutput.setStyle("-fx-font-size: 20 px; -fx-font-weight: bold; margin: 10px");
        HBox spactToHeightlengthContainer = new HBox(spactToHeightlength, spactToHeightlengthOutput, checkLength);

        Button calAndDraw = new Button("Check and Draw");
        calAndDraw.setStyle("-fx-font-size: 20 px; -fx-font-weight: bold; margin: 10px");

        Button clear = new Button("Clear drawing and values");
        clear.setStyle("-fx-font-size: 20 px; -fx-font-weight: bold; margin: 10px");
        VBox distubutedLighting = new VBox(noOfUnitinLengthContainer, noOfUnitinWidthContainer, spactToHeightWidthContainer, spactToHeightlengthContainer, calAndDraw, clear);
        distubutedLighting.setStyle("-fx-border-style: solid; -fx-border-width: 5px; -fx-padding: 10px");
        distubutedLighting.setSpacing(10);
        
        
        //creating the image object
      
       
      GridPane gLambs = new GridPane();
       gLambs.setPadding(new Insets(10, 10, 10, 10)); 
      
      //Setting the vertical and horizontal gaps between the columns 
      gLambs.setVgap(5); 
      gLambs.setHgap(5);    
      gLambs.setAlignment(Pos.CENTER);
      //gLambs.add(imageView, 0, 1);
      //gLambs.getChildren().add(imageView);
      calAndDraw.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                int l = Integer.parseInt(noOfUnitinLengthInput.getText());
                int w = Integer.parseInt(noOfUnitinWidthInput.getText());
                float heightRLength = a/(l*h);
                float heughtRWidth = b/(h*w);
                spactToHeightlengthOutput.setText(String.valueOf(heightRLength));
                spactToHeightWidthOutput.setText(String.valueOf(heughtRWidth));
                if(heightRLength>=0.8 && heightRLength<=1.2){
                    checkLength.setVisible(true);
                    checkLength.setText("Accepted");
                    checkLength.setStyle("-fx-background-color: green");
                }else{
                    checkLength.setVisible(true);
                    checkLength.setText("Not Accepted");
                    checkLength.setStyle("-fx-background-color: red");
                }
                
                if(heughtRWidth>=0.8 && heughtRWidth<=1.2){
                    checkWidth.setVisible(true);
                    checkWidth.setText("Accepted");
                    checkWidth.setStyle("-fx-background-color: green");
                }else{
                    checkWidth.setVisible(true);
                    checkWidth.setText("Not Accepted");
                    checkWidth.setStyle("-fx-background-color: red");
                }
                try {
                    for(int j=0; j<w; j++)
                        createElement(gLambs, j, l);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(LightingProgram.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
      
        clear.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                gLambs.getChildren().clear();
                spactToHeightWidthOutput.setText("Not calculated");
                spactToHeightlengthOutput.setText("Not calculated");
                noOfUnitinWidthInput.setText("");
                noOfUnitinLengthInput.setText("");
                checkLength.setVisible(false);
                checkWidth.setVisible(false);
            }
            
        });
      
        GridPane root = new GridPane();
        root.setMinSize(1600, 1600); 
       
        //Setting the padding  
        root.setPadding(new Insets(10, 10, 10, 10)); 
      
      //Setting the vertical and horizontal gaps between the columns 
        root.setVgap(5); 
        root.setHgap(5);       
      
      //Setting the Grid alignment 
        //root.setAlignment(Pos.CENTER); 
       
      //Arranging all the nodes in the grid 
        root.add(dimensions, 1, 0); 
        root.add(lampBox, 1, 1);
        //root.add(calculate, 0, 2);
        root.add(calculatedValues1, 1, 3);
        root.add(unitLampsContainer, 1, 4);
        root.add(emergencyContainer, 1, 5);
        root.add(gLambs, 0, 1);
        root.add(distubutedLighting, 0, 4);
        Scene scene = new Scene(root, 1400, 900);
        
        primaryStage.setTitle("Lighting distribution");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    File f = new File("lamb.jpg");
  
            // Get the absolute path of file f
    private void createElement(GridPane gLambs, int j, int c) throws FileNotFoundException {
       
        for(int i=0; i<c; i++){
                FileInputStream stream = new FileInputStream("images/lamb.jpg");
                Image image = new Image(stream);
                //Creating the image view
                ImageView imageView = new ImageView();
                //Setting image to the image view
                imageView.setImage(image);
                //Setting the image view parameters
                imageView.setX(1);
                imageView.setY(1);
                imageView.setFitWidth(25);
                imageView.setPreserveRatio(true);
                gLambs.add(imageView, j, i);
                System.out.println("I: " + i + "J" + c);
                    }
                }
}
