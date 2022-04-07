package view;

import javax.swing.*;
import java.util.Arrays;

public class ConnectedView extends JFrame {
    private JTextField __portTextField;
    private JPanel __mainPanel;
    private JButton __connectedBtn;
    private JTextField __hostTextField;

    private String __host;
    private int __port;

    private MainView __mainView;

    public ConnectedView(MainView mainView){
        __mainView = mainView;
        setContentPane(__mainPanel);
        setLocationRelativeTo(null);
        setSize(300,200);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initViewComponents(this::initConnectedButton);
        setVisible(true);
    }

    void initViewComponents(IViewComponent... views){
        Arrays.stream(views).forEach(IViewComponent::init);
    }

    void initConnectedButton(){
        __connectedBtn.addActionListener(e -> {
            __port = Integer.parseInt(__portTextField.getText());
            __host = __hostTextField.getText();
            __mainView.setConnectionData(__host, __port);
            setVisible(false);
        });
    }
}
