package cn.kuoyio.common.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SnowflakeIdUtilTest {

    @Test
    public void given_snowflake_id_generator_when_generate_id_then_return_positive_id() {
        long id = SnowflakeIdUtil.generateId();
        Assertions.assertTrue(id > 0, "生成的ID应该大于0");
    }

    @Test
    public void given_snowflake_id_generator_when_generate_multiple_ids_then_all_ids_should_be_unique() {
        Set<Long> ids = new HashSet<>();
        int count = 1000;

        for (int i = 0; i < count; i++) {
            long id = SnowflakeIdUtil.generateId();
            ids.add(id);
        }

        Assertions.assertEquals(count, ids.size(), "生成的ID数量应该等于预期数量");
    }

    @Test
    public void given_multiple_threads_when_generate_ids_concurrently_then_all_ids_should_be_unique() throws InterruptedException {
        int threadCount = 10;
        int idsPerThread = 1000;
        Set<Long> ids = new HashSet<>();
        CountDownLatch latch = new CountDownLatch(threadCount);
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    for (int j = 0; j < idsPerThread; j++) {
                        synchronized (ids) {
                            ids.add(SnowflakeIdUtil.generateId());
                        }
                    }
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executorService.shutdown();

        Assertions.assertEquals(threadCount * idsPerThread, ids.size(), "并发生成的ID应该全部唯一");
    }

    @Test
    public void given_snowflake_id_generator_when_generate_100k_ids_then_complete_within_5_seconds() {
        int count = 100000;
        long startTime = System.currentTimeMillis();
        
        for (int i = 0; i < count; i++) {
            SnowflakeIdUtil.generateId();
        }
        
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        
        System.out.printf("生成 %d 个ID耗时: %d ms, 平均每个ID生成时间: %.3f ms%n", 
            count, duration, (double) duration / count);
        
        Assertions.assertTrue(duration < 5000, "生成10万个ID应该在5秒内完成");
    }
}