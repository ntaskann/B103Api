package homework.herokuapp_smoketest;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({

        PostBooking.class,
        PutBooking.class,
        DeleteBooking.class,
        GetBooking.class,
})
public class Runner {


}
