package xjtu.test;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import xjtu.task.SpringBootApplicationImpl;
import xjtu.task.controller.TaskController;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootApplicationImpl.class)
public class ApplicationTests {
    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new TaskController()).build();
    }

    @Test
    public void testTaskController() throws Exception {
        RequestBuilder request = null;


        request = get("/api/task/");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));


        request = post("/api/task/")
                .param("id", "1")
                .param("content", "Restful API homework")
                .param("createdTime", "2019-05-15T00:00:00Z");
        mvc.perform(request)
                .andExpect(content().string(equalTo("success")));

        request = post("/api/task/")
                .param("id", "2")
                .param("content", "Restful API 222222222222homework2222222222222")
                .param("createdTime", "2018-05-15T00:00:00Z22222222222222");
        mvc.perform(request)
                .andExpect(content().string(equalTo("success")));



        request = get("/api/task/");
        mvc.perform(request).andExpect(status().isOk()).andDo(print());

        System.out.println("-----------------测试---------------");
        request = get("/api/task/2");
        mvc.perform(request).andExpect(status().isOk()).andDo(print());

        System.out.println("------------------测试删除id为2的数据-----------------");

        request = delete("/api/task/2");
        mvc.perform(request)
                .andExpect(content().string(equalTo("success")));

        request = get("/api/task/");
        mvc.perform(request).andExpect(status().isOk()).andDo(print());
    }

}
