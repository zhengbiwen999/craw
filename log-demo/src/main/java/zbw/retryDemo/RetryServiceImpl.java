package zbw.retryDemo;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class RetryServiceImpl implements RetryService {

    @Override
    @Retryable(value = {RuntimeException.class, RuntimeException.class},
            maxAttempts = 5,
            backoff = @Backoff(value = 2000))
    public void testRetry() {
        System.out.println("do something");
        throw new RuntimeException("exception");
    }

    /**
     * 所有的重试失败后，调用此方法
     */
    @Recover
    public void recover(RuntimeException e) {
        System.out.printf("尽力了。。。。");
    }

}
