package tadakazu1972.osakasports;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.opencsv.CSVParser;
import com.opencsv.CSVReader;

import java.io.InputStream;
import java.io.InputStreamReader;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Facility[] mFacility = new Facility[62];
    private Boolean mLoadCSV = false; //読み込み完了判定フラグ

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //施設CSVファイル読み込み
        loadCSV("facilities.csv");

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in 大阪市役所 and move the camera
        LatLng osakacityhall = new LatLng(34.694062, 135.502154);
        mMap.addMarker(new MarkerOptions().position(osakacityhall).title("大阪市役所"));

        //スポーツセンターのマーカー描画
        for (int i=0; i<61; i++){
            //読み込み完了してれば実行。これしないと先にここが処理され、nullpointerExceptionエラーになる
            if ( mLoadCSV ) {
                LatLng _latlng = new LatLng(mFacility[i].lat, mFacility[i].lng);
                mMap.addMarker(new MarkerOptions().position(_latlng).title(mFacility[i].name));
            }
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(osakacityhall, 12));
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
                    mFacility[i] = new Facility(csv[0], csv[1], csv[2]);
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
