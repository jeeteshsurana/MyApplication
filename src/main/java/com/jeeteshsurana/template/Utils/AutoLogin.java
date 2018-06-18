package com.jeeteshsurana.template.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class AutoLogin {


    private static String TAG = "SharedPreference";
    public static String Email;
    public static String Password;

    public static void SaveLoginData(Activity activity, String Email, String password) {
        Log.d(TAG, "SaveLoginData: ");

        SharedPreferences settings = activity.getSharedPreferences("Login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        editor.putBoolean("SaveLogin", true);
        editor.putString("Email", Email);
        editor.putString("Password", password);
        editor.apply();

    }

    public static Boolean GetData(Activity activity) {
        Log.d(TAG, "GetLoginData: ");
        Boolean flag = false;

        SharedPreferences settings = null;
        try {
            settings = activity.getSharedPreferences("Login", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();


            Boolean saveGuest = settings.getBoolean("SaveLogin", false);

            Email = settings.getString("Email", "");

            Password = settings.getString("Password", "");


            if (saveGuest) {
                if (!Email.isEmpty() && !Password.isEmpty()) {
                    flag = true;
                }
            } else {
                flag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static Boolean Erase(Activity activity) {

        Log.d(TAG, "EraseLoginData: ");
        SharedPreferences settings = activity.getSharedPreferences("Login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.remove("SaveLogin");
        editor.remove("Email");
        editor.remove("Password");
        editor.clear();
        editor.apply();
        return true;
    }

    public static void showHashKey(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getApplicationContext().getPackageName(),
                    PackageManager.GET_SIGNATURES);
            for (android.content.pm.Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());

                String sign = Base64.encodeToString(md.digest(), Base64.DEFAULT);
                Log.e("KeyHash:", sign);
                //  Toast.makeText(getApplicationContext(),sign,     Toast.LENGTH_LONG).show();
            }
            Log.d("KeyHash:", "****------------***");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
