package
        Marketing.Wrapping;

import Manufacturing.CanEntity.Can;
import Marketing.Wrapping.Builder.FruitWrappingBuilder;
import Marketing.Wrapping.Builder.VegetableWrappingBuilder;
import Marketing.Wrapping.Builder.WrappingBuilder;
import Marketing.Wrapping.Builder.WrappingDirector;
import Marketing.Wrapping.Converter.WrappingConverter;
import Marketing.Wrapping.Cover.WrappingCover;
import Presentation.Protocol.OutputManager;


/**
 * 负责管理包装业务的包装部门类
 * 实现单例模式.
 *
 * @author 王立友
 * @date 2021/10/24 10:42
 */
public class WrappingDepartment {

    private static final WrappingDepartment instance = new WrappingDepartment();

    private WrappingDepartment() {
    }

    public static WrappingDepartment getInstance() {
        return instance;
    }

    /**
     * 包装部门对外接口,实现对罐头信息转化为封面罐头信息
     *
     * @param can : 等待包装的罐头信息
     * @return : Marketing.Wrapping.Cover.WrappingCover
     * @author "王立友"
     * @date 2021-10-24 10:48
     */
    public WrappingCover printWrappingCover(Can can) {

        //生成罐头包装实体信息;
        WrappingCanInfo wrappingCanInfo = convertWrappingCanInfo(can);

        //生成罐头工厂包装信息;
        WrappingFactoryInfo wrappingFactoryInfo = WrappingFactoryInfo.getInstance();

        //生成罐头包装纸背景信息;
        String wrappingBackGround = getWrappingBackground(wrappingCanInfo);

        //计算罐头价格信息;
        //float canPrice = computeCanPrice(wrappingFactoryInfo);

        //获得对应罐头的Builder;
        WrappingBuilder wrappingBuilder = getBuilder(wrappingCanInfo.getCanName());
        //生成WrappingDirector;
        WrappingDirector wrappingDirector = new WrappingDirector(wrappingBuilder);
        //开始构建;
        wrappingDirector.construct(wrappingCanInfo, wrappingFactoryInfo, wrappingBackGround);
        //得到产品;
        OutputManager.getInstance().print(
                "实现建造者设计模式：完成包装封面信息的填充与组装",
                "實現建造者設計模式：完成包裝封面信息的填充與組裝",
                "Realize the builder design mode: complete the filling and assembly of the package cover information"
        );

        return wrappingBuilder.build();
    }

    /**
     * 转换器模式的调用;
     *
     * @param can : 待转换的罐头;
     * @return : Marketing.Wrapping.WrappingCanInfo 包装纸信息;
     * @author "王立友"
     * @date 2021-10-24 14:54
     */
    private WrappingCanInfo convertWrappingCanInfo(Can can) {
        WrappingConverter wrappingConverter = new WrappingConverter();
        //实现转换器过程的输出.
        OutputManager.getInstance().print(
                "实现转换器设计模式：将罐头实体信息转化为包装信息",
                "實現轉換器設計模式：將罐頭實體信息轉化為包裝信息",
                "Realize the converter design pattern: convert canned entity information into packaging information"
        );
        return wrappingConverter.getFromCanEntityFunction().apply(can);
    }

    /**
     * 根据wrappingCanInfo中的罐头名称信息生成对应的背景,返回url.
     *
     * @param wrappingCanInfo :  包装信息;
     * @return : java.lang.String
     * @author "王立友"
     * @date 2021-10-24 15:04
     */
    private String getWrappingBackground(WrappingCanInfo wrappingCanInfo) {
        String canName = wrappingCanInfo.getCanName();
        return canName + ".png";
    }

    /**
     * 根据罐头名字获得对应的builder.
     * @param canName :
     * @return : Marketing.Wrapping.Builder.WrappingBuilder
     * @author "王立友"
     * @date 2021-10-24 16:13
     */
    private WrappingBuilder getBuilder(String canName) {
        if (canName.equals("水果罐头")) {
            return new FruitWrappingBuilder();
        } else if(canName.equals("蔬菜罐头")){
            return new VegetableWrappingBuilder();
        }
        return null;
    }

    /**
     * 对外接口的呈现，包装罐头;
     * @param can :  一个待包装的实体罐头;
     * @return : Marketing.Wrapping.WrappedCan
     * @author "王立友"
     * @date 2021-10-25 1:13
     */
    public WrappedCan wrapCan(Can can){

        return new WrappedCan(can, printWrappingCover(can));
    }

    /**
     * 对外接口的呈现,用来输出包装好的罐头的信息;
     * @param wrappedCan :
     * @author "王立友"
     * @date 2021-10-25 1:14
     */
    public void outputWrappedCan(WrappedCan wrappedCan){

        WrappingCover wrappingCover = wrappedCan.getWrappingCover();

        OutputManager.getInstance().print("输出包装封面的罐头实体信息\n",
                "輸出包裝封面的罐頭實體信息\n","Output canned entity information on wrapping cover\n");

        OutputManager.getInstance().print("罐头名称:"+wrappingCover.getWrappingCanInfo().getCanName()+"\n",
                "罐頭名稱:"+wrappingCover.getWrappingCanInfo().getCanName()+"\n",
                "canName:"+wrappingCover.getWrappingCanInfo().getCanName()+"\n" );
        OutputManager.getInstance().print("罐头尺寸:"+wrappingCover.getWrappingCanInfo().getSize()+"\n",
                "罐頭尺寸:"+wrappingCover.getWrappingCanInfo().getSize()+"\n",
                "canSize:"+wrappingCover.getWrappingCanInfo().getSize()+"\n" );
        OutputManager.getInstance().print("罐头原料列表:"+wrappingCover.getWrappingCanInfo().getIngredients()+"\n",
                "罐頭原料列表:"+wrappingCover.getWrappingCanInfo().getIngredients()+"\n",
                "canIngredients:"+wrappingCover.getWrappingCanInfo().getIngredients()+"\n" );
        OutputManager.getInstance().print("罐头生产日期:"+wrappingCover.getWrappingCanInfo().getManufactureTime()+"\n",
                "罐頭生產日期:"+wrappingCover.getWrappingCanInfo().getManufactureTime()+"\n",
                "canManufactureTime:"+wrappingCover.getWrappingCanInfo().getManufactureTime()+"\n" );
        OutputManager.getInstance().print("罐头保质期:"+wrappingCover.getWrappingCanInfo().getShelfTime()+"\n",
                "罐頭保質期:"+wrappingCover.getWrappingCanInfo().getShelfTime()+"\n",
                "canShelfTime:"+wrappingCover.getWrappingCanInfo().getShelfTime()+"\n" );

        OutputManager.getInstance().print("*************************","*************************","*************************");
        OutputManager.getInstance().print("输出包装封面的罐头工厂信息\n",
                "輸出包裝封面的罐頭工廠信息\n","Output cannery information on wrapping cover\n");

        OutputManager.getInstance().print("工厂名称:"+wrappingCover.getWrappingFactoryInfo().getFactoryName()+"\n",
                "工廠名稱:"+wrappingCover.getWrappingFactoryInfo().getFactoryName()+"\n",
                "factoryName:"+wrappingCover.getWrappingFactoryInfo().getFactoryName()+"\n");
        OutputManager.getInstance().print("工厂地址:"+wrappingCover.getWrappingFactoryInfo().getFactoryAddress()+"\n",
                "工廠地址:"+wrappingCover.getWrappingFactoryInfo().getFactoryAddress()+"\n",
                "factoryAddress:"+wrappingCover.getWrappingFactoryInfo().getFactoryAddress()+"\n");
        OutputManager.getInstance().print("工厂联系方式:"+wrappingCover.getWrappingFactoryInfo().getFactoryPhone()+"\n",
                "工廠聯繫方式:"+wrappingCover.getWrappingFactoryInfo().getFactoryPhone()+"\n",
                "factoryPhone:"+wrappingCover.getWrappingFactoryInfo().getFactoryPhone()+"\n");
        OutputManager.getInstance().print("工厂网址:"+wrappingCover.getWrappingFactoryInfo().getFactoryWebsite()+"\n",
                "工廠網址:"+wrappingCover.getWrappingFactoryInfo().getFactoryWebsite()+"\n",
                "factoryWebsite:"+wrappingCover.getWrappingFactoryInfo().getFactoryWebsite()+"\n");
        OutputManager.getInstance().print("食用提示:"+wrappingCover.getWrappingFactoryInfo().getFactoryTip()+"\n",
                "食用提示:"+wrappingCover.getWrappingFactoryInfo().getFactoryTip()+"\n",
                "factoryTips:"+wrappingCover.getWrappingFactoryInfo().getFactoryTip()+"\n");
    }
}