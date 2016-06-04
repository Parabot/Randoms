#! /bin/bash

sleep 15
curl -sS "http://v3.bdn.parabot.org/api/bot/create/random?build_id=$TRAVIS_BUILD_ID&version=$RANDOMS_VERSION" >/dev/null