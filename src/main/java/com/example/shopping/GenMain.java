package com.example.shopping;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.util.ResourceUtils;
/**************************************
 * 类说明:
 *     mybatis逆向工程main函数
 ***************************************
 */
public class GenMain {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        //如果这里出现空指针，直接写绝对路径即可。<br><br>　　　　//这个地方是一个非常大的重点  你的逆向工程的配置文件的地址 为了确定可以写成绝对的 具体的 就是点击配置文件 右击最后一个就看见了 ctrl  c+v 大法来完成<br>
        //String genCfg = "D:\\workspace\\wechat\\src\\main\\resources\\mybatis-generator.xml";
        String genCfg = "D:\\A_Project\\shopping\\shop\\src\\main\\resources\\generator\\generatorConfig.xml";
//        File configFile = new File(GenMain.class.getResource(genCfg).getFile()); //获取路径出错
        File configFile = ResourceUtils.getFile(genCfg);
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = null;
        try {
            config = cp.parseConfiguration(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMLParserException e) {
            e.printStackTrace();
        }
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = null;
        try {
            myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
        try {
            myBatisGenerator.generate(null);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}