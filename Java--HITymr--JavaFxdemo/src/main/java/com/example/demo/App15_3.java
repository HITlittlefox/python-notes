import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

public class App15_3 extends Application{  
  Button bt=new Button("设置字体颜色");
  TextArea ta=new TextArea("字体颜色");
  @Override
  public void start(Stage primaryStage){
      BorderPane bPane=new BorderPane();
      bPane.setCenter(ta);
      bPane.setBottom(bt);
      bPane.setAlignment(bt,Pos.CENTER);
      bt.setOnAction(e->ta.setStyle("-fx-text-fill:purple"));
      Scene scene=new Scene(bPane,180,100);
      primaryStage.setTitle("操作事件");
      primaryStage.setScene(scene);
      primaryStage.show();
  }
}