#!/bin/bash
if [ $# -ne 1 ]
then
   echo "usage: $(basename $0) jmx_port"
   echo "where:"
   echo "    jmx_port - Listening port of JMX"
   exit 1
fi
typeset -r JMX_PORT=$1
exec java -cp target/test-classes -Dcom.sun.management.jmxremote.port="$JMX_PORT" -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false com.boundary.plugin.sdk.jmx.ExampleAgent
