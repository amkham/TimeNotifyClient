import view.ConnectedView;
import presenter.Presenter;
import view.MainView;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        //createClient();
        createClient();

    }

    private static void createClient(){
        Presenter _presenter = new Presenter();
        MainView _mainView = new MainView(_presenter);
        _presenter.bindView(_mainView);
        _presenter.start();
    }
}
