package presenter;

import com.google.gson.Gson;
import model.SocketService;
import org.jetbrains.annotations.NotNull;
import responce.Notification;
import responce.Response;
import responce.Time;
import view.IViewContract;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Presenter {
    private final List<Notification> __notifications = new ArrayList<>();
    private SocketService __socketService;
    private IViewContract __iViewContract;
    private final Gson _gson = new Gson();


    public void bindView(IViewContract viewContract){
        __iViewContract = viewContract;
    }

    public void start(){
        __iViewContract.start();
    }
    public void createConnection(String host, int port){
        try {
            __socketService = new SocketService(host, port);
            __socketService.setListener(this::updateView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createNotification(String name, String msg, int hour, int min, int sec){
        Notification _notification = new Notification(name, msg, hour, min, sec);
        String _msg = _gson.toJson(_notification);
        __socketService.writeMessage(_msg);
    }

    void updateView(@NotNull Response response){


        Time _time = response.getTime();
        List<Notification> _notifications = Arrays.stream(response.getNotifications()).toList();
        _notifications
                .stream()
                .filter(i -> i.getHour() == _time.getHour())
                .filter(i-> i.getMin() == _time.getMin())
                .filter(i-> i.getSec() == _time.getSec())
                .forEach(i-> __iViewContract.alarm(i));
        __iViewContract.updateTimer(_time.toString());
        __iViewContract.updateNotifications(_notifications);



    }



}
