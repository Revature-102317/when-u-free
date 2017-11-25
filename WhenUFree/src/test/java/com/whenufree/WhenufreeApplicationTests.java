package com.whenufree;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
<<<<<<< HEAD

@RunWith(SpringRunner.class)
@SpringBootTest
public class WhenufreeApplicationTests {

	@Test
	public void contextLoads() {
	}
=======
import org.springframework.test.context.TestPropertySource;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:application.properties")
public class WhenufreeApplicationTests {

    @Test
    public void contextLoads() {
    }
>>>>>>> 78cc519364ce84a24e404c29a15819d0438042fa

}
