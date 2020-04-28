#!/bin/bash

# Common properties
USER_HOME="/Users/mgy"
JDK_DIR="jdk1.8.0_181"
APPS_PATH="${USER_HOME}/apps"
INSTALL_PATH="${USER_HOME}/INSTALL"

shell_path="${USER_HOME}/scripts/install"

# Delete existed jdk
rpm -qa | grep jdk | while read -r line ; do
	echo "remove " $line
	sudo rpm -e --nodeps $line
done
sudo rm -f /usr/bin/java

# Clean up directory
if [ -d "${APPS_PATH}/${JDK_DIR}" ]; then
	echo "Remove installed jdk directory"
	sudo rm -rf ${APPS_PATH}/${JDK_DIR}
fi

if [ -L "${APPS_PATH}/jdk" ];then
	echo "Remove jdk symbolic link.(${APPS_PATH/jdk})"
	sudo rm -f ${APPS_PATH}/jdk
fi

echo ">>> Install JDK"

sudo mkdir -p ${INSTALL_PATH}/java
cd ${INSTALL_PATH}/java
sudo cp ${shell_path}/java-packages/jdk-8u181-linux-x64.tar.gz ${INSTALL_PATH}/java/jdk-8u181-linux-x64.tar.gz
# sudo wget https://mirrors.huaweicloud.com/java/jdk/8u181-b13/jdk-8u181-linux-x64.tar.gz

# Check jdk os version
echo "os version `uname -i`"

if [ `uname -i` != "x86_64" ]; then
	echo "Unsupported os version"
	exit 1
fi

sudo tar zxf jdk-8u181-linux-x64.tar.gz --directory=${APPS_PATH}
sudo chown -R mgy:mgy ${APPS_PATH}/${JDK_DIR}
sudo ln -s ${APPS_PATH}/${JDK_DIR} ${APPS_PATH}/jdk
sudo chown -h mgy:mgy ${APPS_PATH}/jdk

if [ -f "/etc/profile.d/java.sh" ]; then
    echo "/etc/profile.d/java.sh already exists."
else
    sudo touch /etc/profile.d/java.sh
    echo "export JAVA_HOME=${APPS_PATH}/jdk" | sudo tee -a /etc/profile.d/java.sh
    echo 'export PATH=${JAVA_HOME}/bin:${PATH}' | sudo tee -a /etc/profile.d/java.sh
fi

#sudo ln -s ${APPS_PATH}/jdk/bin/java /usr/bin/java

echo ">>> Install JDK finished"