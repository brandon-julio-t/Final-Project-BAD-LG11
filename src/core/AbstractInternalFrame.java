package core;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public abstract class AbstractInternalFrame extends JInternalFrame {
    public AbstractInternalFrame(String title, Dimension size) {
        JPanel contentPane = buildUI();
        contentPane.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        setContentPane(contentPane);

        setTitle(title);
        setResizable(true);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setSize(size);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    protected abstract JPanel buildUI();
}
