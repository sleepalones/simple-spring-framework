package org.simpleframework.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileFilter;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * @author brotherming
 * @createTime 2022年05月10日 15:26:00
 */
@Slf4j
public class ClassUtil {

    public final static String FILE_PROTOCOL = "file";

    /**
     * 获取包下类集合
     */
    public static Set<Class<?>> extractPackageClass(String packageName) {
        //1.获取到类的加载器
        ClassLoader classLoader = getClassLoader();
        //2.通过类加载器获取到加载的资源
        URL url = classLoader.getResource(packageName.replace(".", "/"));
        if (url == null) {
            log.warn("unable to retrieve anything from package:" + packageName);
            return null;
        }
        //3.依据不同的资源类型，采用不同的方式获取资源的集合
        Set<Class<?>> classSet = null;
        if (url.getProtocol().equalsIgnoreCase(FILE_PROTOCOL)) {
            classSet = new HashSet<>();
            File packageDirectory = new File(url.getPath());
            extractClassFile(classSet,packageDirectory,packageName);
        }
        //TODO 此处可以加入针对其他类型资源的处理
        return classSet;
    }

    /**
     * 递归获取目标package里面的所有class文件（包括子package里的class文件）
     * @param emptyClassSet 装载目标类的集合
     * @param fileSource 文件或者目录
     * @param packageName 包名
     */
    private static void extractClassFile(Set<Class<?>> emptyClassSet, File fileSource, String packageName) {
        if (!fileSource.isDirectory()) {
            return;
        }
        File[] files = fileSource.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                if (file.isDirectory()) {
                    return true;
                }else {
                    String absolutePath = file.getAbsolutePath();
                    if (absolutePath.endsWith(".class")) {
                        addToClassSet(absolutePath);
                    }
                }
                return false;
            }

            private void addToClassSet(String absolutePath) {
                //1.从class文件的绝对值路径里提前出包含了package的类名
                absolutePath = absolutePath.replace(File.separator,".");
                String className = absolutePath.substring(absolutePath.indexOf(packageName));
                className = className.substring(0,className.lastIndexOf("."));
                //2.通过反射机制获取对应的Class对象并加入到classSet里
                emptyClassSet.add(loadClass(className));
            }
        });
        if (files != null) {
            for (File file : files) {
                extractClassFile(emptyClassSet,file,packageName);
            }
        }
    }

    /**
     * 获取Class对象
     */
    public static Class<?> loadClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            log.error("load class error:",e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取classLoader
     */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

}
