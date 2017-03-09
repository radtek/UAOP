title sso DataSyncMessageSendApp

call setEnv.bat
java -Xms512m -Xmx512m  com.hisign.sso.service.impl.msg.DataSyncMessageSendApp %1 %2