package com.jeeteshsurana.template.Fragments.UserAccess;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jeeteshsurana.template.DialogBoxes.DialogBoxes;
import com.jeeteshsurana.template.R;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import java.util.List;


public class ForgetPassword extends Fragment implements Validator.ValidationListener {

    @NotEmpty
    @Email
    TextInputEditText editText_forgetPassword;

    Button button_submit_FG;
    String Email;
    Dialog dialog;

    private Validator validator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.forget_password, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText_forgetPassword =  view.findViewById(R.id.FP_editxt);

        button_submit_FG = view.findViewById(R.id.FP_btn);
        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        button_submit_FG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
            }
        });
    }

    @Override
    public void onValidationSucceeded() {
        dialog= DialogBoxes.waitingDialog(getActivity());
        dialog.show();
        Email = editText_forgetPassword.getText().toString().trim();

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
