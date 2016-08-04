package co.harsh.youtube_assigment.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;

import co.harsh.youtube_assigment.R;

/**
 * Created by harshkaranpuria on 9/24/15.
 */

public class MainActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener {

    private static final int RC_SIGN_IN = 0;
    private GoogleApiClient mGoogleApiClient;
    private boolean mIsResolving = false;
    private boolean mShouldResolve = false;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SignInButton login = (SignInButton) findViewById(R.id.sign_in_button);
        login.setOnClickListener(this);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Logging in, Please wait.");
        mProgressDialog.setCancelable(false);
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(new Scope(Scopes.PROFILE))
                .build();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
        if (requestCode == RC_SIGN_IN) {
            if (resultCode != RESULT_OK) {
                mShouldResolve = false;
            }
            mIsResolving = false;
            mGoogleApiClient.connect();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }


    @Override
    public void onConnected(Bundle bundle) {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
        mShouldResolve = false;
        Intent getCategories = new Intent(this, Categories.class);
        startActivity(getCategories);
        finish();
        //on success
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.sign_in_button) {
            onSignInClicked();
        }

    }

    private void onSignInClicked() {
        mShouldResolve = true;
        mGoogleApiClient.connect();
        mProgressDialog.show();

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
        if (!mIsResolving && mShouldResolve) {
            if (connectionResult.hasResolution()) {
                try {
                    connectionResult.startResolutionForResult(this, RC_SIGN_IN);
                    mIsResolving = true;
                } catch (IntentSender.SendIntentException e) {
                    Log.e("Error:", "Could not resolve ConnectionResult.", e);
                    mIsResolving = false;
                    mGoogleApiClient.connect();
                    Toast.makeText(MainActivity.this, "Error in Connection", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "Error in Connection", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
