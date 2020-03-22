package com.home.retry.business;

import com.home.retry.business.BusinessRepository;
import com.home.retry.business.Retryer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class RetryerTest {

    @MockBean
    private BusinessRepository businessRepository;

    @Autowired
    private Retryer retryer;

    @Test
    public void shouldSucceedAfter3Attempts() {
        when(businessRepository.saveToRepo()).thenThrow(new RuntimeException("Test"))
                .thenThrow(new RuntimeException("Test 2"))
                .thenReturn("Success");
        String res = retryer.methodWithRetry("custom param from test");

        assertThat(res, is("Success"));
        verify(businessRepository, times(3)).saveToRepo();
    }

    @Test
    public void shouldFailAfter4Attempts() {
        when(businessRepository.saveToRepo()).thenThrow(new RuntimeException("Test 1"));

        String res = retryer.methodWithRetry("custom param from test");

        assertThat(res, is("Recovered"));
        verify(businessRepository, times(4)).saveToRepo();
    }
}
