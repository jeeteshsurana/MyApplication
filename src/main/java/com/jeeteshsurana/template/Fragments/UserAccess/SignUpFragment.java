package com.jeeteshsurana.template.Fragments.UserAccess;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jeeteshsurana.template.DialogBoxes.DialogBoxes;
import com.jeeteshsurana.template.R;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SignUpFragment extends Fragment implements Validator.ValidationListener {


    Dialog dialog;

    @NotEmpty
    @BindView(R.id.FirstName)
    TextInputEditText FirstName;

    @NotEmpty
    @BindView(R.id.LastName)
    TextInputEditText LastName;

    @Email
    @BindView(R.id.EmailAddress)
    TextInputEditText EmailAddress;

    @NotEmpty
    @BindView(R.id.Mobile)
    TextInputEditText Mobile;

    @NotEmpty
    @Password(min = 2, scheme = Password.Scheme.ALPHA_NUMERIC_MIXED_CASE_SYMBOLS)
    @BindView(R.id.password)
    TextInputEditText password;

    @NotEmpty
    @ConfirmPassword
    @BindView(R.id.ConformPassword)
    TextInputEditText ConformPassword;

    @BindView(R.id.reg_SignIn)
    TextView regSignIn;

    @BindView(R.id.SignUp)
    TextView SignUp;

    Unbinder unbinder;
    @BindView(R.id.reg_show_Password)
    ImageView regShowPassword;
    @BindView(R.id.reg_showConfirm_Password)
    ImageView regShowConfirmPassword;

    public View viewLayout;
    public Validator validator;

    public Boolean Show = true;
    public Boolean Show2 = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewLayout = inflater.inflate(R.layout.sign_up, container, false);

        unbinder = ButterKnife.bind(this, viewLayout);
        return viewLayout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        validator = new Validator(this);
        validator.setValidationListener(this);
//        progressDialog = new ProgressDialog(getActivity());

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.reg_show_Password, R.id.reg_showConfirm_Password, R.id.reg_SignIn, R.id.SignUp})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.reg_SignIn:
                getFragmentManager().popBackStack();
                break;

            case R.id.SignUp:
                validator.validate();
                break;

            case R.id.reg_show_Password:
                if (Show) {
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    Show = false;
                    regShowPassword.setBackgroundResource(R.drawable.ic_visibility_black_24dp);
                } else {
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    Show = true;
                    regShowPassword.setBackgroundResource(R.drawable.ic_visibility_off_black_24dp);
                }
                break;
            case R.id.reg_showConfirm_Password:
                if (Show2) {
                    ConformPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    Show2 = false;
                    regShowConfirmPassword.setBackgroundResource(R.drawable.ic_visibility_black_24dp);
                } else {
                    ConformPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    Show2 = true;
                    regShowConfirmPassword.setBackgroundResource(R.drawable.ic_visibility_off_black_24dp);
                }
                break;
        }
    }

    @Override
    public void onValidationSucceeded() {
        String first = FirstName.getText().toString().trim();
        String last = LastName.getText().toString().trim();
        String email = EmailAddress.getText().toString().trim();
        String mobile = Mobile.getText().toString().trim();
        String pass = password.getText().toString().trim();
        String ConFirmPass = ConformPassword.getText().toString().trim();

        dialog= DialogBoxes.waitingDialog(getActivity());
        dialog.show();
//        if (MyFirebaseInstanceIDService.refreshToken != null) {
//            UserDetail userDetail = new UserDetail(first, last, email, mobile, pass, ConFirmPass, "Android", MyFirebaseInstanceIDService.refreshToken);
//            DataTransfer.SignUp(getActivity(), userDetail, dialog, getActivity().getSupportFragmentManager());
//        } else {
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    MyFirebaseInstanceIDService myFirebaseInstanceIDService = new MyFirebaseInstanceIDService();
//                    myFirebaseInstanceIDService.onTokenRefresh();
//                    onValidationSucceeded();
//                }
//            }, 1000);
//        }
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(getActivity());

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
            }
        }
    }

}
