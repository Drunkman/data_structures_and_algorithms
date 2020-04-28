#!/bin/bash

which node
if [[ $? == 0 ]]; then
    echo ">>> nodejs has been installed."
    exit 0
fi

echo ">>> install nodejs"
curl -sL https://rpm.nodesource.com/setup_8.x | sudo bash -

rc=$?
if [[ ${rc} != 0 ]]; then
    echo ">>> setup 8+ version fail"
    exit ${rc}
fi

sudo yum install nodejs
