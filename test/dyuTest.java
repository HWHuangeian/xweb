import com.huangweihan.xweb.core.utils.RedisCache;
import com.huangweihan.xweb.core.utils.SerializerUtil;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Description
 *
 * @author: Administrator
 * @date: 2018/10/29 0029
 */
public class dyuTest {

    @Autowired
    private static RedisCache cache;

    public static void main(String[] args) {
        byte[] arr = SerializerUtil.serialize("123");
        String str = SerializerUtil.deserialize(arr, String.class);
        System.out.println(str);

        cache.getCache("123", String.class);
    }
}
