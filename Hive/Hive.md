# Hive

Hive 是建立在[Hadoop](https://hadoop.apache.org/) 上的数据仓库基础构架，可以将结构化的数据文件映射成一张数据表，实现了对大量数据的轻度汇总、查询和分析功能。Hive 定义了简单的类SQL 查询语言，简称为HQL，用户可以使用HQL进行数据读写以及计算。同时，HQL可以集成自己的功能来进行自定义分析，例如 User Defined Functions (UDF)。  

### Hive的架构

<img src="Hive.assets/image-20211219103128049.png" alt="image-20211219103128049" style="zoom:70%;" />

**用户连接接口**：Shell/CLI是指Shell命令，JDBC/ODBC是指Hive的java实现，Web接口是指可通过浏览器访问Hive

**Thrift Server**：Hive的可选组件，是一个软件框架服务，允许客户端使用多种编程语言通过编程方式远程访问Hive

**元数据**：Hive将元数据存储在如MySQL、Derby等数据库中。Hive的元数据包括表名、表所属的数据库名、表的拥有者、列与分区字段信息、表类型、表数据所在的路径等

**驱动器(Driver)**：驱动器包含解析器(SQLParser)、编译器(Compiler)、优化器(Optimizer)、执行器(Executer)。完成HQL查询语句从语法解析、语法分析、语法编译、语法优化以及查询计划的生成。生成的查询计划存储在HDFS上，之后提交给Hadoop的YARN，随后调用MapReduce执行

### 本地模式安装

安装Hive之前先安装Hadoop、Java和MySQL(使用MySQL保存元数据)，在Hive官网[下载](http://archive.apache.org/dist/hive/)需要的Hive安装包

上传安装包到服务器，此处选择安装到/usr/local下，将安装包解压到指定目录

~~~shell
tar -zvxf apache-hive-2.1.1-bin.tar.gz -C /usr/local
~~~

进去安装目录修改安装文件名

~~~shell
cd /usr/local
mv apache-hive-2.1.1-bin hive
~~~

进入/etc/profile文件配置Hive环境变量

~~~shell
vi /etc/profile
# 添加如下内容
export HIVE_HOME=/usr/local/hive
export PATH=$HIVE_HOME/bin:$PATH
~~~

让profile文件生效

~~~shell
source /etc/profile
~~~

配置hive-env.sh：进入hive目录下的conf目录，复制出一个hive-env.sh文件

~~~shell
cd hive/conf
cp hive-env.sh.template hive-env.sh
~~~

~~~shell
vi hive-env.sh
# 添加如下内容，此处JAVA_HOME和HADOOP_HOME根据自己安装的路径配置
export HIVE_CONF_DIR=/usr/local/hive/conf
export JAVA_HOME=/usr/local/jdk
export HADOOP_HOME=/usr/local/hadoop
export HIVE_AUX_JARS_PATH=/usr/local/hive/lib
~~~

配置hive-site.xml：在hive/conf目录下复制出一个hive-site.xml文件。把hive-site.xml 中所有包含`${system:java.io.tmpdir}`替换成`/usr/local/hive/iotmp.`，如果系统默认没有指定系统用户名,那么要把配置`${system:user.name}`替换成当前用户名`root`，并且修改如下的四个配置。

> 需要降低MySQL密码策略机制，改为low。hive的元数据在mysql库里创建的数据库hive的编码最好设置成latin1

~~~shell
cp hive-default.xml.template hive-site.xml
vi hive-site.xml
~~~

~~~xml
<!--配置mysql的连接字符串-->
<property>
<name>javax.jdo.option.ConnectionURL</name>
<value>jdbc:mysql://master:3306/hive?createDatabaseIfNotExist=true</value>
<description>JDBC connect string for a JDBC metastore</description>
</property>
<!--配置mysql的连接驱动-->
<property>
<name>javax.jdo.option.ConnectionDriverName</name>
<value>com.mysql.jdbc.Driver</value>
<description>Driver class name for a JDBC metastore</description>
</property>
<!--配置登录mysql的用户-->
<property>
<name>javax.jdo.option.ConnectionUserName</name>
<value>root</value>
<description>username to use against metastore database</description>
</property>
<!--配置登录mysql的密码-->
<property>
<name>javax.jdo.option.ConnectionPassword</name>
<value>123456</value>
<description>password to use against metastore database</description>
</property>
~~~

配置好hive-site.xml后，需要下载上传MySQL的驱动包`mysql-connector-java-5.7.23-bin.jar`到/usr/local/hive/lib下，随后启动HDFS和YARN

~~~shell
start-dfs.sh
start-yarn.sh
~~~

初始化数据库

~~~shell
schematool -initSchema -dbType mysql
~~~

启动Hive

~~~sh
hive
~~~

### 数据类型与存储格式

##### 数据类型

`BOOLEAN`	   true/false  
`TINYINT`	   1字节的有符号整数，表示范围: -128~127
`SMALLINT`    2个字节的有符号整数，表示范围: -32768~32767
`INT`	            4个字节的带符号整数，表示范围: -2147483648~2147483647
`BIGINT`	     8字节带符号整数，表示范围: -2^64~2^64-1
`FLOAT`	       4字节单精度浮点数
`DOUBLE`	     8字节双精度浮点数
`DEICIMAL`    任意精度的带符号小数
`STRING`	     字符串
`VARCHAR`	   变长字符串
`CHAR`	          固定长度字符串
`BINARY`	      字节数组
`TIMESTAMP`  时间戳
`DATE`	          日期
`ARRAY`	        数组类型
`MAP`	            key-value，key必须为原始类型，value可以任意类型
`STRUCT`	     段集合，类型可以不同

##### 存储格式

**textfile**：，行式存储，纯文本文件存储格式，不压缩，也是hive的默认存储格式，磁盘开销大，数据解析开销大

**sequencefile**：二进制行式存储，会压缩，不能使用load方式加载数据

**parquet**：二进制列式存储，会压缩，不能使用load方式加载数据

**orc**：二进制列式存储，会压缩，不能load。查询性能高

##### 常用压缩格式

| 压缩    | 压缩比 | 压缩速度 | 需要安装 | 编码器                                     | 是否可切分       |
| ------- | ------ | -------- | -------- | ------------------------------------------ | ---------------- |
| DEFAULT | 无     | 无       | 否       | org.apache.hadoop.io.compress.DefaultCodec | 否               |
| Gzip    | 很高   | 比较快   | 否       | org.apache.hadoop.io.compress.GzipCodec    | 否               |
| bzip2   | 最高   | 慢       | 否       | org.apache.hadoop.io.compress.BZip2Codec   | 是               |
| LZO     | 比较高 | 很快     | 是       | com.hadoop.compression.lzo.LzopCodec       | 是(需要建立索引) |
| Snappy  | 比较高 | 很快     | 是       | org.apache.hadoop.io.compress.SnappyCodec  | 否               |

### 表类型

**内部表**：表目录会创建在`hive-site.xml`中的`{hive.metastore.warehouse.dir}`目录下，数据也会存储到该目录下，删除内部表后数据也同样删除，默认创建的表就是内部表

**外部表**：外部表需要使用关键字`external`，外部表会根据创建表时`LOCATION`指定的路径来创建目录，删除外部表后，表对应的数据不会被删除

**分区表**：Hive在做数据查询时，会扫描整个表内容，随着数据量的加大，全表扫描会消耗更多的时间，而某些情况业务只需要查询某些特定的数据，所以Hive在建表的时候引入了partiiton的概念。即在建表时，将整个表存储在不同的子目录中，每一个子目录对应一个分区，在查询时，可以指定分区查询，避免全表扫描，从而提高查询效率

**分桶表**：桶表就是对指定列进行哈希(hash)计算，然后会根据 hash 值进行切分数据，将具有不同 hash 值的数据写到每个桶对应的文件中。Hive分桶的原理与MapReduce中的HashPartitioner的原理一模一样，使用分桶字段的hash值对分桶的数量进行取模(取余)。针对某一列进行分桶存储。每一条记录都是通过分桶字段的值的hash对分桶个数取余，然后确定放入哪个桶

### Hive函数



### 数据库操作























~~~sql
select
    user_id,
    scope
from
(
    select
        user_id,
        scope,
        row_number() over(partition by user_id order by total desc, n desc) as rk
    from 
    (
        select
            user_id,
            scope,
            case scope when '0-1' then 1 when '2-5' then 3 when '5-10' then 4
                       when '1-2' then 2 when '10-20' then 5 else 6 end as n,
            sum(total) as total
        from t1
        group by user_id, scope
    ) b
) a
where rk = 1;
~~~



