package com.home.springboot.data.jpa.repo;

import com.home.springboot.data.jpa.model.Assignee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;

//This is for debugging purposes - to access H2 web-console
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class AssigneeRepositoryTest {

    @Autowired
    private AssigneeRepository assigneeRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private JdbcTestUtil jdbcUtil;

    @Test
    public void shouldRetrieveDataFromH2() {
        jdbcUtil.insertProject(1, "Project 1");
        jdbcUtil.insertAssignee(10,"Michael Blomkvist", 1);

        List<Assignee> all = assigneeRepository.findAll();

        assertThat(all, hasSize(1));
        assertThat(all.get(0).getProject().getName(), is("Project 1"));
    }
}
