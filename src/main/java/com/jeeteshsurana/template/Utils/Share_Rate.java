package com.jeeteshsurana.template.Utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.jeeteshsurana.template.R;


public class Share_Rate {

    public static void Share(Activity activity) {
        String appPackageName=activity.getPackageName();
        Intent Share = new Intent(Intent.ACTION_SEND);
        Share.setType("text/plain");
        Share.putExtra(Intent.EXTRA_TEXT, activity.getResources().getString(R.string.app_name)+"\n\n"+"https://play.google.com/store/apps/details?id="+ appPackageName); /*change*/
        activity.startActivity(Intent.createChooser(Share, "Share via"));
    }

    public static void RatingOnPlayStore(Activity activity)
    {
        String appPackageName=activity.getPackageName();
        try {
            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }
}
