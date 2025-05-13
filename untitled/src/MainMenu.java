import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainMenu  {
    private JButton countryButton=new JButton("Страны");
    private JButton cityButton=new JButton("Города");
    private JButton attractionsButton=new JButton("Достопримечательности");
    public MainMenu(){
        JFrame mainMenuFrame=new JFrame("Географический справочник");
        mainMenuFrame.setSize(320, 300);
        mainMenuFrame.setVisible(true);
        mainMenuFrame.setResizable(true);
        mainMenuFrame.setLocationRelativeTo(null);
        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainMenuFrame.setLayout(null);

        countryButton.setBounds(55, 35, 200, 50);
        cityButton.setBounds(55, 105, 200, 50);
        attractionsButton.setBounds(55, 175, 200, 50);

        // Добавление кнопок в mainMenuFrame
        mainMenuFrame.add(countryButton);
        mainMenuFrame.add(cityButton);
        mainMenuFrame.add(attractionsButton);

        countryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.getInstance().createCountriesWindow();
                Controller.getInstance().clearCountriesTable();
                Controller.getInstance().fillCountriesTable();
            }
        });
        cityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.getInstance().createCitiesWindow();
                Controller.getInstance().clearCitiesTable();
                Controller.getInstance().fillCitiesTable();
            }
        });
        attractionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.getInstance().createAttractionWindow();
                Controller.getInstance().clearAttractionsTable();
                Controller.getInstance().fillAttractionsTable();
            }
        });
    }
}
