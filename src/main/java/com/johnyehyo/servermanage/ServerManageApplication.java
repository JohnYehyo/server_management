package com.johnyehyo.servermanage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

@SpringBootApplication
public class ServerManageApplication {

    public static void main(String[] args) {
//        SpringApplication.run(ServerManageApplication.class, args);
        SpringApplication springApplication = new SpringApplication(ServerManageApplication.class);
        springApplication.addListeners(new ApplicationPidFileWriter("./server_manage.pid"));
        springApplication.run(args);
    }

}
