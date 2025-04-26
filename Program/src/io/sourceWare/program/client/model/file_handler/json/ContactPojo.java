package io.sourceWare.program.client.model.file_handler.json;

public class ContactPojo {
    public String id;
    public String alias;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }

    @Override
    public String toString() {
        return "ContactPojo{" +
                "id='" + id + '\'' +
                ", alias='" + alias + '\'' +
                '}';
    }
}
