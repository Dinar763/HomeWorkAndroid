import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;


public class TestCalc {

    private AndroidDriver driver;
    private final String NAME_BUTTON = "com.android.calculator2:id/digit_";

    public void setUp() throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("deviceName", "192.168.65.101:5555");
        cap.setCapability("platformName", "Android");
        cap.setCapability("platformVersion", "9");
        cap.setCapability("appPackage","com.android.calculator2");
        cap.setCapability("appActivity","com.android.calculator2.Calculator");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
    }

    @Test
    public void testClickDigit() throws MalformedURLException {
        setUp();

        String str = "";
        for (int i = 0; i<10; i++) {
            MobileElement currentDigitButton = (MobileElement) driver.findElementById((NAME_BUTTON + i));
            currentDigitButton.click();
            Assert.assertEquals((str+i), currentDigitButton.getText());
            MobileElement delButton = (MobileElement) driver.findElementById(("com.android.calculator2:id/del"));
            delButton.click();
        }
    }
    @Test
    public void testMultiply() throws MalformedURLException {
        setUp();

        MobileElement currentButton = (MobileElement) driver.findElementById((NAME_BUTTON + 2));
        currentButton.click();

        driver.findElementById("com.android.calculator2:id/op_mul").click();
        driver.findElementById(NAME_BUTTON + 4).click();
        driver.findElementById("com.android.calculator2:id/eq").click();

        MobileElement result = (MobileElement) driver.findElementById("com.android.calculator2:id/result");

        Assert.assertEquals("8", result.getText());
    }
}
