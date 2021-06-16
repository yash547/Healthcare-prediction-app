//package com.example.healths;
//
//import android.os.AsyncTask;
//
//import com.example.healths.ui.gallery.GalleryFragment;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//
//public class fetchData extends AsyncTask<Void,Void,Void> {
//    String data ="";
//    String dataParsed = "";
//    String singleParsed ="";
//    @Override
//    protected Void doInBackground(Void... voids) {
//        try {
//            URL url = new URL("https://secret-escarpment-94548.herokuapp.com/api/get/diseases");
//            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//            InputStream inputStream = httpURLConnection.getInputStream();
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//
//            String line = "";
//            while(line != null){
//                line = bufferedReader.readLine();
//                data = data + line;
//            }
///**
// *
// * "_id":"60519febf691290022e6ed79",
// *          "disease_id":"5eea",
// *          "disease_name":"asthama",
// *          "symptoms":"asthama may cause diffculty breathing chest pain",
// *          "type":"asthama",
// *          "homeopathic":"adulsa",
// *          "allopathy":"ipecacuanha",
// *          "ayurvedic":"metaproterenol",
// *          "createdAt":"2021-03-17T06:21:31.856Z",
// *          "updatedAt":"2021-03-17T06:21:31.856Z",
// *          "__v":0
// *
// */
//            JSONArray JA = new JSONArray(data);
//            for(int i =0 ;i < JA.length(); i++){
//                JSONObject JO = (JSONObject) JA.get(i);
//                singleParsed =  "Disease Name:" + JO.get("disease");
////                        "Disease ID:" + JO.get("disease_id") + "\n"+
////                        "Disease Name:" + JO.get("disease_name") + "\n"+
////                        "Symptoms:" + JO.get("symptoms") + "\n"+
////                        "Type:" + JO.get("type") + "\n"+
////                        "Homeopathic:" + JO.get("homeopathic") + "\n"+
////                        "Allopathy:" + JO.get("allopathy") + "\n"+
////                        "Ayurvedic:" + JO.get("ayurvedic") + "\n"+
////                        "CreatedAt:" + JO.get("createdAt") + "\n"+
////                        "UpdatedAt:" + JO.get("updatedAt") + "\n";
//
//                dataParsed = dataParsed + singleParsed +"\n" ;
//
//
//            }
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    @Override
//    protected void onPostExecute(Void aVoid) {
//        super.onPostExecute(aVoid);
//
//      GalleryFragment.show.setText(this.dataParsed);
//
//    }
//}