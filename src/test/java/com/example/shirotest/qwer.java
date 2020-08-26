package com.example.shirotest;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.UUID;
import java.util.concurrent.*;

@SpringBootTest
public class qwer {
    private final int CORE=5;
    private final int MAX=10;
    private final int SIZE=100;
    private final int TIME=1000;
    Logger log=LoggerFactory.getLogger(this.getClass());
    @Test
    public void qwerq(){
//        ThreadPoolExecutor pool=new ThreadPoolExecutor(
//                CORE,
//                MAX,
//                TIME,
//                TimeUnit.SECONDS,
//                new ArrayBlockingQueue<>(SIZE),
//                new ThreadPoolExecutor.CallerRunsPolicy()
//        );
//        for(int i=0;i<10;i++){
//            Future f=pool.submit(new myThread(i));
//            int aa=10;
//        }
//        pool.shutdown();
        IOTest io=new IOTest();
        io.moveFile(log);
        TestInter tes=()->{return "222";};
        log.debug(tes.get());
    }
}

class IOTest{
    public void moveFile(Logger log){
        File a1=new File("c:\\work\\Weex培训文档.zip");
        File a2=new File("C:\\work\\filetest"+File.separator+a1.getName());
        try{
            FileUtils.copyFile(a2,a1);
            //File[] files = file.listFiles();
            //.isDirectory()
            //a1.renameTo(a2);
        }catch(Exception IO){
            log.debug(IO.getMessage());
        }
    }
}

class myThread implements Callable{
    Logger log=LoggerFactory.getLogger(this.getClass());
    int MY_i=0;
    public myThread(int i){
        this.MY_i=i;
    }

    @Override
    public Object call() throws Exception {

        log.debug(""+MY_i);
        return MY_i;
    }
}

@FunctionalInterface
 interface TestInter{
    default String getS(){
        return "123";
    }
    public String get();
}

