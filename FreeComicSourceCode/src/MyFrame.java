import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MyFrame extends JFrame implements ActionListener {
    private JButton button;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    static JTextField textField;
    static JLabel label;
    static JLabel fail;
    private ImageIcon backgroundIcon = new ImageIcon("src\\Images\\fondo.jpg");
    private ImageIcon checkBox = new ImageIcon("src\\Images\\cruzar.png");
    private ImageIcon checkBoxSelected = new ImageIcon("src\\Images\\garrapata.png");
    private ImageIcon buttonIcon = new ImageIcon("src\\Images\\boton.png");
    static ImageIcon invalidIcon = new ImageIcon("src\\Images\\invalid.png");
    static ImageIcon successIcon = new ImageIcon("src\\Images\\sucess.png");



    MyFrame(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.setResizable(false);
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0,0,500,500);

        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setBounds(0,0,500,500);
        JLabel background = new JLabel();
        background.setIcon(backgroundIcon);
        backgroundPanel.add(background);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0,0,500,500);
        panel.setOpaque(false);

        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(null);
        messagePanel.setBounds(0,0,500,500);
        messagePanel.setOpaque(false);


        button = new JButton();
        button.setBounds(45,325,200,150);
        button.setContentAreaFilled(false);
        button.setBorder(null);
        button.setFocusable(false);
        button.setIcon(buttonIcon);
        button.addActionListener(this);


        textField = new JTextField();
        textField.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        textField.setBounds(205,277,200,25);



        checkBox1 = new JCheckBox("All in one");
        checkBox1.setIcon(checkBox);
        checkBox1.setSelectedIcon(checkBoxSelected);
        checkBox1.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        checkBox1.setFocusable(false);
        checkBox1.setOpaque(false);
        checkBox1.setBounds(280,310,100,20);

        checkBox2 = new JCheckBox("Only one");
        checkBox2.setIcon(checkBox);
        checkBox2.setSelectedIcon(checkBoxSelected);
        checkBox2.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        checkBox2.setFocusable(false);
        checkBox2.setOpaque(false);
        checkBox2.setBounds(280,330,100,20);

        label = new JLabel();
        label.setFont(new Font("Comic Sans MS", Font.BOLD, 35));
        label.setBounds(350,230,50,50);


        fail = new JLabel("");
        fail.setBounds(210,155,200,200);

        messagePanel.add(fail);
        messagePanel.add(label);


        panel.add(checkBox2);
        panel.add(checkBox1);
        panel.add(textField);
        panel.add(button);


        layeredPane.add(backgroundPanel, Integer.valueOf(0));
        layeredPane.add(panel, Integer.valueOf(1));
        layeredPane.add(messagePanel, Integer.valueOf(2));

        this.add(layeredPane);
        this.setLayout(null);
        this.setVisible(true);
        fill();
    }

    public void fill(){
        while(true){
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (index.end){label.setText("");}
            else{label.setText(Integer.toString(index.chap-1)); }

        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==button){
            index.works =true;
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showSaveDialog(null);
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());


            index.url = textField.getText();
            index.allInOne = checkBox1.isSelected();
            index.soloTomo = checkBox2.isSelected();

            if (!index.allInOne&&!index.soloTomo){
                if (!file.exists()){ file.mkdirs(); index.path = file.getPath(); }
            }
            else{ index.path = file.getParent(); }

            FrameThread frameThread = new FrameThread();
            frameThread.start();
            textField.setText("Downloading...");
        }
    }
}
