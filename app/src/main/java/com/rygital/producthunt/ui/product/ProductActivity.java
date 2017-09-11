package com.rygital.producthunt.ui.product;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rygital.producthunt.BaseApp;
import com.rygital.producthunt.R;

public class ProductActivity extends BaseApp {
    public static final String EXTRA_SCR = "screenshot_EXTRA";
    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_DESC = "description";
    public static final String EXTRA_URL = "url";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        ImageView screenshot = (ImageView) findViewById(R.id.screenshot);
        TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
        TextView tvFullDesc = (TextView) findViewById(R.id.tvFullDesc);
        Button btnGetIt = (Button) findViewById(R.id.btnGetIt);

        final Intent intent = getIntent();
        if(intent != null) {
            Glide.with(this)
                    .load(intent.getStringExtra(EXTRA_SCR))
                    .into(screenshot);

            tvTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            tvFullDesc.setText(intent.getStringExtra(EXTRA_DESC));

            btnGetIt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(intent.getStringExtra(EXTRA_URL))));
                }
            });
        }
    }
}
