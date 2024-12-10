package movie.model;

enum Category {

    SCI_FI("Sci-Fi"),
    THRILLER("Thriller"),
    ADVENTURE("Adventure");

    final String description;

    public String getDescription() {

        return description;
    }

    Category(String description) {

        this.description = description;
    }
}
