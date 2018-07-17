package com.voyager.nearbystores_v2.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.voyager.nearbystores_v2.R;


/**
 * Created by Amine on 9/13/2016.
 */

public class MessageDialog {


    private Dialog dialog;

    private static MessageDialog instance;

    public static MessageDialog newDialog(Context context){
        instance = new MessageDialog(context);
        return instance;
    }

    public boolean isShowen(){


        if(instance!=null && dialog!=null && dialog.isShowing()){
            return true;
        }


        return false;
    }

    public MessageDialog(Context context){
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_msg_layout);
        dialog.setCancelable(false);


    }

    public MessageDialog setContent(String mesg){

        TextView mesgBox = (TextView) dialog.findViewById(R.id.msgbox);

        mesgBox.setText(mesg);
        return instance;

    }



    public MessageDialog onOkClick(View.OnClickListener event){
        Button ok = (Button) dialog.findViewById(R.id.ok);

        ok.setText(Translator.print("OK",null));
        ok.setOnClickListener(event);
        return instance;
    }

    public MessageDialog onCancelClick(View.OnClickListener event){
        Button cancel = (Button) dialog.findViewById(R.id.cancel);
        cancel.setOnClickListener(event);

        cancel.setText(Translator.print("Cancel",null));
        return instance;
    }

    public static MessageDialog setTextColor(int  color){
        if(instance!=null){
           TextView text = ((TextView)instance.dialog.findViewById(R.id.msgbox));
            text.setTextColor(color);
        }

        return instance;
    }

    public static MessageDialog btnCancelText(String txt){
        if(instance!=null){
            ((Button)instance.dialog.findViewById(R.id.cancel)).setText(txt);
        }

        return instance;
    }

    public static MessageDialog btnOkText(String txt){
        if(instance!=null){
            ((Button)instance.dialog.findViewById(R.id.ok)).setText(txt);
        }

        return instance;
    }

    public void show(){

        if(instance!=null)
            dialog.show();
    }

    public static MessageDialog getInstance(){
        return instance;
    }

    public void hide(){

        if(instance!=null) {
            dialog.dismiss();
        }
    }

}
