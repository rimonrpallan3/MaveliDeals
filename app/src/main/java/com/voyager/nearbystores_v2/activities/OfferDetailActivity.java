package com.voyager.nearbystores_v2.activities;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.voyager.nearbystores_v2.GPS.GPStracker;
import com.voyager.nearbystores_v2.R;
import com.voyager.nearbystores_v2.appconfig.AppConfig;
import com.voyager.nearbystores_v2.appconfig.Constances;
import com.voyager.nearbystores_v2.classes.Offer;
import com.voyager.nearbystores_v2.controllers.CampagneController;
import com.voyager.nearbystores_v2.controllers.stores.OffersController;
import com.voyager.nearbystores_v2.controllers.stores.StoreController;
import com.voyager.nearbystores_v2.load_manager.ViewManager;
import com.voyager.nearbystores_v2.network.VolleySingleton;
import com.voyager.nearbystores_v2.network.api_request.SimpleRequest;
import com.voyager.nearbystores_v2.parser.api_parser.OfferParser;
import com.voyager.nearbystores_v2.utils.DateUtils;
import com.voyager.nearbystores_v2.utils.OfferUtils;
import com.voyager.nearbystores_v2.utils.Utils;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.norbsoft.typefacehelper.TypefaceHelper;
import com.squareup.picasso.Picasso;
import com.thefinestartist.finestwebview.FinestWebView;

import org.bluecabin.textoo.LinksHandler;
import org.bluecabin.textoo.Textoo;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmList;

import static com.voyager.nearbystores_v2.appconfig.AppConfig.APP_DEBUG;
import static com.voyager.nearbystores_v2.appconfig.AppConfig.SHOW_ADS;
import static com.voyager.nearbystores_v2.appconfig.AppConfig.SHOW_ADS_IN_OFFER;

/**
 * Created by Amine on 11/13/2017.
 */

public class OfferDetailActivity extends AppCompatActivity implements ViewManager.CustomView {

    private int offer_id;
    private  ViewManager mViewManager;
    private ImageView image;
    private ImageView ivBanner;
    private TextView priceView,distanceView;
    private TextView detail_offer,offer_up_to;
    private TextView storeBtn;
    private LinearLayout storeBtnLayout;
    private LinearLayout llBanner;
    private AdView mAdView;


    @Override
    protected void onResume() {

        if(mAdView!=null)
             mAdView.resume();

        super.onResume();
    }

    @Override
    protected void onPause() {

        if(mAdView!=null)
                mAdView.pause();

        super.onPause();
    }

    @Override
    protected void onDestroy() {

        if(mAdView!=null)
            mAdView.destroy();

        super.onDestroy();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_detail);

        setupToolbar();


        //ADD ADMOB BANNER
        if (SHOW_ADS && SHOW_ADS_IN_OFFER) {

            mAdView = (AdView) findViewById(R.id.adView);
            mAdView.setVisibility(View.VISIBLE);
            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice("4A55E4EA2535643C0D74A5A73C4F550A")
                    .addTestDevice("3CB74DFA141BF4D0823B8EA7D94531B5")
                    .build();
            mAdView.loadAd(adRequest);
        }

        //INIT VIEW MANAGER
        mViewManager = new ViewManager(this);
        mViewManager.setLoading(findViewById(R.id.loading));
        mViewManager.setNoLoading(findViewById(R.id.content_offer));
        mViewManager.setError(findViewById(R.id.error));
        mViewManager.setEmpty(findViewById(R.id.empty));
        mViewManager.setCustumizeView(this);

        mViewManager.loading();

        TypefaceHelper.typeface(this);

         image = (ImageView) findViewById(R.id.image);
         priceView = (TextView) findViewById(R.id.priceView);
         distanceView = (TextView) findViewById(R.id.distanceView);
         detail_offer = (TextView) findViewById(R.id.detail_offer);
         storeBtn = (TextView) findViewById(R.id.storeBtn);
            offer_up_to = (TextView) findViewById(R.id.offer_up_to);
         storeBtnLayout = (LinearLayout) findViewById(R.id.storeBtnLayout);

        try {
            offer_id = getIntent().getExtras().getInt("offer_id");

            if(offer_id==0){
                offer_id = getIntent().getExtras().getInt("id");
            }


            if(offer_id==0){
                offer_id = Integer.parseInt(getIntent().getExtras().getString("id"));
            }

            if(AppConfig.APP_DEBUG)
                Toast.makeText(this,String.valueOf(offer_id),Toast.LENGTH_LONG).show();
        }catch (Exception e){
            finish();
        }

        final Offer mOffer = OffersController.findOfferById(offer_id);
        if(mOffer!=null && mOffer.isLoaded() && mOffer.isValid()){

            mViewManager.showResult();
            putInsideViews(mOffer);

        }else{
            getOffer(offer_id);
        }

        if(mOffer.getDate_end().equals(mOffer.getDate_start())){
            offer_up_to.setVisibility(View.GONE);

        }else {

           String date = DateUtils.prepareOutputDate(mOffer.getDate_end(),"YYYY-mm-dd HH:mm",this);
          // date = date+" 00:00";

           offer_up_to.setText(String.format(getString(R.string.offer_up_to),date));

//            CountdownView mCvCountdownView = (CountdownView)findViewById(R.id.cv_countdownViewTest1);
//
//            long millSeconds = DateUtils.getDateToMillSeconds(date);
//
//            mCvCountdownView.start(millSeconds); // Millisecond
//            for (int time=0; time<1000; time++) {
//                mCvCountdownView.updateShow(time);
//            }
//
//            Toast.makeText(this,"millseconds: "+millSeconds,Toast.LENGTH_LONG).show();

        }


    }

    private void putInsideViews(final Offer mOffer){

        APP_TITLE_VIEW.setText(mOffer.getName());
        if(mOffer.getStore_id()>0){

            Drawable storeDrawable = new IconicsDrawable(this)
                    .icon(CommunityMaterial.Icon.cmd_map_marker)
                    .color(ResourcesCompat.getColor(getResources(),R.color.iconColor,null))
                    .sizeDp(18);


            String symbole = com.voyager.nearbystores_v2.utils.Utils.getDistanceBy(
                    mOffer.getDistance()
            );
            String distance = com.voyager.nearbystores_v2.utils.Utils.preparDistance(
                    mOffer.getDistance()
            );
            distanceView.setText(
                    String.format(getString(R.string.offerIn), distance+" "+symbole.toUpperCase())
            );


            storeBtn.setText(mOffer.getStore_name());
            storeBtn.setCompoundDrawables(storeDrawable,null,null,null);
            storeBtn.setCompoundDrawablePadding(20);
            storeBtn.setPaintFlags(storeBtn.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

            storeBtnLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Realm realm = Realm.getDefaultInstance();
                    realm.beginTransaction();

                    // Do verification if the store exsit before launching the activity
                    // if does not exist launch message
                    if(StoreController.getStore(mOffer.getStore_id()) != null) {

                        if (!StoreDetailActivity.isOpend()) {
                            Intent intent = new Intent(OfferDetailActivity.this, StoreDetailActivity.class);
                            intent.putExtra("id", mOffer.getStore_id());
                            startActivity(intent);
                        }
                    }else
                    {
                        Toast.makeText(getApplicationContext(),"Sorry !! this store does not exist ",Toast.LENGTH_LONG).show();
                    }

                    realm.commitTransaction();

                }
            });

            storeBtnLayout.setVisibility(View.VISIBLE);
        }else
            storeBtnLayout.setVisibility(View.GONE);

        if(mOffer.getContent().getPrice()>0)
            priceView.setText(
                    OfferUtils.parseCurrencyFormat(
                            mOffer.getContent().getPrice(),
                            mOffer.getContent().getCurrency()
                    )
            );
        else if(mOffer.getContent().getPercent()!=0){
            priceView.setText(mOffer.getContent().getPercent()+"%");
        }else {
            priceView.setText(getString(R.string.promo));
        }

        if(mOffer.getImages()!=null)
            Picasso.with(this).load(mOffer.getImages().getUrl500_500()).into(image);

        detail_offer.setText(mOffer.getContent().getDescription());
       // new TextUtils.decodeHtml(detail_offer).execute(mOffer.getContent().getDescription());

            Textoo
                .config(detail_offer)
                .linkifyWebUrls()  // or just .linkifyAll()
                .addLinksHandler(new LinksHandler() {
                    @Override
                    public boolean onClick(View view, String url) {

                        if (Utils.isValidURL(url)) {

                            new FinestWebView.Builder(OfferDetailActivity.this)
                                    .showMenuOpenWith(false)
                                    .statusBarColorRes(R.color.colorPrimary)
                                    .theme(R.style.FinestWebViewAppTheme)
                                    .titleColor(
                                            ResourcesCompat.getColor(getResources(),R.color.defaultColor,null)
                                    ).urlColor(
                                    ResourcesCompat.getColor(getResources(),R.color.defaultColor,null)
                            ).show(url);

                            return true;
                        } else {
                            return false;
                        }
                    }
                })
                .apply();

        try {
           int  cid = Integer.parseInt(getIntent().getExtras().getString("cid")) ;
            CampagneController.markView(cid);
           // Toast.makeText(this,"CMarkViewClicked",Toast.LENGTH_LONG).show();
        }catch (Exception e){
            if(AppConfig.APP_DEBUG)
            e.printStackTrace();
        }

    }


    private TextView APP_TITLE_VIEW,APP_DESC_VIEW;
    public void setupToolbar() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        // getSupportActionBar().setSubtitle("E-shop");
        getSupportActionBar().setTitle("");
        //getSupportActionBar().setHomeButtonEnabled(true);
        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_white_36dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(false);


        getSupportActionBar().setDisplayShowTitleEnabled(false);
        APP_TITLE_VIEW = (TextView) toolbar.findViewById(R.id.toolbar_title);
        APP_DESC_VIEW = (TextView) toolbar.findViewById(R.id.toolbar_description);

        APP_DESC_VIEW.setVisibility(View.GONE);
        Utils.setFont(this, APP_DESC_VIEW, Constances.Fonts.BOLD);
        Utils.setFont(this, APP_TITLE_VIEW, Constances.Fonts.BOLD);

        APP_TITLE_VIEW.setText(R.string.store_title_detail);
        APP_DESC_VIEW.setVisibility(View.GONE);

    }



    public void getOffer(final int offer_id) {

        mViewManager.loading();

        final GPStracker mGPS = new GPStracker(this);

        SimpleRequest request = new SimpleRequest(Request.Method.POST,
                Constances.API.API_GET_OFFERS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                mViewManager.showResult();


                try {

                    if (APP_DEBUG) {
                        Log.e("responseOffersString", response);
                    }

                    JSONObject jsonObject = new JSONObject(response);
                    final OfferParser mOfferParser = new OfferParser(jsonObject);
                    RealmList<Offer> list = mOfferParser.getOffers();

                    if(list.size()>0){

                        OffersController.insertOffers(list);

                        putInsideViews(list.get(0));

                    }else {


                        mViewManager.empty();

                    }

                } catch (JSONException e) {
                    //send a rapport to support
                    if (APP_DEBUG)
                        e.printStackTrace();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (APP_DEBUG) {
                    Log.e("ERROR", error.toString());
                }
                mViewManager.error();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                if (mGPS.canGetLocation()) {
                    params.put("lat", mGPS.getLatitude() + "");
                    params.put("lng", mGPS.getLongitude() + "");
                }

                params.put("limit", "1");
                params.put("offer_id", offer_id + "");

                if (APP_DEBUG) {
                    Log.e("ListStoreFragment", "  params getOffers :" + params.toString());
                }

                return params;
            }

        };


        request.setRetryPolicy(new DefaultRetryPolicy(SimpleRequest.TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        VolleySingleton.getInstance(this).getRequestQueue().add(request);

    }


    @Override
    public void customErrorView(View v) {

    }

    @Override
    public void customLoadingView(View v) {

    }

    @Override
    public void customEmptyView(View v) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(android.R.id.home==item.getItemId()){
            if(!MainActivity.isOpend()){
                startActivity(new Intent(this,MainActivity.class));
            }
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {

        if(!MainActivity.isOpend()){
            startActivity(new Intent(this,MainActivity.class));
        }else {

        }

        super.onBackPressed();
    }

}
