package co.harsh.youtube_assigment.GettersandSetters;

import java.util.ArrayList;

/**
 * Created by harshkaranpuria on 9/25/15.
 */
public class Videos {
    private String etag;


    private String prevPageToken;
    private String nextPageToken;
    private ArrayList<Videos_Item> items;

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getPrevPageToken() {
        return prevPageToken;
    }

    public void setPrevPageToken(String prevPageToken) {
        this.prevPageToken = prevPageToken;
    }

    public ArrayList<Videos_Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Videos_Item> items) {
        this.items = items;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }
}
