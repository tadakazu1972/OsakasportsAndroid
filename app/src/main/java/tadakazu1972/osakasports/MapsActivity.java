package tadakazu1972.osakasports;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.opencsv.CSVParser;
import com.opencsv.CSVReader;

import java.io.InputStream;
import java.io.InputStreamReader;

public class MapsActivity extends FragmentActivity implements OnMarkerClickListener, OnMapReadyCallback {

    private GoogleMap mMap;
    private int N = 62; //施設数
    private Facility[] mFacility = new Facility[N];
    private Marker[] mMarker = new Marker[N];
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
        for (int i=0; i<N-1; i++){
            //読み込み完了してれば実行。これしないと先にここが処理され、nullpointerExceptionエラーになる
            if ( mLoadCSV ) {
                LatLng _latlng = new LatLng(mFacility[i].lat, mFacility[i].lng);
                mMarker[i] = mMap.addMarker(new MarkerOptions().position(_latlng).title(mFacility[i].name));
                mMarker[i].setTag(i);
            }
        }

        mMap.setOnMarkerClickListener(this);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(osakacityhall, 12));
    }

    @Override
    public boolean onMarkerClick(final Marker marker){
        Integer num = (Integer)marker.getTag();

        Toast.makeText(this, marker.getTitle()+"がクリックされた:num="+String.valueOf(num), Toast.LENGTH_SHORT).show();

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
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
