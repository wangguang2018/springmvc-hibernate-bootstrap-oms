import com.alibaba.fastjson.JSON;
import com.wangguang.entity.User;
import com.wangguang.service.UserService;
import com.wangguang.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TestMyBatis {

    private static Logger logger = Logger.getLogger(TestMyBatis.class);

    private ApplicationContext ac = null;

    @Resource
    private UserService userService = null;

    @Before
	public void before() {
        System.out.println("before..................");
		ac = new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml");
	}

    @Test
    public void test1() {
        System.out.println("--------userService:"+userService);
        User user = userService.getUserById(1);
        logger.info(JSON.toJSONString(user));
    }
}
