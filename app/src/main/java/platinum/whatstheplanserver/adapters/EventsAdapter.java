package platinum.whatstheplanserver.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import platinum.whatstheplanserver.R;
import platinum.whatstheplanserver.models.Event;
import platinum.whatstheplanserver.models.Venue;

public class EventsAdapter extends FirestoreRecyclerAdapter<Event, EventsAdapter.EventsHolder> {

    private static final String TAG = "EventsAdapterTag";

    private Context mContext;

    public EventsAdapter(@NonNull FirestoreRecyclerOptions<Event> options, Context context) {
        super(options);
        mContext = context;
    }


    @Override
    protected void onBindViewHolder(@NonNull EventsHolder holder, int position, @NonNull Event event) {

        holder.event_name_TV.setText(event.getEvent_name());
        Log.d(TAG, "onBindViewHolder: eventname = " +  event.getEvent_name());
        holder.event_date_TV.setText(event.getEvent_date());
        holder.event_time_TV.setText(event.getEvent_time()          );
        holder.venue_name_TV.setText(event.getVenue_name());
        holder.venue_address_TV.setText("Address : " + event.getVenue_address());
        Glide.with(mContext)
                .load(Uri.parse(event.getEvent_image()))
                .apply(new RequestOptions().fitCenter())
                .into(holder.event_image_IV);
        Glide.with(mContext)
                .load(Uri.parse(event.getEvent_image()))
                .apply(new RequestOptions().fitCenter())
                .into(holder.event_layout_bg_IV);

    }

    @NonNull
    @Override
    public EventsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.layout_event, viewGroup, false);
        EventsHolder viewHolder = new EventsHolder(itemView);
        return viewHolder;
    }

    class EventsHolder extends RecyclerView.ViewHolder {

        private TextView event_name_TV;
        private TextView event_date_TV;
        private TextView event_time_TV;
        private TextView venue_name_TV;
        private TextView venue_address_TV;
        private ImageView event_image_IV;
        private ImageView event_layout_bg_IV;

        public EventsHolder(@NonNull View itemView) {
            super(itemView);
            event_name_TV = itemView.findViewById(R.id.event_name_TV);
            event_date_TV = itemView.findViewById(R.id.event_date_TV);
            event_time_TV = itemView.findViewById(R.id.event_time_TV);
            venue_name_TV = itemView.findViewById(R.id.venue_name_TV);
            venue_address_TV = itemView.findViewById(R.id.venue_address_TV);
            event_image_IV   = itemView.findViewById(R.id.event_image_IV);
            event_layout_bg_IV = itemView.findViewById(R.id.event_layout_bg_IV);

        }
    }

}