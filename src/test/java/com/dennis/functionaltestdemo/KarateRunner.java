package com.dennis.functionaltestdemo;

import com.intuit.karate.junit5.Karate;

public class KarateRunner {
    @Karate.Test
    Karate testAll() {
        return Karate.run("classpath:functional_tests.feature").relativeTo(getClass());
    }
}
