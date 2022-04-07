package view;

import responce.Notification;
import presenter.Presenter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class MainView extends JFrame implements IViewContract {
    private JPanel __mainPanel;
    private JButton __createButton;
    private JPanel __notificationPanel;
    private JLabel __timerLabel;
    private JTable __table1;
    private  DefaultTableModel _tableModel;

    private final Presenter __presenter;


    public MainView(Presenter presenter) {
        setContentPane(__mainPanel);
        setLocation(200,200);
        setSize(800,700);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents(this::initCreateButton);
        __presenter = presenter;

    }

    public void start(){
        setVisible(true);
        setEnabled(false);
        new ConnectedView(this);
    }



    public void setConnectionData(String host, int port){
        __presenter.createConnection(host, port);
        setEnabled(true);

    }

    public void setNotificationData(String name, String msg, int hour, int min, int sec){
        __presenter.createNotification(name, msg, hour, min, sec);
    }

    @Override
    public void updateTimer(String time){
        SwingUtilities.invokeLater(()-> __timerLabel.setText(time));
    }

    @Override
    public void updateNotifications(List<Notification> notifications){

        SwingUtilities.invokeLater(() -> {
            _tableModel = new DefaultTableModel(new Object[]
                    {
                            "Название",
                            "Текст",
                            "Время"
                    }, 0);

            notifications.forEach(n -> _tableModel.addRow(new Object[]
                    {
                            n.getName(),
                            n.getMessage(),
                            n.getHour() + ":" + n.getMin() + ":" + n.getSec()
                    }));

            __table1.setModel(_tableModel);
        });



    }


    @Override
    public void alarm(Notification notification) {
        JDialog d = new JDialog(getFrames()[0], "Error");
        d.setTitle("Уведомление");
        d.setLocationRelativeTo(null);
        d.setSize(150, 100);
        JLabel l = new JLabel(notification.getMessage());
        l.setBorder(new EmptyBorder(10,10,10,10));
        d.add(l);
        pack();
        d.setVisible(true);
    }

    void initComponents(IViewComponent... components){
        Arrays.stream(components).forEach(IViewComponent::init);
    }

    void initCreateButton(){
        __createButton.addActionListener(e -> {
            new CreateNotificationView(this);
        });
    }



}
