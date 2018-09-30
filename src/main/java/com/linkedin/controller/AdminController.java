package com.linkedin.controller;

import com.linkedin.entities.model.AdminXmlDto;
import com.linkedin.entities.model.UserDto;
import com.linkedin.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = AdminController.tag)
@RestController
@RequestMapping("/api/admin/")
public class AdminController {
  public static final String tag = "Admin Controller";

private final AdminService adminService;


  @Autowired
  public AdminController(AdminService adminService){
	this.adminService = adminService;
  }


  @ApiOperation(value = "Profile", notes = "Returns User's profile info", response = UserDto.class )
  @RequestMapping(value = "/get-xml/", method = RequestMethod.GET, produces=MediaType.APPLICATION_XML_VALUE, consumes = MediaType.ALL_VALUE )
  @ResponseBody
  public List<AdminXmlDto> getXml()throws  Exception {
	return adminService.getXml();
	// return new UserDto(userService.getUser(login.getUserId()));
  }
}
