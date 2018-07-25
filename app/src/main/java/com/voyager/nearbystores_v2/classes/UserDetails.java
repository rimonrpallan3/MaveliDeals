package com.voyager.nearbystores_v2.classes;

import android.os.Parcel;
import android.os.Parcelable;

import com.voyager.nearbystores_v2.activities.signuppage.model.IUserDetails;

/**
 * Created by User on 23-Jul-18.
 */

public class UserDetails implements Parcelable,IUserDetails {
    String name;
    String password;
    String email;
    String auth;
    String phone;
    int success;

    public UserDetails() {
    }

    public UserDetails(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserDetails(String name, String password, String email, String auth, String phone) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.auth = auth;
        this.phone = phone;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getTel() {
        return phone;
    }

    public void setTel(String phone) {
        this.phone = phone;
    }


    @Override
    public int validateUserDetails(String FullName, String email, String mobNo,String pswd, String confirmPswd, Boolean termsAndCondCheck) {
        if (FullName.trim().length()==0||
                pswd.trim().length()==0||
                confirmPswd.trim().length()==0||
                email.trim().length()==0||
                mobNo.trim().length()==0||
                termsAndCondCheck.toString().length()==0){
            {
                return -1;
            }
        }else {
            for (int i = 0; i < FullName.trim().length(); i++) {
                char charAt2 = FullName.trim().charAt(i);
                if (!Character.isLetter(charAt2)) {
                    return -2;
                }
            }
            for (int i = 0; i < password.trim().length(); i++) {
                String charAt2 = password.trim().toString();
                if (charAt2==null) {
                    return -3;
                }
            }

            if(!password.equals(confirmPswd)){
                return -4;
            }
            for (int i = 0; i < email.trim().length(); i++) {
                String charAt2 = email.trim().toString();
                if (charAt2==null) {
                    return -5;
                }
            }
            for (int i = 0; i < mobNo.trim().length(); i++) {
                String charAt2 = mobNo.trim().toString();
                if (charAt2==null) {
                    return -6;
                }
            }
                if (!termsAndCondCheck) {
                    return -8;
                }

        }
        return 0;
    }

    @Override
    public int validateRegisterResponseError(int success) {
        if(success!=0){
            //if there is no error message then it means that data response is correct.
            return -9;
        }
        return 0;
    }

    @Override
    public int checkUserValidity(String name, String passwd) {
        if (email==null||passwd==null||!email.equals(getEmail())||!passwd.equals(getPassword())){
            return -1;
        }
        return 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.password);
        dest.writeString(this.email);
        dest.writeString(this.auth);
        dest.writeString(this.phone);
        dest.writeInt(this.success);
    }

    protected UserDetails(Parcel in) {
        this.name = in.readString();
        this.password = in.readString();
        this.email = in.readString();
        this.auth = in.readString();
        this.phone = in.readString();
        this.success = in.readInt();
    }

    public static final Creator<UserDetails> CREATOR = new Creator<UserDetails>() {
        @Override
        public UserDetails createFromParcel(Parcel source) {
            return new UserDetails(source);
        }

        @Override
        public UserDetails[] newArray(int size) {
            return new UserDetails[size];
        }
    };
}
