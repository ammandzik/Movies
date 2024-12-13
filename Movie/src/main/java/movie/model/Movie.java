package movie.model;

import java.time.LocalDate;
import java.util.Objects;

class Movie {

    private Long id;
    private String title;
    private Category category;
    private Integer releaseDate;
    private double rating;

    Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
    }

    String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    Category getCategory() {
        return category;
    }

    void setCategory(Category category) {
        this.category = category;
    }

    Integer getReleaseDate() {
        return releaseDate;
    }

    void setReleaseDate(Integer releaseDate) {
        this.releaseDate = releaseDate;
    }

    double getRating() {
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
