import java.util.HashMap;

/**
 * Created by liubingfeng on 27/03/2017.
 */
public class ClubEventType implements JDBCTable
{
    public static String tableNameInDatabase = "event_type";
    int eventTypeId = 0;
    String eventTypeName = null;

    public ClubEventType(int eventTypeId, String eventTypeName)
    {
        this.eventTypeId = eventTypeId;
        this.eventTypeName = eventTypeName;
    }

    @Override
    public boolean update()
    {
        return false;
    }

    @Override
    public boolean delete()
    {
        return false;
    }

    @Override
    public boolean insert()
    {
        return false;
    }

    @Override
    public <T, V> HashMap<T, V> getHahsMap(Object obj)
    {
        return null;
    }

    @Override
    public String getHashKey()
    {
        //if have multiple id => firstId,1,secondId,2
        return "event_type_id,"+this.eventTypeId;
    }

    @Override
    public String toString()
    {
        return "ClubEventType{" +
                "eventTypeId=" + eventTypeId +
                ", eventTypeName='" + eventTypeName + '\'' +
                '}';
    }
}
