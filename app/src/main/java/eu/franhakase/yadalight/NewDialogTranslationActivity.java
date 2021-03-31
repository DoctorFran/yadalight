package eu.franhakase.yadalight;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import java.util.Locale;
import java.util.Objects;

public class NewDialogTranslationActivity extends Activity
{
    String Original = "";
    boolean bResume = false;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_translation_dialog);
        this.setFinishOnTouchOutside(true);
        //kn = new KanaToRomaji();
        LinearLayout ll = findViewById(R.id.dlMain);
        ll.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_rounded_rectangle));
        getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        WindowManager.LayoutParams a = getWindow().getAttributes();
        a.gravity = Gravity.TOP;
        getWindow().setAttributes(a);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        if(hasFocus && !bResume)
        {
            bResume = true;
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = clipboard.getPrimaryClip();
            if(   clip != null && clip.getItemCount() > 0 && clip.getItemAt(0).getText() != null)
            {
                Original = clip.getItemAt(0).getText().toString();
                String url = String.format(Locale.getDefault(), "https://www.deepl.com/translator#auto/en/%s", Uri.encode(clip.getItemAt(0).getText().toString()));
                WebView wvMain = findViewById(R.id.wvMain);
                wvMain.getSettings().setJavaScriptEnabled(true);
                wvMain.loadUrl(Objects.requireNonNull(url));
            }

        }
        super.onWindowFocusChanged(hasFocus);
    }



}