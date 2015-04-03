package zx.soft.api.adduser.demo;

import com.google.api.services.plus.model.Person;
import zx.soft.api.adduser.gplus.MonitorUserGplus;

import java.util.List;

/**
 * Created by jimbo on 15-4-3.
 */
public class GplusMonitorUserDemo {
    public static void main(String[] args) {
        MonitorUserGplus monitor = new MonitorUserGplus();
        List<Person> people = monitor.createFriendship("Jimbo Han");

        System.out.println(people);
    }
}

