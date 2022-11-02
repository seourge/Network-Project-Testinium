import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import java.util.Optional;

public class ResultLogger implements TestWatcher {

    Log log = new Log();

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        TestWatcher.super.testDisabled(context, reason);
    }
    @Override
    public void testSuccessful(ExtensionContext context) {

        String testName = context.getDisplayName();
        log.infoLog(testName+ "------PASSED------");
    }
    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
    }
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {

        String testName = context.getDisplayName();
        String testFailCause = cause.getMessage();
        log.errorLog(testName+ "------FAİLED------" + testFailCause);
    }
}
