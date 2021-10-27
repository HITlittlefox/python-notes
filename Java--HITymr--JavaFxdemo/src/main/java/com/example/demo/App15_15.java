import java.io.*;
import java.util.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.event.*;

public class App15_15 extends Application{  
  private Button bOpen,bSave;
  private TextArea ta=new TextArea("");
  private BorderPane rootBP=new BorderPane();
  private HBox hB=new HBox();
  @Override
  public void start(Stage stage){
      bOpen=new Button("选取");
      bSave=new Button("存盘");
      hB.getChildren().addAll(bOpen,bSave);
      hB.setAlignment(Pos.CENTER);
      rootBP.setBottom(hB);
      rootBP.setCenter(ta);
      bOpen.setOnAction(new EventHandler<ActionEvent>(){
         @Override
          public void handle(ActionEvent e){
               FileChooser fC=new FileChooser();
               fC.setTitle("文件选择对话框");
               fC.setInitialDirectory(new File("."));
               FileChooser.ExtensionFilter filter=new FileChooser.ExtensionFilter("所有.java文件","*.java");
               fC.getExtensionFilters().add(filter);
               File file=fC.showOpenDialog(stage);
               if(file!=null){
                   try{
                       Scanner scan=new Scanner(file);
                       String info="";
                       while(scan.hasNext()){
                           String str=scan.nextLine();
                           info+=str+"\r\n";
                       }
                       ta.setText(info);
                   }catch(FileNotFoundException ioe){}
               }else  ta.setText("没有选择文件");
          }
      });

      bSave.setOnAction(new EventHandler<ActionEvent>(){
          @Override
          public void handle(ActionEvent e){
               FileChooser fC=new FileChooser();
               fC.setTitle("文件保存对话框");
               fC.setInitialDirectory(new File("."));
               FileChooser.ExtensionFilter filter=new FileChooser.ExtensionFilter("所有.java文件","*.java");
               fC.getExtensionFilters().add(filter);
               File file=fC.showSaveDialog(stage);
               if(file!=null){
                   try{
                       FileOutputStream f=new FileOutputStream(file);
                       BufferedOutputStream out=new BufferedOutputStream(f);
                       byte[] b=(ta.getText()).getBytes();
                       out.write(b,0,b.length);
                       out.close();
                   }catch(IOException ioe){}
               }
          }
      });

      Scene scene=new Scene(rootBP);
      stage.setTitle("文件选择对话框应用程序");
      stage.setScene(scene);
      stage.show();
  }
}