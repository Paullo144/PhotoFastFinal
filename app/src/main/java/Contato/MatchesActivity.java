package Contato;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.photofastfinal.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MatchesActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mContatoAdapter;
    private RecyclerView.LayoutManager mContatoLayoutManager;
    private String currentUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);

        currentUserID = FirebaseAuth.getInstance().getUid();

            mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            mRecyclerView.setNestedScrollingEnabled(false);
            mRecyclerView.setHasFixedSize(true);
            mContatoLayoutManager = new LinearLayoutManager(MatchesActivity.this);
            mRecyclerView.setLayoutManager(mContatoLayoutManager);
            mContatoAdapter = new MatchesAdapter(getDataSetMatches(), MatchesActivity.this);
            mRecyclerView.setAdapter(mContatoAdapter);
            geUserContatoId();
        mContatoAdapter.notifyDataSetChanged();
        }

    private void geUserContatoId() {
        DatabaseReference contatodb = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserID).child("connections").child("matches");
        contatodb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot contato : dataSnapshot.getChildren()) {
                        FetchContatoInformation(contato.getKey());
                    }
                }
            }
            @Override
            public void onCancelled( DatabaseError error) {

            }
        });
    }

    private void FetchContatoInformation(String key) {
        DatabaseReference userdb = FirebaseDatabase.getInstance().getReference().child("Users").child(key);
        userdb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String userId = dataSnapshot.getKey();
                    String name = "";
                    String profileImagemUrl = "";
                    if (dataSnapshot.child("name").getValue() !=null){
                        name = dataSnapshot.child("name").getValue().toString();
                    }
                    if(dataSnapshot.child("profileImagemUrl").getValue()!=null){
                        profileImagemUrl = dataSnapshot.child("profileImagemUrl").getValue().toString();
                    }

                    MatchesObject object = new MatchesObject(userId, name, profileImagemUrl);
                    resultsContatos.add(object);
                    mContatoAdapter.notifyDataSetChanged();
                }
                }

            @Override
            public void onCancelled( DatabaseError error) {

            }
        });

    }

    private ArrayList<MatchesObject> resultsContatos = new ArrayList<MatchesObject>();
    private List<MatchesObject> getDataSetMatches() {
        return resultsContatos;
    }
}