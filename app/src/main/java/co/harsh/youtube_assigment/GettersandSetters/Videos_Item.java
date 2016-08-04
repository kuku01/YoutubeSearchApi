package co.harsh.youtube_assigment.GettersandSetters;

/**
 * Created by harshkaranpuria on 9/25/15.
 */
public class Videos_Item {

    private Videos_Id id;
    private String kind;
    private Videos_Snippet snippet;
    private String title;

    public Videos_Id getId() {
        return id;
    }

    public void setId(Videos_Id id) {
        this.id = id;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Videos_Snippet getSnippet() {
        return snippet;
    }

    public void setSnippet(Videos_Snippet snippet) {
        this.snippet = snippet;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
