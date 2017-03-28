import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by liubingfeng on 26/03/2017.
 */

// This class is equivalent to the table member but without password
public class ClubMember implements JDBCTable
{
    public static String tableNameInDatabase = "member";
    private int memberId = 0;
    private String memberName = null;
    private String memberPhone = null;
    private String memberEmail = null;
    private String memberPassword = null;

    //for factory create new obj


    public ClubMember(int memberId, String memberName, String memberPhone, String memberEmail, String memberPassword)
    {
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberPhone = memberPhone;
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
    }

    //for insert use only
    public ClubMember(String memberName, String memberPhone, String memberEmail)
    {
        //insert will return member id
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberPhone = memberPhone;
        this.memberEmail = memberEmail;
    }

    public void setMemberName(String memberName)
    {
        this.memberName = memberName;
    }

    public void setMemberPhone(String memberPhone)
    {
        this.memberPhone = memberPhone;
    }

    public void setMemberEmail(String memberEmail)
    {
        this.memberEmail = memberEmail;
    }

    public void setMemberPassword(String memberPassword)
    {
        this.memberPassword = memberPassword;
    }

    @Override
    public boolean update()
    {
        Main.LogInfo.logInfo(ClubMember.class, "update sql => " + SqlQuery.setUpdate(tableNameInDatabase, getUpdateAttributesHahsMap(), "where member_id = " + this.memberId));
        jdbcDriver.executeUpdateSql(SqlQuery.setUpdate(tableNameInDatabase, getUpdateAttributesHahsMap(), "where member_id = " + this.memberId));
        return true;
    }

    @Override
    public boolean delete()
    {
        return false;
    }

    @Override
    public boolean insert()
    {
        Main.LogInfo.logInfo(ClubMember.class, "start inserting => " + this);
        int returnedId = jdbcDriver.executeInsertSql(SqlQuery.insertRow(tableNameInDatabase, getUpdateAttributesHahsMap()));
        Main.LogInfo.logInfo(ClubMember.class, "last inser id = > " + returnedId);

        if (memberId == 0)
        {
            this.setMemberId(returnedId);
        }

        if (returnedId == 0)
            return false;
        else
            return true;
    }

    @Override
    public <T, V> HashMap<T, V> getHahsMap(Object obj)
    {
        return null;
    }


    public boolean compareUserNamePassword(String memberEmail, String memberPassword)
    {
        Main.LogInfo.logInfo(ClubMember.class,"input memberEmail => " + memberEmail + " -> input memberPassWord => " + memberPassword );
        Main.LogInfo.logInfo(ClubMember.class,"this memberEmail => " + this.memberEmail + " -> this memberPassWord => " + this.memberPassword );
        if (this.memberEmail.equals(memberEmail) && this.memberPassword.equals(memberPassword))
        {
            return true;
        }
        return false;
    }

    public HashMap<String, String> getAllAttributesHahsMap()
    {
        HashMap<String, String> attributes = new HashMap<String, String>();
        attributes.put("member_id", "" + this.memberId);
        attributes.put("member_name", "" + this.memberName);
        attributes.put("member_phone", "" + this.memberPhone);
        attributes.put("member_email", "" + this.memberEmail);
        attributes.put("member_password", "" + this.memberPassword);
        return attributes;
    }


    public HashMap<String, String> getUpdateAttributesHahsMap()
    {
        HashMap<String, String> attributes = new HashMap<String, String>();
        //attributes.put("member_id", "" + this.memberId);
        attributes.put("member_name", "" + this.memberName);
        attributes.put("member_phone", "" + this.memberPhone);
        attributes.put("member_email", "" + this.memberEmail);
        attributes.put("member_password", "" + this.memberPassword);
        return attributes;
    }

    public HashMap<String, String> getAttributesHahsMap()
    {
        HashMap<String, String> attributes = new HashMap<String, String>();
        //attributes.put("member_id", "" + this.memberId);
        attributes.put("member_name", "" + this.memberName);
        attributes.put("member_phone", "" + this.memberPhone);
        attributes.put("member_email", "" + this.memberEmail);
        //attributes.put("member_password", "" + this.memberPassword);
        return attributes;
    }

    @Override
    public String getHashKey()
    {
        //if have multiple id => firstId,1,secondId,2
        return "member_id,"+this.memberId;
    }

    @Override
    public String toString()
    {
        return "ClubMember{" +
                "memberId=" + memberId +
                ", memberName='" + memberName + '\'' +
                ", memberPhone='" + memberPhone + '\'' +
                ", memberEmail='" + memberEmail + '\'' +
                ", memberPassword='" + memberPassword + '\'' +
                '}';
    }

    public int getMemberId()
    {
        return memberId;
    }

    public void setMemberId(int memberId)
    {
        this.memberId = memberId;
    }
}
