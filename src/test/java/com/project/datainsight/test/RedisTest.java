package com.project.datainsight.test;

import com.project.datainsight.redis.RedisUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    RedisUtil redisUtil;

    @BeforeEach
    public void setUp() {
        // 테스트 전 Redis 데이터 초기화
        redisUtil = new RedisUtil(redisTemplate);
        redisUtil.removeData("testLoginKey");
    }

    @Test
    public void setData() {
        // 데이터 저장
        redisUtil.setData("testLoginKey", "loginKey", 1000);

        // 데이터 조회
        String value = redisUtil.getData("testLoginKey");
        assertThat(value).isEqualTo("loginKey");
    }

    @Test
    public void removeData(){
        // 데이터 삭제
        redisTemplate.delete("loginKey");

//         데이터가 삭제되었는지 확인
        String deletedValue = redisTemplate.opsForValue().get("loginKey");
        assertThat(deletedValue).isNull();
    }
}
