package com.example.tp6ex3;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;


public class LoginFragment extends Fragment {

    EditText email, password;
    Button login_button;
    FirebaseAuth mAuth;
    FirebaseUser user;

    public LoginFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        user = FirebaseAuth.getInstance().getCurrentUser();
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);

        if (user != null) {
            startActivity(new Intent(getActivity(), MainActivity.class));
        } else {

            login_button = view.findViewById(R.id.login_button);
            login_button.setOnClickListener(v -> {

                String email_text = email.getText().toString();
                String password_text = password.getText().toString();

                if (email_text.isEmpty() || password_text.isEmpty()) {
                    YoYo.with(Techniques.Bounce)
                            .duration(700)
                            .repeat(0)
                            .playOn(view.findViewById(R.id.login_button));

                    Toast.makeText(getContext(), "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {

                    login_button.setText(getResources().getString(R.string.logging_in));

                    mAuth = FirebaseAuth.getInstance();
                    mAuth.signInWithEmailAndPassword(email_text, password_text).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {

                            user = mAuth.getCurrentUser();
                            startActivity(new Intent(getActivity(), MainActivity.class));
                            login_button.setText(getResources().getString(R.string.login));

                            if (getActivity() != null) {
                                getActivity().finish();
                            }

                        } else {
                            YoYo.with(Techniques.Bounce)
                                    .duration(700)
                                    .repeat(0)
                                    .playOn(view.findViewById(R.id.login_button));

                            if (task.getException() != null) {
                                Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            login_button.setText(getResources().getString(R.string.login));
                        }
                    });
                }
            });
        }
    }
}