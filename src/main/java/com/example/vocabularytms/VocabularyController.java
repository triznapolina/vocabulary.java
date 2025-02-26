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

    ArrayList<String> storeValues;

    // Create File & Read / Write
    private final File file;
    BufferedReader reader;

    // Not everything can be by default created and sent to a constructor
    // We must keep in mind timing and the way constructors are build and objects are created
    public VocabularyController(){
        listView = new ListView<>();
        file = new File("src/main/resources/com/example/vocabularytms/vocabulary.txt");
        storeValues = new ArrayList<>();
    }

    @FXML
    public void addWord(ActionEvent actionEvent) {
        // Adds a word to the list
        if (textValue.getText().isBlank() || textValue.getText().isEmpty()){
            System.out.println("Your field is either blank or empty...");
            return;
        }

        listView.getItems().add(textValue.getText());
    }

    @FXML
    public void deleteWord(ActionEvent actionEvent) {
        // This method will delete a particular word in the list
    }

    @FXML
    public void saveVocabulary(ActionEvent actionEvent) {
        // This method will save your data in a .TXT file
        String[] words = new String[]{"Horse", "Dogs", "Cats", "Rabbits", "Camels"};
        listView.getItems().addAll(words);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // method which create a code once some object have been initialized
        // Read document and save values to ArrayList
        // Then add all ArrayList values to listview -> listview.getItems().addAll(_array);
        try{
            String line;
            reader = new BufferedReader(new FileReader(file));

            while((line = reader.readLine()) != null){
                listView.getItems().add(line);
            }
        } catch (FileNotFoundException e){
            System.out.println("File was not found!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}