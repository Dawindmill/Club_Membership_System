import java.util.HashMap;

/**
 * Created by liubingfeng on 27/03/2017.
 */
public class ClubMemberHasTechGroup implements JDBCTable
{
    public static String tableNameInDatabase = "member_has_tech_group";
    int memberMemberId = 0;
    int techGroupTechGroupId = 0;

    public ClubMemberHasTechGroup(int memberMemberId, int techGroupTechGroupId)
    {
        this.memberMemberId = memberMemberId;
        this.techGroupTechGroupId = techGroupTechGroupId;
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
        return "member_member_id," + this.memberMemberId + ",tech_group_tech_group_id," + this.techGroupTechGroupId;
    }

    @Override
    public String toString()
    {
        return "ClubMemberHasTechGroup{" +
                "memberMemberId=" + memberMemberId +
                ", techGroupTechGroupId=" + techGroupTechGroupId +
                '}';
    }

    public int getMemberMemberId()
    {
        return memberMemberId;
    }

    public int getTechGroupTechGroupId()
    {
        return techGroupTechGroupId;
    }
}
