package com.example.shirotest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
public class Spider2 {
    public static void main(String[] a){
        String ss="19/12/1912eea4f15a86b37a91a25a816439d6.jpg-7e/26/7e268f7011f93fb831319f8f37d7d19e.jpg-3f/86/3f86605cbd62b0e495ec7783d126c778.jpg-20/32/2032796a16916b88fcbaa3962488365e.jpg-c6/d4/c6d4425a9926c8b199282b71e01646ce.jpg-28/0b/280b0a4babfb4bfd9aaac8f48364d1f9.jpg-71/74/717411898f694749d1abf2d59befce01.jpg-b7/12/b712e5311a2c7206aa4c54f4674225c9.jpg-c6/d4/c6d4425a9926c8b199282b71e01646ce.jpg-7b/5a/7b5a067a76ac5bb93e0c0eb1631448f6.jpg-51/f8/51f8b96d5823b3b3cbaf79b8bea8d304.jpg-c6/d4/c6d4425a9926c8b199282b71e01646ce.jpg-fe/f2/fef221726255a891aa2e13cad5e947cd.jpg-4c/e2/4ce2d550abeee320d44a859bb34dc34a.jpg-91/e4/91e45dd566657fd400fdb488b7ab53d1.jpg-0d/47/0d47bf6cf101f47b32a122bc480f8b11.jpg-e3/c3/e3c3ac14db3420221806443624b59af2.jpg-db/d8/dbd81e1bade102651c5e1e6588d013a4.jpg-db/ae/dbaec6e122aca85b1a5026d8a20164b1.jpg-d6/3c/d63c3a25b25ee2baa6b3db3d516c54e3.jpg-f0/1b/f01b5baacd56657ad0bca766b4b0a8fb.jpg-c6/d4/c6d4425a9926c8b199282b71e01646ce.jpg-3e/16/3e1677730b199573a65745289af8d764.jpg-ba/97/ba9716c132fa84325ffc579a4f678e54.jpg-4a/86/4a862a34f1ca0f74aba8f903cb5b20d2.jpg-02/6c/026cb97746f5f1240623f4fe1aa28922.jpg-5a/2c/5a2c806bba19d3ce64ddbf23ddf5df5f.jpg-b2/84/b284b2f31bda819f58cc43f077fd04c0.jpg-1f/f7/1ff73a641315fcbed7a07f4c6427bcfb.jpg-86/88/8688c568d8220ab89a9306ed0c754b4d.jpg-b4/4f/b44f7f351f0593aa30e9506a63e32276.jpg-67/95/679525afe75fe9d66f3028e7ffb4e36f.jpg-e8/50/e85069e7409657f100eafd0ff5d674c6.jpg-8e/44/8e441cfc0bb539daaa821de5f25d8a78.jpg-57/6b/576b198ea3662fa55b7fbec332676a8f.jpg-50/da/50da25202f5de3959e70e36cf7e49560.jpg-b2/ab/b2ab120cb0f3ba32a1ae5ddbf9a89d2b.jpg-39/b2/39b26d2d2da3a95b511a8a7a20ee28c8.jpg-be/77/be778583772142fcb65f6f6d125fdaf0.jpg-7f/84/7f844a3f6a2106f852ec46b9af0105fd.jpg-94/07/9407779ce4bf06fddf8272a0ac4d1f02.jpg-51/99/5199644df59e541ff054042f4de51f18.jpg-ac/a9/aca9f41d70f6efe48f5655f935fe29e9.jpg-c6/d4/c6d4425a9926c8b199282b71e01646ce.jpg-ca/f6/caf698303196b80d6f81d44e955e71c6.jpg-60/10/6010190a600e038ef1643a7d86a0cc6f.jpg-31/23/31234588fae07de09ac5744f7300e3d9.jpg-1b/f7/1bf71daf9ec2275f868f8daa058488bc.jpg-63/ad/63ad42f5b0cf8774c603c4570bb52c06.jpg-9c/9f/9c9f11248c5476464680bc2d8b7ded35.jpg-9c/61/9c619adfe679bca6552b5f44e186afea.jpg-5d/86/5d86239eab8909b6c1d09351e61d830f.jpg-f9/0f/f90f3769cb6b3c84c1e7910bfd16a5f0.jpg-94/dd/94dd410473dc1bc9a9933dcf5cfa6a3e.jpg-f9/fc/f9fcee6efc29999551694619e2972622.jpg-00/45/00459d650e0081022ed83ce8e981bee9.jpg-82/17/821769f8e7476caa64b39bd268b61c11.jpg-33/a1/33a1f1863118e88a72089979aaee080b.jpg-45/e5/45e598da60fc7bcad5e8323de899690d.jpg-7d/86/7d86c0c5bed2c73c6c095ee0acebff52.jpg-b9/e8/b9e82dfbb1a91fb82ef9f88cf2c0d79b.jpg-db/5f/db5fcbbc019dd1bd38a5b9d749b4716a.jpg-dc/10/dc103b447ee242dd6bad19d6a3285e0b.jpg-c6/d4/c6d4425a9926c8b199282b71e01646ce.jpg-39/9c/399c4b126319f8d9fe56691734e71057.jpg-e8/5c/e85c2731177c5c290f9dde350da0c41c.jpg-f2/47/f2474e571542bd128b7d2855d0ba5e08.jpg-83/0c/830ccbc0067a918169426da68b923ad9.jpg-39/6f/396fe422007229fd72a56ef59d6a1805.jpg-23/f5/23f514ef8794b3e845f0748e71679e6b.jpg-fa/28/fa28ae16b569535c9981571b86441491.jpg-3e/32/3e32731fd2a890192b80a007d07590a9.jpg-73/fc/73fcd3cee40e36c38be1496dc80d9012.jpg-ac/0b/ac0bef0603cee418c9d7a8078cbe3315.jpg-c3/ae/c3ae4842426e86687a5c2ee227518d31.jpg-37/b1/37b15bd191a3bc789af17a2153c9bfca.jpg-62/ef/62ef6c3adb39a07faa8d6cf170391fd3.jpg-8f/8d/8f8d6ae84fb7fdf89ae0e7976f370801.jpg-c1/2e/c12edb3e5c0e6f9095209c0ae807b060.jpg-1c/8a/1c8a0bae3d5a4526df999da4fd00af37.jpg-ed/02/ed0218f36919e8e1d8d1b66e1de33d69.jpg-f9/60/f9604639796f4cd42af022473c9300a4.jpg-ff/f5/fff50549844dbe6c44bdfe7bb5e6f926.jpg-4e/6c/4e6cad250c772f77fce61904f120a070.jpg-1b/ab/1bab4a58381b75d344ba33f3f5981d35.jpg-b1/d5/b1d56240283306e98a35ae744f27635e.jpg-14/42/144280b003d5ea8ed758654624a26ccc.jpg-77/75/777551c43f06ffb3a51d2f97f6181652.jpg-c6/d4/c6d4425a9926c8b199282b71e01646ce.jpg-c3/82/c382d5e8176fe5a21dcffbb0dee77906.jpg-0d/b9/0db9038ed978321419b0f4fa260fddc5.jpg-40/bf/40bf7204f2b6cd245b1199a9aa97fb6c.jpg-34/49/3449a36695b857e3f3f595a64c32f7a8.jpg-6d/f2/6df2e626adf7c5febf20b900fd8c7d21.jpg-3d/69/3d69298976f7920d8250b65324c99cad.jpg-53/f2/53f2721de27e4c2150e1b128a0929097.jpg-38/41/3841fb3666178b697a10a6453a9cd36e.jpg-a2/99/a299070958be0a96f9aa0eb7913f5f41.jpg-52/73/52731e205a6ebcfafeb3b2056537efa3.jpg-1e/ae/1eaeea2d435a343a5b7b123e543aea98.jpg-f3/1d/f31d9b72a541a27c837c58f603c576f1.jpg-38/41/3841fb3666178b697a10a6453a9cd36e.jpg-4b/3b/4b3bf0429154aa3e0e18824cbfdabff4.jpg-a8/00/a800c0c7311f39ad8d03fb97be829aff.jpg-8e/45/8e4517f4185ba3744e2a3b0610a21507.jpg-57/c2/57c277880cf1fda393edb03e99dd3b32.jpg-32/e1/32e1fc2dcf0fb785008d4af0191909a2.jpg-d5/4f/d54f621f0cadc072d1744f9b6629038e.jpg-63/ed/63ed730f30620a11adf61cbb3cb49873.jpg-99/e0/99e07decb2887271367bdb184ca1a6b4.jpg-30/93/30939b85da060ee97397ef456b590b33.jpg-1d/e1/1de1d0a79914c0a64f23d62264c4013e.jpg-7a/55/7a55cc00c88713bd6c736f9e7a5b4f02.jpg-38/41/3841fb3666178b697a10a6453a9cd36e.jpg-13/f8/13f8a94939fe85867bb605438db5a1c6.jpg-8f/34/8f346e6fc6b3c43da3d8baf488f6dc1c.jpg-6c/48/6c48150601efe597222711b790580265.jpg-c6/d4/c6d4425a9926c8b199282b71e01646ce.jpg-78/1b/781bb76a1833e763b249c7375693e995.jpg-83/6f/836f4c0afe3d8e993c3ad9538c536ca7.jpg-0b/46/0b460d5d1f684317bb25fa7648b72e60.jpg-29/86/29862c529951c73e2b768927e1ff5ae1.jpg-c6/d4/c6d4425a9926c8b199282b71e01646ce.jpg-b0/41/b041419651cd062b153cbe52b0e9f6eb.jpg-08/d9/08d9b941f6f014dc08c471c55c891823.jpg-a8/e2/a8e2dd30fccecf56ba2f1a2394b4c53b.jpg-54/7e/547ed2a40fa841621a96c59f37708c5c.jpg-ca/0b/ca0ba84df1e7c48c82608784d1b3b51d.jpg-cc/f5/ccf5bf0883b1a5f235f35c66452f2cc5.jpg-b6/b3/b6b3f477af8576745966b55643e0af13.jpg-31/ce/31ce0781d95277c8f66e0af79e0c05a9.jpg-7d/8a/7d8a26dfb2d6367629404f70aabe2d9d.jpg-c6/d4/c6d4425a9926c8b199282b71e01646ce.jpg-35/be/35be5719b69feb0e3e22307ed365aacd.jpg-fb/54/fb54d69952e39c3279bc3679c778c551.jpg-db/d9/dbd986f1a9a96f650d8188fc62f7d0d1.jpg-b8/20/b8208da9be559c38691ab0527fad2400.jpg-2c/64/2c64ca7ad098ef743e64a478e3cdd5ae.jpg-aa/95/aa95afe1a1c1f1dd6c3715b69541142c.jpg-35/77/3577314242a68482cb06b00516b8695f.jpg-86/b3/86b3413eaa58706f39ba904d2d0104b1.jpg-5e/36/5e36fb989c2883086e595f37a6abe104.jpg-80/fd/80fd95120e4b413fe70b298af31bbcd9.jpg-8c/7e/8c7e55618f53d2e3fc16e582a95daffb.jpg-ab/77/ab7779562267e94809bfdfade73e3c8f.jpg-78/67/78674f8e228324ae8d9032d1e58c72d8.jpg-c6/d4/c6d4425a9926c8b199282b71e01646ce.jpg-e8/6b/e86b96c0b26c6c2090afa989c0b14858.jpg-b9/49/b949113259642099852dbf48d43edee1.jpg-62/db/62db5d528ebca1fef5b4799b088091e8.jpg-66/b7/66b73785ba238f5c4c3b0b56a1c76f7d.jpg-30/b5/30b5aebe4bcac48ac24f7d2d22dd80d1.jpg-99/64/9964196f1cf97ceb018b032c7574fe0f.jpg-a2/e4/a2e46796d436303eb565a2df85194b21.jpg-91/85/918538bdb74ca6cc390e1312c19a17e2.jpg-db/bd/dbbd6357d1fe917b7250b0bb770a53a8.jpg-46/f1/46f147aa0caa7ec90f7ccafbd959c9f9.jpg-d8/53/d8535c4950809a7f02992ab367253aa8.jpg-86/61/8661547cbcfce5fa3385592e1c921895.jpg-c6/d4/c6d4425a9926c8b199282b71e01646ce.jpg-98/eb/98eb23749a67be3edb5837071ce848a2.jpg-07/73/0773dd5870eceb3358155e233f2ce8df.jpg-bb/84/bb84f9d3c9a15022720f1ae9b3c05365.jpg-be/7f/be7f5129e72776b6b1758f95f2eff691.jpg-63/f7/63f757220df5ae638e277a82aa3b5fb0.jpg-16/1d/161d41a8fe774a8570b8530c6a31d79b.jpg-32/d0/32d0b181874e3b928c27092374326972.jpg-c8/3b/c83b9701d8cdd0727755d068e22feb68.jpg-62/f2/62f2e0bd57cae7cc62d6685adba4848a.jpg-07/9d/079df5a5da6487b7d21096214828cf1b.jpg-2a/db/2adb74fa0e19b89689ad0e08576e2eb2.jpg-6b/60/6b6003c423e7e3e9914f13a26f72a358.jpg-64/88/64880b63d374ad06ad3d6c7982a73dcf.jpg-1a/b9/1ab918872de9bd651320552a756c64c0.jpg-56/06/56061eb725cdd03c693508a9965892ad.jpg-e8/2d/e82d57980803a1b1b29c66de0d9893c4.jpg-aa/b1/aab1c4cfa5d7c4d76fb0957166b181c7.jpg-15/0f/150f6664310e938b887a34f19906d9d1.jpg-45/32/453259cebf01571aaf3ae75546ce0625.jpg-af/63/af63656bd9c9122b5562c35f225cc13a.jpg-37/72/37728d2a6f0b08183683dd6f1a67ad18.jpg-39/78/3978187da657a53493c85c4cd99cddbf.jpg-62/f2/62f2e0bd57cae7cc62d6685adba4848a.jpg-ab/d5/abd5eb9b32bd7637c341cbfafa0ce11b.jpg-0c/7f/0c7f3aec0bb586daa29b2f78aa23bd80.jpg-e5/fa/e5faebb407d2a91b9f4feadfa4a705cd.jpg-5c/45/5c456f0f1816585234f5e3bdbdfa7d1a.jpg-10/e9/10e98966a087118edcf96e9000ca2381.jpg-fa/67/fa67858c14a16e7249e2894c9eadc70b.jpg-b5/cb/b5cb6a13e6de5721c2e87531c97204fb.jpg-a7/a7/a7a7cce380faa663a5d10a66e0134ee5.jpg-38/0c/380c561e006eae1e8a2f8d6030a606f8.jpg-34/aa/34aa93f5ec6e17c4d05e932d831f3e07.jpg-c9/90/c9900908aae5698dfdb65858147da8d4.jpg-57/fd/57fdee0be38f1a18ef75d068846d8872.jpg-58/bb/58bba3538b71320cfa82e1cbd98d7cd7.jpg-cc/02/cc028d22a2187a0b8f247b0a17aa3c9b.jpg-bb/e1/bbe120ad433f6c29649ee682fc958cfa.jpg-62/f2/62f2e0bd57cae7cc62d6685adba4848a.jpg-ca/6d/ca6d0d2a6e73414f0d24b861bc60e40b.jpg-55/a5/55a58fd0bdb6ec6af62565470be8fa78.jpg-c6/d4/c6d4425a9926c8b199282b71e01646ce.jpg-98/6a/986a92e5a394d9890dca96db2768fdc2.jpg-d2/8e/d28ee81367519ee43f5d0224af8f4e93.jpg-bb/9f/bb9f8dedb9be767a1d9c2d028c332d29.jpg-d6/8a/d68a9c8bf339e4fe6e703bae8fb5ed72.jpg-89/f3/89f3353685b46b81b4fb0547b9c0d866.jpg-29/da/29da62ede6258dbc6330f1d6cff023fa.jpg-d7/f2/d7f24c953ae7ccd46df954b1cc57a9a3.jpg-47/dd/47ddc482ab8cdd282f97af65ac670866.jpg-9f/40/9f4088e7911c27d085198088fe9147fc.jpg-09/19/091923583750b4db7ca8ae434badf0c5.jpg-67/35/67355b6d3afa2efe8a24c58b74ae676c.jpg-d5/54/d5549572f6ac585915026831c6ee2198.jpg-d1/a2/d1a2a1a83d081b14a50fb86a20138f77.jpg-fa/91/fa91511ea8ab6530af1bacf360dfcd6a.jpg-81/b7/81b70078e02a50ddb00d851ac0060f00.jpg";
        downImages("",ss,"jpg");//png  jpg
    }
    static String[] UA={"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; en) Opera 9.50",
            "Mozilla/5.0 (X11; U; Linux x86_64; zh-CN; rv:1.9.2.10) Gecko/20100922 Ubuntu/10.10 (maverick) Firefox/3.6.10",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/534.57.2 (KHTML, like Gecko) Version/5.1.7 Safari/534.57.2",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36",
            "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.64 Safari/537.11",
            "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US) AppleWebKit/534.16 (KHTML, like Gecko) Chrome/10.0.648.133 Safari/534.16",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36",
            "Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.11 (KHTML, like Gecko) Chrome/20.0.1132.11 TaoBrowser/2.0 Safari/536.11",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.71 Safari/537.1 LBBROWSER",
            "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E; LBBROWSER)",
            "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; QQDownload 732; .NET4.0C; .NET4.0E; LBBROWSER)",
            "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E; QQBrowser/7.0.3698.400)",
            "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; QQDownload 732; .NET4.0C; .NET4.0E)",
            "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.84 Safari/535.11 SE 2.X MetaSr 1.0",
            "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; SV1; QQDownload 732; .NET4.0C; .NET4.0E; SE 2.X MetaSr 1.0)",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Maxthon/4.4.3.4000 Chrome/30.0.1599.101 Safari/537.36",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 UBrowser/4.0.3214.0 Safari/537.36"};
    public static void downImages(String Url,String ss,String type){
        String s[]=ss.split("-");
        String filePath="F:\\IDEA\\123\\";
        // 截取图片的名称
        //String fileName = imageUrl.substring(Url.lastIndexOf("/"));
        String fileName="";
        //创建文件的目录结构
        File files = new File(filePath);
        if(!files.exists()){// 判断文件夹是否存在，如果不存在就创建一个文件夹
            files.mkdirs();
        }
        try {
            for(int i=6;i<s.length;i++){
                //Thread.sleep((int)(Math.random()*3));
                System.out.println(""+i);
                String imageUrl=Url+s[i];
                //fileName= i+90<10? "00"+(i+90) : (i+90<100?"0"+(i+90) : ""+(i+90));
                fileName= i<10? "00"+i : (i<100?"0"+i : ""+i);
                fileName+=type;
                URL url = new URL(imageUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                System.out.println("UA+"+UA[i%UA.length]);
                connection.setRequestProperty("User-Agent",UA[i%UA.length]);
                connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 UBrowser/4.0.3214.0 Safari/537.36");
                //connection.setRequestProperty("ip","203.195.251.136");
                InputStream is = connection.getInputStream();
                // 创建文件，并设置默认文件名
                File file = new File(filePath+fileName);
                FileOutputStream out = new FileOutputStream(file);
                int j = 0;
                while((j = is.read()) != -1){
                    out.write(j);
                }
                is.close();
                out.close();
                System.out.println("OK");
                Thread.sleep( (int)(Math.random()*300) );
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
