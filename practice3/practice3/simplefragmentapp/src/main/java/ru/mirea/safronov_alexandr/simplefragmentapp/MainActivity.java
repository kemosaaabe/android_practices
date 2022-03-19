package ru.mirea.safronov_alexandr.simplefragmentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebViewFragment;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {
    Fragment fragment1;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment1 = new CalculateFragment();

    }

    public void onClickFragment1(View view) {
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment1).commit();
    }
    public void onClick(View view) {
        getFragmentManager().beginTransaction()
                .add(R.id.fragmentContainer, new CustomWebViewFragment())
                .commit();

    }
    public static class CustomWebViewFragment extends WebViewFragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setRetainInstance(true);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View result= super.onCreateView(inflater, container, savedInstanceState);
            getWebView().getSettings().setJavaScriptEnabled(true);
            getWebView().getSettings().setSupportZoom(true);
            getWebView().getSettings().setBuiltInZoomControls(true);
            getWebView().loadUrl("http://stackoverflow.com");
            return(result);
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
        }
    }
}