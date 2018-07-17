package com.voyager.nearbystores_v2.navigationdrawer;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.voyager.nearbystores_v2.R;
import com.voyager.nearbystores_v2.activities.AboutActivity;
import com.voyager.nearbystores_v2.activities.CategoriesActivity;
import com.voyager.nearbystores_v2.activities.FavoriteStoreActivity;
import com.voyager.nearbystores_v2.activities.ListUsersActivity;
import com.voyager.nearbystores_v2.activities.LoginActivity;
import com.voyager.nearbystores_v2.activities.MapStoresListActivity;
import com.voyager.nearbystores_v2.activities.ProfileActivity;
import com.voyager.nearbystores_v2.activities.SettingActivity;
import com.voyager.nearbystores_v2.activities.SplashActivity;
import com.voyager.nearbystores_v2.adapter.navigation.SimpleListAdapterNavDrawer;
import com.voyager.nearbystores_v2.appconfig.AppConfig;
import com.voyager.nearbystores_v2.classes.HeaderItem;
import com.voyager.nearbystores_v2.classes.Item;
import com.voyager.nearbystores_v2.classes.User;
import com.voyager.nearbystores_v2.controllers.categories.CategoryController;
import com.voyager.nearbystores_v2.controllers.sessions.SessionsController;
import com.voyager.nearbystores_v2.dtmessenger.socket.SocketService;
import com.voyager.nearbystores_v2.fragments.MainFragment;
import com.voyager.nearbystores_v2.network.VolleySingleton;

import net.steamcrafted.materialiconlib.MaterialDrawableBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class NavigationDrawerFragment extends Fragment implements SimpleListAdapterNavDrawer.ClickListener {


    private static class Menu{
        public static final int HOME_ID = 1;
        public static final int CAT_ID = 2;
        public static final int PEOPLE_AROUND_ME = 3;
        public static final int CHAT_LOGIN_ID = 4;
        public static final int FAV = 5;
        /*public static final int MY_EVENT = 6;*/
        public static final int EDIT = 7;
        public static final int ABOUT = 8;
        public static final int CREATE_STORE = 9;
        public static final int SETTING = 10;
        public static final int LOGOUT = 11;
        public static final int WEB_DASHBOARD = 12;
        public static final int MAP_STORES = 13;
    }

    private  static  final  int RESULT_SART_ACTIVITY = 1;
    public static final String PREF_FILE_NAME = "testpref";
    public static  final  String KEY_USER_LEARNED_DRAWER = "learned_user_drawer";
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private boolean mUserLearedLayout;
    private boolean mFromSaveInstanceState;
    private View containerView;


    private RecyclerView drawerList;
    private SimpleListAdapterNavDrawer adapter;


    private User user;
    //init request http
    private RequestQueue queue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        queue = VolleySingleton.getInstance(getActivity()).getRequestQueue();

        mUserLearedLayout = Boolean.valueOf(readFromPreferences(getActivity(),KEY_USER_LEARNED_DRAWER,"false")) ;
        if(savedInstanceState!=null){
            mFromSaveInstanceState = true;
        }

        if(SessionsController.isLogged())
             user = SessionsController.getSession().getUser();

    }

    public TextView Username;
    public TextView Email;


    Fragment frag;
    FragmentManager fm;


    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.navigation_drawer_content,container,false);



        rootView.setClickable(true);






        drawerList = (RecyclerView) rootView.findViewById(R.id.drawerLayout);
        drawerList.setVisibility(View.VISIBLE);

        adapter = new SimpleListAdapterNavDrawer(getActivity(),getData());

        drawerList.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        drawerList.setLayoutManager(mLayoutManager);
        drawerList.setAdapter(adapter);

        adapter.setClickListener(this);



        return rootView;

    }



    List<Item> listItems = Arrays.asList();

    public List<Item> getData(){

        listItems = new ArrayList<Item>();


        HeaderItem header_item = new HeaderItem();
        header_item.setName(getResources().getString(R.string.Home));
        header_item.setEmail(getResources().getString(R.string.your_email));
        header_item.setEnabled(true);



        Item webdashboard =  new Item();
        webdashboard.setName(getResources().getString(R.string.ManageThings));
        webdashboard.setIconDraw(MaterialDrawableBuilder.IconValue.WEB);
        webdashboard.setID(12);



        Item homeItem =  new Item();
        homeItem.setName(getResources().getString(R.string.Home));
        homeItem.setIconDraw(MaterialDrawableBuilder.IconValue.HOME);
        homeItem.setID(1);



        int categories_count = CategoryController.getArrayList().size()- AppConfig.TabsConfig.size();


        Item catItem = new Item();
        catItem.setName(getResources().getString(R.string.Categories)+" ("+categories_count+")");
        catItem.setIconDraw(MaterialDrawableBuilder.IconValue.FORMAT_LIST_BULLETED);
        catItem.setID(2);


        Item findNewCustomers = null;
        if(AppConfig.ENABLE_CHAT){
             findNewCustomers = new Item();
            if(SessionsController.isLogged()){
                //savesItem.setName(getResources().getString(R.string.Favoris)+" ("+bookmaeks_count+")");
                findNewCustomers.setName(getResources().getString(R.string.FindCustomers));
                findNewCustomers.setIconDraw(MaterialDrawableBuilder.IconValue.ACCOUNT_MULTIPLE_OUTLINE);
                findNewCustomers.setID(3);

            }else{
                //savesItem.setName(getResources().getString(R.string.Favoris)+" ("+bookmaeks_count+")");
                findNewCustomers.setName(getResources().getString(R.string.Login));
                findNewCustomers.setIconDraw(MaterialDrawableBuilder.IconValue.ACCOUNT_MULTIPLE_OUTLINE);
                findNewCustomers.setID(4);

            }
        }

        Item savesItem = new Item();
        //savesItem.setName(getResources().getString(R.string.Favoris)+" /*("+bookmaeks_count+")*/");
        savesItem.setName(getResources().getString(R.string.Favoris));
        savesItem.setIconDraw(MaterialDrawableBuilder.IconValue.BOOKMARK_CHECK);
        savesItem.setID(5);


        /*Item EventLikeItem =new Item();
        EventLikeItem.setName(getResources().getString(R.string.EventLike));
        EventLikeItem.setIconDraw(MaterialDrawableBuilder.IconValue.ALARM_CHECK);
        EventLikeItem.setID(6);*/

        Item editProdile =new Item();
        editProdile.setName(getResources().getString(R.string.editProfile));
        editProdile.setIconDraw(MaterialDrawableBuilder.IconValue.ACCOUNT);
        editProdile.setID(7);



        Item aboutItem =new Item();
        aboutItem.setName(getResources().getString(R.string.about));
        aboutItem.setIconDraw(MaterialDrawableBuilder.IconValue.INFORMATION_OUTLINE);
        aboutItem.setID(8);

        Item createStoreItem =new Item();
        createStoreItem.setName(getResources().getString(R.string.store_create_btn));
        createStoreItem.setIconDraw(MaterialDrawableBuilder.IconValue.PLUS_BOX);
        createStoreItem.setID(9);

        Item settingItem =new Item();
        settingItem.setName(getResources().getString(R.string.Settings));
        settingItem.setIconDraw(MaterialDrawableBuilder.IconValue.SETTINGS);
        settingItem.setID(10);



        Item mapStoresItem =new Item();
        mapStoresItem.setName(getResources().getString(R.string.MapStoresMenu));
        mapStoresItem.setIconDraw(MaterialDrawableBuilder.IconValue.MAP_MARKER_RADIUS);
        mapStoresItem.setID(13);



        if(header_item.isEnabled())
            listItems.add(header_item);

        //HOME
        if(homeItem.isEnabled())
            listItems.add(homeItem);

        //Categories
        if(catItem.isEnabled())
            listItems.add(catItem);

        listItems.add(mapStoresItem);


        //People Around Me
        if(true){
            if(findNewCustomers!=null)
                listItems.add(findNewCustomers);
        }

        //My Favourite
        if(savesItem.isEnabled())
            listItems.add(savesItem);

        // Event Liked
        /*if(EventLikeItem.isEnabled())
            listItems.add(EventLikeItem);*/

        /*if(createStoreItem.isEnabled())
            listItems.add(createStoreItem);*/



        //Edit Profile
        if(SessionsController.isLogged()){

            Item logout =new Item();
            //savesItem.setName(getResources().getString(R.string.Favoris)+" /*("+bookmaeks_count+")*/");
            logout.setName(getResources().getString(R.string.Logout));
            logout.setIconDraw(MaterialDrawableBuilder.IconValue.LOGOUT);
            logout.setID(11);

            listItems.add(editProdile);
            listItems.add(logout);

        }

        //Settings
        if(AppConfig.ENABLE_WEB_DASHBOARD){
            listItems.add(webdashboard);
        }


        //About US
        if(aboutItem.isEnabled())
            listItems.add(aboutItem);



        //Settings
        if(settingItem.isEnabled())
            listItems.add(settingItem);







        return  listItems;
    }


    public static void saveToPreferences(Context context,String preferenceName,String preferenceValue){

        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(preferenceName, preferenceValue);
        edit.apply();

    }

    public static String readFromPreferences(Context context,String preferenceName,String defaultValue){

        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName,defaultValue);

    }


    public void setUp(int FragId,DrawerLayout drawerlayout, final Toolbar toolbar){

        containerView = getView().findViewById(FragId);
        mDrawerLayout = drawerlayout;

        //mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);

        mActionBarDrawerToggle = new ActionBarDrawerToggle(
                getActivity(),
                drawerlayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close
                ){
            @Override
            public void onDrawerOpened(View drawerView) {

                super.onDrawerOpened(drawerView);
                if(!mUserLearedLayout){
                    mUserLearedLayout = true;
                    saveToPreferences(getActivity(),KEY_USER_LEARNED_DRAWER,mUserLearedLayout+"");
                }


                getActivity().invalidateOptionsMenu();

            }

            @Override
            public void onDrawerClosed(View drawerView) {

                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();

            }



            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);

            }
        };

        if(!mUserLearedLayout && !mFromSaveInstanceState){

            mDrawerLayout.openDrawer(containerView);
        }



        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mActionBarDrawerToggle.syncState();

            }
        });

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==INT_CHAT_BOX){

            adapter.getData().get(1).setNotify(0);
            adapter.update(1,adapter.getData().get(1));

        }
    }

    public static int INT_CHAT_BOX = 5;


    @Override
    public void itemClicked(View view, int position) {


        MainFragment mf = (MainFragment)getFragmentManager().findFragmentByTag(MainFragment.TAG);

        Item item = adapter.getData().get(position);
        if(item instanceof Item){
            switch (item.getID()){
                case Menu.HOME_ID:

                    if(mDrawerLayout!=null)
                        mDrawerLayout.closeDrawers();

                        mf.setCurrentFragment(0);

                    break;
                case Menu.CAT_ID:

                    if(mDrawerLayout!=null)
                    mDrawerLayout.closeDrawers();

                    startActivity(new Intent(getActivity(), CategoriesActivity.class));
                    getActivity().overridePendingTransition(R.anim.lefttoright_enter, R.anim.lefttoright_exit);


                    break;
                case Menu.PEOPLE_AROUND_ME:

                    if(SessionsController.isLogged()) {
                        startActivity(new Intent(getActivity(), ListUsersActivity.class));
                        getActivity().overridePendingTransition(R.anim.lefttoright_enter, R.anim.lefttoright_exit);
                    }

                    break;
                case Menu.CHAT_LOGIN_ID:

                    if(!SessionsController.isLogged()) {
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                    }

                    break;
                case Menu.FAV:

                    startActivity( new Intent(getActivity(), FavoriteStoreActivity.class));
                    getActivity().overridePendingTransition(R.anim.lefttoright_enter, R.anim.lefttoright_exit);

                    break;
                /*case Menu.MY_EVENT:

                    startActivity( new Intent(getActivity(), EventLikedActivity.class));
                    getActivity().overridePendingTransition(R.anim.lefttoright_enter, R.anim.lefttoright_exit);

                    break;*/
                case Menu.EDIT:

                    if(mDrawerLayout!=null)
                        mDrawerLayout.closeDrawers();

                    startActivity(new Intent(getActivity(), ProfileActivity.class));

                    break;
                case Menu.ABOUT:

                    if(mDrawerLayout!=null)
                        mDrawerLayout.closeDrawers();

                    startActivity( new Intent(getActivity(), AboutActivity.class));
                    getActivity().overridePendingTransition(R.anim.lefttoright_enter, R.anim.lefttoright_exit);

                    break;
                case Menu.CREATE_STORE:
                    break;
                case Menu.SETTING:

                    if(mDrawerLayout!=null)
                        mDrawerLayout.closeDrawers();

                    startActivity(new Intent(getActivity(), SettingActivity.class));
                    getActivity().overridePendingTransition(R.anim.lefttoright_enter, R.anim.lefttoright_exit);

                    break;
                case Menu.MAP_STORES:

//                    if(mDrawerLayout!=null)
//                        mDrawerLayout.closeDrawers();

                    startActivity(new Intent(getActivity(), MapStoresListActivity.class));
                    getActivity().overridePendingTransition(R.anim.lefttoright_enter, R.anim.lefttoright_exit);

                    break;
                case Menu.LOGOUT:

                    SessionsController.logOut();
                    getActivity().stopService(new Intent(getActivity(), SocketService.class));
                    getActivity().finish();
                    startActivity(new Intent(getActivity(), SplashActivity.class));

                    break;

                case Menu.WEB_DASHBOARD:

                    if(!AppConfig.ENABLE_WEB_DASHBOARD)
                        break;

                    if(mDrawerLayout!=null)
                        mDrawerLayout.closeDrawers();

                    if(SessionsController.isLogged()){

                        String url = AppConfig.BASE_URL+"/webdashboard/";
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);

                    }

                    String url = AppConfig.BASE_URL+"/webdashboard/";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);

                    break;


            }
        }


    }







}
