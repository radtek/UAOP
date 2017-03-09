#!/bin/sh
. ./setEnv.sh
$JAVA_HOME/bin/java -Xms512m -Xmx512m -classpath $CLASSPATH com.hisign.sso.service.impl.msg.DataSyncMessageSendApp $1 $2
