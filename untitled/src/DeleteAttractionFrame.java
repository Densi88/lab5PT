import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class DeleteAttractionFrame extends JFrame {
    private JButton exitButton=new JButton("Отмена");
    private JButton deleteButton=new JButton("Удалить");
    private JLabel nameLabel=new JLabel("Название");
    public   JTextField nameField=new JTextField();
    public DeleteAttractionFrame(){
        setTitle("Удалить достопримечательность");
        setSize(320, 250);
        setVisible(true);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        exitButton.setBounds(200,170,100,25);
        add(exitButton);
        deleteButton.setBounds(90, 170, 100, 25);
        add(deleteButton);
        nameLabel.setBounds(110, 40, 100, 25);
        add(nameLabel);
        nameField.setBounds(110, 70, 100, 25);
        add(nameField);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.getInstance().closeDeleteAttractionFrame();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Controller.getInstance().deleteAttractionFromDb();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
