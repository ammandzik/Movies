package movie.model;

public enum Category {

    SCI_FI("Sci-Fi"),
    THRILLER("Thriller"),
    ADVENTURE("Adventure");

    final String description;

    public String getDescription(){

        return description;

    }

    Category(String description) {

        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
