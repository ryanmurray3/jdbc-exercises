package models;



import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter

public class Album {
    private long id;
    private String artist;
    private String name;
    private int releaseDate;
    private double sales;
    private String genre;

    public Album(String artist, String title) {
    }
}