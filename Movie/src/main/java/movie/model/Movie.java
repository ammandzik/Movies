package movie.model;

import java.util.Objects;

public class Movie {

    private Long id;
    private String title;
    private Category category;
    private int releaseDate;
    private Double rating;


    public String getTitle() {
        return title;
    }

    public Category getCategory() {
        return category;
    }


    public Integer getReleaseDate() {
        return releaseDate;
    }

    public Double getRating() {
        return rating;
    }


    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category=" + category +
                ", releaseDate=" + releaseDate +
                ", rating=" + rating +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Double.compare(rating, movie.rating) == 0 && Objects.equals(id, movie.id) && Objects.equals(title, movie.title) && category == movie.category && Objects.equals(releaseDate, movie.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, category, releaseDate, rating);
    }
}
