package cn.redblood;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.ProcessEngines;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.junit.Test;

/**
 * @author The_Beatles
 * @date 2022/3/28 16:42
 */

public class ProcessEngineTest {

    /**
     * 测试时获取流程引擎的方式
     */
    @Test
    public void processEngine01() {
        // 获取 ProcessEngineConfiguration 对象
        ProcessEngineConfiguration configuration = new StandaloneProcessEngineConfiguration();
        // 配置数据库相关连接信息
        configuration.setJdbcDriver("com.mysql.cj.jdbc.Driver");
        configuration.setJdbcUsername("root");
        configuration.setJdbcPassword("****");
        configuration.setJdbcUrl("jdbc:mysql://101.133.175.171:3306/flowable-learn?serverTimezone=UTC");
        // 如果数据库中的表结构不存在就新建
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        // 通过 ProcessEngineConfiguration 构建我们需要的 processEngine 对象
        ProcessEngine processEngine = configuration.buildProcessEngine();
        System.out.println("processEngine: ==>" + processEngine);
    }

    /**
     * 加载默认的配置文件
     */
    @Test
    public void processEngine02() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        System.out.println("processEngine: ==>" + processEngine);
    }

    /**
     * 加载自定义名称的配置文件
     */
    @Test
    public void processEngine03() {
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("flowable.cfg.xml");
        ProcessEngine processEngine = configuration.buildProcessEngine();
        System.out.println("processEngine = " + processEngine);

    }

}
