package org.flowable;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

/**
 * @author The_Beatles
 * @date 2022/3/27 17:28
 */

public class SendRejectionMail implements JavaDelegate {

    /**
     * Flowable中的触发器
     *
     * @param execution
     */
    @Override
    public void execute(DelegateExecution execution) {
        // 触发执行的逻辑 按照我们在流程中的定义应该给被拒绝的员工发送通知的邮件
        System.out.println("给我接着干！");
    }
}
