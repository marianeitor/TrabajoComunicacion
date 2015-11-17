package connectionpoint.trabajocomunicacion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maria_000 on 14/11/2015.
 */
public class Locales {

    private List<Local> misLocales = new ArrayList<Local>();

    public Locales()
    {
        //Comidas Rapidas
        misLocales.add(new Local("Estacion 27","27 de Abril 364","comidas",-31.415663, -64.189449,"08:00","22:00"));
        misLocales.add(new Local("il Gato","Hipolito Irigoyen 181","comidas",-31.422791, -64.187559,"12:00","23:59"));
        misLocales.add(new Local("Johnny B Good","Hipolito Irigoyen 320","comidas",-31.424039, -64.18722,"12:00","23:59"));

        //Farmacias
        misLocales.add(new Local("Farmacity","Bv. Chacabuco 588","farmacias",-31.424322, -64.18291,"00:00","23:59"));
        misLocales.add(new Local("Farmacity","Laprida 86","farmacias",-31.424003, -64.188675,"00:00","23:59"));

        //Kioscos
        misLocales.add(new Local("Kiosco Panchi","Paraná 635","kioscos",-34.494308, -58.490107,"07:00","23:59"));
        misLocales.add(new Local("Kiosco El Rolo","Paraná 590","kioscos",-34.494065, -58.489479,"06:00","23:59"));
        misLocales.add(new Local("Kiosco Zeta","Tránsito Caceres 540","kioscos",-31.424990, -64.17748,"07:00","23:59"));

        //Carnicerias y Verdulerias
        misLocales.add(new Local("Locos X el Asado","Rondeau 284","comidas",-31.423137, -64.183178,"08:00","22:00"));
        misLocales.add(new Local("Tweety","Independencia Esq. Derqui","carneverdu",-31.425546, -64.18806,"07:30","22:30"));
        misLocales.add(new Local("Verduleria El Leo","Obispo Trejo 1294 Loc.1","carneverdu",-31.430506, -64.191641,"08:00","22:00"));
        misLocales.add(new Local("Verduleria Natura","San Lorenzo 180 Loc.1","carneverdu",-31.423932, -64.184814,"07:30","22:00"));

        //Varios
        misLocales.add(new Local("YPF","Av Velez Sarfield y San Luis","varios",-31.422378, -64.189766,"00:00","23:59"));
        misLocales.add(new Local("ESSO","Av Pueyrredon y Belgrano","varios",-31.426081, -64.192926,"00:00","23:59"));
        misLocales.add(new Local("Casserato","Chacabuco 663","varios",-31.425164, -64.183171,"07:00","23:59"));
    }

    public List<Local> getLocales(String cat)
    {
        List<Local> _filtrada =new ArrayList<Local>();
        for (Local _loc : misLocales)
        {
            if (_loc.getCategoria().equals(cat))
            {
                _filtrada.add(_loc);
            }
        }
        return _filtrada;
    }

}
