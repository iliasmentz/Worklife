package com.linkedin.config.constants;

import java.text.SimpleDateFormat;

public class UrlConst {
  public static final SimpleDateFormat DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd");


  public static final String SITE = "http://localhost:8080";

  public static final String INDEX = "/**";
  public static final String SECURED = "/user/secured/*";

  public static final String REGISTER = "/register";
  public static final String LOGIN = "/login";
  public static final String FORGOT_PASSWORD = "/password/forgot";
  public static final String RESET_PASSWORD = "/password/reset";
  public static final String RESET_TOKEN = "/#/reset/";
}
