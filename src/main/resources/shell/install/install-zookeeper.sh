#!/bin/bash

# Common properties
USER_HOME="/Users/mgy"
APPS_PATH="${USER_HOME}/apps"
CONF_PATH="${USER_HOME}/conf"
INSTALL_PATH="${USER_HOME}/INSTALL"
DATA_PATH="${USER_HOME}/data"

# zookeeper properties
ZOOKEEPER_DIR="zookeeper-3.4.14"
ZOOKEEPER_FILENAME="${ZOOKEEPER_DIR}.tar.gz"

echo ">>> Cleanup installed directory"
if [[ -d "${APPS_PATH}/${ZOOKEEPER_DIR}" ]]; then
	rm -rf "${APPS_PATH}/${ZOOKEEPER_DIR}"
fi

echo ">>> Create default directory"
if [[ ! -d "${APPS_PATH}" ]]; then
    mkdir -p ${APPS_PATH}
fi

if [[ ! -d "${CONF_PATH}" ]]; then
    mkdir -p ${CONF_PATH}
fi

if [[ ! -d "${DATA_PATH}" ]]; then
    mkdir -p ${DATA_PATH}
fi

if [[ ! -d "${INSTALL_PATH}/zookeeper" ]]; then
    mkdir -p ${INSTALL_PATH}/zookeeper
fi

echo ">>> Install zookeeper ${ZOOKEEPER_FILENAME}"
cd ${INSTALL_PATH}/zookeeper
wget https://mirrors.tuna.tsinghua.edu.cn/apache/zookeeper/zookeeper-3.4.14/zookeeper-3.4.14.tar.gz
tar zxf ${ZOOKEEPER_FILENAME} --directory=${APPS_PATH}

echo ">>> Create symbolic link"
cp -R ${APPS_PATH}/${ZOOKEEPER_DIR}/conf ${APPS_PATH}/${ZOOKEEPER_DIR}/conf.default
ln -s ${APPS_PATH}/${ZOOKEEPER_DIR}/conf ${CONF_PATH}/zookeeper

datapath="${APPS_PATH}/${ZOOKEEPER_DIR}/data"
mkdir -p ${datapath}
sed -i "s#^dataDir.*#dataDir=${datapath}#g" ${APPS_PATH}/${ZOOKEEPER_DIR}/conf/zoo_sample.cfg
ln -s ${datapath} ${DATA_PATH}/zookeeper

echo ">>> Install zookeeper finished"