package tadakazu1972.osakasports;

import static java.lang.Double.parseDouble;

/**
 * Created by tadakazu on 2017/07/16.
 */

public class Facility {
    protected String name;
    protected double lat;
    protected double lng;
    protected int[] category;

    public Facility(String _name, String _lat, String _lng){
        name = _name;
        lat = parseDouble(_lat);
        lng = parseDouble(_lng);
        category = new int[12];
    }
}
