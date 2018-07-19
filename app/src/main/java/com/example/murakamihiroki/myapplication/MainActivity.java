package com.example.murakamihiroki.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    //グローバル変数
    private SoundPool soundPool;
    private int soundOne;
    private int talk_flag = 0;
    private float shrinker = (float)1.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build();

        //効果音の設定↓
        soundPool = new SoundPool.Builder()
                .setAudioAttributes(audioAttributes)
                // ストリーム数に応じて
                .setMaxStreams(1)
                .build();
        // one.wav をロードしておく
        soundOne = soundPool.load(this, R.raw.pop, 1);
        // load が終わったか確認する場合
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                Log.d("debug","sampleId="+sampleId);
                Log.d("debug","status="+status);
            }
        });
        //効果音の設定↑

    }
    // アクティビティ
    //テキスト部分をタップしたときの振る舞い
    public void mainTalker(View view) {
        //フネさんのセリフはif分岐で実装↓
        if (talk_flag == 0) {
            ((TextView) findViewById(R.id.textView)).setText("どちら様でしょうか？");
            talk_flag++;
        }
        else if(talk_flag == 1){
            ((TextView) findViewById(R.id.textView)).setText("う〜ん、よくわからないわぁ");
            talk_flag++;

        }
        else if(talk_flag == 2){
            ((TextView) findViewById(R.id.textView)).setText("カツオはどこいったのかしら");
            talk_flag=0;

        }
    }

    //画像をタップしたときの振る舞い
    public void vanisher(View view) {
        shrinker = shrinker - (float)0.1;
        System.out.printf("%f", shrinker);
        ((ImageView) findViewById(R.id.imageView1)).setScaleX (shrinker);
        ((ImageView) findViewById(R.id.imageView1)).setScaleY (shrinker);
        double r = Math.random();
        if (r < 0.5) {
            ((TextView) findViewById(R.id.textView)).setText("あら？");
        }
        else {
            ((TextView) findViewById(R.id.textView)).setText("やめてくださいよ");
        }
        if (shrinker < (float)0.0) {
            ((TextView) findViewById(R.id.textView)).setText("じゃあね　　　- 完 -");
            //音を鳴らす
            soundPool.play(soundOne, 1f, 1f, 0, 0, 1);
        }
        else if(shrinker < (float)0.1){
            ((TextView) findViewById(R.id.textView)).setText("き、きえてしまう！");
        }
    }
}
