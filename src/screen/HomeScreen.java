package screen;

import objects.ComicFactory;
import objects.ComicWeb;
import threads.ConverterThread;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HomeScreen extends JFrame {

    private final ImageIcon backgroundIcon = new ImageIcon("assets\\images\\background.png");
    private final ImageIcon whiteIcon = new ImageIcon("assets\\images\\whiteDot.png");
    private final ImageIcon pinkIcon = new ImageIcon("assets\\images\\pinkDot.png");
    private final ImageIcon icon = new ImageIcon("img\\icon.png");
    int option = 1;
    private final JTextField textField;
    private final JLabel button;
    private final JLabel advise;

    private final JLabel one;
    private final JLabel three;
    private final JLabel all;

    public static final int WIDTH = 1632;
    public static final int HEIGHT = 950;
    public static final Rectangle FULL_SCREEN = new Rectangle(0,0, WIDTH, HEIGHT);


    public HomeScreen(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Free Comic App");
        this.setLayout(null);
        this.setResizable(false);
        this.setIconImage(icon.getImage());
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(FULL_SCREEN);

        /* BACKGROUND LAYER */
        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setBounds(FULL_SCREEN);

        JLabel backgroundContainer = new JLabel();
        backgroundContainer.setBounds(FULL_SCREEN);
        backgroundContainer.setIcon(backgroundIcon);
        backgroundPanel.add(backgroundContainer);

        layeredPane.add(backgroundPanel, Integer.valueOf(1));

        /* TEXTFIELD LAYER */
        JPanel componentsPanel = new JPanel();
        componentsPanel.setBounds(FULL_SCREEN);
        componentsPanel.setLayout(null);
        componentsPanel.setOpaque(false);

        textField = new JTextField();
        textField.setBounds(506, 577, 670, 50);
        textField.setOpaque(false);
        textField.setForeground(new Color(0xFFFFFF));
        textField.setFont(new Font("Comic Sans", Font.BOLD, 18));
        textField.setBorder(null);
        textField.setToolTipText("Paste the URL");
        componentsPanel.add(textField);

        button = new JLabel();
        button.setBounds(682, 776, 245, 59);
        button.setOpaque(true);
        button.setBackground(new Color(0x0001123, true));
        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                System.out.println(textField.getText());
                onButtonPress();
            }
            @Override
            public void mousePressed(MouseEvent e) {

            }
            @Override
            public void mouseReleased(MouseEvent e) {

            }
            @Override
            public void mouseEntered(MouseEvent e) {

            }
            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        componentsPanel.add(button);

        advise = new JLabel();
        advise.setBounds(460, 630, 670, 50);
        componentsPanel.add(advise);

        one = new JLabel();
        one.setBounds(502, 703, 30, 30);
        one.setIcon(pinkIcon);
        one.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(one.getIcon()== whiteIcon){
                    one.setIcon(pinkIcon);
                    three.setIcon(whiteIcon);
                    all.setIcon(whiteIcon);
                    option = 1;
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {

            }
            @Override
            public void mouseReleased(MouseEvent e) {

            }
            @Override
            public void mouseEntered(MouseEvent e) {

            }
            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        componentsPanel.add(one);

        three = new JLabel();
        three.setBounds(696, 703, 30, 30);
        three.setIcon(whiteIcon);
        three.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(three.getIcon()== whiteIcon){
                    three.setIcon(pinkIcon);
                    one.setIcon(whiteIcon);
                    all.setIcon(whiteIcon);
                    option = 2;
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {

            }
            @Override
            public void mouseReleased(MouseEvent e) {

            }
            @Override
            public void mouseEntered(MouseEvent e) {

            }
            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        componentsPanel.add(three);

        all = new JLabel();
        all.setBounds(926, 703, 30, 30);
        all.setIcon(whiteIcon);
        all.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(all.getIcon()== whiteIcon){
                    all.setIcon(pinkIcon);
                    three.setIcon(whiteIcon);
                    one.setIcon(whiteIcon);
                    option = 3;
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {

            }
            @Override
            public void mouseReleased(MouseEvent e) {

            }
            @Override
            public void mouseEntered(MouseEvent e) {

            }
            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        componentsPanel.add(all);


        layeredPane.add(componentsPanel, Integer.valueOf(2));

        this.add(layeredPane);
        this.setVisible(true);
    }

    public static void failAdvise(JLabel advise, JFrame frame){
        advise.setForeground(new Color(0xDA3D59));
        advise.setText("Wrong URL, please try again. ");
        frame.repaint();
    }
    public static void sucessAdvise(JLabel advise, JFrame frame){
        advise.setForeground(new Color(0x64DA3D));
        advise.setText("Sucess!! Check your Desktop ");
        frame.repaint();
    }
    public static void waitAdvise(JLabel advise, JFrame frame, String text){
        advise.setForeground(new Color(0x3DB5DA));
        advise.setText(text);
        frame.repaint();
    }

    public void onButtonPress(){
        System.out.println("Starting Process");
        String url = textField.getText();
        ComicWeb comic;

        if(url==null||url.isEmpty()){
            failAdvise(advise, this);
            return;
        }
        comic = ComicFactory.createComicWeb(url);
        if(comic == null ){
            failAdvise(advise, this);
            return;
        }

        ConverterThread thread = new ConverterThread(comic, option, this, advise);
        thread.start();

    }


}