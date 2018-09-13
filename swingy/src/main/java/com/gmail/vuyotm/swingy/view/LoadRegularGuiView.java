package com.gmail.vuyotm.swingy.view;

import com.gmail.vuyotm.swingy.model.characters.Regular;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoadRegularGuiView extends JFrame {

    private JLabel              heading;
    private ArrayList<JButton>  buttons = null;

    public LoadRegularGuiView(ArrayList<Regular> regulars) throws HeadlessException {
        heading = new JLabel("Load Regular");
        heading.setBounds(100, 20, 150, 20);
        add(heading);
        if ((regulars == null) || regulars.isEmpty()) {
            JTextField  noRegularsTxtFld;

            noRegularsTxtFld = new JTextField("No regulars to load.");
            noRegularsTxtFld.setBounds(100, 50, 150, 20);
            noRegularsTxtFld.setEditable(false);
            add(noRegularsTxtFld);
        }
        else {
            int     index;
            int     yPos;
            String  strId;

            buttons = new ArrayList<>();
            yPos = 50;
            index = 0;
            while (index < regulars.size()) {
                buttons.add(new JButton((regulars.get(index)).getName() + " - " + (regulars.get(index)).getClassType() + " level " + (regulars.get(index)).getLevel()));
                buttons.get(index).setBounds(100, yPos, 300, 20);
                strId = Integer.toString((regulars.get(index)).getId());
                buttons.get(index).setActionCommand(strId);
                add(buttons.get(index));
                yPos += 25;
                ++index;
            }
        }
        setSize(500, 600);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void addListenerAllBtns(ActionListener actionListener) {
        if (buttons == null)
            return ;
        for (JButton loadRegularBtn : buttons) {
            loadRegularBtn.addActionListener(actionListener);
        }
    }

}
