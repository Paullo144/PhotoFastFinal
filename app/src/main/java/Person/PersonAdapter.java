package Person;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.photofastfinal.R;

import java.util.List;


public class PersonAdapter extends RecyclerView.Adapter<PersonViewHolders>{
    private List<PersonObject> matchesList;
    private Context context;


    public PersonAdapter(List<PersonObject> matchesList, Context context){
        this.matchesList = matchesList;
        this.context = context;
    }

    @Override
    public PersonViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_matches, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutView.setLayoutParams(lp);
        PersonViewHolders rcv = new PersonViewHolders(layoutView);

        return rcv;
    }

    @Override
    public void onBindViewHolder(PersonViewHolders holder, int position) {
        holder.mMatchId.setText(matchesList.get(position).getUserId());
        holder.mMatchName.setText(matchesList.get(position).getName());
        if(!matchesList.get(position).getprofileImagemUrl().equals("default")){
            Glide.with(context).load(matchesList.get(position).getprofileImagemUrl()).into(holder.mMatchImage);
        }
    }
    @Override
    public int getItemCount() {
        return this.matchesList.size();
    }
}