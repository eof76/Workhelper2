package com.github.eof76.workhelper2;

import com.github.eof76.workhelper2.config.AppConfig;
import com.github.eof76.workhelper2.dal.entities.Singer;
import com.github.eof76.workhelper2.dal.service.SingerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

public class App {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(App.class);

        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        SingerService singerService = (SingerService)ctx.getBean("jpaSingerService");

        List<Singer> singers = singerService.findAll();
        for (Singer singer : singers) {
            System.out.println(singer);
        }
    }
}
