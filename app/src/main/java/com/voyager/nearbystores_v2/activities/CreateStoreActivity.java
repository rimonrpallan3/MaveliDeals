package com.voyager.nearbystores_v2.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.voyager.nearbystores_v2.GPS.GPStracker;
import com.voyager.nearbystores_v2.R;
import com.voyager.nearbystores_v2.appconfig.AppConfig;
import com.voyager.nearbystores_v2.appconfig.Constances;
import com.voyager.nearbystores_v2.classes.Category;
import com.voyager.nearbystores_v2.classes.User;
import com.voyager.nearbystores_v2.controllers.categories.CategoryController;
import com.voyager.nearbystores_v2.controllers.sessions.SessionsController;
import com.voyager.nearbystores_v2.load_manager.ViewManager;
import com.voyager.nearbystores_v2.network.ServiceHandler;
import com.voyager.nearbystores_v2.network.VolleySingleton;
import com.voyager.nearbystores_v2.network.api_request.SimpleRequest;
import com.voyager.nearbystores_v2.parser.api_parser.ImagesParser;
import com.voyager.nearbystores_v2.parser.api_parser.StoreParser;
import com.voyager.nearbystores_v2.parser.api_parser.UserParser;
import com.voyager.nearbystores_v2.parser.tags.Tags;
import com.voyager.nearbystores_v2.utils.Utils;
import com.voyager.nearbystores_v2.views.CustomDialog;
import com.mingle.entity.MenuEntity;
import com.mingle.sweetpick.BlurEffect;
import com.mingle.sweetpick.RecyclerViewDelegate;
import com.mingle.sweetpick.SweetSheet;
import com.soundcloud.android.crop.Crop;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.voyager.nearbystores_v2.appconfig.AppConfig.APP_DEBUG;

public class CreateStoreActivity extends AppCompatActivity implements View.OnClickListener, ViewManager.CustomView {


    public ViewManager mViewManager;
    LinearLayout mImageContainer;
    private Uri imageUri;
    private EditText name;
    private EditText address;
    private ImageView imageView;
    private Button save;
    private Button getImage;
    private GPStracker mGps;
    private ImageView markerPoition;
    private TextView typeBtn;
    private CustomDialog mDialogError;
    private User user;
    private EditText phone;
    private EditText website;
    private EditText description;
    /** Latitude  $ langitude**/
    private double lat = 0, lng = 0;
    private String myAddress = null;
    private String TAG = ".CreateStoreActivity";
    private int RESULT_LOAD_IMAGE = 6555;
    //init request http
    private RequestQueue queue;
    private int success=0;
    private JSONObject imageObjects;
    private ProgressDialog pdialog;
    private int nbrLoding=1;


    /*


    private static final int REQUEST_CAMERA = 63;
    private static final int REQUEST_CAMERA_PERMISSION = 165;
    private Point mSize;
    private void initCamera(){

        Display display = getWindowManager().getDefaultDisplay();
        mSize = new Point();
        display.getSize(mSize);


    }

    public void requestForCameraPermission(View view) {


        final String permission = Manifest.permission.CAMERA;
        if (ContextCompat.checkSelfPermission(CreateStoreActivity.this, permission)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(CreateStoreActivity.this, permission)) {
                showPermissionRationaleDialog("Test", permission);
            } else {
                requestForPermission(permission);
            }
        } else {
            launch();
        }

    }

    private void showPermissionRationaleDialog(final String message, final String permission) {
        new AlertDialog.Builder(CreateStoreActivity.this)
                .setMessage(message)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        CreateStoreActivity.this.requestForPermission(permission);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create()
                .show();
    }



    private void handleCrop(int resultCode, Intent result) {
        if (resultCode == RESULT_OK) {

            imageView.setImageURI(Crop.getOutput(result));
            imageUri = Crop.getOutput(result);


        } else if (resultCode == Crop.RESULT_ERROR) {
            imageUri = null;
            Toast.makeText(this, Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
        }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CAMERA_PERMISSION:
                final int numOfRequest = grantResults.length;
                final boolean isGranted = numOfRequest == 1
                        && PackageManager.PERMISSION_GRANTED == grantResults[numOfRequest - 1];
                if (isGranted) {
                    launch();
                }
                break;

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        }
    }

    private void requestForPermission(final String permission) {
        ActivityCompat.requestPermissions(CreateStoreActivity.this, new String[]{permission}, REQUEST_CAMERA_PERMISSION);
    }

    private void launch() {
        Intent startCustomCameraIntent = new Intent(CreateStoreActivity.this, CameraActivity.class);
        startActivityForResult(startCustomCameraIntent, REQUEST_CAMERA);
    }


*/
    private RelativeLayout rl;
    private SweetSheet mSweetSheet;
    private int category_id = 0;
    private List<Uri> listImages = new ArrayList<>();
    private List<Uri> removedList = new ArrayList<>();

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_create);


        mGps = new GPStracker(this);

         mImageContainer = (LinearLayout) findViewById(R.id.image_container);
        mImageContainer.removeAllViews();



        mViewManager = new ViewManager(this);
        mViewManager.setLoading(findViewById(R.id.loading));
        mViewManager.setNoLoading(findViewById(R.id.content_my_store));
        mViewManager.setError(findViewById(R.id.error));
        mViewManager.setEmpty(findViewById(R.id.empty));
        mViewManager.setCustumizeView(this);
        mViewManager.showResult();


        user = SessionsController.getSession().getUser();

        if(ServiceHandler.isNetworkAvailable(this)){
            checkUserConnected();
        }else{
            mViewManager.empty();
        }



        rl = (RelativeLayout) findViewById(R.id.ll);

        typeBtn = (TextView) findViewById(R.id.type);
        typeBtn.setOnClickListener(this);

        setupRecyclerView();

        save = (Button) findViewById(R.id.save);
        getImage = (Button) findViewById(R.id.getImage);
        getImage.setVisibility(View.VISIBLE);
        getImage.setOnClickListener(this);

        name = (EditText) findViewById(R.id.name);
        address = (EditText) findViewById(R.id.address);
        phone = (EditText) findViewById(R.id.phone);
        website = (EditText) findViewById(R.id.webSite);
        description = (EditText) findViewById(R.id.description);





        name.setHint(getResources().getString(R.string.insert_name) + " Store");
        address.setHint(getResources().getString(R.string.insert_adr) + " Store");

        markerPoition = (ImageView) findViewById(R.id.marker);
        markerPoition.setOnClickListener(this);

        imageView  = (ImageView) findViewById(R.id.image);


        queue = VolleySingleton.getInstance(this).getRequestQueue();


        save.setOnClickListener(this);


        Utils.setFont(this, save, "");
        Utils.setFont(this,getImage,"");
        Utils.setFont(this,name,"");
        Utils.setFont(this,address,"");
        Utils.setFont(this, typeBtn, "");
        Utils.setFont(this, phone, "");
        Utils.setFont(this, description, "");
        Utils.setFont(this, website, "");



        //initCamera();





    }

    @Override
    protected void onStart() {
        super.onStart();

        // getIntent() is a method from the started activity
        Intent myIntent = getIntent(); // gets the previously created intent

        if(myIntent != null){
            myAddress =  myIntent.getStringExtra("description");
            address.setText(myAddress);
            lat= myIntent.getDoubleExtra("lat",0.0);
            lng = myIntent.getDoubleExtra("lng",0.0);
        }



    }

    private void getImageFromGallery(){

        Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, RESULT_LOAD_IMAGE);

    }

    private void beginCrop(Uri source) {
        if(AppConfig.APP_DEBUG)
            Log.e("path", source.getPath());

        String dis = source.getPath()+"_cropped";
        Uri disUri = Uri.parse(dis);
        Crop.of(source, disUri).asSquare().start(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (resultCode != RESULT_OK) return;

        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK) {

            Uri selectedImage = data.getData();

           // beginCrop(selectedImage);


            if(checkImageExist(selectedImage)==false){
                listImages.add(selectedImage);
                addImageView(selectedImage,selectedImage);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    private boolean checkImageExist(Uri path){

        for(int i=0;i<listImages.size();i++){

            if(listImages.get(i).toString().equals(path.toString())){

                for(int f=0;f<removedList.size();f++) {
                    if(removedList.get(f).toString().equals(path.toString())){
                        return false;
                    }
                }

                return true;

            }
        }

        return false;
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.save){

            if(ServiceHandler.isNetworkAvailable(this)){

                if(listImages.size()>0){
                    uploadImage();
                }else{
                    syncToServer();
                }


            }else{
                ServiceHandler.showSettingsAlert(this);
            }


        }else if(v.getId() == R.id.type){

            if(mSweetSheet.isShow()){
                mSweetSheet.dismiss();
            }else{
                mSweetSheet.show();
            }

        }else if(v.getId() == R.id.getImage){
            getImageFromGallery();
        }else if( v.getId() == R.id.marker)
        {

            Intent intent = new Intent(this, MapMarkerPosition.class);
            startActivity(intent);
        }

    }

    private void uploadImage(){


        if(listImages.size()>0) {

            if(pdialog==null)
            pdialog = new ProgressDialog(CreateStoreActivity.this);

            pdialog.setMessage("Uploading image "+nbrLoding+" ...");
            pdialog.setCancelable(false);

            if(!pdialog.isShowing())
                        pdialog.show();


            SimpleRequest request = new SimpleRequest(Request.Method.POST,
                    Constances.API.API_USER_UPLOAD64, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    pdialog.dismiss();

                    try {

                        JSONObject js = new JSONObject(response);

                        ImagesParser mImageParser = new ImagesParser(js);
                        success = Integer.parseInt(mImageParser.getStringAttr(Tags.SUCCESS));

                        if (AppConfig.APP_DEBUG)
                            Log.e("response", response);

                        if (success == 1) {

                            nbrLoding++;

                            mImageParser = new ImagesParser(js.getJSONObject("data"));

                            if(imageObjects==null)
                                imageObjects = new JSONObject();

                            imageObjects.put(String.valueOf((nbrLoding-1)), mImageParser.getStringAttr("image"));




                            if(listImages.size()>0)
                                    listImages.remove(0);

                            if(listImages.size()>0){
                                uploadImage();
                            }else{
                                syncToServer();
                            }


                        } else {
                            //show error


                            Map<String, String> errors = mImageParser.getErrors();

                            mDialogError = showErrors(errors);
                            mDialogError.setTitle("Error uploading");


                        }


                    } catch (JSONException e) {
                        e.printStackTrace();

                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if(APP_DEBUG) { Log.e("ERROR", error.toString());}

                    pdialog.dismiss();
                    nbrLoding = 1;

                }
            }) {

                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();

                    if (listImages.size()>0) {
                        Uri newPath = Uri.parse(getPath(listImages.get(0)));

                        if(AppConfig.APP_DEBUG)
                            Log.e("__path__",newPath.toString());

                        Bitmap bm = BitmapFactory.decodeFile(newPath.getPath());
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
                        byte[] b = baos.toByteArray();
                        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
                        params.put("image", encodedImage);

                    }

                    params.put("token", Utils.getToken(getBaseContext()));
                    params.put("mac_adr", ServiceHandler.getMacAddr() );


                    return params;
                }

            };


            request.setRetryPolicy(new DefaultRetryPolicy(SimpleRequest.TIME_OUT,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            queue.add(request);
        }else{
            syncToServer();
        }


    }

    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    private void syncToServer(){

        queue = VolleySingleton.getInstance(this).getRequestQueue();

        pdialog = new ProgressDialog(this);
        pdialog.setMessage("Wait ...");
        pdialog.setCancelable(false);
        pdialog.show();
        pdialog.show();



        SimpleRequest request = new SimpleRequest(Request.Method.POST,
                Constances.API.API_USER_CREATE_STORE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                pdialog.dismiss();

                try {



                    JSONObject js = new JSONObject(response);


                    StoreParser mStoreParser = new StoreParser(js);

                    int success =Integer.parseInt(mStoreParser.getStringAttr(Tags.SUCCESS));

                    if(success==1) {

                        Toast.makeText(CreateStoreActivity.this,"Success",Toast.LENGTH_LONG).show();

                        startActivity(new Intent(CreateStoreActivity.this, MainActivity.class));
                        overridePendingTransition(R.anim.righttoleft_enter, R.anim.righttoleft_exit);
                        finish();


                    }else if(success==-1){

                        Map<String,String> errors = mStoreParser.getErrors();

                        mDialogError = showErrors(errors);
                        mDialogError.setTitle("Error");

                        mDialogError.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                finish();
                                overridePendingTransition(R.anim.righttoleft_enter, R.anim.righttoleft_exit);
                                startActivity(new Intent(CreateStoreActivity.this,MainActivity.class));
                            }
                        });


                    }else{
                        Map<String,String> errors = mStoreParser.getErrors();

                        mDialogError = showErrors(errors);
                        mDialogError.setTitle("Erreur de l'ajout ");

                    }




                } catch (JSONException e) {
                    e.printStackTrace();
                    Map<String,String> errors = new HashMap<String,String>();
                    errors.put("JSONException:", "Error \"Json parser\"");
                    mDialogError = showErrors(errors);
                    mDialogError.setTitle("Error exception parser");

                    mDialogError.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mDialogError.dismiss();
                            finish();
                        }
                    });
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(APP_DEBUG) {  Log.e("ERROR", error.toString());}

                pdialog.dismiss();

                Map<String,String> errors = new HashMap<String,String>();

                errors.put("NetworkException:", getResources().getString(R.string.check_network));
                mDialogError = showErrors(errors);
                mDialogError.setTitle(getResources().getString(R.string.network_error));

                error.printStackTrace();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();


                if(imageObjects!=null && imageObjects.length()>0)
                    params.put("images",imageObjects.toString());

                User user = SessionsController.getSession().getUser();

                if(user!=null)params.put("user_id",user.getId()+"");  else params.put("user_id","0");
                params.put("name",name.getText().toString());
                params.put("description",address.getText().toString());
                params.put("latitude",lat+"");
                params.put("longitude",lng+"");
                params.put("category_id",category_id+"");
                params.put("detail",description.getText().toString());
                params.put("phone",phone.getText().toString());
                params.put("website",website.getText().toString());
                params.put("token",Utils.getToken(getBaseContext()));
                params.put("mac_adr",ServiceHandler.getMacAddr()   );

                if(AppConfig.APP_DEBUG)
                    Log.e("imagesObjects",imageObjects.toString());

                return params;
            }

        };


        request.setRetryPolicy(new DefaultRetryPolicy(SimpleRequest.TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(request);


    }

    private void checkUserConnected(){

        queue = VolleySingleton.getInstance(this).getRequestQueue();


        mViewManager.loading();

        if(user==null){
            startActivity(new Intent(CreateStoreActivity.this, LoginActivity.class));
            overridePendingTransition(R.anim.lefttoright_enter,R.anim.lefttoright_exit);
            mViewManager.empty();
        }

        SimpleRequest request = new SimpleRequest(Request.Method.POST,
                Constances.API.API_USER_CHECK, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                mViewManager.showResult();

                try {

                    if(APP_DEBUG) {   Log.e("response", response);}

                    JSONObject js = new JSONObject(response);

                    UserParser mUserParser = new UserParser(js);

                    int success = Integer.parseInt(mUserParser.getStringAttr(Tags.SUCCESS));

                    if(success==1){



                    }else{

                        SessionsController.logOut();
                        startActivity(new Intent(CreateStoreActivity.this, LoginActivity.class));
                        overridePendingTransition(R.anim.lefttoright_enter,R.anim.lefttoright_exit);
                        mViewManager.empty();

                    }


                } catch (JSONException e) {
                    e.printStackTrace();


                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(APP_DEBUG) {     Log.e("ERROR", error.toString());}
                mViewManager.empty();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();


                if(user!=null)
                params.put("user_id",user.getId()+"");


                params.put("token",Utils.getToken(getBaseContext()));
                params.put("mac_adr",ServiceHandler.getMacAddr() );



                return params;
            }

        };


        request.setRetryPolicy(new DefaultRetryPolicy(SimpleRequest.TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(request);

    }

    private void setupRecyclerView() {

        ArrayList<MenuEntity> list = new ArrayList<>();

        final List<Category> listCats = CategoryController.getArrayList();

        for(int i=0;i<listCats.size();i++){

            MenuEntity menuEntity = new MenuEntity();
            menuEntity.iconId = R.drawable.ic_store_grey600_24dp;
            menuEntity.title = listCats.get(i).getNameCat();
            list.add(menuEntity);

        }

        mSweetSheet = new SweetSheet(rl);
        mSweetSheet.setMenuList(list);
        mSweetSheet.setDelegate(new RecyclerViewDelegate(true));
        mSweetSheet.setBackgroundEffect(new BlurEffect(8));

        mSweetSheet.setOnMenuItemClickListener(new SweetSheet.OnMenuItemClickListener() {
            @Override
            public boolean onItemClick(int position, MenuEntity menuEntity1) {


//                if(position==0){
//                    type = 1;
//                    name.setHint(getResources().getString(R.string.insert_name) + " Pharmacie");
//                    description.setHint(getResources().getString(R.string.insert_adr) + " Pharmacie");
//
//                }else if(position==1){
//                    type = 3;
//                    name.setHint(getResources().getString(R.string.insert_name) + " Parapharmacie");
//                    description.setHint(getResources().getString(R.string.insert_adr) + " Parapharmacie");
//                    findViewById(R.id.container).setVisibility(View.VISIBLE);
//                }

                category_id = listCats.get(position).getNumCat();

                findViewById(R.id.container).setVisibility(View.VISIBLE);
                return true;
            }
        });


    }

    public CustomDialog showErrors(Map<String,String> errors){
        final CustomDialog dialog = new CustomDialog(this);

        dialog.setContentView(R.layout.fragment_dialog_costum);

        dialog.setCancelable(false);


        String text = "";
        for ( String key : errors.keySet() ) {
            if(!text.equals(""))
                text = text+"<br>";


            text = text+"#"+errors.get(key);
        }

        Button ok = (Button) dialog.findViewById(R.id.ok);
        Button cancel = (Button) dialog.findViewById(R.id.cancel);

        TextView msgbox = (TextView) dialog.findViewById(R.id.msgbox);

        if(!text.equals("") ){
            msgbox.setText(Html.fromHtml(text));
        }
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cancel.setVisibility(View.GONE);


        dialog.show();

        return dialog;

    }

    @Override
    public void onBackPressed() {

        if (mSweetSheet.isShow()) {
            if (mSweetSheet.isShow()) {
                mSweetSheet.dismiss();
            }

        }if(mDialogError!=null){
            if(mDialogError.isShowing())
            mDialogError.dismiss();
            else {
                super.onBackPressed();
                overridePendingTransition(R.anim.righttoleft_enter, R.anim.righttoleft_exit);
            }

        } else {
            super.onBackPressed();
            overridePendingTransition(R.anim.righttoleft_enter, R.anim.righttoleft_exit);

        }




    }

    @Override
    public void customErrorView(View v) {

    }

    @Override
    public void customLoadingView(View v) {

    }

    @Override
    public void customEmptyView(View v) {
            TextView text = (TextView) v.findViewById(R.id.text);
            text.setText("");
    }

    private void getImagesList(){

            for(int f=0;f<removedList.size();f++){

                for(int i=0;i<listImages.size();i++) {

                    if (removedList.get(f).toString().equals(listImages.get(i).toString())) {
                        listImages.remove(i);
                    }
                }

            }


        for(int i=0;i<listImages.size();i++){
            if(AppConfig.APP_DEBUG)
                Log.e("__image",listImages.get(i).getPath());
        }

    }


    private void addImageView(final Uri uri, final Uri path){

        LayoutInflater inflater = (LayoutInflater)getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);

        final View theInflatedView = inflater.inflate(R.layout.custom_image_view, null); // 2 and 3
        ImageView imageView = (ImageView) theInflatedView.findViewById(R.id.image);
        ImageButton btn = (ImageButton) theInflatedView.findViewById(R.id.delete);

        mImageContainer.addView(theInflatedView);
        Picasso.with(CreateStoreActivity.this).load(uri).fit().centerCrop().into(imageView);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                removedList.add(path);

                theInflatedView.setVisibility(View.GONE);

            }
        });


    }




}
