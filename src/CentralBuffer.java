import java.util.HashMap;
import java.util.Objects;

/**
 * Created by liubingfeng on 27/03/2017.
 */
public class CentralBuffer
{
    public static CentralBuffer centralBuffer = null;
    private HashMap<String, ClubTechGroupHasEvent> clubTechGroupHasEventHashMap = null;
    private HashMap<String, ClubTechGroup> clubTechGroupHashMap = null;
    private HashMap<String,ClubStaff> clubStaffHashMap = null;
    private HashMap<String, ClubMemberHasTechGroup> clubMemberHasTechGroupHashMap = null;
    private HashMap<String, ClubMember> clubMemberHashMap = null;
    private HashMap<String, ClubGroupLeader> clubGroupLeaderHashMap = null;
    private HashMap<String, ClubEventType> clubEventTypetHashMap = null;
    private HashMap<String, ClubEvent> clubEventHashMap = null;

    private CentralBuffer()
    {
        clubTechGroupHasEventHashMap = new HashMap<String, ClubTechGroupHasEvent>();
        clubTechGroupHashMap = new HashMap<String, ClubTechGroup>();
        clubStaffHashMap = new HashMap<String,ClubStaff>();
        clubMemberHasTechGroupHashMap = new HashMap<String, ClubMemberHasTechGroup>();
        clubMemberHashMap = new HashMap<String, ClubMember>();
        clubGroupLeaderHashMap = new HashMap<String, ClubGroupLeader>();
        clubEventTypetHashMap = new HashMap<String, ClubEventType>();
        clubEventHashMap = new HashMap<String, ClubEvent>();
    }

    public void printClubMemberHahsMap()
    {
        for(HashMap.Entry entry:clubMemberHashMap.entrySet())
        {
            System.out.println("clubMemberHashMap -> Key =>" + entry.getKey() + " -> Value =>" + entry.getValue());
        }
    }

    public static CentralBuffer getInstance()
    {
        if (centralBuffer == null)
        {
            synchronized (CentralBuffer.class)
            {
                if (centralBuffer == null)
                {
                    centralBuffer = new CentralBuffer();
                    return centralBuffer;
                }
            }

        }
        return centralBuffer;
    }

    public void addedToHahsMap (Class tableType,JDBCTable jdbcTable)
    {
        Main.LogInfo.logInfo(CentralBuffer.class, ""+tableType + " jdbcTable => " + jdbcTable);
        //assume using java 1.8 so following is equavalent to tableType.equals
        switch (tableType.getName())
        {
            case "ClubGroupLeader":
                clubGroupLeaderHashMap.put(jdbcTable.getHashKey(), (ClubGroupLeader) jdbcTable);
                break;
            case "ClubMember":
                clubMemberHashMap.put(jdbcTable.getHashKey(), (ClubMember) jdbcTable);
                break;
            case "ClubEvent":
                clubEventHashMap.put(jdbcTable.getHashKey(), (ClubEvent) jdbcTable);
                break;
            case "ClubStaff":
                clubStaffHashMap.put(jdbcTable.getHashKey(), (ClubStaff)jdbcTable);
                break;
            case "ClubTechGroup":
                clubTechGroupHashMap.put(jdbcTable.getHashKey(), (ClubTechGroup)jdbcTable);
                break;
            case "ClubEventType":
                clubEventTypetHashMap.put(jdbcTable.getHashKey(), (ClubEventType)jdbcTable);
                break;
            case "ClubMemberHasTechGroup":
                clubMemberHasTechGroupHashMap.put(jdbcTable.getHashKey(), (ClubMemberHasTechGroup)jdbcTable);
                break;
            case "ClubTechGroupHasEvent":
                clubTechGroupHasEventHashMap.put(jdbcTable.getHashKey(), (ClubTechGroupHasEvent)jdbcTable);
                break;
        }
    }

    public HashMap<String, ClubTechGroupHasEvent> getClubTechGroupHasEventHashMap()
    {
        return clubTechGroupHasEventHashMap;
    }

    public HashMap<String, ClubTechGroup> getClubTechGroupHashMap()
    {
        return clubTechGroupHashMap;
    }

    public HashMap<String, ClubStaff> getClubStaffHashMap()
    {
        return clubStaffHashMap;
    }

    public HashMap<String, ClubMemberHasTechGroup> getClubMemberHasTechGroupHashMap()
    {
        return clubMemberHasTechGroupHashMap;
    }

    public HashMap<String, ClubMember> getClubMemberHashMap()
    {
        return clubMemberHashMap;
    }

    public HashMap<String, ClubGroupLeader> getClubGroupLeaderHashMap()
    {
        return clubGroupLeaderHashMap;
    }

    public HashMap<String, ClubEventType> getClubEventTypetHashMap()
    {
        return clubEventTypetHashMap;
    }

    public HashMap<String, ClubEvent> getClubEventHashMap()
    {
        return clubEventHashMap;
    }

    @Override
    public String toString()
    {
        return "CentralBuffer{" +
                "clubTechGroupHasEventHashMap=" + clubTechGroupHasEventHashMap +
                ", clubTechGroupHashMap=" + clubTechGroupHashMap +
                ", clubStaffHashMap=" + clubStaffHashMap +
                ", clubMemberHasTechGroupHashMap=" + clubMemberHasTechGroupHashMap +
                ", clubMemberHashMap=" + clubMemberHashMap +
                ", clubGroupLeaderHashMap=" + clubGroupLeaderHashMap +
                ", clubEventTypetHashMap=" + clubEventTypetHashMap +
                ", clubEventHashMap=" + clubEventHashMap +
                '}';
    }




}
