package com.piesat.excel;

import com.piesat.excel.common.basehandle.factory.DataStoreFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;

@EnableAutoConfiguration(exclude = {
        org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class
})
@ComponentScan(basePackages = {"com.piesat.excel"})
@ServletComponentScan
@EnableTransactionManagement
@SpringBootApplication
public class Application extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        SpringApplication.run(Application.class, args);
        DataStoreFactory.initPool();
//        Class clazz = BuoyInfo.class;
//        Field[] fields = clazz.getDeclaredFields();
//        for (Field field : fields) {
//            SuperLogger.e(field.getName());
//        }
//        try {
//            BuoyExcelReadHelper excelReadHelper = new BuoyExcelReadHelper();
//            excelReadHelper.getExcelContent(new File(path), new ReadExcelResultCallback<BuoyInfo>(){
//
//                @Override
//                public void onResult(BuoyInfo buoyInfo) {
//                    SuperLogger.e(buoyInfo);
//                }
//            });
//
////            List<BuoyInfo> buoyInfos = excelReadHelper.ge(path, BuoyInfo.getProperties(), BuoyInfo.class);
////            for (BuoyInfo buoyInfo : buoyInfos) {
////                SuperLogger.e(buoyInfo);
////            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        String path="E:\\软件\\广东18个简易验潮站坐标站代码.xls";
//        try {
//            List<TideStationInfo> tideStationInfos = BaseExcelReadHelper.showExcel(path);
//            for (TideStationInfo tideStationInfo : tideStationInfos) {
//                SuperLogger.e(tideStationInfo);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        String path="E:\\excel\\风场、温度场\\温度场\\msst_5km_20170926.tif";
//        String jpg="E:\\excel\\风场、温度场\\温度场\\";
//        TemperatureDataHandler handler = new TemperatureDataHandler();
//        handler.onStore(jpg);
//        String path = "C:\\Users\\gersy\\Desktop\\txxt - 副本\\9901\\permonth\\T0211801";
//        String path = "C:\\Users\\gersy\\Desktop\\txxt - 副本\\9901\\perclock\\WL0328";
//        TideMonthDataHandler dataStoreHandler = ApplicationContextRegister.getBean(TideMonthDataHandler.class);
//        dataStoreHandler.onStore(path);

//        String path="E:\\excel\\wind\\METOP\\20180424\\20180423_181500_20180424_010000_metopa_coa_东中国海_ssw_7_join_desc.jpg";
//        String path="E:\\excel\\风场、温度场";
//        WindDataHandler windDataHandler = new WindDataHandler();
//        windDataHandler.onStore(path);

//        IDataStoreHandler dataStoreHandler = null;
//        String path = "C:\\Users\\gersy\\Desktop\\新建文件夹 (5)";
//        String distPath = "C:\\Users\\gersy\\Desktop\\新建文件夹 (5)";
////        dataStoreHandler = DataStoreFactory.get("GF4");
////        dataStoreHandler.onStore(path);
//
//        IMultiDataTypeStoreHandler handler = new BaseMultiDataTypeStoreHandler();
//        handler.onStore(distPath);

    }

}
//@EnableDiscoveryClient
//public class Application {
//
//
//    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);
////        SpringApplication app = new SpringApplication(Application.class);
////        app.setBannerMode(Banner.Mode.OFF);
////        app.run(args);
////        logger.info("PortalApplication is success!");
//        System.err.println("sample started. http://localhost:8080/user/test");
//    }
//
//}
