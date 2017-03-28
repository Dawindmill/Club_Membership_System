/**
 * Created by liubingfeng on 26/03/2017.
 */
import java.sql.*;

public class JDBCDriver
{
    public static JDBCDriver jdbcDriver = null;
    // JDBC driver name and database URL
    //private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/club_membership?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";

    //  Database credentials
    private static final String USER = "root";
    private static final String PASS = "root";
    private Connection conn = null;


    private JDBCDriver()
    {
        //STEP 2: Register JDBC driver
        try
        {
            Class.forName(JDBC_DRIVER).newInstance();
            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            //stmt = conn.createStatement();
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (InstantiationException e)
        {
            e.printStackTrace();
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }

    public int executeInsertSql(String sql)
    {
        int result = 0;
        Statement stmt = null;
        try
        {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            result = rs.getInt(1);
            rs.close();
            return result;
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return result;
    }

    public void executeUpdateSql(String sql)
    {
        Statement stmt = null;
        try
        {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public ResultSet executeSQL(String sql)
    {
        //sql = "SELECT id, first, last, age FROM Employees";
        ResultSet rs = null;
        try
        {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
//            while(rs.next()){
//                //Retrieve by column name
//                int id  = rs.getInt("id");
//                int age = rs.getInt("age");
//                String first = rs.getString("first");
//                String last = rs.getString("last");
//
//                //Display values
//                System.out.print("ID: " + id);
//                System.out.print(", Age: " + age);
//                System.out.print(", First: " + first);
//                System.out.println(", Last: " + last);
//            }
            //stmt.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
            return  rs;
        }

        //STEP 5: Extract data from result set
        return rs;
    }

    public boolean closeDataBaseConnection()
    {
        try{
//            if (stmt!=null)
//                stmt.close();
            if (conn!=null)
                conn.close();
            if (jdbcDriver != null)
                jdbcDriver = null;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static JDBCDriver getInstance()
    {
        if (jdbcDriver == null)
        {
            synchronized (JDBCDriver.class)
            {
                if (jdbcDriver == null)
                {
                    jdbcDriver = new JDBCDriver();
                    return  jdbcDriver;
                }
            }
        }
        return  jdbcDriver;
    }
}
