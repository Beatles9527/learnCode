## 一、docker安装

### 1.1、Linux下的安装

**工具下载及安装**

1、安装驱动包

> yum install -y yum-utils device-mapper-persistent-data lvm2
>
> 安装yum的工具集（yum-utils），驱动包（device-mapper-persistent-data、lvm2）

2、设置yum的阿里云安装源

> yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo

3、设置yum的镜像缓存

> yum makecache fast