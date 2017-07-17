package tadakazu1972.osakasports;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_facility);

        //読み込み
        loadCSV("data.csv");

        //リストビュー処理
        listView = (ListView) findViewById(R.id.listview);
        ArrayList<EventData> list = new ArrayList<>();
        //読み込み完了していればデータを貼り付け
        if (mLoadCSV) {
            for (int i=0; i<mEventData.length-1; i++) {
                list.add(mEventData[i]);
            }
        }
        EventDataAdapter adapter = new EventDataAdapter(FacilityActivity.this);
        adapter.setEventDataList(list);
        listView.setAdapter(adapter);


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
                Toast.makeText(this, String.valueOf(i)+"行を読込成功", Toast.LENGTH_LONG).show();
                mLoadCSV = true; //読み込み完了フラグON
            }
        } catch (Exception e) {
            Toast.makeText(this, "CSV読込エラー"+e, Toast.LENGTH_LONG).show();
        }
    }
}
