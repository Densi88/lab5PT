import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class CitiesFrame extends JFrame {
    String[] columns={"Название", "Популяция"};
    public DefaultTableModel cities=new DefaultTableModel(columns, 0);
    private JTable citiesTable=new JTable(cities);
    private JScrollPane scroll = new JScrollPane(citiesTable);
    private JButton exitButton=new JButton("Назад");
    private JButton addButton=new JButton("Добавить");
    private JButton searchButton=new JButton("Поиск по стране");
    private JButton deleteButton=new JButton("Удалить");
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

        citiesTable.setBounds(0, 0, 260, 200);

        exitButton.setBounds(445, 225, 70, 25);
        add(exitButton);
        addButton.setBounds(335, 225, 100, 25);
        add(addButton);
        searchButton.setBounds(110, 225, 220, 25);
        add(searchButton);
        deleteButton.setBounds(5, 225,100, 25);
        add(deleteButton);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.getInstance().closeCitiesFrame();
            }
        });
    }
}
