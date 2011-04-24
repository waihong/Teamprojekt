import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


public class PlayerPanel extends JPanel
{
    private static final long serialVersionUID = 1L;
    private MainFrame hf;
    
    public PlayerPanel(final MainFrame hf)
    {
        this.hf = hf;
        
        JButton player1_btn = new JButton("1");
        JButton player2_btn = new JButton("2");
        JButton player3_btn = new JButton("3");
        JButton player4_btn = new JButton("4");
        JButton player5_btn = new JButton("5");
        JButton player6_btn = new JButton("6");

        this.add(player1_btn);
        this.add(player2_btn);
        this.add(player3_btn);
        this.add(player4_btn);
        this.add(player5_btn);
        this.add(player6_btn);
        
        ActionListener al = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                JButton s = (JButton)e.getSource();
                //s.setEnabled(false);
            } 
        };
        
        player1_btn.addActionListener(al);
        player2_btn.addActionListener(al);
        player3_btn.addActionListener(al);
        player4_btn.addActionListener(al);
        player5_btn.addActionListener(al);
        player6_btn.addActionListener(al);
        
    }
}
