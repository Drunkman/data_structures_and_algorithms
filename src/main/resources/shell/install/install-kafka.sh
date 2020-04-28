#!/bin/bash

# Common properties
USER_HOME="/Users/mgy"
APPS_PATH="${USER_HOME}/apps"
CONF_PATH="${USER_HOME}/conf"
INSTALL_PATH="${USER_HOME}/INSTALL"
LOG_PATH="${USER_HOME}/logs"

# kafka properties
KAFKA_DIR="kafka_2.12-2.2.0"
KAFKA_FILENAME="${KAFKA_DIR}.tgz"

echo ">>> Cleanup installed directory"
if [[ -d "${APPS_PATH}/${KAFKA_DIR}" ]]; then
	rm -rf "${APPS_PATH}/${KAFKA_DIR}"
fi

echo ">>> Create default directory"
if [[ ! -d "${APPS_PATH}" ]]; then
    mkdir -p ${APPS_PATH}
fi

if [[ ! -d "${LOG_PATH}" ]]; then
    mkdir -p ${LOG_PATH}
fi

if [[ ! -d "${CONF_PATH}" ]]; then
    mkdir -p ${CONF_PATH}
fi

if [[ ! -d "${INSTALL_PATH}/kafka" ]]; then
    mkdir -p ${INSTALL_PATH}/kafka
fi

echo ">>> Install kafka ${KAFKA_FILENAME}"
cd ${INSTALL_PATH}/kafka
wget http://mirrors.tuna.tsinghua.edu.cn/apache/kafka/2.2.0/kafka_2.12-2.2.0.tgz
tar zxf ${KAFKA_FILENAME} --directory=${APPS_PATH}

echo ">>> Create symbolic link"
cp -R ${APPS_PATH}/${KAFKA_DIR}/config ${APPS_PATH}/${KAFKA_DIR}/config.default
ln -s ${APPS_PATH}/${KAFKA_DIR}/config ${CONF_PATH}/kafka

logpath="${APPS_PATH}/${KAFKA_DIR}/logs"
mkdir -p ${logpath}
sed -i "s#^log\.dirs.*#log\.dirs=${logpath}#g" ${APPS_PATH}/${KAFKA_DIR}/config/server.properties
ln -s ${logpath} ${LOG_PATH}/kafka

echo ">>> Install kafka finished"