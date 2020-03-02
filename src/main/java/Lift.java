import java.util.ArrayList;
import java.util.List;

public class Lift {
    private int currentFloor;
    private LiftDirection direction;
    private List<Integer> requestQueue = new ArrayList<>();

    public int getCurrentFloor() {
        return currentFloor;
    }

    public LiftDirection getDirection() {
        return direction;
    }

    public void callTo(int sourceFloor, LiftDirection direction) {
        currentFloor = sourceFloor;
        this.direction = direction;
    }

    public void requestTo(int destinationFloor) {
        requestQueue.add(destinationFloor);
        requestQueue.sort(Integer::compareTo);
    }

    public void goNext() {
        Integer nextFloor = requestQueue.get(0);
        direction = nextFloor > currentFloor ? LiftDirection.UP : LiftDirection.DOWN;
        this.currentFloor = nextFloor;
        requestQueue.remove(0);
    }

    public DoorsState getDoorsState() {
        return DoorsState.CLOSED;
    }
}
