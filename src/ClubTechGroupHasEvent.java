import java.util.HashMap;

/**
 * Created by liubingfeng on 27/03/2017.
 */
public class ClubTechGroupHasEvent implements JDBCTable
{
    public static String tableNameInDatabase = "tech_group_has_event";
    int techGroupTechGroupId = 0;
    int eventEventId = 0;

    public ClubTechGroupHasEvent(int techGroupTechGroupId, int eventEventId)
    {
        this.techGroupTechGroupId = techGroupTechGroupId;
        this.eventEventId = eventEventId;
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
        return "tech_group_tech_group_id," + this.techGroupTechGroupId + ",event_event_id," +this.eventEventId;
    }

    @Override
    public String toString()
    {
        return "ClubTechGroupHasEvent{" +
                "techGroupTechGroupId=" + techGroupTechGroupId +
                ", eventEventId=" + eventEventId +
                '}';
    }
}
