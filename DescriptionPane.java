package pack2;

import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class DescriptionPane extends VBox {
  
  private TextArea description = new TextArea(); // 빈칸코드 나오는 TextArea
  private TextArea codeResult = new TextArea();  // 해당 코드의 Input/Output 나오는 TextArea
  private TextArea userInput = new TextArea();   // 코드 빈칸에 정답 입력하는 TextArea
  private Button btCheck = new Button("Check"); // 정답 Check 버튼
  private Button btReset = new Button("Reset"); // 정답란 비우는 버튼
  private Button btAnswer = new Button("Answer"); // 정답을 알려주는 버튼 
  private String correctCode; // 정답 코드

  public DescriptionPane() {
    description.setWrapText(true); // 자동 줄바꿈
    description.setEditable(false); // 입력 불가능

    codeResult.setWrapText(true);  // 자동 줄바꿈
    codeResult.setEditable(false); // 입력 불가능
    codeResult.setPrefWidth(200); // 가로 길이 설정

    userInput.setFont(new Font("Monospaced", 14)); //Font 설정
    userInput.setPrefHeight(100); // 높이 설정

    btCheck.setOnAction(e -> checkAnswer());
    btReset.setOnAction(e -> resetInput());
    btAnswer.setOnAction(e -> showAnswer());

    HBox buttonBox = new HBox(10); // 버튼 가로로 배치
    buttonBox.getChildren().addAll(btCheck, btReset, btAnswer);

    HBox descriptionAndResultBox = new HBox(10); // 빈칸코드와 코드결과 가로로 배치
    descriptionAndResultBox.getChildren().addAll(description, codeResult);
    HBox.setHgrow(description, Priority.ALWAYS); // description 높이를 늘리기 위해 설정
    HBox.setHgrow(codeResult, Priority.ALWAYS); // codeResult 높이를 늘리기 위해 설정

    this.setSpacing(9);
    this.setPadding(new Insets(30,10,10,0));
    this.getChildren().addAll(descriptionAndResultBox, userInput, buttonBox);
    VBox.setVgrow(descriptionAndResultBox, Priority.ALWAYS); // description과 codeResult의 높이를 늘리기 위해 설정
  }

  public void setDescription(String text) {
    description.setText(text);
  }

  public void setCodeResult(String text) {
    codeResult.setText(text);
  }

  public void setCorrectCode(String correctCode) {
    this.correctCode = correctCode;
  }
  
  private void checkAnswer() {
	    String userCode = userInput.getText();
	    if (userCode.equals(correctCode)) {
	      userInput.setStyle("-fx-border-color: green; -fx-border-width: 2px;");
	    } else {
	      userInput.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
	    }
	  }
  
  private void resetInput() {
    userInput.clear();
    userInput.setEditable(true); 
    userInput.setStyle(""); // 테두리 색 초기화
    btCheck.setDisable(false); // Check 버튼 활성화
  }

  private void showAnswer() {
    userInput.setText(correctCode);
    userInput.setEditable(false); 
    userInput.setStyle(""); // 테두리 색 초기화
    btCheck.setDisable(true); // Check 버튼 비활성화
  }
}