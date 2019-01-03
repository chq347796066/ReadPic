package com.pose.readpicredis.server;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class ReadPicsServer {



    private static final String REDIS_PICS_KEY="redis_pics_key";
    @Value("${pic.path}")
    private String path;

    @Autowired
    private StringRedisTemplate redisTemplate;

    public List<String> readPic(){
        Set <String>pic_set=redisTemplate.opsForSet().members(REDIS_PICS_KEY);
        List<String> list=new ArrayList<>();
        for (String value:pic_set) {
            list.add("/image/"+value);
        }
        return list;
    }




    public  void addPics(){
        //先删除
        if(redisTemplate.hasKey(REDIS_PICS_KEY)){
            redisTemplate.delete(REDIS_PICS_KEY);
        }
        String[]pics=new String[9];
        for(int i=0;i<9;i++){
            pics[i]="0000000"+(i+1)+".jpg";
        }
        redisTemplate.opsForSet().add(REDIS_PICS_KEY,pics);
    }

    public void readPic1() {
        redisTemplate.opsForValue().set("test","this is a test");
    }



    public  List<File>readPicFromLocal(){
        List<File>list=new ArrayList<>();
        System.out.println("path:"+path);
        File fileDir=new File(path);
        if(fileDir.exists()){
            File []files=fileDir.listFiles();
            for (File file:files) {
                list.add(file);
            }

        }
        return list;
    }
}
