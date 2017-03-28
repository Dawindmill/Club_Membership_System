import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by liubingfeng on 27/03/2017.
 */
public class MemberAdmin
{
    private DefaultListModel memberInforListModel;
    private JList memberInforList;
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JButton refreshDetailButton;
    private JButton commitChangeButton;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JLabel warningLabel;
    private JTextArea groupInforTextArea;
    private ClubMember member = null;

    public MemberAdmin(ClubMember member)
    {
        this.member = member;
        displayMemberList();
        findGroupInfor();
        memberInforList.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);
                JList list = (JList)e.getSource();
                if (e.getClickCount() == 2)
                {
                    int index = list.locationToIndex(e.getPoint());
                    String[] strList = ((String) memberInforListModel.get(index)).split(",");
                    if(strList[0].equals("member_id"))
                    {
                        return;
                    }
                    String inputStr = JOptionPane.showInputDialog(Main.Jframe, "What is your new value for " + strList[0] + "?", strList[1]);
                    memberInforListModel.setElementAt(strList[0] + "," + inputStr, index);
                    //memberInforList.setModel(memberInforListModel);
                    // Double-click detected
                }
//                } else if (e.getClickCount() == 3) {
//
//                    // Triple-click detected
//                    int index = list.locationToIndex(e.getPoint());
//                }

            }

        });
        refreshDetailButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);
                displayMemberList();
                passwordField1.setText("");
                passwordField2.setText("");

            }
        });
        commitChangeButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);
                String pass1 = new String(passwordField1.getPassword());
                String pass2 = new String(passwordField2.getPassword());
                if(pass1.equals(""))
                {
                    applyMemberInforListModel(memberInforListModel);
                    member.update();
                    warningLabel.setText("Commit Successful.");
                }
                else
                {
                    if(pass1.equals(pass2))
                    {
                        member.setMemberPassword(pass1);
                        applyMemberInforListModel(memberInforListModel);
                        member.update();
                        warningLabel.setText("Commit Successful.");

                    }
                    else
                    {
                        warningLabel.setText("Commit Failed, password does not match.");
                    }
                }
            }
        });
    }

    public void findGroupInfor()
    {
        ResultSet rs = JDBCDriver.jdbcDriver.executeSQL(SqlQuery.findGroupInforWithMemberId(""+member.getMemberId()));
        ArrayList<String> strGroupInforArrayList = new ArrayList<String>();
        try
        {
            while (rs.next())
            {
                String strGroupInfor = "Group Name," + rs.getString("group_name") + "\n";
                strGroupInfor += "Event Name," + rs.getString("event_name") + "\n";
                strGroupInfor += "Event Time," + rs.getString("event_time") + "\n";
                strGroupInfor += "Event Type Name," + rs.getString("event_type_name") + "\n" ;
                strGroupInforArrayList.add(strGroupInfor);
             }
        } catch (SQLException e)
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
        for (String str : strGroupInforArrayList)
        {
            groupInforTextArea.setText(groupInforTextArea.getText()+str);
        }

    }

    public void applyMemberInforListModel(DefaultListModel memberInforListModel)
    {
        for(Object str: memberInforListModel.toArray())
        {
            String[] strList = ((String)str).split(",");
            switch (strList[0])
            {
                case "member_phone":
                    member.setMemberPhone(strList[1]);
                    break;
                case "member_name":
                    member.setMemberName(strList[1]);
                    break;
                case "member_email":
                    member.setMemberEmail(strList[1]);
                    break;
            }
        }
    }


    public void setMember(ClubMember member)
    {
        this.member = member;

    }


    public void displayMemberList()
    {
        this.memberInforListModel = new DefaultListModel();
        this.memberInforList.setModel(memberInforListModel);
        HashMap<String, String> attributes = member.getAttributesHahsMap();

        for(HashMap.Entry entry: attributes.entrySet())
        {
            this.memberInforListModel.addElement("" + entry.getKey() + "," + entry.getValue());
        }

    }

    public JPanel getPanel1()
    {
        return panel1;
    }
}
