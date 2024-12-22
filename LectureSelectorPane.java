package pack2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class LectureSelectorPane extends VBox {

  private ComboBox<String> cbo = new ComboBox<>();
  private ListView<String> listView = new ListView<>();

  private String[] lectures; // ComboBox에 넣을 강의 목록
  private String[][] codes; // ListView에 넣을 코드 목록 
  private DescriptionPane descriptionPane;

  public LectureSelectorPane(String[] lectures, String[][] codes, DescriptionPane descriptionPane) {
    this.lectures = lectures;
    this.codes = codes;
    this.descriptionPane = descriptionPane;

    ObservableList<String> items = FXCollections.observableArrayList(this.lectures);
    cbo.getItems().addAll(items);
    cbo.setValue(lectures[0]); // 초기값을 "Select Lecture"로 설정

    // ComboBox의 선택이 "Select Lecture"인 경우 ListView를 비움
    cbo.setOnAction(e -> {
      int selectedIndex = items.indexOf(cbo.getValue());
      if (selectedIndex == 0) 
      {
        listView.setItems(FXCollections.observableArrayList());
        descriptionPane.setDescription("Please choose a lectures");
        descriptionPane.setCodeResult("");
        descriptionPane.setCorrectCode("");
      } else {
        updateListView(selectedIndex - 1);
      }
    });

    listView.setOnMouseClicked(e -> {
      String selectedCode = listView.getSelectionModel().getSelectedItem();
      if (selectedCode != null) {
        updateDescription(items.indexOf(cbo.getValue()) - 1, listView.getSelectionModel().getSelectedIndex());
      }
    });

    HBox cboBox = new HBox(10);
    cboBox.getChildren().addAll(new Label("Select a lectures: "), cbo);
    cboBox.setPadding(new Insets(5, 0, 0, 6));

    this.getChildren().addAll(cboBox, listView);
    VBox.setVgrow(listView, Priority.ALWAYS); // listView 높이를 늘리기 위해 설정
    this.setPadding(new Insets(0, 0, 0, 0));
    this.setPrefWidth(250);
  }

  public ComboBox<String> getComboBox() {
	    return cbo;
	  }

	  public ListView<String> getListView() {
	    return listView;
	  }
	  
  private void updateListView(int lectureIndex) {
    ObservableList<String> listItems = FXCollections.observableArrayList(codes[lectureIndex]);
    listView.setItems(listItems);
  }

  private void updateDescription(int lectureIndex, int topicIndex) {
    String description = Main.codeDescriptions[lectureIndex][topicIndex];
    String correctCode = Main.correctAnswers[lectureIndex][topicIndex];
    String codeResult = Main.outputResults[lectureIndex][topicIndex];
    String modifiedDescription = description.replace("FILL_CODE_OR_CLICK_ANSWER", "_________"); // 빈 칸으로 표시

    descriptionPane.setDescription(modifiedDescription);
    descriptionPane.setCodeResult(codeResult);
    descriptionPane.setCorrectCode(correctCode);
  }
}