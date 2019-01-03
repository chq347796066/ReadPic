package com.pose.readpicredis.controller;

import com.pose.readpicredis.server.ReadPicsServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@Controller
public class ReadPicsController {


    @Autowired
    public ReadPicsServer readPicsServer;

    private List<File>files;

    @RequestMapping(value = "/pic",method = RequestMethod.GET)
    public String pic(HttpServletRequest request){
        files=readPicsServer.readPicFromLocal();
        request.setAttribute("size",files.size());
        System.out.println("filesize:"+files.size());
        return "pics";
    }








    @RequestMapping("/showpic")
    public void showPic(HttpServletResponse response,String currentIndex){
        System.out.println("currentIndex:"+currentIndex);
        int index=Integer.parseInt(currentIndex);
        if(index<0){
            return;
        }
        if(index<files.size()) {
            if (files != null) {
                File file = files.get(index);
                showPic(response, file);
            }
        }

    }



    public void showPic(HttpServletResponse response,File file){
        InputStream is=null;
        try {
            is= new FileInputStream(file);
            byte[] bytes = new byte[(int)file.length()];
            is.read(bytes,0,(int)bytes.length);
            response.getOutputStream().write(bytes);
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(is!=null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
