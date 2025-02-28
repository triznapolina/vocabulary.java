// 1. Разобраться с кодом;
// 2. Посмотреть как идет связь между бэком и графикой (вопрос контроллера)
// 3. Привести все форму в нормальное состояние и все правильно расположить (можно просто через SceneBuilder)
// 4. Добавить кнопку (четвертую) "Закрыть", которое будет ЗАКРЫВТЬ окно, т.е. прекращать программу;
// 5. Все данные хранить в коллекции, сами выберите какую коллекцию хотите, Map
// 6. Если в документе, где хранятся значения есть данные, все надо сохранить в коллекции и вывести мне на экран
// 7. Если ничего нет, то просто пусто
// 8. Нажав на кнопку Добавить я должен иметь возможность добавлять в конец
// 9. Все добавленное также должно храниться уже в обновленном списке
// 10. Нажав на кнопку Save весь новый список должен сохраниться в документе
// 11. Если я нажму на кнопку Удалить, из списка должен быть удален элемент, также и коллекции и соответственно
// его не должно быть в документ
// 12. Каждый раз, когда я буду закрывать окно через кнопку Закрыть, он также должен сохранить все изменения в документе

package com.example.vocabularytms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VocabularyController implements Initializable {
    @FXML
    public ListView<String> listView;
    @FXML
    public Button delete;
    @FXML
    public Button save;
    @FXML
    private TextField textValue;
    @FXML
    private Button add;

    @FXML
    private Button close;

    ArrayList<String> storeValues;


    private final File file;
    BufferedReader reader;

    public VocabularyController() throws IOException {
        listView = new ListView<>();
        file = new File("src/main/resources/com/example/vocabularytms/vocabulary.txt");
        storeValues = new ArrayList<>();
    }

    @FXML
    public void closeApp(ActionEvent actionEvent){
        updateDocument();
        System.out.println("Closing....");
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    private void updateDocument(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
            for (String word : storeValues) {
                writer.write(word);
                writer.newLine();

            }
            System.out.println("Collection: " + storeValues);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void addWord(ActionEvent actionEvent) {

        if (textValue.getText().isBlank() || textValue.getText().isEmpty()){
            System.out.println("Your field is either blank or empty...");
            return;
        }

        listView.getItems().add(textValue.getText().trim());
        storeValues.add(textValue.getText().trim());

        System.out.println("Adding word '" + textValue.getText().trim() + "'");

        System.out.println("New collection: " + storeValues);

        textValue.clear();
    }

    @FXML
    public void deleteWord(ActionEvent actionEvent) {
        int selectedElement = listView.getSelectionModel().getSelectedIndex();

        if (selectedElement == -1){
            System.out.println("Please choose word in the list");
            return;
        }

        String wordElement = listView.getItems().get(selectedElement);

        listView.getItems().remove(selectedElement);
        storeValues.remove(wordElement);

        System.out.println("Deleting word '" + wordElement + "'");
        updateDocument();

    }

    @FXML
    public void saveVocabulary(ActionEvent actionEvent) {
        try (FileWriter writer = new FileWriter(file, false)) {
            for (String arrayListElement : storeValues) {
                writer.write(arrayListElement + "\n");
            }
            System.out.println("File saved successfully...");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            String line;
            reader = new BufferedReader(new FileReader(file));

            while((line = reader.readLine()) != null){
                storeValues.add(line);
                listView.getItems().add(line);

            }
            if (storeValues.isEmpty()) {
                System.out.println("Empty collection");

            } else {
                System.out.println("Initial collection: " + storeValues);
            }


        } catch (FileNotFoundException e){
            System.out.println("File was not found!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}