package TestDrive;

import java.io.IOException;

public class GoogleAPITestDrive {
    public String getStyle() {
        String value = getClass().getResource("../graphical/panes/web_view.css").toString();
        return value;
    }

    public static void main(String[] args) {
        GoogleAPITestDrive googleAPITestDrive = new GoogleAPITestDrive();
        System.out.println(googleAPITestDrive.getStyle());
    }
}
