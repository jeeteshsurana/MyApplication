package com.jeeteshsurana.template.DialogBoxes;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jeeteshsurana.template.R;
import com.jeeteshsurana.template.Utils.InternetConnection;

public class DialogBoxes {
    public static Dialog dialog;

    public static void NoInternetConnection(final Activity activity) {
        dialog = new Dialog(activity, R.style.AppTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.no_internet_connection);
        dialog.show();
        Button Retry = dialog.findViewById(R.id.NI_Retry);
        TextView Skip = dialog.findViewById(R.id.Skip);

        Retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (InternetConnection.checkConnection(activity)) {
                    dialog.dismiss();
                } else {
                    Toast.makeText(activity, "Internet Connection not Available", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Toast.makeText(activity, "Without Internet Connection Application not working properly", Toast.LENGTH_SHORT).show();
            }
        });

    }



    public static Dialog waitingDialog(Activity activity) {
        dialog = new Dialog(activity, R.style.AppTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.waiting);
        return dialog;
    }

}
