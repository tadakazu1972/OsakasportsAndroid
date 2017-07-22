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
import java.util.Calendar;
import java.util.Date;

import static java.lang.Integer.parseInt;

/**
 * Created by tadakazu on 2017/07/22.
 */

public class ResultActivity extends AppCompatActivity {
    private int N = 252; //イベントデータ総数
    private EventData[] mEventData = new EventData[N];
    private Boolean mLoadCSV = false; //読み込み完了判定フラグ
    protected ListView listView;
    //該当施設名
    private String name = null;
    private TextView txtName;
    //SearchActivityで選択したnum
    private int fromMonth;
    private int fromDate;
    private int toMonth;
    private int toDate;
    private String facility;
    private String category;
    private String freeword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_facility);

        //SearchActivityから受け取りセット
        Intent intent = getIntent();
        fromMonth = intent.getIntExtra("fromMonth", 10);
        fromDate  = intent.getIntExtra("fromDate",  1);
        toMonth = intent.getIntExtra("toMonth", 12);
        toDate  = intent.getIntExtra("toDate", 31);
        facility = intent.getStringExtra("facility");
        category = intent.getStringExtra("category");
        freeword = intent.getStringExtra("freeword");
        //ヘッダーにテキストをセット
        txtName = (TextView) findViewById(R.id.txtFacilityName);
        txtName.setText(facility+":"+category);

        //読み込み
        loadCSV("data.csv");

        //リストビュー処理
        listView = (ListView) findViewById(R.id.listview);
        ArrayList<EventData> list = new ArrayList<>();
        //読み込み完了していればデータを貼り付け
        if (mLoadCSV) {
            //日付処理:引き継いだパラメータから検索開始日と検索終了日をDate型でつくり、csvから開催日をDate型をつくり、比較させる
            Calendar calendar1 = Calendar.getInstance();
            calendar1.set(Calendar.YEAR, 2017);
            calendar1.set(Calendar.MONTH, fromMonth);
            calendar1.set(Calendar.DATE, fromDate);
            Date date1 = calendar1.getTime();

            Calendar calendar2 = Calendar.getInstance();
            calendar2.set(Calendar.YEAR, 2017);
            calendar2.set(Calendar.MONTH, toMonth);
            calendar2.set(Calendar.DATE, toDate);
            Date date2 = calendar2.getTime();

            for (int i=0; i<mEventData.length-1; i++) {

                String tempDateStr = (mEventData[i].date).substring(0, (mEventData[i].date.indexOf("日"))); //開催日の文字列『１０月１０日（祝）」から「１０月１０」だけ切り出し
                String[] tempDatePair = tempDateStr.split("月");
                Calendar calendar3 = Calendar.getInstance();
                calendar3.set(Calendar.YEAR, 2017);
                calendar3.set(Calendar.MONTH, parseInt(tempDatePair[0])-1);
                calendar3.set(Calendar.DATE, parseInt(tempDatePair[1]));
                Date date3 = calendar3.getTime();

                //フリーワード検索用文字列格納
                String allString = mEventData[i].getAllString();

                //３つのDateを比較し、はさまれた開催日のみ表示
                if (date1.compareTo(date3)<0){
                    if (date3.compareTo(date2)<0){
                        if (facility.equals("すべて")){
                            if (category.equals("すべて")){
                                if (freeword.equals("")){
                                    list.add(mEventData[i]);
                                } else if (allString.matches(".*"+freeword+".*")){
                                    list.add(mEventData[i]);
                                }
                            } else if (category.equals(mEventData[i].type)){
                                if (freeword.equals("")){
                                    list.add(mEventData[i]);
                                } else if (allString.matches(".*"+freeword+".*")){
                                    list.add(mEventData[i]);
                                }
                            }
                        } else if (facility.equals(mEventData[i].facility)){
                            if (category.equals("すべて")){
                                if (freeword.equals("")){
                                    list.add(mEventData[i]);
                                } else if (allString.matches(".*"+freeword+".*")){
                                    list.add(mEventData[i]);
                                }
                            } else if (category.equals(mEventData[i].type)){
                                if (freeword.equals("")){
                                    list.add(mEventData[i]);
                                } else if (allString.matches(".*"+freeword+".*")){
                                    list.add(mEventData[i]);
                                }
                            }
                        }
                    }
                }
            }
        }
        EventDataAdapter adapter = new EventDataAdapter(ResultActivity.this);
        adapter.setEventDataList(list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                showEventData(id);
                Toast.makeText(ResultActivity.this, "タップされました:id="+String.valueOf(id), Toast.LENGTH_SHORT).show();
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
