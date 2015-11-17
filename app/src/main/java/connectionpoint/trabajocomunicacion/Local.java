package connectionpoint.trabajocomunicacion;

/**
 * Created by maria_000 on 14/11/2015.
 */
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Local {

    private String Nombre;
    private String Direccion;
    private String Categoria;
    private double latitud;
    private double longitud;
    private String openTime;
    private String closeTime;

    public Local(String _nombre, String _direccion, String _categoria, double lat, double longi,String _open, String _close) {
        Nombre = _nombre;
        Direccion = _direccion;
        Categoria = _categoria;
        latitud = lat;
        longitud = longi;
        openTime = _open;
        closeTime = _close;
    }

    public String getNombre()
    {
        return Nombre;
    }
    public String getDireccion()
    {
        return Direccion;
    }
    public String getCategoria()
    {
        return Categoria;
    }
    public double getLatitud()
    {
        return latitud;
    }
    public double getLongitud()
    {
        return longitud;
    }
    public String getDescuento()
    {
       SecureRandom random = new SecureRandom();
        return randomStringOfLength(7);
    }
    public boolean isOpened()
    {
        Calendar c= Calendar.getInstance();
        final Date currentTime= c.getTime();

        final SimpleDateFormat sdf=new SimpleDateFormat("kk:mm");

        Date oTime;
        Date cTime;
        try {
            oTime = sdf.parse(openTime);
            cTime = sdf.parse(closeTime);
        }
        catch (Exception e)
        {
            return true;
        }

        if (currentTime.after(oTime) && currentTime.before(cTime))
        {
            return true;
        }
        return false;
    }



    private static String randomStringOfLength(int length) {
        StringBuffer buffer = new StringBuffer();
        while (buffer.length() < length) {
            buffer.append(uuidString());
        }

        //this part controls the length of the returned string
        return buffer.substring(0, length);
    }
    private static String uuidString() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }





}
