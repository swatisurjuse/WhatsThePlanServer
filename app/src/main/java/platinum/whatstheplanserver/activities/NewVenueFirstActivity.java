package platinum.whatstheplanserver.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import platinum.whatstheplanserver.R;
import platinum.whatstheplanserver.activities.authentications.SignInActivity;
import platinum.whatstheplanserver.models.Venue;

public class NewVenueFirstActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mVenueNameET;
    private EditText mVenueTypeET;
    private EditText mVenueAddressET;
    private Button mNextBTN;
    private FirebaseUser mCurrentUser;
    private Venue mVenue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_venue_1);

        getCurrentUser ();
        initViewsAndVariables ();
        performActions ();
    }

    private void performActions() {
        mVenueTypeEtActions ();
        mNextBTNActions ();
    }

    private void mVenueTypeEtActions() {

    }

    private void mNextBTNActions () {
        mNextBTN.setOnClickListener(this);
    }



    private void initViewsAndVariables() {
        mVenue = getIntent().getParcelableExtra("venue");
        mVenueNameET = findViewById(R.id.venue_name_ET);
        mVenueNameET.setText(mVenue.getVenue_name());
        mVenueTypeET = findViewById(R.id.venue_type_ET);
        mVenueAddressET = findViewById(R.id.venue_address_ET);
        mVenueAddressET.setText(mVenue.getVenue_address());
        mNextBTN = findViewById(R.id.next_BTN);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.next_BTN :
                String venueType = mVenueTypeET.getText().toString();
                mVenue.setVenue_name(mVenueNameET.getText().toString());
                mVenue.setVenue_address(mVenueAddressET.getText().toString());
                mVenue.setVenue_type(venueType);
                finish();
                navigateToNewActivityCarryingData(NewVenueSecondActivity.class, "venue", mVenue);
        }
    }

    private void getCurrentUser() {
        mCurrentUser = com.google.firebase.auth.FirebaseAuth.getInstance().getCurrentUser();
        if (mCurrentUser == null) {
            navigateToNewActivity (SignInActivity.class);
        }
    }

    private void navigateToNewActivity(Class classname) {
        Intent intent = new Intent(NewVenueFirstActivity.this, classname);
        startActivity(intent);
    }

    private void navigateToNewActivityCarryingData(Class classname, String key, Parcelable venue) {
        Intent intent = new Intent(NewVenueFirstActivity.this, classname);
        intent.putExtra(key, venue);
        startActivity(intent);
    }
}
