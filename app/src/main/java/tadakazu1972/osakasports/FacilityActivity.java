package tadakazu1972.osakasports;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.opencsv.CSVParser;
import com.opencsv.CSVReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by tadakazu on 2017/07/17.
 */

public class FacilityActivity extends AppCompatActivity {

    private FacilityActivity mActivity;
    private int N = 200; //読み込むdata2017b.csvの行数
    private EventData[] mEventData = new EventData[N];
    private Boolean mLoadCSV = false; //読み込み完了判定フラグ
    protected ListView listView;
    //該当施設名
    private String name = null;
    private TextView txtName;
    //HomeActivityで選択したnum
    private int num;
    //施設住所、電話、URL
    private String address = null;
    private String tel = null;
    private String url = null;
    private TextView txtAddress;
    private TextView txtTel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_facility);

        mActivity = this;

        //前ページから施設名を受け取りセット
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        txtName = (TextView) findViewById(R.id.txtFacilityName);
        txtName.setText(name);
        //同じくnum
        num = intent.getIntExtra("num", 0);
        //住所
        address = "住所:"+intent.getStringExtra("address");
        txtAddress = (TextView)findViewById(R.id.txtFacilityAddress);
        txtAddress.setText(address);
        //電話
        tel = "電話:"+intent.getStringExtra("tel");
        txtTel = (TextView)findViewById(R.id.txtFacilityTel);
        txtTel.setText(tel);
        //施設情報
        url = intent.getStringExtra("url");
        //ボタン初期化
        initButton();

        //読み込み
        loadCSV("data2017c.csv");

        //リストビュー処理
        listView = (ListView) findViewById(R.id.listview);
        ArrayList<EventData> list = new ArrayList<>();
        //読み込み完了していればデータを貼り付け
        if (mLoadCSV) {
            for (int i=0; i<mEventData.length-1; i++) {
                //該当施設のデータのみ
                //String facilityName = mEventData[i].facility;
                if ( name.equals(mEventData[i].facility)) {
                    list.add(mEventData[i]);
                }
            }
        }
        EventDataAdapter adapter = new EventDataAdapter(FacilityActivity.this);
        adapter.setEventDataList(list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                showEventData(id);
                //Toast.makeText(FacilityActivity.this, "タップされました:id="+String.valueOf(id), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void initButton(){
        //トップへボタン
        findViewById(R.id.btnHome).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(mActivity, HomeActivity.class);
                startActivity(intent);
            }
        });

        //施設の詳細情報を見る
        findViewById(R.id.btnUrl).setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                if (url.equals("")) {
                    Toast.makeText(FacilityActivity.this, "詳細情報URLがありません", Toast.LENGTH_SHORT).show();
                } else {
                    //オーパス等へジャンプ
                    Uri uri = Uri.parse(url);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            }
        });
        //マップで場所を見る
        findViewById(R.id.btnMap).setOnClickListener(new OnClickListener(){
           @Override
           public void onClick(View v){
             //Google MapのURLへ施設名を付加してジャンプ
             String tempURL = "https://www.google.co.jp/maps/place/"+name;
             Uri uri = Uri.parse(tempURL);
             Intent intent = new Intent(Intent.ACTION_VIEW, uri);
             startActivity(intent);
           }
        });
    }

    public void showEventData(long id){
        int i = (int)id-1; //この-1はlong idとの関係で調整いるよ

        Intent intent = new Intent(this, EventActivity.class);
        intent.putExtra("id", String.valueOf(id));
        intent.putExtra("name", mEventData[i].name);
        intent.putExtra("date", mEventData[i].date);
        intent.putExtra("time", mEventData[i].time);
        intent.putExtra("facility", mEventData[i].facility);
        intent.putExtra("description", mEventData[i].top);
        intent.putExtra("submit", mEventData[i].submit);
        intent.putExtra("fee", mEventData[i].fee);
        intent.putExtra("target", mEventData[i].target);
        intent.putExtra("station", mEventData[i].station);
        intent.putExtra("address", mEventData[i].address);
        intent.putExtra("question", mEventData[i].question);
        //intent.putExtra("favorite", mEventData[i].favorite);
        //intent.putExtra("submitted", mEventData[i].submitted);
        startActivity(intent);

        /*
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(mEventData[i].name);
        String s;
        s = "開催日:"+mEventData[i].date+"\n時間:"+mEventData[i].time+"\n場所:"+mEventData[i].facility+"\n内容:"+mEventData[i].top+"\n申込方法:"+mEventData[i].submit+"\n参加費:"+mEventData[i].fee+"\n参加対象:"+mEventData[i].target+"\n最寄駅:"+mEventData[i].station+"\n所在地:"+mEventData[i].address+"\n問い合わせ:"+mEventData[i].question;
        builder.setMessage(s);
        builder.setNegativeButton("キャンセル", null);
        builder.setCancelable(true);
        builder.create();
        builder.show();*/
    }

    public void loadCSV(String filename){
        InputStream is = null;
        int i = 0;
        try{
            try{
                //assetsフォルダのcsvファイル読み込み
                is = getAssets().open(filename);
                InputStreamReader ir = new InputStreamReader(is, "UTF-8");
                CSVReader csvr = new CSVReader(ir, CSVParser.DEFAULT_SEPARATOR, CSVParser.DEFAULT_QUOTE_CHARACTER, 1); //ヘッダー0行読み込まないため1行から
                String[] csv;
                while((csv = csvr.readNext()) != null){
                    mEventData[i] = new EventData(csv[0], csv[1], csv[2], csv[3], csv[4], csv[5], csv[6], csv[7], csv[8], csv[9], csv[10], csv[11], csv[12], csv[13], csv[14], csv[15], csv[16], csv[17], csv[18], csv[19], csv[20], csv[21], csv[22], csv[23], csv[24], csv[25], csv[26], csv[27], csv[28]);
                    //動的に背景に色をつけるためにフラグを設定->EventDataAdapter getViewを見よ
                    if ( csv[num+16].equals("1") ){
                        mEventData[i].color = true;
                    }
                    i++;
                }
            } finally {
                if ( is != null ) is.close();
                //Toast.makeText(this, String.valueOf(i)+"行を読込成功", Toast.LENGTH_LONG).show();
                mLoadCSV = true; //読み込み完了フラグON
            }
        } catch (Exception e) {
            Toast.makeText(this, "CSV読込エラー"+e, Toast.LENGTH_LONG).show();
        }
    }
}
