# jenkin-dind-sample
jenkins with dind(docker in docker)

简单的基于jenkins，docker的持续集成流程

## 步骤一
安装Docker   地址：https://docs.docker.com/engine/installation/

## 步骤二
构建Jenkins的dind镜像，由于jenkins自带的docker镜像将jenkins独立为一个用户组，单纯地在镜像内部将/var/run/docker.sock和主机的docker.sock挂载并不能解决用户组权限的问题，
也就是会访问不了文件docker.sock，因此从dockerfile入手，基于docker和jenkins的dockerfile，修改成为了主目录下的[dockerfile](./dockerfile)

在安装有docker的主机上运行
```sudo docker built . -t jenkins-docker:1/0 ```生成可以使用的jenkins-docker镜像

## 步骤三
运行jenkins镜像

在安装有docker的主机上运行
```sudo docker run -d -p 8080:8080  -v /var/run/docker.sock:/var/run/docker.sock -v (主机上自定义一个文件夹地址如~/jenkins):/var/jenkins_home -ti jenkins-docker:1/0 ```

之后访问本机的8080端口就能看到jenkins，根据提示一步一步完成配置即可。

## 步骤四
自己完成程序的编码，测试的编写，通过jenkins对git，docker,mvn(java)，junit(java)进行配置和脚本的编写
jenkins配置完成之后，理想效果是点击构建按钮之后，jenkins自动从git上拉取更新，进行编译，输出测试结果，构建出可运行的镜像(可参考[dockerfile](./deployment/dockerfile))，将镜像部署到docker里去。

示例输出：
```
Started by user panda
Building in workspace /var/jenkins_home/workspace/test-docker
 > git rev-parse --is-inside-work-tree # timeout=10
Fetching changes from the remote Git repository
 > git config remote.origin.url https://github.com/wy2745/jenkin-dind-example.git # timeout=10
Fetching upstream changes from https://github.com/wy2745/jenkin-dind-example.git
 > git --version # timeout=10
using GIT_ASKPASS to set credentials github
 > git fetch --tags --progress https://github.com/wy2745/jenkin-dind-example.git +refs/heads/*:refs/remotes/origin/*
 > git rev-parse refs/remotes/origin/master^{commit} # timeout=10
 > git rev-parse refs/remotes/origin/origin/master^{commit} # timeout=10
Checking out Revision 79676502baa4605c855a7758fce4ea5ed1b88930 (refs/remotes/origin/master)
Commit message: "no message"
 > git config core.sparsecheckout # timeout=10
 > git checkout -f 79676502baa4605c855a7758fce4ea5ed1b88930
 > git rev-list b3e149c1e325b21cabec35e4d18d9712b90c252c # timeout=10
[test-docker] $ /bin/sh -xe /tmp/jenkins1458374983805827743.sh
+ cd /var/jenkins_home/workspace/test-docker
+ mvn install
[[1;34mINFO[m] Scanning for projects...
[[1;34mINFO[m] 
[[1;34mINFO[m] [1m------------------------------------------------------------------------[m
[[1;34mINFO[m] [1mBuilding jenkins-dind-test 1.0-SNAPSHOT[m
[[1;34mINFO[m] [1m------------------------------------------------------------------------[m
[[1;34mINFO[m] 
[[1;34mINFO[m] [1m--- [0;32mmaven-resources-plugin:2.6:resources[m [1m(default-resources)[m @ [36mjenkins-dind-test[0;1m ---[m
[[1;33mWARNING[m] Using platform encoding (UTF-8 actually) to copy filtered resources, i.e. build is platform dependent!
[[1;34mINFO[m] skip non existing resourceDirectory /var/jenkins_home/workspace/test-docker/src/main/resources
[[1;34mINFO[m] 
[[1;34mINFO[m] [1m--- [0;32mmaven-compiler-plugin:3.1:compile[m [1m(default-compile)[m @ [36mjenkins-dind-test[0;1m ---[m
[[1;34mINFO[m] Changes detected - recompiling the module!
[[1;33mWARNING[m] File encoding has not been set, using platform encoding UTF-8, i.e. build is platform dependent!
[[1;34mINFO[m] Compiling 2 source files to /var/jenkins_home/workspace/test-docker/target/classes
[[1;34mINFO[m] 
[[1;34mINFO[m] [1m--- [0;32mmaven-resources-plugin:2.6:testResources[m [1m(default-testResources)[m @ [36mjenkins-dind-test[0;1m ---[m
[[1;33mWARNING[m] Using platform encoding (UTF-8 actually) to copy filtered resources, i.e. build is platform dependent!
[[1;34mINFO[m] skip non existing resourceDirectory /var/jenkins_home/workspace/test-docker/src/test/resources
[[1;34mINFO[m] 
[[1;34mINFO[m] [1m--- [0;32mmaven-compiler-plugin:3.1:testCompile[m [1m(default-testCompile)[m @ [36mjenkins-dind-test[0;1m ---[m
[[1;34mINFO[m] Nothing to compile - all classes are up to date
[[1;34mINFO[m] 
[[1;34mINFO[m] [1m--- [0;32mmaven-surefire-plugin:2.12.4:test[m [1m(default-test)[m @ [36mjenkins-dind-test[0;1m ---[m
[[1;34mINFO[m] Surefire report directory: /var/jenkins_home/workspace/test-docker/target/surefire-reports

-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running BaseJunit4Test
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.205 sec

Results :

Tests run: 1, Failures: 0, Errors: 0, Skipped: 0

[[1;34mINFO[m] 
[[1;34mINFO[m] [1m--- [0;32mmaven-jar-plugin:2.4:jar[m [1m(default-jar)[m @ [36mjenkins-dind-test[0;1m ---[m
[[1;34mINFO[m] Building jar: /var/jenkins_home/workspace/test-docker/target/jenkins-dind-test-1.0-SNAPSHOT.jar
[[1;34mINFO[m] 
[[1;34mINFO[m] [1m--- [0;32mmaven-shade-plugin:1.2.1:shade[m [1m(default)[m @ [36mjenkins-dind-test[0;1m ---[m
[[1;34mINFO[m] Including junit:junit:jar:4.12 in the shaded jar.
[[1;34mINFO[m] Including org.hamcrest:hamcrest-core:jar:1.3 in the shaded jar.
[[1;34mINFO[m] Replacing original artifact with shaded artifact.
[[1;34mINFO[m] Replacing /var/jenkins_home/workspace/test-docker/target/jenkins-dind-test-1.0-SNAPSHOT.jar with /var/jenkins_home/workspace/test-docker/target/jenkins-dind-test-1.0-SNAPSHOT-shaded.jar
[[1;34mINFO[m] 
[[1;34mINFO[m] [1m--- [0;32mmaven-install-plugin:2.4:install[m [1m(default-install)[m @ [36mjenkins-dind-test[0;1m ---[m
[[1;34mINFO[m] Installing /var/jenkins_home/workspace/test-docker/target/jenkins-dind-test-1.0-SNAPSHOT.jar to /root/.m2/repository/jenkins-dind-test/jenkins-dind-test/1.0-SNAPSHOT/jenkins-dind-test-1.0-SNAPSHOT.jar
[[1;34mINFO[m] Installing /var/jenkins_home/workspace/test-docker/dependency-reduced-pom.xml to /root/.m2/repository/jenkins-dind-test/jenkins-dind-test/1.0-SNAPSHOT/jenkins-dind-test-1.0-SNAPSHOT.pom
[[1;34mINFO[m] [1m------------------------------------------------------------------------[m
[[1;34mINFO[m] [1;32mBUILD SUCCESS[m
[[1;34mINFO[m] [1m------------------------------------------------------------------------[m
[[1;34mINFO[m] Total time: 6.791 s
[[1;34mINFO[m] Finished at: 2017-07-20T14:21:34Z
[[1;34mINFO[m] Final Memory: 17M/60M
[[1;34mINFO[m] [1m------------------------------------------------------------------------[m
+ java -jar /var/jenkins_home/workspace/test-docker/target/jenkins-dind-test-1.0-SNAPSHOT.jar
Hello World!
+ cp /var/jenkins_home/workspace/test-docker/target/jenkins-dind-test-1.0-SNAPSHOT.jar ./deployment
+ cd ./deployment
+ docker build . -t java-test:1.0
Sending build context to Docker daemon  370.7kB

Step 1 : FROM java:latest
 ---> d23bdf5b1b1b
Step 2 : COPY jenkins-dind-test-1.0-SNAPSHOT.jar /home
 ---> 90ce64dcf502
Removing intermediate container a3bad5e7aac7
Step 3 : CMD java -jar /home/jenkins-dind-test-1.0-SNAPSHOT.jar
 ---> Running in 208b87830507
 ---> aee3d08e7ff1
Removing intermediate container 208b87830507
Successfully built aee3d08e7ff1
+ docker run java-test:1.0
Hello World!
Recording test results
Build step 'Publish JUnit test result report' changed build result to UNSTABLE
Finished: UNSTABLE
```


=。=不是很详细，有不懂的私下联系


