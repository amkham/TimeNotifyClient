package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Vector;

public class CreateNotificationView extends JFrame{
    private JTextField __nameTextField;
    private JTextField __msgTextField;
    private JComboBox<Integer> __hourComboBox;
    private JComboBox<Integer> __minComboBox;
    private JComboBox<Integer> __secComboBox;
    private JButton __createButton;
    private JButton __cancelButton;
    private JPanel __mainPanel;

    private final MainView __mainView;

    public CreateNotificationView(MainView mainView) throws HeadlessException {
        __mainView = mainView;
        setContentPane(__mainPanel);
        setLocationRelativeTo(null);
        pack();
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        initViewComponents(
                this::initCreateButton,
                this::initCancelButton,
                this::initHourComboBox,
                this::initMinComboBox,
                this::initSecComboBox);

        setVisible(true);


    }

    void initViewComponents(IViewComponent... views){
        Arrays.stream(views).forEach(IViewComponent::init);
    }

    void initCreateButton(){
        __createButton.addActionListener(e -> {
            String _name = __nameTextField.getText();
            String _msg = __msgTextField.getText();
            Integer _hour = (Integer) __hourComboBox.getSelectedItem();
            Integer _min = (Integer) __minComboBox.getSelectedItem();
            Integer _sec = (Integer) __secComboBox.getSelectedItem();
            __mainView.setNotificationData(_name, _msg, _hour, _sec, _min);
        });
    }

    void initCancelButton(){
        __cancelButton.addActionListener(e -> {
            setVisible(false);
        });
    }

    void initHourComboBox(){
        initComboBox(__secComboBox,  60);
    }

    void initMinComboBox(){
        initComboBox(__minComboBox,  60);
    }

    void initSecComboBox(){
        initComboBox(__hourComboBox,  24);
    }

    void initComboBox(JComboBox<Integer> jComboBox, int range){

        Vector<Integer> _range = new Vector<>();
        for (int i = 0; i <= range; i++) {
            _range.add(i);
        }
        DefaultComboBoxModel<Integer> _defaultComboBoxModel = new DefaultComboBoxModel<>(_range);
        jComboBox.setModel(_defaultComboBoxModel);
    }
}
