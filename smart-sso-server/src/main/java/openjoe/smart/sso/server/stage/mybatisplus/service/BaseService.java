package openjoe.smart.sso.server.stage.mybatisplus.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import openjoe.smart.sso.server.stage.core.Page;
import openjoe.smart.sso.server.stage.mybatisplus.util.PageHelper;


public interface BaseService<T> extends IService<T> {

 
    default Page<T> findPage(long current, long size) {
        IPage<T> t = page(new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(current, size));
        return PageHelper.convert(t);
    }

   
    default Page<T> findPage(long current, long size, Wrapper<T> wrapper) {
        IPage<T> t = page(new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(current, size), wrapper);
        return PageHelper.convert(t);
    }
}