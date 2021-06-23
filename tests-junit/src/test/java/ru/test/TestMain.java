package ru.test;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

public class TestMain {

    public static void main(String[] args) {
        JUnitCore engine = new JUnitCore();
        engine.addListener(new TextListener(System.out)); // required to print reports
        engine.run(SuiteTestClass.class);

    }

}
