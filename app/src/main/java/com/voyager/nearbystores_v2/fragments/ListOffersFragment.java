package com.voyager.nearbystores_v2.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.multidex.MultiDex;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.mikepenz.iconics.IconicsDrawable;
import com.voyager.nearbystores_v2.GPS.GPStracker;
import com.voyager.nearbystores_v2.adapter.OfferChildListAdapter;
import com.voyager.nearbystores_v2.R;
import com.voyager.nearbystores_v2.RetorHelper.MainOffers;
import com.voyager.nearbystores_v2.RetorHelper.OfferList;
import com.voyager.nearbystores_v2.RetorHelper.Result;
import com.voyager.nearbystores_v2.activities.MainActivity;
import com.voyager.nearbystores_v2.activities.OfferDetailActivity;
import com.voyager.nearbystores_v2.adapter.OfferFilterListAdapter;
import com.voyager.nearbystores_v2.adapter.lists.OfferListAdapter;
import com.voyager.nearbystores_v2.appconfig.AppConfig;
import com.voyager.nearbystores_v2.appconfig.Constances;
import com.voyager.nearbystores_v2.classes.Category;
import com.voyager.nearbystores_v2.classes.Filter;
import com.voyager.nearbystores_v2.classes.Offer;
import com.voyager.nearbystores_v2.controllers.ErrorsController;
import com.voyager.nearbystores_v2.controllers.Filter.FilterController;
import com.voyager.nearbystores_v2.controllers.stores.OffersController;
import com.voyager.nearbystores_v2.dtmessenger.MessengerHelper;
import com.voyager.nearbystores_v2.load_manager.ViewManager;
import com.voyager.nearbystores_v2.network.ServiceHandler;
import com.voyager.nearbystores_v2.network.VolleySingleton;
import com.voyager.nearbystores_v2.network.api_request.SimpleRequest;
import com.voyager.nearbystores_v2.parser.api_parser.OfferParser;
import com.voyager.nearbystores_v2.parser.tags.Tags;
import com.voyager.nearbystores_v2.presenter.IOfferFilterPresenter;
import com.voyager.nearbystores_v2.presenter.OfferFilterPresenter;
import com.voyager.nearbystores_v2.utils.DateUtils;
import com.voyager.nearbystores_v2.utils.Utils;
import com.mikepenz.actionitembadge.library.ActionItemBadge;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.norbsoft.typefacehelper.TypefaceHelper;
import com.voyager.nearbystores_v2.views.IOfferFilterView;
import com.voyager.nearbystores_v2.webservices.ApiClient;
import com.voyager.nearbystores_v2.webservices.WebServices;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.realm.Realm;
import io.realm.RealmList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

import static com.voyager.nearbystores_v2.appconfig.AppConfig.APP_DEBUG;

public class ListOffersFragment extends android.support.v4.app.Fragment
        implements OfferListAdapter.ClickListener,
        IOfferFilterView,
        SwipeRefreshLayout.OnRefreshListener,
        ViewManager.CustomView {

    public ViewManager mViewManager;
    //loading
    public SwipeRefreshLayout swipeRefreshLayout;
    //for scrolling params
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    private LinearLayoutManager mLayoutManager;
    private int listType = 1;
    private RecyclerView list;
    private RecyclerView childListRecycleView;
    private RecyclerView filterListView;
    private OfferListAdapter adapter;
    private OfferFilterListAdapter adapterFilter;
    private OfferChildListAdapter adapterChildFilter;
    LinearLayout filterBtnClose;
    LinearLayout filterLayoutBtn;
    Button applyFilter;
    FrameLayout filterListLayout;
    FrameLayout filterApplyLayout;
    //init request http
    private RequestQueue queue;
    private boolean loading = true;
    //pager
    private int COUNT = 0;
    private int REQUEST_PAGE = 1;
    private Category mCat;
    private GPStracker mGPS;
    private List<Offer> listStores = new ArrayList<>();

    private List<Filter> filterList = new ArrayList<>();
    private int Fav = 0;
    private  int REQUEST_RANGE_RADIUS =-1;
    private String REQUEST_SEARCH ="";
    String offertype_id="";
    IOfferFilterPresenter iOfferFilterPresenter;
    @BindView(R.id.closeFilterTitle)
    TextView closeFilterTitle;
    @BindView(R.id.revertFilterTxt)
    ImageView revertFilterTxt;
    @BindView(R.id.closeFiltersImg)
    ImageView closeFiltersImg;
    @BindView(R.id.applyBtnFilter)
    Button applyBtnFilter;
    OfferList offerList;
    ExpandableListView expandableListView;
    List<MainOffers> revertFilter;
    List<String> offerIdList=new ArrayList<String>();
    List<String> offerInnerIdList=new ArrayList<String>();
    List<String> offerChildInnerIdList=new ArrayList<String>();

    //----------- rimon changes -----------
    private Unbinder unbinder;

    private int store_id = 0;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
        REQUEST_RANGE_RADIUS = getResources().getInteger(R.integer.radius_map);

    }


    private void updateBadge(){
        if (MessengerHelper.NbrMessagesManager.getNbrTotalMessages() > 0) {
            ActionItemBadge.update(getActivity(), MainActivity.mainMenu.findItem(R.id.item_samplebadge),
                    CommunityMaterial.Icon.cmd_bell_ring_outline,
                    ActionItemBadge.BadgeStyles.RED,
                    MessengerHelper.NbrMessagesManager.getNbrTotalMessages());
        } else {
            ActionItemBadge.hide(MainActivity.mainMenu.findItem(R.id.item_samplebadge));
        }
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.search_icon).setVisible(true);
        updateBadge();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.search_icon){

            SearchDialog.newInstance(getContext()).setOnSearchListener(new SearchDialog.Listener() {
                @Override
                public void onSearchClicked(SearchDialog mSearchDialog, String value, int radius) {

                    if(mSearchDialog.isShowing())
                        mSearchDialog.dismiss();

                    if(AppConfig.APP_DEBUG)
                        Toast.makeText(getContext(),value+" "+radius,Toast.LENGTH_LONG).show();

                    REQUEST_RANGE_RADIUS = radius;
                    REQUEST_SEARCH = value;
                    getOffers(REQUEST_PAGE);

                }
            }).setHeader(getString(R.string.searchOnOffers)).showDialog();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_offers_list, container, false);
        MultiDex.install(getActivity());
        unbinder = ButterKnife.bind(this, rootView);
        iOfferFilterPresenter = new OfferFilterPresenter(this);
        expandableListView = (ExpandableListView) rootView.findViewById(R.id.expandible_listview);

        TypefaceHelper.typeface(rootView);
        Realm realm = Realm.getDefaultInstance();


        try {
            store_id = getArguments().getInt("store_id");
        }catch (Exception e){}

        try {

            int CatId = getArguments().getInt("category");
            mCat = realm.where(Category.class).equalTo("numCat",CatId).findFirst();

            //load from assets
           /*if(!ServiceHandler.isNetworkAvailable(getActivity())){

               if(Constances.ENABLE_OFFLINE)
                listStores = loader.parseStoresFromAssets(getActivity(),mCat.getNumCat());
           }*/

        } catch (Exception e) {

            e.printStackTrace();
        }

        mGPS = new GPStracker(getActivity());
        queue = VolleySingleton.getInstance(getActivity()).getRequestQueue();

        mViewManager = new ViewManager(getActivity());
        mViewManager.setLoading(rootView.findViewById(R.id.loading));
        mViewManager.setNoLoading(rootView.findViewById(R.id.content_my_store));
        mViewManager.setError(rootView.findViewById(R.id.error));
        mViewManager.setEmpty(rootView.findViewById(R.id.empty));
        mViewManager.setCustumizeView(this);


        list = (RecyclerView) rootView.findViewById(R.id.list);
        childListRecycleView = (RecyclerView) rootView.findViewById(R.id.childList);
        filterListView = (RecyclerView) rootView.findViewById(R.id.filterListView);
        filterBtnClose = (LinearLayout) rootView.findViewById(R.id.filterBtnClose);
        filterLayoutBtn = (LinearLayout) rootView.findViewById(R.id.filterLayoutBtn);
        filterListLayout = (FrameLayout) rootView.findViewById(R.id.filterListLayout);
        filterApplyLayout = (FrameLayout) rootView.findViewById(R.id.filterApplyLayout);

        Drawable backDrawable = new IconicsDrawable(getActivity())
                .icon(CommunityMaterial.Icon.cmd_keyboard_backspace)
                .color(ResourcesCompat.getColor(getResources(),R.color.iconColor,null))
                .sizeDp(20);

        revertFilterTxt.setImageDrawable(backDrawable);

        /*filterBtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                childListRecycleView.setVisibility(View.GONE);
                filterListView.setVisibility(View.VISIBLE);
                revertFilterTxt.setVisibility(View.GONE);
                filterListLayout.setVisibility(View.GONE);
            }
        });*/
        /*filterLayoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterListLayout.setVisibility(View.VISIBLE);
            }
        });*/


        try {
            adapter = new OfferListAdapter(getActivity(), listStores);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println(" onCreateView error : "+e.getMessage());
        }
        adapter.setClickListener(this);


        list.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        //listcats.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        list.setItemAnimator(new DefaultItemAnimator());
        list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        list.setAdapter(adapter);

//
        list.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                visibleItemCount = mLayoutManager.getChildCount();
                totalItemCount = mLayoutManager.getItemCount();
                pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();
                if (loading) {

                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        loading = false;
                        if(ServiceHandler.isNetworkAvailable(getContext())) {
                            if (COUNT > adapter.getItemCount())
                                getOffers(REQUEST_PAGE);
                        }else
                        {
                            Toast.makeText(getContext(),"Network not available ",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });


        swipeRefreshLayout = (SwipeRefreshLayout)
                rootView.findViewById(R.id.refresh);

        swipeRefreshLayout.setOnRefreshListener(this);


        swipeRefreshLayout.setColorSchemeResources(
                R.color.colorPrimary,
                R.color.colorPrimary,
                R.color.colorPrimary,
                R.color.colorPrimary
        );


        if (ServiceHandler.isNetworkAvailable(this.getActivity())) {
            getOffers(REQUEST_PAGE);

        }else {

            swipeRefreshLayout.setRefreshing(false);
            Toast.makeText(getActivity(),getString(R.string.check_network),Toast.LENGTH_LONG).show();
            if(adapter.getItemCount()==0)
                mViewManager.error();
        }


        return rootView;
    }

    @Override
    public void itemClicked(View view, int position) {

        Intent intent = new Intent(getActivity(), OfferDetailActivity.class);
        intent.putExtra("offer_id",adapter.getItem(position).getId());
        startActivity(intent);

    }

    @Override
    public void iconImageViewOnClick(View v, int position) {

    }

    public void getOffers(final int page) {

        mGPS = new GPStracker(getActivity());
        swipeRefreshLayout.setRefreshing(true);

        if (adapter.getItemCount() == 0)
            mViewManager.loading();

        SimpleRequest request = new SimpleRequest(Request.Method.POST,
                Constances.API.API_GET_OFFERS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    if (APP_DEBUG) {
                        Log.e("responseOffersString", response);
                    }

                    JSONObject jsonObject = new JSONObject(response);


                    //Log.e("response",response);

                    final OfferParser mOfferParser = new OfferParser(jsonObject);
                   // List<Store> list = mStoreParser.getEventFromDB();
                    COUNT = 0;
                    COUNT = mOfferParser.getIntArg(Tags.COUNT);
                    System.out.println(" ----------- List Offer Fragment getOffers Count : "+COUNT);
                    mViewManager.showResult();


                    //check server permission and display the errors
                    if(mOfferParser.getSuccess()==-1){
                        ErrorsController.serverPermissionError(getActivity());
                    }

                    if (page == 1) {

                        (new Handler()).postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                RealmList<Offer> list = mOfferParser.getOffers();

                                adapter.removeAll();
                                List<Offer> expiredDateList = new ArrayList<>();
                                expiredDateList.clear();
                                Gson gson = new Gson();
                                for(int i=0;i<list.size();i++){
                                    Offer newOffer = list.get(i);
                                    if(DateUtils.checkDatesExpired(newOffer.getDate_end())){
                                        expiredDateList.add(newOffer);
                                    }
                                }
                                String jsonString = gson.toJson(list);
                                String jsonString2 = gson.toJson(expiredDateList);
                                //UserDetails user1 = gson.fromJson(jsonString,UserDetails.class);
                                System.out.println(" ----------- List Offer Fragment getOffers listStores : "+jsonString);
                                System.out.println(" ----------- List Offer Fragment  getOffers expiredDateList : "+jsonString2);

                                for (int i = 0; i < expiredDateList.size(); i++) {
                                   // if (list.get(i).getDistance() <= REQUEST_RANGE_RADIUS)
                                        adapter.addItem(expiredDateList.get(i));
                                }

                                //set it into database
                                OffersController.insertOffers(list);

                                swipeRefreshLayout.setRefreshing(false);
                                loading = true;

                                mViewManager.showResult();

                                if (COUNT > adapter.getItemCount())
                                    REQUEST_PAGE++;
                                if (COUNT == 0  || adapter.getItemCount() == 0) {
                                    mViewManager.empty();
                                }



                                if (APP_DEBUG) {
                                    Log.e("count ", COUNT + " page = " + page);
                                }
                                getFilters();

                            }
                        }, 800);
                    } else {
                        (new Handler()).postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                RealmList<Offer> list = mOfferParser.getOffers();
                                adapter.removeAll();
                                List<Offer> expiredDateList = new ArrayList<>();
                                expiredDateList.clear();
                                for(int i=0;i<list.size();i++){
                                    Offer newOffer = list.get(i);
                                    if(DateUtils.checkDatesExpired(newOffer.getDate_end())){
                                        expiredDateList.add(newOffer);
                                    }
                                }

                                for (int i = 0; i < expiredDateList.size(); i++) {
                                    // if (list.get(i).getDistance() <= REQUEST_RANGE_RADIUS)
                                    adapter.addItem(expiredDateList.get(i));
                                }

                                //set it into database
                                OffersController.insertOffers(list);

                                swipeRefreshLayout.setRefreshing(false);
                                mViewManager.showResult();
                                loading = true;
                                if (COUNT > adapter.getItemCount())
                                    REQUEST_PAGE++;

                                if (COUNT == 0  || adapter.getItemCount() == 0) {
                                    mViewManager.empty();
                                }
                                getFilters();
                            }
                        }, 800);

                    }

                } catch (JSONException e) {
                    //send a rapport to support
                    if(APP_DEBUG)
                        e.printStackTrace();

                    if(adapter.getItemCount()==0)
                        mViewManager.error();


                    swipeRefreshLayout.setRefreshing(false);
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

                params.put("token", Utils.getToken(getActivity()));
                params.put("mac_adr", ServiceHandler.getMacAddr()  );

                params.put("limit", "100");
                params.put("page", page + "");
                params.put("offertype_id",offertype_id);
                params.put("search", REQUEST_SEARCH);

                if(store_id>0)
                    params.put("store_id", String.valueOf(store_id));

                if (APP_DEBUG) {
                    Log.e("ListStoreFragment", "  params getOffers :" + params.toString());
                }


                if(REQUEST_RANGE_RADIUS ==-1){
                    int radius =100;
//                    if(radius<=99)
                        params.put("radius", String.valueOf((radius*1024)));
                }else {
                    if(REQUEST_RANGE_RADIUS <= 99)
                        params.put("radius", String.valueOf((REQUEST_RANGE_RADIUS *1024)));
                }

                return params;
            }

        };


        request.setRetryPolicy(new DefaultRetryPolicy(SimpleRequest.TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(request);

    }

    @Override
    public void onRefresh() {

            if(ServiceHandler.isNetworkAvailable(getContext())) {
                //SearchDialog.newInstance(getContext()).resetSearchDialog(true);
                REQUEST_SEARCH = "";
                REQUEST_PAGE = 1;
                REQUEST_RANGE_RADIUS = -1;
                getOffers(REQUEST_PAGE);


            }else
            {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(getActivity(),getString(R.string.check_network),Toast.LENGTH_LONG).show();

                if(adapter.getItemCount()==0)
                    mViewManager.error();
            }

    }

    private void setupFilterViews(){
        List <Filter> results = new ArrayList<>();

        RealmList<Filter> listCats = FilterController.list();

        for(Filter filt : listCats){
            if(filt.getNumFilt()>0)
                results.add(filt);
        }

    }

    private void getFilters(){

       /* if(!ServiceHandler.isNetworkAvailable(getContext())){
            if(FilterController.list().size()==0){
                //database.insertCats(Loader.parseCategoriesFromAssets(this));
            }
        }
*/
        System.out.println("-----------getFilters retrofit OfferList");
        Retrofit retrofit = new ApiClient().getRetrofitClient();
        WebServices webServices = retrofit.create(WebServices.class);
        Call<OfferList> call = webServices.doGetUserList();
        call.enqueue(new Callback<OfferList>() {
            @Override
            public void onResponse(Call<OfferList> call, retrofit2.Response<OfferList> response) {
                swipeRefreshLayout.setRefreshing(false);
                offerList  = (OfferList) response.body();
                Gson gson = new Gson();

                Result result = offerList.getResult();
                List<MainOffers> mainOffersList = new ArrayList<>();
                mainOffersList.add(result.getZeroOffer());
                mainOffersList.add(result.getFirstOffer());
                mainOffersList.add(result.getSecondOffer());
                mainOffersList.add(result.getThirdOffer());
                mainOffersList.add(result.getFourOffer());
                mainOffersList.add(result.getFiveOffer());
                mainOffersList.add(result.getSixOffer());
                mainOffersList.add(result.getEightOffer());
                mainOffersList.add(result.getNineOffer());
                mainOffersList.add(result.getTenOffer());
                result.setMainOffersList(mainOffersList);
                mainOffersList.removeAll(Collections.singleton(null));
                String jsonString = gson.toJson(mainOffersList);
                iOfferFilterPresenter.setAdapter(mainOffersList);
                //UserDetails user1 = gson.fromJson(jsonString,UserDetails.class);
                System.out.println(" ----------- getFilters OfferList "+jsonString);
                if(jsonString!=null) {
                    System.out.println("-----------getFilters OfferList"+jsonString);
                }

            }

            @Override
            public void onFailure(Call<OfferList> call, Throwable t) {
                if(APP_DEBUG) { Log.e("ERROR", t.toString());}
                System.out.println("-----------validateLoginDataBaseApi ERROR"+t.getMessage());
                swipeRefreshLayout.setRefreshing(false);
                //Toast.makeText((Context) iRegisterView, "ErrorMessage"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


       /* queue = VolleySingleton.getInstance(getContext()).getRequestQueue();

        SimpleRequest request = new SimpleRequest(Request.Method.GET,
                Constances.API.API_GET_FILTERS_OFFER_LIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                swipeRefreshLayout.setRefreshing(false);

                try{

                    if(APP_DEBUG) { Log.e("filtResponse",response);}

                    JSONObject jsonObject = new JSONObject(response);
                    // Log.e("response", jsonObject.toString());
                    final FilterOffersParser mFilterParser = new FilterOffersParser(jsonObject);
                    int success = Integer.parseInt(mFilterParser.getStringAttr(Tags.SUCCESS));

                    if(success==1){
                        System.out.println("ListOffersFragment getFilters success");
                        //database.deleteCats();
                        FilterController.insertFilters(
                                mFilterParser.getFilters()
                        );
                        filterList = FilterController.getArrayList();
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(jsonObject);
                        System.out.println("-----------ListOffersFragment getFilterso nResponse filterList: "+jsonString);
                        iOfferFilterPresenter.setAdapter();
                        adapterFilter = new OfferFilterListAdapter(filterList);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                        filterListView.setLayoutManager(mLayoutManager);
                        filterListView.setItemAnimator(new DefaultItemAnimator());
                        filterListView.setAdapter(adapterFilter);
                    }

                }catch (JSONException e){
                    System.out.println("ListOffersFragment getFilters Error:"+e.getMessage());
                    //send a rapport to support
                    e.printStackTrace();

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }


        }) {



        };

        request.setRetryPolicy(new DefaultRetryPolicy(SimpleRequest.TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(request);
*/




    }

    @Override
    public void onStart() {
        super.onStart();

        Bundle args = getArguments();
        if (args != null) {
            //Toast.makeText(getActivity(), "  is Liked  :"+args.get("isLiked"), Toast.LENGTH_LONG).show();
            Fav = args.getInt("fav");

        }
    }

    @Override
    public void customErrorView(View v) {

        Button retry = (Button) v.findViewById(R.id.btn);

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mGPS = new GPStracker(getActivity());

                if (!mGPS.canGetLocation() && listType == 1)
                    mGPS.showSettingsAlert();

                getOffers(1);
                REQUEST_PAGE = 1;
                mViewManager.loading();
            }
        });

    }

    @Override
    public void customLoadingView(View v) {


    }

    @Override
    public void customEmptyView(View v) {

        Button btn = (Button) v.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeFilterTitle.setVisibility(View.GONE);
                offertype_id= "";
                mViewManager.loading();
                REQUEST_RANGE_RADIUS = -1;
                //getOffers(1);
                REQUEST_PAGE = 1;
                SearchDialog.newInstance(getContext()).resetSearchDialog(true);
                REQUEST_RANGE_RADIUS = 100;
                REQUEST_SEARCH = "";
                getOffers(REQUEST_PAGE);

            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

    }

    @OnClick(R.id.applyFilters)
    public void applyFilters(){
        filterListLayout.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.applyFiltersImg)
    public void applyFiltersImg(){
        filterListLayout.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.revertFilterTxt)
    public void backToMainFilter(){
        childListRecycleView.setVisibility(View.GONE);
        filterListView.setVisibility(View.VISIBLE);
        revertFilterTxt.setVisibility(View.GONE);
        filterApplyLayout.setVisibility(View.GONE);
        /*adapterFilter = new OfferFilterListAdapter(revertFilter, this);
        filterListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        filterListView.setItemAnimator(new DefaultItemAnimator());
        filterListView.setAdapter(adapterFilter);*/
    }

    @OnClick(R.id.closeFilterTitle)
    public void closeTitleFilters(){
        filterListLayout.setVisibility(View.GONE);
        offertype_id= "";
        mViewManager.loading();
        getOffers(1);
        REQUEST_PAGE = 1;
        closeFilterTitle.setVisibility(View.GONE);
    }

    public String removeArrayBracket(List<String> offerLists){
        Gson gson = new Gson();
        String jsonString = gson.toJson(offerLists);
        jsonString = jsonString.replaceAll("\\[", "").replaceAll("\\]","");
        //UserDetails user1 = gson.fromJson(jsonString,UserDetails.class);
        System.out.println(" ----------- getFilters OfferList "+jsonString);
        if(jsonString!=null) {
            System.out.println("-----------getFilters OfferList"+jsonString);
        }
        return jsonString;
    }


    @OnClick(R.id.applyBtnFilter)
    public void applyInnerFilters() {

        if(filterListView.getVisibility()== View.VISIBLE){
            offertype_id = removeArrayBracket(offerIdList);
            filterListLayout.setVisibility(View.GONE);
            getfiltered(offertype_id);
            closeFilterTitle.setVisibility(View.VISIBLE);
            offerIdList.clear();
        }else if(childListRecycleView.getVisibility()== View.VISIBLE){
            offertype_id = removeArrayBracket(offerInnerIdList);
            filterListLayout.setVisibility(View.GONE);
            getfiltered(offertype_id);
            closeFilterTitle.setVisibility(View.VISIBLE);
            offerInnerIdList.clear();
            childListRecycleView.setVisibility(View.GONE);
            filterListView.setVisibility(View.VISIBLE);
        }

    }


    @OnClick(R.id.closeFiltersImg)
    public void closeFiltersImg() {
        childListRecycleView.setVisibility(View.GONE);
        filterListView.setVisibility(View.VISIBLE);
        revertFilterTxt.setVisibility(View.GONE);
        filterApplyLayout.setVisibility(View.GONE);
        filterListLayout.setVisibility(View.GONE);
    }

    @Override
    public void setFilterList(List<MainOffers> mainOffersList) {
        revertFilter = mainOffersList;
        adapterFilter = new OfferFilterListAdapter(mainOffersList, this, getActivity());
        filterListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        filterListView.setItemAnimator(new DefaultItemAnimator());
        filterListView.setAdapter(adapterFilter);
    }

    public void getfiltered(final String num){
        swipeRefreshLayout.setRefreshing(true);

        if (adapter.getItemCount() == 0)
            mViewManager.loading();

        SimpleRequest request = new SimpleRequest(Request.Method.POST,
                Constances.API.API_GET_OFFERS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    if (APP_DEBUG) {
                        Log.e("responseOffersString", response);
                    }

                    JSONObject jsonObject = new JSONObject(response);


                    //Log.e("response",response);

                    final OfferParser mOfferParser = new OfferParser(jsonObject);
                    // List<Store> list = mStoreParser.getEventFromDB();
                    COUNT = 0;
                    COUNT = mOfferParser.getIntArg(Tags.COUNT);
                    mViewManager.showResult();


                    //check server permission and display the errors
                    if(mOfferParser.getSuccess()==-1){
                        ErrorsController.serverPermissionError(getActivity());
                    }

                    if (num!=null) {

                        (new Handler()).postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                RealmList<Offer> list = mOfferParser.getOffers();

                                adapter.removeAll();
                                List<Offer> expiredDateList = new ArrayList<>();
                                expiredDateList.clear();
                                Gson gson = new Gson();
                                for(int i=0;i<list.size();i++){
                                    Offer newOffer = list.get(i);
                                    if(DateUtils.checkDatesExpired(newOffer.getDate_end())){
                                        expiredDateList.add(newOffer);
                                    }
                                }
                                String jsonString = gson.toJson(list);
                                String jsonString2 = gson.toJson(expiredDateList);
                                //UserDetails user1 = gson.fromJson(jsonString,UserDetails.class);
                                System.out.println(" ----------- List Offer Fragment getOffers listStores : "+jsonString);
                                System.out.println(" ----------- List Offer Fragment  getOffers expiredDateList : "+jsonString2);
                                for (int i = 0; i < expiredDateList.size(); i++) {
                                    // if (list.get(i).getDistance() <= REQUEST_RANGE_RADIUS)
                                    adapter.addItem(expiredDateList.get(i));
                                }

                                //set it into database
                                OffersController.insertOffers(list);

                                swipeRefreshLayout.setRefreshing(false);
                                loading = true;

                                mViewManager.showResult();

                                if (COUNT > adapter.getItemCount())
                                    REQUEST_PAGE++;
                                if (COUNT == 0  || adapter.getItemCount() == 0) {
                                    mViewManager.empty();
                                }



                                if (APP_DEBUG) {
                                    Log.e("count ", COUNT + " num = " + num);
                                }
                                getFilters();

                            }
                        }, 800);
                    } else {
                        (new Handler()).postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                RealmList<Offer> list = mOfferParser.getOffers();
                                List<Offer> expiredDateList = new ArrayList<>();
                                expiredDateList.clear();
                                Gson gson = new Gson();
                                for(int i=0;i<list.size();i++){
                                    Offer newOffer = list.get(i);
                                    if(DateUtils.checkDatesExpired(newOffer.getDate_end())){
                                        expiredDateList.add(newOffer);
                                    }
                                }
                                String jsonString = gson.toJson(list);
                                String jsonString2 = gson.toJson(expiredDateList);
                                //UserDetails user1 = gson.fromJson(jsonString,UserDetails.class);
                                System.out.println(" ----------- List Offer Fragment getOffers listStores : "+jsonString);
                                System.out.println(" ----------- List Offer Fragment  getOffers expiredDateList : "+jsonString2);
                                for (int i = 0; i < expiredDateList.size(); i++) {
                                    //if (list.get(i).getDistance() <=REQUEST_RANGE_RADIUS)
                                    adapter.addItem(expiredDateList.get(i));
                                }


                                //set it into database
                                OffersController.insertOffers(list);

                                swipeRefreshLayout.setRefreshing(false);
                                mViewManager.showResult();
                                loading = true;
                                if (COUNT > adapter.getItemCount())
                                    REQUEST_PAGE++;

                                if (COUNT == 0  || adapter.getItemCount() == 0) {
                                    mViewManager.empty();
                                }
                            }
                        }, 800);

                    }

                } catch (JSONException e) {
                    //send a rapport to support
                    if(APP_DEBUG)
                        e.printStackTrace();

                    if(adapter.getItemCount()==0)
                        mViewManager.error();


                    swipeRefreshLayout.setRefreshing(false);
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

                params.put("token", Utils.getToken(getActivity()));
                params.put("mac_adr", ServiceHandler.getMacAddr()  );

                params.put("limit", "30");
                params.put("offertype_id",num);

                if(store_id>0)
                    params.put("store_id", String.valueOf(store_id));

                if (APP_DEBUG) {
                    Log.e("ListStoreFragment", "  params getOffers :" + params.toString());
                }

                return params;
            }

        };


        request.setRetryPolicy(new DefaultRetryPolicy(SimpleRequest.TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(request);

    }

    @Override
    public void setFilterListRecycle(View view, int position, int num) {
        /*filterListLayout.setVisibility(View.GONE);
        offertype_id = String.valueOf(num);
        System.out.println("offertype_id :"+offertype_id);
        getfiltered(offertype_id);
        closeFilterTitle.setVisibility(View.VISIBLE);*/
    }

    @Override
    public void setChildList(List<MainOffers> childMainOffersList) {
        filterListView.setVisibility(View.INVISIBLE);
        childListRecycleView.setVisibility(View.VISIBLE);
        revertFilterTxt.setVisibility(View.VISIBLE);
        filterApplyLayout.setVisibility(View.VISIBLE);
        /*LinkedHashMap<String, String[]> thirdLevelChild = new LinkedHashMap<>();

        List<String[]> secondLevel = new ArrayList<>();

        List<LinkedHashMap<String, String[]>> data = new ArrayList<>();

        ThreeLevelListAdapter threeLevelListAdapterAdapter = new ThreeLevelListAdapter(this, parent, secondLevel, data);

        expandableListView.setAdapter();*/
        adapterChildFilter = new OfferChildListAdapter(childMainOffersList, this,getActivity());
        childListRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        childListRecycleView.setItemAnimator(new DefaultItemAnimator());
        childListRecycleView.setAdapter(adapterChildFilter);
        /*View footerView = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.footer_layout, null, false);
        childListRecycleView.addFooterView(footerView);*/
    }

    @Override
    public void onItemCheck(String offerTypeCheckList) {
        offerIdList.add(offerTypeCheckList);
            filterApplyLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemUncheck(String offerTypeCheckedLists) {
        offerIdList.remove(offerTypeCheckedLists);
        filterApplyLayout.setVisibility(View.GONE);

    }


    @Override
    public void onItemInnerCheck(String offerTypeCheckList) {
        System.out.println("offerTypeCheckList: "+offerTypeCheckList);
        offerInnerIdList.add(offerTypeCheckList);
        filterApplyLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemInnerUncheck(String offerTypeCheckedLists) {
        offerInnerIdList.remove(offerTypeCheckedLists);
        setFilterApplyBtnVisiblity();
    }


    public void setFilterApplyBtnVisiblity(){
        if(offerIdList!=null&&offerIdList.size()>0){
            filterApplyLayout.setVisibility(View.VISIBLE);
        }else if(offerInnerIdList!=null&&offerInnerIdList.size()>0){
            filterApplyLayout.setVisibility(View.VISIBLE);
        }   else {
            filterApplyLayout.setVisibility(View.GONE);
        }
    }
}
