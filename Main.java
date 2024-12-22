package pack2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main extends Application {

  public static String[][] codeDescriptions; 
  public static String[][] outputResults;
  public static String[][] correctAnswers;
  
  private String[] lectures = {"Select Lecture", "Lec2", "Lec3", "Lec4", "Lec5", "Lec6", "Lec7", "Lec8", "Lec9", "Lec10", "Lec11", "Lec12"};
  private String[][] codes = {
    {"Code 1", "Code 2", "Code 3", "Code 4", "Code 5"},
    {"Code 1", "Code 2", "Code 3", "Code 4", "Code 5"},
    {"Code 1", "Code 2", "Code 3", "Code 4"},
    {"Code 1", "Code 2", "Code 3", "Code 4"},
    {"Code 1", "Code 2", "Code 3"},
    {"Code 1", "Code 2", "Code 3", "Code 4"},
    {"Code 1", "Code 2", "Code 3"},
    {"Code 1", "Code 2", "Code 3", "Code 4", "Code 5", "Code 6", "Code 7"},
    {"Code 1", "Code 2", "Code 3", "Code 4"},
    {"Code 1"},
    {"Code 1", "Code 2"}
  };

  private DescriptionPane descriptionPane = new DescriptionPane();
  private LectureSelectorPane lectureSelectorPane;

  @Override
  public void start(Stage primaryStage) {
    codeDescriptions = new String[lectures.length - 1][];
    outputResults = new String[lectures.length - 1][];
    correctAnswers = new String[lectures.length - 1][];

    lectureSelectorPane = new LectureSelectorPane(lectures, codes, descriptionPane);

    // VBox와 descriptionPane을 HBox로 배치
    HBox mainBox = new HBox(10);
    mainBox.getChildren().addAll(lectureSelectorPane, descriptionPane);
    mainBox.setPadding(new Insets(10, 0, 0, 0));
    HBox.setHgrow(descriptionPane, Priority.ALWAYS); // descriptionPane이 HBox의 남은 공간을 차지하도록 설정
    descriptionPane.setMinWidth(650); // descriptionPane의 최소 너비를 650으로 설정

    Scene scene = new Scene(mainBox, 1350, 480);
    primaryStage.setTitle("Study OOP With Java Code"); 
    primaryStage.setScene(scene); 
    primaryStage.show(); 

    // 초기 설명 설정
    descriptionPane.setDescription("Study Java Programming\n"
    		+ "You will be given a code for each week of lectures you learned in class.\n"
    		+ "Study by matching the code that goes into the blank.\n"
    		+"Write and match the answer to the TextArea below");
    descriptionPane.setCodeResult("The input and output for the given code are given.");
    
    loadDescriptions();
    loadAnswers();
    loadResults();
  }
  
  private void loadDescriptions() {
	    for (int i = 1; i < lectures.length; i++) {
	      int topicCount = codes[i - 1].length;
	      codeDescriptions[i - 1] = new String[topicCount];
	      for (int j = 0; j < topicCount; j++) {
	        String fileName = "codeDescriptions/" + lectures[i] + "_D_Code" + (j + 1) + ".txt";
	        try {
	          List<String> lines = Files.readAllLines(Paths.get(fileName));
	          codeDescriptions[i - 1][j] = String.join("\n", lines);
	        } catch (IOException e) {
	          e.printStackTrace();
	        }
	      }
	    }
	  }

  private void loadAnswers() {
    for (int i = 1; i < lectures.length; i++) {
      int topicCount = codes[i - 1].length;
      correctAnswers[i - 1] = new String[topicCount];
      for (int j = 0; j < topicCount; j++) {
        String fileName = "correctAnswers/" + lectures[i] + "_A_Code" + (j + 1) + ".txt";
        try {
          List<String> lines = Files.readAllLines(Paths.get(fileName));
          correctAnswers[i - 1][j] = String.join("\n", lines);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
  
  private void loadResults() {
	    for (int i = 1; i < lectures.length; i++) {
	      int topicCount = codes[i - 1].length;
	      outputResults[i - 1] = new String[topicCount];
	      for (int j = 0; j < topicCount; j++) {
	        String fileName = "result/" + lectures[i] + "_R_Code" + (j + 1) + ".txt";
	        try {
	          List<String> lines = Files.readAllLines(Paths.get(fileName));
	          outputResults[i - 1][j] = String.join("\n", lines);
	        } catch (IOException e) {
	          e.printStackTrace();
	        }
	      }
	    }
	  }


  public static void main(String[] args) {
    launch(args);
  }
}
