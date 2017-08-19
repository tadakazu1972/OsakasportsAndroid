package tadakazu1972.osakasports;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Integer.parseInt;

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
    private String description = null;
    private TextView eventSubmit = null;
    private String submit = null;
    private TextView eventFee = null;
    private String fee = null;
    private TextView eventTarget = null;
    private String target = null;
    private TextView eventStation = null;
    private String station = null;
    private TextView eventAddress = null;
    private String address = null;
    private TextView eventQuestion = null;
    private String question = null;
    private SharedPreferences sp;
    private String favorite = null;
    private String submitted = null;
    private ImageButton btnFavorite = null;
    private ImageButton btnSubmitted = null;
    private TextView lblFavorite = null;
    private TextView lblSubmitted = null;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        mActivity = this;

        setContentView(R.layout.activity_event);

        sp = PreferenceManager.getDefaultSharedPreferences(this);

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
        description = intent.getStringExtra("description");
        //申込方法
        eventSubmit = (TextView)findViewById(R.id.eventSubmit);
        eventSubmit.setText(intent.getStringExtra("submit"));
        submit = intent.getStringExtra("submit");
        //参加費
        eventFee = (TextView)findViewById(R.id.eventFee);
        eventFee.setText(intent.getStringExtra("fee"));
        fee = intent.getStringExtra("fee");
        //参加対象
        eventTarget = (TextView)findViewById(R.id.eventTarget);
        eventTarget.setText(intent.getStringExtra("target"));
        target = intent.getStringExtra("target");
        //最寄駅
        eventStation = (TextView)findViewById(R.id.eventStation);
        eventStation.setText(intent.getStringExtra("station"));
        station = intent.getStringExtra("station");
        //所在地
        eventAddress = (TextView)findViewById(R.id.eventAddress);
        eventAddress.setText(intent.getStringExtra("address"));
        address = intent.getStringExtra("address");
        //問い合わせ
        eventQuestion = (TextView)findViewById(R.id.eventQuestion);
        eventQuestion.setText(intent.getStringExtra("question"));
        question = intent.getStringExtra("question");
        //お気に入り登録したか
        String favoriteStr = "favorite"+id; //探索用キーワードをイベントidと組み合わせて生成
        favorite = sp.getString(favoriteStr, "0"); // 第２引数はkeyが存在しない時に返す初期値
        //申込み済か
        String submittedStr = "submitted"+id;
        submitted = sp.getString(submittedStr, "0");

        //ボタン初期化
        initButtons();
    }

    public void initButtons(){
        //お気に入りチェック
        btnFavorite = (ImageButton)findViewById(R.id.btnFavorite);
        lblFavorite = (TextView)findViewById(R.id.lblFavorite);
        if (favorite.equals("1")){
            btnFavorite.setImageResource(R.drawable.ic_star_yellow_24dp);
            lblFavorite.setText("お気に入り");
        }
        btnFavorite.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                String messageFavorite;
                if (favorite.equals("1")){
                    favorite = "0";
                    //書き込み
                    String submittedStr = "favorite"+id;
                    sp.edit().putString(submittedStr, "0").apply();
                    btnFavorite.setImageResource(R.drawable.ic_star_border_yellow_24dp);
                    lblFavorite.setText("お気に入りに追加");
                    messageFavorite = "お気に入りを解除しました";
                } else {
                    favorite = "1";
                    //書き込み
                    String submittedStr = "favorite"+id;
                    sp.edit().putString(submittedStr, "1").apply();
                    btnFavorite.setImageResource(R.drawable.ic_star_yellow_24dp);
                    lblFavorite.setText("お気に入り");
                    messageFavorite = "お気に入りに登録しました";
                }
                Toast.makeText(EventActivity.this, messageFavorite, Toast.LENGTH_SHORT).show();
            }
        });
        //申込み済チェック
        btnSubmitted = (ImageButton)findViewById(R.id.btnSubmitted);
        lblSubmitted = (TextView)findViewById(R.id.lblSubmitted);
        if (submitted.equals("1")){
            btnSubmitted.setImageResource(R.drawable.ic_check_box_green_24dp);
            lblSubmitted.setText("申込済");
        }
        btnSubmitted.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                String messageSubmitted;
                if (submitted.equals("1")){
                    submitted = "0";
                    //書き込み
                    String submittedStr = "submitted"+id;
                    sp.edit().putString(submittedStr, "0").apply();
                    btnSubmitted.setImageResource(R.drawable.ic_check_box_outline_blank_black_24dp);
                    lblSubmitted.setText("申込み済にする");
                    messageSubmitted = "申込済を解除しました";
                } else {
                    submitted = "1";
                    //書き込み
                    String submittedStr = "submitted"+id;
                    sp.edit().putString(submittedStr, "1").apply();
                    btnSubmitted.setImageResource(R.drawable.ic_check_box_green_24dp);
                    lblSubmitted.setText("申込済");
                    messageSubmitted = "申込済にしました";
                }
                Toast.makeText(EventActivity.this, messageSubmitted, Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btnCalender).setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                //日付のフォーマット処理。以下、超絶めんどくさい。覚悟しろよ!!
                String tempDateStr = date.replaceAll("\\(.+?\\)", ""); //開催日の文字列『10/9(祝)」から「10/9」だけ切り出し　"("かっこがあるから正規表現
                String[] tempDatePair = tempDateStr.split("/");
                String tempMonth = String.valueOf(String.format("%02d", parseInt(tempDatePair[0])));
                String tempDate  = String.valueOf(String.format("%02d", parseInt(tempDatePair[1])));
                String startDate = "2017" + tempMonth + tempDate;
                String endDate   = "2017" + tempMonth + tempDate;
                //まだだ。時間の処理が残っている。ちなみに、複数時間が同一セルに記載されている場合は正しい結果にならないのであしからず。結果、終日になる。
                String tempTimeStr = time.replaceAll(":",""); // 9:00~12:00から:を削除して900~1200に
                String[] tempTimePair = tempTimeStr.split("~"); // 900~1200を900と1200に分割
                String startTime = String.valueOf(String.format("%04d", parseInt(tempTimePair[0])))+"00";
                String endTime   = String.valueOf(String.format("%04d", parseInt(tempTimePair[1])))+"00";
                //さあ、すべてを終わらせよう
                String dates = startDate + 'T' + startTime + '/' + endDate + 'T' + endTime;
                //Toast.makeText(EventActivity.this, "dates="+dates, Toast.LENGTH_LONG).show();

                String url = "http://www.google.com/calendar/event?action=TEMPLATE&text=" + name + "&dates=" + dates + "&location=" + address;
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnTwitter).setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                String url = "https://twitter.com/share?url=https://osakasports.azurewebsites.net/event.html?id="+id+"&text=【大阪市オータムチャレンジスポーツ2017】" + name + "/" + date + "/" + time + "/" + facility;
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnLine).setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                String url = "http://line.me/R/msg/text/?【イベント名】" + name + "%0A【開催日】" + date + "%0A【時間】" + time + "%0A【場所】" + facility + "(最寄り駅:"+ station + ")%0A【内容】" + description + "%0A【参加費】" + fee + "%0A【参加対象】" + target + "%0A【申込方法】" + submit + "%0A【問い合わせ先】" + question + "%0A★★オータム・チャレンジ・スポーツ2017ーこの秋から始めようスポーツライフ！ー★★%0A9月から11月の3ヶ月間、大阪市内各所で様々なスポーツを楽しめる参加型イベントを開催します！%0Aアプリのダウンロードはこちらから。%0Ahttps://civictechosaka.wixsite.com/undobusoku";
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

        findViewById(R.id.btnMypage).setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(mActivity, MypageActivity.class);
                startActivity(intent);
            }
        });
    }
}
