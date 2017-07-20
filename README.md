# jenkin-dind-sample
jenkins with dind(docker in docker)

简单的基于jenkins，docker的持续集成流程

## 步骤一
安装Docker   地址：https://docs.docker.com/engine/installation/

## 步骤二
构建Jenkins的dind镜像，由于jenkins自带的docker镜像将jenkins独立为一个用户组，单纯地在镜像内部将/var/run/docker.sock和主机的docker.sock挂载并不能解决用户组权限的问题，
也就是会访问不了文件docker.sock，因此从dockerfile入手，基于docker和jenkins的dockerfile，修改成为了主目录下的[dockerfile](#./dockerfile)

在安装有docker的主机上运行
```sudo docker built . -t jenkins-docker:1/0 ```生成可以使用的jenkins-docker镜像

## 步骤三
运行jenkins镜像

在安装有docker的主机上运行
```sudo docker run -d -p 8080:8080  -v /var/run/docker.sock:/var/run/docker.sock -v (主机上自定义一个文件夹地址如~/jenkins):/var/jenkins_home -ti jenkins-docker:1/0 ```

之后访问本机的8080端口就能看到jenkins，根据提示一步一步完成配置即可。

## 步骤四
自己完成程序的编码，测试的编写，通过jenkins对git，docker,mvn(java)，junit(java)进行配置和脚本的编写
jenkins配置完成之后，理想效果是点击构建按钮之后，jenkins自动从git上拉取更新，进行编译，输出测试结果，构建出可运行的镜像(可参考[dockerfile](#./dockerfile))，将镜像部署到docker里去。


=。=不是很详细，有不懂的私下联系


