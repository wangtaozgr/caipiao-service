package com.atao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.atao.base.support.MyBatisDao;

import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = "com.atao.caipiao.mapper", annotationClass = MyBatisDao.class)
@ComponentScan
@SpringBootApplication
public class BaseApiApplication implements CommandLineRunner {
	private Logger logger = LoggerFactory.getLogger(getClass());

	public static void main(String[] args) throws Exception {
		SpringApplication.run(BaseApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("服务启动完成!");
	}
}