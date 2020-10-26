package com.day.today;

import com.day.api.provider.YesterdayProvider;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DayTodayApplicationTests {
    @DubboReference
    private YesterdayProvider yesterdayProvider;

    @Test
    void contextLoads() {
        final String yesterDayTime = yesterdayProvider.getYesterDayTime();
        System.out.println(yesterDayTime);
    }

}
