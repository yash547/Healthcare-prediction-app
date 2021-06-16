package com.example.healths.ui.Medicine;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.healths.R;
import com.example.healths.ui.gallery.GalleryFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

public class MedicineFragment extends Fragment {

    private MedicineViewModel mViewModel;
    EditText EnterDis;
    TextView homo,aiu,allo;
    String url;
    Button btn1;

    class DISDATAS extends AsyncTask<String ,Void,String>
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
                    String symptoms      = jsonobject.getString("homeopathic");
                    String symptoms1      = jsonobject.getString("allopathy");
                    String symptoms2      = jsonobject.getString("ayurvedic");

//                String company  = jsonobject.getString("company");
//                String category = jsonobject.getString("category");
                    homo.setText(symptoms);
                    aiu.setText(symptoms1);
                    allo.setText(symptoms2);

                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mViewModel =
                new ViewModelProvider(this).get(MedicineViewModel.class);
        View root = inflater.inflate(R.layout.fragment_medicine, container, false);
        final TextView textView = root.findViewById(R.id.text_Medicine);
        mViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        EnterDis=root.findViewById(R.id.editTextTextPersonName7);
        homo=root.findViewById(R.id.textView10);
        aiu=root.findViewById(R.id.textView11);
        allo=root.findViewById(R.id.textView12);
       btn1=root.findViewById(R.id.button);

        final     String[] temp={""};
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dom= EnterDis.getText().toString();

                try{
                    if(dom!=null)
                    {
                        url="https://morning-springs-78536.herokuapp.com/api"+"/"+dom;
                    }

                    DISDATAS task=new  DISDATAS();
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

            }
        });

        return root;
    }
}