import java.util.HashMap;

/**
 * Created by liubingfeng on 26/03/2017.
 */
public class ClubTechGroup implements JDBCTable
{
    public static String tableNameInDatabase = "tech_group";

    int techGroupId = 0;
    String groupName = null;
    int staffStaffId = 0;
    int groupLeaderGroupLeaderId = 0;

    public ClubTechGroup(int techGroupId, String groupName, int staffStaffId, int groupLeaderGroupLeaderId)
    {
        this.techGroupId = techGroupId;
        this.groupName = groupName;
        this.staffStaffId = staffStaffId;
        this.groupLeaderGroupLeaderId = groupLeaderGroupLeaderId;
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
        return "tech_group_id," + this.techGroupId;
    }

    @Override
    public String toString()
    {
        return "ClubTechGroup{" +
                "techGroupId=" + techGroupId +
                ", groupName='" + groupName + '\'' +
                ", staffStaffId=" + staffStaffId +
                ", groupLeaderGroupLeaderId=" + groupLeaderGroupLeaderId +
                '}';
    }

    public int getTechGroupId()
    {
        return techGroupId;
    }


}
