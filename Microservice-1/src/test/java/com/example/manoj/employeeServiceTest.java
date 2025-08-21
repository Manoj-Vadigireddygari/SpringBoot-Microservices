package com.example.manoj;

import org.junit.jupiter.api.*;


public class employeeServiceTest {

    public employeeServiceTest() {
        System.out.println("Junit5SampleTestCases() Instance Created");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("Before Each Method Called");
    }
    @BeforeAll
    static void beforeAll() {
        System.out.println("Before All Method Called");
    }

    @Test
    public void testmethod1() {
        System.out.println("testmethod1");
    }
    @Test
    public void testmethod2() {
        System.out.println("testmethod2");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("After Each Method Called");
    }
    @AfterAll
    static void afterAll() {
        System.out.println("After All Method Called");
    }
}
