package cn.redblood;

import org.flowable.engine.*;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author The_Beatles
 * @date 2022/3/23 11:19
 */

public class Test01 {

    /**
     * 获取流程引擎对象
     */
    @Test
    public void testProcessEngine() {
        // 获取 ProcessEngineConfiguration 对象
        ProcessEngineConfiguration configuration = new StandaloneProcessEngineConfiguration();
        // 配置数据库相关连接信息
        configuration.setJdbcDriver("com.mysql.cj.jdbc.Driver");
        configuration.setJdbcUsername("root");
        configuration.setJdbcPassword("***");
        configuration.setJdbcUrl("jdbc:mysql://101.133.175.171:3306/flowable-learn?serverTimezone=UTC");
        // 如果数据库中的表结构不存在就新建
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        // 通过 ProcessEngineConfiguration 构建我们需要的 processEngine 对象
        ProcessEngine processEngine = configuration.buildProcessEngine();
        System.out.println("processEngine: ==>" + processEngine);
    }

    ProcessEngineConfiguration configuration = null;

    @Before
    public void before() {
        // 获取 ProcessEngineConfiguration 对象
        configuration = new StandaloneProcessEngineConfiguration();
        // 配置数据库相关连接信息
        configuration.setJdbcDriver("com.mysql.cj.jdbc.Driver");
        configuration.setJdbcUsername("root");
        configuration.setJdbcPassword("***");
        configuration.setJdbcUrl("jdbc:mysql://101.133.175.171:3306/flowable-learn?serverTimezone=UTC");
        // 如果数据库中的表结构不存在就新建
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
    }

    /**
     * 部署流程
     */
    @Test
    public void testDeploy() {
        // 1.获取 ProcessEngine 对象
        ProcessEngine processEngine = configuration.buildProcessEngine();
        // 2.获取RepositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 3.完成流程的部署操作
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("holiday-request.bpmn20.xml") // 关联要部署的流程文件
                .name("请假流程")
                .deploy();
        System.out.println("deploy.getId() = " + deploy.getId());
        System.out.println("deploy.getName() = " + deploy.getName());
    }

    /**
     * 查询任务
     */
    @Test
    public void testDeployQuery() {
        ProcessEngine processEngine = configuration.buildProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        ProcessDefinition result = repositoryService.createProcessDefinitionQuery()
                .deploymentId("1")
                .singleResult();
        System.out.println("result.getDeploymentId() = " + result.getDeploymentId());
        System.out.println("result.getId() = " + result.getId());
        System.out.println("result.getName() = " + result.getName());
        System.out.println("result.getDescription() = " + result.getDescription());
    }

    /**
     * 删除流程
     */
    @Test
    public void testDeleteDeploy() {
        ProcessEngine processEngine = configuration.buildProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 删除部署的流程 第一个参数是流程ID，如果部署的流程已经启动了就不允许删除了
//        repositoryService.deleteDeployment("2501");
        // 第二个参数是级联删除，如果流程启动了 相关的任务一并会被删除掉
        repositoryService.deleteDeployment("2501", true);
    }

    /**
     * 启动流程
     */
    @Test
    public void testRunProcess() {
        ProcessEngine processEngine = configuration.buildProcessEngine();
        // 我们需要通过RuntimeService来启动流程实例
        RuntimeService runtimeService = processEngine.getRuntimeService();
        // 构建流程变量
        Map<String, Object> variables = new HashMap<>(16);
        variables.put("employee", "刘德华");
        variables.put("nrOfHolidays", 3);
        variables.put("description", "爷不干了！");
        //  启动流程实例
        ProcessInstance holidayRequest = runtimeService.startProcessInstanceByKey("holidayRequest", variables);
        System.out.println("holidayRequest.getProcessDefinitionId() = " + holidayRequest.getProcessDefinitionId());
        System.out.println("holidayRequest.getActivityId() = " + holidayRequest.getActivityId());
        System.out.println("holidayRequest.getId() = " + holidayRequest.getId());
    }

    /**
     * 测试任务查询
     */
    @Test
    public void testQueryTask() {
        ProcessEngine processEngine = configuration.buildProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        List<Task> list = taskService.createTaskQuery()
                .processDefinitionKey("holidayRequest")
                .taskAssignee("andy")
                .list();
        for (Task task : list) {
            System.out.println("task.getProcessDefinitionId() = " + task.getProcessDefinitionId());
            System.out.println("task.getName() = " + task.getName());
            System.out.println("task.getAssignee() = " + task.getAssignee());
            System.out.println("task.getDescription() = " + task.getDescription());
            System.out.println("task.getId() = " + task.getId());
        }
    }

    /**
     * 完成任务
     */
    @Test
    public void testCompleteTask() {
        ProcessEngine processEngine = configuration.buildProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("holidayRequest")
                .taskAssignee("andy")
                .singleResult();
        // 创建流程变量
        Map<String, Object> map = new HashMap<>(16);
        map.put("approved", false);
        // 完成任务
        taskService.complete(task.getId(), map);
    }

    /**
     * 获取流程任务的历史数据
     */
    @Test
    public void testHistory() {
        ProcessEngine processEngine = configuration.buildProcessEngine();
        HistoryService historyService = processEngine.getHistoryService();
        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery()
                .processDefinitionId("holidayRequest:1:10003")
                .finished() // 查询的历史记录的状态是已完成
                .orderByHistoricActivityInstanceEndTime().asc() // 指定排序的字段和顺序
                .list();
        for (HistoricActivityInstance history : list) {
            System.out.println(history.getActivityName() + "：" + history.getAssignee()
                    + "--" + history.getActivityId() + ": " + history.getDurationInMillis() + "ms.");
        }
    }

}
