package co.harsh.youtube_assigment.GettersandSetters;

/**
 * Created by harshkaranpuria on 9/25/15.
 */
public class Categories_Item {
    private String etag;
    private String id;
    private String kind;
    private CategoriesSnippet snippet;

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public CategoriesSnippet getSnippet() {
        return snippet;
    }

    public void setSnippet(CategoriesSnippet snippet) {
        this.snippet = snippet;
    }
}
