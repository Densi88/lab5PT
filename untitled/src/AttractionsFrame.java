import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AttractionsFrame extends JFrame {
    String[] columns={"Название"};
    public DefaultTableModel attractions=new DefaultTableModel(columns, 0);
    private JTable attractionsTable=new JTable(attractions);
    private JScrollPane scroll = new JScrollPane(attractionsTable);
    private JButton exitButton=new JButton("Назад");
    private JButton addButton=new JButton("Добавить");
    private JButton searchButton=new JButton("Поиск по городу");
    public JTextField searchingCityField=new JTextField();
    private JButton deleteButton=new JButton("Удалить");
    public AttractionsFrame(){
        setTitle("Достопримечательности");
        setSize(520, 300);
        setVisible(true);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        attractionsTable.setBounds(0, 0, 200, 200);
        scroll.setBounds(1, 1, 500, 200);
        add(scroll);

        exitButton.setBounds(445, 225, 70, 25);
        add(exitButton);
        addButton.setBounds(335, 225, 100, 25);
        add(addButton);
        searchButton.setBounds(110, 225, 220, 25);
        add(searchButton);
        searchingCityField.setBounds(110,200, 220, 25 );
        add(searchingCityField);
        deleteButton.setBounds(5, 225,100, 25);
        add(deleteButton);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.getInstance().closeAttractionsFrame();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.getInstance().createDeleteAttractionFrame();
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.getInstance().createAddAttractionFrame();
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DataAccesObject.citySearch();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                Controller.getInstance().clearAttractionsTable();
                Controller.getInstance().fillSearchingAttractions();
            }
        });

    }
}
