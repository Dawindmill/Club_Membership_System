import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by liubingfeng on 27/03/2017.
 */
public class JDBCTableFactory
{
    //use getShape method to get object of type shape
    //factory design pattern

    public ClubMember clubMemberFactory(ResultSet rs)
    {
        try
        {
                return new ClubMember (rs.getInt("member_id"),
                        rs.getString("member_name"),
                        rs.getString("member_phone"),
                        rs.getString("member_email"),
                        rs.getString("member_password"));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public ClubStaff clubStaffFactory(ResultSet rs)
    {
        try
        {
            return new ClubStaff (rs.getInt("staff_id"), rs.getInt("member_member_id"));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public ClubTechGroup clubTechGroupFactory(ResultSet rs)
    {
        try
        {
            return new ClubTechGroup (rs.getInt("tech_group_id"),
                    rs.getString("group_name"),
                    rs.getInt("staff_staff_id"),
                    rs.getInt("group_leader_group_leader_id"));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public ClubGroupLeader clubLeaderFactory(ResultSet rs)
    {
        try
        {
            return new ClubGroupLeader (rs.getInt("group_leader_id"),
                    rs.getInt("member_member_id"));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public ClubEvent clubEventFactory(ResultSet rs)
    {
        try
        {
            return new ClubEvent (rs.getInt("event_id"),
                    rs.getString("event_name"),
                    rs.getDate("event_time"),
                    rs.getInt("event_type_event_type_id"));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public ClubEventType clubEventTypeFactory(ResultSet rs)
    {
        try
        {
            return new ClubEventType (rs.getInt("event_type_id"),
                    rs.getString("event_type_name"));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public ClubMemberHasTechGroup clubMemberHasTechGroupFactory(ResultSet rs)
    {
        try
        {
            return new ClubMemberHasTechGroup (rs.getInt("member_member_id"),
                    rs.getInt("tech_group_tech_group_id"));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public ClubTechGroupHasEvent clubTechGroupHasEventFactory (ResultSet rs)
    {
        try
        {
            return new ClubTechGroupHasEvent (rs.getInt("tech_group_tech_group_id"),
                    rs.getInt("event_event_id"));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }


    public JDBCTable getTableObject(ResultSet rs, Class tableType)
    {
        switch (tableType.getName())
        {
            //assume using java 1.8 so following is equavalent to tableType.equals
            case "ClubGroupLeader":
                return clubLeaderFactory(rs);
            case "ClubMember":
                return clubMemberFactory(rs);
            case "ClubEvent":
                return clubEventFactory(rs);
            case "ClubStaff":
                return clubStaffFactory(rs);
            case "ClubTechGroup":
                return clubTechGroupFactory(rs);
            case "ClubEventType":
                return clubEventTypeFactory(rs);
            case "ClubMemberHasTechGroup":
                return clubMemberHasTechGroupFactory(rs);
            case "ClubTechGroupHasEvent":
                return clubTechGroupHasEventFactory(rs);
            }
        return null;
    }
}
