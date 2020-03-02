import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LiftTest {

    private Lift lift = new Lift();

    @BeforeEach
    void setUp() {
        assertEquals(0, lift.getCurrentFloor());
    }

    @Test
    public void shouldArriveAtCalledFloor() {
        lift.callTo(2, LiftDirection.UP);
        assertEquals(2, lift.getCurrentFloor());
        lift.callTo(5, LiftDirection.UP);
        assertEquals(5, lift.getCurrentFloor());
    }

    @Test
    void shouldGoToFloorsByNextClosestComparedToPrevious() {
        lift.requestTo(5);
        lift.requestTo(9);
        lift.requestTo(6);

        lift.goNext();
        assertEquals(5, lift.getCurrentFloor());
        lift.goNext();
        assertEquals(6, lift.getCurrentFloor());
    }

    @Test
    void shouldChangeDirectionToUpIfNextFloorHigher() {
        lift.callTo(5, LiftDirection.UP);
        lift.requestTo(7);

        lift.goNext();

        assertEquals(LiftDirection.UP, lift.getDirection());
    }

    @Test
    void shouldChangeDirectionToDownIfNextFloorLower() {
        lift.callTo(5, LiftDirection.DOWN);
        lift.requestTo(2);

        lift.goNext();

        assertEquals(LiftDirection.DOWN, lift.getDirection());
    }

    @Test
    void shouldSetDirectionUpIfReqFloorIsHigherByCallWasLower() {
        lift.callTo(5, LiftDirection.DOWN);
        lift.requestTo(7);

        lift.goNext();

        assertEquals(LiftDirection.UP, lift.getDirection());
    }

    @Test
    void shouldHaveDoorsClosedWhenIdle() {
        assertEquals(DoorsState.CLOSED, lift.getDoorsState());
    }

    @Test
    void shouldOpenDoorsWhenLiftArrivesAtRequestedFloor() {
        lift.requestTo(1);

        lift.goNext();

        assertEquals(DoorsState.OPEN, lift.getDoorsState());
    }

}
