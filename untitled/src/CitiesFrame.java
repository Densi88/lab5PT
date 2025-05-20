import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CitiesFrame extends JFrame {
    String[] columns={"Название", "Популяция"};
    public DefaultTableModel cities=new DefaultTableModel(columns, 0);
    private JTable citiesTable=new JTable(cities);
    private JScrollPane scroll = new JScrollPane(citiesTable);
    private JButton exitButton=new JButton("Назад");
    private JButton addButton=new JButton("Добавить");
    private JButton searchButton=new JButton("Поиск по стране");
    private JButton deleteButton=new JButton("Удалить");
    public JTextField searchingCountryField=new JTextField();
    public CitiesFrame(){
       setTitle("Города");
        setSize(520, 300);
        setVisible(true);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        scroll.setBounds(1, 1, 500, 200);
        add(scroll);

        citiesTable.setBounds(0, 0, 260, 190);

        exitButton.setBounds(445, 225, 70, 25);
        add(exitButton);
        addButton.setBounds(335, 225, 100, 25);
        add(addButton);
        searchButton.setBounds(110, 225, 220, 25);
        add(searchButton);
        searchingCountryField.setBounds(110,200, 220, 25 );
        add(searchingCountryField);
        deleteButton.setBounds(5, 225,100, 25);
        add(deleteButton);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.getInstance().closeCitiesFrame();
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.getInstance().createAddCityFrame();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.getInstance().createDeleteCityFrame();
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DataAccesObject.countrySearch();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                Controller.getInstance().clearCitiesTable();
                Controller.getInstance().fillSearchingCities();
            }
        });
    }
}
