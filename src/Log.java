import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by liubingfeng on 19/03/2016.
 */
public class Log
{
    public static Log logInfo = null;
    public final static int DEBUG = 1;
    public final static int NONE = 0;
    private static int LOG_LEVEL = NONE;
    private Calendar cal = null;
    private SimpleDateFormat sdf = null;


    private Log()
    {
        this.cal = Calendar.getInstance();
        this.sdf = new SimpleDateFormat("YYYY:MM:dd:HH:mm:ss");
    }

    public static Log getInstance()
    {
        if (logInfo == null)
        {
            synchronized (Log.class)
            {
                if (logInfo == null)
                {
                    if (logInfo == null)
                    {
                        System.out.println("Log instance is created");
                        logInfo = new Log();
                    }
                }
            }
        }
        return logInfo;
    }

    public void logInfo(Class classnames,String msg)
    {
        if(LOG_LEVEL==DEBUG)
        {
            System.out.println(""+
                    "Date:"+sdf.format(cal.getTime())+" "+
                    "[ Class:"+classnames.getName()+" ]"+
                    "[ Method:"+ Thread.currentThread().getStackTrace()[2].getMethodName()+" ]"+
                    msg);
        }
    }

    public void setLogLevel(int logLevel)
    {
        LOG_LEVEL=logLevel;
    }
}