package com.dharaneesh.rest.webservices.restful_web_services.helloWorld;

public class HelloWorldBean {

    String message;

    public HelloWorldBean(String msg) {
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "HelloWorld{" +
                "message='" + message + '\'' +
                '}';
    }
}
