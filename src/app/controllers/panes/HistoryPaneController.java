package app.controllers.panes;

public class HistoryPaneController extends SearchPaneController {
    private ContainerController state;

    public void initData(ContainerController state) {
        this.state = state;
    }
}
