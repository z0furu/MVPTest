package com.mvplogin;

import android.content.Intent;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mvplogin.api.ApiClient;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnLogin;

    private ApiClient apiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiClient = ApiClient.getInstance();
        bindView();
        onClickListener();
    }

    private void bindView() {
        btnLogin = findViewById(R.id.btn_login);
    }

    private void onClickListener() {
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
//                CustomTabsIntent customTabsIntent = builder.build();
//                customTabsIntent.launchUrl(this,
//                        Uri.parse("http://github.com/login/oauth/authorize?client_id=3f0b1333267268d857e4&redirect_uri=lu://login"));

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://github.com/login/oauth/authorize?client_id=3f0b1333267268d857e4&redirect_uri=lu://login"));
                startActivity(intent);
                break;
        }
    }
}
