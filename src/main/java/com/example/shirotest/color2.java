package com.example.shirotest;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.CollationElementIterator;
import java.util.*;

public class color2 extends JFrame {

    int abs = 80, delta = 1;
    //int abs = 50, delta = 20;
    //int abs = 30, delta = 20;//normal
    //int abs = 30, delta = 50;

    int removeBlackPixel = 100;//移除少于此数量的单独的黑点
    int dontProcessColorIfNumLessThan=10;//移除少于此数量的单独的色块

    double  speed = 11.53;//描绘速度



    boolean colorful=false;//自行随机涂颜色
    boolean black = true;//画黑线

    boolean removeBlackLast=false;
    boolean removeForNormal=false;//true用自带颜色替换、false用左边颜色替换 (替换小色块、 黑色块（如果上面是true）)



    //------------------------------------------------------------------------

    boolean process = false;
    int time11 = 0;
    String url = "";
    int times = 1;
    int cntColor;
    public JPanel panel = null;
    private JPanel panel1 = null;
    private BufferedImage img = null;
    private BufferedImage img1 = null;


    JLabel lab1 = new JLabel("");
    JLabel lab2 = new JLabel("");
    JColorChooser op = new JColorChooser(Color.black);


    JTextArea num = new JTextArea();
    JTextArea value1 = new JTextArea();

    JButton Cal = new JButton("生成");
    JButton Cal1 = new JButton("预览");
    JButton load = new JButton("读取图片");
    JButton change = new JButton("转换图片");

    JComboBox<String> Tb = new JComboBox<String>(new String[]{"左上", "最近的", "不消除"});

    final JTabbedPane tabbedPane = new JTabbedPane();


    int smooth = 0;


    public color2() {
        initComponent();
        this.setVisible(true);
        Runner1 r1 = new Runner1();
        Thread t = new Thread(r1);
        t.start();
    }

    private void clickTry() {
        begin();
    }

    private void click() {
        begin();
    }

    private void begin() {
        try {
            smooth = Integer.parseInt(num.getText());
        } catch (Exception e) {
        }

        process = true;

    }

    private void Load() {
        String path = System.getProperty("user.dir");
        JFileChooser choose = new JFileChooser(path);
        choose.setDialogTitle("Please Choose Picture");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "picture", "jpg");
        choose.setFileFilter(filter);
        int returnVal = choose.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String filepath = choose.getSelectedFile().getPath();
            initImg(new File(filepath));
            url = filepath.substring(0, filepath.lastIndexOf("(") + 1);

            try {
                times = Integer.parseInt(filepath.substring(filepath.lastIndexOf("(") + 1, filepath.lastIndexOf(")")));
            } catch (Exception e) {
            }
            panel1.repaint();
        }
    }

    private void initComponent() {

        lab1.setBounds(380, 60, 300, 70);
        lab1.setFont(new Font("", Font.BOLD, 15));
        lab2.setBounds(380, 260, 300, 70);
        lab2.setFont(new Font("", Font.BOLD, 15));

        this.setTitle("生成奇奇怪怪的图片吧");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1640, 1000);
        setLayout(null);


        JPanel panel1st = new JPanel();
        panel1st.setBounds(0, 0, 600, 600);
        panel1st.setLayout(null);


        tabbedPane.addTab("处理", null, panel1st, "");
        tabbedPane.setBounds(1000, 200, 600, 600);

        op.setBounds(20, 250, 560, 310);
        Tb.setBounds(140, 360, 150, 40);
        Tb.setSelectedIndex(1);
        panel1st.add(Tb);



        panel1 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.drawImage(img1, 0, 0, this.getWidth(), this.getHeight(), null);
            }
        };
        panel1.setBounds(80, 580, 580, 320);
        panel1.setBackground(new Color(224, 224, 255));

        this.add(panel1);
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
            }
        };
        panel.setBounds(20, 20, 960, 540);
        panel.setBackground(new Color(224, 255, 224));
        this.add(panel);
        load.setBounds(1100, 70, 120, 60);
        load.setFont(new Font("", Font.BOLD, 20));
        load.addActionListener((e) -> Load());
        change.setBounds(750, 650, 200, 60);
        change.setFont(new Font("", Font.BOLD, 20));
        change.addActionListener((e) -> Change());
        this.add(change);
        Cal.setBounds(1250, 70, 130, 60);
        Cal.setFont(new Font("", Font.BOLD, 20));
        Cal.addActionListener((e) -> click());
        Cal1.setBounds(980, 20, 120, 50);
        Cal1.setFont(new Font("", Font.BOLD, 20));
        Cal1.addActionListener((e) -> clickTry());

        value1.setText("");
        value1.setBounds(20, 10, 100, 40);
        value1.setFont(new Font("", Font.BOLD, 20));
        value1.setForeground(Color.BLACK);



        panel1st.add(num);

        this.add(tabbedPane);
        this.add(Cal);
        this.add(Cal1);
        this.add(load);
    }

    private void Change() {
        BufferedImage img3 = img1;
        img1 = img;
        img = img3;
        panel.repaint();
        panel1.repaint();
    }

    private void initImg(File file) {
        try {
            img = ImageIO.read(file);
            img1 = ImageIO.read(file);
        } catch (IOException e) {
            if (times <= 1)
                JOptionPane.showMessageDialog(null, "图片丢失");
            else
                JOptionPane.showMessageDialog(null, "成功生成");

        }
    }


    /**
     * 处理图片
     */


    public static void main(String[] args) {
        new color2();

    }

    class Runner1 implements Runnable {

        int cnt = 0;

        @Override
        public void run() {
            while (time11 < 10000000) {
                time11++;
                try {
                    Thread.sleep(55);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (process) {
                    processMC();
                    //processImg();
                    process = false;
                }
                // System.out.println(time);
            }
        }

        //比较两个颜色的相似度
        private double colorPar(int c1,int c2){                //50
            Color C1=new Color(c1);
            Color C2=new Color(c2);
            double r=(C1.getRed()-C2.getRed())/255.0;
            double g=(C1.getGreen()-C2.getGreen())/255.0;
            double b=(C1.getBlue()-C2.getBlue())/255.0;
            double dlt=Math.sqrt(r*r+g*g+b*b);
            return dlt;
        }
        //生成像素图片
        int pix=250,dlt=0;//单列像素块数,间隔
        double thr=50.0;  //图片相似度  越小越相似
        private  void processMC(){
//            Color color = new Color(255, 255, 255);
//            for (int x = 1; x < img.getWidth() - 1; x++) {
//                for (int y = 1; y < img.getHeight() - 1; y++) {
//                    img.setRGB(x, y, color.getRGB());
//                    panel.repaint();
//                }
//            }

//            int[] r={0xbcc2c3,0xcd5901,0x992b90,
//                    0x207db5,0xdca115,0x569a16,0xc25b81,0x313438,0x727269,0x136c7c,0x5b1c8e,0x292b83,
//                    0x58371d,0x435422,0x821f1f,0x080a0f,0xd1d3d4,0xd28831,0xbc5cb6,0x49b2d0,0xd3b635,
//                    0x76ae25/*,0xcb7f9b*/,0x4e5557,0x8e8e88,0x238c94,0x7e35ac,0x3d4095,0x6c492e,0x55672c,
//                    0x972f2e,0x101319};
            int[] r={0xe9e1cc,0xaaada2,0x5d6164,0x2a2f32,0x513333,
                    0xd83033,0x693d30,0xf1d596,0xf16c23,0xffd40a,
                    0x0d753b,0x1b4fb3,0x94b915,0xffa0ca,0x233249,
                    0x308ad3,0x492681,0xf3c9a3,0x92663c,0x2e3e33,
                    0xc23d85,0xff5d7f};
            LinkedList<Integer> matList =new LinkedList<Integer>();
            HashMap matMap=new HashMap();
            for(int i=0;i<r.length;i++){
                matList.add( (new Color(r[i])).getRGB() );
                matMap.put((new Color(r[i])).getRGB(),r[i]);
            }
            Collections.sort(matList);
            int col=img.getHeight()/pix, row=col;  //row= img.getWidth()/pix
            int dev=20;      //左侧距离
            int devL=65,devR=90,devT=10,devB=140;      //左侧偏移量  右侧偏移量
            int matrix[][] =new int[pix][pix];
            for(int i=dev;i<pix+dev;i++){
                for(int j=0;j<pix;j++){
                    //得到当前色块颜色
                    HashMap tmpmap=new HashMap();
                    int last=img.getRGB(i*row+dlt, j*col+dlt);
                    int num=1;
                    for(int x=i*row+dlt;x<(i+1)*row;x++){
                        for(int y=j*col+dlt+1;y<(j+1)*col;y++){
                            int rgb= img.getRGB(x,y);
                            if(colorPar(last,rgb)<thr){
                                num++;
                            }else{
                                //判断是否已存在
                                Iterator it=tmpmap.keySet().iterator();
                                while(it.hasNext()){
                                    int key=(int)it.next();
                                    if(key == last){
                                        num+=(int)tmpmap.get(key);
                                        break;
                                    }
                                }
                                tmpmap.put(last,num);
                                last=rgb;
                                num=1;
                            }
                        }
                    }
                    //判断是否已存在
                    Iterator it0=tmpmap.keySet().iterator();
                    while(it0.hasNext()){
                        int key=(int)it0.next();
                        if(key == last){
                            num+=(int)tmpmap.get(key);
                            break;
                        }
                    }
                    tmpmap.put(last,num);
                    //取出最多的色块
                    Iterator mapt=tmpmap.keySet().iterator();
                    int k=0,max=0;
                    while(mapt.hasNext()){
                        int key=(int)mapt.next();
                        int val=(int)tmpmap.get(key);
                        if(val>max){
                            k=key;
                        }
                    }
                    //比对
                    Iterator it= matList.iterator();
                    double min=999999;
                    int colo=0;
                    while(it.hasNext()){
                        int x=(int)it.next();
                        double d=colorPar(k,x);
                        if(d<min){
                            min=d;
                            colo=x;
                        }
                    }
                    //上色
                    matrix[j][i-dev]=colo;
                    for(int x=i*row+dlt;x<(i+1)*row;x++){
                        for(int y=j*col+dlt;y<(j+1)*col;y++){
                            img.setRGB(x, y, colo);   //k
                        }
                    }
                    panel.repaint();
                }
            }

            //二次绘制
            Color color = new Color(255, 255, 255);
            for (int x = 1; x < img.getWidth() - 1; x++) {
                for (int y = 1; y < img.getHeight() - 1; y++) {
                    img.setRGB(x, y, color.getRGB());
                }
            }
            panel.repaint();
            int lastC=0,num=0;
            int N=0;//总数
            HashMap NumMap=new HashMap();      //各颜色总数
            for(int i=devL;i<pix-devR;i++) {
                for (int j = devT; j < pix-devB; j++) {
                    N++;
                    if(lastC!=matrix[j][i]){
                        int c=1;
                        for(;c<r.length+1;c++){
                            if((new Color(r[c-1])).getRGB()==lastC)break;
                        }
                        int t= NumMap.get(c)==null ? num : num +Integer.valueOf(NumMap.get(c).toString());
                        NumMap.put(c,t);
                        System.out.print("颜色位置 "+c+" :"+num+"块  ");
                        lastC=matrix[j][i];
                        num=1;
                    }else{
                        num++;
                    }
                    for(int x=i*row+dlt;x<(i+1)*row;x++){
                        for(int y=j*col+dlt;y<(j+1)*col;y++){
                            img.setRGB(x, y, matrix[j][i]);
                        }
                    }
                    panel.repaint();
                }
                //每列的最后一组
                int c=1;
                for(;c<r.length+1;c++){
                    if((new Color(r[c-1])).getRGB()==lastC)break;
                }
                int t= NumMap.get(c)==null ? num : num +Integer.valueOf(NumMap.get(c).toString());
                NumMap.put(c,t);
                System.out.print("颜色位置 "+c+" :"+num+"块  ");

                lastC=0;num=0;
                System.out.println(" ");
            }
            System.out.println(" ");
            Iterator it=NumMap.keySet().iterator();
            while (it.hasNext()){
                int x=(int)it.next();
                System.out.print("颜色位置 "+x+" :"+(int)NumMap.get(x)+"块  ");
            }
            System.out.println(" ");

            System.out.println(N);
            int aaa=10;
        }
        //转换图片
        private void processImg() {
            int data[][][] = new int[img.getWidth()][img.getHeight()][11];
            for (int x = 1; x < img.getWidth() - 1; x++) {
                for (int y = 1; y < img.getHeight() - 1; y++) {
                    Color colors = new Color(img.getRGB(x, y));
                    data[x][y][10] = img.getRGB(x, y);
                }
            }
            for (int x = 1; x < img.getWidth() - 1; x++) {
                for (int y = 1; y < img.getHeight() - 1; y++) {
                    Color colors = new Color(data[x][y][10]);
//                    double red = (new Color(data[x - 1][y][10]).getRed() + new Color(data[x + 1][y][10]).getRed() - new Color(data[x][y - 1][10]).getRed()
//                            - new Color(data[x][y + 1][10]).getRed() + 0.5 * (new Color(data[x - 1][y - 1][10]).getRed() - new Color(data[x + 1][y + 1][10]).getRed())
//                            + 0.5 * (new Color(data[x + 1][y - 1][10]).getRed() - new Color(data[x - 1][y + 1][10]).getRed()));
//                    double green = (new Color(data[x - 1][y][10]).getGreen() + new Color(data[x + 1][y][10]).getGreen() - new Color(data[x][y - 1][10]).getGreen()
//                            - new Color(data[x][y + 1][10]).getGreen() + 0.5 * (new Color(data[x - 1][y - 1][10]).getGreen() - new Color(data[x + 1][y + 1][10]).getGreen())
//                            + 0.5 * (new Color(data[x + 1][y - 1][10]).getGreen() - new Color(data[x - 1][y + 1][10]).getGreen()));
//                    double blue = (new Color(data[x - 1][y][10]).getBlue() + new Color(data[x + 1][y][10]).getBlue() - new Color(data[x][y - 1][10]).getBlue()
//                            - new Color(data[x][y + 1][10]).getBlue() + 0.5 * (new Color(data[x - 1][y - 1][10]).getBlue() - new Color(data[x + 1][y + 1][10]).getBlue())
//                            + 0.5 * (new Color(data[x + 1][y - 1][10]).getBlue() - new Color(data[x - 1][y + 1][10]).getBlue()));

                    double red= Math.abs(new Color(data[x - 1][y][10]).getRed()-new Color(data[x + 1][y][10]).getRed())+
                            Math.abs(new Color(data[x][y-1][10]).getRed()-new Color(data[x][y+1][10]).getRed())+
                            Math.abs(new Color(data[x - 1][y-1][10]).getRed()-new Color(data[x + 1][y+1][10]).getRed())+
                            Math.abs(new Color(data[x+1][y-1][10]).getRed()-new Color(data[x-1][y+1][10]).getRed());
                    double green= Math.abs(new Color(data[x - 1][y][10]).getGreen()-new Color(data[x + 1][y][10]).getGreen())+
                            Math.abs(new Color(data[x][y-1][10]).getGreen()-new Color(data[x][y+1][10]).getGreen())+
                            Math.abs(new Color(data[x - 1][y-1][10]).getGreen()-new Color(data[x + 1][y+1][10]).getGreen())+
                            Math.abs(new Color(data[x+1][y-1][10]).getGreen()-new Color(data[x-1][y+1][10]).getGreen());
                    double blue= Math.abs(new Color(data[x - 1][y][10]).getBlue()-new Color(data[x + 1][y][10]).getBlue())+
                            Math.abs(new Color(data[x][y-1][10]).getBlue()-new Color(data[x][y+1][10]).getBlue())+
                            Math.abs(new Color(data[x - 1][y-1][10]).getBlue()-new Color(data[x + 1][y+1][10]).getBlue())+
                            Math.abs(new Color(data[x+1][y-1][10]).getBlue()-new Color(data[x-1][y+1][10]).getBlue());

                    if (Math.abs(red) + Math.abs(green) + Math.abs(blue) < abs) data[x][y][0] = 1;


                    if ((colors.getRed() + colors.getBlue() + colors.getGreen()) > 255 * 2.95) {
                        data[x][y][0] = 2;//白色不画
                    }


                    Color color = new Color(255, 255, 255);
                    img.setRGB(x, y, color.getRGB());
                    panel.repaint();
                }
            }
            for (int y = 1; y < img.getHeight() - 1; y++) {
                for (int x = 1; x < img.getWidth() - 1; x++) {
                    if (data[x][y][1] == 0) {
                        Queue<Integer> q = new LinkedList<>();
                        q.add(x * 10000 + y);
                        proRemove(x, y, data, q);
                        // pro(x, y, data);
                    }
                }
            }
            //噪点  描边
            if (black)
                for (int y = 1; y < img.getHeight() - 1; y++) {
                    for (int x = 1; x < img.getWidth() - 1; x++) {
                        if (data[x][y][1] <= 0) {
                            //Queue<Integer> q = new LinkedList<>();
                            Stack<Integer> q =new Stack<>();
                            q.add(x * 10000 + y);
                            pro(data, q);
                            // pro(x, y, data);
                        }
                    }
                }
/*
            for (int i = 0; i < ran; i++) {
                int x = (int) (1 + (img.getWidth() - 2) * Math.random());
                int y = (int) (1 + (img.getHeight() - 2) * Math.random());
                if (data[x][y][1] == 0) {
                    Queue<Integer> q = new LinkedList<>();
                    q.add(x * 10000 + y);
                    Color color = new Color(data[x][y][10]);
                    Color color2 =pro2(data, q, color);
                    Queue<Integer> q1 = new LinkedList<>();
                    q1.add(x * 10000 + y);
                    pro3(data,q1,color2);
                }
            }
            if (last)
                for (int y = 1; y < img.getHeight() - 1; y++) {
                    for (int x = 1; x < img.getWidth() - 1; x++) {
                        if (data[x][y][1] <= 0) {
                            Color color = new Color(data[x-1][y][10]);
                            img.setRGB(x, y, color.getRGB());
                            cnt++;
                            if (cnt > speed * 2) {
                                update();
                                cnt-=speed * 2;
                            }
                        }
                    }
                }
*/
            //着色
            for (int y = 1; y < img.getHeight() - 1; y++) {
                for (int x = 1; x < img.getWidth() - 1; x++) {
                    if (data[x][y][1] == 0) {
                        Queue<Integer> q = new LinkedList<>();
                        q.add(x * 10000 + y);
                        Color color = new Color(data[x][y][10]);
                        //算出一类颜色
                        Color color2 =pro2(data, q, color);

                        if(colorful){
                            int total=color2.getGreen()+color2.getBlue()+color2.getRed();
                            int min,max;
                            min=Math.max(0,total-255*2);max=Math.min(255,2*total/3);
                            int red= (int) (min+(max-min)*Math.random());
                            total-=red;
                            min=Math.max(0,total-255);max=Math.min(255,total);
                            int green=(int) (min+(max-min)*Math.random());
                            color2=new Color(red,green,total-green);
                        }

                        if(cntColor>=dontProcessColorIfNumLessThan) {
                            Queue<Integer> q1 = new LinkedList<>();
                            q1.add(x * 10000 + y);
                            pro3(data, q1, color2);
                        }
                    }
                }
            }
            int rgb=(new Color(255, 255, 255)).getRGB();
            for (int y = 1; y < img.getHeight() - 1; y++) {
                for (int x = 1; x < img.getWidth() - 1; x++) {
                    if(img.getRGB(x,y)==rgb){
                        img.setRGB(x,y,data[x][y][10]);
                    }
                }
            }
            update();
            //描边
            /*
            for (int y = 1; y < img.getHeight() - 1; y++) {
                for (int x = 1; x < img.getWidth() - 1; x++) {
                    if (removeBlackLast&&data[x][y][1] == 1||data[x][y][1] == 4) {

                        Color color;
                        if(!removeForNormal){
                            int x1=x,y1=y;
                            int w=img.getWidth(),h=img.getHeight();
                            boolean br=false;
                            for (int i = 1; i < 50; i+=2) {
                                for (int j=0;j<=i;j++){
                                    x1++;
                                    if(data[(w+x1)%w][(h+y1)%h][1]==2){
                                        br=true;
                                        break;
                                    }
                                }
                                if(br)break;
                                for (int j=0;j<=i;j++){
                                    y1++;
                                    if(data[(w+x1)%w][(h+y1)%h][1]==2){
                                        br=true;
                                        break;
                                    }
                                }
                                if(br)break;
                                for (int j=0;j<=i+1;j++){
                                    x1--;
                                    if(data[(w+x1)%w][(h+y1)%h][1]==2){
                                        br=true;
                                        break;
                                    }
                                }
                                if(br)break;
                                for (int j=0;j<=i+1;j++){
                                    y1--;
                                    if(data[(w+x1)%w][(h+y1)%h][1]==2){
                                        br=true;
                                        break;
                                    }
                                }
                                if(br)break;
                            }
                            color = new Color(img.getRGB((w+x1)%w,(h+y1)%h));



                        }
                        else  color = new Color(data[x][y][10]);
                        img.setRGB(x, y, color.getRGB());
                        cnt++;
                        if (cnt > speed * 6) {
                            update();
                            cnt-=speed * 6;
                        }
                    }
                }
            }
            */

            try {
                ImageIO.write(img, "JPEG", new FileOutputStream(System.getProperty("user.dir") + "/out/out"+(int)(Math.random()*10000)+".jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        void pro(int data[][][], Stack queue) {
            while (queue.size() > 0) {

                int a = (int) queue.pop();
                int x = a / 10000;
                int y = a % 10000;

                if (data[x][y][0] == 0 && data[x][y][1] <= 0) {
                    data[x][y][1] = 1;

                    Color color = new Color(0, 0, 0);
                    img.setRGB(x, y, color.getRGB());
                    cnt++;
                    if (cnt > speed * 10) {
                        update();
                    }

//                    if (true) {  //queue.size() < 1000
//                        if (x < img.getWidth() - 1 && data[x + 1][y][1] <= 0) {
//                            int ad = (x + 1) * 10000 + y;
//                            queue.add(ad);
//                        }
//                        if (y < img.getHeight() - 1 && data[x][y + 1][1] <= 0) {
//                            int ad = (x) * 10000 + y + 1;
//                            queue.add(ad);
//                        }
//                        if (x > 0 && data[x - 1][y][1] <= 0) {
//                            int ad = (x - 1) * 10000 + y;
//                            queue.add(ad);
//                        }
//                        if (y > 0 && data[x][y - 1][1] <= 0) {
//                            int ad = (x) * 10000 + y - 1;
//                            queue.add(ad);
//                        }
//                    }
                }
            }
        }//dfs stack<1000

        void pro(int x, int y, int data[][][]) {
            if (data[x][y][1] > 0) return;
            if (data[x][y][0] == 0 && data[x][y][1] <= 0) {
                data[x][y][1] = 1;

                Color color = new Color(0, 0, 0);
                img.setRGB(x, y, color.getRGB());

                cnt++;
                if (cnt > 10) {
                    update();
                }
                if (x < img.getWidth() - 1 && data[x + 1][y][1] <= 0)
                    pro(x + 1, y, data);
                if (y < img.getHeight() - 1 && data[x][y + 1][1] <= 0)
                    pro(x, y + 1, data);
                if (x > 0 && data[x - 1][y][1] <= 0) {
                    pro(x - 1, y, data);
                }
                if (y > 0 && data[x][y - 1][1] <= 0) {
                    pro(x, y - 1, data);
                }
            }
        }//dfs

        void pro(int data[][][], Queue queue) {
            while (queue.size() > 0) {


                int a = (int) queue.poll();
                int x = a / 10000;
                int y = a % 10000;

                if (data[x][y][0] == 0 && data[x][y][1] <= 0) {
                    data[x][y][1] = 1;

                    Color color = new Color(0, 0, 0);
                    img.setRGB(x, y, color.getRGB());
                    cnt++;
                    if (cnt > speed * 10) {
                        update();
                    }

                    if (x < img.getWidth() - 1 && data[x + 1][y][1] <= 0) {
                        int ad = (x + 1) * 10000 + y;
                        queue.add(ad);
                    }
                    if (y < img.getHeight() - 1 && data[x][y + 1][1] <= 0) {
                        int ad = (x) * 10000 + y + 1;
                        queue.add(ad);
                    }
                    if (x > 0 && data[x - 1][y][1] <= 0) {
                        int ad = (x - 1) * 10000 + y;
                        queue.add(ad);
                    }
                    if (y > 0 && data[x][y - 1][1] <= 0) {
                        int ad = (x) * 10000 + y - 1;
                        queue.add(ad);
                    }
                }
            }
        }//bfs

        void proRemove(int x1, int y1, int data[][][], Queue queue) {
            int size = 0;
            while (queue.size() > 0) {

                size++;
                int a = (int) queue.poll();
                int x = a / 10000;
                int y = a % 10000;

                if (data[x][y][0] == 0 && data[x][y][1] == 0) {
                    data[x][y][1] = -1;

                    if (x < img.getWidth() - 1 && data[x + 1][y][1] == 0) {
                        int ad = (x + 1) * 10000 + y;
                        queue.add(ad);
                    }
                    if (y < img.getHeight() - 1 && data[x][y + 1][1] == 0) {
                        int ad = (x) * 10000 + y + 1;
                        queue.add(ad);
                    }
                    if (x > 0 && data[x - 1][y][1] == 0) {
                        int ad = (x - 1) * 10000 + y;
                        queue.add(ad);
                    }
                    if (y > 0 && data[x][y - 1][1] == 0) {
                        int ad = (x) * 10000 + y - 1;
                        queue.add(ad);
                    }
                }
            }

            if (size <= removeBlackPixel) {

                Queue<Integer> q1 = new LinkedList<>();
                q1.add(x1 * 10000 + y1);
                proRemove2(data, q1);
            }//else System.out.println(size);
        }//bfs

        void proRemove2(int data[][][], Queue queue) {

            while (queue.size() > 0) {

                int a = (int) queue.poll();
                int x = a / 10000;
                int y = a % 10000;

                //   Color color = new Color(0, 0, 0);img.setRGB(x, y, color.getRGB());cnt++;if (cnt > speed*100) { update(); }

                if (data[x][y][0] == 0 && data[x][y][1] == -1) {
                    data[x][y][1] = 0;
                    data[x][y][0] = 1;

                    if (x < img.getWidth() - 1 && data[x + 1][y][1] < 0) {
                        int ad = (x + 1) * 10000 + y;
                        queue.add(ad);
                    }
                    if (y < img.getHeight() - 1 && data[x][y + 1][1] < 0) {
                        int ad = (x) * 10000 + y + 1;
                        queue.add(ad);
                    }
                    if (x > 0 && data[x - 1][y][1] < 0) {
                        int ad = (x - 1) * 10000 + y;
                        queue.add(ad);
                    }
                    if (y > 0 && data[x][y - 1][1] < 0) {
                        int ad = (x) * 10000 + y - 1;
                        queue.add(ad);
                    }
                }
            }
        }//bfs

        Color pro2(int data[][][], Queue queue, Color color2) {
            long red=0,blue=0,green=0,cnt=0;
            while (queue.size() > 0) {
                cnt++;
                int a = (int) queue.poll();
                int x = a / 10000;
                int y = a % 10000;
                Color color = new Color(data[x][y][10]);
                red+=color.getRed();
                green+=color.getGreen();
                blue+=color.getBlue();

                if (data[x][y][1] == 0 && data[x][y][0] != 2) {
                    data[x][y][1] = 4;




                    Color color1 = new Color(data[x][y][10]);
                    int max = delta;
                    if (x < img.getWidth() - 1 && data[x + 1][y][1] == 0) {
                        color1 = new Color(data[x + 1][y][10]);
                        if (Math.abs(color.getRed() - color1.getRed()) + Math.abs(color.getGreen() - color1.getGreen()) + Math.abs(color.getBlue() - color1.getBlue()) < max) {
                            int ad = (x + 1) * 10000 + y;
                            queue.add(ad);
                        }
                    }
                    if (y < img.getHeight() - 1 && data[x][y + 1][1] == 0) {
                        color1 = new Color(data[x][y + 1][10]);
                        if (Math.abs(color.getRed() - color1.getRed()) + Math.abs(color.getGreen() - color1.getGreen()) + Math.abs(color.getBlue() - color1.getBlue()) < max) {
                            int ad = (x) * 10000 + y + 1;
                            queue.add(ad);
                        }
                    }
                    if (x > 0 && data[x - 1][y][1] == 0) {
                        color1 = new Color(data[x - 1][y][10]);
                        if (Math.abs(color.getRed() - color1.getRed()) + Math.abs(color.getGreen() - color1.getGreen()) + Math.abs(color.getBlue() - color1.getBlue()) < max) {
                            int ad = (x - 1) * 10000 + y;
                            queue.add(ad);
                        }
                    }
                    if (y > 0 && data[x][y - 1][1] == 0) {
                        color1 = new Color(data[x][y - 1][10]);
                        if (Math.abs(color.getRed() - color1.getRed()) + Math.abs(color.getGreen() - color1.getGreen()) + Math.abs(color.getBlue() - color1.getBlue()) < max) {
                            int ad = (x) * 10000 + y - 1;
                            queue.add(ad);
                        }
                    }
                }
            }
            cntColor= (int) cnt;
            //System.out.println(red/cnt);
            return new Color((int)(red/cnt),(int)(green/cnt),(int)(blue/cnt));
        }//bfs

        void pro3(int data[][][], Queue queue, Color color3) {
            while (queue.size() > 0) {

                int a = (int) queue.poll();
                int x = a / 10000;
                int y = a % 10000;
                Color color = new Color(data[x][y][10]);

                if (data[x][y][1] == 4 && data[x][y][0] != 2) {
                    data[x][y][1] = 2;
                    img.setRGB(x, y, color3.getRGB());
                    cnt++;
                    if (cnt > speed * 100) {
                        update();
                    }


                    Color color1 = new Color(data[x][y][10]);
                    int max = delta;
                    if (x < img.getWidth() - 1 && data[x + 1][y][1] == 4) {
                        //   color1 = new Color(data[x + 1][y][10]);
                        //  if (Math.abs(color.getRed() - color1.getRed()) + Math.abs(color.getGreen() - color1.getGreen()) + Math.abs(color.getBlue() - color1.getBlue()) < max) {
                        int ad = (x + 1) * 10000 + y;
                        queue.add(ad);
                        //  }
                    }
                    if (y < img.getHeight() - 1 && data[x][y + 1][1] == 4) {
                        //  color1 = new Color(data[x][y + 1][10]);
                        //  if (Math.abs(color.getRed() - color1.getRed()) + Math.abs(color.getGreen() - color1.getGreen()) + Math.abs(color.getBlue() - color1.getBlue()) < max) {
                        int ad = (x) * 10000 + y + 1;
                        queue.add(ad);
                        //  }
                    }
                    if (x > 0 && data[x - 1][y][1] == 4) {
                        //  color1 = new Color(data[x - 1][y][10]);
                        //if (Math.abs(color.getRed() - color1.getRed()) + Math.abs(color.getGreen() - color1.getGreen()) + Math.abs(color.getBlue() - color1.getBlue()) < max) {
                        int ad = (x - 1) * 10000 + y;
                        queue.add(ad);
                        //  }
                    }
                    if (y > 0 && data[x][y - 1][1] == 4) {
                        //   color1 = new Color(data[x][y - 1][10]);
                        //  if (Math.abs(color.getRed() - color1.getRed()) + Math.abs(color.getGreen() - color1.getGreen()) + Math.abs(color.getBlue() - color1.getBlue()) < max) {
                        int ad = (x) * 10000 + y - 1;
                        queue.add(ad);
                        //  }
                    }
                }
            }
        }//bfs

        void update() {
            cnt = 0;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            panel.repaint();
        }
    }
}
