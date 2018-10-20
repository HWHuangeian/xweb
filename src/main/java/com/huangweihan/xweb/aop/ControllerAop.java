package com.huangweihan.xweb.aop;

import com.huangweihan.xweb.core.pojo.ResultBean;
import com.huangweihan.xweb.exception.CheckException;
import com.huangweihan.xweb.exception.NoPermissionException;
import com.huangweihan.xweb.exception.UnloginException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Description
 *
 * @author: Administrator
 * @date: 2018/10/18 0018
 */
@Aspect
@Component
public class ControllerAop {

    /**
     * 日志处理器必须用static声明，防止被垃圾回收
     */
    private static final Logger logger = LoggerFactory.getLogger(ControllerAop.class);

    /**
     * 定义Pointcut，拦截返回这个ResultBean的public方法：public ResultBean<?> *(..)
     */
    @Pointcut("execution(public com.huangweihan.xweb.core.pojo.ResultBean *(..)))")
    public void aspectJMethod() {
    }

    @Around("aspectJMethod()")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) {
        long startTime = System.currentTimeMillis();
        ResultBean<?> result = null;

        try {
            result = (ResultBean<?>) pjp.proceed();
            long elapsedTime = System.currentTimeMillis() - startTime;
            logger.info("[{}] processing time: {}ms", pjp.getSignature(), elapsedTime);
        } catch (Throwable e) {
            result = handlerException(pjp, e);
        }

        return result;
    }

    private ResultBean<?> handlerException(ProceedingJoinPoint pjp, Throwable e) {
        ResultBean<?> result = new ResultBean();

        // 已知异常【注意：已知异常不要打印堆栈，否则会干扰日志】
        // 校验出错，参数非法
        if (e instanceof CheckException || e instanceof IllegalArgumentException) {
            result.setMsg(e.getLocalizedMessage());
            result.setCode(ResultBean.CHECK_FAIL);
        }
        // 没有登陆
        else if (e instanceof UnloginException) {
            result.setMsg("Unlogin");
            result.setCode(ResultBean.NO_LOGIN);
        }
        // 没有权限
        else if (e instanceof NoPermissionException) {
            result.setMsg("NO PERMISSION");
            result.setCode(ResultBean.NO_PERMISSION);
        } else {
            logger.error(pjp.getSignature() + " error ", e);

            // TODO 未知的异常，应该格外注意，可以发送邮件通知等
            result.setMsg(e.toString());
            result.setCode(ResultBean.UNKNOWN_EXCEPTION);
        }

        return result;
    }

}
