#!/bin/sh

function build {
    echo "Build Start!";
    cd /home/mgy/deploy/gdc-static;
    npm install;
    echo "npm install finish!";
    npm run build;
    echo "Build Finish!";
}

COMMAND=$1
case ${COMMAND} in
        "build")
                build
                ;;
esac

exit 0;