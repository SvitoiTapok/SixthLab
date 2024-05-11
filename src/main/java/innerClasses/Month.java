package innerClasses;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Вспомогательный класс для организации вывода команды info, выводит расшифровку месяца.
 */
public class Month implements Serializable {
    private static HashMap<String, String> mounths =  new HashMap<>() ;
    static {
        mounths.put("Jan","января");
        mounths.put("Feb","февраля");
        mounths.put("Mar","марта");
        mounths.put("Apr","апреля");
        mounths.put("May","мая");
        mounths.put("Jun","июня");
        mounths.put("Jul","июля");
        mounths.put("Aug","августа");
        mounths.put("Sep","сентября");
        mounths.put("Oct","октября");
        mounths.put("Now","ноября");
        mounths.put("Dec","декабря");
    }
    public static String getMounthTranslation(String x){
        return mounths.get(x);
    }
}

