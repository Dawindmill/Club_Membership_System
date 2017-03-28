import java.util.HashMap;

/**
 * Created by liubingfeng on 26/03/2017.
 */
public class ClubGroupLeader implements JDBCTable
{
    public static String tableNameInDatabase = "group_leader";
    int groupLeaderId = 0;
    int memberMemberId = 0;

    public ClubGroupLeader(int groupLeaderId, int memberMemberId)
    {
        this.groupLeaderId = groupLeaderId;
        this.memberMemberId = memberMemberId;
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
        return "group_leader_id,"+this.groupLeaderId;
    }

    @Override
    public String toString()
    {
        return "ClubGroupLeader{" +
                "groupLeaderId=" + groupLeaderId +
                '}';
    }
}
