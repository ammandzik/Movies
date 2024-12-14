package movie.model;

import java.util.Objects;

public class Movie {

    private Long id;
    private String title;
    private Category category;
    private int releaseDate;
    private double rating;

    Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    void setCategory(Category category) {
        this.category = category;
    }

    public Integer getReleaseDate() {
        return releaseDate;
    }

    void setReleaseDate(Integer releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getRating() {
        return rating;
    }

    void setRating(double rating) {
        this.rating = rating;
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
