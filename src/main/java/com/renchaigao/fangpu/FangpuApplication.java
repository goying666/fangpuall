package com.renchaigao.fangpu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
@MapperScan("com.renchaigao.fangpu.dao.mapper")
public class FangpuApplication {

	public static void main(String[] args) {
		SpringApplication.run(FangpuApplication.class, args);
	}
}
