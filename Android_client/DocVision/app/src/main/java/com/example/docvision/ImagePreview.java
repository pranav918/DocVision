package com.example.docvision;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.tabs.TabLayout;

import java.nio.InvalidMarkException;
import java.util.HashMap;
import java.util.Map;

import static com.example.docvision.MainActivity.bitmap;

public class ImagePreview extends AppCompatActivity {


    Map<Integer, String> map;
    TabLayout tabLayout;
    SeekBar seek;
    FrameLayout fl;
    ImageView iv;
    Bitmap orig_bitmap;
    Bitmap processedBitmap;
    ProgressBar pb;
    boolean pbvisible = false;
    String filename;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_preview);
        tabLayout = findViewById(R.id.tablayout);
        seek = findViewById(R.id.seek);
        fl = findViewById(R.id.iv);
        iv = new ImageView(this);
        iv.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        fl.addView(iv);
        pb = findViewById(R.id.progress);
        pb.setVisibility(View.GONE);
        context = this;
        orig_bitmap = bitmap;
        processedBitmap = Bitmap.createBitmap(bitmap);

        filename = getalpnum(10) ;

        Connect connect = new Connect(context);

        connect.upload_image(orig_bitmap, filename);
        iv.setImageBitmap(bitmap);
        map = new HashMap<>();
        map.put(0, "original");
        map.put(1, "grey");
        map.put(2, "bw1");
        map.put(3, "bw2");

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pb.setVisibility(View.VISIBLE);
                pbvisible = true;
                int pos = tab.getPosition();
                String selection = map.get(pos);
                switch (selection) {
                    case "original":
                        processedBitmap = Bitmap.createBitmap(bitmap);
                        iv.setImageBitmap(processedBitmap);
                        pb.setVisibility(View.GONE);
                        pbvisible = false;
                        break;
                    case "grey":
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                processedBitmap = Helper.toGrey(bitmap);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        iv.setImageBitmap(processedBitmap);
                                        pb.setVisibility(View.GONE);
                                        pbvisible = false;
                                    }
                                });
                            }
                        }).start();

                        break;
                    case "bw1":
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                processedBitmap = Helper.threshold(bitmap);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        iv.setImageBitmap(processedBitmap);
                                        pb.setVisibility(View.GONE);
                                        pbvisible = false;
                                    }
                                });
                            }
                        }).start();

                        break;
                    case "bw2":
                        Helper.ada(filename, connect);
                        break;
                    case "OCR":
                        //Helper.ocr();
                        break;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pb.setVisibility(View.VISIBLE);
                pbvisible = true;
                int val = (progress - 10) * 5;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        processedBitmap = Helper.adjustBrightness(bitmap, val);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                iv.setImageBitmap(processedBitmap);
                                pb.setVisibility(View.GONE);
                                pbvisible = false;
                            }
                        });
                    }
                }).start();


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private String getalpnum(int n) {
        String AlphaNumericString = "0123456789" +
                "ABCDEFGHIJKLMNOPQRSUVWXYS" +
                "abcdefghijklmnopqrstuvwxyz";

        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            int index = (int) (AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }

        return sb.toString();

    }

    public void callback(String url) {
        if (pbvisible) {
            pb.setVisibility(View.GONE);
            pbvisible = false;

            Glide.with(context)
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(iv);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Glide.get(context).clearDiskCache();
                }
            }).start();
        }

    }
    public void textCallback(String text){
        TextView tv = new TextView(context);
        tv.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        tv.setText(text);
        fl.removeAllViews();
        fl.addView(tv);


    }

}