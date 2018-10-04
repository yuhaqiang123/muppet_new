package com.muppet.data.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.*;

public class ClassScanner {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private static final String RESOURCE_PATTERN = "**/%s/**/*.class";
    private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

    public ClassScanner() {
    }

    @SafeVarargs
    public final Set<Class<?>> scan(String[] confPkgs, Class<? extends Annotation>... annotationTags) {
        Set<Class<?>> resClazzSet = new HashSet<>();
        List<AnnotationTypeFilter> typeFilters = new LinkedList<>();
        if (!Objects.isNull(annotationTags)) {
            for (Class<? extends Annotation> annotation : annotationTags) {
                typeFilters.add(new AnnotationTypeFilter(annotation, false));
            }
        }
        if (!Objects.isNull(confPkgs)) {
            for (String pkg : confPkgs) {
                String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + String.format(RESOURCE_PATTERN, ClassUtils.convertClassNameToResourcePath(pkg));
                try {
                    Resource[] resources = this.resourcePatternResolver.getResources(pattern);
                    MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(this.resourcePatternResolver);
                    for (Resource resource : resources) {
                        if (resource.isReadable()) {
                            MetadataReader reader = readerFactory.getMetadataReader(resource);
                            String className = reader.getClassMetadata().getClassName();
                            if (ifMatchesEntityType(reader, readerFactory, typeFilters)) {
                                Class<?> curClass = Thread.currentThread().getContextClassLoader().loadClass(className);
                                resClazzSet.add(curClass);
                            }
                        }
                    }
                } catch (Exception e) {
                    logger.error("扫描提取[{}]包路径下，标记了注解[{}]的类出现异常", pattern, StringUtils.collectionToDelimitedString(typeFilters, ","));
                }
            }
        }
        return resClazzSet;
    }

    /**
     * 检查当前扫描到的类是否含有任何一个指定的注解标记
     *
     * @param reader
     * @param readerFactory
     * @return ture/false
     */
    private boolean ifMatchesEntityType(MetadataReader reader, MetadataReaderFactory readerFactory, List<AnnotationTypeFilter> typeFilters) {
        if (!CollectionUtils.isEmpty(typeFilters)) {
            for (TypeFilter filter : typeFilters) {
                try {
                    if (filter.match(reader, readerFactory)) {
                        return true;
                    }
                } catch (IOException e) {
                    logger.error("过滤匹配类型时出错 {}", e.getMessage());
                }
            }
        }
        return false;
    }
}
