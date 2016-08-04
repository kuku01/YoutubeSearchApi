package co.harsh.youtube_assigment.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import co.harsh.youtube_assigment.GettersandSetters.CategoriesList;
import co.harsh.youtube_assigment.R;
import co.harsh.youtube_assigment.Utils.NetworkUtils;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by harshkaranpuria on 9/24/15.
 */
public class Categories extends AppCompatActivity {
    private ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        final RecyclerView recList = (RecyclerView) findViewById(R.id.rvCategories);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        NetworkUtils mNetworkUtils = new NetworkUtils(this);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Logging in, Please wait.");
        mProgressDialog.setCancelable(false);
        if(mNetworkUtils.isnetconnected()) {
            mProgressDialog.show();
            mNetworkUtils.getapi().getCategories("AIzaSyBbqzMwhUA53I4RGDOT0L5rjah6cVdPuIk", "snippet", "IN", new Callback<CategoriesList>() {
                @Override
                public void success(CategoriesList categoriesList, Response response) {
                    mProgressDialog.dismiss();
                    ArrayList<CategoriesList> categoryList = new ArrayList<>();

                    for (int i = 0; i < categoriesList.getItems().size(); i++) {
                        categoryList.add(categoriesList);
                    }
                    CategoriesAdapter adapter = new CategoriesAdapter(categoryList);
                    adapter.notifyDataSetChanged();
                    recList.setAdapter(adapter);

                }

                @Override
                public void failure(RetrofitError error) {
                    mProgressDialog.dismiss();
                    Log.d("Error:", error.toString());
                }
            });
        }else {
            Snackbar.make(recList,"No Network Detected",Snackbar.LENGTH_SHORT).show();
        }
    }

    public static class CategoriesViewHolder extends RecyclerView.ViewHolder {
        protected TextView vName;
        public CategoriesViewHolder(View v) {
            super(v);
            vName =  (TextView) v.findViewById(R.id.tvCategories);

        }
    }
    public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesViewHolder> {

        private List<CategoriesList> Categories;

        public CategoriesAdapter(List<CategoriesList> categories) {
            this.Categories = categories;
        }

        @Override
        public int getItemCount() {
            return Categories.size();
        }

        @Override
        public void onBindViewHolder(final CategoriesViewHolder contactViewHolder, final int i) {
            String category = Categories.get(i).getItems().get(i).getSnippet().getTitle();
            contactViewHolder.vName.setText(category);
            contactViewHolder.vName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent viewVideos = new Intent(getApplicationContext(),VideoCategorized.class);
                    viewVideos.putExtra("categoryId",Categories.get(i).getItems().get(i).getId());
                    startActivity(viewVideos);
                 }
            });
        }

        @Override
        public CategoriesViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View itemView = LayoutInflater.
                    from(viewGroup.getContext()).
                    inflate(R.layout.list_item_categories, viewGroup, false);

            return new CategoriesViewHolder(itemView);
        }
    }
}