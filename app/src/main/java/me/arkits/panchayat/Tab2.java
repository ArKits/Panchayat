package me.arkits.panchayat;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



/**
 * Created by Archit on 5/27/2015.
 */
public class Tab2 extends Fragment {

    // Progress Dialog
    private ProgressDialog pDialog;
    ListView list;
 Context context;
    TextView username;
    TextView title;
    TextView message;




    // testing on Emulator:
    private static final String READ_COMMENTS_URL = "http://bkchdi.x10host.com/comments.php";



    // JSON IDS:
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_TITLE = "title";
    private static final String TAG_POSTS = "posts";
    private static final String TAG_POST_ID = "post_id";
    private static final String TAG_USERNAME = "username";
    private static final String TAG_MESSAGE = "message";


    // An array of all of our comments
    private JSONArray mComments = null;
    // manages all of our comments in a list.
    private ArrayList<HashMap<String, String>> mCommentList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.tab_2, container, false);



    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

        mCommentList = new ArrayList<HashMap<String, String>>();
        new JSONParse().execute();









    }



    private class JSONParse extends AsyncTask<String, String, JSONObject>{

        private ProgressDialog pDialog;


        JSONParser jParser = new JSONParser();
        JSONObject json ;

        protected void onPreExecute() {
            super.onPreExecute();
            title = (TextView)getView().findViewById(R.id.title);
            message = (TextView)getView().findViewById(R.id.message);
            username = (TextView)getView().findViewById(R.id.username);

            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Getting Data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }

        protected JSONObject doInBackground(String... args) {

            // Getting JSON from URL
            json = jParser.getJSONFromUrl(READ_COMMENTS_URL);


            return json;

        }

        protected void onPostExecute(JSONObject json) {
            pDialog.dismiss();

            try{
                mComments = json.getJSONArray(TAG_POSTS);
                for (int i = 0; i < mComments.length(); i++) {
                    JSONObject c = mComments.getJSONObject(i);

                    String title = c.getString(TAG_TITLE);
                    final String content = c.getString(TAG_MESSAGE);
                    String username = c.getString(TAG_USERNAME);

                    // creating new HashMap
                    HashMap<String, String> map = new HashMap<String, String>();

                    map.put(TAG_TITLE, title);
                    map.put(TAG_MESSAGE, content);
                    map.put(TAG_USERNAME, username);

                    // adding HashList to ArrayList
                    mCommentList.add(map);
                    list = (ListView)getView().findViewById(R.id.list);

                    ListAdapter adapter = new SimpleAdapter(getActivity(), mCommentList,
                            R.layout.single_post, new String[]{TAG_TITLE, TAG_MESSAGE,
                            TAG_USERNAME}, new int[]{R.id.title, R.id.message,
                            R.id.username});

                    list.setAdapter(adapter);

                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                           // Toast.makeText(getActivity(), "You Clicked at " + mCommentList.get(+position).get("title"), Toast.LENGTH_SHORT).show();

                            onClick(position);



                        }

                        public void onClick(int position) {

                            Toast.makeText(getActivity(), "You Clicked at " + mCommentList.get(+position).get("title"), Toast.LENGTH_SHORT).show();
                        }


                    });





            }


        } catch (JSONException e) {
                e.printStackTrace();
            }

        }


    }
}
