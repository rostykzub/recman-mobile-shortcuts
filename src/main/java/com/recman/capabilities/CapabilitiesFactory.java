package com.recman.capabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

/**
 * Factory class for creating Appium driver capabilities for mobile testing
 */
public class CapabilitiesFactory {

    private static final String APPIUM_SERVER_URL = System.getProperty("appium.url", "http://localhost:4723");
    private static final String ANDROID_AUTOMATION_NAME = "UiAutomator2";
    private static final String IOS_AUTOMATION_NAME = "XCUITest";

    /**
     * Creates Android capabilities using UiAutomator2Options (Appium 10.x compatible)
     *
     * @param appPath Path to the APK file
     * @param deviceName Name of the Android device or emulator
     * @return UiAutomator2Options configured for Android testing
     */
    public static UiAutomator2Options createAndroidCapabilities(String appPath, String deviceName) {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(deviceName);
        options.setApp(appPath);
        options.setAutomationName(ANDROID_AUTOMATION_NAME);
        options.setNewCommandTimeout(Duration.ofSeconds(300));
        options.setAutoGrantPermissions(true);
        return options;
    }

    /**
     * Creates iOS capabilities using XCUITestOptions (Appium 10.x compatible)
     *
     * @param appPath Path to the .app file
     * @param deviceName Name of the iOS device or simulator
     * @return XCUITestOptions configured for iOS testing
     */
    public static XCUITestOptions createIOSCapabilities(String appPath, String deviceName) {
        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName(deviceName);
        options.setApp(appPath);
        options.setAutomationName(IOS_AUTOMATION_NAME);
        options.setNewCommandTimeout(Duration.ofSeconds(300));
        options.setAutoAcceptAlerts(true);
        return options;
    }

    /**
     * Creates an Android driver with the specified capabilities
     *
     * @param appPath Path to the APK file
     * @param deviceName Name of the Android device or emulator
     * @return AndroidDriver instance
     * @throws MalformedURLException if the Appium server URL is invalid
     */
    public static AndroidDriver createAndroidDriver(String appPath, String deviceName) throws MalformedURLException {
        UiAutomator2Options options = createAndroidCapabilities(appPath, deviceName);
        return new AndroidDriver(new URL(APPIUM_SERVER_URL), options);
    }

    /**
     * Creates an iOS driver with the specified capabilities
     *
     * @param appPath Path to the .app file
     * @param deviceName Name of the iOS device or simulator
     * @return IOSDriver instance
     * @throws MalformedURLException if the Appium server URL is invalid
     */
    public static IOSDriver createIOSDriver(String appPath, String deviceName) throws MalformedURLException {
        XCUITestOptions options = createIOSCapabilities(appPath, deviceName);
        return new IOSDriver(new URL(APPIUM_SERVER_URL), options);
    }
}
