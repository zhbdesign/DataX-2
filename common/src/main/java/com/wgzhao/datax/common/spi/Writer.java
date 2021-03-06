package com.wgzhao.datax.common.spi;

import com.wgzhao.datax.common.base.BaseObject;
import com.wgzhao.datax.common.plugin.AbstractJobPlugin;
import com.wgzhao.datax.common.plugin.AbstractTaskPlugin;
import com.wgzhao.datax.common.plugin.RecordReceiver;
import com.wgzhao.datax.common.util.Configuration;

import java.util.List;

/**
 * 每个Writer插件需要实现Writer类，并在其内部实现Job、Task两个内部类。
 */
public abstract class Writer
        extends BaseObject
{
    /**
     * 每个Writer插件必须实现Job内部类
     */
    public abstract static class Job
            extends AbstractJobPlugin
    {
        /**
         * 切分任务。<br>
         *
         * @param mandatoryNumber 为了做到Reader、Writer任务数对等，这里要求Writer插件必须按照源端的切分数进行切分。否则框架报错！
         *
         * @return list of configuration
         */
        public abstract List<Configuration> split(int mandatoryNumber);
    }

    /**
     * 每个Writer插件必须实现Task内部类
     */
    public abstract static class Task
            extends AbstractTaskPlugin
    {

        public abstract void startWrite(RecordReceiver lineReceiver);

        public boolean supportFailOver() {return false;}
    }
}
