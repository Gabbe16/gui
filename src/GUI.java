import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class GUI {
    private JPanel panel1;
    private JTextArea textArea1;
    private JButton searchButton;

    public GUI() {
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Search();
            }
        });
    }

    private void New() {
        textArea1.setText("");
    }

    private void Search() {
        int textRows = textArea1.getRows();
        String searchedPhrase = JOptionPane.showInputDialog("Write what letter/letters you want to search for");
    }

    private void open() {
        String filename = JOptionPane.showInputDialog("Write the name of the file you wish to open");
        filename = filename + ".txt";
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException ex) {
            return;
        }
        String nextLine = null;
        try {
            nextLine = in.readLine();
            while (nextLine != null) {
                textArea1.append(nextLine + "\n");
                nextLine = in.readLine();
            }
        } catch (IOException ex) {
            textArea1.setText("");
        }
    }

    public void save() {
        Save();
    }

    private void Save() {
        String filename = JOptionPane.showInputDialog("Name your text file");
        filename = filename + ".txt";
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Failed to save");
        }
        out.println(textArea1.getText());
        out.flush();
        out.close();
    }

    public JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenu menu, submenu;
        JMenuItem menuItem;
        JRadioButtonMenuItem rbMenuItem;
        JCheckBoxMenuItem cbMenuItem;

        //Create the menu bar.
        menuBar = new JMenuBar();

        //Build the first menu.
        menu = new JMenu("Arkiv");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");
        menuBar.add(menu);

        //a group of JMenuItems
        menuItem = new JMenuItem("Öppna",
                KeyEvent.VK_T);
        //menuItem.setMnemonic(KeyEvent.VK_T); //used constructor instead
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                open();
            }
        });

        menu.add(menuItem);

        menuItem = new JMenuItem("Spara");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        menuItem.setMnemonic(KeyEvent.VK_O);
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem();
        menuItem.setMnemonic(KeyEvent.VK_D);
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        menuItem = new JMenuItem("Nytt",
                KeyEvent.VK_T);
        //menuItem.setMnemonic(KeyEvent.VK_T); //used constructor instead
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                New();
            }
        });

        menu.add(menuItem);

        return menuBar;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("GUI");
        GUI gui = new GUI();
        frame.setContentPane(gui.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(gui.createMenuBar());
        frame.pack();
        frame.setVisible(true);
    }
}