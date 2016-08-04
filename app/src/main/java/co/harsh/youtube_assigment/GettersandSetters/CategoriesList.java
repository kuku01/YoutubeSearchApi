package co.harsh.youtube_assigment.GettersandSetters;

import java.util.ArrayList;

/**
 * Created by harshkaranpuria on 9/25/15.
 */
public class CategoriesList {
    private String etag;
    private ArrayList<Categories_Item> items;

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public ArrayList<Categories_Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Categories_Item> items) {
        this.items = items;
    }
}
