package ru.otus;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import org.junit.platform.engine.*;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;


public class Otus {
    private static final Logger logger = LogManager.getLogger("ru.otus.Otus");
    public static void main(String[] args) {
        logger.info("Running tests!");
        final LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(
                        selectPackage("src/test/java/ru/test")
                )
                .build();

        final Launcher launcher = LauncherFactory.create();

        final boolean pathContainsTests = launcher.discover(request).containsTests();
        if (!pathContainsTests) {
            logger.error("This path is invalid or folder doesn't consist tests");
        }

        final SummaryGeneratingListener listener = new SummaryGeneratingListener();

        launcher.execute(request, listener);

        final TestExecutionSummary summary = listener.getSummary();

        final long containersFoundCount = summary.getContainersFoundCount();
        System.out.println("containers Found Count  " + containersFoundCount);

        final long containersSkippedCount = summary.getContainersSkippedCount();
        System.out.println("containers Skipped Count  " + containersSkippedCount);

        final long testsFoundCount = summary.getTestsFoundCount();
        System.out.println("tests Found Count  " + testsFoundCount);

        final long testsSkippedCount = summary.getTestsSkippedCount();
        System.out.println("tests Skipped Count  " + testsSkippedCount);
    }
}
