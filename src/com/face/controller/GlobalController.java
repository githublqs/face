package com.face.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mangofactory.swagger.annotations.ApiIgnore;
@ApiIgnore
@Controller
public class GlobalController {
	@RequestMapping("/errors/404")
	 public String notFound404() {
        return "errors/error404";
    }
	@RequestMapping("/errors/500")
	 public String notFound500() {
       return "errors/error500";
   }
}
