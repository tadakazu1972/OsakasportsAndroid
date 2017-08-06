package tadakazu1972.osakasports;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


/**
 * Created by tadakazu on 2017/08/06.
 */

public class EventActivity extends AppCompatActivity {

    private TextView eventName = null;
    private TextView eventDate = null;
    private TextView eventTime = null;
    private TextView eventFacility = null;
    private TextView eventDescription = null;
    private TextView eventSubmit = null;
    private TextView eventFee = null;
    private TextView eventTarget = null;
    private TextView eventStation = null;
    private TextView eventAddress = null;
    private TextView eventQuestion = null;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_event);

        //前ページから受け取り処理
        Intent intent = getIntent();
        //イベント名
        eventName = (TextView)findViewById(R.id.eventName);
        eventName.setText(intent.getStringExtra("name"));
        //開催日
        eventDate = (TextView)findViewById(R.id.eventDate);
        eventDate.setText(intent.getStringExtra("date"));
        //時間
        eventTime = (TextView)findViewById(R.id.eventTime);
        eventTime.setText(intent.getStringExtra("time"));
        //場所
        eventFacility = (TextView)findViewById(R.id.eventFacility);
        eventFacility.setText(intent.getStringExtra("facility"));
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
        //問い合わせ
        eventQuestion = (TextView)findViewById(R.id.eventQuestion);
        eventQuestion.setText(intent.getStringExtra("question"));
    }

}
