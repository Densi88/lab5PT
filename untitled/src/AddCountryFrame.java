import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddCountryFrame extends JFrame {
    private JButton exitButton=new JButton("Отмена");
    private JButton addButton=new JButton("Добавить");
    private JLabel nameLabel=new JLabel("Название");
    public   JTextField nameField=new JTextField();
    public AddCountryFrame(){
        setTitle("Добавить страну");
        setSize(320, 250);
        setVisible(true);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        exitButton.setBounds(200,170,100,25);
        add(exitButton);
        addButton.setBounds(90, 170, 100, 25);
        add(addButton);
        nameLabel.setBounds(110, 40, 100, 25);
        add(nameLabel);
        nameField.setBounds(110, 70, 100, 25);
        add(nameField);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.getInstance().closeAddCountryFrame();
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Controller.getInstance().addCountryToDb();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
