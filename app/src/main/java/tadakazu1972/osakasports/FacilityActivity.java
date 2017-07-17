package tadakazu1972.osakasports;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

    private int N = 252; //イベントデータ総数
    private EventData[] mEventData = new EventData[N];
    private Boolean mLoadCSV = false; //読み込み完了判定フラグ
    protected ListView listView;
    //該当施設名
    private String name = null;
    private TextView txtName;
    //HomeActivityで選択したnum
    private int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_facility);

        //前ページから施設名を受け取りセット
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        txtName = (TextView) findViewById(R.id.txtFacilityName);
        txtName.setText(name);
        //同じくnum
        num = intent.getIntExtra("num", 0);

        //読み込み
        loadCSV("data.csv");

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
                Toast.makeText(FacilityActivity.this, "タップされました:id="+String.valueOf(id), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showEventData(long id){
        int i = (int)id-1; //この-1はlong idとの関係で調整いるよ
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(mEventData[i].name);
        String s;
        s = mEventData[i].date+"\n"+mEventData[i].time+"\n"+mEventData[i].facility+"\n"+mEventData[i].submit+"\n"+mEventData[i].fee+"\n"+mEventData[i].target+"\n"+mEventData[i].station+"\n"+mEventData[i].address;
        builder.setMessage(s);
        builder.setNegativeButton("キャンセル", null);
        builder.setCancelable(true);
        builder.create();
        builder.show();
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
