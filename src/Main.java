import javax.swing.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

// log -> singleton
// factory -> factor
public class Main {
    public static MemberAdmin MemberAdmin = null;
    public static final LogIn LoginWindow = new LogIn();
    public static StaffAdmin staffAdmin ;
    public static final JFrame Jframe = new JFrame("Technology Club System");
    public static Log LogInfo = Log.getInstance();
    public static JDBCTableFactory jdbcTableFactory = new JDBCTableFactory();
    public static JDBCDriver jdbcDriver = JDBCDriver.getInstance();
    public static CentralBuffer centralBuffer = CentralBuffer.getInstance();

    public static void setUpCentralBuffer(CentralBuffer centralBuffer, Class classType, String tableNameInDataBase)
    {
        String sql = SqlQuery.selectAllfromTable(tableNameInDataBase);
        Main.LogInfo.logInfo(Main.class, "Try to get all, className -> " + classType + " tableName -> " + classType + " -> sql used => " + sql);
        ResultSet rs = jdbcDriver.executeSQL(sql);
        try
        {
            while(rs.next())
            {
                Main.LogInfo.logInfo(Main.class, "inside while(rs.next())");
                centralBuffer.addedToHahsMap(classType, jdbcTableFactory.getTableObject(rs, classType));
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                rs.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void startMemberAdminWindow(ClubMember member)
    {
        MemberAdmin = new MemberAdmin(member);
        Jframe.setContentPane(MemberAdmin.getPanel1());
        //Jframe.setSize(800,800);
        Jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Jframe.pack();
        //shoul be called after pack(), this will set window to be apperaed in the center of the screen
        Jframe.setLocationRelativeTo(null);
        Jframe.setVisible(true);
        MemberAdmin.setMember(member);
    }

    public static void startStaffWindow(ClubStaff staff)
    {
        staffAdmin = new StaffAdmin(staff);
        Jframe.setContentPane(staffAdmin.getPanel1());
        //Jframe.setSize(800,800);
        Jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Jframe.pack();
        //shoul be called after pack(), this will set window to be apperaed in the center of the screen
        Jframe.setLocationRelativeTo(null);
        Jframe.setVisible(true);
    }

    public static void startLoginWindow()
    {

        Jframe.setContentPane(LoginWindow.getPanel1());
        //Jframe.setSize(800,800);
        Jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Jframe.pack();
        //shoul be called after pack(), this will set window to be apperaed in the center of the screen
        Jframe.setLocationRelativeTo(null);
        Jframe.setVisible(true);
    }

    public static void main(String[] args)
    {
        LogInfo.setLogLevel(Log.DEBUG);
        System.out.println("Hello World!");
        setUpCentralBuffer(centralBuffer, ClubEvent.class, ClubEvent.tableNameInDatabase);
        setUpCentralBuffer(centralBuffer, ClubEventType.class, ClubEventType.tableNameInDatabase);
        setUpCentralBuffer(centralBuffer, ClubGroupLeader.class, ClubGroupLeader.tableNameInDatabase);
        setUpCentralBuffer(centralBuffer, ClubMember.class, ClubMember.tableNameInDatabase);
        setUpCentralBuffer(centralBuffer, ClubMemberHasTechGroup.class, ClubMemberHasTechGroup.tableNameInDatabase);
        setUpCentralBuffer(centralBuffer, ClubStaff.class, ClubStaff.tableNameInDatabase);
        setUpCentralBuffer(centralBuffer, ClubTechGroup.class, ClubTechGroup.tableNameInDatabase);
        setUpCentralBuffer(centralBuffer, ClubTechGroupHasEvent.class, ClubTechGroupHasEvent.tableNameInDatabase);
        startLoginWindow();



        //fatalErrorWindow("hello");

        //centralBuffer.printClubMemberHahsMap();
//        String password = "123456";
//        MessageDigest messageDigest = null;
//        try
//        {
//            messageDigest = MessageDigest.getInstance("SHA-256");
//        } catch (NoSuchAlgorithmException e)
//        {
//            e.printStackTrace();
//        }
//        messageDigest.update(password.getBytes());
//        String encryptedString = new String(messageDigest.digest());
//        System.out.println("encrypted string = > " + encryptedString.length());

    }
}
