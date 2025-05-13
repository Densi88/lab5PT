import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CountriesFrame extends JFrame {
    String[] columns={"Название"};
    public DefaultTableModel countryTable=new DefaultTableModel(columns, 0);
    private JTable countriesTable=new JTable(countryTable);
    private JScrollPane scroll = new JScrollPane(countriesTable);
    private JButton exitButton=new JButton("Назад");
    private  JButton addButton=new JButton("Добавить");
    private JButton deleteButton=new JButton("Удалить");
    public CountriesFrame(){
        setTitle("Страны");
       setSize(320, 300);
       setVisible(true);
       setResizable(true);
       setLocationRelativeTo(null);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       setLayout(null);

       countriesTable.setBounds(0,0,260, 200);
       scroll.setBounds(1, 1, 300, 200);
       add(scroll);

       //exitButton.setSize(50, 50);
       exitButton.setBounds(210,225, 100, 25 );
       add(exitButton);

       addButton.setBounds(110, 225, 100, 25);
       add(addButton);

       deleteButton.setBounds(15, 225, 100, 25 );
       add(deleteButton);

       addButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               Controller.getInstance().createAddCountryFrame();
           }
       });

       exitButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               Controller.getInstance().closeCountriesFrame();
           }
       });
       deleteButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               Controller.getInstance().createDeleteCountryFrame();
           }
       });
    }
}
