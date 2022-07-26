package com.opcc.ccforum.consumer.action;

import com.opcc.ccforum.consumer.bean.input.IndexInput;
import com.opcc.ccforum.consumer.bean.output.IndexOutput;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ------------------------------------------------
 * Class HelloAction
 *
 * @author Glan
 * @description 入口实例
 * @date 2022-03-28
 * ------------------------------------------------
 */
@RestController
@RequestMapping("/hello")
public class HelloAction {
    
    @GetMapping("index")
    public IndexOutput index(@RequestBody IndexInput input){
        
        return new IndexOutput();
    }
    
    
}
