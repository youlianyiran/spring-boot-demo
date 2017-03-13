package com.atian.demo.web;

import com.atian.demo.exception.MyException;
import com.atian.demo.support.RedisSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xutiantian on 2017/3/2.
 */
@RestController
public class HelloController {
    @Autowired
    private RedisSupport redisSupport;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/")
    public String index() {
        logger.info("hello-------");
        return "mysql";
    }

    @RequestMapping("/test")
    public String error() throws MyException {
        throw new MyException("发生错误");
    }

    @RequestMapping("/redis")
    public String redis() {
        return redisSupport.get("config:indoor:fee:20161108204215926511805", String.class);
    }

}