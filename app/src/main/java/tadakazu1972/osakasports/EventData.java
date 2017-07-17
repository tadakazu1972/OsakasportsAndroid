package tadakazu1972.osakasports;

import static java.lang.Long.parseLong;

/**
 * Created by tadakazu on 2017/07/17.
 */

public class EventData {

    protected long id;
    protected String fileName;
    protected String facility;
    protected String place;
    protected String name;
    protected String type;
    protected String top;
    protected String date;
    protected String time;
    protected String submit;
    protected String fee;
    protected String target;
    protected String station;
    protected String address;
    protected String tel;
    protected String question;
    protected String etc;
    protected boolean[] category = new boolean[12];

    public EventData(String _id, String _fileName, String _facility, String _place, String _name, String _type, String _top, String _date, String _time, String _submit, String _fee, String _target, String _station, String _address, String _tel, String _question, String _etc, String _cat0,  String _cat1, String _cat2, String _cat3, String _cat4, String _cat5, String _cat6, String _cat7, String _cat8, String _cat9, String _cat10, String _cat11) {
        id = parseLong(_id);
        fileName = _fileName;
        facility = _facility;
        place = _place;
        name = _name;
        type = _type;
        top = _top;
        date = _date;
        time = _time;
        submit = _submit;
        fee = _fee;
        target = _target;
        station = _station;
        address = _address;
        tel = _tel;
        question = _question;
        etc = _etc;
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
        if (_cat11.equals("1")) { category[11] = true; } else { category[11] = false; }

    }
}
