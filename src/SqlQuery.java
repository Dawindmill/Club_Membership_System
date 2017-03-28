import java.util.HashMap;

/**
 * Created by liubingfeng on 27/03/2017.
 */
public class SqlQuery
{

    public static String findGroupInforWithMemberId(String member_id)
    {
        return "select member_name, group_name, event_name, event_time, event_type_name from member " +
                "left join member_has_tech_group on member_id = member_member_id " +
                "left join tech_group on member_has_tech_group.tech_group_tech_group_id = tech_group_id " +
                "left join tech_group_has_event on tech_group_id = tech_group_has_event.tech_group_tech_group_id " +
                "left join event on tech_group_has_event.tech_group_tech_group_id = event_id " +
                "left join event_type on event_type_id = event_type_event_type_id where member_id = " + member_id + ";";
    }

    public static String selectAllfromTable(String tableName)
    {
        return "SELECT * FROM " + tableName + ";";
    }

    public static String setUpdate(String tableName, HashMap<String, String> columns, String where)
    {
        String sql = "UPDATE " + tableName + " set ";
        boolean first = true;
        for (HashMap.Entry<String, String> entry : columns.entrySet())
        {

            if (!first)
                sql += ",";
            first = false;
            sql += entry.getKey() + " = " + "'" + entry.getValue() + "'";
        }
        sql += " " + where + ";";
        return sql;
    }

    public static String insertRow(String tableName, HashMap<String, String> values)
    {
        String sql = "INSERT INTO " + tableName + " (";
        boolean first = true;
        for (String key : values.keySet())
        {

            if (!first)
                sql += ",";
            first = false;
            sql += key;
        }
        sql += ") VALUES (";
        first = true;

        for (String value : values.values())
        {

            if (!first)
                sql += ",";
            first = false;
            sql += "'" + value + "'";
        }

        sql += ");";

        return sql;
    }

}
