package tadakazu1972.osakasports;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by tadakazu on 2017/07/22.
 */

public class SearchActivity extends AppCompatActivity {
    private SearchActivity mActivity = null;
    private DatePicker mDatePickerStart = null;
    private DatePicker mDatePickerEnd = null;
    private Spinner mSpnFacility = null;
    private Spinner mSpnCategory = null;
    private EditText editFreeword = null;
    //選択格納用
    private String facility = null;
    private String category = null;
    private String freeword = "";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        mActivity = this;
        setContentView(R.layout.activity_search);
        //スピナー初期化
        initSpinner();
        //EditText初期化
        initEditText();
        //ボタン初期化
        initButtons();
    }

    private void initSpinner(){
        //開始日
        mDatePickerStart = (DatePicker)findViewById(R.id.datePickerStart);
        //終了日
        mDatePickerEnd = (DatePicker)findViewById(R.id.datePickerEnd);
        mDatePickerEnd.updateDate(2017,10,23);

        //場所
        mSpnFacility = (Spinner)findViewById(R.id.spnFacility);
        ArrayAdapter<String> adapterFacility = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        String[] facilityLabel = {"すべて","北スポーツセンター","扇町プール","桜橋ボウル","都島スポーツセンター","福島スポーツセンター","福島区民センター","下福島プール","此花スポーツセンター","此花屋内プール","府民共済SUPERアリーナ","舞洲障がい者スポーツセンター","中央スポーツセンター","中央屋内プール","大阪弓道場","大阪城公園太陽の広場","西スポーツセンター","西屋内プール","港スポーツセンター","大阪プール","中央体育館","大正スポーツセンター","大正屋内プール","アゼリア大正","千島体育館","天王寺スポーツセンター","真田山プール","浪速スポーツセンター","浪速屋内プール","浪速スケートリンク","西淀川スポーツセンター","西淀川屋内プール","淀川スポーツセンター","淀川屋内プール","東淀川スポーツセンター","東淀川屋内プール","東淀川体育館","東成スポーツセンター","生野スポーツセンター","生野屋内プール","巽小学校","旭スポーツセンター","旭屋内プール","城東スポーツセンター","城東屋内プール","城東区民センター","鶴見スポーツセンター","鶴見緑地プール","鶴見緑地球技場","鶴見緑地庭球場","阿倍野スポーツセンター","阿倍野屋内プール","住之江スポーツセンター","住之江屋内プール","大空小学校","山之内西庭球場","住吉スポーツセンター","住吉屋内プール","東住吉スポーツセンター","ヤンマースタジアム長居","長居プール","長居庭球場","長居障がい者スポーツセンター","湯里小学校","平野スポーツセンター","平野屋内プール","もと平野青少年会館付設体育館","西成スポーツセンター","西成屋内プール"};
        for (int i=0; i<facilityLabel.length; i++){
            adapterFacility.add(facilityLabel[i]);
        }
        mSpnFacility.setAdapter(adapterFacility);
        mSpnFacility.setOnItemSelectedListener(new OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                facility = (String)mSpnFacility.getSelectedItem();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){
                //何もしない
            }
        });

        //競技
        mSpnCategory = (Spinner)findViewById(R.id.spnCategory);
        //リスト設定
        ArrayAdapter<String> adapterCategory = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        String[] categoryLabel = {"すべて","サッカー・フットサル","テニス","バドミントン","卓球","弓道","水泳","水中エクササイズ","水上レクリエーション","エアロビクス","エクササイズ","ヨガ","太極拳・太極舞","体操","体力・身体測定","体組成測定","その他","不明"};
        for (int i=0; i<categoryLabel.length; i++){
            adapterCategory.add(categoryLabel[i]);
        }
        mSpnCategory.setAdapter(adapterCategory);
        mSpnCategory.setOnItemSelectedListener(new OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                category = (String)mSpnCategory.getSelectedItem();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){
                //何もしない
            }
        });
    }

    public void initEditText(){
        editFreeword = (EditText)findViewById(R.id.freeword);
    }

    public void initButtons(){
        findViewById(R.id.btnSearch).setOnClickListener(new OnClickListener(){
           @Override
            public void onClick(View v){
               Intent intent = new Intent(mActivity, ResultActivity.class);
               int fromMonth = mDatePickerStart.getMonth();
               intent.putExtra("fromMonth", fromMonth);
               int fromDate = mDatePickerStart.getDayOfMonth();
               intent.putExtra("fromDate", fromDate);
               int toMonth = mDatePickerEnd.getMonth();
               intent.putExtra("toMonth", toMonth);
               int toDate = mDatePickerEnd.getDayOfMonth();
               intent.putExtra("toDate", toDate);
               intent.putExtra("facility", facility);
               intent.putExtra("category", category);
               freeword = editFreeword.getText().toString();
               intent.putExtra("freeword", freeword);
               startActivity(intent);
           }
        });
    }
}
