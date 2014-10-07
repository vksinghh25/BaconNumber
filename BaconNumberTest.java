import org.junit.Test;

public class BaconNumberTest {
    @Test
    public void testPrintBaconNumber() throws Exception {

        BaconNumber baconNumber = new BaconNumber(new AWSMovieProvider());
        System.out.println(baconNumber.getBaconNumber("Brad Pitt"));

    }
}
