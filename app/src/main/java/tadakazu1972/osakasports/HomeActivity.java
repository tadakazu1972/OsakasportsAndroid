package tadakazu1972.osakasports;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * Created by tadakazu on 2017/07/15.
 */

public class HomeActivity extends AppCompatActivity {

    private HomeActivity mActivity = null;
    private View mView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        mActivity = this;
        mView = this.getWindow().getDecorView();
        setContentView(R.layout.activity_home);

        initButtons();
    }

    //ボタン設定
    private void initButtons(){
        //
        mView.findViewById(R.id.btnTaiken).setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(mActivity, MapsActivity.class);
                //マーカー色判定用
                intent.putExtra("num",10);
                startActivity(intent);
            }
        });
        //
        mView.findViewById(R.id.btnWakaimon).setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(mActivity, MapsActivity.class);
                //マーカー色判定用
                intent.putExtra("num",7);
                startActivity(intent);
            }
        });
        //
        mView.findViewById(R.id.btnKodomo).setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(mActivity, MapsActivity.class);
                //マーカー色判定用
                intent.putExtra("num",9);
                startActivity(intent);
            }
        });
        mView.findViewById(R.id.btnSearch).setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(mActivity, SearchActivity.class);
                startActivity(intent);
            }
        });
    }
}
