import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import static java.awt.Color.HSBtoRGB;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Menu extends JFrame implements ActionListener {

    private final Font FontMetrics;
    JMenuBar menuBar;
    JButton szkukaj , bWybierzkolor;
    JMenu menuPlik , menuNarzedzia, menuPomoc, menuOpcje;
    JMenuItem mOtworz , mZapisz, mWyjscie , mNarz1 , mNarz2 , mOprogrmaie , mOpcaj1, mpKopiuj , mpWklej , mpDolocz , mpSzukaj;
    JTextField tSzukany;
    JCheckBoxMenuItem   chOpcja2;
    JTextArea notatnik;
    JPopupMenu popup;
    String wybranytext;
    JComboBox colorBox;


    public Menu(){


        JFrame frame = new JFrame();
        setTitle("Notatnik");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLayout(null);
       setVisible(true);


        menuBar = new JMenuBar();

        menuPlik = new JMenu("Plik");

        mOtworz = new JMenuItem("Otwórz");
        mOtworz.addActionListener(this);
        mZapisz = new JMenuItem("Zapisz");
        mZapisz.addActionListener(this);
        mWyjscie = new JMenuItem("Wyjście");

        mWyjscie.addActionListener(this);
        mWyjscie.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));

        menuPlik.add(mOtworz);
        menuPlik.addSeparator();
        menuPlik.add(mZapisz);
        menuPlik.addSeparator();
        menuPlik.add(mWyjscie);

        menuNarzedzia = new JMenu("Narzędzia");
        mNarz1 = new JMenuItem("Narz 1");
        mNarz2 = new JMenuItem("Narz 2");
        mNarz2.addActionListener(this);


        menuNarzedzia.add(mNarz1);
        menuNarzedzia.addSeparator();
        menuNarzedzia.add(mNarz2);
        menuNarzedzia.addSeparator();


        menuOpcje = new JMenu("Opcje");
        mOpcaj1 = new JMenuItem("Opcja_1");
        chOpcja2 = new JCheckBoxMenuItem("Opcaj_2");
        chOpcja2.addActionListener(this);

        menuOpcje.add(mOpcaj1);
        menuOpcje.addSeparator();
        menuOpcje.add(chOpcja2);

        menuNarzedzia.add(menuOpcje);



        menuPomoc = new JMenu("Pomoc");

        mOprogrmaie = new JMenuItem("O programie");
        menuPomoc.add(mOprogrmaie);
        mOprogrmaie.addActionListener(this);


        setJMenuBar(menuBar);
        menuBar.add(menuPlik);
        menuBar.add(menuNarzedzia);
        menuBar.add(menuPomoc);

        notatnik = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(notatnik);
        scrollPane.setBounds(50, 50 , 400 ,400);
        add(scrollPane);

        tSzukany = new JTextField();
        tSzukany.setBounds(50, 500, 100 ,20);
        add(tSzukany);



        float hue  = 1.5f;
        float saturation= 5.0f;
        float brightness = 3.7f;




        szkukaj = new JButton("Szukaj");
        szkukaj.setBounds(200, 500, 100, 20);
        szkukaj.setBackground(new Color(Color.HSBtoRGB(saturation , hue , brightness)));

        add(szkukaj);
        szkukaj.addActionListener(this);




        popup = new JPopupMenu();
        mpKopiuj = new JMenuItem("Kopiuj");
        mpKopiuj.addActionListener(this);
        mpWklej = new JMenuItem("Wklej");
        mpWklej.addActionListener(this);
        mpDolocz = new JMenuItem("dołącz");
        mpDolocz.addActionListener(this);

        popup.add(mpKopiuj);
        popup.add(mpWklej);
        popup.add(mpDolocz);

        notatnik.setComponentPopupMenu(popup);

        colorBox = new JComboBox();
        colorBox.setBounds(500 ,50 , 100 ,20);
        colorBox.addItem("czarny");
        colorBox.addItem("czerwony");
        colorBox.addItem("zielony");
        colorBox.addItem("niebieski");
        colorBox.addItem("żułty");
        add(colorBox);
        colorBox.addActionListener(this);


        FontMetrics = null;

        bWybierzkolor = new JButton("Wybierz Kolor");
        bWybierzkolor.setBounds(350, 500, 150, 20);
        add(bWybierzkolor);
        bWybierzkolor.addActionListener(this);


    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object z = e.getSource();
        if (z == mOtworz){
            JFileChooser fc = new JFileChooser();
            if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                File plik = fc.getSelectedFile();
                try {

                    Scanner skaner = new Scanner(plik);
                    while (skaner.hasNext()) {
                        notatnik.append(skaner.nextLine() + "\n");
                    }

                } catch (FileNotFoundException e1) {

                e1.printStackTrace();


                }
            }
        } else if (z == mZapisz) {
               JFileChooser fc = new JFileChooser();
               if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                   File plik =  fc.getSelectedFile();
                   try {

                       PrintWriter pw = new PrintWriter(plik);
                       Scanner scanner = new Scanner(notatnik.getText());

                       while(scanner.hasNext()){

                           pw.println(scanner.nextLine() + "\n");

                           pw.close();

                       }

                   } catch (FileNotFoundException ex) {

                       throw new RuntimeException(ex);

                   }
               }

        } else if (z == mWyjscie){
            int odp = JOptionPane.showConfirmDialog(null, "Czy chcesz wyjść ?", "Pytaie", JOptionPane.YES_NO_OPTION);

            if (odp == JOptionPane.YES_NO_OPTION){
                dispose();
            } else if (odp == JOptionPane.NO_OPTION) {
                JOptionPane.showConfirmDialog(null, "wiedzałem ..." ,"nw" , JOptionPane.DEFAULT_OPTION);
            }else if(odp == JOptionPane.CANCEL_OPTION){
                JOptionPane.showConfirmDialog(null ,"Tak nie robimy" , "wn", JOptionPane.DEFAULT_OPTION);
            }
        }else if (z == chOpcja2){
            if (chOpcja2.isSelected()){
                mNarz1.setEnabled(true);

            }else if (!chOpcja2.isSelected()){
                mNarz1.setEnabled(false);
            }
        }else if(z == mOprogrmaie){

            JOptionPane.showMessageDialog(this,"Program desmonstruje użycie menu w Java \n wersja 1.0");

        } else if (z == mNarz1){

            String sMetry = JOptionPane.showInputDialog("podaj Długość w metrach");
            double metry  = Double.parseDouble(sMetry);

            Double stopy = metry/0.3048;
            String sStopy = String.format("%.2f" , stopy);

            JOptionPane.showMessageDialog(null , metry + " metrów: " + sStopy + " Stóp");
        } else if (z == szkukaj) {

            String tekst = notatnik.getText();
            String szukane = tSzukany.getText();
            String wystąpienia = "";
            int i = 0;
            int inedx;
            int startIndex = 0;

            while ((inedx = tekst.indexOf(szukane , startIndex)) != -1){
                startIndex = inedx + szukane.length();
                i++;
                wystąpienia = wystąpienia + "" + inedx;
            }
            JOptionPane.showMessageDialog(null, szukane + " wystąpiło: " + i + " razy: " + wystąpienia);

        }else if(z == mpKopiuj){
            wybranytext = notatnik.getSelectedText();
        } else if (z == mpWklej) {
            notatnik.insert(wybranytext, notatnik.getCaretPosition());
        }else if(z ==mpDolocz){
            notatnik.append("\n" + wybranytext);
        } else if (z == colorBox) {
            
            String color = colorBox.getSelectedItem().toString();
            if (color.equals("zielony")){
                
                notatnik.setForeground(Color.GREEN);
                
            } else if (color.equals("czarny")) {
                
                notatnik.setForeground(Color.BLACK);
                
            } else if (color.equals("czerwony")) {
                
                notatnik.setForeground(Color.RED);
                
            } else if (color.equals("niebieski")) {
                
                notatnik.setForeground(Color.BLUE);
                
            } else if (color.equals("żułty")) {
                
                notatnik.setForeground(Color.yellow);
                
            }
        } else if (z == bWybierzkolor) {
            Color wybranyKolor = JColorChooser.showDialog(null , "Wybur koloru", Color.GREEN);
            notatnik.setForeground(wybranyKolor);
        }

    }

    @Override
    public boolean isFocusable() {
        return super.isFocusable();
    }

    public static void main(String[] args) {

      Menu menu = new Menu();
        
    }


}
