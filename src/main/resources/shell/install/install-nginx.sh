#!/bin/bash

# Common properties
USER_HOME="/Users/mgy"
APPS_PATH="${USER_HOME}/apps"
CONF_PATH="${USER_HOME}/conf"
INSTALL_PATH="${USER_HOME}/INSTALL"
LOG_PATH="${USER_HOME}/logs"

# Nginx properties
NGINX_DIR="nginx-1.14.2"
NGINX_FILENAME="${NGINX_DIR}.tar.gz"
PCRE_DIR="pcre-8.42"
PCRE_FILENAME="${PCRE_DIR}.tar.gz"
OPENSSL_DIR="openssl-1.1.1b"
OPENSSL_FILENAME="${OPENSSL_DIR}.tar.gz"
ZLIB_DIR="zlib-1.2.11"
ZLIB_FILENAME="${ZLIB_DIR}.tar.gz"

shell_path="${USER_HOME}/scripts/install"

echo ">>> Cleanup installed directory"
if [[ -d "${APPS_PATH}/${NGINX_DIR}" ]]; then
    sudo rm -rf ${APPS_PATH}/${NGINX_DIR}
fi

# Create default directory
if [[ ! -d "${APPS_PATH}" ]]; then
    sudo mkdir -p ${APPS_PATH}
    sudo chown -R mgy:mgy ${APPS_PATH}
fi

if [[ ! -d "${LOG_PATH}" ]]; then
    sudo mkdir -p ${LOG_PATH}
    sudo chown -R mgy:mgy ${LOG_PATH}
fi

if [[ ! -d "${CONF_PATH}" ]]; then
    sudo mkdir -p ${CONF_PATH}
    sudo chown -R mgy:mgy ${CONF_PATH}
fi

if [[ ! -d "${INSTALL_PATH}/nginx" ]]; then
    sudo mkdir -p ${INSTALL_PATH}/nginx
    sudo chown -R mgy:mgy ${INSTALL_PATH}
    sudo chown -R mgy:mgy ${INSTALL_PATH}/nginx
fi

echo ">>> Install nginx ${NGINX_FILENAME}"
sudo cp ${shell_path}/nginx-packages/nginx-1.14.2.tar.gz ${INSTALL_PATH}/nginx/nginx-1.14.2.tar.gz
sudo cp ${shell_path}/nginx-packages/pcre-8.42.tar.gz ${INSTALL_PATH}/nginx/pcre-8.42.tar.gz
sudo cp ${shell_path}/nginx-packages/openssl-1.1.1b.tar.gz ${INSTALL_PATH}/nginx/openssl-1.1.1b.tar.gz
sudo cp ${shell_path}/nginx-packages/zlib-1.2.11.tar.gz ${INSTALL_PATH}/nginx/zlib-1.2.11.tar.gz
cd ${INSTALL_PATH}/nginx

# Unarchive nginx
sudo tar zxf ${NGINX_FILENAME}
# Unarchive libs for build.
sudo mkdir -p ${NGINX_DIR}/lib
sudo tar zxf ${PCRE_FILENAME} --directory=${NGINX_DIR}/lib
sudo tar zxf ${OPENSSL_FILENAME} --directory=${NGINX_DIR}/lib
sudo tar zxf ${ZLIB_FILENAME} --directory=${NGINX_DIR}/lib

echo ">>> configure nginx"
cd ${NGINX_DIR}
sudo ./configure --prefix=${APPS_PATH}/${NGINX_DIR} \
            --with-pcre=lib/${PCRE_DIR} \
            --with-openssl=lib/${OPENSSL_DIR} \
            --with-zlib=lib/${ZLIB_DIR} \
            --with-http_ssl_module \
            --with-http_stub_status_module \
            --user=mgy --group=mgy
rc=$?
if [[ ${rc} != 0 ]]; then
    echo ">>> Install nginx failed. (configure error)"
    exit ${rc}
fi

echo ">>> make nginx"
if ! sudo make; then
    echo ">>> Install nginx failed. (make error)"
    exit 1
fi

echo ">>> install nginx"
if ! sudo make install; then
    echo ">>> Install nginx failed. (make install error)"
    exit 1
fi
sudo chown -R mgy:mgy ${APPS_PATH}/${NGINX_DIR}
sudo chown root:mgy ${APPS_PATH}/${NGINX_DIR}/sbin/nginx
sudo chmod +s ${APPS_PATH}/${NGINX_DIR}/sbin/nginx

# Create symbolic link
echo ">>> Create log directory"
sudo ln -s ${APPS_PATH}/${NGINX_DIR}/logs ${LOG_PATH}/nginx
sudo chown -R mgy:mgy ${APPS_PATH}/${NGINX_DIR}/logs
sudo chown -h mgy:mgy ${LOG_PATH}/nginx

echo ">>> Create conf directory"
sudo mv ${APPS_PATH}/${NGINX_DIR}/conf ${APPS_PATH}/${NGINX_DIR}/conf.default
sudo cp -R ${shell_path}/nginx-conf ${APPS_PATH}/${NGINX_DIR}/conf
sudo ln -s ${APPS_PATH}/${NGINX_DIR}/conf ${CONF_PATH}/nginx
sudo chown -R mgy:mgy ${APPS_PATH}/${NGINX_DIR}/conf
sudo chown -h mgy:mgy ${CONF_PATH}/nginx

echo ">>> Install nginx finished"
