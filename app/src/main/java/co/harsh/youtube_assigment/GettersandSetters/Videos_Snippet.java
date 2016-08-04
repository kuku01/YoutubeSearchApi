package co.harsh.youtube_assigment.GettersandSetters;

/**
 * Created by harshkaranpuria on 9/25/15.
 */
public class Videos_Snippet {
    private String title;
    private Videos_Thumbnail thumbnails;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Videos_Thumbnail getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(Videos_Thumbnail thumbnails) {
        this.thumbnails = thumbnails;
    }
}
