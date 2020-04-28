#!/bin/sh

PROJECT_NAME=$2
PROJECT_PORT=$3
BASE_DIR=/Users/mgy/deploy/${PROJECT_NAME}
PID=${BASE_DIR}/${PROJECT_NAME}.pid
LOG_PATH=/Users/mgy/logs/${PROJECT_NAME}

JVM_OPTS="-server -Dfile.encoding=UTF-8 -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=256m -Xss384k -Xms2g -Xmx2g -Xmn512m "
HEAPDUMP_OPTS="-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=${LOG_PATH}"
GC_CONFIG="-Xloggc:${LOG_PATH}/${PROJECT_NAME}.gc.out"
GC_OPTS="-XX:-UseAdaptiveSizePolicy -XX:+UseParNewGC -XX:ParallelGCThreads=2  \
 -XX:SurvivorRatio=8 -XX:TargetSurvivorRatio=90 -XX:MaxTenuringThreshold=15 \
 -XX:+CMSClassUnloadingEnabled -XX:+ExplicitGCInvokesConcurrent \
 -XX:+CMSParallelRemarkEnabled -XX:+UseConcMarkSweepGC -XX:CMSInitiatingOccupancyFraction=70 -XX:+UseCMSInitiatingOccupancyOnly"
JAVA_OPTS="$JAVA_OPTS $JVM_OPTS $GC_OPTS $HEAPDUMP_OPTS $GC_CONFIG"

function stop
{
        sleep 10
        if [[ -f ${PID} ]] ; then
                kill -9 `cat ${PID}` ;
        fi
}

function check_mgy
{
   USER=`/usr/bin/whoami`
   if [[ ${USER} != "mgy" ]] ;then
           echo "change user mgy ";
           exit;
   fi
}

function checkProcess
{
        if [[ -f ${PID} ]] ; then
                pid=`cat ${PID}`
                if [[ `ps -p${pid} | grep -v PID | wc -l` -eq 1 ]] ; then
                        echo "App alread started!"
                        exit 1;
                fi
        fi
}


function checkAppL7Status
{
        i=0;
        while [[ ${i} -lt 30 ]] ; do
                httpStatus=`curl -s -o /dev/null -w "%{http_code}" 127.0.0.1:$1`;
                if [[ ${httpStatus} -eq 404 ]] ; then
                        echo "l7 check Ok!"
                        return 0;
                fi

                echo "App l7status is " ${httpStatus}
                sleep 3;

                ((i++));
        done;

        return 1;
}

function start
{
        checkProcess

        cd ${BASE_DIR}/libs && jar=`ls -t *.jar | head -1`
        logdir=/Users/mgy/logs
        if [[ ! -d "${logdir}" ]]; then
            mkdir -p ${logdir}
        fi
        nohup java ${JAVA_OPTS} -Dspring.profiles.active=dev -jar ${BASE_DIR}/libs/${jar} > /Users/mgy/logs/${PROJECT_NAME}.nohup.log 2>&1 &

        sleep 2

        appid=$!

        if echo ${appid} | egrep -q '^[0-9]+$'; then
                echo ${appid} > ${PID}
        else
                echo 'Service start failed ! Pleash Check Log ! '
                cat  /Users/mgy/logs/${PROJECT_NAME}.nohup.log
                exit 1;
        fi

        checkAppL7Status ${PROJECT_PORT};

        if [[ $? -eq 1 ]] ; then
                 echo 'L7Check Error!';
                 exit 1;
        fi

        echo 'L7Check OK!';

        echo 'Service started!'

        sleep 1

}


COMMAND=$1
check_mgy
case ${COMMAND} in

     "start")
        start
        ;;

     "stop")
        stop
        ;;

     "restart")
        stop
        start
        ;;

esac

exit 0;