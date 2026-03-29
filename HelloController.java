package org.example.demo13;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class HelloController {



    @FXML
    private ComboBox<String> genre;

    @FXML
    private TextField genreField;

    @FXML
    private ListView<String> listView;

    @FXML
    private ComboBox<String> price;

    @FXML
    private TextField priceField;

    @FXML
    private ComboBox<String> publisher;

    @FXML
    private TextField publisherField;

    @FXML
    private TextField searchField;

    @FXML
    private ComboBox<String> title;

    @FXML
    private TextField titleField;

    @FXML
    private TextField startPrice;

    @FXML
    private TextField endPrice;


    private BoardGameStorage boardGameStorage = new BoardGameStorage();

    @FXML
    public void initialize() {
        boardGameStorage.addNewGame(new BoardGame("Kosmos", "Catan", 35, "Strategy"));
        boardGameStorage.addNewGame(new BoardGame("Hasbro", "Monopoly", 45, "Family"));

        publisher.setOnAction(actionEvent -> sortBoxPublisher());
        genre.setOnAction(actionEvent -> sortBoxGenre());
        price.setOnAction(actionEvent -> sortBoxAction());
        title.setOnAction(actionEvent -> sortBoxTitle());

        updateList(boardGameStorage.getAllBoardGames());
        updateComboBox();
    }
    @FXML
    public void updateComboBox(){
        ObservableList<String> publishersList = FXCollections.observableArrayList(boardGameStorage.getAllPublishers());
        publisher.setItems(publishersList);

        ObservableList<String> titlesList = FXCollections.observableArrayList(boardGameStorage.getAllTitles());
        title.setItems(titlesList);

        ObservableList<String> genresList = FXCollections.observableArrayList(boardGameStorage.getAllGenres());
        genre.setItems(genresList);

        price.setItems(FXCollections.observableArrayList(List.of("High to Low", "Low to High")));


    }

    @FXML
    public void handleAdd() {
        String publisher = publisherField.getText();
        String title = titleField.getText();
        float price = Float.parseFloat(priceField.getText());
        String genre = genreField.getText();

        BoardGame game = new BoardGame(publisher, title, price, genre);
        boardGameStorage.addNewGame(game);

        updateList(boardGameStorage.getAllBoardGames());
        updateComboBox();
    }

    @FXML
    public void resetFilters(){
        updateList(boardGameStorage.getAllBoardGames());
    }
    @FXML
    public void minToMaxi(){
        int startPrice1 = Integer.parseInt(startPrice.getText());
        int endPrice1 = Integer.parseInt(endPrice.getText());

        updateList(boardGameStorage.minToMax(startPrice1, endPrice1));
    }

    @FXML
    public void handleSearch() {
        String search = searchField.getText();
        List<BoardGame> result = boardGameStorage.filterGamesByTitleAndPublisher(search);
        updateList(result);
    }
    @FXML
    private void updateList(List<BoardGame> games) {
        listView.getItems().clear();
        for (BoardGame game : games) {
            listView.getItems().add(game.toString());
        }
    }
    @FXML
    public void groupGamesByPublisher1(){
        var grouped = boardGameStorage.groupGamesByPublisher();

        listView.getItems().clear();
        grouped.forEach((publisher, games)-> {
            listView.getItems().add("==" + publisher + " ==");
            for(BoardGame game : games) {
                listView.getItems().add(game.toString());
            }
        });
    }
    @FXML
    public void groupGamesByGenre(){
        var grouped = boardGameStorage.groupGamesByGenre();

        listView.getItems().clear();
        grouped.forEach((genre, games) ->{
            listView.getItems().add("==" + genre + " ==");
            for(BoardGame game : games){
                listView.getItems().add(game.toString());
            }
        });
    }
    @FXML
    public void getAllGenres(){
        var genres = boardGameStorage.getAllGenres();

        listView.getItems().clear();
        listView.getItems().addAll(genres);
    }

    @FXML
    public void getAllPublishers(){
        var publishers = boardGameStorage.getAllPublishers();

        listView.getItems().clear();
        listView.getItems().addAll(publishers);
    }
    @FXML
    public void sortBoxAction(){
        String option = price.getSelectionModel().getSelectedItem();

        if(option.equals("High to Low")){
            updateList(boardGameStorage.addSortedGameBoardLowPrice());
        }
        else if(option.equals("Low to High")){
            updateList(boardGameStorage.addSortedGameByHighPrice());
        }
    }
    @FXML
    public void sortBoxGenre(){
        String option = genre.getSelectionModel().getSelectedItem();

        updateList(boardGameStorage.filterGamesByGenre(option));
    }
    @FXML
    public void sortBoxPublisher(){
        String option = publisher.getSelectionModel().getSelectedItem();

        updateList(boardGameStorage.filterGamesByPublisher(option));
    }
    @FXML
    public void sortBoxTitle(){
        String option = title.getSelectionModel().getSelectedItem();

        updateList(boardGameStorage.filterGamesByTitle(option));
    }
}
