package com.springboot.train;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.springboot.train.util.HostUtils;

/**
 * @author lijintao
 */
@Controller 
@EnableWebMvc
@SpringBootApplication
public class Application extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		HostUtils.addHostBinding("127.0.0.1", "test.kyfw.12306.cn");
		String url = "http://test.kyfw.12306.cn/index";
		String cmd = "cmd.exe /c start " + url;
		try {
			Process proc = Runtime.getRuntime().exec(cmd);
			proc.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
