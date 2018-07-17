package com.voyager.nearbystores_v2.activities;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.voyager.nearbystores_v2.R;
import com.voyager.nearbystores_v2.appconfig.Constances;
import com.voyager.nearbystores_v2.utils.Utils;

public class AboutActivity extends AppCompatActivity {

    Toolbar toolbar;
    private String TAG = ".AboutActivity";
    private TextView app_name , version , description ,mail ,verion_content , description_content  ,mail_content,phone,phone_content;
    private TextView APP_TITLE_VIEW = null;
    private TextView APP_DESC_VIEW = null;

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        //INSTANCIATE TEXTVIEW
        version = (TextView) findViewById(R.id.about_app_version);
        description = (TextView) findViewById(R.id.about_description);
        mail = (TextView) findViewById(R.id.about_mail);
        //INSTANCIATE EDITTEXT
        verion_content = (TextView) findViewById(R.id.about_version);
        description_content = (TextView) findViewById(R.id.about_description_content);
        mail_content = (TextView) findViewById(R.id.about_mail_content);
        phone = (TextView) findViewById(R.id.about_phone);
        phone_content = (TextView) findViewById(R.id.about_phone_content);


        //SET FONT TO THE TEXTVIEW
        // Utils.setFont(this, app_name, "");
        Utils.setFont(this,description,"");
        Utils.setFont(this, version, "");
        Utils.setFont(this, mail, "");
        Utils.setFont(this, verion_content, "");
        Utils.setFont(this, description_content, "");
        Utils.setFont(this, mail_content, "");
        Utils.setFont(this, phone, "");
        Utils.setFont(this, phone_content, "");

        initToolbar();
        APP_TITLE_VIEW.setText(getResources().getString(R.string.app_name));
        APP_TITLE_VIEW.setVisibility(View.VISIBLE);


        mail_content.setText(Constances.initConfig.AppInfos.ADDRESS_CONTACT);


        try {
            verion_content.setText(getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(),0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        initToolbar();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if(item.getItemId() == android.R.id.home){
                finish();
            overridePendingTransition(R.anim.righttoleft_enter,R.anim.righttoleft_exit);
        }

        return super.onOptionsItemSelected(item);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.righttoleft_enter, R.anim.righttoleft_exit);
    }

    public void initToolbar(){

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.app_bar);
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
        Utils.setFont(this, APP_DESC_VIEW, "SourceSansPro-Black.otf");
        Utils.setFont(this, APP_TITLE_VIEW , "SourceSansPro-Black.otf");




        String about = Constances.initConfig.AppInfos.ABOUT_CONTENT;
        APP_TITLE_VIEW.setText(R.string.About_us);
        description_content.setText(about);

        APP_DESC_VIEW.setVisibility(View.GONE);

    }



}
