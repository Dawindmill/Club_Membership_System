import java.util.HashMap;

/**
 * Created by liubingfeng on 26/03/2017.
 */
public class ClubStaff implements JDBCTable
{
    public static String tableNameInDatabase = "staff";

    private int staffId = 0;
    private int memberMemberId = 0;

    public ClubStaff(int staffId, int memberMemberId)
    {
        this.memberMemberId = memberMemberId;
        this.staffId = staffId;
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
        return "staff_id,"+this.staffId;
    }

    public int getMemberMemberId()
    {
        return memberMemberId;
    }

    @Override
    public String toString()
    {
        return "ClubStaff{" +
                "staffId=" + staffId +
                '}';
    }
}
