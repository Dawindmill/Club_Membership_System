import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by liubingfeng on 27/03/2017.
 */
public class StaffAdmin
{
    private TableModel model = null;
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JTable allMembersTable;
    private JButton commitChangeButton;
    private JButton refreshButton;
    private JTextField memberNameTextField;
    private JTextField memberPhoneTextField;
    private JTextField memberEmailTextField;
    private JPasswordField memberPasswordTextField1;
    private JPasswordField memberPasswordTextField2;
    private JLabel warningLabel;
    private JButton commitNewMemberButton;
    private JScrollPane scrollPane;
    private JTextArea detailTextFileArea;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JLabel warningLabelDetail;
    private JButton changePasswordButton;
    private JButton refreshDetailButton;
    private Object[] columnNames = {"member_id", "member_name", "member_phone", "member_email"};
    private Object[][] rowValuesObj = null;
    private ClubStaff clubStaff = null;
    public StaffAdmin(ClubStaff clubStaff)
    {
        this.clubStaff = clubStaff;

        displayStaffDetail();

        showAllMember();

        commitNewMemberButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);
                if (memberNameTextField.equals("") ||
                        memberPhoneTextField.equals("") ||
                        memberEmailTextField.equals("") ||
                        new String(memberPasswordTextField1.getPassword()).equals("") ||
                        new String(memberPasswordTextField2.getPassword()).equals(""))
                {
                    warningLabel.setText("Please do not leave any text fields blank.");
                } else
                {
                    if (new String(memberPasswordTextField1.getPassword()).equals(new String(memberPasswordTextField2.getPassword())))
                    {
                        ClubMember newMember = new ClubMember(memberNameTextField.getText(),
                                memberPhoneTextField.getText(),
                                memberEmailTextField.getText());
                        newMember.setMemberPassword(new String(memberPasswordTextField1.getPassword()));
                        newMember.insert();
                        warningLabel.setText("Insert Successful.");


                    } else
                    {
                        warningLabel.setText("Passwords do not match.");
                    }
                }
            }
        });
        allMembersTable.addMouseMotionListener(new MouseMotionAdapter()
        {
        });

        allMembersTable.getModel().addTableModelListener(new TableModelListener()
        {
            @Override
            public void tableChanged(TableModelEvent e)
            {
                int row = e.getFirstRow();
                int column = e.getColumn();



                TableModel model = (TableModel)e.getSource();

                String columnName = model.getColumnName(column);
                Object data = model.getValueAt(row, column);
                Main.LogInfo.logInfo(StaffAdmin.class, "changed column name => " + columnName + " changed data => " + data);
                rowValuesObj[row][column] = data;
            }
        });


        allMembersTable.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);
                //double clicks

                int row = allMembersTable.rowAtPoint(e.getPoint());
                int col = allMembersTable.columnAtPoint(e.getPoint());
                if (row >= 0 && col >= 0)
                {
                    Main.LogInfo.logInfo(StaffAdmin.class, "row => " + row + "col => " + col + " value => " + rowValuesObj[row][col]);
                    rowValuesObj[row][col] = model.getValueAt(row, col);
                }
            }
        });
        refreshButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);
                showAllMember();
            }
        });
        commitChangeButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);
                for(int row = 0; row < rowValuesObj.length; row++)
                {
                    ClubMember member = Main.centralBuffer.getClubMemberHashMap().get("member_id,"+rowValuesObj[row][0]);
                    member.setMemberName((String)rowValuesObj[row][1]);//name
                    member.setMemberPhone((String) rowValuesObj[row][2]);//phone
                    member.setMemberEmail((String) rowValuesObj[row][3]);//email
                    Main.LogInfo.logInfo(StaffAdmin.class, " update => " + member);
                    member.update();
                }
            }
        });
        refreshDetailButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);
                displayStaffDetail();
            }

        });
        changePasswordButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);
                String p1 = new String(passwordField1.getPassword());
                String p2 = new String(passwordField2.getPassword());
                if (p1.equals("")||p2.equals(""))
                {
                    warningLabelDetail.setText("Passwords do not match");
                    return;
                }
                if (p1.equals(p2))
                {
                    ClubMember member = Main.centralBuffer.getClubMemberHashMap().get("member_id," + clubStaff.getMemberMemberId());
                    member.setMemberPassword(p1);
                    member.update();
                    warningLabelDetail.setText("Password changed successful.");
                    return;
                }

            }
        });
    }

    public JPanel getPanel1()
    {
        return panel1;
    }

    public void showAllMember()
    {


        ArrayList<Object[]> rowValues = new ArrayList<Object[]>();
        for(HashMap.Entry entry : Main.centralBuffer.getClubMemberHashMap().entrySet())
        {
            ArrayList<Object> oneRowValues = new ArrayList<Object>();
            HashMap<String, String> attributes = ((ClubMember)entry.getValue()).getAllAttributesHahsMap();
            for(Object str : columnNames)
            {
                oneRowValues.add((Object)attributes.get(str));
            }
            rowValues.add(oneRowValues.toArray());
        }

        rowValuesObj = new Object[rowValues.size()][columnNames.length];

        for(int i = 0; i < rowValues.size(); i++)
        {
            rowValuesObj[i] = rowValues.get(i);
        }


        model = new DefaultTableModel(rowValuesObj, columnNames);
        allMembersTable.setModel(model);
    }


    private void displayStaffDetail()
    {
        ClubMember member = Main.centralBuffer.getClubMemberHashMap().get("member_id," + clubStaff.getMemberMemberId());

        HashMap<String, String> attributes = member.getAttributesHahsMap();

        Object[] attributesValue = {attributes.get("member_name"), attributes.get("member_email"), attributes.get("member_phone")};

        detailTextFileArea.setText("Hello "+ attributesValue[0] + "\n\nYour Email is " + attributesValue[1] + "\n\nYour Phone Number is " + attributesValue[2]);



    }

    private void createUIComponents()
    {
        //dont allow column 0 to be editable

        // TODO: place custom component creation code here
        allMembersTable = new JTable()
        {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {

                if (colIndex == 0)
                {
                    return false;
                }
                return true; //Disallow the editing of any cell
            }
        };
    }
}
