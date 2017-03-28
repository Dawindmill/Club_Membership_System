import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created by liubingfeng on 27/03/2017.
 */
public interface JDBCTable
{
    static JDBCDriver jdbcDriver = JDBCDriver.getInstance();
    public boolean update();
    public boolean delete();
    public boolean insert();
    public <T, V> HashMap<T, V> getHahsMap(Object obj);
    public String getHashKey();
    //public <T> T parseAll (ResultSet rs);
}
