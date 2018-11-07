package it.visionmobya.models;

public class ArticleCategory {

    private String id;
    private String name;

    public ArticleCategory() {

    }

    public ArticleCategory(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String toCsvRecord() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(id).append(";")
                .append(name);
        return stringBuilder.toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
