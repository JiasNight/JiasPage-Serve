package com.page.common.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.SpringBootVFS;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.sql.DataSource;
import org.apache.ibatis.io.VFS;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;

@Configuration
public class MybatisPlusConfig {

  @Autowired private Environment env;

  static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";

  /** 分页插件 */
  @Bean
  public MybatisPlusInterceptor mybatisPlusInterceptor() {
    MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
    // 1.创建分页插件
    PaginationInnerInterceptor paginationInnerInterceptor =
        new PaginationInnerInterceptor(DbType.MYSQL);
    paginationInnerInterceptor.setMaxLimit(1000L);
    // 2.添加分页插件
    interceptor.addInnerInterceptor(paginationInnerInterceptor);
    return interceptor;
  }

  //  @Bean
  //  public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
  //    // 获取配置文件中定义的 mybatis-plus.typeAliasesPackage 的值
  //    String typeAliasesPackage = env.getProperty("mybatis-plus.type-aliases-package");
  //    // 获取配置文件中定义的 mybatis-plus.mapperLocations 的值
  //    String mapperLocations = env.getProperty("mybatis-plus.mapper-locations");
  //    // 获取配置文件中定义的 mybatis-plus.configLocation 的值
  //    String configLocation = env.getProperty("mybatis-plus.config-location");
  //    typeAliasesPackage = setTypeAliasesPackage(typeAliasesPackage);
  //    VFS.addImplClass(SpringBootVFS.class);
  //
  //    final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
  //    sessionFactory.setDataSource(dataSource);
  //    sessionFactory.setTypeAliasesPackage(typeAliasesPackage);
  //    // 在所有jar包的classpath下查找所有以Mapper.xml结尾的xml文件
  //    sessionFactory.setMapperLocations(
  //        new PathMatchingResourcePatternResolver().getResources(mapperLocations));
  //    sessionFactory.setConfigLocation(new DefaultResourceLoader().getResource(configLocation));
  //    return sessionFactory.getObject();
  //  }
  //
  //  /**
  //   * 自定义typeAliasesPackage 在application.yml中typeAliasesPackage的值等于com.ruoyi.**.domain
  //   * 但是mybatis是无法识别**通配符的 需要我们自己实现通过**通配符匹配到所有的domain包
  //   *
  //   * @param typeAliasesPackage
  //   * @return
  //   */
  //  public static String setTypeAliasesPackage(String typeAliasesPackage) {
  //    ResourcePatternResolver resolver =
  //        (ResourcePatternResolver) new PathMatchingResourcePatternResolver();
  //    MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resolver);
  //    List<String> allResult = new ArrayList<String>();
  //    try {
  //      for (String aliasesPackage : typeAliasesPackage.split(",")) {
  //        List<String> result = new ArrayList<String>();
  //        aliasesPackage =
  //            ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
  //                + ClassUtils.convertClassNameToResourcePath(aliasesPackage.trim())
  //                + "/"
  //                + DEFAULT_RESOURCE_PATTERN;
  //        Resource[] resources = resolver.getResources(aliasesPackage);
  //        if (resources != null && resources.length > 0) {
  //          MetadataReader metadataReader = null;
  //          for (Resource resource : resources) {
  //            if (resource.isReadable()) {
  //              metadataReader = metadataReaderFactory.getMetadataReader(resource);
  //              try {
  //                result.add(
  //                    Class.forName(metadataReader.getClassMetadata().getClassName())
  //                        .getPackage()
  //                        .getName());
  //              } catch (ClassNotFoundException e) {
  //                e.printStackTrace();
  //              }
  //            }
  //          }
  //        }
  //        if (result.size() > 0) {
  //          HashSet<String> hashResult = new HashSet<String>(result);
  //          allResult.addAll(hashResult);
  //        }
  //      }
  //      if (allResult.size() > 0) {
  //        typeAliasesPackage = String.join(",", (String[]) allResult.toArray(new String[0]));
  //      } else {
  //        throw new RuntimeException(
  //            "mybatis typeAliasesPackage 路径扫描错误,参数typeAliasesPackage:"
  //                + typeAliasesPackage
  //                + "未找到任何包");
  //      }
  //    } catch (IOException e) {
  //      e.printStackTrace();
  //    }
  //    return typeAliasesPackage;
  //  }
}
