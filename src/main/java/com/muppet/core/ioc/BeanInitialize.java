package com.muppet.core.ioc;

public interface BeanInitialize {


    public <T> T initializeBean(Class<T> clazz);

    //public List<Object> initializeBeans(List<Class<?>> clazzList);
}
