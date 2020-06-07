import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.vavr.CheckedRunnable;
import io.vavr.control.Try;
import org.junit.Test;

import java.time.Duration;
import java.util.Date;

public class R4Test {


    @Test
    public void test3(){
        RateLimiterConfig config = RateLimiterConfig.custom()
                //阈值刷新时间
                .limitRefreshPeriod(Duration.ofMillis(1000))
                //限制数
                .limitForPeriod(2)
                //冷却时间
                .timeoutDuration(Duration.ofMillis(1000))
                .build();
        RateLimiter rateLimiter = RateLimiter.of("CxgZzz", config);

        CheckedRunnable checkedRunnable = RateLimiter.decorateCheckedRunnable(rateLimiter, () -> {
            System.out.println(new Date());
        });
        Try.run(checkedRunnable)
                .andThenTry(checkedRunnable)
                .andThenTry(checkedRunnable)
                .andThenTry(checkedRunnable)
                .onFailure(t-> System.out.println("error"));

    }


    @Test
    public void test4(){
        RetryConfig retryConfig = RetryConfig.custom()
                //重试次数
                .maxAttempts(2)
                //重试间隔
                .waitDuration(Duration.ofMillis(500))
                //重试异常
                .retryExceptions(RuntimeException.class)
                .build();

        Retry retry = Retry.of("CxgZzz", retryConfig);

        Retry.decorateRunnable(retry, new Runnable() {
            int count = 0;
            @Override
            public void run() {
                if(count++ < 3){
                    System.out.println(count);
                    throw new RuntimeException();
                }
            }
        }).run();
    }
}
