package com.example.healths.ui.gallery;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.healths.MainActivity;
import com.example.healths.NAVS;
import com.example.healths.R;
import com.example.healths.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
//import com.example.healths.fetchData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.BreakIterator;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;


public class GalleryFragment  extends Fragment {


    private GalleryViewModel galleryViewModel;
EditText enterSym;
Button btn;
TextView show;
FirebaseUser user;
String url ;

FirebaseAuth fAuth;
DatabaseReference reference;
class DISDATA extends AsyncTask<String ,Void,String>
{

    protected String doInBackground(String... urls)
    {
        StringBuilder result=new StringBuilder();
        try
        {
            URL url=new URL(urls[0]);
            HttpsURLConnection urlConnection=(HttpsURLConnection) url.openConnection();
            urlConnection.connect();
            InputStream inputStream=urlConnection.getInputStream();
            BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));

            String line="";
            while ((line=reader.readLine())!=null)
            {
                result.append(line).append("\n");

            }
            return result.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;

        }
    }

    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        try {
//            JSONObject jsonObject=new JSONObject(result);
//            String shows=jsonObject.getString("symptoms");
//        //    disshowdata=disshowdata.replace("");
//           shows=shows.replace("so=","symptoms") ;
//            show.setText(shows);
       //     JSONObject json = new JSONObject(result;
            JSONArray jsonarray = new JSONArray(result);
            for(int i=0; i < jsonarray.length(); i++) {
                JSONObject jsonobject = jsonarray.getJSONObject(i);
                String symptoms      = jsonobject.getString("type");
//                String company  = jsonobject.getString("company");
//                String category = jsonobject.getString("category");
                show.setText(symptoms);
            }

            addpredictedDiseasetofirebase();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }

        });
        //editTextTextPersonName
       // textView7
        enterSym=root.findViewById(R.id.editTextTextPersonName);
        btn=root.findViewById(R.id.button1);
        show=root.findViewById(R.id.textView7);

    final     String[] temp={""};
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user= FirebaseAuth.getInstance().getCurrentUser();

                String dom=enterSym.getText().toString();

            try{
                if(dom!=null)
                {
                    url="https://morning-springs-78536.herokuapp.com/api"+"/"+dom;
                }

                DISDATA task=new DISDATA();
                temp[0]= task.execute(url).get();
            }
            catch (ExecutionException e)
            {
                e.printStackTrace();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
if(temp[0]==null)
{
    show.setText("no Symptoms");
}
            }
        });

        return root;
    }

 public    void addpredictedDiseasetofirebase()
    {

        //LocalDateTime now = LocalDateTime.now();
     //   System.out.println(dtf.format(now));

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        Date date = new Date();

        Random random=new Random();
        int x = random.nextInt(50*345);
        String SYMPTOMS=enterSym.getText().toString().trim();
        String predDIs=show.getText().toString().trim();

        String dtfl=formatter.format(date);


        String   userIDs=user.getUid();

String childnode=Integer.toString(x);

        reference= FirebaseDatabase.getInstance().getReference("predictedDisease");
Dataholder dh=new Dataholder(SYMPTOMS,predDIs,dtfl,userIDs);
        //  reference= FirebaseDatabase.getInstance().getReference("predictedDisease");

//        FirebaseDatabase.getInstance().getReference("predictedDisease")
//                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                .setValue(dh);
        reference .child(childnode).setValue(dh);
/**
 *
 *  FirebaseDatabase.getInstance().getReference("Users")
 *                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
 *                                   .setValue(us)
 */

    }


}