package movie.model;

public enum Category {

    SCI_FI("Sci-Fi"),
    THRILLER("Thriller"),
    ADVENTURE("Adventure");

    final String description;

    Category(String description) {

        this.description = description;
    }

    public String getDescription() {

        return description;

    }

    @Override
    public String toString() {
        return description;
    }
}
