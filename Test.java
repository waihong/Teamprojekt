import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JToggleButton;

public class Test extends JFrame
{
    private static final long serialVersionUID = 1L;
    
    private boolean c = false;

    public Test(String title) throws InterruptedException
    {
        super(title);
        this.setLayout(new BorderLayout());
    
        ButtonGroup bgroup = new ButtonGroup();
        AbstractButton abstract1 = new JToggleButton("+");
        AbstractButton abstract2 = new JToggleButton("-");

        bgroup.add(abstract1);
        bgroup.add(abstract2);

        this.add(abstract1, BorderLayout.NORTH);
        this.add(abstract2, BorderLayout.SOUTH);
        //--------------------------------------------
        ActionListener aListener = new ActionListener() {
            public void actionPerformed(ActionEvent event)
            {
                AbstractButton aButton = (AbstractButton) event.getSource();
                System.out.println("Selected: " + aButton.getText());
                
            }
        };
          
        abstract1.addActionListener(aListener);
        abstract2.addActionListener(aListener);
        //------------------------------------------
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(200, 400);
        //setSize(400, 300);
        pack();
        setVisible(true);
    }
 
    public static void main(String[] args) throws InterruptedException
    {
        new Test("Scouting");
    }
}
