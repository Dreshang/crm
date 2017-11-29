package com.shsxt.crm.base;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml","classpath:servlet-context.xml"})
public class BaseTest {

    @Test
    public void hello() {
        System.out.println("hello");
    }
	
}
