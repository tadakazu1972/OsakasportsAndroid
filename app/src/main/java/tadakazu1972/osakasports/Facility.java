package tadakazu1972.osakasports;

import static java.lang.Double.parseDouble;

/**
 * Created by tadakazu on 2017/07/16.
 */

public class Facility {
    protected String name;
    protected double lat;
    protected double lng;
    protected boolean[] category = new boolean[12];

    public Facility(String _name, String _lat, String _lng, String _cat0, String _cat1, String _cat2, String _cat3, String _cat4, String _cat5, String _cat6, String _cat7, String _cat8, String _cat9, String _cat10, String _cat11){
        name = _name;
        lat = parseDouble(_lat);
        lng = parseDouble(_lng);
        if (_cat0.equals("1")) { category[0] = true; } else { category[0] = false; }
        if (_cat1.equals("1")) { category[1] = true; } else { category[1] = false; }
        if (_cat2.equals("1")) { category[2] = true; } else { category[2] = false; }
        if (_cat3.equals("1")) { category[3] = true; } else { category[3] = false; }
        if (_cat4.equals("1")) { category[4] = true; } else { category[4] = false; }
        if (_cat5.equals("1")) { category[5] = true; } else { category[5] = false; }
        if (_cat6.equals("1")) { category[6] = true; } else { category[6] = false; }
        if (_cat7.equals("1")) { category[7] = true; } else { category[7] = false; }
        if (_cat8.equals("1")) { category[8] = true; } else { category[8] = false; }
        if (_cat9.equals("1")) { category[9] = true; } else { category[9] = false; }
        if (_cat10.equals("1")) { category[10] = true; } else { category[10] = false; }
        if (_cat11 != null) { category[11] = true; } else { category[11] = false; }
    }
}
