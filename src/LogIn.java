import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

/**
 * Created by liubingfeng on 27/03/2017.
 */
public class LogIn
{
    private JPanel panel1;
    private JButton loginButton;
    private JFormattedTextField usernameTextField;
    private JPasswordField passwordField;
    private JLabel technologyClubMemberShipLabel;
    private JLabel passwordLabel;
    private JLabel usernameLabel;
    private JLabel warningLabel;

    public LogIn()
    {
        loginButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);
                Main.LogInfo.logInfo(LogIn.class, "mouse clicked");
                checkUserNamePassWord(usernameTextField.getText(), new String(passwordField.getPassword()));

            }
        });
    }

    public void checkUserNamePassWord(String username, String password)
    {
        for(HashMap.Entry entry : Main.centralBuffer.getClubMemberHashMap().entrySet())
        {
            ClubMember user = ((ClubMember)entry.getValue());
            if(user.compareUserNamePassword(username, password))
            {

                for(HashMap.Entry entry1 : Main.centralBuffer.getClubStaffHashMap().entrySet())
                {
                    ClubStaff clubStaff = ((ClubStaff)entry1.getValue());
                    if(clubStaff.getMemberMemberId() == user.getMemberId())
                    {
                        Main.startStaffWindow(clubStaff);
                        return;
                    }
                }
                Main.startMemberAdminWindow(((ClubMember)entry.getValue()));
                return;
            }
        }
        warningLabel.setText("Please check your username and password.");

    }



    public JPanel getPanel1()
    {
        return panel1;
    }
}
