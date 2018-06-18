package com.jeeteshsurana.template.Fragments.UserAccess;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jeeteshsurana.template.DialogBoxes.DialogBoxes;
import com.jeeteshsurana.template.R;
import com.jeeteshsurana.template.Utils.AutoLogin;
import com.jeeteshsurana.template.Utils.FragmentHandler;
import com.jeeteshsurana.template.Utils.InternetConnection;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class LoginFragment extends Fragment implements Validator.ValidationListener {

    Dialog dialog;

    @NotEmpty
    @BindView(R.id.log_EmailAddress)
    TextInputEditText logEmailAddress;

    @NotEmpty
    @BindView(R.id.log_password)
    TextInputEditText logPassword;

    @BindView(R.id.SignIn)
    Button SignIn;

    @BindView(R.id.SignUp)
    TextView SignUp;

    @BindView(R.id.showPassword)
    ImageView showPassword;

    @BindView(R.id.forget_password)
    TextView forgetPassword;


    public Boolean Show = true;


    Unbinder unbinder;
    View viewLayout;

    static String TAG = "LoginFragment";

    private Validator validator;
    int RC_SIGN_IN;

    String DeviceType = "Android";
    String DeviceId;
    private Context context;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewLayout = inflater.inflate(R.layout.login_screen, container, false);
        unbinder = ButterKnife.bind(this, viewLayout);
        return viewLayout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        AutoLogin.showHashKey(getContext());
        if (!InternetConnection.checkConnection(getActivity())) {
            DialogBoxes.NoInternetConnection(getActivity());
        }
        if (AutoLogin.GetData(getActivity())) {
            Log.d(TAG, "AutoLogin And Run LoginScript ");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.showPassword, R.id.forget_password, R.id.SignIn, R.id.SignUp})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.SignIn:
                validator.validate();
                break;

            case R.id.SignUp:
                FragmentHandler.replaceFragment(new SignUpFragment(), R.id.container3, getActivity().getSupportFragmentManager());
                break;

            case R.id.showPassword:
                if (Show) {
                    logPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    Show = false;
                    showPassword.setBackgroundResource(R.drawable.ic_visibility_black_24dp);
                } else {
                    logPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    Show = true;
                    showPassword.setBackgroundResource(R.drawable.ic_visibility_off_black_24dp);
                }
                break;

            case R.id.forget_password:
                FragmentHandler.replaceFragment(new ForgetPassword(), R.id.container3, getActivity().getSupportFragmentManager());
                break;

        }
    }

    @Override
    public void onValidationSucceeded() {
        dialog = DialogBoxes.waitingDialog(getActivity());
        dialog.show();
        String email = logEmailAddress.getText().toString().trim();
        String password = logPassword.getText().toString().trim();

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {

        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(context);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            }

        }

    }


    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: Clear AccessToken And Profile Tracker");
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

}
