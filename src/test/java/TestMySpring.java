import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
public class TestMySpring {

   // private static Logger logger = Logger.getLogger(TestMySpring.class);

    private ApplicationContext ac = null;

    @Resource
    //private UserService userService = null;

    @Before
	public void before() {
        System.out.println("before..................");
		ac = new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml");
	}


}
