package ricardo.colormatch.activities;

/**
 * Created by Ricardo on 3/21/2016.
 * Code from https://github.com/tvbarthel/CameraColorPicker
 */

import ricardo.colormatch.R;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;


/**
 * A simple {@link android.support.v7.app.AppCompatActivity} for displaying the licenses of the open source projects used in this application.
 */
public class LicenseActivity extends AppCompatActivity {

    /**
     * The url of the license html page.
     */
    private static final String URL_LICENSES = "file:///android_asset/licenses.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_license);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final WebView webView = (WebView) findViewById(R.id.activity_license_web_view);
        webView.loadUrl(URL_LICENSES);
    }

}

