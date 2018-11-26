import cn.hutool.core.date.DateUtil;
import com.huangweihan.xweb.core.utils.CollectionUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Description
 *
 * @author: Administrator
 * @date: 2018/11/23 0023
 */
public class CollectionTest {

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("1", 1);
        map.put("2", 2);
        map.put("3", 3);
        map.put("4", 4);
        map.put("5", 2);
        System.out.println(CollectionUtil.sortByValue(map, false).toString());
    }
}
