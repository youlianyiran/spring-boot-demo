package com.atian.demo.web;

import com.atian.demo.config.JdbcConfig;
import com.atian.demo.exception.MyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xutiantian on 2017/3/2.
 */
@RestController
public class RestfulController {
    @Autowired
    private JdbcConfig jdbcConfig;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/")
    public String index() {
        logger.info("hello-------");
        return jdbcConfig.getName();
    }

    @RequestMapping("/test")
    public String error() throws MyException {
        throw new MyException("发生错误");
    }

}