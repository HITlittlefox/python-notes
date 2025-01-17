package com.example.demo;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import javafx.scene.paint.ImagePattern;

import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ImagePatternExample extends Application {
    @Override
    public void start(Stage stage) {
        //Drawing a Circle
        Circle circle = new Circle();

        //Setting the properties of the circle
        circle.setCenterX(300.0f);
        circle.setCenterY(180.0f);
        circle.setRadius(90.0f);

        //Drawing a text
        Text text = new Text("This is a colored circle");

        //Setting the font of the text
        text.setFont(Font.font("Edwardian Script ITC", 50));

        //Setting the position of the text
        text.setX(155);
        text.setY(50);

        //Setting the image pattern
        String link = "https://pic.imgdb.cn/item/615f113b2ab3f51d918c0b37.jpg";

        Image image = new Image(link);
        ImagePattern radialGradient = new ImagePattern(image, 20, 20, 40, 40, false);

        //Setting the linear gradient to the circle and text
        circle.setFill(radialGradient);
        text.setFill(radialGradient);

        //Creating a Group object
        Group root = new Group(circle, text);

        //Creating a scene object
        Scene scene = new Scene(root, 600, 300);

        //Setting title to the Stage
        stage.setTitle("Image pattern Example");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }

    public static void main(String args[]) {
        launch(args);
    }
}