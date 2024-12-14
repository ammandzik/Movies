package movie.model;

enum Category {

    SCI_FI("Sci-Fi"),
    THRILLER("Thriller"),
    ADVENTURE("Adventure");

    final String description;

    Category(String description) {

        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
