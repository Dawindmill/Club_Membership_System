import java.sql.Date;
import java.util.HashMap;

/**
 * Created by liubingfeng on 26/03/2017.
 */
public class ClubEvent implements JDBCTable
{
    public static String tableNameInDatabase = "event";
    int eventId = 0;
    String eventName = null;
    Date eventTime = null;
    int eventTypeEventTypeId= 0;

    public ClubEvent(int eventId, String eventName, Date eventTime, int eventTypeEventTypeId)
    {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventTime = eventTime;
        this.eventTypeEventTypeId = eventTypeEventTypeId;
    }

    @Override
    public String toString()
    {
        return "ClubEvent{" +
                "eventId=" + eventId +
                ", eventName='" + eventName + '\'' +
                ", eventTime=" + eventTime +
                ", eventTypeEventTypeId=" + eventTypeEventTypeId +
                '}';
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
        return "event_id,"+this.eventId;
    }
}
