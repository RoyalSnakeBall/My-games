package org.example.demo13;

public class BoardGame {
    private String publisher;
    private String title;
    private float price;
    private String genre;

    public BoardGame(String publisher, String title, float price, String genre) {
        this.publisher = publisher;
        this.title = title;
        this.price = price;
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public float getPrice() {
        return price;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "BoardGame{" +
                "genre='" + genre + '\'' +
                ", publisher='" + publisher + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}
