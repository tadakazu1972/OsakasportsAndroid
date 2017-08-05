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
        //btn1
        mView.findViewById(R.id.btn1).setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(mActivity, MapsActivity.class);
                //マーカー色判定用
                intent.putExtra("num",0);
                startActivity(intent);
            }
        });
        //btn2
        mView.findViewById(R.id.btn2).setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(mActivity, MapsActivity.class);
                //マーカー色判定用
                intent.putExtra("num",1);
                startActivity(intent);
            }
        });
        //btn3
        mView.findViewById(R.id.btn3).setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(mActivity, MapsActivity.class);
                //マーカー色判定用
                intent.putExtra("num",2);
                startActivity(intent);
            }
        });
        //btn4
        mView.findViewById(R.id.btn4).setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(mActivity, MapsActivity.class);
                //マーカー色判定用
                intent.putExtra("num",3);
                startActivity(intent);
            }
        });
        //btn5
        mView.findViewById(R.id.btn5).setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(mActivity, MapsActivity.class);
                //マーカー色判定用
                intent.putExtra("num",4);
                startActivity(intent);
            }
        });
        //btn6
        mView.findViewById(R.id.btn6).setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(mActivity, MapsActivity.class);
                //マーカー色判定用
                intent.putExtra("num",5);
                startActivity(intent);
            }
        });
        //btn7
        mView.findViewById(R.id.btn7).setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(mActivity, MapsActivity.class);
                //マーカー色判定用
                intent.putExtra("num",6);
                startActivity(intent);
            }
        });
        //btn8
        mView.findViewById(R.id.btn8).setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(mActivity, MapsActivity.class);
                //マーカー色判定用
                intent.putExtra("num",7);
                startActivity(intent);
            }
        });
        //btn9
        mView.findViewById(R.id.btn9).setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(mActivity, MapsActivity.class);
                //マーカー色判定用
                intent.putExtra("num",8);
                startActivity(intent);
            }
        });
        //btn10
        mView.findViewById(R.id.btn10).setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(mActivity, MapsActivity.class);
                //マーカー色判定用
                intent.putExtra("num",9);
                startActivity(intent);
            }
        });
        //btn11
        mView.findViewById(R.id.btn11).setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(mActivity, MapsActivity.class);
                //マーカー色判定用
                intent.putExtra("num",10);
                startActivity(intent);
            }
        });
        //btn12
        mView.findViewById(R.id.btn12).setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(mActivity, MapsActivity.class);
                //マーカー色判定用
                intent.putExtra("num",11);
                startActivity(intent);
            }
        });
        //詳細検索ボタン
        mView.findViewById(R.id.btnSearch).setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(mActivity, SearchActivity.class);
                startActivity(intent);
            }
        });
    }
}
