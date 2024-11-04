package raf.dsw.classycraft.app.gui.swing.view;




import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.net.URL;
@Getter
@Setter
public class ScenaZaAboutUs extends JFrame {
    private JLabel l1;
    private GridLayout gp;
    private JLabel l2;
    public ScenaZaAboutUs(){
        init();
        editing();
    }

    private void editing() {
        Border border1 = BorderFactory.createLineBorder(Color.black, 3);
        gp = new GridLayout(2, 1);

        l1 = new JLabel("Đorđe Zlatanović");
        l1.setIcon(loadIcon("/images/djordje.png"));
        l1.setFont(new Font("Arial", Font.PLAIN, 30));
        l1.setForeground(Color.white);
        l1.setBackground(Color.black);
        l1.setOpaque(true);
        l1.setIconTextGap(20);
        l1.setBorder(border1);


        l2 = new JLabel("Ivana Nikolić");
        l2.setIcon(loadIcon("/images/Ivana.png"));
        l2.setFont(new Font("Arial", Font.PLAIN, 30));
        l2.setForeground(Color.white);
        l2.setBackground(Color.black);
        l2.setOpaque(true);
        l2.setBorder(border1);
        l2.setIconTextGap(20);


        this.add(l2);
        this.add(l1);
        this.setLayout(gp);
    }

    public Icon loadIcon(String fileName){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        Icon icon = null;
        URL ImageURL = getClass().getResource(fileName);
        if(ImageURL != null){
            Image img = new ImageIcon(ImageURL).getImage();
            Image newImg = img.getScaledInstance((screenWidth*2)/9 , screenHeight / 2,Image.SCALE_DEFAULT);
            icon = new ImageIcon(newImg);
        }
        else{
            System.err.println("Resource not found: " + fileName);
        }
        return icon;
    }

    private void init() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 2, (int) (screenHeight / 1.25));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("About us");
    }


}
