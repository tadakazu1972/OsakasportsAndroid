package tadakazu1972.osakasports;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * Created by tadakazu on 2017/08/06.
 */

public class EventActivity extends AppCompatActivity {

    private EventActivity mActivity = null;
    private String id = null;
    private TextView eventName = null;
    private String name = null;
    private TextView eventDate = null;
    private String date = null;
    private TextView eventTime = null;
    private String time = null;
    private TextView eventFacility = null;
    private String facility = null;
    private TextView eventDescription = null;
    private TextView eventSubmit = null;
    private TextView eventFee = null;
    private TextView eventTarget = null;
    private TextView eventStation = null;
    private TextView eventAddress = null;
    private String address = null;
    private TextView eventQuestion = null;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        mActivity = this;

        setContentView(R.layout.activity_event);

        //前ページから受け取り処理
        Intent intent = getIntent();
        //id
        id = intent.getStringExtra("id");
        //イベント名
        eventName = (TextView)findViewById(R.id.eventName);
        eventName.setText(intent.getStringExtra("name"));
        name = intent.getStringExtra("name");
        //開催日
        eventDate = (TextView)findViewById(R.id.eventDate);
        eventDate.setText(intent.getStringExtra("date"));
        date = intent.getStringExtra("date");
        //時間
        eventTime = (TextView)findViewById(R.id.eventTime);
        eventTime.setText(intent.getStringExtra("time"));
        time = intent.getStringExtra("time");
        //場所
        eventFacility = (TextView)findViewById(R.id.eventFacility);
        eventFacility.setText(intent.getStringExtra("facility"));
        facility = intent.getStringExtra("facility");
        //内容
        eventDescription = (TextView)findViewById(R.id.eventDescription);
        eventDescription.setText(intent.getStringExtra("description"));
        //申込方法
        eventSubmit = (TextView)findViewById(R.id.eventSubmit);
        eventSubmit.setText(intent.getStringExtra("submit"));
        //参加費
        eventFee = (TextView)findViewById(R.id.eventFee);
        eventFee.setText(intent.getStringExtra("fee"));
        //参加対象
        eventTarget = (TextView)findViewById(R.id.eventTarget);
        eventTarget.setText(intent.getStringExtra("target"));
        //最寄駅
        eventStation = (TextView)findViewById(R.id.eventStation);
        eventStation.setText(intent.getStringExtra("station"));
        //所在地
        eventAddress = (TextView)findViewById(R.id.eventAddress);
        eventAddress.setText(intent.getStringExtra("address"));
        address = intent.getStringExtra("address");
        //問い合わせ
        eventQuestion = (TextView)findViewById(R.id.eventQuestion);
        eventQuestion.setText(intent.getStringExtra("question"));

        //ボタン初期化
        initButtons();
    }

    public void initButtons(){
        findViewById(R.id.btnCalender).setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                String url = "http://www.google.com/calendar/event?action=TEMPLATE&text=" + name + "&dates=" + date + "&location=" + address;
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnTwitter).setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                String url = "https://twitter.com/share?url=https://osakasports.azurewebsites.net/event.html?id="+id+"&text=【大阪市オータムチャレンジスポーツ2017】" + name + "/" + date + "/" + time;
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnLine).setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                String url = "http://line.me/R/msg/text/?https://osakasports.azurewebsites.net/event.html?id="+id+"【大阪市オータムチャレンジスポーツ2017】" + name + "/" + date + "/" + time;
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnFacebook).setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                String url = "https://www.facebook.com/sharer/sharer.php?u=https://osakasports.azurewebsites.net/event.html?id=" + id;
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnHome).setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(mActivity, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}